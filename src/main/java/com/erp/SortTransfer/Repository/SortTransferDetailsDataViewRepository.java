package com.erp.SortTransfer.Repository;

import com.erp.PtCottonBalesSales.Model.CPtCottonBalesSalesDetailsViewModel;
import com.erp.SortTransfer.Model.CSortTransferDetailsViewModel;
import com.erp.SortTransfer.Model.CSortTransferMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SortTransferDetailsDataViewRepository extends JpaRepository<CSortTransferDetailsViewModel, Long> {


    @Query(value = "FROM CSortTransferDetailsViewModel model where model.is_delete = 0 and model.sort_transfer_master_id = ?1 and model.company_id = ?2")
    List<CSortTransferDetailsViewModel> FnShowParticularRecordForUpdate(Integer sort_transfer_master_id, Integer companyId);

}
