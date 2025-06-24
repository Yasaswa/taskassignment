package com.erp.XtWeavingProductionSizingDetails.Controller;


import com.erp.RawMaterial.Product_Rm.Model.CProductRmModel;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.StIndentIssueMaster.Repository.IStIndentMaterialIssueDetailRepository;
import com.erp.XtBeamInwards.Repository.IXtBeamInwardsRepository;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderCreelsModel;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtSizingProductionStockDetailViewModel;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingDetailsViewModel;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtSizingProductionStockDetailsViewRepository;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtWeavingProductionSizingDetailsRepository;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtWeavingProductionSizingDetailsViewRepository;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtWeavingProductionSizingMasterRepository;
import com.erp.XtWeavingProductionSizingDetails.Service.IXtWeavingProductionSizingDetailsService;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingBottomDetailsModel;
import com.erp.XtWeavingProductionWarpingMaster.Repository.IXtWeavingProductionWarpingBottomDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/XtWeavingProductionSizingDetails")
public class CXtWeavingProductionSizingDetailsController {

    @Autowired
    IXtWeavingProductionSizingDetailsService iXtWeavingProductionSizingDetailsService;

    @Autowired
    IXtWeavingProductionSizingDetailsRepository iXtWeavingProductionSizingDetailsRepository;

    @Autowired
    IXtSizingProductionStockDetailsViewRepository iXtSizingProductionStockDetailsViewRepository;

    @Autowired
    IXtWeavingProductionSizingDetailsViewRepository iXtWeavingProductionSizingDetailsViewRepository;

    @Autowired
    IXtWeavingProductionWarpingBottomDetailsRepository iXtWeavingProductionWarpingBottomDetailsRepository;

    @Autowired
    IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

    @Autowired
    IXtWeavingProductionSizingMasterRepository iXtWeavingProductionSizingMasterRepository;

    @Autowired
    IXtBeamInwardsRepository iXtBeamInwardsRepository;

