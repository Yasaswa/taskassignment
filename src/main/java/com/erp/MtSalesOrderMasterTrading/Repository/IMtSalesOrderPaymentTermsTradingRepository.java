package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderPaymentTermsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IMtSalesOrderPaymentTermsTradingRepository extends JpaRepository<CMtSalesOrderPaymentTermsTradingModel, Integer> {

	@Query(value = "FROM CMtSalesOrderPaymentTermsTradingModel model where model.is_delete =0 and model.sales_order_payment_terms_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesOrderPaymentTermsTradingModel FnShowParticularRecordForUpdate(int sales_order_payment_terms_transaction_id, int company_id);

	@Query(value = "FROM CMtSalesOrderPaymentTermsTradingModel cmts where cmts.is_delete =0 and cmts.payment_terms_name = ?1")
	CMtSalesOrderPaymentTermsTradingModel checkIfNameExist(String getpayment_terms_name);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_payment_terms_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updatePaymentTermsStatus(String sales_order_no, Integer sales_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_payment_terms_trading set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderPaymentTerms(String sales_order_no, int sales_order_version, int company_id,String deleted_by);


	@Query(value = "from CMtSalesOrderPaymentTermsTradingModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderPaymentTermsTradingModel> FnShowSalesOrderPaymentTerms(String sales_order_no, int sales_order_version, String company_id);


}
