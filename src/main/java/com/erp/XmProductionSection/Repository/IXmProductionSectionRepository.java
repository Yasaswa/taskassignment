package com.erp.XmProductionSection.Repository;

import com.erp.XmProductionSection.Model.CXmProductionSectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IXmProductionSectionRepository extends JpaRepository<CXmProductionSectionModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM xm_production_section  WHERE (production_section_name = ?1 or (?2 IS NOT NULL AND production_section_short_name = ?2)) and company_id = ?3 order by production_section_id Desc limit 1")
	CXmProductionSectionModel getCheck(String production_section_name, String getproduction_section_short_name,
	                                   int company_id);

	@Query(value = "FROM CXmProductionSectionModel model where model.is_delete =0 and model.company_id = ?1 and model.production_section_id = ?2")
	CXmProductionSectionModel FnShowProductionSectionRecordForUpdate(int company_id, int production_section_id);


	@Modifying
	@Transactional
	@Query(value = "update xm_production_section set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where production_section_id = ?1", nativeQuery = true)
	void FnDeleteProductionSectionRecord(int production_section_id, String deleted_by);


}
