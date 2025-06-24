package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISupplierContactRepository extends JpaRepository<CSupplierContactModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_contacts set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supplier_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int supplier_id, String deleted_by);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_supplier_contacts WHERE supp_contact_person = ?1")
	CSupplierContactModel getCheck(String supp_contact_person);

	@Query("FROM CSupplierContactModel  cscm where  cscm.is_delete =  0 and cscm.supplier_id = ?1 ")
	List<CSupplierContactModel> findBySupplierId(int supplier_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_contacts set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_id=?1 and company_id=?2 and is_delete = 0", nativeQuery = true)
	void updateSupplierContactActiveStatus(int supplier_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier_contacts set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supp_branch_id=?1 and is_delete = 0", nativeQuery = true)
	void FnDeleteSupplierBranchBankRecords(int supp_branch_id, String deleted_by);

}
