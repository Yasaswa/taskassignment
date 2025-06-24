package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderPaymentTermsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesOrderPaymentTermsTradingViewRepository
		extends JpaRepository<CMtSalesOrderPaymentTermsTradingViewModel, Integer> {

	@Query(value = "FROM CMtSalesOrderPaymentTermsTradingViewModel viewM where viewM.is_delete = 0 and viewM.company_id = ?1 order by viewM.sales_order_payment_terms_transaction_id Desc")
	Page<CMtSalesOrderPaymentTermsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderPaymentTermsTradingViewModel viewM where viewM.is_delete =0 and viewM.sales_order_payment_terms_transaction_id = ?1 and viewM.company_id = ?2 order by viewM.sales_order_payment_terms_transaction_id Desc")
	Page<CMtSalesOrderPaymentTermsTradingViewModel> FnShowParticularRecord(int sales_order_payment_terms_transaction_id,
	                                                                       Pageable pageable, int company_id);

	@Query(value = "from CMtSalesOrderPaymentTermsTradingViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderPaymentTermsTradingViewModel> FnShowSalesOrderPaymentTerms(String sales_order_no,
	                                                                             int sales_order_version, int company_id);


}
