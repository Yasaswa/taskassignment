package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtGoodsReceiptDetailsViewRepository extends JpaRepository<CPtGoodsReceiptDetailsViewModel, Integer> {

	@Query(value = "FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.goods_receipt_details_transaction_id Desc")
	Page<CPtGoodsReceiptDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete =0 and model.goods_receipt_details_transaction_id = ?1 and model.company_id = ?2 order by model.goods_receipt_details_transaction_id Desc")
	Page<CPtGoodsReceiptDetailsViewModel> FnShowParticularRecord(int goods_receipt_details_transaction_id,
	                                                             Pageable pageable, int company_id);

//	@Query(value = "Select * FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete =0 and model.goods_receipt_master_transaction_id = ?1")

	@Query(value = "Select * FROM ptv_goods_receipt_details where is_delete =0 and goods_receipt_master_transaction_id = ?1", nativeQuery = true)
	List<CPtGoodsReceiptDetailsViewModel> getAllDetails(int goods_receipt_master_transaction_id);

	@Query(value = "Select convsersion_factor FROM smv_product_unit_conversion where is_delete =0 and product_from_unit_id = ?1 and product_to_unit_id = ?2 and company_id = ?3", nativeQuery = true)
	Integer FnGetUnitConversionFactor(Integer product_material_unit_id, Integer product_material_unit_id1, int company_id);

//	@Query(value = "FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete=0 and model.company_id = ?1 and model.expected_branch_id = ?2 and model.supplier_id = ?3 and model.goods_receipt_type_id = ?4 AND model.goods_receipt_no IN ?5")
//	List<CPtGoodsReceiptDetailsViewModel> FnShowGoodReceiptDetails(int company_id, int expected_branch_id,
//			int supplier_id, int goods_receipt_type_id, List<String> goodReceiptNosList);

	@Query(value = "FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete=0 and model.company_id = ?1 and model.expected_branch_id = ?2 and model.supplier_id = ?3 AND model.goods_receipt_no IN ?4")
	List<CPtGoodsReceiptDetailsViewModel> FnShowGoodReceiptDetails(int company_id, int expected_branch_id,
	                                                               int supplier_id, List<String> goodReceiptNosList);

	@Query(value = "FROM CPtGoodsReceiptDetailsViewModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.company_id = ?2")
    List<CPtGoodsReceiptDetailsViewModel> getAllExistingGRNPODetails(String distinctPurchaseOrderNumbers, int companyId);

	@Query(value = "SELECT COUNT(*) FROM pt_goods_receipt_details WHERE is_delete = 0 AND product_material_id = ?1 and company_id = ?2", nativeQuery = true)
	Integer checkGrnExists(String productRmId, Integer companyId);

}
