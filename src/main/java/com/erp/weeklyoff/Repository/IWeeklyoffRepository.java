package com.erp.weeklyoff.Repository;

import com.erp.weeklyoff.Model.CWeeklyoffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IWeeklyoffRepository extends JpaRepository<CWeeklyoffModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_weeklyoff set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where weeklyoff_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int weeklyoff_id);

	@Query(nativeQuery = true, value = "SELECT * FROM hm_weeklyoff WHERE weeklyoff_name = ?1")
	CWeeklyoffModel getCheck(String weeklyoff_name);


}
