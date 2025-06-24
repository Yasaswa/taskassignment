package com.erp.SmProductStockAdjustment.Repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentModel;


public interface ISmProductStockAdjustmentRepository extends JpaRepository<CSmProductStockAdjustmentModel, Integer> {

	@Query(value = "FROM CSmProductStockAdjustmentModel psa WHERE psa.is_delete = 0 and psa.stock_adjustment_transaction_id = ?1 and psa.company_id = ?2")
	CSmProductStockAdjustmentModel FnShowProductStockAdjustmentRecord(int stock_adjustment_transaction_id,
			int company_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE CSmProductStockAdjustmentModel psa SET psa.is_delete = true, psa.deleted_by = ?3, psa.deleted_on = CURRENT_TIMESTAMP() WHERE psa.stock_adjustment_transaction_id  = ?1 and psa.company_id = ?2")
	void FnDeleteStockAdjustmentRecord(int stock_adjustment_transaction_id, int company_id, String deleted_by);

}
