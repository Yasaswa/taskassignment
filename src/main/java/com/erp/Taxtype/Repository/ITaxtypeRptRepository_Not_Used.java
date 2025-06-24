package com.erp.Taxtype.Repository;

import com.erp.Taxtype.Model.CTaxtypeRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxtypeRptRepository_Not_Used extends JpaRepository<CTaxtypeRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM cmv_taxtype_rpt", nativeQuery = true)
//	Page<CTaxtypeRptModel> FnShowAllReportRecords(Pageable pageable);

}
