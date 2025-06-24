package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationEarningMappingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDesignationEarningMappingViewModelRepository extends JpaRepository<CDesignationEarningMappingViewModel, Integer> {

	@Query(value = "FROM CDesignationEarningMappingViewModel model where model.is_delete = 0 and model.designation_id = ?1")
	List<CDesignationEarningMappingViewModel> FnShowErningMapingRecords(int designation_id);

}
