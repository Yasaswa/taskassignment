package com.erp.PtCottonBalesSales.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesDetailsModel;
import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPtCottonBalesSalesMasterRepository extends JpaRepository<CPtCottonBalesSalesMasterModel, Integer> {

    @Query(value = "From CPtCottonBalesSalesMasterModel model Where model.pt_cotton_bales_sales_master_transaction_id = ?1 AND model.is_delete = 0 AND model.company_id = ?2")
    CPtCottonBalesSalesMasterModel FnGetBalesSalesMasterModel(Integer pt_cotton_bales_sales_master_transaction_id, Integer company_id);


    @Query(value = "Update CPtCottonBalesSalesMasterModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    void FnDeleteBalesSalesMasterModel(Integer pt_cotton_bales_sales_master_transaction_id, Integer company_id, String username);

}
