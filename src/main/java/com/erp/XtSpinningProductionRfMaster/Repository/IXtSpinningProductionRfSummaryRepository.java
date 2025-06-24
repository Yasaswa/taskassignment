package com.erp.XtSpinningProductionRfMaster.Repository;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfSummaryModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtSpinningProductionRfSummaryRepository extends JpaRepository<CXtSpinningProductionRfSummaryModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_production_rf_summary set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinning_production_rf_master_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProductionRfSummaryRecord(int spinning_production_rf_master_id, String deleted_by);

	//	@Query(value = "FROM CXtSpinningProductionRfSummaryModel model where model.spinning_production_date <= ?1  and model.company_id = ?2 ORDER BY model.spinning_production_date DESC")
	@Query(value = "SELECT * FROM xt_spinning_production_rf_summary WHERE spinning_production_date = ?1 " +
			"UNION " +
			"SELECT * FROM xt_spinning_production_rf_summary WHERE spinning_production_date = ( " +
			"   SELECT MAX(spinning_production_date) FROM xt_spinning_production_rf_summary " +
			"   WHERE spinning_production_date < ?1 and is_delete = 0)",
			nativeQuery = true)
	List<CXtSpinningProductionRfSummaryModel> FnShowParticularShiftSummary(String spinning_production_date, int company_id);


	@Query(value = "FROM CXtSpinningProductionRfSummaryModel model where model.is_delete = false and model.spinning_production_rf_master_id = ?1  and model.company_id = ?2")
	CXtSpinningProductionRfSummaryModel FnShowspinningProductionRfSummaryRecord(int spinning_production_rf_master_id,
	                                                                            int company_id);


	@Query(value = "FROM CXtSpinningProductionRfSummaryModel model where model.is_delete = false and model.spinning_production_rf_master_id = ?1 and model.company_id = ?2")
	List<CXtSpinningProductionRfSummaryModel> FnShowSpinningProductionRfSummaryRecords(
			int spinning_production_rf_master_id, int company_id);
}
