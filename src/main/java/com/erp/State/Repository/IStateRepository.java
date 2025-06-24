package com.erp.State.Repository;

import com.erp.State.Model.CStateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IStateRepository extends JpaRepository<CStateModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_state set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where state_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int state_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_state WHERE (state_name = ?1 OR (?2 IS NOT NULL AND state_short_name = ?2)) and company_id = ?3 order by state_id Desc limit 1")
	CStateModel getCheck(String state_name, String state_short_name, int company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_state WHERE state_name = ?1")
//	CStateModel getCheck(String state_name);

}
