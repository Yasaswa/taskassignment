package com.erp.DispatchSizedYarn.Repository;

import com.erp.DispatchSizedYarn.Model.CDtSizedYarnDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IDtSizedYarnDetailsRepository extends JpaRepository<CDtSizedYarnDetailsModel, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE CDtSizedYarnDetailsModel model SET model.is_delete = 1 WHERE model.dispatch_challan_no = ?1 AND model.company_id = ?2")
    void updateBeamDetailsStatus(String dispatchChallanNo, int company_id);

}
