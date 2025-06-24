package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderSchedulesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtPurchaseOrderSchedulesViewRepository extends JpaRepository<CPtPurchaseOrderSchedulesViewModel, Integer> {


	@Query(value = "FROM CPtPurchaseOrderSchedulesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.purchase_order_schedules_transaction_id Desc")
	Page<CPtPurchaseOrderSchedulesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CPtPurchaseOrderSchedulesViewModel model where model.is_delete = 0 and model.purchase_order_schedules_transaction_id = ?1 and model.company_id =?2 order by model.purchase_order_schedules_transaction_id Desc")
	Page<CPtPurchaseOrderSchedulesViewModel> FnShowParticularRecord(int purchase_order_schedules_transaction_id,
	                                                                Pageable pageable, int company_id);


	@Query(value = "from CPtPurchaseOrderSchedulesViewModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3 and model.is_delete = 0")
	List<CPtPurchaseOrderSchedulesViewModel> FnShowPurchaseOrderSchedules(String purchase_order_no,
	                                                                      Integer purchase_order_version, int company_id);


}
