package com.erp.SortTransfer.Repository;


import com.erp.SortTransfer.Model.CSortTransferMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SortTransferMasterDataViewRepository  extends JpaRepository<CSortTransferMasterViewModel, Long> {

    @Query(value = "From CSortTransferMasterViewModel model Where model.sort_transfer_master_id = ?1 AND model.is_delete = 0 AND model.company_id = ?2")
    CSortTransferMasterViewModel FnGetSortTransferMasterModel(Integer sort_transfer_master_id, Integer company_id);

}
