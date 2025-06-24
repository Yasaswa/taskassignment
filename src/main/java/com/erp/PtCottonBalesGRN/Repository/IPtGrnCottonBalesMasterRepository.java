package com.erp.PtCottonBalesGRN.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesMasterModel;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Map;


public interface IPtGrnCottonBalesMasterRepository extends JpaRepository<CPtGrnCottonBalesMasterModel, Integer> {

    @Query(value = "FROM CPtGrnCottonBalesMasterModel model where model.is_delete =0 and model.grn_cotton_bales_master_transaction_id = ?1 and model.company_id = ?2")
    CPtGrnCottonBalesMasterModel FnShowParticularRecordForUpdate(int grn_cotton_bales_master_transaction_id, int company_id);

    @Query(value = "SELECT * FROM pt_grn_cotton_bales_master WHERE goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
    CPtGrnCottonBalesMasterModel getExistingRecord(String goodsReceiptNo, Integer goodsReceiptVersion, String financialYear, int companyId);

    @Modifying
    @Transactional
    @Query(value = "update pt_grn_cotton_bales_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where grn_cotton_bales_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteGRNCottonBalesMaster(int goodsReceiptMasterTransactionId, String userName);

    @Query(value = "SELECT * FROM ptv_grn_cotton_bales_summary WHERE goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
    Map<String, Object> FnShowGRNCottonBalesMasterRecord(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);



    //Update GRN status after Cotton Bales Sales
    @Modifying
    @Transactional
    @Query("UPDATE CPtGrnCottonBalesMasterModel m SET m.goods_receipt_status = 'RE' WHERE m.goods_receipt_no = :grnNo AND m.is_delete = 0 AND m.company_id = :companyId")
    void FnUpdateGRNStatusForMaster(@Param("grnNo") String goods_receipt_no, @Param("companyId") Integer company_id);

}
