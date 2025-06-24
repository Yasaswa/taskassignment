package com.erp.Payment_Terms.Repository;

import com.erp.Payment_Terms.Model.CPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPaymentTermsRepository extends JpaRepository<CPaymentTermsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_payment_terms set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where payment_terms_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int payment_terms_id, String deleted_by);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_payment_terms WHERE payment_terms_name = ?1  and is_delete = 0")
	CPaymentTermsModel getCheck(String payment_terms_name);

}
