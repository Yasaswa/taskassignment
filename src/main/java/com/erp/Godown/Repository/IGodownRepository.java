package com.erp.Godown.Repository;

import com.erp.Godown.Model.CGodownModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IGodownRepository extends JpaRepository<CGodownModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_godown set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where godown_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int godown_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown WHERE (godown_name = ?1 OR (?2 IS NOT NULL AND godown_short_name = ?2)) and company_id = ?3 order by godown_id Desc limit 1")
	CGodownModel getCheck(String godown_name, String godown_short_name, Integer company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown WHERE godown_name = ?1")
//	CGodownModel getCheck(String godown_name);

}
