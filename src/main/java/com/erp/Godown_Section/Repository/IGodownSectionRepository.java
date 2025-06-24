package com.erp.Godown_Section.Repository;

import com.erp.Godown_Section.Model.CGodownSectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IGodownSectionRepository extends JpaRepository<CGodownSectionModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_godown_section set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where godown_section_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int godown_section_id, String deleted_by);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown_section WHERE godown_section_name = ?1")
//	CGodownSectionModel getCheck(String godown_section_name);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown_section WHERE (godown_section_name = ?1 OR (?2 IS NOT NULL AND godown_section_short_name = ?2)) and company_id = ?3 order by godown_section_id Desc limit 1")
	CGodownSectionModel getCheck(String godown_section_name, String godown_section_short_name, Integer company_id);

}
