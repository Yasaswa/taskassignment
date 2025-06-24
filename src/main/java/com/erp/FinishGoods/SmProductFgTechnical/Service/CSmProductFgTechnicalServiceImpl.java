package com.erp.FinishGoods.SmProductFgTechnical.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgTechnical.Model.CSmProductFgTechnicalModel;
import com.erp.FinishGoods.SmProductFgTechnical.Repository.ISmProductFgTechnicalRepository;
import com.erp.FinishGoods.SmProductFgTechnical.Repository.ISmProductFgTechnicalViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CSmProductFgTechnicalServiceImpl implements ISmProductFgTechnicalService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgTechnicalRepository iSmProductFgTechnicalRepository;

	@Autowired
	ISmProductFgTechnicalViewRepository iSmProductFgTechnicalViewRepository;

//
//	@Override
//	public JSONObject FnAddUpdateRecord(CSmProductFgTechnicalModel cSmProductFgTechnicalModel) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		Optional<CSmProductFgTechnicalModel> option = iSmProductFgTechnicalRepository
//				.findById(cSmProductFgTechnicalModel.getproduct_fg_technical_id());
//		CSmProductFgTechnicalModel MyModel = null;
//		int company_id = cSmProductFgTechnicalModel.getcompany_id();
//		try {
//			if (option.isPresent()) {
//				CSmProductFgTechnicalModel cSmProductFg = iSmProductFgTechnicalRepository
//						.save(cSmProductFgTechnicalModel);
//				String json = objectMapper.writeValueAsString(cSmProductFg);
//				resp.put("success", "1");
//				resp.put("data", json);
//				resp.put("error", "");
//				resp.put("message", "Record updated successfully!...");
//				System.out.println(" SmProductFgTechnical Updated Successfully!..");
//				return resp;
//			} else {
//				CSmProductFgTechnicalModel model = iSmProductFgTechnicalRepository
//						.checkIfExist(cSmProductFgTechnicalModel.getproduct_fg_technical_name());
//
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", "Technical name  is already exist!");
//
//					return resp;
//
//				} else {
//
//					CSmProductFgTechnicalModel json = iSmProductFgTechnicalRepository.save(cSmProductFgTechnicalModel);
//					String json1 = objectMapper.writeValueAsString(json);
//					resp.put("success", "1");
//					resp.put("data", json1);
//					resp.put("error", "");
//					resp.put("message", "Record added successfully!...");
//
//					System.out.println("SmProductFgTechnical  saved succesfully!..");
//					return resp;
//				}
//			}
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgTechnical/FnAddUpdateRecord", sqlEx.getErrorCode(),sqlEx.getMessage());
//                 System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", e.getMessage());
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgTechnical/FnAddUpdateRecord", 0, e.getMessage());
//            resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//
//			return resp;
//		}
//
//		return resp;
//
//	}

	@Override
	public Object FnDeleteRecord(int product_fg_technical_id) {
		Optional<CSmProductFgTechnicalModel> option = iSmProductFgTechnicalRepository.findById(product_fg_technical_id);
		CSmProductFgTechnicalModel cSmProductFgTechnicalModel = new CSmProductFgTechnicalModel();
		if (option.isPresent()) {
			cSmProductFgTechnicalModel = option.get();
			cSmProductFgTechnicalModel.setis_delete(true);
			cSmProductFgTechnicalModel.setdeleted_on(new Date());
			iSmProductFgTechnicalRepository.save(cSmProductFgTechnicalModel);

		}
		return cSmProductFgTechnicalModel;
	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_fg_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CSmProductFgTechnicalModel cSmProductFgTechnicalModel = iSmProductFgTechnicalRepository.FnShowParticularRecordForUpdate(product_fg_id);
			String json = objectMapper.writeValueAsString(cSmProductFgTechnicalModel);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}


}
