package com.erp.MaterialTransferMaster.Controller;


import com.erp.HtSalaryRules.Model.CHtSalaryRulesModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsViewModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferMasterModel;
import com.erp.MaterialTransferMaster.Repository.IMaterialTransferDetailsRepository;
import com.erp.MaterialTransferMaster.Repository.IMaterialTransferMasterRepository;
import com.erp.MaterialTransferMaster.Repository.IMaterialTransferViewDetailsRepository;
import com.erp.MaterialTransferMaster.Service.IMaterialTransferDetailsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materialTransfer")
public class CMaterialTransferDetailsController {
    @Autowired
    private IMaterialTransferDetailsService iMaterialTransferDetailsService;

    @Autowired
    private IMaterialTransferMasterRepository iMaterialTransferMasterRepository;

    @Autowired
    private IMaterialTransferDetailsRepository iMaterialTransferDetailsRepository;

    @Autowired
    private IMaterialTransferViewDetailsRepository iMaterialTransferViewDetailsRepository;

    @GetMapping("/FnShowParticularRecord/{company_id}")
    public Map<String, Object> FnShowParticularRecord( @RequestParam("transfer_no") String transfer_no, @PathVariable Integer company_id) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        try {
            // Fetching data from repository
            CMaterialTransferMasterModel cMaterialTransferMasterModel = iMaterialTransferMasterRepository.FnShowParticularRecord(transfer_no, company_id);
            List<CMaterialTransferDetailsViewModel> transferdetails = iMaterialTransferViewDetailsRepository.FnShowParticularRecord(transfer_no, company_id);

            // Preparing success response
            response.put("success", 1);
            response.put("TransferDetailsData", transferdetails);
            response.put("TransferMasterData", cMaterialTransferMasterModel);
            response.put("error", "");
        } catch (Exception e) {
            e.printStackTrace();
            // Preparing failure response in case of error
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }


    @PostMapping("/FnAddUpdateRecord/{company_id}")
    public ResponseEntity<ResponseEntity<Map<String, Object>>> FnAddUpdateTransferDetail(@RequestParam("PtMaterialTransferData") JSONObject jsonObject, @PathVariable Integer company_id ) {
        return ResponseEntity.ok(iMaterialTransferDetailsService.FnAddUpdateTransferDetail(jsonObject, company_id));
    }

}
