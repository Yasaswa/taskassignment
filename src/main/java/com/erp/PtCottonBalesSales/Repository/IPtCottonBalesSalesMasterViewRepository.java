package com.erp.PtCottonBalesSales.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesMasterModel;
import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPtCottonBalesSalesMasterViewRepository extends JpaRepository<CPtCottonBalesSalesMasterViewModel , Integer> {

    @Query(value = "From CPtCottonBalesSalesMasterViewModel model Where model.pt_cotton_bales_sales_master_transaction_id = ?1 AND model.is_delete = 0 AND model.company_id = ?2")
    CPtCottonBalesSalesMasterViewModel FnGetBalesSalesMasterModel(Integer pt_cotton_bales_sales_master_transaction_id, Integer company_id);

}
