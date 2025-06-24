package com.erp.MaterialGatePass.Repository;

import com.erp.MaterialGatePass.Model.CPtMaterialReturnGatePassDetailsModel;
import com.erp.MaterialGatePass.Model.CPtMaterialReturnGatePassDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtMaterialReturnGatePassDetailsRepository extends JpaRepository<CPtMaterialReturnGatePassDetailsModel,Integer> {
    @Query(value = "FROM CPtMaterialReturnGatePassDetailsViewModel model where model.material_return_gate_pass_master_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
    List<CPtMaterialReturnGatePassDetailsViewModel> FnGetParticularRecord(Integer materialReturnGatePassMasterId, Integer companyId);

    @Query(value = "FROM CPtMaterialReturnGatePassDetailsModel model where model.material_gate_pass_details_id IN ?1 and model.company_id = ?2 and model.is_delete = 0")
    List<CPtMaterialReturnGatePassDetailsModel> getAllDetailsById(List<Integer> detailmodelsIds, int companyId);
}
