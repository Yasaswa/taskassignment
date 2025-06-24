package com.erp.XtWeavingBeamIssueMaster.Repository;

import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface IXtWeavingBeamIssueMasterRepository extends JpaRepository<CXtWeavingBeamIssueMasterModel, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update xt_weaving_beam_issue_master set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where weaving_beam_issue_master_id=?1", nativeQuery = true)
    void FnDeleteRecord(int weavingBeamIssueMasterId, String deletedBy);

    @Query(value = "SELECT wpo.t_ends, wpo.sort_no ,xspsd.sizing_length, spdp.product_parameter_value, spdp.product_parameter_name, (SELECT xwpsd.yarn_count FROM xt_weaving_production_sizing_details xwpsd WHERE xwpsd.weaving_production_set_no = ?2 LIMIT 1) AS yarn_count FROM xt_warping_production_order wpo left join xt_sizing_production_stock_details xspsd on xspsd.set_no = wpo.set_no and xspsd.beam_no = ?3 LEFT JOIN sm_product_dynamic_parameters spdp ON spdp.product_id = wpo.product_material_id AND (spdp.product_parameter_name LIKE '%REED [RD]%' OR spdp.product_parameter_name LIKE '%PICK [PK]%' OR spdp.product_parameter_name LIKE '%RS [RS]%') AND spdp.is_delete = 0 WHERE wpo.set_no = ?2 AND wpo.company_id = ?1", nativeQuery = true)
    ArrayList<Map<String, Object>> FnGetBeamIssueSlipMasterData(Integer company_id, String set_no, String beam_no);

    @Query(value = "SELECT spf.product_fg_code, spdp.product_parameter_value, spdp.product_parameter_name FROM sm_product_fg spf LEFT JOIN sm_product_dynamic_parameters spdp ON spdp.product_id = spf.product_fg_id AND (spdp.product_parameter_name LIKE '%REED [RD]%' OR spdp.product_parameter_name LIKE '%PICK [PK]%' OR spdp.product_parameter_name LIKE '%RS [RS]%' OR spdp.product_parameter_name LIKE '%TOTAL ENDS [TE]%') AND spdp.is_delete = 0 WHERE spf.product_fg_code = ?2 AND spf.company_id = ?1 AND spf.is_delete = 0", nativeQuery = true)
    ArrayList<Map<String, Object>> FnGetBeamIssueSlipMasterDataBySortNo(Integer company_id, String sort_no);


}
