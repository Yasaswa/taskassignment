package com.erp.XtProductionStandardLossMaster.Controller;


import com.erp.XtProductionStandardLossMaster.Service.CXtProductionStandardLossService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/XtProductionStandardLoss")
public class CXtStandardLossMasterController {

    private final CXtProductionStandardLossService cXtProductionStandardLossService;

    @PostMapping("/FnAddUpdateRecord")
    public ResponseEntity<?> FnAddUpdateRecord(@RequestParam("XtProductionStandardLossData") JSONObject jsonObject) {
        return cXtProductionStandardLossService.FnAddUpdateRecord(jsonObject);
    }

    @DeleteMapping("/FnDeleteRecord/{production_standard_losses_id}/{company_id}/{deleted_by}")
    public ResponseEntity<?> FnDeleteRecord(@PathVariable Integer production_standard_losses_id, @PathVariable Integer company_id,
                                            @PathVariable String deleted_by) {
        return cXtProductionStandardLossService.FnDeleteRecord(production_standard_losses_id, company_id, deleted_by);
    }

    @GetMapping("/FnShowStandardLossDetails/{production_standard_losses_id}/{company_id}")
    public ResponseEntity<?> FnShowStandardLossDetails(@PathVariable("production_standard_losses_id") Integer production_standard_losses_id, @PathVariable Integer company_id)
            throws SQLException {
        return cXtProductionStandardLossService.FnShowStandardLossDetails(production_standard_losses_id, company_id);
    }
}











