package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationDeductionMappingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDesignationDeductionMappingViewModelRepository extends JpaRepository<CDesignationDeductionMappingViewModel, Integer> {

	@Query(value = "FROM CDesignationDeductionMappingViewModel model where model.designation_id = ?1")
	List<CDesignationDeductionMappingViewModel> FnShowDeductionMapingRecords(int designation_id);

}
