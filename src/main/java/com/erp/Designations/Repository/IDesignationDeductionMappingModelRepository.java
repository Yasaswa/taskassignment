package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationDeductionMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IDesignationDeductionMappingModelRepository extends JpaRepository<CDesignationDeductionMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_designation_deduction_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and designation_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateDeductionRecord(int company_id, int designation_id);

	@Query(value = "FROM CDesignationDeductionMappingModel model where model.is_delete = 0 and model.designation_id = ?1")
	List<CDesignationDeductionMappingModel> FnShowDeductionMapingRecords(int designation_id);

	@Query(value = "FROM CDesignationDeductionMappingModel model where model.is_delete=0 and model.company_id = ?1")
	List<CDesignationDeductionMappingModel> FnShowAllDesignationDeductionRecords(int company_id);

	@Query(value = "FROM CDesignationDeductionMappingModel model where model.is_delete=0 and model.company_id = ?1 and model.designation_id = ?2")
	List<CDesignationDeductionMappingModel> FnShowAllDesignationDeductionRecords(int company_id, int common_id);


	@Modifying
	@Transactional
	@Query(value = "update hm_designation_deduction_mapping set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where designation_id = ?1", nativeQuery = true)
	void FnDeleteDesignationDeductionMappingRecord(int designation_id, String deleted_by);


}
