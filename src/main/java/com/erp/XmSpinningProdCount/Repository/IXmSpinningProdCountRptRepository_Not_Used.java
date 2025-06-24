package com.erp.XmSpinningProdCount.Repository;

import com.erp.XmSpinningProdCount.Model.CxmSpinningProdCountRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IXmSpinningProdCountRptRepository_Not_Used extends JpaRepository<CxmSpinningProdCountRptModel_Not_Used, Integer> {

//    @Query(value ="select * FROM xmv_spinning_prod_count_rpt", nativeQuery = true)
//	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
