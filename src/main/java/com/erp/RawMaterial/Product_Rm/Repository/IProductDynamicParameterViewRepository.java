package com.erp.RawMaterial.Product_Rm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersViewModel;

public interface IProductDynamicParameterViewRepository extends JpaRepository<CSmProductDynamicParametersViewModel, Integer>{



	@Query(value = "FROM CSmProductDynamicParametersViewModel cpdp where cpdp.is_delete = 0 and cpdp.product_type_id = ?1 and product_id = ?2")
	List<CSmProductDynamicParametersViewModel> FnShowParticularProductDynamicParametersRecords(int product_type_id,String product_id);



}
