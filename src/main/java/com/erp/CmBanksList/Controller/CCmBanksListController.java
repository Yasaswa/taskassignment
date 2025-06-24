package com.erp.CmBanksList.Controller;

import com.erp.CmBanksList.Model.CCmBanksListModel;
import com.erp.CmBanksList.Model.CCmBanksListViewModel;
import com.erp.CmBanksList.Service.ICmBanksListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/CmBanksList")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmBanksListController {

	@Autowired
	ICmBanksListService iCmBanksListService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCmBanksListModel CmBanksListModel) {
		return iCmBanksListService.FnAddUpdateRecord(CmBanksListModel);

	}

	@DeleteMapping("/FnDeleteRecord/{bank_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int bank_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCmBanksListService.FnDeleteRecord(bank_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	Object FnShowAllActiveRecords(@PathVariable int company_id, Pageable pageable) throws SQLException {
		Object cCmBanksListViewModel = null;
		cCmBanksListViewModel = iCmBanksListService.FnShowAllActiveRecords(company_id, pageable);

		return cCmBanksListViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{bank_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int bank_id, @PathVariable int company_id) {
		return iCmBanksListService.FnShowParticularRecordForUpdate(bank_id, company_id);
	}


	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object reportRecordObj = null;
		try {
			reportRecordObj = iCmBanksListService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportRecordObj;

	}

	@GetMapping("/FnShowParticularRecords/{bank_id}/{company_id}")
	public Page<CCmBanksListViewModel> FnShowParticularRecord(@PathVariable int bank_id, Pageable pageable, @PathVariable int company_id) {
		return iCmBanksListService.FnShowParticularRecord(bank_id, pageable, company_id);

	}


}
