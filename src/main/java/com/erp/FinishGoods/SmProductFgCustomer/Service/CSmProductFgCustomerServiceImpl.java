package com.erp.FinishGoods.SmProductFgCustomer.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerModel;
import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerViewModel;
import com.erp.FinishGoods.SmProductFgCustomer.Repository.ISmProductFgCustomerRepository;
import com.erp.FinishGoods.SmProductFgCustomer.Repository.ISmProductFgCustomerViewRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CSmProductFgCustomerServiceImpl implements ISmProductFgCustomerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgCustomerRepository iSmProductFgCustomerRepository;

	@Autowired
	ISmProductFgCustomerViewRepository iSmProductFgCustomerViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		CSmProductFgCustomerModel cProductFgCustomerModel = new CSmProductFgCustomerModel();
		JSONObject resp = new JSONObject();
		int company_branch_id = 0;
		int company_id = 0;
		String product_fg_id = "0";
		//	int company_id = jsonObject.getInt("Company_id");
		try {
			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
			JSONObject customerIds = (JSONObject) jsonObject.get("customerIds");

			if (!compAndBrnchId.keySet().isEmpty()) {
				for (String currentKey : compAndBrnchId.keySet()) {
					Object key = compAndBrnchId.get(currentKey);
					String value = key.toString();
					if (currentKey.equals("company_branch_id")) {
						company_branch_id = Integer.parseInt(value);
					} else if (currentKey.equals("company_id")) {
						company_id = Integer.parseInt(value);
					} else if (currentKey.equals("product_fg_id")) {
						product_fg_id = value;
					}
				}
			}

			List<CSmProductFgCustomerModel> cProductCustomerModel = null;
			cProductCustomerModel = iSmProductFgCustomerRepository.checkRecordIfExist(product_fg_id);

			if (!cProductCustomerModel.isEmpty()) {
				int product_fg_customer_id = 0;
				for (int i = 0; i < cProductCustomerModel.size(); i++) {
					product_fg_customer_id = (int) cProductCustomerModel.get(i).getproduct_fg_customer_id();
					Object obj = iSmProductFgCustomerRepository
							.updateProductFgSupplierActiveStatus(product_fg_customer_id);
				}
			}
			if (!customerIds.keySet().isEmpty()) {
				for (String currentKey : customerIds.keySet()) {
					CSmProductFgCustomerModel customerModel = new CSmProductFgCustomerModel();
					Object key = customerIds.get(currentKey);
					String customer_id = key.toString();

					customerModel.setcompany_id(company_id);
					customerModel.setcompany_branch_id(company_branch_id);
					customerModel.setcustomer_id(Integer.parseInt(customer_id));
					customerModel.setproduct_fg_id(product_fg_id);

					cProductFgCustomerModel = iSmProductFgCustomerRepository.save(customerModel);
				}

			}
			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgCustomer/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgCustomer/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}

		return resp;
	}

	@Override
	public Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iSmProductFgCustomerViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Page<CSmProductFgCustomerModel> FnShowParticularRecord(int product_fg_id, Pageable pageable) {
		return iSmProductFgCustomerRepository.FnShowParticularRecord(product_fg_id, pageable);
	}

	@Override
	public Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecordsCustomersFG(Pageable pageable, int company_id,
	                                                                             int customer_id) {
		return iSmProductFgCustomerViewRepository.FnShowAllActiveRecordsCustomersFG(pageable, company_id, customer_id);
	}

}
