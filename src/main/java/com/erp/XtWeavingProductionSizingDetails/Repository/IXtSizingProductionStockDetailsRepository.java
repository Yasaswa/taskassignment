package com.erp.XtWeavingProductionSizingDetails.Repository;

import com.erp.XtWeavingProductionSizingDetails.Model.CxtSizingProductionStockDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IXtSizingProductionStockDetailsRepository extends JpaRepository<CxtSizingProductionStockDetailModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update CxtSizingProductionStockDetailModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1 AND company_id = ?2")
    void updateSizingProductionStockDetails(int weavingProductionSizingMasterId, int companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CxtSizingProductionStockDetailModel model " +
            "SET model.dispatch_date = ?1, model.sized_beam_status = 'D' " +
            "WHERE model.set_no = ?2 AND model.beam_no IN ?3 AND model.company_id = ?4 AND model.is_delete = 0")
    void updateSizingProdBeamStatus(Date dispatch_date, String set_no, List<String> beamNos, Integer company_id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_sizing_production_stock_details xspsd " +
            "SET xspsd.sized_beam_status = ?5, " +
            "    xspsd.dispatch_date = ?4, " +
            "    xspsd.remaining_length = ?6, " +
            "    xspsd.cut_beam_date = CASE WHEN ?7 IS NOT NULL THEN ?7 ELSE xspsd.cut_beam_date END " +
            "WHERE xspsd.beam_no = ?1 AND xspsd.set_no = ?2 AND xspsd.company_id = ?3 AND xspsd.is_delete = 0", nativeQuery = true)
    void updateSizingBeamStatus(Object beam_no, Object set_no, Integer company_id, Date beam_issue_date, String beamStatus, double remaining_length, String beamCutDt);

//For updating Beam Status from Beam Issue SLip for Add or Update

    @Modifying
    @Transactional
    @Query(value = "UPDATE am_properties ap SET ap.property_group = :loomStatus WHERE ap.property_value = :propertyValue AND ap.is_delete = 0 And properties_master_name = 'LoomMachineNo' ", nativeQuery = true)
    void updateLoomBeams(@Param("propertyValue") String propertyValue, @Param("loomStatus") String loomStatus);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_beam_inwards_table xt SET xt.beam_status = :beamStatus WHERE xt.beam_inwards_id = :beam_inwards_id AND xt.customer_id = 0 and xt.is_delete = 0 and xt.company_id = :companyId", nativeQuery = true)
    void emtyInHoseBeams(@Param("beam_inwards_id") String beam_no, @Param("beamStatus") String beamStatus, @Param("companyId") int companyId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE CxtSizingProductionStockDetailModel model " +
            "SET model.dispatch_date = ?1, model.sized_beam_status = 'A' " +
            "WHERE model.set_no = ?2 AND model.beam_no IN ?3 AND model.company_id = ?4 AND model.is_delete = 0")
    void updateSizingProdBeamStatusAfterApproved(Date dispatchChallanDate, String setNo, List<String> approvedBeamIds, int companyId);


}
