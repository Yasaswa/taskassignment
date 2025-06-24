package com.erp.Company.Company_Directors.Service;

import com.erp.Company.Company_Directors.Model.CCompanyDirectorsModel;
import com.erp.Company.Company_Directors.Model.CCompanyDirectorsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompanyDirectorsService {

	Page<CCompanyDirectorsViewModel> FnShowAllRecords(Pageable pageable);

	JSONObject FnAddUpdateRecord(CCompanyDirectorsModel companydirectorsModel);

	CCompanyDirectorsModel FnDeleteRecord(int company_director_id);

	Page<CCompanyDirectorsViewModel> FnShowAllActiveRecords(Pageable pageable);

	Page<CCompanyDirectorsViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	CCompanyDirectorsViewModel FnShowParticularRecordForUpdate(int company_director_id);


}
