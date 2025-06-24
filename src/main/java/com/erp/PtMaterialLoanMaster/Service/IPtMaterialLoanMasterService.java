package com.erp.PtMaterialLoanMaster.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanMasterModel;


public interface IPtMaterialLoanMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String addOrIssued);


	Map<String, Object> FnDeleteRecord(int loanMasterTransactionId, String userName);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String loanNo, int loanVersion, String financialYear
			, int companyId );
}
