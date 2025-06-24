package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialReturnGatePassMasterModel;
import com.erp.MaterialGatePass.Model.CPtMaterialReturnGatePassMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPtMaterialReturnGatePassMasterRepository extends JpaRepository<CPtMaterialReturnGatePassMasterModel,Integer> {

    @Query(value = "FROM CPtMaterialReturnGatePassMasterViewModel model where model.material_return_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    CPtMaterialReturnGatePassMasterViewModel FnGetParticularRecord(Integer materialReturnGatePassMasterId, Integer companyId);



}
