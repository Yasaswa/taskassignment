package com.erp.DispatchSizedYarn.Controller;

import com.erp.DispatchSizedYarn.Model.CDtSizedYarnDetailsModel;
import com.erp.DispatchSizedYarn.Model.CDtSizedYarnDetailsViewModel;
import com.erp.DispatchSizedYarn.Model.CDtSizedYarnMasterModel;
import com.erp.DispatchSizedYarn.Model.CDtSizedYarnMasterViewModel;
import com.erp.DispatchSizedYarn.Repository.IDtSizedYarnDetailsRepository;
import com.erp.DispatchSizedYarn.Repository.IDtSizedYarnModelRepository;
import com.erp.DispatchSizedYarn.Repository.IDtSizedYarnViewDetailsRepository;
import com.erp.DispatchSizedYarn.Repository.IDtSizedYarnViewModelRepository;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingModel;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderDetailsTradingRepository;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderMasterTradingRepository;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptPaymentTermsModel;
import com.erp.XtWarpingProductionOrder.Repository.IXtWarpingProductionOrderRepository;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtSizingProductionStockDetailViewModel;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtSizingProductionStockDetailsRepository;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtSizingProductionStockDetailsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/DispatchSizedYarn")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDtSizedYarnController {

    @Autowired
    IDtSizedYarnModelRepository iDtSizedYarnModelRepository;

    @Autowired
    IXtSizingProductionStockDetailsViewRepository iXtSizingProductionStockDetailsViewRepository;

    @Autowired
    IXtSizingProductionStockDetailsRepository iXtSizingProductionStockDetailsRepository;
    @Autowired
    IMtSalesOrderDetailsTradingRepository iMtSalesOrderDetailsTradingRepository;
    @Autowired
    IMtSalesOrderMasterTradingRepository iMtSalesOrderMasterTradingRepository;

    @Autowired
    IDtSizedYarnDetailsRepository iDtSizedYarnDetailsRepository;

    @Autowired
    IDtSizedYarnViewModelRepository iDtSizedYarnViewModelRepository;

    @Autowired
    IDtSizedYarnViewDetailsRepository iDtSizedYarnViewDetailsRepository;

    @Autowired
    IXtWarpingProductionOrderRepository iXtWarpingProductionOrderRepository;

    @GetMapping("/FnShowParticularRecordForUpdate/{dispatch_challan_sized_yarn_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int dispatch_challan_sized_yarn_id, @PathVariable int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CDtSizedYarnMasterViewModel cDtSizedYarnMasterViewModel = iDtSizedYarnViewModelRepository.FnGetDispatchSizedYarnMasterData(dispatch_challan_sized_yarn_id, company_id);
            List<CDtSizedYarnDetailsViewModel> cDtSizedYarnDetailsViewModels = iDtSizedYarnViewDetailsRepository.FnGetDispatchSizedYarnDetailsData(dispatch_challan_sized_yarn_id, company_id);

            response.put("SizedYarnMasterData", cDtSizedYarnMasterViewModel);
            response.put("SizedYarnDetailsData", cDtSizedYarnDetailsViewModels);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
        }
        return response;
    }


    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("DispatchSizingYarnData") JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
            int company_id = commonIdsObj.getInt("company_id");
            String keyviewupdate = commonIdsObj.getString("keyviewupdate");
            String afterApproveStatus = commonIdsObj.getString("afterApproveStatus");
            String weightFlag = commonIdsObj.getString("weightFlag");
            int dispatch_challan_sized_yarn_id = commonIdsObj.getInt("dispatch_challan_sized_yarn_id");

            JSONObject masterjson = jsonObject.getJSONObject("DispatchYarnMasterData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("DispatchYarnDetailsData");

            //Saving Master data
            CDtSizedYarnMasterModel cDtSizedYarnMasterModel = objectMapper.readValue(masterjson.toString(), CDtSizedYarnMasterModel.class);

            String customer_order_no = cDtSizedYarnMasterModel.getCustomer_order_no();
            if (keyviewupdate.equals("Add")) {
                List<String> paymentterms = iDtSizedYarnModelRepository.FNGetPaymentTermDetails(customer_order_no, company_id);
                cDtSizedYarnMasterModel.setDispatch_payment_terms(paymentterms.toString());
            }
            CDtSizedYarnMasterModel respcDtSizedYarnMasterModel = iDtSizedYarnModelRepository.save(cDtSizedYarnMasterModel);


            //Saving Details Data
            List<CDtSizedYarnDetailsModel> cDtSizedYarnDetailsModels = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CDtSizedYarnDetailsModel>>() {
            });

            //For updating status of Beams in Sizing Stock Yarn
            iDtSizedYarnDetailsRepository.updateBeamDetailsStatus(cDtSizedYarnMasterModel.getDispatch_challan_no(), company_id);

            if (keyviewupdate.equals("approve")) {
                // Get dispatch challan date
                Date dispatch_challan_date = new SimpleDateFormat("yyyy-MM-dd").parse(cDtSizedYarnMasterModel.getDispatch_challan_date());

                // Validate list size before accessing an element by index
                String set_no = cDtSizedYarnDetailsModels.get(0).getSet_no();

                JSONArray approvedBeamIdsJsonArray = (JSONArray) commonIdsObj.get("approvedBeamIds");

                List<String> approvedBeamIds = IntStream.range(0, approvedBeamIdsJsonArray.length())
                        .mapToObj(i -> approvedBeamIdsJsonArray.getString(i))
                        .collect(Collectors.toList());
                if (dispatch_challan_date != null && set_no != null && !approvedBeamIds.isEmpty() && "D".equalsIgnoreCase(afterApproveStatus)) {
                    iXtSizingProductionStockDetailsRepository.updateSizingProdBeamStatusAfterApproved(dispatch_challan_date, set_no, approvedBeamIds, company_id);
                }
                String salesOrderStatus = "";
                // Extract beam IDs from the list
                List<Integer> beamIds = cDtSizedYarnDetailsModels.stream().map(data -> data.getBeam_id()).collect(Collectors.toList());
                List<String> beamIdStrings = beamIds.stream().map(String::valueOf).collect(Collectors.toList());


                if (dispatch_challan_date != null && set_no != null && !beamIds.isEmpty()) {
                    iXtSizingProductionStockDetailsRepository.updateSizingProdBeamStatus(dispatch_challan_date, set_no, beamIdStrings, company_id);
                }
                // update status and Quantity in sales order
                List<CMtSalesOrderDetailsTradingModel> customerOrderDetailsFromDB = iMtSalesOrderDetailsTradingRepository.FnShowSalesOrderDetailsByCustomerOrderNo(company_id, customer_order_no);
                List<CMtSalesOrderDetailsTradingModel> updatedSalesOrderDetails = new ArrayList<CMtSalesOrderDetailsTradingModel>();
                Optional<CMtSalesOrderDetailsTradingModel> matchingSODetailObjs = customerOrderDetailsFromDB.stream()
                        .filter(soDetail -> cDtSizedYarnMasterModel.getProduct_material_id().equals(soDetail.getProduct_material_id()) && cDtSizedYarnMasterModel.getCustomer_order_no().equals(soDetail.getCustomer_order_no()))
                        .findFirst();
                if (matchingSODetailObjs.isPresent()) {
                    CMtSalesOrderDetailsTradingModel matchedSoDetail = matchingSODetailObjs.get();

                    Double updatedSOQty;
                    Double updatedSOWt;
                    Double BeforeApprovalWt = commonIdsObj.getDouble("beforeApproveWeight");
                    if ("D".equalsIgnoreCase(afterApproveStatus)) {
                        if ("Yes".equalsIgnoreCase(weightFlag)) {
                            updatedSOQty = matchedSoDetail.getSales_quantity() + (cDtSizedYarnMasterModel.getWeight() - BeforeApprovalWt);
                            updatedSOWt = matchedSoDetail.getSales_weight() + (cDtSizedYarnMasterModel.getWeight() - BeforeApprovalWt);
                            matchedSoDetail.setPending_quantity(matchedSoDetail.getPending_quantity() - (cDtSizedYarnMasterModel.getWeight() - BeforeApprovalWt));
                            matchedSoDetail.setPending_weight(matchedSoDetail.getPending_weight() - (cDtSizedYarnMasterModel.getWeight() - BeforeApprovalWt));
                        } else {
                            updatedSOQty = matchedSoDetail.getSales_quantity() - (BeforeApprovalWt - cDtSizedYarnMasterModel.getWeight());
                            updatedSOWt = matchedSoDetail.getSales_weight() - (BeforeApprovalWt - cDtSizedYarnMasterModel.getWeight());
                            matchedSoDetail.setPending_quantity(matchedSoDetail.getPending_quantity() + (BeforeApprovalWt - cDtSizedYarnMasterModel.getWeight()));
                            matchedSoDetail.setPending_weight(matchedSoDetail.getPending_weight() + (BeforeApprovalWt - cDtSizedYarnMasterModel.getWeight()));
                        }

                    } else {
                        updatedSOQty = matchedSoDetail.getSales_quantity() + cDtSizedYarnMasterModel.getWeight();
                        updatedSOWt = matchedSoDetail.getSales_weight() + cDtSizedYarnMasterModel.getWeight();
                        matchedSoDetail.setPending_quantity(matchedSoDetail.getMaterial_quantity() - updatedSOQty);
                        matchedSoDetail.setPending_weight(matchedSoDetail.getMaterial_weight() - updatedSOWt);
                    }

// Update sales quantity and weight
                    matchedSoDetail.setSales_quantity(updatedSOQty);
                    matchedSoDetail.setSales_weight(updatedSOWt);


                    // Update the sales-order sales details.
//                    matchedSoDetail.setSales_quantity(updatedSOQty);
//                    matchedSoDetail.setSales_weight(updatedSOWt);
//                    matchedSoDetail.setPending_quantity(matchedSoDetail.getMaterial_quantity() - updatedSOQty);
//                    matchedSoDetail.setPending_weight(matchedSoDetail.getMaterial_weight() - updatedSOWt);
                    matchedSoDetail.setModified_by(cDtSizedYarnMasterModel.getModified_by());

                    // Update the So_details item_status.
                    if (matchedSoDetail.getSales_quantity() < matchedSoDetail.getMaterial_quantity()) {
                        matchedSoDetail.setSales_order_item_status("I");
                        System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Sales Qty: " + matchedSoDetail.getSales_quantity() + " Sales Wt: " + matchedSoDetail.getSales_weight());
                        salesOrderStatus = "I";
                    } else if (matchedSoDetail.getSales_quantity() == matchedSoDetail.getMaterial_quantity()) {
                        matchedSoDetail.setSales_order_item_status("C");
                        System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Sales Qty: " + matchedSoDetail.getSales_quantity() + " Sales Wt: " + matchedSoDetail.getSales_weight());
                        salesOrderStatus = "C";
                    }

                    // add the so_details in the list for update the so-details.
                    updatedSalesOrderDetails.add(matchedSoDetail);
                }
                iMtSalesOrderDetailsTradingRepository.saveAll(updatedSalesOrderDetails);
                iMtSalesOrderMasterTradingRepository.updateSalesOrderMasterDetail(customer_order_no, company_id, salesOrderStatus, cDtSizedYarnMasterModel.getModified_by());
                System.out.println("Updated the SalesOrder Details. Now updating the so-master.");
            }


            cDtSizedYarnDetailsModels.stream().forEach((detailmodel) -> detailmodel.setDispatch_challan_sized_yarn_id(respcDtSizedYarnMasterModel.getDispatch_challan_sized_yarn_id()));
            iDtSizedYarnDetailsRepository.saveAll(cDtSizedYarnDetailsModels);

            responce.put("success", 1);
            responce.put("error", "");
            responce.put("message", respcDtSizedYarnMasterModel.getDispatch_challan_status().equals("D") ? "Record Approved successfully!..." :
                    (dispatch_challan_sized_yarn_id == 0 ? "Record added successfully!..." : "Record updated successfully!..."));
        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 1);
            responce.put("message", e.getMessage());
        }
        return responce;

    }

    @GetMapping("/FnGetSizedYarnData/{company_id}/{set_no}/{isAfterApprove}/{approvedBeamIdsStr}")
    public Map<String, Object> FnGetSizedYarnData(
            @PathVariable("company_id") int company_id,
            @PathVariable("set_no") String set_no, @PathVariable("isAfterApprove") String isAfterApprove , @PathVariable("approvedBeamIdsStr") String approvedBeamIdsStr) {
        Map<String, Object> response = new HashMap<>();

        List<String> approvedBeamIds = Arrays.asList(approvedBeamIdsStr.split(","));

        try {
            Map<String, Object> warpingProdPlanData = iXtWarpingProductionOrderRepository.FetchWarpingOrderData(set_no, company_id);

            // Trimming into individual Product Material Id's
            String productMaterialId = (String) warpingProdPlanData.get("product_material_id");
            List<String> productMaterialIds;
            if (productMaterialId != null && !productMaterialId.isEmpty()) {
                if (productMaterialId.contains(",")) {
                    // If comma found split into multiple
                    productMaterialIds = Arrays.stream(productMaterialId.split(",")).map(String::trim).collect(Collectors.toList());
                } else {
                    // Single value → directly add
                    productMaterialIds = new ArrayList<>();
                    productMaterialIds.add(productMaterialId.trim());
                }
            } else {
                productMaterialIds = new ArrayList<>(); // empty list if input is null/blank
            }


            //Trimming into individual Customer Order No's
            String customerOrderNo = (String) warpingProdPlanData.get("customer_order_no");
            List<String> customerOrderNos;

            if (customerOrderNo != null && !customerOrderNo.isEmpty()) {
                if (customerOrderNo.contains(",")) {
                    customerOrderNos = Arrays.stream(customerOrderNo.split(",")).map(String::trim).collect(Collectors.toList());
                } else {
                    customerOrderNos = new ArrayList<>();
                    customerOrderNos.add(customerOrderNo);
                }
            } else {
                customerOrderNos = new ArrayList<>();
            }

            List<CXtSizingProductionStockDetailViewModel> sizedYarnDetailsData;
            List<Map<String, Object>> sizedYarnMasterData = iDtSizedYarnModelRepository.FnGetDispatchSizedYarnMasterData(productMaterialIds, customerOrderNos);
            if ("Yes".equalsIgnoreCase(isAfterApprove)) {
                sizedYarnDetailsData = iXtSizingProductionStockDetailsViewRepository.FnGetDispatchSizedYarnDetailsAllData(company_id, set_no,approvedBeamIds);
            } else {
                sizedYarnDetailsData = iXtSizingProductionStockDetailsViewRepository.FnGetDispatchSizedYarnDetailsData(company_id, set_no);
            }
            Map<String, Object> issuedDataAgainstSetNo = iDtSizedYarnModelRepository.FnGetIssuedWtQty(company_id, set_no);

            List<String> paymentTerms = iDtSizedYarnModelRepository.findPaymentTermsByCustomerOrderNos(customerOrderNos);


            response.put("WarpingProdPlanData", warpingProdPlanData);
            response.put("SizedYarnMasterData", sizedYarnMasterData);
            response.put("SizedYarnDetailsData", sizedYarnDetailsData);
            response.put("IssuedQtyWtNetWt", issuedDataAgainstSetNo);
            response.put("paymentTerms", paymentTerms);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "An error occurred while fetching the data: " + e.getMessage());
        }
        return response;
    }


    //Generating Dispatch Sized Yarn Auto Generated Number
    @GetMapping("/FnGenerateSizedYarnChallanNo/{company_id}/{job_type_id}")
    public String FnGenerateSizedYarnChallanNo(@PathVariable Integer company_id, @PathVariable Integer job_type_id) {
        return iDtSizedYarnModelRepository.FnGenerateSizedYarnChallanNo(company_id, job_type_id);
    }

}
