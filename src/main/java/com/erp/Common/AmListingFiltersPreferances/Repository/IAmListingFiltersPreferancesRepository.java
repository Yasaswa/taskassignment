package com.erp.Common.AmListingFiltersPreferances.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesModel;

public interface IAmListingFiltersPreferancesRepository extends JpaRepository<CAmListingFiltersPreferancesModel, Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE CAmListingFiltersPreferancesModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.user_id = ?1 AND model.modules_forms_id = ?2 AND model.report_type = ?3 AND model.company_id = ?4")
    void updateListingFilterPreferenceRecord(Integer user_id, Integer modules_forms_id, String report_type,
			int company_id);


}
