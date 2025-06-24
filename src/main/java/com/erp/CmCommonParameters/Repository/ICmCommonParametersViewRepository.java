package com.erp.CmCommonParameters.Repository;

import com.erp.CmCommonParameters.Model.CCmCommonParametersViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface ICmCommonParametersViewRepository extends JpaRepository<CCmCommonParametersViewModel, Integer> {


	@Query(value = "FROM CCmCommonParametersViewModel model where model.is_delete =0 and model.common_parameters_id = ?1 and model.company_id = ?2 order by model.common_parameters_id Desc")
	Page<CCmCommonParametersViewModel> FnShowParticularRecord(int common_parameters_id, Pageable pageable, int company_id);


	@Query(value = "FROM CCmCommonParametersViewModel model where model.is_delete = 0 and model.common_parameters_master_name = ?1  order by model.common_parameters_id Desc")
	Page<CCmCommonParametersViewModel> FnShowAllActiveRecords(String common_parameters_master_name, Pageable pageable);


	@Query(value = "Select * FROM cmv_common_parameters_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
