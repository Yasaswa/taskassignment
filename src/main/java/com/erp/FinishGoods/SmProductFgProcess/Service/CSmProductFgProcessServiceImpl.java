package com.erp.FinishGoods.SmProductFgProcess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessModel;
import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessViewModel;
import com.erp.FinishGoods.SmProductFgProcess.Repository.ISmProductFgProcessRepository;
import com.erp.FinishGoods.SmProductFgProcess.Repository.ISmProductFgProcessViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CSmProductFgProcessServiceImpl implements ISmProductFgProcessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgProcessRepository iSmProductFgProcessRepository;

	@Autowired
	ISmProductFgProcessViewRepository iSmProductFgProcessViewRepository;

//	@Override
//	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
//		CSmProductFgProcessModel cProductFgProcessModel = new CSmProductFgProcessModel();
//		JSONObject responce = new JSONObject();
//		int company_branch_id = 0;
//		int company_id = 0;
//		int product_fg_id = 0;
//		try {
//			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
//			JSONObject productProcessIds = (JSONObject) jsonObject.get("productProcessIds");
//
//			if (!compAndBrnchId.keySet().isEmpty()) {
//				for (String currentKey : compAndBrnchId.keySet()) {
//					Object key = compAndBrnchId.get(currentKey);
//					String value = key.toString();
//					if (currentKey.equals("company_branch_id")) {
//						company_branch_id = Integer.parseInt(value);
//					} else if (currentKey.equals("company_id")) {
//						company_id = Integer.parseInt(value);
//					} else if (currentKey.equals("product_fg_id")) {
//						product_fg_id = Integer.parseInt(value);
//					}
//				}
//			}
//
//			Object obj = iSmProductFgProcessRepository.updateProductFGProcessActiveStatus(product_fg_id, company_id, company_branch_id);
//
//
//			if (!productProcessIds.keySet().isEmpty()) {
//				for (String currentKey : productProcessIds.keySet()) {
//					CSmProductFgProcessModel processModel = new CSmProductFgProcessModel();
//					Object key = productProcessIds.get(currentKey);
//					String product_process_id = key.toString();
//
//					processModel.setcompany_id(company_id);
//					processModel.setcompany_branch_id(company_branch_id);
//					processModel.setproduct_process_id(Integer.parseInt(product_process_id));
//					processModel.setproduct_fg_id(product_fg_id);
//
//					cProductFgProcessModel = iSmProductFgProcessRepository.save(processModel);
//				}
//
//			}
//			responce.put("success", "1");
//			responce.put("message", "Records added successfully !...");
//			responce.put("error", "");
//
//			return responce;
//
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgProcess/FnAddUpdateRecord", sqlEx.getErrorCode(),sqlEx.getMessage());
//             	responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", sqlEx.getMessage());
//				return responce;
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgProcess/FnAddUpdateRecord", 0, e.getMessage());
//	        responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//			return responce;
//
//		}
//
//		return responce;
//
//	}


	@Override
	public List<CSmProductFgProcessViewModel> FnShowAllActiveRecords() {
		return iSmProductFgProcessViewRepository.FnShowAllActiveRecords();
	}

	@Override
	public List<CSmProductFgProcessModel> FnShowParticularRecord(int product_fg_id) {
		return iSmProductFgProcessRepository.FnShowParticularRecord(product_fg_id);
	}

}
