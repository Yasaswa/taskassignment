package com.erp.XtLoomEfficiencyMaster.Controller;

import com.erp.XtLoomEfficiencyMaster.Model.CXtLoomEfficiencyMasterModel;
import com.erp.XtLoomEfficiencyMaster.Repository.IXtloomMasterEfficiencyMasterRepository;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/LoomEfficiency")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCtLoomEfficiencyController {

    @Autowired
    IXtloomMasterEfficiencyMasterRepository iXtloomMasterEfficiencyMasterRepository;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CXtLoomEfficiencyMasterModel cXtLoomEfficiencyMasterModel) {
        Map<String, Object> response = new HashMap<>();
        try {
            String weave_design_name = cXtLoomEfficiencyMasterModel.getWeave_design_name();
            Integer weave_design_id = cXtLoomEfficiencyMasterModel.getWeave_design_id();
            Integer company_id = cXtLoomEfficiencyMasterModel.getCompany_id();

            if (cXtLoomEfficiencyMasterModel.getLoom_efficiency_master_id() == 0) {
                Optional<CXtLoomEfficiencyMasterModel> xtLoomEfficiencyMasterModel =
                        iXtloomMasterEfficiencyMasterRepository.checkIfWeaveDesignExist(weave_design_name, weave_design_id, company_id);

                if (xtLoomEfficiencyMasterModel.isPresent()) {
                    response.put("success", "0");
                    response.put("error", String.format("%s already Exists...! Please Select Another Design", weave_design_name));
                    return response;
                }
            }
            iXtloomMasterEfficiencyMasterRepository.save(cXtLoomEfficiencyMasterModel);


            response.put("success", "1");
            response.put("data", cXtLoomEfficiencyMasterModel.getLoom_efficiency_master_id() > 0 ? "Record Updated Successfully" : "Record Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

    @DeleteMapping("/FnDeleteRecord/{loom_efficiency_master_id}/{company_id}/{deleted_by}")
    public Object FnDeleteRecord(@PathVariable int loom_efficiency_master_id, @PathVariable int company_id, @PathVariable String deleted_by) {
        Map<String, Object> response = new HashMap<>();

        Optional<CXtLoomEfficiencyMasterModel> xtLoomEfficiencyMasterModel = iXtloomMasterEfficiencyMasterRepository.FnShowParticularRecordForUpdate(loom_efficiency_master_id, company_id);
        xtLoomEfficiencyMasterModel.ifPresent(model -> {
            model.setIs_delete(true); // Soft delete
            model.setDeleted_by(deleted_by); // Who deleted it
            model.setDeleted_on(new Date()); // Current timestamp

            // Save the updated entity
            iXtloomMasterEfficiencyMasterRepository.save(model);
        });
        response.put("message", "Record Deleted Successfully");
        return response;
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{loom_efficiency_master_id}/{company_id}")
    public CXtLoomEfficiencyMasterModel FnShowParticularRecordForUpdate(
            @PathVariable int loom_efficiency_master_id,
            @PathVariable int company_id) {

        return iXtloomMasterEfficiencyMasterRepository.FnShowParticularRecordForUpdate(loom_efficiency_master_id, company_id).orElse(null);
    }
}
