package com.erp.PtCottonBalesSales.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesDetailsModel;
import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtCottonBalesPaymentTermsRepository extends JpaRepository<CPtCottonBalesSalesPaymentTermsModel, Integer> {

    @Query(value = "Update CPtCottonBalesSalesPaymentTermsModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    void FnDeleteBalesSalesPaymentTermRecords(int pt_cotton_bales_sales_master_transaction_id, int companyId, String deletedBy);

    @Query(value = "FROM CPtCottonBalesSalesPaymentTermsModel model where model.is_delete = 0 and model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    List<CPtCottonBalesSalesPaymentTermsModel> FnShowParticularRecordForUpdate(int pt_cotton_bales_sales_master_transaction_id, int companyId);

}
