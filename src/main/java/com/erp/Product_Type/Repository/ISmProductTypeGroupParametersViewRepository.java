package com.erp.Product_Type.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.Product_Type.Model.CSmProductTypeGroupParametersViewModel;


public interface ISmProductTypeGroupParametersViewRepository extends JpaRepository<CSmProductTypeGroupParametersViewModel, Integer>{

	
	@Query(value = "FROM CSmProductTypeGroupParametersViewModel model where model.is_delete = 0 and model.product_type_id = ?1 and model.company_id = ?2")
	List<CSmProductTypeGroupParametersViewModel> FnShowProductTypeGroupParametersRecordForUpdate(int product_type_id,
			int company_id);

}
