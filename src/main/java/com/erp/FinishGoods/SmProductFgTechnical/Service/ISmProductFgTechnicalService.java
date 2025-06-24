package com.erp.FinishGoods.SmProductFgTechnical.Service;

import org.json.JSONObject;

public interface ISmProductFgTechnicalService {

//	JSONObject FnAddUpdateRecord(CSmProductFgTechnicalModel cSmProductFgTechnicalModel);

	Object FnDeleteRecord(int product_fg_technical_id);

	JSONObject FnShowParticularRecordForUpdate(int product_fg_id);


}
