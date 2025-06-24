package com.erp.RawMaterial.Product_Rm_Process.Repository;

import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IProductRmProcessRepository extends JpaRepository<CProductRmProcessModel, Integer> {

    @Query(value = "Select * FROM sm_product_rm_process where  product_rm_id = ?1", nativeQuery = true)
    List<CProductRmProcessModel> checkRecordIfExist(int product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_process set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
    Object updateProductRMProcessActiveStatus(String product_rm_id, int company_id);

    @Query(value = "FROM CProductRmProcessModel cppm where cppm.is_delete=0 and cppm.product_rm_id =?1 and cppm.company_id = ?2")
    List<CProductRmProcessModel> FnShowParticularRecords(int product_rm_id, int company_id);

    //	@Query(value = "FROM CProductRmProcessModel cppm where cppm.is_delete=0 and cppm.product_rm_id =?1 and cppm.company_id = ?2")
//	List<CProductRmProcessModel> FnShowParticularRmmaterialProcessRecords(String product_rm_id, int company_id);
    @Query(value = "FROM CProductRmProcessModel cppm where cppm.is_delete=0 and cppm.product_rm_id =?1")
    List<CProductRmProcessModel> FnShowParticularRmmaterialProcessRecords(String product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_process set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteProductRmProcessRecords(String product_rm_id, String deleted_by);

}
