package com.erp.Customer_Contacts.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICustomerContactsService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int customer_contact_id);

	Object FnDeleteRecord(int customer_contact_id);

	Map<String, Object> FnShowParticularRecord(int customer_id, int company_id);


}
