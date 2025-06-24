package com.erp.Common.AmListingFiltersPreferances.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesViewModel;

public interface IAmListingFiltersPreferancesViewRepository extends JpaRepository<CAmListingFiltersPreferancesViewModel, Integer>{
	
	@Query(value = "FROM CAmListingFiltersPreferancesViewModel model where model.is_delete = false and model.user_id = ?1 and model.company_id = ?2")
	List<CAmListingFiltersPreferancesViewModel> getAllListingFilterPreferences(Integer user_id, Integer company_id);
}
