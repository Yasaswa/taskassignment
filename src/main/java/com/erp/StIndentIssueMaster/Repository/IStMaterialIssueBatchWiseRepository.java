package com.erp.StIndentIssueMaster.Repository;

import com.erp.StIndentIssueMaster.Model.CStMaterialIssueBatchWiseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IStMaterialIssueBatchWiseRepository extends JpaRepository<CStMaterialIssueBatchWiseModel , Integer> {


    @Query(value = "SELECT st.* , rm.product_rm_code as product_material_code, rm.product_rm_name as product_material_name  FROM st_material_issue_batch_wise st left join sm_product_rm rm on rm.product_rm_id = st.product_material_id and rm.is_delete = 0  WHERE st.transaction_no = ?1 and st.financial_year = ?2 and st.company_id= ?3 and st.is_delete = 0", nativeQuery = true)
    List<Map<String, Object>> FnShowMaterialIssueBatchWiseRecord(String issue_no, String financial_year, int company_id);

    @Modifying
    @Transactional
    @Query(value = "update st_material_issue_batch_wise set is_delete = 1 ,deleted_by =?3, deleted_on = CURRENT_TIMESTAMP() where transaction_no = ?1 and company_id = ?2", nativeQuery = true)
    void FnDeleteIssueBatchDetailsRecord(String issueNo, int companyId, String deletedBy);


    @Query(value = "SELECT st.* , rm.product_rm_code as product_material_code, rm.product_rm_name as product_material_name  FROM st_material_issue_batch_wise st left join sm_product_rm rm on rm.product_rm_id = st.product_material_id and rm.is_delete = 0  WHERE st.transaction_no = ?1 and st.company_id= ?2 and st.is_delete = 0", nativeQuery = true)
    List<Map<String, Object>> FnShowMaterialReturnBatchWiseRecord(String issue_no,  int company_id);


    @Query(value = "From CStMaterialIssueBatchWiseModel model where model.set_no = :set_no and model.company_id = :company_id and model.is_delete = 0 and model.issue_return_status = 'R'")
    List<CStMaterialIssueBatchWiseModel> FnGetModalDataAgainstSetNo(@Param("set_no") String set_no,@Param("company_id") Integer company_id);

}
