package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ITransporterViewRepository extends JpaRepository<CTransporterViewModel, Long> {

	@Query(value = "Select * FROM  cmv_transporter order by transporter_id Desc", nativeQuery = true)
	Page<CTransporterViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_transporter where is_delete =0  order by transporter_id Desc", nativeQuery = true)
	Page<CTransporterViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_transporter where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and transporter_id = ?3")
	CTransporterViewModel FnShowParticularRecord(int company_id, int company_branch_id, int transporter_id);

	@Query(value = "SELECT * FROM cmv_transporter_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
