package com.erp.CmPlant.Repository;

import com.erp.CmPlant.Model.CCmPlantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ICmPlantRepository extends JpaRepository<CCmPlantModel, Integer> {

	@Query(value = "FROM CCmPlantModel model where model.is_delete =0 and model.plant_id = ?1 and model.company_id = ?2")
	CCmPlantModel FnShowParticularRecordForUpdate(int plant_id, int company_id);

	@Query(value = "FROM CCmPlantModel model where model.is_delete =0 and model.plant_name = ?1 and model.company_id = ?2")
	CCmPlantModel checkIfNameExist(String plant_name, int company_id);


}
