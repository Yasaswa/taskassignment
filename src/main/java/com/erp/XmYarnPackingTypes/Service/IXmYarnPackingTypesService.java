package com.erp.XmYarnPackingTypes.Service;
import java.util.Map;


import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesModel;


public interface IXmYarnPackingTypesService {

	Map<String, Object> FnAddUpdateRecord(CXmYarnPackingTypesModel xmYarnPackingTypesModel);

	Map<String, Object> FnDeleteRecord(int yarn_packing_types_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int yarn_packing_types_id, int company_id);

	

}
