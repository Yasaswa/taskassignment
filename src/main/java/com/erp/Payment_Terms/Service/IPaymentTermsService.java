package com.erp.Payment_Terms.Service;

import com.erp.Payment_Terms.Model.CPaymentTermsModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPaymentTermsService {

	Map<String, Object> FnAddUpdateRecord(CPaymentTermsModel cPaymentTermsModel);

	Object FnDeleteRecord(int payment_terms_id, String deleted_by);

	Map<String, Object> FnShowAllRecords(Pageable pageable);

	Map<String, Object> FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int payment_terms_id);

	Map<String, Object> FnShowParticularRecord(int company_id, int payment_terms_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable);

}
