package com.erp.Company.Company_Bank.Service;

import com.erp.Company.Company_Bank.Model.CCompanyBankViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompanyBankService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int company_bank_id);

	Page<CCompanyBankViewModel> FnShowAllActiveRecords(Pageable pageable);

	CCompanyBankViewModel FnShowParticularRecord(int company_bank_id);

	List<CCompanyBankViewModel> FnShowParticularRecordForUpdate(int company_branch_id, int company_id, Pageable pageable);

	Page<CCompanyBankViewModel> FnShowAllRecords(Pageable pageable);


}
