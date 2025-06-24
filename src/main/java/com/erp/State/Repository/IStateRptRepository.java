package com.erp.State.Repository;

import com.erp.State.Model.CStateRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStateRptRepository extends JpaRepository<CStateRptModel, String> {

	@Query(value = "SELECT * FROM cmv_state_rpt", nativeQuery = true)
	Page<CStateRptModel> FnShowAllReportRecords(Pageable pageable);

}
