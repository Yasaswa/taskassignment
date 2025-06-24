package com.erp.XmProductionElectricalMeter.Repository;

import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterMachineMappingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IXmProductionElectricalMeterMachineMappingViewRepository extends JpaRepository<CXmProductionElectricalMeterMachineMappingViewModel, Integer> {


	@Query(value = "from CXmProductionElectricalMeterMachineMappingViewModel model where model.is_delete = 0 and model.prod_electrical_meter_id = ?1 and model.company_id = ?2")
	List<CXmProductionElectricalMeterMachineMappingViewModel> FnShowElectricalMeterMappingRecords(
			int prod_electrical_meter_id, int company_id);

//	@Query(value = "FROM CXmProductionElectricalMeterMachineMappingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.prod_electrical_meter_machine_mapping_id Desc")
//	Page<CXmProductionElectricalMeterMachineMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);
//
//	@Query(value = "FROM CXmProductionElectricalMeterMachineMappingViewModel model where model.is_delete =0 and model.prod_electrical_meter_machine_mapping_id = ?1 and model.company_id = ?2 order by model.prod_electrical_meter_machine_mapping_id Desc")
//	Page<CXmProductionElectricalMeterMachineMappingViewModel> FnShowParticularRecord(int prod_electrical_meter_machine_mapping_id, Pageable pageable, int company_id);


}
