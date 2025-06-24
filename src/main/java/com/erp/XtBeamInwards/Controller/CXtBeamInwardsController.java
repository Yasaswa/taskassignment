package com.erp.XtBeamInwards.Controller;

import com.erp.XtBeamInwards.Model.CXtBeamInwardsModel;
import com.erp.XtBeamInwards.Model.CXtBeamInwardsViewModel;
import com.erp.XtBeamInwards.Repository.IXtBeamInwardsRepository;
import com.erp.XtBeamInwards.Repository.IXtBeamInwardsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/XtBeamInwards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXtBeamInwardsController {

    @Autowired
    IXtBeamInwardsRepository iXtBeamInwardsRepository;

    @Autowired
    IXtBeamInwardsViewRepository iXtBeamInwardsViewRepository;


    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("BeamInwardsData") JSONObject jsonObject) {

        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
            int companyId = companyIds.getInt("company_id");
            String keyForViewUpdate = companyIds.getString("keyForViewUpdate");
            JSONArray beamInwardsDetails = jsonObject.getJSONArray("BeamInwardsDetails");


            List<CXtBeamInwardsModel> beamInwardsModels = objectMapper.readValue(
                    beamInwardsDetails.toString(),
                    new TypeReference<List<CXtBeamInwardsModel>>() {
                    }
            );


            List<Integer> beamInwardsId = beamInwardsModels.stream()
                    .map(CXtBeamInwardsModel::getBeam_inwards_id) // Extract the ID using the getter
                    .collect(Collectors.toList()); // Collect the IDs into a list

            if (keyForViewUpdate.equals("update")) {
                iXtBeamInwardsRepository.deleteRecords(beamInwardsId, companyId, beamInwardsModels.get(0).getModified_by(), beamInwardsModels.get(0).getCustomer_id(),beamInwardsModels.get(0).getBeam_inwards_date());
            }
            beamInwardsModels = iXtBeamInwardsRepository.saveAll(beamInwardsModels);

            response.put("success", 1);
            response.put("data", beamInwardsModels);
            response.put("error", "");
            if (keyForViewUpdate.equals("Add")) {
                response.put("message", "Record Added Successfully");
            } else {
                response.put("message", "Record Updated Successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }
        return response;
    }

    @GetMapping("/FnShowParticularRecord/{customer_id}/{company_id}/{beam_inwards_date}")
    public Map<String, Object> FnShowParticularRecord(@PathVariable Integer customer_id, @PathVariable Integer company_id, @PathVariable String beam_inwards_date)
            throws SQLException {
        Map<String, Object> response = new HashMap<>();
        try {
            List<CXtBeamInwardsViewModel> beamInwardsModels = iXtBeamInwardsViewRepository.FnShowParticularRecord(customer_id, company_id, beam_inwards_date);

            response.put("BeamInwardsData", beamInwardsModels);
            response.put("success", "1");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }


    @PostMapping("/FnDeleteBeamInwardsData")
    public Map<String, Object> FnDeleteBeamInwardsData(@RequestParam("BeamInwardsData") JSONObject jsonObject) {

        Map<String, Object> response = new HashMap<>();
        try {
            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
            String userName = companyIds.getString("USERNAME");
            int companyId = companyIds.getInt("company_id");
            int customerId = companyIds.getInt("customer_id");

            JSONArray beamIds = jsonObject.getJSONArray("beamIds");
            List<Integer> reqBeamIds = new ArrayList<>();

            // Correct way to extract integers from JSONArray
            for (int i = 0; i < beamIds.length(); i++) {
                reqBeamIds.add(beamIds.getInt(i));  // Ensure proper conversion
            }
            if (reqBeamIds != null && !reqBeamIds.isEmpty()) {
                iXtBeamInwardsRepository.FnDeleteBeamInwardsData(reqBeamIds, companyId, userName, customerId);
            }

            response.put("success", 1);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }
}
