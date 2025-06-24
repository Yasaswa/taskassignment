package com.erp.Taxtype.Repository;

import com.erp.Taxtype.Model.CTaxtypeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ITaxtypeViewRepository extends JpaRepository<CTaxtypeViewModel, Long> {

	@Query(value = "Select * FROM  cmv_taxtype order by taxtype_id Desc", nativeQuery = true)
	Page<CTaxtypeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_taxtype where is_delete =0  order by taxtype_id Desc", nativeQuery = true)
	Page<CTaxtypeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_taxtype where is_delete =0 and taxtype_id = ?1", nativeQuery = true)
	CTaxtypeViewModel FnShowParticularRecordForUpdate(int taxtype_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_taxtype where is_delete =0 and company_id = ?1 and taxtype_id = ?2")
	CTaxtypeViewModel FnShowParticularRecord(int company_id, int taxtype_id);

	@Query(nativeQuery = true, value = "Select * FROM cmv_taxtype_rpt")
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
