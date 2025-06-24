package com.erp.SortTransfer.Repository;


import com.erp.SortTransfer.Model.CSortTransferMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SortTransferMasterDataRepository extends JpaRepository<CSortTransferMasterModel, Long> {

    CSortTransferMasterModel save(CSortTransferMasterModel sortTransfer);



    @Modifying
    @Transactional
    @Query(value = "update xt_weaving_production_inspection_details set inspection_production_set_no = ?2, sort_no= ?3, style = ?4 where roll_no=?1", nativeQuery = true)
    void ChangeRollDetails(Integer rollNo, String toSetNo, String toSortNo, String toStyle);
}
