package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ITransporterRptRepository extends JpaRepository<CTransporterRptModel, String> {

	@Query(value = "SELECT * FROM cmv_transporter_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}

