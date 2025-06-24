package com.erp.PtGoodsReceiptMasterCustomer.Repository;

import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptDetailsCustomerViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPtGoodsReceiptDetailsCustomerViewRepository extends JpaRepository<CPtGoodsReceiptDetailsCustomerViewModel, Integer> {

	@Query(value = "SELECT * FROM ptv_goods_receipt_customer_details_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

	@Query(value = "Select * FROM ptv_goods_receipt_customer_details where is_delete =0 and customer_goods_receipt_master_transaction_id = ?1", nativeQuery = true)
	List<CPtGoodsReceiptDetailsCustomerViewModel> getAllDetails(int customer_goods_receipt_master_transaction_id);

	@Query(value = "Select convsersion_factor FROM smv_product_unit_conversion where is_delete =0 and product_from_unit_id = ?1 and product_to_unit_id = ?2 and company_id = ?3", nativeQuery = true)
	Integer FnGetUnitConversionFactor(Integer customer_material_unit_id, Integer product_material_unit_id, int company_id);

	@Query(value = "Select * FROM ptv_goods_receipt_customer_details where is_delete =0 and customer_goods_receipt_master_transaction_id IN ?1", nativeQuery = true)
	List<CPtGoodsReceiptDetailsCustomerViewModel> getAllDetails(List<Integer> responceCustomerGoodsReceiptMasterId);


}
