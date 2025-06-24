package com.erp.PtCottonBalesGRN.Controller;


import com.erp.PtCottonBalesGRN.Repository.IPtGrnCottonBalesDetailsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.erp.PtCottonBalesGRN.Service.IPtGrnCottonBalesMasterService;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/PtGrnCottonBalesMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtGrnCottonBalesMasterController {

    @Autowired
    IPtGrnCottonBalesMasterService iPtGrnCottonBalesMasterService;

    @Autowired
    IPtGrnCottonBalesDetailsRepository iPtGrnCottonBalesDetailsRepository;

    @PostMapping("/FnAddUpdateRecord/{isApprove}")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodReceiptNoteData") JSONObject jsonObject, @PathVariable boolean isApprove) {
        return iPtGrnCottonBalesMasterService.FnAddUpdateRecord(jsonObject, isApprove);

    }
//	@PostMapping("/FnAddUpdateRecord/{isApprove}")
//	public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodReceiptNoteData") JSONObject jsonObject, @PathVariable boolean isApprove) {
//		Map<String, Object> responce = iPtGoodsReceiptDetailsService.FnAddUpdateRecord(jsonObject, isApprove);
//		return responce;
//	}

    @DeleteMapping("/FnDeleteRecord/{grn_cotton_bales_master_transaction_id}/{UserName}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int grn_cotton_bales_master_transaction_id, @PathVariable String UserName) {
        return iPtGrnCottonBalesMasterService.FnDeleteRecord(grn_cotton_bales_master_transaction_id, UserName);

    }

//    @GetMapping("/FnShowParticularRecords/{goods_receipt_details_transaction_id}/{company_id}")
//    public Page<CPtGoodsReceiptDetailsViewModel> FnShowParticularRecord(
//            @PathVariable int goods_receipt_details_transaction_id, Pageable pageable, @PathVariable int company_id) {
//        Page<CPtGoodsReceiptDetailsViewModel> cptGoodsReceiptDetailsViewModel = iPtGoodsReceiptDetailsService
//                .FnShowParticularRecord(goods_receipt_details_transaction_id, pageable, company_id);
//        return cptGoodsReceiptDetailsViewModel;
//
//    }

//	@GetMapping("/FnShowParticularRecords/{grn_cotton_bales_master_transaction_id}/{company_id}")
//	public Page<CPtGrnCottonBalesMasterViewModel> FnShowParticularRecord(@PathVariable int grn_cotton_bales_master_transaction_id, Pageable pageable, @PathVariable int company_id){
//		return iPtGrnCottonBalesMasterService.FnShowParticularRecord(grn_cotton_bales_master_transaction_id, pageable, company_id);
//
//	}

    @GetMapping("/FnShowAllDetailsAndMastermodelRecords/{goods_receipt_version}/{financial_year}/{company_id}")
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
            @RequestParam("goods_receipt_no") String goods_receipt_no, @PathVariable int goods_receipt_version,
            @PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
        return iPtGrnCottonBalesMasterService.FnShowAllDetailsandMastermodelRecords(goods_receipt_no,
                goods_receipt_version, financial_year, company_id);
    }
    @PostMapping("/FnShowPurchaseOrderDetailsRecords")
    public Map<String, Object> FnShowPurchaseOrderDetailsRecords(@RequestParam("purchaseOrderNos") JSONObject purchaseOrderNo) {
        return iPtGrnCottonBalesMasterService.FnShowPurchaseOrderDetailsRecords(purchaseOrderNo);
    }


    @GetMapping("/FnGetLastLotNo/{company_id}")
    public String FnGetLastLotNo(@PathVariable int company_id) {
        String lastLotNo = iPtGrnCottonBalesDetailsRepository.FnGetLastLotNo(company_id);
        return (lastLotNo == null || lastLotNo.trim().isEmpty()) ? "0000" : lastLotNo;
    }
}
