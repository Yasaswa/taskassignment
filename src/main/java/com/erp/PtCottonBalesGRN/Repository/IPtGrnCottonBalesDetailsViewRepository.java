package com.erp.PtCottonBalesGRN.Repository;

import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IPtGrnCottonBalesDetailsViewRepository extends JpaRepository<CPtGrnCottonBalesDetailsViewModel,Integer> {

    @Query(value = "Select * FROM ptv_grn_cotton_bales_details where is_delete =0 and grn_cotton_bales_master_transaction_id = ?1", nativeQuery = true)
    List<CPtGrnCottonBalesDetailsViewModel> getAllDetails(int grnCottonBalesMasterTransactionId);
    @Query(value = "FROM CPtGrnCottonBalesDetailsViewModel model where model.is_delete = 0 and model.purchase_order_no IN ?1 and model.company_id = ?2")
    List<CPtGrnCottonBalesDetailsViewModel> getAllExistingGRNPODetails(List<String> distinctPurchaseOrderNumbers, int companyId);
}
