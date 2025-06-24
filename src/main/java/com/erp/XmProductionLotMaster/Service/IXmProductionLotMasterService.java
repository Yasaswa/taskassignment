package com.erp.XmProductionLotMaster.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterModel;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterViewModel;

public interface IXmProductionLotMasterService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionLotMasterModel xmProductionLotMasterModel);

	Map<String, Object> FnDeleteRecord(int lot_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int lot_id, int company_id);

	

}
