package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderTermsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IMtSalesOrderTermsTradingViewRepository extends JpaRepository<CMtSalesOrderTermsTradingViewModel, Long> {


	@Query(value = "FROM CMtSalesOrderTermsTradingViewModel model where model.is_delete =0 and model.sales_order_terms_transaction_id = ?1 and model.company_id = ?2 order by model.sales_order_terms_transaction_id Desc")
	Page<CMtSalesOrderTermsTradingViewModel> FnShowParticularRecord(int sales_order_terms_transaction_id, Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderTermsTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.sales_order_terms_transaction_id Desc")
	Page<CMtSalesOrderTermsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


}
