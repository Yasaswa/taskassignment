package com.erp.PtGoodsReceiptMasterCustomer.Repository;

import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptDetailsCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IPtGoodsReceiptDetailsCustomerRepository extends JpaRepository<CPtGoodsReceiptDetailsCustomerModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details_customer set is_delete = 1, deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP() where customer_goods_receipt_no = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void deleteGoodsReceiptDetailsCustomer(String customer_goods_receipt_no, int company_id, String UserName);

	@Query(value = "SELECT * FROM ptv_goods_receipt_customer_details  WHERE customer_goods_receipt_no = ?1 and customer_goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowGoodReceiptCustomerDetailsRecords(String customer_goods_receipt_no,
	                                                                  int customer_goods_receipt_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details_customer set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_goods_receipt_no = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdateGoodReceiptDetailsCustomerRecord(String customer_goods_receipt_no, int company_id);

	
	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details_customer  set customer_item_status = ?1 WHERE customer_material_id = ?2 AND is_delete = 0 ", nativeQuery = true)
	void updateCustomerMaterialReceiptDetailStatus(String string, String customer_material_id);


	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details_customer set customer_return_quantity = ?1 , customer_return_weight = ?2  WHERE customer_goods_receipt_no = ?3 AND customer_material_id = ?4 AND is_delete = 0", nativeQuery = true)
    void updateCustomerMaterialReceiptDetails(double customer_return_quantity, double customer_return_weight,
			String customer_goods_receipt_no, String customer_material_id);


}
