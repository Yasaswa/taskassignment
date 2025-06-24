package com.erp.FinishGoods.SmProductFgSupplier.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierModel;
import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierViewModel;
import com.erp.FinishGoods.SmProductFgSupplier.Repository.ISmProductFgSupplierRepository;
import com.erp.FinishGoods.SmProductFgSupplier.Repository.ISmProductFgSupplierViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CSmProductFgSupplierServiceImpl implements ISmProductFgSupplierService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgSupplierRepository iSmProductFgSupplierRepository;

	@Autowired
	ISmProductFgSupplierViewRepository iSmProductFgSupplierViewRepository;

//	@Override
//	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
//		CSmProductFgSupplierModel cProductFgSupplierModel = new CSmProductFgSupplierModel();
//		JSONObject resp = new JSONObject();
//		int company_branch_id = 0;
//		int company_id = 0;
//		int product_fg_id = 0;
//		try {
//			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
//			JSONObject supplierIds = (JSONObject) jsonObject.get("supplierIds");
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
//			Object obj = iSmProductFgSupplierRepository
//					.updateProductFgSupplierActiveStatus(product_fg_id, company_id, company_branch_id);
//
//
//			if (!supplierIds.keySet().isEmpty()) {
//				for (String currentKey : supplierIds.keySet()) {
//					CSmProductFgSupplierModel supplierModel = new CSmProductFgSupplierModel();
//					Object key = supplierIds.get(currentKey);
//					String supplier_id = key.toString();
//
//					supplierModel.setCompany_id(company_id);
//					supplierModel.setCompany_branch_id(company_branch_id);
//					supplierModel.setSupplier_id(Integer.parseInt(supplier_id));
//					supplierModel.setProduct_fg_id(product_fg_id);
//
//					cProductFgSupplierModel = iSmProductFgSupplierRepository.save(supplierModel);
//				}
//
//			}
//			resp.put("success", "1");
//			resp.put("message", "Records added successfully !...");
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgSupplier/FnAddUpdateRecord", sqlEx.getErrorCode(),sqlEx.getMessage());
//                resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//				return resp;
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/SmProductFgSupplier/FnAddUpdateRecord", 0, e.getMessage());
//            resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//			return resp;
//
//		}
//
//		return resp;
//	}

	@Override
	public Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iSmProductFgSupplierViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Page<CSmProductFgSupplierModel> FnShowParticularRecord(int product_fg_id, Pageable pageable) {
		return iSmProductFgSupplierRepository.FnShowParticularRecord(product_fg_id, pageable);
	}


	@Override
	public Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecordsSupplierFG(Pageable pageable, int company_id,
	                                                                            int supplier_id) {
		return iSmProductFgSupplierViewRepository.FnShowAllActiveRecordsSupplierFG(pageable, company_id, supplier_id);
	}

}
