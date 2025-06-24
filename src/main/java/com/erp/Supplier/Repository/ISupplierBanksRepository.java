package com.erp.Supplier.Repository;


import com.erp.Supplier.Model.CSupplierBanksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISupplierBanksRepository extends JpaRepository<CSupplierBanksModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_banks set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP()  where supplier_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int supplier_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_supplier_banks WHERE supp_bank_account_type = ?1")
	CSupplierBanksModel getCheck(String supp_bank_account_type);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1  and supplier_id = ?2 and is_delete = 0", nativeQuery = true)
	Object updateSupplierBankActiveStatus(int company_id, int supplier_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_banks set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supp_branch_id=?1 and is_delete = 0", nativeQuery = true)
	void FnDeleteSupplierBranchContactsRecords(int supp_branch_id, String deleted_by);

}
