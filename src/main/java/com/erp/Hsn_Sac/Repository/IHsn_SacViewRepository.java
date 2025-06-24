package com.erp.Hsn_Sac.Repository;

import com.erp.Hsn_Sac.Model.CHsn_SacViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IHsn_SacViewRepository extends JpaRepository<CHsn_SacViewModel, Long> {

	@Query(value = "Select * FROM  cmv_hsn_sac order by hsn_sac_id Desc", nativeQuery = true)
	Page<CHsn_SacViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_hsn_sac where is_delete =0  order by hsn_sac_id Desc", nativeQuery = true)
	Page<CHsn_SacViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_hsn_sac where is_delete =0 and hsn_sac_id = ?1", nativeQuery = true)
	CHsn_SacViewModel FnShowParticularRecordForUpdate(int hsn_sac_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_hsn_sac where is_delete =0 and company_id = ?1 and hsn_sac_id = ?2")
	CHsn_SacViewModel FnShowParticularRecord(int company_id, int hsn_sac_id);

	@Query(value = "SELECT * FROM cmv_hsn_sac_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
