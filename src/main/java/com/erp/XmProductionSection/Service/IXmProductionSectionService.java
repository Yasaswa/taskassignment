package com.erp.XmProductionSection.Service;

import com.erp.XmProductionSection.Model.CXmProductionSectionModel;
import com.erp.XmProductionSection.Model.CXmProductionSubSectionModel;

import java.util.Map;

import org.json.JSONObject;

public interface IXmProductionSectionService {

	Map<String, Object> FnAddUpdateProdSectionRecord(CXmProductionSectionModel xmProductionSectionModel);

	Map<String, Object> FnDeleteProdSectionRecord(int production_section_id, String deleted_by);

	Map<String, Object> FnDeleteProdSubSectionRecord(int production_sub_section_id, String deleted_by);

	Map<String, Object> FnShowParticularProdSectionRecordForUpdate(int production_section_id, int company_id);

	Map<String, Object> FnShowParticularProdSubSectionRecordForUpdate(int production_sub_section_id, int company_id);

	Map<String, Object> FnAddUpdateProdSubSectionAndGodownMappingRecord(JSONObject jsonObject);


}
