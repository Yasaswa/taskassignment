package com.erp.FinishGoods.SmProductFgCommercial.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgCommercial.Model.CSmProductFgCommercialModel;
import com.erp.FinishGoods.SmProductFgCommercial.Repository.ISmProductFgCommercialRepository;
import com.erp.FinishGoods.SmProductFgCommercial.Repository.ISmProductFgCommercialViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Service
public class CSmProductFgCommercialServiceImpl implements ISmProductFgCommercialService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgCommercialRepository iSmProductFgCommercialRepository;

	@Autowired
	ISmProductFgCommercialViewRepository iSmProductFgCommercialViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CSmProductFgCommercialModel cSmProductFgCommercialModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CSmProductFgCommercialModel> option = iSmProductFgCommercialRepository
				.findById(cSmProductFgCommercialModel.getproduct_fg_commercial_id());
		CSmProductFgCommercialModel cSCommercialModel = null;
		int company_id = cSmProductFgCommercialModel.getcompany_id();
		try {
			if (option.isPresent()) {
				cSCommercialModel = option.get();
				cSCommercialModel.setis_delete(true);
				cSCommercialModel.setdeleted_on(new Date());
				cSCommercialModel.setmodified_on(new Date());
				iSmProductFgCommercialRepository.save(cSCommercialModel);

				cSmProductFgCommercialModel.setproduct_fg_commercial_id(0);
				CSmProductFgCommercialModel cSmProductFgCommercial = iSmProductFgCommercialRepository
						.save(cSmProductFgCommercialModel);

				String json = objectMapper.writeValueAsString(cSmProductFgCommercial);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");

				System.out.println(" Product Finish Goods Commercial Updated Successfully!..");
				return resp;
			} else {

				CSmProductFgCommercialModel json = iSmProductFgCommercialRepository.save(cSmProductFgCommercialModel);
				String json1 = objectMapper.writeValueAsString(json);
				resp.put("success", "1");
				resp.put("data", json1);
				resp.put("error", "");
				resp.put("message", "Record added successfully!...");

				System.out.println("SmProductFgCommercial  saved succesfully!..");
				return resp;

			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgCommercial/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgCommercial/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_fg_commercial_id) {
		Optional<CSmProductFgCommercialModel> option = iSmProductFgCommercialRepository
				.findById(product_fg_commercial_id);
		CSmProductFgCommercialModel cSmProductFgCommercialModel = new CSmProductFgCommercialModel();
		if (option.isPresent()) {
			cSmProductFgCommercialModel = option.get();
			cSmProductFgCommercialModel.setis_delete(true);
			cSmProductFgCommercialModel.setdeleted_on(new Date());
			iSmProductFgCommercialRepository.save(cSmProductFgCommercialModel);

		}
		return cSmProductFgCommercialModel;
	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_fg_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CSmProductFgCommercialModel cSmProductFgCommercialModel = null;
		try {
			cSmProductFgCommercialModel = iSmProductFgCommercialRepository
					.FnShowParticularRecordForUpdate(product_fg_id);
			String json = objectMapper.writeValueAsString(cSmProductFgCommercialModel);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

}
