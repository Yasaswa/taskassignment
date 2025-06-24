package com.erp.XtBeamInwards.Repository;

import com.erp.XtBeamInwards.Model.CXtBeamInwardsModel;
import com.erp.XtBeamInwards.Model.CXtBeamInwardsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXtBeamInwardsViewRepository extends JpaRepository<CXtBeamInwardsViewModel,Integer> {

            @Query(
                    nativeQuery = true,
                    value = "SELECT * FROM xtv_beam_inwards_table WHERE customer_id = ?1 AND company_id = ?2 AND is_delete = 0 AND beam_inwards_date = ?3 GROUP BY beam_inwards_id ORDER BY beam_inwards_id DESC"
            )
            List<CXtBeamInwardsViewModel> FnShowParticularRecord(Integer customerId, Integer companyId, String beam_inwards_date);

}
