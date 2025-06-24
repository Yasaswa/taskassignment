package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassDetailsModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtMaterialGatePassDetailsViewRepository extends JpaRepository<CPtMaterialGatePassDetailsViewModel, Integer> {


    @Query(value = "FROM CPtMaterialGatePassDetailsViewModel model where model.material_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    List<CPtMaterialGatePassDetailsViewModel> FnGetParticularRecord(Integer material_gate_pass_master_id, Integer company_id);

    @Query(value = "FROM CPtMaterialGatePassDetailsViewModel model where model.material_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0 and model.return_item_status IN ('I','P')")
    List<CPtMaterialGatePassDetailsViewModel> FnGetParticularInwardRecord(Integer material_gate_pass_master_id, Integer company_id);



}
