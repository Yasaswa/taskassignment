package com.erp.PayRollSetting.Controller;
import com.erp.PayRollSetting.Model.CPayRollModel;
import com.erp.PayRollSetting.Service.IPayRollSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/payrollSettings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPayRollSettingController {

    @Autowired
    private IPayRollSettingService ipayrollSettingService; // The service layer interface


    @GetMapping("/FnShowAllReportRecords")
    public List<Map<String, Object>>FnShowAllReportRecords() {
        return ipayrollSettingService.FnShowAllReportRecords();  // Call the service method
    }

    @PutMapping("/FnUpdateRecord/{id}")
    public ResponseEntity<CPayRollModel> FnUpdateRecord(@PathVariable Long id, @RequestBody CPayRollModel updatedModel) {
        CPayRollModel updated = ipayrollSettingService.FnUpdateRecord(id, updatedModel);
        return ResponseEntity.ok(updated);
    }

}


