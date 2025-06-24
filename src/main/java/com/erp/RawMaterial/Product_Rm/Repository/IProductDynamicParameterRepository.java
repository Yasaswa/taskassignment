package com.erp.RawMaterial.Product_Rm.Repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IProductDynamicParameterRepository extends JpaRepository<CSmProductDynamicParametersModel, Integer>{


    @Modifying
    @Transactional
    @Query(value = "update sm_product_dynamic_parameters  set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteProductDynamicParameters(String product_id, String deletedBy);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_dynamic_parameters  set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_id = ?1 and is_delete = 0", nativeQuery = true)
    void fnDeleteProductDynamicParameters(String product_id, String modifiedBy);


    @Query(value = "FROM CSmProductDynamicParametersModel model WHERE model.product_parameter_value IN :sortNos AND model.is_delete = false")
    List<CSmProductDynamicParametersModel> findProductIdsBySortNos(@Param("sortNos") List<String> sortNos);


}
