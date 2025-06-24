package com.erp.PtGoodsReceiptMasterCustomer.Repository;

import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptCustomerSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IPtGoodsReceiptMasterCustomerViewRepository extends JpaRepository<CPtGoodsReceiptCustomerSummaryViewModel, Integer> {


	@Query(value = "SELECT * FROM ptv_goods_receipt_customer_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();


}
