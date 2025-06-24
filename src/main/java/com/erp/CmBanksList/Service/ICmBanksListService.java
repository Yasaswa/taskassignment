package com.erp.CmBanksList.Service;

import com.erp.CmBanksList.Model.CCmBanksListModel;
import com.erp.CmBanksList.Model.CCmBanksListViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICmBanksListService {

	Map<String, Object> FnAddUpdateRecord(CCmBanksListModel cCmBanksListModel);

	Object FnDeleteRecord(int bank_id, int company_id, String deleted_by);

	Object FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int bank_id, int company_id);

	Page<CCmBanksListViewModel> FnShowParticularRecord(int bank_id, Pageable pageable, int company_id);

	Object FnShowAllActiveRecords(int company_id, Pageable pageable);


}
