package com.erp.Payment_Terms.Repository;

import com.erp.Payment_Terms.Model.CPaymentTermsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPaymentTermsViewRepository extends JpaRepository<CPaymentTermsViewModel, Long> {

	@Query(value = "Select * FROM  cmv_payment_terms order by payment_terms_id Desc", nativeQuery = true)
	Page<CPaymentTermsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_payment_terms where is_delete =0  order by payment_terms_id", nativeQuery = true)
	Page<CPaymentTermsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_payment_terms where is_delete =0 and payment_terms_id = ?1", nativeQuery = true)
	CPaymentTermsViewModel FnShowParticularRecordForUpdate(int payment_terms_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_payment_terms where is_delete =0 and company_id = ?1 and payment_terms_id = ?2")
	CPaymentTermsViewModel FnShowParticularRecord(int company_id, int payment_terms_id);

	@Query(value = "SELECT * FROM cmv_payment_terms_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
