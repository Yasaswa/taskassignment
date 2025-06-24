package com.erp.XtWeavingBeamIssueMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterModel;
import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterViewModel;
import com.erp.XtWeavingBeamIssueMaster.Repository.IXtWeavingBeamIssueMasterRepository;
import com.erp.XtWeavingBeamIssueMaster.Repository.IXtWeavingBeamIssueMasterViewRepository;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingMasterModel;
import com.erp.XtWeavingProductionSizingDetails.Repository.IXtSizingProductionStockDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class CXtWeavingBeamIssueMaterServiceImpl implements IXtWeavingBeamIssueMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IXtWeavingBeamIssueMasterRepository iXtWeavingBeamIssueMasterRepository;

    @Autowired
    IXtWeavingBeamIssueMasterViewRepository iXtWeavingBeamIssueMasterViewRepository;

    @Autowired
    IXtSizingProductionStockDetailsRepository iXtSizingProductionStockDetailsRepository;

    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response  = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        CXtWeavingBeamIssueMasterModel cXtWeavingBeamIssueMasterModels = new CXtWeavingBeamIssueMasterModel();
        try {
            // Get CommonIds
            JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
            int company_id = commonIdsObj.getInt("company_id");
            String keyviewupdate = commonIdsObj.getString("keyviewupdate");
            String process_type = commonIdsObj.getString("process_type");

            String beamStatus = "";
            String loomStatus = "";
            double remaining_length = 0.00;

            // Get master data
            JSONObject masterjson = jsonObject.getJSONObject("BeamIssueMaster");
            cXtWeavingBeamIssueMasterModels = objectMapper.readValue(masterjson.toString(), CXtWeavingBeamIssueMasterModel.class);
            CXtWeavingBeamIssueMasterModel savedBeamIssueMasterModel = iXtWeavingBeamIssueMasterRepository.save(cXtWeavingBeamIssueMasterModels);

            response.put("success", 1);
            response.put("data", savedBeamIssueMasterModel);
            response.put("error", "");
            response.put("message", keyviewupdate.equals("add") ? "Record Added Successfully" : "Record Updated Successfully");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String beamCutDt = null;
            // Handle keyviewupdate actions
            switch (keyviewupdate) {
                case "add":
                case "update":
                        beamStatus = "D";
                        loomStatus = "In-Progress";
                        Date beamIssueDate = formatter.parse(savedBeamIssueMasterModel.getBeam_issue_date());
                        iXtSizingProductionStockDetailsRepository.updateSizingBeamStatus(
                                savedBeamIssueMasterModel.getBeam_no().toString(),
                                savedBeamIssueMasterModel.getSet_no().toString(),
                                company_id,
                                beamIssueDate, beamStatus, remaining_length, beamCutDt
                        );
                        iXtSizingProductionStockDetailsRepository.updateLoomBeams(savedBeamIssueMasterModel.getLoom_no().toString(), loomStatus);
                    break;
                // update the beams in sizing stock
                case "cutbeams":
                    beamStatus = "A";
                    loomStatus = "Available";
                    remaining_length = savedBeamIssueMasterModel.getRemaining_length();
                    beamCutDt = savedBeamIssueMasterModel.getCut_beam_date();
                    Date beamIssueDt = formatter.parse(savedBeamIssueMasterModel.getBeam_issue_date());
                    iXtSizingProductionStockDetailsRepository.updateSizingBeamStatus(
                            savedBeamIssueMasterModel.getBeam_no().toString(),
                            savedBeamIssueMasterModel.getSet_no().toString(),
                            company_id,
                            beamIssueDt, beamStatus, remaining_length, beamCutDt
                    );
                    iXtSizingProductionStockDetailsRepository.updateLoomBeams(savedBeamIssueMasterModel.getLoom_no().toString(), loomStatus);
                    break;
                // update the beams in BeamInwards Master
                case "emptybeam" :
                    beamStatus = "E";
                    loomStatus = "Available";
                    iXtSizingProductionStockDetailsRepository.emtyInHoseBeams(savedBeamIssueMasterModel.getBeam_no().toString() ,beamStatus, company_id);
                    iXtSizingProductionStockDetailsRepository.updateLoomBeams(savedBeamIssueMasterModel.getLoom_no().toString(), loomStatus);
                    break;

                default:
                    // Handle other cases if needed
                    break;
            }


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();

                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }

        return response;

    }

    @Override
    public Map<String, Object> FnDeleteRecord(int weaving_beam_issue_master_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iXtWeavingBeamIssueMasterRepository.FnDeleteRecord(weaving_beam_issue_master_id, deleted_by);
            responce.put("success", 1);
        } catch (Exception e) {
            responce.put("success", 0);
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int weaving_beam_issue_master_id) {
        Map<String, Object> responce = new HashMap<>();
        CXtWeavingBeamIssueMasterViewModel cXtWeavingBeamIssueMasterViewModel = null;
        try {
            cXtWeavingBeamIssueMasterViewModel = iXtWeavingBeamIssueMasterViewRepository.FnShowParticularRecordForUpdate(company_id, weaving_beam_issue_master_id);
            responce.put("success", "1");
            responce.put("data", cXtWeavingBeamIssueMasterViewModel);
            responce.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }
}
