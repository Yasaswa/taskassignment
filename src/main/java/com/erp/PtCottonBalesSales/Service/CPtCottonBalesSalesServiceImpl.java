package com.erp.PtCottonBalesSales.Service;

import com.erp.PtCottonBalesGRN.Repository.IPtGrnCottonBalesDetailsRepository;
import com.erp.PtCottonBalesGRN.Repository.IPtGrnCottonBalesMasterRepository;
import com.erp.PtCottonBalesSales.Model.*;
import com.erp.PtCottonBalesSales.Repository.*;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockLogRepository;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockManagementRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
@Service
@RequiredArgsConstructor
public class CPtCottonBalesSalesServiceImpl implements IPtCottonBalesSalesService{


    @Autowired
    IPtCottonBalesSalesMasterRepository iPtCottonBalesSalesMasterRepository;

    @Autowired
    IPtCottonBalesSalesDetailsRepository iPtCottonBalesSalesDetailsRepository;

    @Autowired
    IPtCottonBalesPaymentTermsRepository iPtCottonBalesPaymentTermsRepository;

    @Autowired
    ISmMaterialStockLogRepository iSmMaterialStockLogRepository;

    @Autowired
    ISmMaterialStockManagementRepository iSmMaterialStockManagementRepository;

    @Autowired
    IPtGrnCottonBalesMasterRepository iPtGrnCottonBalesMasterRepository;

    @Autowired
    IPtGrnCottonBalesDetailsRepository iPtGrnCottonBalesDetailsRepository;

    @Autowired
    IPtCottonBalesSalesMasterViewRepository iPtCottonBalesSalesMasterViewRepository;

    @Autowired
    IPtCottonBalesSalesDetailsViewRepository iPtCottonBalesSalesDetailsViewRepository;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject commonIdsObj = (JSONObject) jsonObject.getJSONObject("commonIdsObj");
            JSONObject  cottonBalesSalesMasterData = (JSONObject) jsonObject.getJSONObject("CottonBalesSalesMasterData");
            JSONArray cottonBalesSalesDetailsData = (JSONArray) jsonObject.getJSONArray("CottonBalesSalesDetailsData");
            JSONArray cottonBalesSalesPaymentTermsData = (JSONArray) jsonObject.getJSONArray("CottonBalesSalesPaymentTermsData");

            String keyForViewUpdate = commonIdsObj.getString("keyForViewUpdate");
            int company_id = commonIdsObj.getInt("company_id");
            String product_material_id = commonIdsObj.getString("product_material_id");
            String UserName = commonIdsObj.getString("UserName");

            // Use Timestamp instead of Date for better DB compatibility
            Timestamp currentDate = Timestamp.from(Instant.now());
            double total_sales_return_quantity = 0;
            double total_sales_return_weight = 0;

