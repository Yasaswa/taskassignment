package com.erp.MaterialTransferMaster.Repository;

import com.erp.HtSalaryRules.Model.CHtSalaryRulesModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMaterialTransferDetailsRepository extends JpaRepository<CMaterialTransferDetailsModel, Long> {

    @Query(value = "From CMaterialTransferDetailsModel model where model.is_delete = 0 AND model.material_transfer_master_id = ?1 AND model.company_id = ?2")
    List<CMaterialTransferDetailsModel> FnShowParticularRecord(Long materialTransferMasterId, Long companyId);
}
