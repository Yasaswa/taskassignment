package com.erp.SmProductSrSupplier.Repository;

import com.erp.SmProductSrSupplier.Model.CSmProductSrSupplierViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductSrSupplierViewRepository extends JpaRepository<CSmProductSrSupplierViewModel, Integer> {

	@Query(value = "FROM CSmProductSrSupplierViewModel model where  model.company_id = ?1 order by model.product_sr_id Desc")
	Page<CSmProductSrSupplierViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrSupplierViewModel model where model.product_sr_id = ?1 and model.company_id = ?2 order by model.product_sr_id Desc")
	Page<CSmProductSrSupplierViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrSupplierViewModel model where model.product_sr_id = ?1 and model.company_id = ?2 order by model.product_sr_id Desc")
	List<CSmProductSrSupplierViewModel> FnShowProductServiceSupplierRecords(int product_sr_id, int company_id);

	@Query(value = "Select * FROM cmv_supplier_summary where is_delete = 0 and company_id=?1 order by supplier_id Desc", nativeQuery = true)
	List<CSmProductSrSupplierViewModel> FnShowProductSrSupplierActiveRecords(int company_id);


}
