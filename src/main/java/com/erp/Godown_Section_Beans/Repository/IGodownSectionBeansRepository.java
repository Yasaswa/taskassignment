package com.erp.Godown_Section_Beans.Repository;

import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IGodownSectionBeansRepository extends JpaRepository<CGodownSectionBeansModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_godown_section_beans set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where godown_section_beans_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int godown_section_beans_id, String deleted_by);

	
//	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown_section_beans WHERE godown_section_beans_name = ?1")
//	CGodownSectionBeansModel getCheck(String godown_section_beans_name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cm_godown_section_beans WHERE (godown_section_beans_name = ?1 OR (?2 IS NOT NULL AND godown_section_beans_short_name = ?2)) and company_id = ?3 order by godown_section_beans_id Desc limit 1")
	CGodownSectionBeansModel getCheck(String godown_section_beans_name, String godown_section_beans_short_name,
			Integer company_id);

}
