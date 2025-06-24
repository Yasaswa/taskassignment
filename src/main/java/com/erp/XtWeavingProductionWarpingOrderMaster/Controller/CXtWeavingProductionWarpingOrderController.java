package com.erp.XtWeavingProductionWarpingOrderMaster.Controller;

import com.erp.XtWeavingProductionOrderMaster.Repository.IXtWeavingProductionDetailsRepository;
import com.erp.XtWeavingProductionWarpingOrderMaster.Repository.IxtWeavingProductionWarpingOrderRepository;
import com.erp.XtWeavingProductionWarpingOrderMaster.Service.CxtWeavingProductionWarpingOrderMasterServiceImpl;
import com.erp.XtWeavingProductionWarpingOrderMaster.Service.IxtWeavingProductionWarpingOrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/XtWeavingProductionWarpingOrder")
public class CXtWeavingProductionWarpingOrderController {
    @Autowired
    private CxtWeavingProductionWarpingOrderMasterServiceImpl cxtWeavingProductionWarpingOrderMasterServiceImpl;

    @Autowired
    private IxtWeavingProductionWarpingOrderMasterService ixtWeavingProductionWarpingOrderMasterService;

    @Autowired
    private IxtWeavingProductionWarpingOrderRepository ixtWeavingProductionWarpingOrderRepository;

    @GetMapping("/FnShowParticularRecordForUpdate/{set_no}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable String set_no, @PathVariable int company_id)  {
        Map<String, Object> responce =  ixtWeavingProductionWarpingOrderMasterService.FnShowParticularRecordForUpdate(set_no, company_id);
        return responce;
    }



}
