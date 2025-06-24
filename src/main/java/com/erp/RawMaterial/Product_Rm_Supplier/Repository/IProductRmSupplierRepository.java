package com.erp.RawMaterial.Product_Rm_Supplier.Repository;

import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IProductRmSupplierRepository extends JpaRepository<CProductRmSupplierModel, Integer> {

    @Query(value = "FROM CProductRmSupplierModel cpsm where cpsm.is_delete=0 and cpsm.product_rm_id =?1 and cpsm.company_id = ?2")
    List<CProductRmSupplierModel> FnShowParticularRecords(int product_rm_id, int company_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_supplier set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
    Object updateProductRMSupplierActiveStatus(String product_rm_id, int company_id);

    @Query(value = "Select * FROM sm_product_rm_supplier where is_delete = 0 and product_rm_id = ?1", nativeQuery = true)
    List<CProductRmSupplierModel> checkRecordIfExist(int product_rm_id);

    //	@Query(value = "FROM CProductRmSupplierModel cpsm where cpsm.is_delete=0 and cpsm.product_rm_id =?1 and cpsm.company_id = ?2")
//	List<CProductRmSupplierModel> FnShowParticularRmmaterialSupplierRecords(String product_rm_id, int company_id);
    @Query(value = "FROM CProductRmSupplierModel cpsm where cpsm.is_delete=0 and cpsm.product_rm_id =?1 ")
    List<CProductRmSupplierModel> FnShowParticularRmmaterialSupplierRecords(String product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_supplier set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteProductRmSupplierRecords(String product_rm_id, String deleted_by);

}
