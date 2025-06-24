package com.erp.PtCottonBalesSales.Controller;

import com.erp.PtCottonBalesSales.Service.IPtCottonBalesSalesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/PtCottonBalesSales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtCottonBalesSalesController {

    @Autowired
    IPtCottonBalesSalesService iPtCottonBalesSalesService;


    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("CottonBalesSalesData") JSONObject jsonObject){
        Map<String,Object> response = iPtCottonBalesSalesService.FnAddUpdateRecord(jsonObject);
        return response;
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{pt_cotton_bales_sales_master_transaction_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable Integer company_id, @PathVariable Integer pt_cotton_bales_sales_master_transaction_id){
        Map<String, Object> response = iPtCottonBalesSalesService.FnShowParticularRecordForUpdate(company_id, pt_cotton_bales_sales_master_transaction_id);
        return response;
    }
}
