package com.erp.PtCottonBalesGRN.Repository;


import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesDetailsViewModel;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsModel;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesDetailsModel;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface IPtGrnCottonBalesDetailsRepository extends JpaRepository<CPtGrnCottonBalesDetailsModel, Integer> {

	@Query(value = "FROM CPtGrnCottonBalesDetailsModel model where model.is_delete =0 and model.grn_cotton_bales_details_transaction_id = ?1 and model.company_id = ?2")
	CPtGrnCottonBalesDetailsModel FnShowParticularRecordForUpdate(int grn_cotton_bales_details_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_grn_cotton_bales_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set grn_item_status = ?1, pree_closed_quantity = ?2 ,pree_closed_weight = ?3, pending_quantity = 0, pending_weight = 0, modified_on = CURRENT_TIMESTAMP() where purchase_order_details_transaction_id = ?4 and is_delete = 0", nativeQuery = true)
	void FnUpdatePoMaterialStatusAndQty(String z, double preeClosedGrnQuantity, double preeClosedGrnWeight, int purchaseOrderDetailsTransactionId);



	@Modifying
	@Transactional
	@Query(value = "UPDATE pt_purchase_order_master SET grn_status = :status, modified_on = CURRENT_TIMESTAMP() WHERE purchase_order_master_transaction_id = :poMasterId AND is_delete = 0", nativeQuery = true)
	void FnUpdatePoMasterStatus(@Param("poMasterId") Integer poMasterId, @Param("status") String status);


	@Modifying
	@Transactional
	@Query(value = "UPDATE pt_purchase_order_details " +
			"SET grn_item_status = CASE " +
			"WHEN purchase_order_details_transaction_id IN (:poDetailsId) THEN 'C' " +
			"WHEN purchase_order_details_transaction_id NOT IN (:poDetailsId) THEN 'I' " +
			"END, " +
			"modified_on = CURRENT_TIMESTAMP() " +
			"WHERE company_id = :companyId AND is_delete = 0",
			nativeQuery = true)
	void FnUpdatePoMaterialStatus(@Param("poDetailsId") List<Integer> poDetailsId,
								  @Param("companyId") Integer companyId);


	@Query("FROM CPtGrnCottonBalesDetailsViewModel WHERE goods_receipt_no = :goodsReceiptNo AND goods_receipt_version = :goodsReceiptVersion AND financial_year = :financialYear AND company_id = :companyId")
	List<CPtGrnCottonBalesDetailsViewModel> FnShowGRNCottonBalesDetailsRecords(
			@Param("goodsReceiptNo") String goodsReceiptNo,
			@Param("goodsReceiptVersion") int goodsReceiptVersion,
			@Param("financialYear") String financialYear,
			@Param("companyId") int companyId
	);


	@Query(value = "SELECT batch_no  FROM pt_grn_cotton_bales_details where is_delete = 0 ORDER BY grn_cotton_bales_details_transaction_id DESC LIMIT 1", nativeQuery = true)
	String FnGetLastLotNo(int companyId);


	@Query("SELECT model FROM CPtGrnCottonBalesDetailsModel model WHERE model.purchase_order_no = ?1 AND model.company_id = ?2 AND model.is_delete = 0")
	ArrayList<CPtGrnCottonBalesDetailsModel> fnGetGRNModelsAgainstPONo(String purchase_order_no, Integer company_id);


	//Update GRN Status after Cotton Bales Sales approve
	@Modifying
	@Transactional
	@Query("UPDATE CPtGrnCottonBalesDetailsModel m SET m.grn_item_status = 'RE' WHERE m.goods_receipt_no = :grnNo AND m.company_id = :companyId AND m.is_delete = 0")
	void FnUpdateGRNStatusForDetails(@Param("grnNo") String goods_receipt_no, @Param("companyId") Integer company_id);


//	@Query(value = "SELECT * FROM pt_grn_cotton_bales_details WHERE goods_receipt_no = ?1 AND is_delete = 0", nativeQuery = true)
//	List<CPtGrnCottonBalesDetailsModel> FnShowPerticularGRNCottonBalesDetailsRecords(String goods_receipt_no);


	@Query(value = "SELECT product_material_id, batch_no, product_material_grn_accepted_quantity, product_material_grn_accepted_weight FROM pt_grn_cotton_bales_details WHERE goods_receipt_no = :grn", nativeQuery = true)
	List<Object[]> FnShowPerticularGRNCottonBalesDetailsRecords(@Param("grn") String goods_receipt_no);


	@Query("FROM CSmProductMaterialStockNewModel model WHERE model.is_delete = false AND model.product_material_id IN ?1 AND model.company_id = ?2 AND model.batch_no IN ?3")
	List<CSmProductMaterialStockNewModel> FnGetAllCottonBalesStockDetails(List<String> distinctMaterialIds, int companyId, List<String> distinctBatchNo);

	@Query("FROM CSmMaterialStockLogModel model WHERE model.is_delete = false AND model.product_material_id IN ?1 AND model.company_id = ?2 AND model.batch_no IN ?3")
	List<CSmMaterialStockLogModel> FnGetAllCottonBalesStockLogDetails(List<String> distinctMaterialIds, int companyId, List<String> distinctBatchNo);
}
