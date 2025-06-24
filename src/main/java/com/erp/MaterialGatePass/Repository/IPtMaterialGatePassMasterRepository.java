package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;

public interface IPtMaterialGatePassMasterRepository extends JpaRepository<CPtMaterialGatePassMasterModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_material_gate_pass_master pmgpm " +
            "SET pmgpm.is_delete = 1, " +
            "pmgpm.deleted_by = ?3, " +
            "pmgpm.deleted_on = CURRENT_TIMESTAMP() " +
            "WHERE pmgpm.material_gate_pass_master_id = ?1 " +
            "AND pmgpm.company_id = ?2", nativeQuery = true)
    int deleteMasterRecord(Integer materialGatePassMasterId, Integer companyId, String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_material_gate_pass_master pmgpm  SET pmgpm.return_status = ?2 WHERE pmgpm.gate_pass_no = ?1 AND pmgpm.company_id = ?3", nativeQuery = true)
    void updateReturnStatus(String gatePassNo, String finalReturnStatus,int companyId);
}
