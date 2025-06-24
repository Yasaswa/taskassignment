package com.erp.Bank.Repository;

import com.erp.Bank.Model.CBankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IBankRepository extends JpaRepository<CBankModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where bank_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int bank_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_banks set is_delete = 1,deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP()  where bank_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int bank_id, Integer company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_banks WHERE bank_name = ?1 and bank_account_no = ?2  and account_type = ?3  and bank_branch_name= ?4 and company_id = ?5")
	CBankModel getCheck(String bank_name, String bank_account_no, String account_type, String bank_branch_name,
	                    Integer company_id);

}
