package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryTermsTradingRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMtEnquiryTermsTradingRptRepository extends JpaRepository<CMtEnquiryTermsTradingRptModel_Not_Used, String> {

//	
//	@Query(value ="SELECT * FROM mtv_enquiry_terms_trading_rpt", nativeQuery = true)
//	Page<CMtEnquiryTermsTradingRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);


}
