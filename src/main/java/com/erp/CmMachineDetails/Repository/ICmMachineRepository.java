package com.erp.CmMachineDetails.Repository;

import com.erp.CmMachineDetails.Model.CCmMachineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICmMachineRepository extends JpaRepository<CCmMachineModel, Integer> {


	@Query(value = "FROM CCmMachineModel model where model.is_delete =0 and model.machine_name = ?1")
	CCmMachineModel checkIfNameExist(String machine_name);


	@Query(value = "FROM CCmMachineModel model where model.is_delete =0 and model.machine_id = ?1 and model.company_id = ?2")
	CCmMachineModel FnShowParticularMachineRecordForUpdate(int machine_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_machine WHERE ( machine_name = ?1 or machine_short_name = ?2 ) and company_id = ?3 and is_delete = 0 ")
	CCmMachineModel checkIfNameExist(String machine_name, String machine_short_name, Integer company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_machine WHERE (machine_name = ?1 or (?2 IS NOT NULL AND machine_short_name = ?2)) and company_id = ?3 order by machine_id Desc limit 1")
	CCmMachineModel getCheck(String machine_name, String machine_short_name, Integer company_id);

}
