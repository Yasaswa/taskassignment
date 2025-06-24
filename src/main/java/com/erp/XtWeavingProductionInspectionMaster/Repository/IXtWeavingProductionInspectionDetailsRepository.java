package com.erp.XtWeavingProductionInspectionMaster.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionDetailsModel;


public interface IXtWeavingProductionInspectionDetailsRepository extends JpaRepository<CXtWeavingProductionInspectionDetailsModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update CXtWeavingProductionInspectionDetailsModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_inspection_master_id = ?1 AND company_id = ?2")
    void updateWVProductionInspectionDetailsRecord(int weaving_production_inspection_master_id, int company_id);

    @Modifying
    @Transactional
    @Query(value = "update CXtWeavingProductionInspectionDetailsModel model SET model.is_delete = 1 , model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_inspection_master_id = ?1 and model.company_id = ?2")
    void FnDeleteRecordWVProductionInspectionDetailsRecord(int weaving_production_inspection_master_id, int company_id,
                                                           String deleted_by);


    @Query(value = "FROM CXtWeavingProductionInspectionDetailsModel model WHERE model.is_delete = 0 and inspection_production_set_no IN ?1")
    List<CXtWeavingProductionInspectionDetailsModel> getAllWeavingProductionInspectionDetails(
            List<String> weavingProductionInspectionSetNumbers);


    @Modifying
    @Transactional
    @Query(value = "update xt_weaving_production_inspection_details set dispatch_quantity =?1,dispatch_weight =?6, stock_status = ?2 ,stock_status_description = ?8, dispatch_date= ?5, dispatch_challan_no = ?7, modified_on = CURRENT_TIMESTAMP() WHERE weaving_production_inspection_details_id = ?3 and company_id = ?4", nativeQuery = true)
    void updateDispatchInspectionDetails(double dispatchQuantity, String dispatchStatus, Integer weavingProductionInspectionDetailsId, Integer companyId, String dispatch_date, double dispatchWeight, String dispatch_challan_no,String stock_status_description);

    @Query(value = "FROM CXtWeavingProductionInspectionDetailsModel model WHERE model.is_delete = false and model.weaving_production_inspection_details_id  IN ?1 and company_id = ?2")
    List<CXtWeavingProductionInspectionDetailsModel> getAllDispatchQtySummary(List<Integer> distinctInspDetailsIds, int companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CXtWeavingProductionInspectionDetailsModel model SET model.inspection_production_status = ?2, model.modified_by = ?3, model.modified_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_inspection_details_id IN (?1) and model.is_delete = false")
    void updateInspectionDtlsByDispNote(List<Integer> yarnInspectionDtlsIds, String inspectionDtlStatus, String modifiedBy);

    @Query(value = "FROM CXtWeavingProductionInspectionDetailsModel model WHERE model.is_delete = false and model.roll_no IN ?1 and model.sort_no IN ?2 and company_id = ?3")
    List<CXtWeavingProductionInspectionDetailsModel> getAllDetailsOfInspection(List<Integer> distinctRollNo, List<String> distinctSortNo, int company_id);


    @Query(value = "SELECT * FROM xt_weaving_production_inspection_details WHERE book_type_id = :bookTypeId and is_delete = false ORDER BY roll_no DESC LIMIT 1", nativeQuery = true)
    Map<String, Object> GetLastRollNoWeavingInspection(int bookTypeId);
}
