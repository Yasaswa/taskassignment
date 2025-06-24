package com.erp.XmProductionElectricalMeter.Repository;

import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterMachineMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IXmProductionElectricalMeterMachineMappingRepository extends JpaRepository<CXmProductionElectricalMeterMachineMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update xm_production_electrical_meter_machine_mapping set is_delete = 1,  deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where prod_electrical_meter_id = ?1 and company_id = ?3", nativeQuery = true)
	void deleteElectricalMeterMapping(int prod_electrical_meter_id, String deleted_by, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update xm_production_electrical_meter_machine_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where prod_electrical_meter_id = ?1 and company_id = ?2", nativeQuery = true)
	void updateStatus(int prod_electrical_meter_id, int company_id);


}
