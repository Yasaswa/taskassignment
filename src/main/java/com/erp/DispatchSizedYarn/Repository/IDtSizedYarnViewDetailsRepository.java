package com.erp.DispatchSizedYarn.Repository;

import com.erp.DispatchSizedYarn.Model.CDtSizedYarnDetailsViewModel;
import com.erp.DispatchSizedYarn.Model.CDtSizedYarnMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IDtSizedYarnViewDetailsRepository extends JpaRepository<CDtSizedYarnDetailsViewModel, Integer> {


    @Transactional
    @Query(value = "From CDtSizedYarnDetailsViewModel model where model.is_delete = 0 AND model.company_id = ?2 AND model.dispatch_challan_sized_yarn_id = ?1")
    List<CDtSizedYarnDetailsViewModel> FnGetDispatchSizedYarnDetailsData(int dispatchChallanSizedYarnId, int companyId);
}