            CPtCottonBalesSalesMasterModel cPtCottonBalesSalesMasterModel = objectMapper.readValue(cottonBalesSalesMasterData.toString(), CPtCottonBalesSalesMasterModel.class);
            CPtCottonBalesSalesMasterModel updatedModel = iPtCottonBalesSalesMasterRepository.save(cPtCottonBalesSalesMasterModel);
            Integer masterid = updatedModel.getPt_cotton_bales_sales_master_transaction_id();
            List<CPtCottonBalesSalesDetailsModel> cPtCottonBalesSalesDetailsModels = objectMapper.readValue(cottonBalesSalesDetailsData.toString(), new TypeReference<List<CPtCottonBalesSalesDetailsModel>>() {});
            if(!cPtCottonBalesSalesDetailsModels.isEmpty()){
                
                CSmMaterialStockLogModel cSmMaterialStockLogModelAgainstBatchNo = null;
                List<CSmMaterialStockLogModel> stockLogModels = new ArrayList<>();

                if (keyForViewUpdate.equals("approve")) {
                    // Fetching one stock log against that batch_no
                    cSmMaterialStockLogModelAgainstBatchNo = iSmMaterialStockLogRepository.FnGetStockModelAgainstLotNo(cPtCottonBalesSalesDetailsModels.get(0).getBatch_no(), company_id);
                }

                for (CPtCottonBalesSalesDetailsModel detailsModel : cPtCottonBalesSalesDetailsModels) {
                    detailsModel.setPt_cotton_bales_sales_master_transaction_id(masterid);

                    if (keyForViewUpdate.equals("approve") && cSmMaterialStockLogModelAgainstBatchNo != null) {
                        total_sales_return_quantity += detailsModel.getSales_return_quantity();
                        total_sales_return_weight += detailsModel.getSales_return_weight();

                        // Create a new stock log model based on the fetched one
                        CSmMaterialStockLogModel newLogModel = new CSmMaterialStockLogModel(cSmMaterialStockLogModelAgainstBatchNo);

                        newLogModel.setTransaction_no(detailsModel.getSales_return_no());
                        newLogModel.setTransaction_date(new SimpleDateFormat("yyyy-MM-dd").parse(detailsModel.getSales_return_date()));
                        newLogModel.setTransaction_type("Return");
                        newLogModel.setStock_log_transaction_id(0);
                        newLogModel.setBatch_rate(detailsModel.getSales_return_rate());
                        newLogModel.setQuantity(detailsModel.getSales_return_quantity());
                        newLogModel.setWeight(detailsModel.getSales_return_weight());
                        newLogModel.setCreated_by(UserName);
                        newLogModel.setCreated_on(currentDate);

                        stockLogModels.add(newLogModel); // Add to list
                    }

                }

                iPtCottonBalesSalesDetailsRepository.saveAll(cPtCottonBalesSalesDetailsModels);

                if(keyForViewUpdate.equals("approve")){
                    //Updating Closing Balance & Weight, Production  Against batch_no
                    CSmProductMaterialStockNewModel cSmProductMaterialStockNewModelAgainstBatchNo = iSmMaterialStockManagementRepository.fnGetdataBasedonPrmIdBatchNo(product_material_id, cPtCottonBalesSalesDetailsModels.get(0).getBatch_no());

                    cSmProductMaterialStockNewModelAgainstBatchNo.setClosing_balance_quantity(cSmProductMaterialStockNewModelAgainstBatchNo.getClosing_balance_quantity() - total_sales_return_quantity);
                    cSmProductMaterialStockNewModelAgainstBatchNo.setClosing_balance_weight(cSmProductMaterialStockNewModelAgainstBatchNo.getClosing_balance_weight() - total_sales_return_weight);
                    cSmProductMaterialStockNewModelAgainstBatchNo.setSales_quantity(cSmProductMaterialStockNewModelAgainstBatchNo.getSales_quantity() + total_sales_return_quantity);
                    cSmProductMaterialStockNewModelAgainstBatchNo.setSales_weight(cSmProductMaterialStockNewModelAgainstBatchNo.getSales_weight() + total_sales_return_weight);
                    cSmProductMaterialStockNewModelAgainstBatchNo.setModified_by(UserName);
                    cSmProductMaterialStockNewModelAgainstBatchNo.setModified_on(currentDate);
                    //save updated stock & all logs
                    iSmMaterialStockLogRepository.saveAll(stockLogModels);
                    iSmMaterialStockManagementRepository.save(cSmProductMaterialStockNewModelAgainstBatchNo);


                    //Updating GRN Status after gets approved
                    iPtGrnCottonBalesMasterRepository.FnUpdateGRNStatusForMaster(cPtCottonBalesSalesMasterModel.getGoods_receipt_no(), company_id);
                    iPtGrnCottonBalesDetailsRepository.FnUpdateGRNStatusForDetails(cPtCottonBalesSalesMasterModel.getGoods_receipt_no(), company_id);
                }
            }

            if(!cottonBalesSalesPaymentTermsData.isEmpty()){
                List<CPtCottonBalesSalesPaymentTermsModel> cPtCottonBalesSalesPaymentTermsModel = objectMapper.readValue(cottonBalesSalesPaymentTermsData.toString(), new TypeReference<List<CPtCottonBalesSalesPaymentTermsModel>>() {});
                if(!keyForViewUpdate.isEmpty()){
                    //Deleting prev entries
                    iPtCottonBalesPaymentTermsRepository.FnDeleteBalesSalesPaymentTermRecords(masterid, company_id, UserName);
                }

                cPtCottonBalesSalesPaymentTermsModel.forEach(paymenttermmodel ->
                        paymenttermmodel.setPt_cotton_bales_sales_master_transaction_id(masterid)
                );
                iPtCottonBalesPaymentTermsRepository.saveAll(cPtCottonBalesSalesPaymentTermsModel);
            }

            response.put("success", 1);
            response.put("message", keyForViewUpdate.equals("approve") ? "Records Approved Successfully" : keyForViewUpdate.equals("update") ? "Records Updated Successfully" : "Records Added Successfully");
            response.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(Integer companyId, Integer ptCottonBalesSalesMasterTransactionId) {
        Map<String, Object> response = new HashMap<>();
        try{
            CPtCottonBalesSalesMasterViewModel balesMasterModel = iPtCottonBalesSalesMasterViewRepository.FnGetBalesSalesMasterModel(ptCottonBalesSalesMasterTransactionId, companyId);
            List<CPtCottonBalesSalesDetailsViewModel> balesSalesDetailsModels = iPtCottonBalesSalesDetailsViewRepository.FnShowParticularRecordForUpdate(ptCottonBalesSalesMasterTransactionId, companyId);
            List<CPtCottonBalesSalesPaymentTermsModel> balesSalesPaymentTermsModels = iPtCottonBalesPaymentTermsRepository.FnShowParticularRecordForUpdate(ptCottonBalesSalesMasterTransactionId, companyId);

            response.put("CottonBalesSalesMasterRecords", balesMasterModel);
            response.put("CottonBalesSalesDetailsRecords", balesSalesDetailsModels);
            response.put("CottonBalesSalesPaymentTermRecords", balesSalesPaymentTermsModels);
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }

        return response;
    }


}
