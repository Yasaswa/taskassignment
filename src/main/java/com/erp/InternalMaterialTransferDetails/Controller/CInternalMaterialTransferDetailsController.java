package com.erp.InternalMaterialTransferDetails.Controller;

import com.erp.InternalMaterialTransferDetails.Repository.IInternalMaterialTransferDetailsRepository;
import com.erp.InternalMaterialTransferDetails.Repository.IInternalMaterialTransferMasterRepository;
import com.erp.InternalMaterialTransferDetails.Service.IInternalMaterialTransferDetailsService;
import com.erp.PtMaterialLoanMaster.Service.IPtMaterialLoanMasterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/PtInternalMaterialTransferDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CInternalMaterialTransferDetailsController {

    @Autowired
    IInternalMaterialTransferDetailsService iInternalMaterialTransferDetailsService;

    @PostMapping("/FnAddUpdateRecord/{addOrIssued}")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("InternalMaterialTransferDetails") JSONObject jsonObject, @PathVariable String addOrIssued) {
        return iInternalMaterialTransferDetailsService.FnAddUpdateRecord(jsonObject, addOrIssued);

    }

    @GetMapping("/FnShowAllDetailsAndMastermodelRecords/{financial_year}/{company_id}")
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
            @RequestParam("inter_material_transfer_no") String inter_material_transfer_no,
            @PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
        return iInternalMaterialTransferDetailsService.FnShowAllDetailsandMastermodelRecords(inter_material_transfer_no,
                financial_year , company_id );
    }
    @DeleteMapping("/FnDeleteRecord/{inter_material_transfer_master_id}/{UserName}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int inter_material_transfer_master_id, @PathVariable String UserName) {
        return iInternalMaterialTransferDetailsService.FnDeleteRecord(inter_material_transfer_master_id, UserName);

    }

}
