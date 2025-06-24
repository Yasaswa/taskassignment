package com.erp.XmProductionElectricalMeter.Repository;

import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionElectricalMeterViewRepository extends JpaRepository<CXmProductionElectricalMeterViewModel, Integer> {


	@Query(value = "from CXmProductionElectricalMeterViewModel model where model.is_delete = 0 and model.prod_electrical_meter_id = ?1 and model.company_id = ?2")
	CXmProductionElectricalMeterViewModel FnShowElectricalMeterRecords(int prod_electrical_meter_id,
	                                                                   int company_id);


}
