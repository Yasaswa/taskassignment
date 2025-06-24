package com.erp.MtSalesOrderMasterWorks.Repository;

import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IMtSalesOrderMasterWorksSummaryRptRepository extends JpaRepository<CMtSalesOrderMasterWorksSummaryRptModel, String> {

	@Query(value = "FROM CMtSalesOrderMasterWorksSummaryRptModel")
	Page<CMtSalesOrderMasterWorksSummaryRptModel> FnShowAllReportRecords(Pageable pageable);


}
