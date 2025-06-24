package com.erp.CmMachineDetails.Repository;

import com.erp.CmMachineDetails.Model.CCmMachineViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICmMachineViewRepository extends JpaRepository<CCmMachineViewModel, Integer> {


	@Query(value = "FROM CCmMachineViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.machine_id Desc")
	Page<CCmMachineViewModel> FnShowAllActiveMachineRecords(Pageable pageable, int company_id);


	@Query(value = "select * FROM cmv_machine_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllMachineReportRecords(Pageable pageable, int company_id);

}
