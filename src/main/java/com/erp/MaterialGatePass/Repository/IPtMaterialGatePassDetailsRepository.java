package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPtMaterialGatePassDetailsRepository extends JpaRepository<CPtMaterialGatePassDetailsModel, Integer> {




    //Get all detail id's
    @Query(value = "Select material_gate_pass_details_id from pt_material_gate_pass_details pmgpd where pmgpd.is_delete = 0 and pmgpd.company_id = ?2 and pmgpd.material_gate_pass_master_id = ?1", nativeQuery = true)
    List<Integer> FnGetAllDetailIds(Integer material_gate_pass_master_id, Integer company_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_material_gate_pass_details pmgpd " +
            "SET pmgpd.is_delete = 1, pmgpd.deleted_by = ?4, pmgpd.deleted_on = CURRENT_TIMESTAMP() " +
            "WHERE pmgpd.material_gate_pass_details_id NOT IN (?1) " +
            "AND pmgpd.company_id = ?2 " +
            "AND pmgpd.material_gate_pass_master_id = ?3",
            nativeQuery = true)
    int deleteDetailModelForUpdate(List<Integer> detailmodelsIds, int companyId, Integer masterId, String modifiedBy);


    @Modifying
    @Transactional
    @Query("UPDATE CPtMaterialGatePassDetailsModel SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP, deleted_by = ?3 WHERE material_gate_pass_master_id = ?2 AND company_id = ?1")
    int deleteDetails(Integer companyId, Integer masterId, String deletedBy);


    @Modifying
    @Query(value = "UPDATE pt_material_gate_pass_details d " +
            "SET d.inward_quantity = :inwardQuantity, " +  // <-- Directly set, no addition
            "    d.pending_inward_quantity = d.outward_quantity - :inwardQuantity, " + // <-- Corrected
            "    d.return_item_status = CASE WHEN (d.outward_quantity - :inwardQuantity) > 0 THEN 'I' ELSE 'R' END " +
            "WHERE d.material_gate_pass_details_id = :id",
            nativeQuery = true)
    void updateInwardPendingReturnStatus(@Param("id") Integer id, @Param("inwardQuantity") Double inwardQuantity);

@Query(value = "SELECT * FROM pt_material_gate_pass_details pmgpd " +
        "WHERE pmgpd.is_delete = 0 " +
        "AND pmgpd.company_id = ?2 " +
        "AND pmgpd.gate_pass_no = ?1",
        nativeQuery = true)
List<CPtMaterialGatePassDetailsModel> getAllGatePassDetails(String gatePassNo, Integer company_id);

    @Query(
            value = "SELECT godown_id AS godownId, godown_section_id AS godownSectionId, godown_section_beans_id AS godownSectionBeansId " +
                    "FROM sm_product_rm " +
                    "WHERE product_rm_id = :productMaterialId  AND is_delete = 0 ",
            nativeQuery = true
    )
    Optional<Map<String, Object>> findGodownInfoByProductMaterialId(
            @Param("productMaterialId") String productMaterialId
    );

}
