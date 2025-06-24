package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassMasterModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPtMaterialGatePassMasterViewRepository extends JpaRepository<CPtMaterialGatePassMasterViewModel, Integer> {

    @Query(value = "FROM CPtMaterialGatePassMasterViewModel model where model.material_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    CPtMaterialGatePassMasterViewModel FnGetParticularRecord(Integer material_gate_pass_master_id, Integer company_id);


    @Query(value = "FROM CPtMaterialGatePassMasterViewModel model where model.material_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    CPtMaterialGatePassMasterViewModel FnGetParticularInwardRecord(Integer material_gate_pass_master_id, Integer company_id);


}
