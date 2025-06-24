package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderTermsTradingModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderTermsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IMtSalesOrderTermsTradingRepository extends JpaRepository<CMtSalesOrderTermsTradingModel, Integer> {

	@Query(value = "FROM CMtSalesOrderTermsTradingModel model where model.is_delete =0 and model.sales_order_terms_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesOrderTermsTradingModel FnShowParticularRecordForUpdate(int sales_order_terms_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_terms_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateOrderTermdetails(String sales_order_no, Integer sales_order_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_terms_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String sales_order_no, Integer sales_order_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_terms_trading set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderTermsTrading(String sales_order_no, int sales_order_version, int company_id,String deleted_by);

	@Query(value = "from CMtSalesOrderTermsTradingViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderTermsTradingViewModel> FnShowSalesOrderTermsTrading(String sales_order_no,
	                                                                      int sales_order_version, int company_id);


	@Query(value = "from CMtSalesOrderTermsTradingModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderTermsTradingModel> FnShowSalesOrdertermsTrading(String sales_order_no, int sales_order_version, String company_id);


}
