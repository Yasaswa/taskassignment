package com.erp.Product_Type.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.Product_Type.Model.CSmProductTypeGroupParametersValueViewModel;
public interface ISmProductTypeGroupParametersValueViewRepository extends JpaRepository<CSmProductTypeGroupParametersValueViewModel, Integer>{	

	@Query(value = "FROM CSmProductTypeGroupParametersValueViewModel model where model.is_delete = 0 and model.product_type_id = ?1 and model.company_id = ?2")
	List<CSmProductTypeGroupParametersValueViewModel> FnShowProductTypeGroupParametersValueRecordForUpdate(
			int product_type_id, int company_id);
	

}
