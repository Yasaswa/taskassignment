package com.erp.SmProductStockAdjustment.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentDetailsViewModel;

public interface ISmProductStockAdjustmentDetailsViewModelRepository extends JpaRepository<CSmProductStockAdjustmentDetailsViewModel, Integer> {


	@Query(value = "FROM CSmProductStockAdjustmentDetailsViewModel psad WHERE psad.is_delete = 0 and psad.stock_adjustment_transaction_id = ?1 and psad.company_id = ?2")
	List<CSmProductStockAdjustmentDetailsViewModel> FnShowProductStockAdjustmentDetailsRecord(
			int stock_adjustment_transaction_id, int company_id);
	
	
}