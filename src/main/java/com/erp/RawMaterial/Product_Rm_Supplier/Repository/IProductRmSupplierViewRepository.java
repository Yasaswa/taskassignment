package com.erp.RawMaterial.Product_Rm_Supplier.Repository;

import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRmSupplierViewRepository extends JpaRepository<CProductRmSupplierViewModel, Integer> {

	@Query(value = "Select * FROM smv_product_rm_supplier where company_id = ?1", nativeQuery = true)
	List<CProductRmSupplierViewModel> FnShowAllActiveRecords(int company_id);

	@Query(value = "Select * FROM smv_product_rm_supplier where company_id = ?1 and supplier_id = ?2", nativeQuery = true)
	List<CProductRmSupplierViewModel> FnShowAllActiveRecordsSuppliersRM(int company_id, int supplier_id);

	@Query(value = "FROM CProductRmSupplierViewModel cpsm where cpsm.product_rm_id =?1")
	List<CProductRmSupplierViewModel> FnShowParticularRmmaterialSupplierRecords(String product_rm_id);


}
