package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IStIndentDetailsRepository extends JpaRepository<CStIndentDetailsModel, Integer> {

	@Query(value = "FROM CStIndentDetailsModel model where model.indent_details_id = ?1 and model.company_id = ?2")
	CStIndentDetailsModel FnShowParticularRecordForUpdate(int indent_details_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set is_delete = true, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	int updateStatus(String indent_no, Integer indent_version, String financial_year, int company_id);


	@Query(value = "SELECT * FROM stv_indent_details  WHERE indent_no = ?1 and indent_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowIndentDetailsRecords(String indent_no, int indent_version, String financial_year,
	                                                     int company_id);

	@Query(value = "SELECT * FROM stv_indent_details  WHERE indent_no = ?1 and indent_item_status = 'A' and po_item_status IN ('IPO', 'P')  and indent_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowPrecloseIndentDetailsRecords(String indent_no, int indent_version, String financial_year,
														 int company_id);

	@Query(value = "SELECT indent_details_id, product_material_id, product_material_preclosed_quantity, indent_item_status FROM st_indent_details  WHERE indent_no = ?1 and financial_year = ?2 and company_id= ?3 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> GetallPrecloseEntries(String indent_no, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set is_delete = 1,deleted_by =?4 , deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteIndentDetails(String indent_no, int indent_version, int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set indent_item_status = 'X' , modified_by =?4 , modified_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void cancelIndentDetails(String indent_no, int indent_version, int company_id, String user_name);


	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set product_material_preclosed_quantity = ?1, preclosed_remark = ?2,  indent_item_status = 'Z'," +
			"  modified_by =?6 , modified_on = CURRENT_TIMESTAMP() where indent_no = ?4 and indent_details_id = ?3 and indent_version = ?7 and company_id = ?5", nativeQuery = true)
	void updatePreClosedDetailsRecord(Double preclosed_quantity, String preclosed_remark, int indent_details_id, String indent_no,  int company_id, int userId, int indent_version );


	@Modifying
	@Transactional
	@Query(value = "UPDATE sm_product_rm_commercial SET cost_center_id = ?2 WHERE product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
	void updateCostCenterForProductMaterialIds(String productMaterialIds, int costCenterId);

	@Query(value = "SELECT product_rm_id FROM sm_product_rm_commercial WHERE product_rm_id = ?1 AND cost_center_id = 0 AND is_delete = 0", nativeQuery = true)
	String getCostCenterForProductMaterialIds(String productMaterialId);


	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set indent_item_status = ?1,product_material_issue_quantity = ?2 ,product_material_issue_weight = ?3 WHERE indent_details_id = ?4 AND is_delete = 0  AND company_id = ?5", nativeQuery = true)
	void updateIndentDetailRecord(String indentStatus, double product_material_issue_quantity,
			double product_material_issue_weight, Integer indent_details_id, int company_id);

	@Query(value = "FROM CStIndentDetailsModel model WHERE model.is_delete = false and model.indent_no IN ?1")
	List<CStIndentDetailsModel> getIndentDetails(List<String> getDistinctIndentNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set po_item_status = 'P' WHERE indent_no = ?1 AND product_material_id = ?2 AND company_id = ?3 AND is_delete = 0", nativeQuery = true)
	void revertIndentDetailsPOStatus(String indentNumber, String productMaterialId, int companyId);
}
