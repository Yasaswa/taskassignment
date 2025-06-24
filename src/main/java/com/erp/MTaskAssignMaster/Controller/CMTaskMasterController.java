package com.erp.MTaskAssignMaster.Controller;

import com.erp.CottonBalesMixingChart.Model.CCottonBalesMixingChartModel;
import com.erp.CottonBalesMixingChart.Model.CMixingChartStandardValuesModel;
import com.erp.MTaskAssignMaster.Model.CMTaskMasterModel;
import com.erp.MTaskAssignMaster.Respository.IMTaskMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MTaskAssign")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMTaskMasterController {

    @Autowired
    IMTaskMasterRepository imTaskMasterRepository;

    @PutMapping("/FnAddUpdateRecord/{keyForViewUpdate}")
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CMTaskMasterModel cmTaskMasterModel, @PathVariable String keyForViewUpdate) {
        Map<String, Object> response = new HashMap<>();
        try {
            imTaskMasterRepository.save(cmTaskMasterModel);

            response.put("success", 1);
            response.put("error", "");
            response.put("message", keyForViewUpdate.equals("Add")
                    ? "Records Added Successfully"
                    : "Records Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }
        return response;
    }


    @DeleteMapping("/FnDeleteRecord/{task_transaction_id}/{deleted_by}/{company_id}")
    public Integer FnDeleteRecord(@PathVariable int task_transaction_id, @PathVariable String deleted_by, @PathVariable int company_id) {
        imTaskMasterRepository.FnDeleteRecord(task_transaction_id, deleted_by, company_id);
        return 0;
    }


    @GetMapping("/FnShowParticularRecordForUpdate/{task_transaction_id}/{company_id}")
    public CMTaskMasterModel FnShowParticularRecordForUpdate(
            @PathVariable Integer task_transaction_id,
            @PathVariable Integer company_id) {

        CMTaskMasterModel cmTaskMasterModel = imTaskMasterRepository.FnGetTaskModel(task_transaction_id, company_id);
        return cmTaskMasterModel;
    }
}
