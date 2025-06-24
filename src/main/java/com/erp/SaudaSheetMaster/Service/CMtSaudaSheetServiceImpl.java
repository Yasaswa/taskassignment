package com.erp.SaudaSheetMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReturnsDetails.Model.*;
import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetMasterModel;
import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetMasterViewModel;
import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetPaymentTermsModel;
import com.erp.SaudaSheetMaster.Repository.IMtSaudaSheetMasterRepository;
import com.erp.SaudaSheetMaster.Repository.IMtSaudaSheetMasterViewRepository;
import com.erp.SaudaSheetMaster.Repository.IMtSaudaSheetPaymentTermRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@Service
public class CMtSaudaSheetServiceImpl implements IMtSaudaSheetService {
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IMtSaudaSheetMasterRepository iMtSaudaSheetMasterRepository;
    @Autowired
    IMtSaudaSheetPaymentTermRepository iMtSaudaSheetPaymentTermRepository;
    @Autowired
    IMtSaudaSheetMasterViewRepository iMtSaudaSheetMasterViewRepository;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

        Map<String, Object> responce = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject SaudasheetMasterData = jsonObject.getJSONObject("SaudaSheetMasterData");
        JSONArray paymentTermsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");

        int company_id = SaudasheetMasterData.getInt("company_id");
        int sauda_sheet_master_id = SaudasheetMasterData.getInt("sauda_sheet_master_id");
        String sauda_sheet_no = SaudasheetMasterData.getString("sauda_sheet_no");
        String sauda_sheet_date = SaudasheetMasterData.getString("sauda_sheet_date");
        try {
            CMtSaudaSheetMasterModel cMtSaudaSheetMasterModel = objectMapper
                    .readValue(SaudasheetMasterData.toString(), CMtSaudaSheetMasterModel.class);

            CMtSaudaSheetMasterModel respSaudaSheetMasterModel = iMtSaudaSheetMasterRepository.save(cMtSaudaSheetMasterModel);
            if (!paymentTermsArray.isEmpty()) {

                iMtSaudaSheetPaymentTermRepository.updatePaymentTermsStatus(sauda_sheet_master_id,company_id);

                List<CMtSaudaSheetPaymentTermsModel> tradingOrderPaymentTerms = objectMapper.readValue(
                        paymentTermsArray.toString(),
                        new TypeReference<List<CMtSaudaSheetPaymentTermsModel>>() {
                        });
                tradingOrderPaymentTerms.forEach(paymentTerm -> {
                    paymentTerm.setSauda_sheet_master_id(respSaudaSheetMasterModel.getSauda_sheet_master_id());
                });
                iMtSaudaSheetPaymentTermRepository.saveAll(tradingOrderPaymentTerms);

            }

            responce.put("success", 0);
            responce.put("data", "");
            responce.put("message", sauda_sheet_master_id == 0 ? "Record added successfully!..." : "Record updated successfully!...");

        }catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SaudaSheetMaster/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SaudaSheetMaster/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            responce.put("message", sauda_sheet_master_id == 0 ? "Record added successfully!..." : "Record updated successfully!...");

        }
        return responce;

    }
    @Override
    public Map<String, Object> FnDeleteRecord(int sauda_sheet_master_id, int company_id,
                                              String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iMtSaudaSheetMasterRepository.FnDeleteSaudaSheetMasterRecord(sauda_sheet_master_id, company_id,
                    deleted_by);

            iMtSaudaSheetPaymentTermRepository.FnDeleteSaudaSheetPaymentTermRecord(sauda_sheet_master_id, company_id,
                    deleted_by);

            responce.put("success", 1);
            responce.put("data", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;

    }
    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int sauda_sheet_master_id, int company_id) {
        Map<String, Object> responce = new HashMap<>();

        try {

            CMtSaudaSheetMasterViewModel cMtSaudaSheetMasterViewModel =
                    iMtSaudaSheetMasterViewRepository.FnShowParticularRecordForUpdate(sauda_sheet_master_id, company_id);

            List<CMtSaudaSheetPaymentTermsModel> cMtSaudaSheetPaymentTermsModels =
                    iMtSaudaSheetPaymentTermRepository.FnShowSaudaSheetPaymentTerms(sauda_sheet_master_id, company_id);

            responce.put("success", 1);
            responce.put("saudaSheetMasterData", cMtSaudaSheetMasterViewModel);
            responce.put("saudaSheetPaymentTerms", cMtSaudaSheetPaymentTermsModels);


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }
}
