package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierContactViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISupplierContactViewRepository extends JpaRepository<CSupplierContactViewModel, Long> {

	@Query(nativeQuery = true, value = "Select * FROM cmv_supplier_contacts where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and supplier_contact_id = ?3")
	CSupplierContactViewModel FnShowParticularRecord(int company_id, int company_branch_id, int supplier_contact_id);

	@Query(value = "Select * FROM cmv_supplier_contacts order by supplier_contact_id Desc", nativeQuery = true)
	Page<CSupplierContactViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_supplier_contacts where is_delete =0  order by supplier_contact_id Desc", nativeQuery = true)
	Page<CSupplierContactViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query("from CSupplierContactViewModel csvm where csvm.is_delete =0 and csvm.supplier_id = ?1")
	List<CSupplierContactViewModel> FnShowParticularRecordForUpdate(int supplier_id);


}
