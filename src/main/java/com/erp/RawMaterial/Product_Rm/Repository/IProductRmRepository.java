package com.erp.RawMaterial.Product_Rm.Repository;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IProductRmRepository extends JpaRepository<CProductRmModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1", nativeQuery = true)
    Object FnDeleteRecord(int product_rm_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_rm WHERE product_rm_name = ?1")
//	CProductRmModel getCheck(String product_rm_name);

    //	@Query(value = "SELECT * FROM sm_product_rm WHERE product_rm_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//    CProductRmModel FnShowParticularProductRmmaterialRecords(String product_rm_id, int company_id);
    @Query(value = "SELECT * FROM sm_product_rm WHERE product_rm_id = ?1  and is_delete = 0", nativeQuery = true)
    CProductRmModel FnShowParticularProductRmmaterialRecords(String product_rm_id);

    //	@Query(value = "SELECT * FROM sm_product_rm_technical WHERE product_rm_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//	Map<String, Object> FnShowParticularProductRmmaterialTechnicalRecords(String product_rm_id, int company_id);
    @Query(value = "SELECT * FROM sm_product_rm_technical WHERE product_rm_id = ?1  and is_delete = 0", nativeQuery = true)
    Map<String, Object> FnShowParticularProductRmmaterialTechnicalRecords(String product_rm_id);

    //	@Query(value = "SELECT * FROM sm_product_rm_commercial WHERE product_rm_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//	Map<String, Object> FnShowParticularProductRmmaterialcommercialRecords(String product_rm_id, int company_id);
    @Query(value = "SELECT * FROM sm_product_rm_commercial WHERE product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    Map<String, Object> FnShowParticularProductRmmaterialcommercialRecords(String product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteProductRmDetailsRecords(String product_rm_id, String deleted_by);

    @Modifying
    @Transactional
    @Query(value = "update CProductRmModel model set model.product_rm_qr_code = ?2 where model.product_rm_id = ?1 and model.is_delete = 0")
    void updateQrCodePath(String product_rm_id, Object dataValue);

    @Query(value = "SELECT * FROM sm_product_rm WHERE product_rm_id = ?1 ", nativeQuery = true)
    Optional<CProductRmModel> findByProduct_rm_id(String productRmId);

//    @Query(nativeQuery = true, value = "SELECT * FROM sm_product_rm WHERE (REPLACE(product_rm_name, ' ', '') = REPLACE('?1', ' ', '') OR (?2 IS NOT NULL AND product_rm_short_name = ?2)) and company_id = ?3 and is_delete = 0 order by product_rm_id Desc limit 1")
//    CProductRmModel getCheck(String product_rm_name, String product_rm_short_name, int company_id);

    @Query(nativeQuery = true, value = "SELECT * FROM sm_product_rm WHERE (REPLACE(product_rm_name, ' ', '') = REPLACE(?1, ' ', '') OR (?2 IS NOT NULL AND product_rm_short_name = ?2)) AND company_id = ?3 AND is_delete = 0 ORDER BY product_rm_id DESC LIMIT 1")
    CProductRmModel getCheck(String product_rm_name, String product_rm_short_name, int company_id);

    @Query(value = "SELECT * FROM smv_product_rm_fg_sr  where is_delete =0 and product_material_code = ?1 and company_id =?2", nativeQuery = true)
    Map<String, Object> CheckIfCodeExist(String productRmCode, int companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_rm SET product_rm_hsn_sac_code_id = ?2 WHERE product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void updateHsnCodeForProductMaterialIds(String productMaterialId, Integer productMaterialHsnCodeId);

    @Query(value = "SELECT product_rm_id FROM sm_product_rm WHERE product_rm_id = ?1 AND product_rm_hsn_sac_code_id = 1 AND is_delete = 0", nativeQuery = true)
    String getHsnCodeForProductMaterialIds(String productMaterialId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_rm SET godown_section_beans_id = ?2 WHERE product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void updateMaterialLocation(String productMaterialId, Integer godownSectionBeansId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_stock_tracking SET stock_quantity = ?1 WHERE product_material_id = ?2 and is_delete = 0", nativeQuery = true)
    void updateMaterialStockTrack(Double stockQuantity, String productRmId);


    @Query(value = "SELECT " +
            "COALESCE(SUM(closing_balance_quantity), 0) AS current_stock_qty, " +
            "COALESCE(SUM(closing_balance_weight), 0) AS current_stock_weight " +
            "FROM sm_product_rm_stock_summary " +
            "WHERE product_rm_id = :productRmId " +
            "AND company_id = :company_id ",
            nativeQuery = true)
    Tuple findMaterialQuantityAndWeightById(String productRmId, int company_id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_rm_stock_summary SET godown_id = ?1, godown_section_id = ?2 , godown_section_beans_id = ?3   WHERE product_rm_id = ?4 and company_id = ?5 and is_delete = 0", nativeQuery = true)
    void updateMaterialStockSummary(Integer godownId, Integer godownSectionId, Integer godownSectionBeansId, String productRmId, Integer companyId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_rm_stock_details SET godown_id = ?1, godown_section_id = ?2 , godown_section_beans_id = ?3 WHERE product_rm_id = ?4 and company_id = ?5 and is_delete = 0 and day_closed = 0", nativeQuery = true)
    void updateMaterialStockDetails(Integer godownId, Integer godownSectionId, Integer godownSectionBeansId, String productRmId, Integer companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_rm_stock_details SET batch_rate = ?1 where product_rm_id = ?2 and company_id = ?3", nativeQuery = true)
    void updateMaterialStockRate(double productRmMpq, String productRmId, Integer companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE st_indent_material_issue_details SET material_batch_rate = ?1 where product_material_id = ?2 and company_id = ?3  AND issue_no = ?4 ", nativeQuery = true)
    void updateMaterialIssueRate(double productRmMrp, String productRmId, Integer companyId, String issueNo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE st_indent_material_issue_details  SET material_batch_rate = ?1  WHERE product_material_id = ?2 AND company_id = ?3 ",nativeQuery = true)
    void updateAllMaterialIssueRate(double productRmMrp, String productRmId, Integer companyId);


    @Query(value = "FROM CProductRmModel rm WHERE rm.product_type_id = 12")
    List<CProductRmModel> getRawMaterials();

    @Query(nativeQuery = true, value = "SELECT * FROM sm_product_rm WHERE (product_rm_name = ?1 OR (?2 IS NOT NULL AND product_rm_short_name = ?2)) AND company_id = ?3 AND is_delete = 0 ORDER BY product_rm_id DESC LIMIT 1")
    CProductRmModel getCheckMultipleCountExist(String product_rm_name, String product_rm_short_name, int company_id);



}
