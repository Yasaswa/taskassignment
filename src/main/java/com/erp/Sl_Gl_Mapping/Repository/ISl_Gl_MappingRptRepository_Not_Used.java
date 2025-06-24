package com.erp.Sl_Gl_Mapping.Repository;

import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISl_Gl_MappingRptRepository_Not_Used extends JpaRepository<CSl_Gl_MappingRptModel_Not_Used, String> {

//	 @Query(value ="SELECT * FROM hmv_sl_gl_mapping_rpt", nativeQuery = true)
//	 Page<CSl_Gl_MappingRptModel> FnShowAllReportRecords(Pageable pageable);
}