    @Autowired
    IProductRmRepository iProductRmRepository;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionSizingData") JSONObject jsonObject) {
        Map<String, Object> responce = iXtWeavingProductionSizingDetailsService.FnAddUpdateRecord(jsonObject);
        return responce;

    }

    @DeleteMapping("/FnDeleteRecord/{weaving_production_sizing_master_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_sizing_master_id, @PathVariable String deleted_by) {
        return iXtWeavingProductionSizingDetailsService.FnDeleteRecord(weaving_production_sizing_master_id, deleted_by);

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_sizing_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_sizing_master_id, @PathVariable int company_id) {
        return iXtWeavingProductionSizingDetailsService.FnShowParticularRecordForUpdate(weaving_production_sizing_master_id, company_id);
    }


    @GetMapping("/FnShowParticularSizingShiftSummary/{sizing_production_date}/{company_id}")
    public Map<String, Object> FnShowParticularSizingShiftSummary(@PathVariable String sizing_production_date, @PathVariable int company_id) {
        Map<String, Object> responce = iXtWeavingProductionSizingDetailsService.FnShowParticularSizingShiftSummary(sizing_production_date, company_id);
        return responce;
    }

    @Transactional
    @GetMapping("/FnGetSizingProductionRecords/{set_no}/{company_id}/{department_id}/{sub_department_id}")
    public Map<String, Object> FnGetSizingProductionRecords(@PathVariable String set_no, @PathVariable int company_id, @PathVariable int department_id, @PathVariable int sub_department_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ArrayList<CXtWeavingProductionSizingDetailsViewModel> sizingProductionDetails = iXtWeavingProductionSizingDetailsViewRepository.FnGetSizingProductionRecords(set_no, company_id);
//            List<CXtWarpingProductionOrderCreelsModel> warpingCreelDetails = iXtWeavingProductionWarpingBottomDetailsRepository.FnGetWarpingCreelRecords(set_no, company_id);
            List<Object[]> warpingBottomDetails = iXtWeavingProductionWarpingBottomDetailsRepository.FnGetWarpingProductionBottomRecords(set_no, company_id);
            List<CXtWeavingProductionWarpingBottomDetailsModel> resultList = new ArrayList<>();

            for (Object[] row : warpingBottomDetails) {
                CXtWeavingProductionWarpingBottomDetailsModel model = new CXtWeavingProductionWarpingBottomDetailsModel();

                model.setNo_of_package(row[0] != null ? ((Number) row[0]).doubleValue() : 0.0);
                model.setGross_weight(row[1] != null ? ((Number) row[1]).doubleValue() : 0.0);
                model.setTare_weight(row[2] != null ? ((Number) row[2]).doubleValue() : 0.0);
                model.setNet_weight(row[3] != null ? ((Number) row[3]).doubleValue() : 0.0);
                model.setProduct_rm_name(row[4] != null ? row[4].toString() : "");

                resultList.add(model);
            }
//            Map<String, Object> masterdata = iStIndentMaterialIssueDetailRepository.FnGetSizingProductionMasterdata(set_no, company_id, department_id, sub_department_id);
            List<Map<String, Object>> masterdataList = iStIndentMaterialIssueDetailRepository.FnGetSizingProductionMasterdata(set_no, company_id, department_id, sub_department_id);


            response.put("SizingProdData",sizingProductionDetails);
//            response.put("warpingCreelDetails",warpingCreelDetails);
            response.put("WarpingBottomData", resultList);
            response.put("MasterData", masterdataList);
        }catch (Exception e){
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }



    @Transactional
    @GetMapping("/FnGetSizingMasterData/{set_no}/{company_id}")
    public Map<String, Object> FnGetSizingMasterData(@PathVariable String set_no, @PathVariable int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ArrayList<Map<String, Object>> sizingMasterData = iXtWeavingProductionSizingMasterRepository.FnGetSizingMasterData(set_no, company_id);
            Map<String, Object> firstRecord = sizingMasterData.get(0);

            String job_type = (String) firstRecord.get("job_type");
            Object customer_id = firstRecord.get("customer_id");

            ArrayList<Map<String, Object>> sizingBeams = new ArrayList<>();
            if (!"Fabric Sale".equals(job_type)) {
                 sizingBeams = iXtBeamInwardsRepository.FnGetBeams(customer_id, company_id);
            }else{
                sizingBeams = iXtBeamInwardsRepository.FnGetBeams(0, company_id);
            }
            response.put("SizingBeams", sizingBeams);
            response.put("SizingMasterData", sizingMasterData);
        }catch (Exception e){
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @GetMapping("/checkMultiCountExist")
    public String checkMultiCountExist(@RequestParam String yarn_count, @RequestParam Integer companyId) {

        String material_id = "";
        CProductRmModel resultsProductRmName = null;
        resultsProductRmName = iProductRmRepository.getCheckMultipleCountExist(yarn_count,
                null, companyId);
        if (resultsProductRmName != null) {
            material_id = resultsProductRmName.getProduct_rm_id();
        }
        return material_id;
    }



    @GetMapping("/FnGetSizingProductionReportData/{company_id}/{from_date}/{to_date}/{set_no}")
    public Map<String, Object> FnGetWarpingProductionReportData(@PathVariable Integer company_id, @PathVariable String from_date, @PathVariable String to_date, @PathVariable  String set_no, @RequestParam String yarn_count, @RequestParam String customer_order_no){
        Map<String, Object> response = new HashMap<>();
        try {
            //Warping Data For Break Per Millions
            set_no = !set_no.equals("set_no") ? set_no : null;
            yarn_count = !yarn_count.equals("yarn_count") ? yarn_count : null;
            customer_order_no = !customer_order_no.equals("customer_order_no") ? customer_order_no : null;

            //Sizing Prod Data
            List<Map<String, Object>> sizingProdData =  iXtWeavingProductionSizingDetailsRepository.FetchSizingProdData(set_no, company_id, from_date, to_date, yarn_count, customer_order_no);


            List<String> customerOrderNos = sizingProdData.stream().map(data -> {Object val = data.get("customer_order_no");return val != null ? val.toString().split(",")[0] : "";}).distinct().collect(Collectors.toList());

            //JobType Data
            List<Map<String, Object>> jobtypeNames = iXtWeavingProductionSizingDetailsRepository.FnGetJobType(customerOrderNos, company_id);

            response.put("JobTypeData", jobtypeNames);
            response.put("SizingProductionData", sizingProdData);

        }catch (Exception e){
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }
}
