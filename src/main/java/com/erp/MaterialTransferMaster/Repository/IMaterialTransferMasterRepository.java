package com.erp.MaterialTransferMaster.Repository;

import com.erp.MaterialTransferMaster.Model.CMaterialTransferMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMaterialTransferMasterRepository extends JpaRepository<CMaterialTransferMasterModel, Long> {
  CMaterialTransferMasterModel save(CMaterialTransferMasterModel existingTransfer);

  @Query(value = "From CMaterialTransferMasterModel model where model.is_delete = 0 AND model.transfer_no = ?1 AND model.company_id = ?2")
  CMaterialTransferMasterModel FnShowParticularRecord(String transfer_no, Integer companyId);
}
