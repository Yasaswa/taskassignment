package com.erp.Customer_Branch.Repository;

import com.erp.Customer_Branch.Model.CCustomerBranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ICustomerBranchRepository extends JpaRepository<CCustomerBranchModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_branch set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where cust_branch_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int cust_branch_id, int company_id);

//	@Query(value = "SELECT * FROM cm_customer_branch WHERE (cust_branch_name = ?1 or (?2 IS NOT NULL AND cust_branch_short_name = ?2)) and company_id = ?3", nativeQuery = true)
//	CCustomerBranchModel getCheck(String cust_branch_name, String cust_branch_short_name, int company_id);

	@Query(value = "SELECT * FROM cm_customer_branch WHERE cust_branch_name = ?1 and customer_id = ?2 and company_id = ?3", nativeQuery = true)
	CCustomerBranchModel getCheck(String cust_branch_name,int customer_id,  int company_id);
	@Modifying
	@Transactional
	@Query(value = "update cm_customer_branch set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supp_branch_id=?1 and is_delete = 0", nativeQuery = true)
	void FnDeleteCustomerBranchRecords(int cust_branch_id, String deleted_by);

	@Query(value = "FROM CCustomerBranchModel model where model.is_delete = 0 and model.customer_id = ?1 and model.company_id = ?2")
	List<CCustomerBranchModel> FnShowCustomerBranchRecords(int customer_id, int company_id);

}
