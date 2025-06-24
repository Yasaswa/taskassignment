package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderMasterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPtPurchaseOrderMasterViewRepository extends JpaRepository<CPtPurchaseOrderMasterViewModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderMasterViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.purchase_order_master_transaction_id Desc")
	Page<CPtPurchaseOrderMasterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	@Query(value = "FROM CPtPurchaseOrderMasterViewModel model where model.is_delete =0 and model.purchase_order_master_transaction_id = ?1 and model.company_id = ?2 order by model.purchase_order_master_transaction_id Desc")
	Page<CPtPurchaseOrderMasterViewModel> FnShowParticularRecord(int purchase_order_master_transaction_id,
	                                                             Pageable pageable, int company_id);
}
