package com.erp.Payment_Terms.Repository;

import com.erp.Payment_Terms.Model.CPaymentTermsRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentTermsRptRepository_Not_Used extends JpaRepository<CPaymentTermsRptModel_Not_Used, String> {

//	@Query(value = "SELECT * FROM cmv_payment_terms_rpt", nativeQuery = true)
//	Page<CPaymentTermsRptModel> FnShowAllReportRecords(Pageable pageable);

}
