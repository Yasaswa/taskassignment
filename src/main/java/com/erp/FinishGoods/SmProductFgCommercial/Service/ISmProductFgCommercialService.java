package com.erp.FinishGoods.SmProductFgCommercial.Service;

import com.erp.FinishGoods.SmProductFgCommercial.Model.CSmProductFgCommercialModel;
import org.json.JSONObject;

public interface ISmProductFgCommercialService {

	JSONObject FnAddUpdateRecord(CSmProductFgCommercialModel cSmProductFgCommercialModel);

	Object FnDeleteRecord(int product_fg_commercial_id);

	JSONObject FnShowParticularRecordForUpdate(int product_fg_id);

}
