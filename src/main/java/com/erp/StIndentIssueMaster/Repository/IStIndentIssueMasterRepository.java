package com.erp.StIndentIssueMaster.Repository;

import com.erp.StIndentIssueMaster.Model.CStIndentIssueMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Map;


public interface IStIndentIssueMasterRepository extends JpaRepository<CStIndentIssueMasterModel, Integer> {

	@Query(value = "FROM CStIndentIssueMasterModel model where model.is_delete =0 and model. issue_master_transaction_id = ?1 and model.company_id = ?2")
	CStIndentIssueMasterModel FnShowParticularRecordForUpdate(int issue_master_transaction_id, int company_id);


	@Query(value = "FROM CStIndentIssueMasterModel model WHERE model.issue_no = ?1 and model.issue_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CStIndentIssueMasterModel getExistingRecord(String issue_no, Integer issue_version, String financial_year,
	                                            int company_id);


	@Query(value = "SELECT * FROM stv_indent_material_issue_summary  WHERE issue_no = ?1 and issue_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowIndentIssueMasterRecord(String issue_no, int issue_version, String financial_year,
	                                                  int company_id);
	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_master set is_delete = 1, deleted_by =?3, deleted_on = CURRENT_TIMESTAMP() where issue_no = ?1 and company_id = ?2", nativeQuery = true)
	void FnDeleteIndentIssueMasterRecord(String issue_no, int company_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_master set accepted_by_id = ?1 where indent_no = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateIssueMaster(Integer acceptedById, String indent_no, Integer company_id);


	//Get Issue Master Model based on set no
	@Query("SELECT model FROM CStIndentIssueMasterModel model WHERE model.set_no = :set_no AND model.company_id = :company_id AND model.is_delete = 0")
	CStIndentIssueMasterModel FnFetchMasterModelBySetno(@Param("set_no") String set_no, @Param("company_id") Integer company_id);

}
