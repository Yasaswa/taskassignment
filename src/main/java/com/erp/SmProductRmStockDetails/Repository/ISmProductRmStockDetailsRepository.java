package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmCustomerStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductRmStockDetailsRepository extends JpaRepository<CSmProductRmStockDetailsModel, Integer> {

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete=0 and model.company_id = ?1 AND model.goods_receipt_no = ?2 AND model.goods_receipt_version =?3 AND model.stock_date = ?4 AND model.product_rm_id IN ?5")
    List<CSmProductRmStockDetailsModel> FnGetProductRmStockDetails(int company_id, String goods_receipt_no, int goods_receipt_version, String todayDate, List<Integer> distinctMaterialIds);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.product_rm_id IN ?1 and model.company_id = ?2 and model.is_delete = 0 and model.closing_balance_quantity > 0 order by model.stock_transaction_id")
    List<CSmProductRmStockDetailsModel> FnGetStockDetails(List<String> materialIds, int company_id);

    @Query(value = "Select convsersion_factor FROM smv_product_unit_conversion where is_delete =0 and product_from_unit_id = ?1 and product_to_unit_id = ?2 and company_id = ?3", nativeQuery = true)
    Integer FnGetUnitConversionFactor(Integer product_material_unit_id, Integer product_material_unit_id1, int company_id);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete=0 AND model.day_closed = false")
    List<CSmProductRmStockDetailsModel> getAllStockDetail();

    @Modifying
    @Transactional
    @Query(value = "UPDATE CSmProductRmStockDetailsModel model SET day_closed = TRUE where model.is_delete=0")
    void FnUpdateStockDetailsDayClosed();

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete=0 and model.company_id = ?1 AND model.product_rm_id IN ?2")
    List<CSmProductRmStockDetailsModel> FnGetProductRmDetailsSummary(int companyId, List<String> distinctMaterialIds);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 AND model.company_id = ?2")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetails(List<String> distinctMaterialIds, int company_id);

    @Query(value = "Select * FROM sm_product_rm_stock_details_customer where is_delete= 0 and product_rm_id IN ?1 AND company_id = ?2", nativeQuery = true)
    List<CSmProductRmCustomerStockDetailsModel> FnGetAllProductRmCustomerStockDetails(List<String> distinctMaterialIds, int company_id);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 ")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsFromMainGodown(List<String> distinctMaterialIds);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and closing_balance_quantity > 0 and godown_id = 2")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterials(List<String> distinctMaterialIds, int company_id);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete = 0 and model.goods_receipt_no IN ?1 and model.company_id = ?2")
    List<CSmProductRmStockDetailsModel> getGrnDetailsForTransferStock(List<String> distinctGrnNos, Integer company_id);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.product_rm_id IN ?1 and stock_date = ?2")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterialsByIssueDate(List<String> distinctMaterialIds, String issueDate);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.product_rm_id IN ?1 AND model.company_id = ?2  and stock_date = ?3")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsByIssueDate(List<String> distinctMaterialIds, int companyId, String issueDate);

    @Transactional
    @Modifying
    @Query("UPDATE CSmProductRmStockDetailsModel model SET model.is_delete = true, model.day_closed = true, model.deleted_by = ?2 WHERE model.product_rm_id = ?1")
    void deleteRmStockDetails(String product_rm_id, String deleted_by);

    @Transactional
    @Modifying
    @Query("UPDATE CSmProductRmStockDetailsModel model SET model.batch_rate = ?2 WHERE model.goods_receipt_no = 'Opening Balance' and model.product_rm_id = ?1 and model.company_id = ?3 and model.is_delete = 0")
    void updateOpeningBalRate(String productMaterialId, double materialRate, int companyId);

    @Query(value = "select material_rate from sm_product_rm_stock_summary model where model.is_delete= false and model.product_rm_id = ?1 AND model.company_id = ?2 and model.day_closed = false", nativeQuery = true)
    double getMaterialRate(String productMaterialId, int companyId);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and goods_receipt_no = ?3")
    CSmProductRmStockDetailsModel FnGetToCompanieOpeningBalanceEntry(List<String> distinctMaterialIds, int toCompany, String openingBalance);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and batch_no IN ?3  and closing_balance_quantity > 0 and godown_id = 2")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockDetailsRawMaterialsLotwise(List<String> distinctMaterialIds, int company_id, List<String> distinctIssue_batch_no);
//  and weight_per_box_item IN ?4

    @Transactional
    @Modifying
    @Query(value = "UPDATE sm_product_rm_stock_details sprmsd SET sprmsd.closing_balance_quantity = ?1, sprmsd.closing_balance_weight = ?2, sprmsd.closing_no_of_boxes = ?3 WHERE sprmsd.weight_per_box_item = ?4 AND sprmsd.company_id = ?5 AND sprmsd.product_rm_id = ?6 AND sprmsd.goods_receipt_no IN ?7 AND sprmsd.day_closed = 0 AND sprmsd.godown_id = 5 AND sprmsd.godown_section_id = 5 AND sprmsd.godown_section_beans_id = 5", nativeQuery = true)
    void updateStockDetailsForWarping(double closing_balance_qty, double closing_balance_weight, Integer setClosing_no_of_boxes, double cone_per_weight, int company_id, String product_rm_id, List<String> updatedGRNumbers);

    @Query(value = "select department_id,godown_id From cm_department where is_delete=false and department_id=?1", nativeQuery = true)
    List<Object[]> getGodownIdAndSubDPt(int departmentId);

    @Query(value = "select godown_id From cm_department where is_delete=false and department_id=?1", nativeQuery = true)
    int getGodownId(int subDepartmentIds);

    @Query(value = "FROM CSmProductRmStockDetailsModel model where model.is_delete= false and model.day_closed = false and model.product_rm_id IN ?1 and company_id = ?2 and batch_no IN ?3  and weight_per_box_item IN ?4 and closing_balance_quantity > 0 and godown_id = ?5")
    List<CSmProductRmStockDetailsModel> FnGetAllProductRmStockRawMaterialsLotGodownwise(List<String> distinctMaterialIds, int company_id, List<String> distinctIssue_batch_no, List<Double> weights,int GodownId);

}

