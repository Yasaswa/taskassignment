package com.erp.SortTransfer.Controller;


import com.erp.SortTransfer.Service.STSortTransferService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/SortTransfer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SortTransferController {

    @Autowired
    STSortTransferService sTSortTransferService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody Map<String, Object> jsonObject) {
        JSONObject jsonObj = new JSONObject(jsonObject);
        Map<String, Object> apiResponse = sTSortTransferService.FnAddUpdateRecord(jsonObj);
        JSONObject commonIdsObj = (JSONObject) jsonObj.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        Map<String, Object> stockResponse = (Map<String, Object>) apiResponse.get("stockResponse");
        if(stockResponse.containsKey("FGStockAddDetails")){
            Map<String, Object> FgStockDetails = (Map<String, Object>) stockResponse.get("FGStockAddDetails");
            List<Map<String, Object>> fgBatchList = (List<Map<String, Object>>) FgStockDetails.get("issuedBatchList");
            if (!fgBatchList.isEmpty()) {
                String storedProcedure = "{call fabric_insert_update_stock(?,?,?,?,?)}";
                jdbcTemplate.execute((Connection con) -> {
                    try (CallableStatement cs = con.prepareCall(storedProcedure)) {
                        for (Map<String, Object> batch : fgBatchList) {
                            cs.setString(1, batch.get("goods_receipt_no").toString());
                            cs.setString(2, batch.get("stock_date").toString());
                            cs.setString(3, batch.get("product_material_id").toString());
                            cs.setString(4, batch.get("batch_no").toString());
                            cs.setInt(5, company_id);
                            cs.addBatch();
                        }
                        cs.executeBatch();
                    }
                    return null;
                });
            }
        }
        if(stockResponse.containsKey("FGStockReducedDetails")){
            Map<String, Object> FgStockDetails = (Map<String, Object>) stockResponse.get("FGStockReducedDetails");
            List<Map<String, Object>> fgBatchList = (List<Map<String, Object>>) FgStockDetails.get("issuedBatchList");
            if (!fgBatchList.isEmpty()) {
                String storedProcedure = "{call fabric_insert_update_stock(?,?,?,?,?)}";
                jdbcTemplate.execute((Connection con) -> {
                    try (CallableStatement cs = con.prepareCall(storedProcedure)) {
                        for (Map<String, Object> batch : fgBatchList) {
                            cs.setString(1, batch.get("goods_receipt_no").toString());
                            cs.setString(2, batch.get("stock_date").toString());
                            cs.setString(3, batch.get("product_material_id").toString());
                            cs.setString(4, batch.get("batch_no").toString());
                            cs.setInt(5, company_id);
                            cs.addBatch();
                        }
                        cs.executeBatch();
                    }
                    return null;
                });
            }
        }
        return apiResponse;

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{sort_transfer_master_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable Integer company_id, @PathVariable Integer sort_transfer_master_id){
        Map<String, Object> response = sTSortTransferService.FnShowParticularRecordForUpdate(company_id, sort_transfer_master_id);
        return response;
    }



}
