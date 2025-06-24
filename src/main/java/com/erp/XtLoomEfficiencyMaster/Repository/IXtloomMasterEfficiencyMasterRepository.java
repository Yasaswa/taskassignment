package com.erp.XtLoomEfficiencyMaster.Repository;

import com.erp.XtLoomEfficiencyMaster.Model.CXtLoomEfficiencyMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IXtloomMasterEfficiencyMasterRepository extends JpaRepository<CXtLoomEfficiencyMasterModel, Integer> {

    @Query("FROM CXtLoomEfficiencyMasterModel model WHERE model.is_delete = 0 AND model.loom_efficiency_master_id = ?1 AND model.company_id = ?2")
    Optional<CXtLoomEfficiencyMasterModel> FnShowParticularRecordForUpdate(int loom_efficiency_master_id, int company_id);

    @Query("FROM CXtLoomEfficiencyMasterModel model WHERE model.is_delete = 0 AND model.weave_design_name = ?1 AND model.weave_design_id = ?2 AND model.company_id = ?3")
    Optional<CXtLoomEfficiencyMasterModel> checkIfWeaveDesignExist(String weave_design_name, int weave_design_id, int company_id);


}
