package com.erp.XtWastageProduction.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.erp.XtWastageProduction.Model.CXtWeavingProductionWastageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IXtWeavingProductionWastageRepository extends JpaRepository<CXtWeavingProductionWastageModel, Integer>{

    @Modifying
    @Transactional
    @Query(value = "update CXtWeavingProductionWastageModel model SET model.is_delete = 1 , model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_wastage_id = ?1")
    void FnDeleteProductionSizingWastageRecord(int weaving_production_wastage_id, String deleted_by);

    @Modifying
    @Transactional
    @Query(value = "update xt_weaving_production_warping_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_wastage_id NOT IN ?1 AND production_set_no = ?2 AND sub_section_id=?4 AND company_id = ?3", nativeQuery = true)
    void FnUpdateWastageRecords(List<Integer> distinctWastageIds, String production_set_no, int company_id, Integer subSectionId);


    @Query(value = "FROM CXtWeavingProductionWastageModel model WHERE model.is_delete = 0 and model.production_set_no = ?1 and model.sub_section_id = ?2 and model.company_id = ?3")
    List<CXtWeavingProductionWastageModel> FnShowParticularRecordForUpdate(String production_set_no,int sub_section_id, int company_id);

    @Query("FROM CXtWeavingProductionWastageModel model WHERE model.is_delete = 0 and model.weaving_production_wastage_id = ?1 and model.sub_section_id = ?2 and model.company_id = ?3")
    List<CXtWeavingProductionWastageModel> FnShowParticularRecordForUpdateWVWV(Integer weaving_production_wastage_id, int sub_section_id, int company_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_warping_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP WHERE weaving_production_wastage_id IN (?1) AND company_id = ?2 AND sub_section_id=?3", nativeQuery = true)
    void updateWeavingProductionSizingWastageDetailsWVWV(List<Integer> distinctWastageIds, int companyId, Integer subSectionId);
}
