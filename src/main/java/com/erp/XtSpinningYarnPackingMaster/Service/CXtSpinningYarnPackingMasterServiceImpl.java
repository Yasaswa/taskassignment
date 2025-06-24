package com.erp.XtSpinningYarnPackingMaster.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptMasterModel;
import com.erp.PtGoodsReceiptDetails.Repository.IPtGoodsReceiptDetailsViewRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingDetailsModel;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingDetailsViewModel;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingMasterModel;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingMasterViewModel;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingDetailsRepository;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingDetailsViewRepository;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingMasterRepository;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingMasterViewRepository;
import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomMaterialModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CXtSpinningYarnPackingMasterServiceImpl implements IXtSpinningYarnPackingMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXtSpinningYarnPackingMasterRepository iXtSpinningYarnPackingMasterRepository;
	
	@Autowired
	IXtSpinningYarnPackingDetailsRepository iXtSpinningYarnPackingDetailsRepository;

	@Autowired
	IXtSpinningYarnPackingMasterViewRepository iXtSpinningYarnPackingMasterViewRepository;
	
	@Autowired
	IXtSpinningYarnPackingDetailsViewRepository iXtSpinningYarnPackingDetailsViewRepository;

	@Autowired
	CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

	@Autowired
	IPtGoodsReceiptDetailsViewRepository iPtGoodsReceiptDetailsViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper =  new ObjectMapper();
		
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int yarn_packing_master_id = commonIdsObj.getInt("yarn_packing_master_id");

		// get master data
		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		try{
			
			CXtSpinningYarnPackingMasterModel cXtSpinningYarnPackingMasterModel = 
					objectMapper.readValue(masterjson.toString(),CXtSpinningYarnPackingMasterModel.class);
			
			//save cXtSpinningYarnPackingMasterModel
			CXtSpinningYarnPackingMasterModel respSpinningYarnPackingMasterModel  = iXtSpinningYarnPackingMasterRepository.save(cXtSpinningYarnPackingMasterModel);
			
			// JSON array into a list of CXtSpinningYarnPackingDetailsModel objects using an ObjectMapper
			List<CXtSpinningYarnPackingDetailsModel> cXtSpinningYarnPackingDetailsModels =
					objectMapper.readValue(detailsArray.toString(),new TypeReference<List<CXtSpinningYarnPackingDetailsModel>>(){} );
			
			cXtSpinningYarnPackingDetailsModels.forEach(details->{
				details.setYarn_packing_master_id(respSpinningYarnPackingMasterModel.getYarn_packing_master_id());
			});
			
			
			//update Spinning Yarn PackingDetails
	
			if (yarn_packing_master_id != 0) {
				
				//Extract Spinning Yarn Packing Id's here 
				List<Integer> distinctSpinningYarnPackingIds = cXtSpinningYarnPackingDetailsModels.parallelStream()
		                .map(CXtSpinningYarnPackingDetailsModel::getYarn_packing_details_id)
		                .distinct().collect(Collectors.toList());
				
				iXtSpinningYarnPackingDetailsRepository.updateSpinningYarnPackingDetails(distinctSpinningYarnPackingIds);

			}
			
			//save cXtSpinningYarnPackingDetailsModels
			iXtSpinningYarnPackingDetailsRepository.saveAll(cXtSpinningYarnPackingDetailsModels);

//			Add stock
			FnUpdateProductRmStockDetails(respSpinningYarnPackingMasterModel, company_id);

			responce.put("data", respSpinningYarnPackingMasterModel);
			responce.put("success", 1);
			responce.put("error", "");
			responce.put("message", yarn_packing_master_id == 0 ? "Record added successfully!..." : "Record updated successfully!...");
			
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtSpinningYarnPackingMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtSpinningYarnPackingMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
	
		}

		return responce;
	}


	private void FnUpdateProductRmStockDetails(CXtSpinningYarnPackingMasterModel responseSpinningYarnPackingMasterModel, int company_id) {
//		 Get the current date
		Date currentDate = new Date();

//		 Formatting the date to display only the date portion
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(currentDate);

//		 Retrieve the list of goods receipt details based on the master transaction id
		List<CXtSpinningYarnPackingDetailsViewModel> responseYarnPackingDetails = iXtSpinningYarnPackingDetailsViewRepository
				.getAllYarnPackingDetails(responseSpinningYarnPackingMasterModel.getYarn_packing_master_id());

		Map<String, Object> stockDetails = new HashMap<>();

//		Create list to add object to save stock details & Summary
		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
		List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();


//		Iterate on good receipt details for stock updation list creation
		responseYarnPackingDetails.forEach(object -> {
			String material_id = object.getProduct_material_id();

//			smv_product_rm_stock_summary
			CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

			productRmStockSummaryModel.setCompany_id(object.getCompany_id());
			productRmStockSummaryModel.setCompany_branch_id(object.getCompany_branch_id());
			productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
			productRmStockSummaryModel.setProduct_type_group(object.getProduct_type_group());
			productRmStockSummaryModel.setProduct_type_id(object.getProduct_type_id());
			productRmStockSummaryModel.setProduct_rm_id(material_id);
			productRmStockSummaryModel.setProduct_material_unit_id(object.getProduct_unit_id());
			productRmStockSummaryModel.setProduct_material_packing_id(object.getYarn_packing_types_id());
			productRmStockSummaryModel.setClosing_balance_quantity(object.getPacking_quantity());
			productRmStockSummaryModel.setProduction_quantity(object.getPacking_quantity());
			productRmStockSummaryModel.setClosing_balance_weight(object.getPacking_quantity() * object.getPer_packing_weight());

			productRmStockSummaryModel.setProduction_weight(object.getPacking_quantity() * object.getPer_packing_weight());
			productRmStockSummaryModel.setGodown_id(object.getGodown_id());
			productRmStockSummaryModel.setCreated_by(object.getCreated_by());
			productRmStockSummaryModel.setModified_by(object.getCreated_by());

			addProductRmStockSummaryList.add(productRmStockSummaryModel);

//			smv_product_rm_stock_details
			CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

			productRmStockDetailsModel.setCompany_id(object.getCompany_id());
			productRmStockDetailsModel.setCompany_branch_id(object.getCompany_branch_id());
			productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
			productRmStockDetailsModel.setProduct_type_group(object.getProduct_type_group());
			productRmStockDetailsModel.setProduct_type_id(object.getProduct_type_id());
			productRmStockDetailsModel.setProduct_rm_id(object.getProduct_material_id());
			productRmStockDetailsModel.setProduct_material_unit_id(object.getProduct_unit_id());
			productRmStockDetailsModel.setProduct_material_packing_id(object.getYarn_packing_types_id());
			productRmStockDetailsModel.setStock_date(todayDate);
//			productRmStockDetailsModel.setBatch_no(object.getBatch_no());
			productRmStockDetailsModel.setGoods_receipt_no(object.getLot_no());
			productRmStockDetailsModel.setCustomer_order_no(object.getCustomer_order_no());
			productRmStockDetailsModel.setProduction_quantity(object.getPacking_quantity());
			productRmStockDetailsModel.setClosing_balance_quantity(object.getPacking_quantity());
			productRmStockDetailsModel.setClosing_balance_weight(object.getPacking_quantity() * object.getPer_packing_weight());
			productRmStockDetailsModel.setGodown_id(object.getGodown_id());
			productRmStockDetailsModel.setCreated_by(object.getCreated_by());
			productRmStockDetailsModel.setModified_by(object.getCreated_by());

			addProductRmStockDetailsList.add(productRmStockDetailsModel);


//			Stock tracking details
			CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

			smProductStockTracking.setCompany_id(object.getCompany_id());
			smProductStockTracking.setCompany_branch_id(object.getCompany_branch_id());
			smProductStockTracking.setFinancial_year(object.getFinancial_year());
			smProductStockTracking.setGoods_receipt_no(object.getLot_no());
			smProductStockTracking.setProduct_material_id(material_id);
			smProductStockTracking.setStock_date(todayDate);
			smProductStockTracking.setStock_quantity(object.getPacking_quantity());
			smProductStockTracking.setProduct_material_unit_id(object.getProduct_unit_id());
			smProductStockTracking.setStock_type("Own");
			smProductStockTracking.setCreated_by(object.getCreated_by());

			addProductStockTrackingList.add(smProductStockTracking);

		});
		stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
		stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
		stockDetails.put("StockTracking", addProductStockTrackingList);

		cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Increase", company_id);

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int yarn_packing_master_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Spinning Yarn Packing Master
		    iXtSpinningYarnPackingMasterRepository.FnDeleteSpinningYarnPackingMasterRecord(yarn_packing_master_id, company_id, deleted_by);
		    
			//Delete Spinning Yarn Packing Details
		    iXtSpinningYarnPackingDetailsRepository.FnDeleteSpinningYarnPackingDetailsRecord(yarn_packing_master_id, company_id, deleted_by);
		    
			responce.put("success", 1);
		
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success",0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
            return responce;
		}
	return responce;
	}
	
	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int yarn_packing_master_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
				
			//Fetch Spinning Yarn Packing Master Record for update
			CXtSpinningYarnPackingMasterViewModel spinningYarnPackingMasterRecord  =
					iXtSpinningYarnPackingMasterViewRepository.FnShowSpinningYarnPackingMasterRecord(yarn_packing_master_id,company_id);
			
			//Fetch Spinning Yarn Packing Details Record for update
			List<CXtSpinningYarnPackingDetailsViewModel>  spinningYarnPackingDetailsRecord = 
					iXtSpinningYarnPackingDetailsViewRepository.FnShowSpinningYarnPackingDetailsRecord(yarn_packing_master_id,company_id);
			
			responce.put("success", 1);
			responce.put("spinningYarnPackingMasterRecord", spinningYarnPackingMasterRecord);
			responce.put("spinningYarnPackingDetailsRecord", spinningYarnPackingDetailsRecord);

			} catch (DataAccessException e) {
				if (e.getRootCause() instanceof SQLException) {
					SQLException sqlEx = (SQLException) e.getRootCause();
					System.out.println(sqlEx.getMessage());
					responce.put("success",0);
					responce.put("data", "");
					responce.put("error", e.getMessage());
					return responce;
				}
	
			} catch (Exception e) {
				e.printStackTrace();
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
	            return responce;
			}
			return responce;
		}

	}
