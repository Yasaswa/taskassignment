package com.erp.XmProductionSection.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmProductionSection.Model.CXmProductionSubSectionGodownMappingViewModel;

public interface IXmProductionSubSectionGodownMappingViewRepository	extends JpaRepository<CXmProductionSubSectionGodownMappingViewModel, Integer> {

	@Query(value = "FROM CXmProductionSubSectionGodownMappingViewModel model where model.is_delete = 0 and model.production_sub_section_id = ?1 and model.company_id = ?2")
	List<CXmProductionSubSectionGodownMappingViewModel> FnShowProdGodownMappingRecordForUpdate(
			int production_sub_section_id, int company_id);

}
