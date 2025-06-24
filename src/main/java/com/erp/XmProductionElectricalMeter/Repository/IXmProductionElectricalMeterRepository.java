package com.erp.XmProductionElectricalMeter.Repository;

import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IXmProductionElectricalMeterRepository extends JpaRepository<CXmProductionElectricalMeterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update xm_production_electrical_meter set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where prod_electrical_meter_id = ?1 and company_id = ?3", nativeQuery = true)
	void deleteElectricalMeter(int prod_electrical_meter_id, String deleted_by, int company_id);

	
//	@Query(value = "FROM CXmProductionElectricalMeterModel model WHERE (model.prod_electrical_meter_name = ?1 OR (?2 IS NOT NULL AND model.prod_electrical_meter_short_name = ?2)) AND model.company_id = ?3 ORDER BY model.prod_electrical_meter_id DESC")
	@Query(nativeQuery = true, value = "SELECT * FROM xm_production_electrical_meter WHERE (prod_electrical_meter_name = ?1 OR (?2 IS NOT NULL AND prod_electrical_meter_short_name = ?2)) and company_id = ?3 order by prod_electrical_meter_id Desc limit 1")
	CXmProductionElectricalMeterModel getCheck(String prod_electrical_meter_name,
			String prod_electrical_meter_short_name, Integer company_id);


}
