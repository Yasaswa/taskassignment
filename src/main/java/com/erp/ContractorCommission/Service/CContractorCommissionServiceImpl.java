package com.erp.ContractorCommission.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.ContractorCommission.Model.CContractorCommissionModel;
import com.erp.ContractorCommission.Repository.IContractorCommissionRepository;

import com.erp.JobType.Repository.IJobTypeRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@Service
public class CContractorCommissionServiceImpl implements IContractorCommissionService {


    @Autowired
    IContractorCommissionRepository iContractorCommissionRepository;

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Override
    public Map<String, Object> FnAddUpdateRecord(CContractorCommissionModel cContractorCommissionModel) {
        Map<String, Object> resp = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        CContractorCommissionModel MyModel = null;
        int company_id = cContractorCommissionModel.getCompany_id();

        try {
            CContractorCommissionModel resultsJobTypeName = null;
            Optional<CContractorCommissionModel> option = iContractorCommissionRepository.findById(cContractorCommissionModel.getCommission_rate_id());

            if (option.isPresent()) {
//                resultsJobTypeName = iContractorCommissionRepository.getCheckForUpdate(cContractorCommissionModel.getJob_type_name(),
//                        cContractorCommissionModel.getJob_type_id());
//
//                if (resultsJobTypeName != null) {
//                    resp.put("success", 0);
//                    resp.put("error", "Job Type Name or Short Name already exists!");
//                    return resp;
//                }else {
                    cContractorCommissionModel.setModified_on(new Date());
                    CContractorCommissionModel mymodel = iContractorCommissionRepository.save(cContractorCommissionModel);
                    String json = objectMapper.writeValueAsString(mymodel);
                    resp.put("success", "1");
                    resp.put("data", json);
                    resp.put("error", "");
                    resp.put("message", "Record updated succesfully!...");
                    return resp;
//                }
            }else{


//                resultsJobTypeName = iContractorCommissionRepository.getCheck(cContractorCommissionModel.getJob_type_name());
//
//                if (resultsJobTypeName != null) {
//                    resp.put("success", 0);
//                    resp.put("error", "Job Type Name or Short Name is already exist!");
//                    return resp;
//                } else {

                    CContractorCommissionModel contractorCommissionModel = iContractorCommissionRepository.save(cContractorCommissionModel);
                    String json = objectMapper.writeValueAsString(contractorCommissionModel);
                    resp.put("success", "1");
                    resp.put("data", json);
                    resp.put("error", "");
                    resp.put("message", "Record added succesfully!...");

                    System.out.println(" Job Type saved succesfully!..");
                    return resp;
//                }
            }



        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/jobType/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/jobType/FnAddUpdateRecord", 0, e.getMessage());
            resp.put("success", "0");
            resp.put("data", "");
            resp.put("error", e.getMessage());

            return resp;
        }
        return resp;

    }

    @Override
    public Map<String, Object> FnDeleteRecord(int commission_rate_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iContractorCommissionRepository.FnDeleteRecord(commission_rate_id, deleted_by);
            responce.put("success", "1");
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", "");
        }
        return responce;
    }


    @Override
    public Map<String, Object> FnShowParticularRecord(int commission_rate_id) {
        Map<String, Object> resp = new HashMap();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            CContractorCommissionModel json = iContractorCommissionRepository.FnShowParticularRecord(commission_rate_id);
            String json1 = objectMapper.writeValueAsString(json);

            resp.put("success", "1");
            resp.put("data", json1);
            resp.put("error", "");

            return resp;

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

//    @Override
//    public Map<String, Object> FnShowAllReportRecords(Pageable pageable) {
//        Map<String, Object> resp = new HashMap<>();
//        try {
//
//            Map<String, Object> data = iContractorCommissionRepository.FnShowAllReportRecords();
//            resp.put("data", data);
//            resp.put("success", "1");
//            resp.put("error", "");
//
//
//        } catch (DataAccessException e) {
//            if (e.getRootCause() instanceof SQLException) {
//                SQLException sqlEx = (SQLException) e.getRootCause();
//                System.out.println(sqlEx.getMessage());
//                resp.put("success", "0");
//                resp.put("error", sqlEx.getMessage());
//
//            }
//        } catch (Exception e) {
//            resp.put("success", "0");
//            resp.put("error", "");
//            e.printStackTrace();
//        }
//
//        return resp;
//    }


}
