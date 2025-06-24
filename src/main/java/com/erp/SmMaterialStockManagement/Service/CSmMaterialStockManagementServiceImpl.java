package com.erp.SmMaterialStockManagement.Service;

import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockLogRepository;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CSmMaterialStockManagementServiceImpl implements ISmMaterialStockManagementService {

    @Autowired
    ISmMaterialStockLogRepository iSmMaterialStockLogRepository;

    @Autowired
    ISmMaterialStockManagementRepository iSmMaterialStockManagementRepository;

    Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());

    @Override
    public Map<String, Object> FnGRNAddUpdateRecord(Map<String, Object> grnStockdata) {
        Map<String, Object> responce = new HashMap<>();
        try {
// Retrieve stockModels and stockLogModels
            List<CSmProductMaterialStockNewModel> stockModels = (List<CSmProductMaterialStockNewModel>) grnStockdata.get("GRNStockModels");
            List<CSmMaterialStockLogModel> stockLogModels = (List<CSmMaterialStockLogModel>) grnStockdata.get("GRNActivityLogModels");
            String ApprovedName = (String) grnStockdata.get("ApprovedName");
// Process and update stock models directly
            List<CSmProductMaterialStockNewModel> updatedStockModels = stockModels.stream()
                    .map(stockmodel -> {
                        CSmProductMaterialStockNewModel matchedModel =
                                iSmMaterialStockManagementRepository.fnGetdataBasedonPrmIdBatchNo(
                                        stockmodel.getProduct_material_id(),
                                        stockmodel.getBatch_no()
                                );

                        if (matchedModel != null) {
                            // Matched case: update the matched model
                            stockmodel.setStock_transaction_id(matchedModel.getStock_transaction_id());
                            stockmodel.setPurchase_quantity(stockmodel.getPurchase_quantity() + matchedModel.getClosing_balance_quantity());
                            stockmodel.setPurchase_weight(stockmodel.getPurchase_weight() + matchedModel.getClosing_balance_weight());
                            stockmodel.setClosing_balance_weight(stockmodel.getClosing_balance_weight() + matchedModel.getClosing_balance_weight());
                            stockmodel.setClosing_balance_quantity(stockmodel.getClosing_balance_quantity() + matchedModel.getClosing_balance_quantity());
                            stockmodel.setModified_by(matchedModel.getModified_by());
                            stockmodel.setModified_on(currentTimestamp);
                        } else {
                            // No match found, treat it as a new record
                            stockmodel.setCreated_by(ApprovedName);
                            stockmodel.setCreated_on(currentTimestamp);
                        }

                        return stockmodel;
                    })
                    .collect(Collectors.toList());

            // Save all updated stock models
            iSmMaterialStockManagementRepository.saveAll(updatedStockModels);

            // Save stock log models
            iSmMaterialStockLogRepository.saveAll(stockLogModels);

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }
        return responce;
    }


    public Map<String, Object> FnIssueAllMixingBales(List<CSmProductMaterialStockNewModel> stockDataModels, List<CSmMaterialStockLogModel> stockLogModels) {


        // Save all updated stock models
        iSmMaterialStockManagementRepository.saveAll(stockDataModels);
        // Save stock log models
        iSmMaterialStockLogRepository.saveAll(stockLogModels);

        return Map.of();
    }

}
