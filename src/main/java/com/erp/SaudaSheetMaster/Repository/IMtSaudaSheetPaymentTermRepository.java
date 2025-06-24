package com.erp.SaudaSheetMaster.Repository;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsPaymentTermsModel;
import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IMtSaudaSheetPaymentTermRepository extends JpaRepository<CMtSaudaSheetPaymentTermsModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update mt_sauda_sheet_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sauda_sheet_master_id= ?1 and company_id = ?2", nativeQuery = true)
    void updatePaymentTermsStatus(int saudaSheetMasterId, int companyId);

    @Modifying
    @Transactional
    @Query(value = "Update CMtSaudaSheetPaymentTermsModel model  SET  model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.sauda_sheet_master_id = ?1 and model.company_id = ?2")
    void FnDeleteSaudaSheetPaymentTermRecord(int saudaSheetMasterId, int companyId, String deletedBy);

    @Query(value = "FROM CMtSaudaSheetPaymentTermsModel model where model.is_delete = 0 and model.sauda_sheet_master_id = ?1 and model.company_id = ?2")
    List<CMtSaudaSheetPaymentTermsModel> FnShowSaudaSheetPaymentTerms(int saudaSheetMasterId, int companyId);
}
