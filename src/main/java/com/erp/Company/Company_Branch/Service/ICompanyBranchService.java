package com.erp.Company.Company_Branch.Service;

import com.erp.Company.Company_Branch.Model.CCompanyBranchModel;
import com.erp.Company.Company_Branch.Model.CCompanyBranchViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICompanyBranchService {

	JSONObject FnAddUpdateRecord(CCompanyBranchModel companyBranchModel);

	CCompanyBranchModel FnDeleteRecord(int company_branch_id, String deleted_by);

	Page<CCompanyBranchViewModel> FnShowAllRecords(Pageable pageable);

	Page<CCompanyBranchViewModel> FnShowAllActiveRecords(Pageable pageable);

	Page<CCompanyBranchViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	CCompanyBranchModel FnShowParticularRecordForUpdate(int company_branch_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	List<CCompanyBranchViewModel> FnShowFilterRecords(JSONObject jsonQuery);

	JSONObject FnMShowFilterRecords(JSONObject jsonQuery, int page, int size);

}
