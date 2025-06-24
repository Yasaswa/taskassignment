package com.erp.XtProductionStandardLossMaster.Service;


import com.erp.Common.ExceptionHandling.ExceptionHandlingClass;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnDetailsViewModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterViewModel;
import com.erp.XtProductionStandardLossMaster.Model.CXtProductionStandardLossMasterModel;
import com.erp.XtProductionStandardLossMaster.Repository.IXtProductionLossMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CXtProductionStandardLossService {

    private final ExceptionHandlingClass exceptionHandlingClass;

    @Autowired
    IXtProductionLossMasterRepository iXtProductionLossMasterRepository;

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public ResponseEntity<?> FnAddUpdateRecord(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
        int companyId = commonIdsObj.getInt("company_id");
        try {
            JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
            //CONVERT Production Standard Loss DATA FROM JSON
            CXtProductionStandardLossMasterModel productionStandardLossMasterData = objectMapper.readValue(masterjson.toString(),
                    CXtProductionStandardLossMasterModel.class);

            CXtProductionStandardLossMasterModel responceProductionStandardLossMasterData = iXtProductionLossMasterRepository.save(productionStandardLossMasterData);

            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse(productionStandardLossMasterData.getProduction_standard_losses_id() == 0 ? "Record Added successfully." : "Record Updated successfully.");
            successResponse.put("responseProductionStandardLossMaster", responceProductionStandardLossMasterData);
            return ResponseEntity.ok(successResponse);
        } catch (
                DataAccessException e) {
            return exceptionHandlingClass.handleException(e, companyId, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/XtProductionStandardLoss/FnAddUpdateRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, companyId, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/XtProductionStandardLoss/FnAddUpdateRecord");
        }
    }

    public ResponseEntity<?> FnShowStandardLossDetails(Integer productionStandardLossesId, Integer companyId) {
        try {
            CXtProductionStandardLossMasterModel StandardLossMasterRecord = iXtProductionLossMasterRepository.FnShowStandardLossDetailsRecord(productionStandardLossesId, companyId);
            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record fetched successfully");
            successResponse.put("StandardLossMasterRecord", StandardLossMasterRecord);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, companyId, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/XtProductionStandardLoss/FnShowStandardLossDetails");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, companyId, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/XtProductionStandardLoss/FnShowStandardLossDetails");
        }
    }

    public ResponseEntity<?> FnDeleteRecord(Integer productionStandardLossesId, Integer companyId, String deletedBy) {

        try {
            iXtProductionLossMasterRepository.FnDeleteRawMaterialReturnMasterRecord(productionStandardLossesId, companyId, deletedBy);

            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record deleted successfully");
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, companyId, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/XtProductionStandardLoss/FnDeleteRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, companyId, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/XtProductionStandardLoss/FnDeleteRecord");
        }
    }
}
