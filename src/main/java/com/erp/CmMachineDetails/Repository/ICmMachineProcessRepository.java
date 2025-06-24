package com.erp.CmMachineDetails.Repository;

import com.erp.CmMachineDetails.Model.CCmMachineProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ICmMachineProcessRepository extends JpaRepository<CCmMachineProcessModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_machine_process set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where machine_id=?1 and company_id = ?2 and company_branch_id = ?3", nativeQuery = true)
	Object updateMachineProcessActiveStatus(int machine_id, int company_id, int company_branch_id);


	@Query(value = "FROM CCmMachineProcessModel model where model.is_delete=0 and model.machine_id = ?1 and model.company_id = ?2")
	List<CCmMachineProcessModel> FnShowParticularMachineProcessRecord(int machine_id, int company_id);

}
