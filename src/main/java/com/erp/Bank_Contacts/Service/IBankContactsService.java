package com.erp.Bank_Contacts.Service;

import com.erp.Bank_Contacts.Model.CBankContactsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBankContactsService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int bank_contact_id, int company_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	List<CBankContactsViewModel> FnShowParticularRecordForUpdate(int bank_id, int company_id);

	Object FnShowParticularRecord(int company_id, int bank_contact_id);

}
