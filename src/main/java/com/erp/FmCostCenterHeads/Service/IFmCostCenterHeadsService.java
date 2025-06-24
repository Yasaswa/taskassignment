package com.erp.FmCostCenterHeads.Service;

import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsModel;

import java.util.Map;

public interface IFmCostCenterHeadsService {

	Map<String, Object> FnAddUpdateRecord(CFmCostCenterHeadsModel costCenterHeadRequest);

	Map<String, Object> FnDeleteRecord(int cost_center_heads_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int cost_center_heads_id);
}
