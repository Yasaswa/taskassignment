package com.erp.XmProductionHoliday.Repository;

import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IXmProductionHolidayViewRepository extends JpaRepository<CXmProductionHolidayViewModel, Integer> {

	@Query(value = "FROM CXmProductionHolidayModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_holiday_id Desc")
	Page<CXmProductionHolidayModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CXmProductionHolidayViewModel model where model.is_delete =0 and model.production_holiday_id = ?1 and model.company_id = ?2 order by model.production_holiday_id Desc")
	Page<CXmProductionHolidayViewModel> FnShowParticularRecord(int production_holiday_id, Pageable pageable, int company_id);

	@Query(value = "Select * From xmv_production_holiday_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
