package com.erp.District.Repository;

import com.erp.District.Model.CDistrictModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IDistrictRepository extends JpaRepository<CDistrictModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_district set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where district_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int district_name);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_district WHERE district_name = ?1")
	CDistrictModel getCheck(String district_name);

}
