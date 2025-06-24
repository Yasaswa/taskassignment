package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentSummaryRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStIndentSummaryRptRepository extends JpaRepository<CStIndentSummaryRptModel, String> {

	@Query(value = "select * from stv_indent_summary_rpt", nativeQuery = true)
	Page<CStIndentSummaryRptModel> FnShowAllReportRecords(Pageable pageable);

	@Query(value = "update st_indent_master set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where indent_no=?1", nativeQuery = true)
	Object FnDeleteRCD(String indent_no);

}
