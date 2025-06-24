package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ISupplierViewRepository extends JpaRepository<CSupplierViewModel, Long> {


	@Query(value = "Select * FROM  cmv_supplier order by supplier_id Desc", nativeQuery = true)
	Page<CSupplierViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_supplier where is_delete =0  order by supplier_id Desc", nativeQuery = true)
	Page<CSupplierViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_supplier where is_delete =0 and supplier_id = ?1", nativeQuery = true)
	CSupplierViewModel FnShowParticularRecordForUpdate(int supplier_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_supplier where is_delete =0 and supplier_id = ?1 and company_branch_id = ?2 and supplier_id = ?3")
	CSupplierViewModel FnShowParticularRecord(int company_id, int company_branch_id, int supplier_id);

	@Query(value = "Select * FROM cmv_supplier where is_delete = 0 and company_id=?1 order by supplier_id Desc", nativeQuery = true)
	List<CSupplierViewModel> FnShowProductSrSupplierActiveRecords(int company_id);


	@Query(value = "FROM CSupplierViewModel model where model.is_delete=0 and model.company_id = ?1")
	List<CSupplierViewModel> FnShowAllSupplierRecords(int company_id);

	@Query(value = "Select * FROM cmv_supplier_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
