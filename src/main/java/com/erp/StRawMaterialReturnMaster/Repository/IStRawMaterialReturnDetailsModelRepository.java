package com.erp.StRawMaterialReturnMaster.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IStRawMaterialReturnDetailsModelRepository extends JpaRepository<CStRawMaterialReturnDetailsModel, Long> {
	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_return_details set is_delete = 1 ,deleted_by =?3, deleted_on = CURRENT_TIMESTAMP() where issue_return_master_transaction_id = ?1 and company_id = ?2", nativeQuery = true)
	void FnDeleteRawMaterialReturnDetailsRecord(int issue_return_master_transaction_id, int company_id, String deleted_by);

	@Query(value = "FROM CStRawMaterialReturnDetailsModel model WHERE model.issue_return_master_transaction_id = ?1 and model.is_delete = false and model.company_id= ?2")
	List<CStRawMaterialReturnDetailsModel> FnShowRawMaterialReturnDetailRecords(int issue_return_master_transaction_id, int company_id);

	@Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and batch_no IN ?3 and weight_per_box_item IN ?4 and closing_balance_quantity > 0 and godown_id = ?5")
	List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterialsLotwise(List<String> distinctMaterialIds, int company_id, List<String> distinctIssue_batch_no, List<Double> weights,int godownId);

	@Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and batch_no IN ?3 and closing_balance_quantity > 0 and godown_id = ?4")
	List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterialsDptAndLotwise(List<String> distinctMaterialIds, int company_id, List<String> distinctIssue_batch_no, int godownId);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_return_details set is_delete = 1 ,deleted_by =?3, deleted_on = CURRENT_TIMESTAMP() where issue_return_master_transaction_id = ?1 and company_id = ?2", nativeQuery = true)
    void deleteDetailsRecordsForupdate(Integer issueReturnMasterTransactionId , int company_id, String deleted_by);
}





