package com.erp.StRawMaterialReturnMaster.Repository;

import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IStRawMaterialReturnMasterModelRepository extends JpaRepository<CStRawMaterialReturnMasterModel, Long> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE st_indent_material_issue_return_master SET is_delete = 1, deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP() WHERE issue_return_master_transaction_id = ?1 AND company_id = ?2", nativeQuery = true)
	void FnDeleteRawMaterialReturnMasterRecord(int issue_return_master_transaction_id, int company_id, String deleted_by);

}