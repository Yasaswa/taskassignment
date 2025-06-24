package com.erp.Transporter_Banks.Service;

import com.erp.Transporter.Model.CTransporterBanksViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransporterBanksService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int transporter_bank_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int transporter_bank_id);

	Object FnShowParticularRecord(int company_id, int company_branch_id, int transporter_bank_id);

	List<CTransporterBanksViewModel> FnShowParticularRecordForUpdate(int company_id, int company_branch_id,
	                                                                 int transporter_id);

}
