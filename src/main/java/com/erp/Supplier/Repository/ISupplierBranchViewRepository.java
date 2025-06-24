package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierBranchViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISupplierBranchViewRepository extends JpaRepository<CSupplierBranchViewModel, Long> {

	@Query(nativeQuery = true, value = "Select * FROM cmv_supplier_branch where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and supp_branch_id = ?3")
	CSupplierBranchViewModel FnShowParticularRecord(int company_id, int company_branch_id, int supp_branch_id);

	@Query(value = "Select * FROM cmv_supplier_branch order by supp_branch_id Desc", nativeQuery = true)
	Page<CSupplierBranchViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_supplier_branch where is_delete =0  order by supp_branch_id Desc", nativeQuery = true)
	Page<CSupplierBranchViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_supplier_branch where is_delete =0 and supp_branch_id = ?1", nativeQuery = true)
	CSupplierBranchViewModel FnShowParticularRecordForUpdate(int supp_branch_id);

}
