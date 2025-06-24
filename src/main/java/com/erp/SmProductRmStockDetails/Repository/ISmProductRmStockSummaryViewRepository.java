package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISmProductRmStockSummaryViewRepository extends JpaRepository<CSmProductRmStockSummaryViewModel, Integer> {

}
