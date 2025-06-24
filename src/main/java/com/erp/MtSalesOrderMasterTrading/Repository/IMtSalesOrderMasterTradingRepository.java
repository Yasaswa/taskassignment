package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface IMtSalesOrderMasterTradingRepository extends JpaRepository<CMtSalesOrderMasterTradingModel, Integer> {

    @Query(value = "FROM CMtSalesOrderMasterTradingModel model where model.is_delete =0 and model.sales_order_master_transaction_id = ?1 and model.company_id = ?2")
    CMtSalesOrderMasterTradingModel FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id);


    @Query(value = "SELECT * FROM mtv_sales_order_master_trading_summary  WHERE sales_order_no = ?1 and sales_order_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
    Map<String, Object> FnShowSalesOrderMasterRecord(String sales_order_no, int sales_order_version,
                                                     String financial_year, int company_id);


    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_master_trading set is_delete = 1, deleted_by = ?4 , deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
    void deleteSalesDetails(String sales_order_no, int sales_order_version, int company_id, String deleted_by);


    @Query(value = "Select * from mt_sales_order_master_trading where is_delete = 0 and sales_order_no = ?1 and company_id = ?2", nativeQuery = true)
    CMtSalesOrderMasterTradingModel getLastSalesOrderVersion(String sales_order_no, String string);

    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_master_trading set sales_order_mail_sent_status = ?1 where company_id = ?2 and sales_order_master_transaction_id =?3", nativeQuery = true)
    void updateMailStatus(String sales_order_mail_sent_status, int company_id, int sales_order_master_transaction_id);

    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_master_trading set sales_order_acceptance_status = ?1 where sales_order_master_transaction_id =?2 and company_id = ?3", nativeQuery = true)
    void FnAcceptCustomerOrder(String sales_order_acceptance_status, int sales_order_master_transaction_id, int company_id);

    @Query(value = "FROM CMtSalesOrderMasterTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.sales_order_no IN ?2")
    List<CMtSalesOrderMasterTradingModel> FnShowSalesOrdersBySONos(int company_id, List<String> salesOrderNosList);


    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_master_trading set sales_order_status = 'X', modified_by = ?4 , modified_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
    void cancelSalesMasterDetails(String sales_order_no, int sales_order_version, int company_id, String modified_by);

    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_master_trading set sales_order_status = ?3, modified_by = ?4 , modified_on = CURRENT_TIMESTAMP() where customer_order_no = ?1  and company_id = ?2", nativeQuery = true)
    void updateSalesOrderMasterDetail(String customerOrderNo, int companyId, String salesOrderStatus, String modifiedBy);


    @Query("SELECT so FROM CMtSalesOrderDetailsTradingViewModel so WHERE so.sales_order_status IN ('A','I') AND so.sales_order_date < :overdueSODate")
    List<CMtSalesOrderDetailsTradingViewModel> GetAllOverDueSalesOrders(@Param("overdueSODate") String overdueSODate);

    @Query(value = "SELECT property_value FROM am_properties WHERE properties_master_name='DepartmentHeadMails' AND property_group='SO' AND is_delete=0", nativeQuery = true)
    List<String> findActiveDepartmentHeadEmails();

}
