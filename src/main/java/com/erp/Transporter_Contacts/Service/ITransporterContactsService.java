package com.erp.Transporter_Contacts.Service;

import com.erp.Transporter.Model.CTransporterContactsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransporterContactsService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int transporter_contact_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	List<CTransporterContactsViewModel> FnShowParticularRecordForUpdate(int transporter_id);

	Object FnShowParticularRecord(int company_id, int company_branch_id, int transporter_contact_id);

}
