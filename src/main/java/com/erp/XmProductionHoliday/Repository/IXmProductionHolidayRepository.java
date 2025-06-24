package com.erp.XmProductionHoliday.Repository;

import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXmProductionHolidayRepository extends JpaRepository<CXmProductionHolidayModel, Integer> {

	@Query(value = "FROM CXmProductionHolidayModel model where model.is_delete =0 and model.production_holiday_name = ?1 and model.company_id = ?2")
	CXmProductionHolidayModel checkIfNameExist(String production_holiday_name, int company_id);

	@Query(value = "FROM CXmProductionHolidayModel model where model.is_delete =0 and model.production_holiday_id = ?1 and model.company_id = ?2")
	CXmProductionHolidayModel FnShowParticularRecordForUpdate(int production_holiday_id, int company_id);

	@Query("SELECT COUNT(model) FROM CXmProductionHolidayModel model WHERE model.is_delete = 0 AND model.production_holiday_date = ?1")
	int countHolidayDetails(String attendanceDate);

	@Query(value = "FROM CXmProductionHolidayModel model where model.is_delete =0 and model.production_holiday_date BETWEEN  ?1 AND  ?2")
	List<CXmProductionHolidayModel> FnGetHolidaysBetweenTwoDates(String fromDate, String toDate);

}
