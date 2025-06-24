package com.erp.CottonBalesMixingChart.Respository;

import com.erp.CottonBalesMixingChart.Model.CCottonBalesMixingChartModel;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ICottonBalesMixingChartRepository extends JpaRepository<CCottonBalesMixingChartModel, Integer> {


    //Function to Update is_delete = 0 while user remove rows in Update
    @Modifying
    @Query(value = "UPDATE pt_mixing_chart_cotton_bales mcn SET mcn.is_delete = 1, mcn.deleted_by = ?3, mcn.deleted_on = CURRENT_TIMESTAMP() WHERE mcn.mixing_chart_cotton_bales_transaction_id NOT IN (?2) AND mcn.mixing_chart_no = ?1", nativeQuery = true)
    void FnGetUpdateDeletedRows( String mixing_chart_no , List<Integer> mixingChartCottonBalesTransactionIds, String deletedBy);


    //Getting Stock Against Lot No
    @Query(value = "SELECT spmsn.supplier_batch_no, (spmsn.closing_balance_quantity - COALESCE(SUM(pmccb.issue_quantity), 0)) AS closing_balance_quantity, (spmsn.closing_balance_weight - COALESCE(SUM(pmccb.issue_weight), 0)) AS closing_balance_weight, pqtcb.* FROM pt_quality_testing_cotton_bales pqtcb LEFT JOIN sm_product_material_stock_new spmsn ON pqtcb.batch_no = spmsn.batch_no AND pqtcb.company_id = spmsn.company_id AND spmsn.closing_balance_quantity > 0 AND spmsn.is_delete = 0 LEFT JOIN pt_mixing_chart_cotton_bales pmccb ON pmccb.batch_no = pqtcb.batch_no AND pmccb.is_delete = 0 AND pmccb.issue_flag = 0 WHERE pqtcb.is_delete = 0 AND pqtcb.batch_no = ? AND pqtcb.company_id = ? GROUP BY pqtcb.batch_no, spmsn.supplier_batch_no, spmsn.closing_balance_quantity, spmsn.closing_balance_weight, pqtcb.company_id", nativeQuery = true)
    List<Map<String, Object>> FnGetStockDataAgainstLot(String lotNo, int companyId);

    //Fetching the last mixing chart number
    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING_INDEX(mixing_chart_no, '/', -1) AS UNSIGNED)) + 1, 1) FROM pt_mixing_chart_cotton_bales WHERE company_id = ?2 AND plant_id = ?1 AND MONTH(mixing_chart_date) = ?3 AND YEAR(mixing_chart_date) = ?4 AND is_delete = 0", nativeQuery = true)
    Integer FnGetNextMixingCartNo(int plantId, int companyId, int month, int year);


    //Fetching data based on chart mixing no
    @Query(value = "Select * from pt_mixing_chart_cotton_bales pmccb where pmccb.mixing_chart_no = ?1 AND pmccb.company_id = ?2 AND pmccb.is_delete = 0", nativeQuery = true)
    List<CCottonBalesMixingChartModel> FnFetchData(String chartMixingNo, Integer company_id);


    //Last Saved Lot No's
    @Query(value = "SELECT batch_no FROM pt_mixing_chart_cotton_bales WHERE mixing_chart_no = :mixing_chart_no AND is_delete = 0 AND company_id = :company_id", nativeQuery = true)
    List<String> FnGetLatestsavedLotNos(@Param("company_id") Integer company_id, @Param("mixing_chart_no") String mixing_chart_no);

    @Query(value = "SELECT (spmsn.closing_balance_quantity - COALESCE(issue_totals.total_issue_quantity, 0)) AS remaining_quantity, " +
            "(spmsn.closing_balance_weight - COALESCE(issue_totals.total_issue_weight, 0)) AS remaining_weight, " +
            "pmccb.* " +
            "FROM pt_quality_testing_cotton_bales pqtcb " +
            "LEFT JOIN sm_product_material_stock_new spmsn ON pqtcb.batch_no = spmsn.batch_no " +
            "AND pqtcb.company_id = spmsn.company_id " +
            "AND spmsn.is_delete = 0 " +
            "LEFT JOIN (SELECT batch_no, SUM(issue_quantity) AS total_issue_quantity, SUM(issue_weight) AS total_issue_weight " +
            "FROM pt_mixing_chart_cotton_bales " +
            "WHERE is_delete = 0 AND issue_flag = 0 " +
            "GROUP BY batch_no) AS issue_totals ON issue_totals.batch_no = pqtcb.batch_no " +
            "LEFT JOIN pt_mixing_chart_cotton_bales pmccb ON pmccb.batch_no = pqtcb.batch_no " +
            "AND pmccb.is_delete = 0 AND pmccb.issue_flag = 0 " +
            "AND (:mixing_chart_no IS NULL OR :mixing_chart_no = '' OR pmccb.mixing_chart_no = :mixing_chart_no) " +
            "WHERE pqtcb.is_delete = 0 " +
            "AND pqtcb.batch_no IN (:lastsavedLotNos) " +
            "AND pqtcb.company_id = :companyId " +
            "GROUP BY spmsn.supplier_batch_no, spmsn.batch_no, spmsn.closing_balance_quantity, spmsn.closing_balance_weight, pqtcb.batch_no, pqtcb.company_id;",
            nativeQuery = true)
    List<Map<String, Object>> FnGetStockDataAgainstLotNosForUpdate(@Param("lastsavedLotNos") List<String> lastsavedLotNos,
                                                          @Param("companyId") int companyId,
                                                          @Param("mixing_chart_no") String mixing_chart_no);

    @Query("SELECT spmsn FROM CSmProductMaterialStockNewModel spmsn " +
            "WHERE spmsn.batch_no IN (:lastsavedLotNos) " +
            "AND spmsn.company_id = :companyId " +
            "AND spmsn.is_delete = 0")
    List<CSmProductMaterialStockNewModel> FnGetStockDetailsAgainstLotNos(
            @Param("lastsavedLotNos") List<String> lastsavedLotNos,
            @Param("companyId") Integer companyId);

    @Query(value = "FROM CSmMaterialStockLogModel smsl " +
            "WHERE smsl.batch_no IN (:lastsavedLotNos) " +
            "AND smsl.company_id = :companyId " +
            "AND smsl.is_delete = 0 " +
            "ORDER BY smsl.stock_log_transaction_id ASC")
    List<CSmMaterialStockLogModel> FnGetStockLogDetailsAgainstLotNos(
            @Param("lastsavedLotNos") List<String> lastsavedLotNos,
            @Param("companyId") Integer companyId);






    @Query(value = "select pmccb.batch_no from pt_mixing_chart_cotton_bales pmccb where pmccb.mixing_chart_no = ?2 and pmccb.is_delete = 0 AND pmccb.company_id = ?1",nativeQuery = true)
    List<String> FnGetLotNosByMixingChart(Integer companyId, String mixingChartNo);

    @Modifying
    @Transactional
    @Query(value = "update pt_mixing_chart_cotton_bales set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where mixing_chart_no=?1", nativeQuery = true)
    void FnDeleteRecord(String mixing_chart_no, String deleted_by);

    @Query(value = "SELECT mixing_chart_no FROM pt_mixing_chart_cotton_bales WHERE is_delete = 0 AND company_id = :company_id AND plant_id = :plant_id AND mixing_chart_date <= :mixing_chart_date ORDER BY mixing_chart_date DESC, created_on DESC LIMIT 1;", nativeQuery = true)
    String FnGetLatestMixingChartNo(@Param("company_id") Integer company_id, @Param("plant_id") Integer plant_id, @Param("mixing_chart_date") String mixing_chart_date);


    @Query(value = "SELECT " +
            "    pmccb.batch_no, " +
            "    COALESCE(SUM(pmccb.issue_quantity), 0) AS issue_quantity, " +
            "    COALESCE(SUM(pmccb.issue_weight), 0) AS issue_weight, " +
            "    MAX(pmccb.mixing_chart_cotton_bales_transaction_id) AS latest_transaction_id " +
            "FROM pt_mixing_chart_cotton_bales pmccb " +
            "WHERE pmccb.batch_no IN (:lotNo) " +
            "    AND pmccb.issue_flag = 0 " +
            "    AND pmccb.is_delete = 0 " +
            "    AND pmccb.company_id = :company_id " +
            "GROUP BY pmccb.batch_no " +
            "ORDER BY latest_transaction_id DESC", nativeQuery = true)
    List<Map<String, Object>> FnGetDataFromMixingChart(@Param("lotNo") List<String> lotNo,
                                                       @Param("company_id") Integer company_id);


    @Query(value = "SELECT DISTINCT pmccb.batch_no " +
            "FROM pt_mixing_chart_cotton_bales pmccb " +
            "WHERE pmccb.issue_flag = 0 " +
            "AND pmccb.is_delete = 0 " +
            "AND pmccb.company_id = ?1 " +
            "ORDER BY pmccb.mixing_chart_cotton_bales_transaction_id ASC", nativeQuery = true)
    List<String> FnGetLotNos(Integer company_id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_mixing_chart_cotton_bales pmccb SET pmccb.issue_flag = 1 WHERE pmccb.batch_no IN ?1 AND pmccb.is_delete = 0 AND pmccb.issue_flag = 0 AND pmccb.company_id = ?2",nativeQuery = true)
    void FnUpdateIssueFlag(List<String> lot_nos, Integer company_id);



    @Query(value = "SELECT (spmsn.closing_balance_quantity - COALESCE(issue_totals.total_issue_quantity, 0)) AS remaining_quantity, " +
            "(spmsn.closing_balance_weight - COALESCE(issue_totals.total_issue_weight, 0)) AS remaining_weight, " +
            "spmsn.supplier_batch_no , pqtcb.* " +
            "FROM pt_quality_testing_cotton_bales pqtcb " +
            "LEFT JOIN sm_product_material_stock_new spmsn ON pqtcb.batch_no = spmsn.batch_no " +
            "AND pqtcb.company_id = spmsn.company_id " +
            "AND spmsn.is_delete = 0 " +
            "LEFT JOIN (SELECT batch_no, SUM(issue_quantity) AS total_issue_quantity, SUM(issue_weight) AS total_issue_weight " +
            "FROM pt_mixing_chart_cotton_bales " +
            "WHERE is_delete = 0 AND issue_flag = 0 " +
            "GROUP BY batch_no) AS issue_totals ON issue_totals.batch_no = pqtcb.batch_no " +
            "LEFT JOIN pt_mixing_chart_cotton_bales pmccb ON pmccb.batch_no = pqtcb.batch_no " +
            "AND pmccb.is_delete = 0 AND pmccb.issue_flag = 0 " +
            "WHERE pqtcb.is_delete = 0 " +
            "AND pqtcb.batch_no IN (:lastsavedLotNos) " +
            "AND pqtcb.company_id = :companyId " +
            "GROUP BY spmsn.supplier_batch_no, spmsn.batch_no, spmsn.closing_balance_quantity, spmsn.closing_balance_weight, pqtcb.batch_no, pqtcb.company_id;",
            nativeQuery = true)
    List<Map<String, Object>> FnGetStockDataAgainstLotNosForAdd(@Param("lastsavedLotNos") List<String> lastsavedLotNos,
                                                          @Param("companyId") int companyId);


    @Query("FROM CCottonBalesMixingChartModel model WHERE model.batch_no IN ?1 AND model.is_delete = 0 AND model.issue_flag = 0 AND model.company_id = ?2")
    List<CCottonBalesMixingChartModel> FnGetMixingChartNosForStockLog(List<String> lotnos, Integer company_id);


    //Mixing Chart Models For Bales GRN Update Functionality
    @Query("FROM CCottonBalesMixingChartModel model WHERE model.batch_no IN ?1 AND model.is_delete = 0 AND model.company_id = ?2")
    List<CCottonBalesMixingChartModel> FnGetMixingChartNosForBalesGRN(List<String> lotnos, Integer company_id);

}
