package com.erp.PtCottonBalesSales.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtCottonBalesSalesDetailsViewRepository extends JpaRepository<CPtCottonBalesSalesDetailsViewModel, Integer> {

    @Query(value = "FROM CPtCottonBalesSalesDetailsViewModel model where model.is_delete = 0 and model.pt_cotton_bales_sales_master_transaction_id = ?1 and model.company_id = ?2")
    List<CPtCottonBalesSalesDetailsViewModel> FnShowParticularRecordForUpdate(int pt_cotton_bales_sales_master_transaction_id, int companyId);
}
