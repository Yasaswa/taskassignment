package com.erp.PtCottonBalesQualityTesting.Controller;

import com.erp.PtCottonBalesQualityTesting.Service.IPtCottonBalesQualityTestingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/PtCottonBalesQualityTesting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtCottonBalesQualityTestingController {
@Autowired
    IPtCottonBalesQualityTestingService iPtCottonBalesQualityTestingService;
    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("PtCottonBalesQualityTesting") JSONObject jsonObject) {
        Map<String, Object> resp = new HashMap<>();
        resp = iPtCottonBalesQualityTestingService.FnAddUpdateRecord(jsonObject);
        return resp;
    }
    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
                                                               @RequestParam String quality_testing_no) {
        return iPtCottonBalesQualityTestingService.FnShowParticularRecordForUpdate(company_id, quality_testing_no);
    }
}
