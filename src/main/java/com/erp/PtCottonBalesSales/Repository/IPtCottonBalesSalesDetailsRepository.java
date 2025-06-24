package com.erp.PtCottonBalesSales.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesDetailsModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtCottonBalesSalesDetailsRepository extends JpaRepository<CPtCottonBalesSalesDetailsModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update CPtCottonBalesSalesDetailsModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    void FnDeleteBalesSalesDetailsRecord(int pt_cotton_bales_sales_master_transaction_id, int companyId, String deletedBy);

    @Query(value = "FROM CPtCottonBalesSalesDetailsModel model where model.is_delete = 0 and model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    List<CPtCottonBalesSalesDetailsModel> FnShowParticularRecordForUpdate(int pt_cotton_bales_sales_master_transaction_id, int companyId);
}
