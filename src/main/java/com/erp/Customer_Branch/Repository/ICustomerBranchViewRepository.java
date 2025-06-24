package com.erp.Customer_Branch.Repository;

import com.erp.Customer_Branch.Model.CCustomerBranchViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ICustomerBranchViewRepository extends JpaRepository<CCustomerBranchViewModel, Long> {

	@Query(value = "Select * FROM cmv_customer_branch order by cust_branch_id Desc", nativeQuery = true)
	Page<CCustomerBranchViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer_branch where is_delete =0  order by cust_branch_id Desc", nativeQuery = true)
	Page<CCustomerBranchViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer_branch where is_delete =0 and cust_branch_id = ?1", nativeQuery = true)
	CCustomerBranchViewModel FnShowParticularRecordForUpdate(int cust_branch_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_customer_branch where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and cust_branch_id = ?3")
	CCustomerBranchViewModel FnShowParticularRecord(int company_id, int company_branch_id, int cust_branch_id);

	@Query(value = "Select * FROM cmv_customer_branch_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
