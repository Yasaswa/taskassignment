package com.erp.XtWarpingProductionOrder.Controller;

import java.util.*;


import com.erp.StIndentIssueMaster.Model.CStIndentIssueMasterModel;
import com.erp.StIndentIssueMaster.Model.CStMaterialIssueBatchWiseModel;
import com.erp.StIndentIssueMaster.Repository.IStIndentIssueMasterRepository;
import com.erp.StIndentIssueMaster.Repository.IStIndentMaterialIssueDetailRepository;
import com.erp.StIndentIssueMaster.Repository.IStMaterialIssueBatchWiseRepository;
import com.erp.XtWarpingProductionOrder.Repository.IXtWarpingProductionBeamDetailsRepository;
import com.erp.XtWarpingProductionOrder.Repository.IXtWarpingProductionOrderRepository;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMasterModel;
import com.erp.XtWeavingProductionWarpingMaster.Repository.IXtWeavingProductionWarpingMasterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.XtWarpingProductionOrder.Service.IXtWarpingProductionOrderService;

@RestController
@RequestMapping("/api/XtWarpingProductionOrder")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXtWarpingProductionOrderController {

    @Autowired
    IXtWarpingProductionOrderService iXtWarpingProductionOrderService;

    @Autowired
    IXtWarpingProductionOrderRepository iXtWarpingProductionOrderRepository;

    @Autowired
    IXtWeavingProductionWarpingMasterRepository iXtWeavingProductionWarpingMasterRepository;

    @Autowired
    IStMaterialIssueBatchWiseRepository iStMaterialIssueBatchWiseRepository;

    @Autowired
    IStIndentIssueMasterRepository iStIndentIssueMasterRepository;

    @Autowired
    IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

    @Autowired
    IXtWarpingProductionBeamDetailsRepository iXtWarpingProductionBeamDetailsRepository;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("warpingProductionOrderRequest") JSONObject warpingProductionOrderRequest) throws JsonProcessingException {
        return iXtWarpingProductionOrderService.FnAddUpdateRecord(warpingProductionOrderRequest);

    }

    @DeleteMapping("/FnDeleteRecord/{warping_production_order_id}/{deleted_by}/{company_id}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int warping_production_order_id, @PathVariable String deleted_by, @PathVariable int company_id) {
        return iXtWarpingProductionOrderService.FnDeleteRecord(warping_production_order_id, deleted_by, company_id);
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{warping_production_order_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int warping_production_order_id, @PathVariable int company_id) {
        return iXtWarpingProductionOrderService.FnShowParticularRecordForUpdate(warping_production_order_id, company_id);
    }

    @GetMapping("/FnGetProductBasedProperties/{product_id}")
    public Map<String, Object> FnGetProductBasedProperties(@PathVariable String product_id) {
        return iXtWarpingProductionOrderService.FnGetProductBasedProperties(product_id);
    }

    @PostMapping("/FnGetCustomerMaterialData")
    public Map<String, Object> FnGetCustomerMaterialData(
            @RequestBody Map<String, Object> params
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            String customer_order_no = (String) params.get("customer_order_no");
            int product_type_id = (int) params.get("product_type_id");
            int company_id = (int) params.get("company_id");

            ArrayList<Map<String, Object>> customerMaterialList =
                    iXtWarpingProductionOrderRepository.FnGetCustomerMaterialData(customer_order_no, product_type_id, company_id);
            response.put("CustomerMaterialData", customerMaterialList);
            System.out.println("CustomerMaterialData: " + customerMaterialList);
        } catch (Exception e) {
            response.put("data", "");
            System.out.println("Error: " + e.getMessage());
            response.put("error", e.getMessage());
        }
        return response;
    }

    @GetMapping("/FnCheckSetNoExists/{set_no}/{company_id}")
    public boolean FnCheckSetNoExists(@PathVariable String set_no, @PathVariable Integer company_id) {
        Optional<String> result = iXtWarpingProductionOrderRepository.FnCheckSetNoExists(set_no, company_id);
        System.out.println("Query Result: " + result.orElse("No Value Found"));
        return result.isPresent();
    }

    @PatchMapping("/FnCancelSetno/{company_id}")
    public Map<String, Object> FnCancelSetno(@RequestBody Map<String, Object> obj, @PathVariable Integer company_id) {
        Map<String, Object> result = new HashMap<>();

        try {
            String set_no = (String) obj.get("set_no");
            String userName = (String) obj.get("username");

            //Check Indent Material Issue Status
            CStIndentIssueMasterModel cStIndentIssueMasterModel = iStIndentIssueMasterRepository.FnFetchMasterModelBySetno(set_no , company_id);

            if(cStIndentIssueMasterModel.getIssue_status().equals("P") || cStIndentIssueMasterModel.getIssue_status().equals("A")){
                iStIndentIssueMasterRepository.FnDeleteIndentIssueMasterRecord(cStIndentIssueMasterModel.getIssue_no() , company_id , userName);
                iStIndentMaterialIssueDetailRepository.FnDeleteIndentIssueDetailsRecord(cStIndentIssueMasterModel.getIssue_no() , cStIndentIssueMasterModel.getIssue_version() , company_id , userName);
                iStMaterialIssueBatchWiseRepository.FnDeleteIssueBatchDetailsRecord(cStIndentIssueMasterModel.getIssue_no() , company_id ,userName);

                iXtWarpingProductionOrderRepository.FnUpdateWarpingModalStatus(set_no, company_id);
                iXtWarpingProductionBeamDetailsRepository.FnUpdateWarpingBeamWiseModalStatus(set_no, company_id);
                result.put("success", 1);
                result.put("message", "You haven't issued Yarn against Set No: " + set_no + ".Plan canceled successfully.");
                return result;
            }


            //Checking Production Started
            Optional<CXtWeavingProductionWarpingMasterModel> warpingModel =
                    iXtWeavingProductionWarpingMasterRepository.FnGetWarpingModel(set_no, company_id);

            if (warpingModel.isPresent()) {
                result.put("success", 0);
                result.put("message", "Cannot cancel this plan against " + set_no + " because production already started.");
                return result;
            }

            List<CStMaterialIssueBatchWiseModel> materialIssueList =
                    iStMaterialIssueBatchWiseRepository.FnGetModalDataAgainstSetNo(set_no, company_id);

            if (materialIssueList == null || materialIssueList.isEmpty()) {
                result.put("success", 0);
                result.put("message", "Cannot cancel this plan against " + set_no + " because you haven't returned stock.");
                return result;
            }

            boolean allReturned = materialIssueList.stream().allMatch(data ->
                    parseFloatSafe(data.getIssue_quantity()) == parseFloatSafe(data.getReceipt_quantity()) &&
                            parseFloatSafe(data.getIssue_weight()) == parseFloatSafe(data.getReceipt_weight())
            );

            if (!allReturned) {
                result.put("success", 0);
                result.put("message", "Cannot cancel this plan against " + set_no + " because quantities do not match.");
            } else {
                iXtWarpingProductionOrderRepository.FnUpdateWarpingModalStatus(set_no, company_id);
                iXtWarpingProductionBeamDetailsRepository.FnUpdateWarpingBeamWiseModalStatus(set_no, company_id);
                result.put("success", 1);
                result.put("message", "Plan canceled successfully for Set No: " + set_no);
            }

        } catch (Exception e) {
            result.put("success", 0);
            result.put("message", "An error occurred during cancellation.");
            result.put("error", e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }

    private float parseFloatSafe(Object val) {
        try {
            return Float.parseFloat(String.valueOf(val));
        } catch (Exception e) {
            return 0f;
        }
    }


}
