package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductRmStockDetailsViewRepository extends JpaRepository<CSmProductRmStockDetailsViewModel, Integer> {

	@Query(value = "FROM CSmProductRmStockDetailsViewModel model where model.product_rm_id IN ?1 and model.company_id = ?2 and model.is_delete = 0 and model.day_closed = false and model.closing_balance_quantity > 0 order by model.stock_transaction_id")
	List<CSmProductRmStockDetailsViewModel> FnGetStockDetails(List<Integer> materialIds, int company_id);

}
