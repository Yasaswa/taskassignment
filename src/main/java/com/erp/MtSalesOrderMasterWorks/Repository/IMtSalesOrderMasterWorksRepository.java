package com.erp.MtSalesOrderMasterWorks.Repository;

import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface IMtSalesOrderMasterWorksRepository extends JpaRepository<CMtSalesOrderMasterWorksModel, Integer> {

	@Query(value = "FROM CMtSalesOrderMasterWorksModel model where model.is_delete =0 and model.sales_order_master_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesOrderMasterWorksModel FnShowParticularRecordForUpdate(int sales_order_master_transaction_id,
	                                                              int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_master_works  set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesDetails(String sales_order_no, int sales_order_version, int company_id);

	@Query(value = "SELECT * FROM mt_sales_order_master_works  WHERE sales_order_no = ?1 and sales_order_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowSalesOrderMasterRecord(String sales_order_no, int sales_order_version,
	                                                 String financial_year, int company_id);

}
