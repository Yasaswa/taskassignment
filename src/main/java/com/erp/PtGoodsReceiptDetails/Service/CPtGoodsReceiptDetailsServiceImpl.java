package com.erp.PtGoodsReceiptDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.Common.EmailUtilities.EmailServices.EmailSender;
import com.erp.Common.EmailUtilities.Model.EmailModel;
import com.erp.Common.EmailUtilities.PropertyReader.PropertiesReader;
import com.erp.PtGoodsReceiptDetails.Model.*;
import com.erp.PtGoodsReceiptDetails.Repository.*;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsViewModel;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderDetailsViewRepository;
import com.erp.RawMaterial.Product_Rm_Commercial.Repository.IProductRmCommercialRepository;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Service.CSmProductRmStockDetailsServiceImpl;
import com.erp.StIndentDetails.Model.CStIndentDetailsModel;
import com.erp.StIndentDetails.Repository.IStIndentDetailsRepository;
import com.erp.StIndentDetails.Repository.IStIndentMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CPtGoodsReceiptDetailsServiceImpl implements IPtGoodsReceiptDetailsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
	@Autowired
	IPtGoodsReceiptMasterRepository iPtGoodsReceiptMasterRepository;
	@Autowired
	IPtGoodsReceiptDetailsRepository iPtGoodsReceiptDetailsRepository;
	@Autowired
	IPtGoodsReceiptDetailsViewRepository iPtGoodsReceiptDetailsViewRepository;
	@Autowired
	IPtGoodsReceiptsNotesTaxSummaryRepository iPtGoodsReceiptsNotesTaxSummaryRepository;
	@Autowired
	IPtGoodsReceiptsNotesTaxSummaryViewRepository iPtGoodsReceiptsNotesTaxSummaryViewRepository;
	@Autowired
	IPtPurchaseOrderDetailsViewRepository iPtPurchaseOrderDetailsViewRepository;

	@Autowired
	IPtGoodsReceiptPaymentTermsRepository iPtGoodsReceiptPaymentTermsRepository;

	@Autowired
	IPtGoodsReceiptPaymentTermsViewRepository iPtGoodsReceiptPaymentTermsViewRepository;

	@Autowired
	IPtGoodsReceiptIndentDetailsViewRepository iPtGoodsReceiptIndentDetailsViewRepository;
	@Autowired
	CSmProductRmStockDetailsServiceImpl cSmProductRmStockDetailsServiceImpl;

	@Autowired
	IStIndentMasterRepository indentMasterRepository;

	@Autowired
	IStIndentDetailsRepository iStIndentDetailsRepository;
	@Autowired
	IPtGoodsReceiptIndentDetailsRepository iPtGoodsReceiptIndentDetailsRepository;

	@Autowired
	IProductRmCommercialRepository iProductRmCommercialRepository;

	@Autowired
	IDocumentsRepository iDocumentsRepository;

	Pattern emailPattern = Pattern
			.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
	@Autowired
	private JdbcTemplate executeQuery;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String goods_receipt_no = commonIdsObj.getString("goods_receipt_no");
		int goods_receipt_version = commonIdsObj.getInt("goods_receipt_version");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray notesTaxSummaryArray = (JSONArray) jsonObject.get("TransTaxSummaryData");
		JSONArray paymentTermsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
		JSONArray indentGRNDetailsArray = (JSONArray) jsonObject.get("TransIndentGRNDetailsData");

		try {
			CPtGoodsReceiptMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CPtGoodsReceiptMasterModel.class);

