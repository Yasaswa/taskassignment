package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassDetailsModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassReturnSummaryModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassReturnSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface IPtMaterialGatePassReturnSummaryRepository extends JpaRepository<CPtMaterialGatePassReturnSummaryModel, Integer> {
    @Query(value = "FROM CPtMaterialGatePassReturnSummaryModel model where model.material_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    List<CPtMaterialGatePassReturnSummaryModel> FnGetParticularMaterialReturnSummary(Integer materialGatePassMasterId, Integer companyId);
    @Query("SELECT material_gate_pass_return_summary_id FROM CPtMaterialGatePassReturnSummaryModel e WHERE e.material_gate_pass_master_id IN :masterIds and e.is_delete = 0")
    Set<Integer> findByMaterialGatePassMasterIdIn(Set<Integer> masterIds);

    @Modifying
    @Transactional
    @Query("UPDATE CPtMaterialGatePassReturnSummaryModel c SET c.is_delete = 1 WHERE c.material_gate_pass_return_summary_id IN :ids")
    void updateMaterialSummaryByIds(Set<Integer> ids);



    @Query(value = "FROM CPtMaterialGatePassReturnSummaryModel model where model.material_return_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    List<CPtMaterialGatePassReturnSummaryModel> FnGetParticularMaterialReturnSummaryDetails(Integer materialGatePassMasterId, Integer companyId);
}


