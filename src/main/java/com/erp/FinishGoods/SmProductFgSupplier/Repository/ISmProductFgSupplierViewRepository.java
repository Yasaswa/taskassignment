package com.erp.FinishGoods.SmProductFgSupplier.Repository;

import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmProductFgSupplierViewRepository extends JpaRepository<CSmProductFgSupplierViewModel, Integer> {

	@Query(value = "FROM CSmProductFgSupplierViewModel")
	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM smv_product_fg_supplier where company_id = ?1 and supplier_id = ?2", nativeQuery = true)
	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecordsSupplierFG(Pageable pageable, int company_id,
	                                                                     int supplier_id);
}
