package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierBranchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface ISupplierBranchRepository extends JpaRepository<CSupplierBranchModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_branch set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supplier_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int supplier_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_supplier_branch WHERE supp_branch_name = ?1 and supplier_id = ?2 and company_id = ?3")
	CSupplierBranchModel getCheck(String supp_branch_name, int supplier_id, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_branch set modified_on = CURRENT_TIMESTAMP() where supp_branch_id= ?1 and company_id=?2", nativeQuery = true)
	void updateSupplierBranch(int supp_branch_id, int company_id);

	@Query(value = "SELECT * FROM cmv_supplier_branch_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_branch set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supp_branch_id=?1", nativeQuery = true)
	void FnDeleteSupplierBranchRecord(int supp_branch_id, String deleted_by);

}
