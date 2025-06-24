package com.erp.PtGoodsReturnFabricDetails.Controller;

import com.erp.PtGoodsReturnFabricDetails.Repository.*;
import com.erp.PtGoodsReturnFabricDetails.Service.IPtGoodsReturnFabricService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/GoodsReturnFabricDetails")
public class CPtGoodsReturnFabricDetailsController {

    @Autowired
    IPtGoodsReturnFabricService iPtGoodsReturnFabricService;

    @Autowired
    IPtGoodsReturnFabricDetailsRepository iPtGoodsReturnFabricDetailsRepository;

    @Autowired
    IPtGoodsReturnFabricMasterRepository iPtGoodsReturnFabricMasterRepository;

    @Autowired
    IPtGoodsReturnFabricPaymentTermRepository iPtGoodsReturnFabricPaymentTermRepository;

    @Autowired
    IPtGoodsReturnFabricTaxSummaryRepository iPtGoodsReturnFabricTaxSummaryRepository;

    @Autowired
    IPtGoodsReturnFabricRollsDetailsRepository iPtGoodsReturnFabricRollsDetailsRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodsReturnFabricDetails") JSONObject jsonObject) {
        Map<String, Object> responce = iPtGoodsReturnFabricService.FnAddUpdateRecord(jsonObject);

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        Map<String, Object> stockResponse = (Map<String, Object>) responce.get("stockResponse");
            if (stockResponse != null && stockResponse.containsKey("FGStockAddDetails")) {
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
        return responce;
    }

    @GetMapping("/FnShowAllDetailsAndMastermodelRecords/{goods_return_fabric_version}/{financial_year}/{company_id}")
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
            @RequestParam("goods_return_fabric_no") String goods_return_fabric_no, @PathVariable int goods_return_fabric_version,
            @PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
        return iPtGoodsReturnFabricService.FnShowAllDetailsandMastermodelRecords(goods_return_fabric_no,
                goods_return_fabric_version, financial_year, company_id);
    }

    @DeleteMapping("/FnDeleteRecord/{company_id}/{UserName}")
    public int FnShowParticularRecordForUpdate(@PathVariable int company_id, @PathVariable String UserName, @RequestParam String goods_return_fabric_no) {

        iPtGoodsReturnFabricMasterRepository.deleteRecords(company_id, UserName, goods_return_fabric_no);
        iPtGoodsReturnFabricDetailsRepository.deleteRecords(company_id, UserName, goods_return_fabric_no);
        iPtGoodsReturnFabricRollsDetailsRepository.deleteRecords(company_id, UserName, goods_return_fabric_no);
        iPtGoodsReturnFabricPaymentTermRepository.deleteRecords(company_id, UserName, goods_return_fabric_no);
        iPtGoodsReturnFabricTaxSummaryRepository.deleteRecords(company_id, UserName, goods_return_fabric_no);
        return 0;
    }
}
