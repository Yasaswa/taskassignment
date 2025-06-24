package com.erp.SmProductTypeAccordianAccess.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductTypeAccordianAccess.Model.CSmProductTypeAccordianAccessModel;


public interface ISmProductTypeAccordianAccessRepository extends JpaRepository<CSmProductTypeAccordianAccessModel, Integer> {

	
//	@Query(value = "FROM CSmProductTypeAccordianAccessModel model WHERE model.is_delete = 0 and model.product_type_short_name = ?1 AND model.modules_forms_id = ?2 AND model.company_id = ?3")

	@Query(value = "SELECT * FROM sm_product_type_accordian_access ptac WHERE ptac.is_delete = 0 and ptac.product_type_short_name = ?1 AND ptac.modules_forms_id = ?2",nativeQuery = true)
	List<Map<String, Object>> FnShowProductTypeAccordianAccessDetails(String product_type_short_name,
			int modules_forms_id);

	


}
