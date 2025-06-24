package com.erp.XtWarpingBottomReturn.Repository;

import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomReturnModel;
import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomReturnViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtWarpingBottomReturnRepository extends JpaRepository<CXtWarpingBottomReturnModel, Integer> {
    @Query(value = "FROM CXtWarpingBottomReturnViewModel model where model.is_delete = 0 and model.bottom_return_no = ?1 and model.bottom_return_item_status = 'P' and model.company_id = ?2")
    List<CXtWarpingBottomReturnViewModel> FnShowParticularRecordForUpdate(String bottomReturnNo, int companyId);

    @Query(value = "FROM CXtWarpingBottomReturnViewModel model where model.is_delete = 0 and model.bottom_return_no = ?1 and model.company_id = ?2")
    List<CXtWarpingBottomReturnViewModel> FnShowParticularRecordForUpdateview(String bottomReturnNo, int companyId);
    @Modifying
    @Transactional
    @Query(value = "update xtv_warping_bottom_return_details set is_delete = 1, deleted_by= ?2, deleted_on = CURRENT_TIMESTAMP() where bottom_return_no=?1", nativeQuery = true)
    void FnDeleteRecord(String bottomReturnNo, String deletedBy, int companyId);
    @Query(value = "FROM CXtWarpingBottomReturnModel model where model.is_delete = 0 and model.bottom_return_no = ?1 and model.company_id = ?2")
    List<CXtWarpingBottomReturnModel> findByBottomReturnNo(String bottomReturnNo, int companyId);
    @Modifying
    @Transactional
    @Query(value = "update xtv_warping_bottom_return_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where warping_bottom_return_details_id IN ?1", nativeQuery = true)
    void updateReturnRecord(List<Integer> warpingReturnDetailsIds, int companyId);
}
