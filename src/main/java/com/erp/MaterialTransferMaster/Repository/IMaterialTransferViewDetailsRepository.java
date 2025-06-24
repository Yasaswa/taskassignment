package com.erp.MaterialTransferMaster.Repository;

import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMaterialTransferViewDetailsRepository extends JpaRepository<CMaterialTransferDetailsViewModel, Long> {

    @Query(value = "From CMaterialTransferDetailsViewModel model where model.is_delete = 0 AND model.transfer_no = ?1 AND model.company_id = ?2")
    List<CMaterialTransferDetailsViewModel> FnShowParticularRecord(String transfer_no, Integer companyId);
}
