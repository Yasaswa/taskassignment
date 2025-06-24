package com.erp.XtStoppageProduction.Repository;

import com.erp.XtStoppageProduction.Model.CXtWeavingProductionStoppageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtWeavingProductionStoppageRepository extends JpaRepository<CXtWeavingProductionStoppageModel, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE CXtWeavingProductionStoppageModel model SET model.is_delete = 1, model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP WHERE model.weaving_production_stoppage_id = ?1")
    void FnDeleteProductionSizingStoppageRecord(int weaving_production_stoppage_id, String deleted_by);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_warping_stoppage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP WHERE weaving_production_stoppage_id NOT IN (?1) AND production_set_no=?2 AND sub_section_id=?4 AND company_id = ?3", nativeQuery = true)
    void updateWeavingProductionSizingStoppageDetails(List<Integer> distinctWVStoppageIds, String production_set_no, int company_id, int subSectionId);

    @Query("FROM CXtWeavingProductionStoppageModel model WHERE model.is_delete = 0 and model.weaving_production_stoppage_id = ?1 and model.sub_section_id = ?2 and model.company_id = ?3")
    List<CXtWeavingProductionStoppageModel> FnShowParticularRecordForUpdate(Integer weaving_production_stoppage_id, int sub_section_id, int company_id);

    @Query("FROM CXtWeavingProductionStoppageModel model WHERE model.is_delete = 0 and model.weaving_production_stoppage_id = ?1 and model.sub_section_id = ?2 and model.company_id = ?3")
    List<CXtWeavingProductionStoppageModel> FnShowParticularRecordForUpdateWVWV(Integer weaving_production_stoppage_id, int sub_section_id, int company_id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_warping_stoppage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP WHERE weaving_production_stoppage_id IN (?1) AND company_id = ?2 AND sub_section_id =?3", nativeQuery = true)
    void updateWeavingProductionSizingStoppageDetailsWVWV(List<Integer> distinctStoppageModelIds, int companyId, int subSectionId);
}
