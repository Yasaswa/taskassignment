package com.erp.City.Repository;

import com.erp.City.Model.CCityModel;
import com.erp.Contractors.Model.CContractorsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ICityRepository extends JpaRepository<CCityModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_city set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where city_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int city_id, String deleted_by);


//	@Query(value = "FROM CCityModel cm WHERE cm.city_name = ?1 and cm.state_id = ?2 and cm.district_id = ?3 ")
//	CCityModel checkIfCityExist(String city_name, long state_id, long district_id);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_city WHERE (city_name = ?1 or (?2 IS NOT NULL AND city_short_name = ?2)) and state_id = ?3 and district_id = ?4  order by city_id Desc limit 1")
	CCityModel getCheck(String city_name, String city_short_name, long state_id, long district_id);
	


}
