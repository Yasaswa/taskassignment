package com.erp.Sl_Gl_Mapping.Repository;

import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISl_Gl_MappingRepository extends JpaRepository<CSl_Gl_MappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update fm_sl_gl_mapping set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where sl_gl_mapping_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int sl_gl_mapping_id);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_sl_gl_mapping WHERE general_ledger_id = ?1")
	CSl_Gl_MappingModel getCheck(Integer general_ledger_id);

	@Query(value = "FROM CSl_Gl_MappingModel model where model.is_delete = 0 and model.general_ledger_id = ?1 and model.company_id = ?2")
	Page<CSl_Gl_MappingModel> FnShowParticularRecord(int general_ledger_id, Pageable pageable, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update fm_sl_gl_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE general_ledger_id = ?1 and company_id =?2 and is_delete = 0", nativeQuery = true)
	int updateStatus(int general_ledger_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update fm_sl_gl_mapping set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() WHERE general_ledger_id = ?1 and company_id =?3", nativeQuery = true)
	void deleteMapping(int general_ledger_id, String deleted_by, int company_id);
}
