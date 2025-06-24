package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IMtEnquiryDetailsTradingViewRepository extends JpaRepository<CMtEnquiryDetailsTradingViewModel, Long> {


	@Query(value = "FROM CMtEnquiryDetailsTradingViewModel model where model.is_delete =0 and model.enquiry_details_transaction_id = ?1 and model.company_id = ?2 order by model.enquiry_details_transaction_id Desc")
	Page<CMtEnquiryDetailsTradingViewModel> FnShowParticularRecord(int enquiry_details_transaction_id, Pageable pageable, int company_id);


	@Query(value = "FROM CMtEnquiryDetailsTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.enquiry_details_transaction_id Desc")
	Page<CMtEnquiryDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "select * FROM mtv_enquiry_details_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();


}
