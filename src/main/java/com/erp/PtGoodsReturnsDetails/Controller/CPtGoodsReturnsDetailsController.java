package com.erp.PtGoodsReturnsDetails.Controller;

import com.erp.PtGoodsReturnsDetails.Repository.IPtGoodsReturnsDetailsRepository;
import com.erp.PtGoodsReturnsDetails.Service.IPtGoodsReturnsService;
import com.erp.StIndentIssueMaster.Service.RescheduleIssueServiceImp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.*;

@RestController
@RequestMapping("/api/GoodsReturnsDetails")
public class CPtGoodsReturnsDetailsController {
    @Autowired
    IPtGoodsReturnsService iPtGoodsReturnsService;
     @Autowired
     IPtGoodsReturnsDetailsRepository iPtGoodsReturnsDetailsRepository;

    @Autowired
    RescheduleIssueServiceImp rescheduleIssueServiceImp;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodsReturnsDetails") JSONObject jsonObject) {

        Map<String, Object> response = iPtGoodsReturnsService.FnAddUpdateRecord(jsonObject);

        if (response.containsKey("issuedBatchList")) {
            List<Map<String, Object>> issuedBatchList = (List<Map<String, Object>>) response.get("issuedBatchList");

            JSONArray GoodsReturnDetails = jsonObject.getJSONArray("GoodsReturnDetailData");
            JSONObject firstDetail = GoodsReturnDetails.getJSONObject(0);
            int companyId = firstDetail.getInt("company_id");
            int godownId = firstDetail.getInt("godown_id");

            if (issuedBatchList != null && !issuedBatchList.isEmpty()) {
                rescheduleIssueServiceImp.rescheduleStockIssue(issuedBatchList, companyId, godownId);
            }

        }

        return response;

    }


    @DeleteMapping("/FnDeleteRecord/{goods_return_master_id}/{company_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int goods_return_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
        return iPtGoodsReturnsService.FnDeleteRecord(goods_return_master_id, company_id,deleted_by);

    }


    @GetMapping("/FnShowParticularRecordForUpdate/{goods_return_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int goods_return_master_id, @PathVariable int company_id)  {
        Map<String, Object> responce =  iPtGoodsReturnsService.FnShowParticularRecordForUpdate(goods_return_master_id, company_id);
        return responce;
    }

    @GetMapping("/FnGenerateYarnSaleChallanNo/{company_id}/{job_type_id}")
    public String FnGenerateYarnSaleChallanNo(@PathVariable Integer company_id, @PathVariable Integer job_type_id){
        return iPtGoodsReturnsDetailsRepository.FnGenerateYarnSaleChallanNo(company_id, job_type_id);
    }
}
