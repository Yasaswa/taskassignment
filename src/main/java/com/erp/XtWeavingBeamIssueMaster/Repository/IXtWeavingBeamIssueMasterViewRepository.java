package com.erp.XtWeavingBeamIssueMaster.Repository;

import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterModel;
import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IXtWeavingBeamIssueMasterViewRepository extends JpaRepository<CXtWeavingBeamIssueMasterViewModel, Integer> {

    @Query(value = "FROM CXtWeavingBeamIssueMasterViewModel model where model.is_delete =0 and model.company_id=?1 and model.weaving_beam_issue_master_id = ?2")
    CXtWeavingBeamIssueMasterViewModel FnShowParticularRecordForUpdate(int companyId, int weavingBeamIssueMasterId);

}
