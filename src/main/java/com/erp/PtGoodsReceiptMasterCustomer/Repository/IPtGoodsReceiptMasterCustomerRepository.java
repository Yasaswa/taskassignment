package com.erp.PtGoodsReceiptMasterCustomer.Repository;

import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptMasterCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;


public interface IPtGoodsReceiptMasterCustomerRepository extends JpaRepository<CPtGoodsReceiptMasterCustomerModel, Integer> {


	@Query(value = "SELECT * FROM pt_goods_receipt_master_customer WHERE customer_goods_receipt_no = ?1 and customer_goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	CPtGoodsReceiptMasterCustomerModel getExistingRecord(String customer_goods_receipt_no,
	                                                     Integer customer_goods_receipt_version, String financial_year, int company_id);

	@Query(value = "SELECT * FROM ptv_goods_receipt_customer_summary  WHERE customer_goods_receipt_no = ?1 and customer_goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowEnquiryMasterRecord(String customer_goods_receipt_no, int customer_goods_receipt_version,
	                                              String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_master_customer set is_delete = 1, deleted_by = ?3 ,deleted_on = CURRENT_TIMESTAMP() where customer_goods_receipt_no = ?1  and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void deleteGoodsReceiptMasterCustomer(String customer_goods_receipt_no, int company_id, String UserName);

	@Query(value = "SELECT * FROM ptv_goods_receipt_customer_summary  WHERE customer_goods_receipt_no = ?1 and customer_goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowGoodReceiptCustomerMasterRecords(String customer_goods_receipt_no,
	                                                           int customer_goods_receipt_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_master_customer set customer_goods_receipt_status = ?1 WHERE customer_goods_receipt_no = ?2 AND is_delete = 0 ", nativeQuery = true)
	void updateCustomerMaterialReceiptMasterStatus(String status, String customerGoodReceiptNumber);

}
