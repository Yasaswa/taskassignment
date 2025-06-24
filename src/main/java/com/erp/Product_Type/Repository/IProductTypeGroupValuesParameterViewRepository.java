package com.erp.Product_Type.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.jpa.repository.JpaRepository;


import com.erp.Product_Type.Model.CSmProductTypeGroupValuesParametersViewModel;

public interface IProductTypeGroupValuesParameterViewRepository extends JpaRepository<CSmProductTypeGroupValuesParametersViewModel, Integer>{

	
	
	@Query(value = "FROM CSmProductTypeGroupValuesParametersViewModel model where model.is_delete = 0 and model.product_type_id = ?1 and model.company_id = ?2")
	List<CSmProductTypeGroupValuesParametersViewModel> FnShowProductTypeGroupParametersNameAndValueRecordForUpdate(
			int product_type_id, int company_id);



}
