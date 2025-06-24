package com.erp.SmProductFgParameters.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductFgParameters.Model.CSmProductFgParametersViewModel;


public interface ISmProductFgParametersViewRepository extends JpaRepository<CSmProductFgParametersViewModel, Integer> {

	
	@Query(value = "FROM CSmProductFgParametersViewModel model where model.is_delete = 0 AND model.product_type_id = ?1 AND model.company_id = ?2")
	CSmProductFgParametersViewModel FnShowParticularRecordForUpdate(int product_type_id, int company_id);
	

}
