package com.erp.CmMachineDetails.Repository;

import com.erp.CmMachineDetails.Model.CCmMachineProcessViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICmMachineProcessViewRepository extends JpaRepository<CCmMachineProcessViewModel, Integer> {

	@Query(value = "Select * FROM cmv_machine_process where company_id = ?1", nativeQuery = true)
	List<CCmMachineProcessViewModel> FnShowAllActiveMachineProcessRecords(int company_id);

}
