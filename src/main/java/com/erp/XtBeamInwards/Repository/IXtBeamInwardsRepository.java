package com.erp.XtBeamInwards.Repository;

import com.erp.XtBeamInwards.Model.CXtBeamInwardsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IXtBeamInwardsRepository extends JpaRepository<CXtBeamInwardsModel, Integer> {


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE xt_beam_inwards_table xbit SET xbit.is_delete = 1, xbit.deleted_on = CURRENT_TIMESTAMP(), xbit.deleted_by = :modifiedBy WHERE xbit.beam_inwards_id NOT IN (:beamInwardIds) AND xbit.company_id = :companyId AND xbit.customer_id = :customerId AND xbit.beam_inwards_date = :beamInwardsDate AND xbit.beam_status = 'E'")
    void deleteRecords(@Param("beamInwardIds") List<Integer> beamInwardIds,
                       @Param("companyId") Integer companyId,
                       @Param("modifiedBy") String modifiedBy,
                       @Param("customerId") Integer customerId,
                       @Param("beamInwardsDate") String beamInwardsDate);


    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE xt_beam_inwards_table xbit " +
                    "SET xbit.customer_order_no = :customer_order_no " +
                    "WHERE xbit.beam_inwards_id IN (:beamNos) AND xbit.company_id = :company_id")
    void updateCustOrderNo(@Param("customer_order_no") String customer_order_no, @Param("beamNos") List<String> beamNos, @Param("company_id") Integer company_id);

    @Query(value = "select beam_inwards_id, beam_no, beam_inward_type,tare_weight from xt_beam_inwards_table xbit where xbit.customer_id = ?1 AND xbit.company_id = ?2 AND xbit.beam_status = 'E' AND xbit.is_delete = 0", nativeQuery = true)
    ArrayList<Map<String, Object>> FnGetBeams(Object customer_id, int company_id);

    @Modifying
    @Transactional
    @Query(value = "Update xt_beam_inwards_table xbit set is_delete = 1 , xbit.deleted_on = CURRENT_TIMESTAMP(), xbit.deleted_by = :modifiedBy WHERE xbit.beam_inwards_id IN (:beamInwardIds) AND xbit.company_id = :companyId AND xbit.customer_id = :customerId ",nativeQuery = true)
    void FnDeleteBeamInwardsData(@Param("beamInwardIds") List<Integer> beamInwardIds,
                       @Param("companyId") Integer companyId,
                       @Param("modifiedBy") String modifiedBy,
                       @Param("customerId") Integer customerId);
}