//			Goods Receipt Master
			if (!isApprove) {

				CPtGoodsReceiptMasterModel getExistingRecord = iPtGoodsReceiptMasterRepository.getExistingRecord(
						goods_receipt_no, jsonModel.getGoods_receipt_version(), financial_year, company_id);

				if (getExistingRecord != null) {
					update = true;
//				cptGoodsReceiptMasterModel = results.get(0);
					getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
					getExistingRecord.setDeleted_on(new Date());
					getExistingRecord.setIs_delete(true);
					iPtGoodsReceiptMasterRepository.save(getExistingRecord);
					jsonModel.setGoods_receipt_master_transaction_id(0);
					jsonModel.setGoods_receipt_version(getExistingRecord.getGoods_receipt_version() + 1);

					String GRNNo = commonIdsObj.getString("goods_receipt_no").replaceAll("/", "_");
					List<String> groupIds = Collections.singletonList(GRNNo);
					iDocumentsRepository.updateDocActive(groupIds);
				}
				CPtGoodsReceiptMasterModel responceGoodsReceiptMasterTrading = iPtGoodsReceiptMasterRepository
						.save(jsonModel);

//			Goods Receipt Details
				if (update) {
					iPtGoodsReceiptDetailsRepository.updateStatus(goods_receipt_no,
							masterjson.getInt("goods_receipt_version"), financial_year, company_id);
				}

				List<CPtGoodsReceiptDetailsModel> cPtGoodsReceiptDetailsModel = objectMapper
						.readValue(detailsArray.toString(), new TypeReference<List<CPtGoodsReceiptDetailsModel>>() {
						});

				cPtGoodsReceiptDetailsModel.forEach(items -> {
					items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
					items.setGoods_receipt_master_transaction_id(
							responceGoodsReceiptMasterTrading.getGoods_receipt_master_transaction_id());
				});

				iPtGoodsReceiptDetailsRepository.saveAll(cPtGoodsReceiptDetailsModel);

				String GRNNo = commonIdsObj.getString("goods_receipt_no").replaceAll("/", "_");
				List<String> groupIds = Collections.singletonList(GRNNo);
				iDocumentsRepository.updateDocActive(groupIds);


//			 Goods Receipts Notes Tax Summary
				iPtGoodsReceiptsNotesTaxSummaryRepository.updateStatus(goods_receipt_no,
						masterjson.getInt("goods_receipt_version"), financial_year, company_id);

				List<CPtGoodsReceiptsNotesTaxSummaryModel> cptGoodsReceiptsNotesTaxSummaryModel = objectMapper
						.readValue(notesTaxSummaryArray.toString(),
								new TypeReference<List<CPtGoodsReceiptsNotesTaxSummaryModel>>() {
								});
				cptGoodsReceiptsNotesTaxSummaryModel.forEach(items -> {
					items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
					items.setGoods_receipt_master_transaction_id(
							responceGoodsReceiptMasterTrading.getGoods_receipt_master_transaction_id());
				});

				iPtGoodsReceiptsNotesTaxSummaryRepository.saveAll(cptGoodsReceiptsNotesTaxSummaryModel);

//   			 Goods Receipts Payment Terms
				iPtGoodsReceiptPaymentTermsRepository.updateStatus(goods_receipt_no,
						masterjson.getInt("goods_receipt_version"), financial_year, company_id);
				List<CPtGoodsReceiptPaymentTermsModel> cptGoodsReceiptsPaymentTermsModel = objectMapper.readValue(
						paymentTermsArray.toString(), new TypeReference<List<CPtGoodsReceiptPaymentTermsModel>>() {
						});
				cptGoodsReceiptsPaymentTermsModel.forEach(items -> {
					items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
					items.setGoods_receipt_master_transaction_id(
							responceGoodsReceiptMasterTrading.getGoods_receipt_master_transaction_id());
				});
				iPtGoodsReceiptPaymentTermsRepository.saveAll(cptGoodsReceiptsPaymentTermsModel);

				if (!indentGRNDetailsArray.isEmpty()) {
					List<CptGoodsReceiptIndentDetailsModel> cptGoodsReceiptIndentDetailsModel = objectMapper.readValue(
							indentGRNDetailsArray.toString(), new TypeReference<List<CptGoodsReceiptIndentDetailsModel>>() {
							});
					cptGoodsReceiptIndentDetailsModel.forEach(items -> {
						items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
						items.setGoods_receipt_master_transaction_id(
								responceGoodsReceiptMasterTrading.getGoods_receipt_master_transaction_id());
					});
					List<CptGoodsReceiptIndentDetailsModel> indentDetailsList = iPtGoodsReceiptIndentDetailsRepository.saveAll(cptGoodsReceiptIndentDetailsModel);
					FnUpdateIndentStatus(indentDetailsList, company_id);
				}
//				update purchase order master & details status
				FnUpdatePurchaseOrderStatus(responceGoodsReceiptMasterTrading,company_id, false);
				responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");
				responce.put("data", responceGoodsReceiptMasterTrading);
			} else {

				String GRNNo = commonIdsObj.getString("goods_receipt_no").replaceAll("/", "_");
				List<String> groupIds = Collections.singletonList(GRNNo);
				iDocumentsRepository.updateDocActive(groupIds);

				CPtGoodsReceiptMasterModel responceGoodsReceiptMasterTrading = iPtGoodsReceiptMasterRepository
						.save(jsonModel);

				List<CPtGoodsReceiptDetailsModel> cPtGoodsReceiptDetailsModel = objectMapper
						.readValue(detailsArray.toString(), new TypeReference<List<CPtGoodsReceiptDetailsModel>>() {
						});
				iPtGoodsReceiptDetailsRepository.saveAll(cPtGoodsReceiptDetailsModel);

				List<CPtGoodsReceiptsNotesTaxSummaryModel> cptGoodsReceiptsNotesTaxSummaryModel = objectMapper
						.readValue(notesTaxSummaryArray.toString(),
								new TypeReference<List<CPtGoodsReceiptsNotesTaxSummaryModel>>() {
								});
				iPtGoodsReceiptsNotesTaxSummaryRepository.saveAll(cptGoodsReceiptsNotesTaxSummaryModel);

				if (!indentGRNDetailsArray.isEmpty()) {
					List<CptGoodsReceiptIndentDetailsModel> cptGoodsReceiptIndentDetailsModel = objectMapper.readValue(
							indentGRNDetailsArray.toString(), new TypeReference<List<CptGoodsReceiptIndentDetailsModel>>() {
							});

					List<CptGoodsReceiptIndentDetailsModel> indentDetailsList = iPtGoodsReceiptIndentDetailsRepository.saveAll(cptGoodsReceiptIndentDetailsModel);
					FnUpdateIndentStatus(indentDetailsList, company_id);
				}
				FnUpdatePurchaseOrderStatus(responceGoodsReceiptMasterTrading,company_id, true);

////				 Update stock after approve Good received note
//				String status = responceGoodsReceiptMasterTrading.getGoods_receipt_status();
//				if (status != null && !status.equalsIgnoreCase("X") && !status.equalsIgnoreCase("R")) {
//					FnUpdateProductRmStockDetails(responceGoodsReceiptMasterTrading, company_id);
//
//					// Define a date formatter matching your date string format
//					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
//					// Get today's date
//					LocalDate todayDate = LocalDate.now();
//					// Parse the goods receipt date as LocalDate
//					LocalDate grnDate = LocalDate.parse(responceGoodsReceiptMasterTrading.getGoods_receipt_date(), dateFormatter);
//
//					if (grnDate.isBefore(todayDate)) {
//						String storedProcedure = "{call Insert_reschedule_stock(?,?,?)}";
//						executeQuery.execute((Connection con) -> {
//							try (CallableStatement cs = con.prepareCall(storedProcedure)) {
//								for (CPtGoodsReceiptDetailsModel cPtGRNItem : cPtGoodsReceiptDetailsModel) {
//									cs.setString(1, cPtGRNItem.getGoods_receipt_no());
//									cs.setString(2, cPtGRNItem.getGoods_receipt_date());
//									cs.setString(3, cPtGRNItem.getProduct_material_id());
//									cs.addBatch(); // Add the statement to the batch
//								}
//								cs.executeBatch(); // Execute the batch
//							}
////                            con.close();
//							return null;
//						});
//					}
//				}

				responce.put("message",
						responceGoodsReceiptMasterTrading.getGoods_receipt_status().equals("R")
								? "Record rejected successfully!..."
								: "Record approved successfully!...");
				responce.put("data", responceGoodsReceiptMasterTrading);
			}

			responce.put("success", 1);
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PtGoodsReceiptDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtGoodsReceiptDetails/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}


	private void FnUpdateIndentStatus(List<CptGoodsReceiptIndentDetailsModel> indentGRNDetailsArray, int company_id) {
		List<String> inCompleteMaterialIndents = new ArrayList<>();
		List<String> completeMaterialIndents = new ArrayList<>();

		// Get distinct Indent order numbers
		List<String> distinctIndentNumbers = indentGRNDetailsArray.stream()
				.map(CptGoodsReceiptIndentDetailsModel::getIndent_no) // Get the concatenated indent numbers
				.distinct() // Get distinct values
				.collect(Collectors.toList()); // Collect to list

		// update grn accept qaunatity and status in indent details
		List<CStIndentDetailsModel> indentDetailsList = iStIndentDetailsRepository.getIndentDetails(distinctIndentNumbers, company_id);

//      Add list of indent object to save
		List<CStIndentDetailsModel> addIndentDetails = new ArrayList<>();

		indentDetailsList.forEach(indentMaterialItem -> {
			Optional<CptGoodsReceiptIndentDetailsModel> matchingGRNObj = indentGRNDetailsArray.stream()
					.filter(item -> item.getIndent_details_id().equals(indentMaterialItem.getIndent_details_id()))
					.findFirst();

			if (matchingGRNObj.isPresent()) {
				CptGoodsReceiptIndentDetailsModel grnItem = matchingGRNObj.get();
				double total_indent_grn = indentMaterialItem.getProduct_material_grn_accepted_quantity() + grnItem.getProduct_material_grn_accepted_quantity();
				double materialRemainingQty = grnItem.getProduct_material_indent_approved_quantity() - total_indent_grn;
				indentMaterialItem.setProduct_material_grn_accepted_quantity(indentMaterialItem.getProduct_material_grn_accepted_quantity() + grnItem.getProduct_material_grn_accepted_quantity());
				indentMaterialItem.setProduct_material_grn_accepted_weight(indentMaterialItem.getProduct_material_grn_accepted_weight() + grnItem.getProduct_material_grn_accepted_weight());
				if (materialRemainingQty == 0 || materialRemainingQty < 0) {
					indentMaterialItem.setGrn_item_status("G");
				} else if (materialRemainingQty > 0) {
					indentMaterialItem.setGrn_item_status("IG");
					inCompleteMaterialIndents.add(indentMaterialItem.getIndent_no());
				}
//                iStIndentDetailsRepository.save(indentMaterialItem);
				addIndentDetails.add(indentMaterialItem);

				// Update Indent Master status
				distinctIndentNumbers.forEach(indentNumber -> {
					if (inCompleteMaterialIndents.contains(indentNumber)) {
//						iPtGoodsReceiptDetailsRepository.FnUpdateIndentStatus("IG", indentNumber);    // Update status to "IG" for incomplete material Indents
					} else {
						completeMaterialIndents.add(indentNumber);
//						iPtGoodsReceiptDetailsRepository.FnUpdateIndentStatus("G", indentNumber);
					}
				});
			}
		});

		iStIndentDetailsRepository.saveAll(addIndentDetails);  //Save All Indent's

		if (!inCompleteMaterialIndents.isEmpty())
			iPtGoodsReceiptDetailsRepository.FnUpdateIndentStatus("IG", inCompleteMaterialIndents);    // Update status to "IG" for incomplete material Indents

		if (!completeMaterialIndents.isEmpty())
			iPtGoodsReceiptDetailsRepository.FnUpdateIndentStatus("G", completeMaterialIndents);       // Update status to "G" for complete material Indents
	}

	private void FnUpdatePurchaseOrderStatus(CPtGoodsReceiptMasterModel responseGoodsReceiptMasterTrading, int company_id, boolean isGRNApprove) {
		List<String> inCompleteMaterialPOs = new ArrayList<>();
		String poNumber =responseGoodsReceiptMasterTrading.getPurchase_order_no();
		// Retrieve the list of goods receipt details based on the master transaction id
		List<CPtGoodsReceiptDetailsViewModel> responseGoodReceiptDetails = iPtGoodsReceiptDetailsViewRepository
				.getAllDetails(responseGoodsReceiptMasterTrading.getGoods_receipt_master_transaction_id());

		// Retrieve the list of goods receipt details based on the master transaction id
		List<CPtPurchaseOrderDetailsViewModel> responsePurchaseDetails = iPtPurchaseOrderDetailsViewRepository
				.FngetExistingPurchaseOrderDetails(poNumber, company_id);

		// Retrieve the previous list of goods receipt details based on PO nos
		List<CPtGoodsReceiptDetailsViewModel> existingGoodReceiptDetails = iPtGoodsReceiptDetailsViewRepository
				.getAllExistingGRNPODetails(poNumber, company_id);

		// Create a set of distinct composite keys from the goods receipt details where the condition is met
		Set<String> distinctKeys = existingGoodReceiptDetails.stream()
				.map(goodReceipt -> goodReceipt.getProduct_material_id() + ":" + goodReceipt.getPurchase_order_no())
				.collect(Collectors.toSet());

		// Create a map with the distinct composite keys
		Map<String, Boolean> goodsReceiptDetailsMap = distinctKeys.stream()
				.collect(Collectors.toMap(
						key -> key,
						key -> true
				));

		// Filter purchase order details to only include those not in the goods receipt details map
		List<CPtPurchaseOrderDetailsViewModel> filteredPurchaseDetails = responsePurchaseDetails.stream()
				.filter(purchaseOrder -> !goodsReceiptDetailsMap.containsKey(purchaseOrder.getProduct_material_id() + ":" + purchaseOrder.getPurchase_order_no()))
				.collect(Collectors.toList());

		// Get distinct Indent order numbers
		List<String> distinctIndentNumbers = responseGoodReceiptDetails.stream()
				.map(CPtGoodsReceiptDetailsViewModel::getIndent_no) // Get the concatenated indent numbers
				.filter(Objects::nonNull) // Filter out null values
				.flatMap(indentNo -> Arrays.stream(indentNo.split(":")))
				.filter(indentNo -> !indentNo.isEmpty())
				.distinct()
				.collect(Collectors.toList());
		if (responseGoodsReceiptMasterTrading.isIs_preeclosed()) {
			System.out.println("Material Item Status: (Z-PreeClosed)");

			responseGoodReceiptDetails.forEach(material -> {
				iPtGoodsReceiptDetailsRepository.FnUpdatePoMaterialStatusAndQty("Z",
						material.getPree_closed_grn_quantity(), material.getPree_closed_grn_weight(),
						material.getPurchase_order_details_transaction_id());
			});

				System.out.println("Material Item Status: (Z-PreeClosed) for PO: " + poNumber);
				iPtGoodsReceiptDetailsRepository.FnUpdateAllPoMaterialStatus("Z", poNumber);
				iPtGoodsReceiptDetailsRepository.FnUpdatePoStatus("Z", poNumber);

		} else {
			// Update material status for each item
			responseGoodReceiptDetails.forEach(item -> {

				// Calculate the remaining quantity for the current item
				double materialRemainingQty = item.getProduct_material_po_approved_quantity() - item.getPrev_grn_quantity();
				double materialRemainingWt = item.getProduct_material_po_approved_weight() - item.getPrev_grn_weight();
				if (materialRemainingQty == 0 || materialRemainingQty < 0) {
					System.out.println("Material Item Status: (C-Completed): " + materialRemainingQty);
					iPtGoodsReceiptDetailsRepository.FnUpdatePoMaterialStatus("C", materialRemainingQty,
							materialRemainingWt, item.getPurchase_order_details_transaction_id());
				} else if (materialRemainingQty > 0) {
					inCompleteMaterialPOs.add(item.getPurchase_order_no());
					System.out.println("Material Item Status: (I-Partial GRN): " + materialRemainingQty);
					iPtGoodsReceiptDetailsRepository.FnUpdatePoMaterialStatus("I", materialRemainingQty,
							materialRemainingWt, item.getPurchase_order_details_transaction_id());
				}
			});

				if (inCompleteMaterialPOs.contains(poNumber) || !filteredPurchaseDetails.isEmpty()) {
					// Update status to "I" for incomplete material POs
					iPtGoodsReceiptDetailsRepository.FnUpdatePoStatus("I", poNumber);
				} else {
					iPtGoodsReceiptDetailsRepository.FnUpdatePoStatus("C", poNumber);
				}

		}
		if(isGRNApprove) { // Update stock after approve Good received note
			String status = responseGoodsReceiptMasterTrading.getGoods_receipt_status();
			if (status != null && !status.equalsIgnoreCase("X") && !status.equalsIgnoreCase("R")) {
				FnUpdateProductRmStockDetails(responseGoodReceiptDetails, company_id, responseGoodsReceiptMasterTrading);

				// Define a date formatter matching your date string format
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
				// Get today's date
				LocalDate todayDate = LocalDate.now();
				LocalDate grnDate = LocalDate.parse(responseGoodsReceiptMasterTrading.getGoods_receipt_date(), dateFormatter);
				if (grnDate.isBefore(todayDate)) {
					String storedProcedure = "{call Insert_reschedule_stock(?,?,?)}";
					executeQuery.execute((Connection con) -> {
						try (CallableStatement cs = con.prepareCall(storedProcedure)) {
							for (CPtGoodsReceiptDetailsViewModel cPtGRNItem : responseGoodReceiptDetails) {
								cs.setString(1, cPtGRNItem.getGoods_receipt_no());
								cs.setString(2, cPtGRNItem.getGoods_receipt_date());
								cs.setString(3, cPtGRNItem.getProduct_material_id());
								cs.addBatch(); // Add the statement to the batch
							}
							cs.executeBatch(); // Execute the batch
						}
						return null;
					});
				}
			}
		}
		if(!distinctIndentNumbers.isEmpty()) {
			FnSendNotification(distinctIndentNumbers, responseGoodReceiptDetails, company_id, isGRNApprove);
		}
	}

	private void FnUpdateProductRmStockDetails(List<CPtGoodsReceiptDetailsViewModel> responseGoodReceiptDetails,
											   int company_id, CPtGoodsReceiptMasterModel responseGoodsReceiptMasterTrading) {

		Map<String, Object> stockDetails = new HashMap<>();
//		Create list to add object to save stock details & Summary
		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
		List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();

//		Iterate on good receipt details for stock updation list creation
		responseGoodReceiptDetails.forEach(object -> {
			String material_id = object.getProduct_material_id();

			Map<String , Object> responce = getTotalsOfStockBatches(material_id, object.getCompany_id());
			Double averageRate = object.getMaterial_rate();
			if(!responce.get("closing_balance_quantity").equals(0)){
				Double currentGRNMaterialAmt = object.getProduct_material_grn_accepted_quantity() * object.getMaterial_rate();
				BigDecimal closingTotalMaterialValue = (BigDecimal) responce.get("closing_total_material_value");
				BigDecimal closingBalanceQuantity = (BigDecimal) responce.get("closing_balance_quantity");

				Double existingTotalAmount = closingTotalMaterialValue.doubleValue();
				Double existingTotalQuantity = closingBalanceQuantity.doubleValue();
				averageRate = (existingTotalAmount + currentGRNMaterialAmt) / (existingTotalQuantity + object.getProduct_material_grn_accepted_quantity());
			}
			iProductRmCommercialRepository.updateMaasterRateByAdjustment(material_id, averageRate);// update in material master

//			smv_product_rm_stock_summary
			CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

			productRmStockSummaryModel.setCompany_id(object.getCompany_id());
			productRmStockSummaryModel.setCompany_branch_id(object.getCompany_branch_id());
			productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
			productRmStockSummaryModel.setProduct_type_group(object.getProduct_material_type_group());
			productRmStockSummaryModel.setProduct_type_id(object.getProduct_type_id());
			productRmStockSummaryModel.setProduct_rm_id(material_id);
			productRmStockSummaryModel.setProduct_material_unit_id(object.getProduct_material_unit_id());
			productRmStockSummaryModel.setProduct_material_packing_id(object.getProduct_material_packing_id());
			productRmStockSummaryModel.setOrder_quantity(object.getProduct_material_po_approved_quantity());
			productRmStockSummaryModel.setOrder_weight(object.getProduct_material_po_approved_weight());
			productRmStockSummaryModel.setExcess_quantity(object.getExcess_quantity());
			productRmStockSummaryModel.setExcess_weight(object.getExcess_weight());
			if (!object.getIndent_no().equalsIgnoreCase("")) {
				productRmStockSummaryModel.setReserve_quantity(object.getTotal_quantity_in_box());
				productRmStockSummaryModel.setReserve_weight(object.getTotal_box_weight());
				productRmStockSummaryModel.setReserve_no_of_boxes(object.getNo_of_boxes());
			}
			productRmStockSummaryModel.setPurchase_quantity(object.getTotal_quantity_in_box());
			productRmStockSummaryModel.setPurchase_weight(object.getTotal_box_weight());
			productRmStockSummaryModel.setClosing_balance_quantity(object.getTotal_quantity_in_box());
			productRmStockSummaryModel.setClosing_balance_weight(object.getTotal_box_weight());
			productRmStockSummaryModel.setTotal_box_weight(object.getTotal_box_weight());
			productRmStockSummaryModel.setTotal_quantity_in_box(object.getTotal_quantity_in_box());
			productRmStockSummaryModel.setWeight_per_box_item(object.getWeight_per_box_item());
			productRmStockSummaryModel.setGodown_id(object.getGodown_id());
			productRmStockSummaryModel.setGodown_section_id(object.getGodown_section_id());
			productRmStockSummaryModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
			productRmStockSummaryModel.setCreated_by(object.getCreated_by());
			productRmStockSummaryModel.setModified_by(object.getCreated_by());
			productRmStockSummaryModel.setPurchase_no_of_boxes(object.getNo_of_boxes());
			productRmStockSummaryModel.setClosing_no_of_boxes(object.getNo_of_boxes());
			productRmStockSummaryModel.setMaterial_rate(averageRate);

//			If this grn is preeclsosed
			if (responseGoodsReceiptMasterTrading.isIs_preeclosed()) {
				productRmStockSummaryModel.setPree_closed_quantity(object.getPree_closed_grn_quantity());
				productRmStockSummaryModel.setPree_closed_weight(object.getPree_closed_grn_weight());
			}
			addProductRmStockSummaryList.add(productRmStockSummaryModel);

			CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

			productRmStockDetailsModel.setCompany_id(object.getCompany_id());
			productRmStockDetailsModel.setCompany_branch_id(object.getCompany_branch_id());
			productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
			productRmStockDetailsModel.setProduct_type_group(object.getProduct_material_type_group());
			productRmStockDetailsModel.setProduct_type_id(object.getProduct_type_id());
			productRmStockDetailsModel.setProduct_rm_id(object.getProduct_material_id());
			productRmStockDetailsModel.setSupplier_id(object.getSupplier_id());
			productRmStockDetailsModel.setProduct_material_unit_id(object.getProduct_material_unit_id());
			productRmStockDetailsModel.setProduct_material_packing_id(object.getProduct_material_packing_id());
			productRmStockDetailsModel.setStock_date(object.getGoods_receipt_date());
			productRmStockDetailsModel.setBatch_no(object.getBatch_no());
			productRmStockDetailsModel.setBatch_expiry_date(object.getExpiry_date());
			productRmStockDetailsModel.setGoods_receipt_no(object.getGoods_receipt_no());
			productRmStockDetailsModel.setCustomer_order_no(object.getCustomer_order_no());
			productRmStockDetailsModel.setOrder_quantity(object.getProduct_material_po_approved_quantity());
			productRmStockDetailsModel.setOrder_weight(object.getProduct_material_po_approved_weight());
			productRmStockDetailsModel.setExcess_quantity(object.getExcess_quantity());
			productRmStockDetailsModel.setExcess_weight(object.getExcess_weight());
			if (!object.getIndent_no().equalsIgnoreCase("")) {
				productRmStockDetailsModel.setReserve_quantity(object.getTotal_quantity_in_box());
				productRmStockDetailsModel.setReserve_weight(object.getTotal_box_weight());
				productRmStockDetailsModel.setSales_order_no(object.getSales_order_no());
				productRmStockDetailsModel.setReserve_no_of_boxes(object.getNo_of_boxes());
			}
			productRmStockDetailsModel.setPurchase_quantity(object.getTotal_quantity_in_box());
			productRmStockDetailsModel.setPurchase_weight(object.getTotal_box_weight());
			productRmStockDetailsModel.setClosing_balance_quantity(object.getTotal_quantity_in_box());
			productRmStockDetailsModel.setClosing_balance_weight(object.getTotal_box_weight());
			productRmStockDetailsModel.setGodown_id(object.getGodown_id());
			productRmStockDetailsModel.setGodown_section_id(object.getGodown_section_id());
			productRmStockDetailsModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
			productRmStockDetailsModel.setCreated_by(object.getCreated_by());
			productRmStockDetailsModel.setModified_by(object.getCreated_by());
			productRmStockDetailsModel.setTotal_box_weight(object.getTotal_box_weight());
			productRmStockDetailsModel.setTotal_quantity_in_box(object.getTotal_quantity_in_box());
			productRmStockDetailsModel.setWeight_per_box_item(object.getWeight_per_box_item());
			productRmStockDetailsModel.setBatch_rate(object.getMaterial_rate());

			productRmStockDetailsModel.setPurchase_no_of_boxes(object.getNo_of_boxes());
			productRmStockDetailsModel.setClosing_no_of_boxes(object.getNo_of_boxes());

//          If grn is pree-closed
			if (responseGoodsReceiptMasterTrading.isIs_preeclosed()) {
				productRmStockDetailsModel.setPree_closed_quantity(object.getPree_closed_grn_quantity());
				productRmStockDetailsModel.setPree_closed_weight(object.getPree_closed_grn_weight());
			}
			addProductRmStockDetailsList.add(productRmStockDetailsModel);

//			Stock tracking details
			CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

			smProductStockTracking.setCompany_id(object.getCompany_id());
			smProductStockTracking.setCompany_branch_id(object.getCompany_branch_id());
			smProductStockTracking.setFinancial_year(object.getFinancial_year());
			smProductStockTracking.setGoods_receipt_no(object.getGoods_receipt_no());
			smProductStockTracking.setProduct_material_id(material_id);
			smProductStockTracking.setStock_date(object.getGoods_receipt_date());
			smProductStockTracking.setStock_quantity(object.getTotal_quantity_in_box());
			smProductStockTracking.setProduct_material_unit_id(object.getProduct_material_unit_id());
			smProductStockTracking.setStock_type("Own");
			smProductStockTracking.setCreated_by(object.getCreated_by());

			addProductStockTrackingList.add(smProductStockTracking);

		});
		stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
		stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
		stockDetails.put("StockTracking", addProductStockTrackingList);
		cSmProductRmStockDetailsServiceImpl.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int goods_receipt_master_transaction_id, String UserName) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iPtGoodsReceiptMasterRepository.deleteGoodsReceiptMaster(goods_receipt_master_transaction_id, UserName);
			iPtGoodsReceiptDetailsRepository.deleteGoodsReceiptDetails(goods_receipt_master_transaction_id, UserName);
			iPtGoodsReceiptsNotesTaxSummaryRepository
					.deleteGoodsReceiptsNotesTaxSummary(goods_receipt_master_transaction_id, UserName);
			iPtGoodsReceiptPaymentTermsRepository.deletePaymentTerms(goods_receipt_master_transaction_id, UserName);
			iPtGoodsReceiptIndentDetailsRepository.deleteIndentGRNDetails(goods_receipt_master_transaction_id, UserName);
			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", "");
		}
		return responce;
	}

	@Override
	public Page<CPtGoodsReceiptDetailsViewModel> FnShowParticularRecord(int goods_receipt_details_transaction_id,
	                                                                    Pageable pageable, int company_id) {
		return iPtGoodsReceiptDetailsViewRepository.FnShowParticularRecord(goods_receipt_details_transaction_id,
				pageable, company_id);

	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goods_receipt_no, int goods_receipt_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> goodsReceiptMasterRecords = iPtGoodsReceiptMasterRepository
					.FnShowGoodsReceiptMasterRecord(goods_receipt_no, goods_receipt_version, financial_year,
							company_id);

			List<Map<String, Object>> goodsReceiptDetailsRecords = iPtGoodsReceiptDetailsRepository
					.FnShowGoodsReceiptDetailsRecords(goods_receipt_no, goods_receipt_version, financial_year,
							company_id);

			List<CPtGoodsReceiptsNotesTaxSummaryViewModel> goodsReceiptsNotesTaxSummaryRecords = iPtGoodsReceiptsNotesTaxSummaryViewRepository
					.FnShowGoodsReceiptNotesTaxSummaryRecords(goods_receipt_no, goods_receipt_version, financial_year,
							company_id);

			List<CPtGoodsReceiptPaymentTermsViewModel> goodsReceiptsNotesPaymentTermsRecords = iPtGoodsReceiptPaymentTermsViewRepository
					.FnShowGoodsReceiptNotesPaymentTermsRecords(goods_receipt_no, goods_receipt_version, financial_year,
							company_id);
			List<CptGoodsReceiptIndentDetailsViewModel> goodsReceiptsNotesIndentDetailsRecords = iPtGoodsReceiptIndentDetailsViewRepository.FnShowGoodsReceiptNoteIndentDetailsRecords(goods_receipt_no, goods_receipt_version, financial_year,
					company_id);

			responce.put("GoodsReceiptMasterRecord", goodsReceiptMasterRecords);
			responce.put("GoodsReceiptDetailsRecords", goodsReceiptDetailsRecords);
			responce.put("GoodsReceiptNotesTaxSummaryRecords", goodsReceiptsNotesTaxSummaryRecords);
			responce.put("GoodsReceiptNotesPaymentTermsRecords", goodsReceiptsNotesPaymentTermsRecords);
			responce.put("GoodsReceiptsNotesIndentDetailsRecords", goodsReceiptsNotesIndentDetailsRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowPurchaseOrderDetailsTradingPurchaseRecord(JSONObject purchaseOrderNo,
	                                                                           Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			JSONArray purchaseOrderNos = purchaseOrderNo.getJSONArray("purchase_order_nos");
			List<Object> queryParams = new ArrayList<>();
			String view = "ptv_purchase_order_details";

			StringBuilder query = new StringBuilder("SELECT * FROM ").append(view).append(" WHERE purchase_order_no IN")
					.append("(");

			// Append placeholders for the customer_order_no values
			for (int count = 0; count < purchaseOrderNos.length(); count++) {
				query.append("?");
				queryParams.add(purchaseOrderNos.getString(count)); // Assuming purchaseOrderNo is an array of strings
				if (count < purchaseOrderNos.length() - 1) {
					query.append(", ");
				}
			}

			query.append(")");

			List<CPtPurchaseOrderDetailsViewModel> fetchdata = executeQuery.query(query.toString(),
					queryParams.toArray(), new BeanPropertyRowMapper<>(CPtPurchaseOrderDetailsViewModel.class));

			resp.put("success", "1");
			resp.put("data", fetchdata);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM ptv_goods_receipt_summary_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query);
			response.put("content", results);
		} else {
			query = "SELECT * FROM ptv_goods_receipt_details_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query);
			response.put("content", results);
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowPurchaseOrderDetailsRecords(JSONObject purchaseOrderNo) {
		Map<String, Object> response = new HashMap<>();
		try {
			JSONObject jsonObject = purchaseOrderNo.getJSONObject("Ids");
			int company_id = jsonObject.getInt("company_id");
			int expected_branch_id = jsonObject.getInt("expected_branch_id");

			JSONArray purchaseOrderNos = purchaseOrderNo.getJSONArray("purchase_order_nos");
			List<Map<String, Object>> fetchdata = new ArrayList<>();
			List<Map<String, Object>> paymentData = new ArrayList<>();
			if (!purchaseOrderNos.isEmpty()) {
				List<Object> queryParams = new ArrayList<>();
				String view = "ptv_purchase_order_details";

				StringBuilder query = new StringBuilder("SELECT * FROM ")
						.append(view)
						.append(" WHERE is_delete = 0 and purchase_order_item_status = 'A' ")
						.append(" and grn_item_status IN ('P' , 'I') ")
						.append("and company_id = ? and expected_branch_id = ? ").append(" and purchase_order_no IN ")
						.append("(");
				queryParams.add(company_id);
				queryParams.add(expected_branch_id);

				// Append placeholders for the purchase_order_no values
				for (int count = 0; count < purchaseOrderNos.length(); count++) {
					query.append("?");
					queryParams.add(purchaseOrderNos.getString(count));
					if (count < purchaseOrderNos.length() - 1) {
						query.append(", ");
					}
				}

				query.append(")");

				System.out.println("FnShowPurchaseOrderDetailsRecords: " + query);
				fetchdata = executeQuery.queryForList(query.toString(), queryParams.toArray());

//          Clear the StringBuilder and List<Object>
				query.setLength(0);
				queryParams.clear();

				String paymentView = "ptv_purchase_order_payment_terms";
				query = new StringBuilder("SELECT * FROM ")
						.append(paymentView)
						.append(" WHERE is_delete = 0 ")
						.append("and company_id = ? and company_branch_id = ? ").append(" and purchase_order_no IN ")
						.append("(");
				queryParams.add(company_id);
				queryParams.add(expected_branch_id);

				// Append placeholders for the purchase_order_no values
				for (int count = 0; count < purchaseOrderNos.length(); count++) {
					query.append("?");
					queryParams.add(purchaseOrderNos.getString(count));
					if (count < purchaseOrderNos.length() - 1) {
						query.append(", ");
					}
				}
				query.append(")");
				paymentData = executeQuery.queryForList(query.toString(), queryParams.toArray());
				response.put("data", fetchdata);
				response.put("purchasePaymentTerms", paymentData);
			} else {
				response.put("data", fetchdata);
				response.put("purchasePaymentTerms", paymentData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private void FnSendNotification(List<String> distinctIndentNumbers,
	                                List<CPtGoodsReceiptDetailsViewModel> responseGoodReceiptDetails, int company_id, boolean isGRNApprove) {
		Map<String, Object> response = new HashMap<>();
		try {
			PropertiesReader propertiesReader = new PropertiesReader();
			// Get the email settings.
			Map<String, Object> emailDetails = propertiesReader.getEmailDetailsByCompanyId(company_id);
			if (emailDetails != null) {
				// set the company email properties.
				String fromEmail = (String) emailDetails.get("from_email_id");
				String host = (String) emailDetails.get("smtp_host_name");
				String smtpUserName = (String) emailDetails.get("from_email_username");
				String smtpPassword = (String) emailDetails.get("from_email_password");
				Integer port = (Integer) emailDetails.get("smtp_port");

				String msgBody = "";
				Map<String, Object> foundedTemplate = propertiesReader.getEmailTemplateByTemplateName(
//						company_id,
						"GRN Received Notification For Indent");
				msgBody = (String) foundedTemplate.get("communications_templates_description");
				String modifiedMessage = msgBody.replace("\\n", "<br><br>");
				AtomicReference<String> modifiedMsg = new AtomicReference<String>(modifiedMessage);
				String alias = "store@pashupaticotspin.com";

				// set the data in emailModel.
				EmailModel email = new EmailModel();
				email.setMailfrom(alias + " <"+fromEmail+">");

//				// Send Email notifications.   FnGetIndentMstByIndentNos          
				List<Map<String, Object>> indentMsts = indentMasterRepository
						.FnGetIndentMstByIndentNos(distinctIndentNumbers, company_id);
				List<String> emailFields = Arrays.asList("indented_by_email", "approved_by_email"); // List of email
				// fields
				List<String> nameFields = Arrays.asList("indented_by_name", "approved_by_name"); // List of email fields
				String supplierName = responseGoodReceiptDetails.get(0).getSupplier_name();
				String bestRegards = responseGoodReceiptDetails.get(0).getCompany_name();
				String goods_receipt_no = responseGoodReceiptDetails.get(0).getGoods_receipt_no();

				indentMsts.forEach(indentMst -> {
					String indentNo = (String) indentMst.get("indent_no");
					Date indentDate = (Date) indentMst.get("indent_date");
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String indentDateStr = dateFormat.format(indentDate);

					// Collect the emails.
					for (int i = 0; i < emailFields.size(); i++) {
						String emailId = (String) indentMst.get(emailFields.get(i));
						String name = (String) indentMst.get(nameFields.get(i));
						// Validate email
						Matcher matcher = emailPattern.matcher(emailId);
						if (matcher.matches()) {
							email.setMailto(new ArrayList<>(Arrays.asList(emailId)));
							email.setBcc(new ArrayList<String>());
							email.setCc(new ArrayList<String>());
							email.setMailSubject("Goods Received - Indent No.: " + indentNo);
							String[] templateParams = {"Goods Received", name, indentNo, indentDateStr, goods_receipt_no};
							modifiedMsg.set(replaceParameters(modifiedMessage, templateParams));

							AtomicInteger srNo = new AtomicInteger(1);
							StringBuilder htmlTable = new StringBuilder();
							htmlTable.append(
									"<table style='border-collapse: collapse; table-layout: fixed; width: 100%; margin-top: 20px; padding: 10px;'>");
							htmlTable.append("<thead><tr style='font-size: 13px;'>");
							htmlTable.append(
									"<th style='border: 1px solid black; padding: 5px; width: 25px; word-wrap: break-word;'>Sr.</br>No.</th>");
							htmlTable.append(
									"<th style='border: 1px solid black; padding: 5px; word-wrap: break-word;'>Material Name</th>");
//				        	    htmlTable.append("<th style='border: 1px solid black; padding: 5px; width: 40px; word-wrap: break-word;'>GRN </br> Qty.</th>");
							if (!isGRNApprove) {
								htmlTable.append(
										"<th style='border: 1px solid black; padding: 5px; width: 55px; word-wrap: break-word;'>GRN</br> Qty. </th>");
							} else {
								htmlTable.append(
										"<th style='border: 1px solid black; padding: 5px; width: 55px; word-wrap: break-word;'>Accepted</br> Qty. </th>");
								htmlTable.append(
										"<th style='border: 1px solid black; padding: 5px; width: 65px; word-wrap: break-word;'>Rejected</br> Qty. </th>");
							}
							htmlTable.append("</tr></thead>");
							htmlTable.append("<tbody>");

							responseGoodReceiptDetails.stream().forEach((detail) -> {
								htmlTable.append("<tr style='font-size: 13px;'>");
								htmlTable.append(
												"<td style='border: 1px solid black; padding: 5px;width: 20px; word-wrap: break-word;'>")
										.append(srNo.getAndIncrement()).append("</td>");
								htmlTable.append(
												"<td style='border: 1px solid black; padding: 5px; word-wrap: break-word;'>")
										.append(detail.getProduct_material_name()).append("</td>");
//					                    htmlTable.append("<td style='border: 1px solid black; padding: 5px; width: 40px; word-wrap: break-word;'>").append(detail.getProduct_material_grn_quantity()).append("</td>");

								if (!isGRNApprove) {
									htmlTable.append(
													"<td style='border: 1px solid black; padding: 5px; width: 55px; word-wrap: break-word;'>")
											.append(detail.getProduct_material_grn_quantity()).append("</td>");
								} else {
									htmlTable.append(
													"<td style='border: 1px solid black; padding: 5px; width: 55px; word-wrap: break-word;'>")
											.append(detail.getProduct_material_grn_accepted_quantity()).append("</td>");
									htmlTable.append(
													"<td style='border: 1px solid black; padding: 5px; width: 55px; word-wrap: break-word;'>")
											.append(detail.getProduct_material_grn_rejected_quantity()).append("</td>");
								}
								htmlTable.append("</tr>");
							});
							htmlTable.append("</tbody></table><p> Thanks </p>");

							String emailTemplate = "<!DOCTYPE html> " + "<html> " + " " + "<head> "
									+ "    <title>" + templateParams[0] + "</title> " + "</head> "
									+ "<body style='font-family: Arial, sans-serif; margin: 0; padding: 0;'> "
									+ "    <div style=\"max-width: 600px; margin: 0 auto;\"> "
									+ "        <h1 style=\"color: #ffffff; background-color: #007bff; padding: 5px 0; text-align: center;\">"
									+ templateParams[0] + "</h1> "
									+ "        <div style=\"background-color: #f4f4f4; padding: 20px 40px; text-align: justify; font-family: Arial, sans-serif; font-size: 14px; max-width: 900px;\"> "
									+ modifiedMsg.get() + htmlTable + "        </div> " + "    </div> "
									+ "</body></html>";

							email.setMailBodyMsg(emailTemplate);
//							System.out.println(emailTemplate);

							try {
								EmailSender emailSender = new EmailSender();
								emailSender.sendEmails(email, host, smtpUserName, smtpPassword, port, false);
							} catch (MessagingException e) {
								response.put("Status", false);
								response.put("success", "0");
								response.put("error", e.getMessage());
								response.put("message", "Email not sent...!");
							}
						}
					}
				});
			} else {
				response.put("Status", false);
				response.put("success", "0");
				response.put("error", "Email Details Not Received from DB.");
				response.put("message", "Email not sent...!");
			}
		} catch (Exception e) {
			response.put("Status", false);
			response.put("success", "0");
			response.put("error", e.getMessage());
			response.put("message", "Email not sent...!");
		}

	}

	public String replaceParameters(String emailTemplate, String[] templateParams) {
		for (int param = 1; param < templateParams.length; param++) {
			String placeholder = "$Param" + param + "$";
			emailTemplate = emailTemplate.replaceAll(Pattern.quote(placeholder),
					"<Strong>" + templateParams[param] + "</Strong>");
		}
		return emailTemplate;
	}



	public Map<String, Object> getTotalsOfStockBatches(String productMaterialId, Integer companyId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productMaterialId", productMaterialId); // Add parameter to prevent SQL injection
		params.addValue("companyId", companyId);

		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append("COALESCE(SUM(st.closing_balance_quantity), 0) as closing_balance_quantity, ");
		query.append("ROUND( ");
		query.append("    IFNULL( ");
		query.append("        SUM(st.closing_balance_quantity * st.material_rate) / ");
		query.append("        NULLIF(SUM(st.closing_balance_quantity), 0), ");
		query.append("    0), 2 ");
		query.append(") AS closing_average_batch_rate, ");
		query.append("ROUND(COALESCE(SUM(st.closing_balance_quantity * st.material_rate), 0), 2) as closing_total_material_value ");
		query.append("FROM sm_product_rm_stock_summary AS st ");
		query.append("WHERE st.product_rm_id = :productMaterialId ");
		query.append("AND st.company_id = :companyId ");
		query.append("AND st.is_delete = 0");

		System.out.println("Summary Query: " + query);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		Map<String, Object> fetchData = namedParameterJdbcTemplate.queryForMap(query.toString(), params);
		System.out.println("Stock Summary Data: " + fetchData);

		return fetchData;
	}

}
