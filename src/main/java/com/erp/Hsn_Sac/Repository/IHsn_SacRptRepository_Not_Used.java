package com.erp.Hsn_Sac.Repository;

import com.erp.Hsn_Sac.Model.CHsn_SacRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHsn_SacRptRepository_Not_Used extends JpaRepository<CHsn_SacRptModel_Not_Used, String> {

//	@Query(value = "SELECT * FROM cmv_hsn_sac_rpt", nativeQuery = true)
//	Page<CHsn_SacRptModel> FnShowAllReportRecords(Pageable pageable);
//	

}
