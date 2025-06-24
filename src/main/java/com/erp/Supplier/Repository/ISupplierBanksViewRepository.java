package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierBanksViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISupplierBanksViewRepository extends JpaRepository<CSupplierBanksViewModel, Long> {

	@Query(nativeQuery = true, value = "Select * FROM cmv_supplier_banks where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and supp_bank_id = ?3")
	CSupplierBanksViewModel FnShowParticularRecord(int company_id, int company_branch_id, int supp_bank_id);

	@Query(value = "Select * FROM cmv_supplier_banks order by supp_bank_id Desc", nativeQuery = true)
	Page<CSupplierBanksViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_supplier_banks where is_delete =0  order by supp_bank_id Desc", nativeQuery = true)
	Page<CSupplierBanksViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query("from CSupplierBanksViewModel snvm where snvm.is_delete =0 and snvm.supp_branch_id = ?1")
	List<CSupplierBanksViewModel> FnShowParticularRecordForUpdate(int supp_branch_id);


	@Query(value = "FROM CSupplierBanksViewModel csbvm where csbvm.company_id =?1 and csbvm.company_branch_id =?2")
	List<CSupplierBanksViewModel> checkRecordIfExist(int company_id, int company_branch_id);


}
