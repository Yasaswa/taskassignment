package com.erp.FinishGoods.SmProductFgProcess.Service;

import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessModel;
import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessViewModel;

import java.util.List;

public interface ISmProductFgProcessService {

//	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	List<CSmProductFgProcessViewModel> FnShowAllActiveRecords();

	List<CSmProductFgProcessModel> FnShowParticularRecord(int product_fg_id);

}
