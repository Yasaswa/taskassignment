package com.erp.XtWarpingBottomReturn.Controller;


import com.erp.XtWarpingBottomReturn.Service.IXtWarpingBottomReturnService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/XtWarpingBottomReturnDetails")
public class CxtWarpingBottomReturnController {

    @Autowired
    IXtWarpingBottomReturnService iXtWarpingBottomReturnService;

    @PostMapping("/FnAddUpdateRecord/{acceptFlag}")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("bottomReturnDetailsData") JSONObject jsonObject, @PathVariable String acceptFlag) {
        return iXtWarpingBottomReturnService.FnAddUpdateRecord(jsonObject,acceptFlag);

    }
//    @DeleteMapping("/FnDeleteRecord/{company_id}/{deleted_by}")
//    public ResponseEntity<?> FnDeleteRecord(@RequestParam("bottom_return_no") String bottom_return_no, @PathVariable int company_id,
//                                            @PathVariable String deleted_by) {
//        return iXtWarpingBottomReturnService.FnDeleteRecord(bottom_return_no, company_id, deleted_by);
//
//    }
    @DeleteMapping("/FnDeleteRecord/{deleted_by}/{company_id}")
    public Map<String, Object> FnDeleteRecord(@RequestParam("bottom_return_no") String bottom_return_no, @PathVariable String deleted_by, @PathVariable int company_id) {
        return iXtWarpingBottomReturnService.FnDeleteRecord(bottom_return_no, deleted_by, company_id);
    }

    @GetMapping("/FnShowRawMaterialReturnDetails/{company_id}/{keyForViewUpdate}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@RequestParam("bottom_return_no") String bottom_return_no, @PathVariable int company_id, @PathVariable String keyForViewUpdate)  {
        Map<String, Object> responce =  iXtWarpingBottomReturnService.FnShowParticularRecordForUpdate(bottom_return_no, company_id,keyForViewUpdate);
        return responce;
    }
}

