package com.erp.RawMaterial.Product_Rm_Supplier.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierViewModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Repository.IProductRmSupplierRepository;
import com.erp.RawMaterial.Product_Rm_Supplier.Repository.IProductRmSupplierViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CProductRmSupplierServiceImpl implements IProductRmSupplierService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductRmSupplierRepository iProductRmSupplierRepository;

	@Autowired
	IProductRmSupplierViewRepository iProductRmSupplierViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject resp = new JSONObject();
		JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
		JSONArray supplierIds = (JSONArray) jsonObject.get("supplierIds");
		int company_branch_id = compAndBrnchId.getInt("company_branch_id");
		int company_id = compAndBrnchId.getInt("company_id");
		String product_rm_id = compAndBrnchId.getString("product_rm_id");
		try {

			Object obj = iProductRmSupplierRepository.updateProductRMSupplierActiveStatus(product_rm_id, company_id);

			List<CProductRmSupplierModel> supplierModel = objectMapper.readValue(supplierIds.toString(),
					new TypeReference<List<CProductRmSupplierModel>>() {
					});
			iProductRmSupplierRepository.saveAll(supplierModel);

			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productrmsupplier/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmsupplier/FnAddUpdateRecord",
					0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}

		return resp;
	}

	@Override
	public List<CProductRmSupplierViewModel> FnShowAllActiveRecords(int company_id) {
		List<CProductRmSupplierViewModel> cProductRmSupplierViewModels = null;
		try {
			cProductRmSupplierViewModels = iProductRmSupplierViewRepository.FnShowAllActiveRecords(company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmSupplierViewModels;
	}

	@Override
	public List<CProductRmSupplierModel> FnShowParticularRecord(int product_rm_id, int company_id) {
		List<CProductRmSupplierModel> cProductRmSupplierModels = null;
		try {
			cProductRmSupplierModels = iProductRmSupplierRepository.FnShowParticularRecords(product_rm_id, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmSupplierModels;
	}

	@Override
	public List<CProductRmSupplierViewModel> FnShowAllActiveRecordsSuppliersRM(int company_id, int supplier_id) {
		List<CProductRmSupplierViewModel> cProductRmSupplierViewModels = null;
		try {
			cProductRmSupplierViewModels = iProductRmSupplierViewRepository
					.FnShowAllActiveRecordsSuppliersRM(company_id, supplier_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmSupplierViewModels;
	}

}
