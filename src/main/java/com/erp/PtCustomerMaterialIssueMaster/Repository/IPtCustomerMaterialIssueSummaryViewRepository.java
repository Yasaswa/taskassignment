package com.erp.PtCustomerMaterialIssueMaster.Repository;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueMasterViewModel;

public interface IPtCustomerMaterialIssueSummaryViewRepository extends JpaRepository<CPtCustomerMaterialIssueMasterViewModel, Integer>{

	
	
	@Query(value = "FROM CPtCustomerMaterialIssueMasterViewModel model WHERE model.customer_material_issue_no = ?1 and model.customer_material_issue_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	Map<String, Object> FnShowCustomerMaterialIssueMasterRecord(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);

}
