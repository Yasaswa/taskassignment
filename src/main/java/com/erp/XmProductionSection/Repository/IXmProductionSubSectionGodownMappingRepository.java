package com.erp.XmProductionSection.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmProductionSection.Model.CXmProductionSubSectionGodownMappingModel;

public interface IXmProductionSubSectionGodownMappingRepository extends JpaRepository<CXmProductionSubSectionGodownMappingModel, Integer> {

	@Query(value = "FROM CXmProductionSubSectionGodownMappingModel model where model.is_delete =0 and model.production_sub_section_godown_id = ?1 and model.company_id = ?2")
	CXmProductionSubSectionGodownMappingModel FnShowParticularRecordForUpdate(int production_sub_section_godown_id,
			int company_id);

	@Modifying
	@Transactional
	@Query(value = "update xm_production_sub_section_godown_mapping set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where production_sub_section_godown_id = ?1", nativeQuery = true)
	void FnDeleteRecord(int production_sub_section_godown_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update xm_production_sub_section_godown_mapping set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where production_sub_section_id = ?1", nativeQuery = true)
	void FnDeleteProductionSubSectionGodownMappingRecord(int production_sub_section_id, String deleted_by);

//	@Query(value = "FROM CXmProductionSubSectionGodownMappingModel model where model.is_delete = 0 and model.production_sub_section_id = ?1 and model.company_id = ?2")
//	List<CXmProductionSubSectionGodownMappingModel> FnShowProdGodownMappingRecordForUpdate(
//			int production_sub_section_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update xm_production_sub_section_godown_mapping set is_delete = 1 ,deleted_on = CURRENT_TIMESTAMP() where production_sub_section_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateProductionSubSectionGodownMapping(int production_sub_section_id, int company_id);

}
