package com.erp.FinishGoods.SmProductFg.Repository;

import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgModel;
import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ISmProductFgRepository extends JpaRepository<CSmProductFgModel, Integer> {

	@Query(value = "FROM  CSmProductFgModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1")
	CSmProductFgModel FnShowParticularRecordForUpdate(int product_fg_id);

	@Query(value = "FROM  CSmProductFgModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_name = ?1 and cdvm.company_id =?2")
	CSmProductFgModel CheckIfExist(String product_fg_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteProductFgRecords(String product_fg_id, String deleted_by);

//	@Query(value = "SELECT * FROM sm_product_fg WHERE product_fg_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//    CSmProductFgModel FnShowParticularProductFgRecords(String product_fg_id, int company_id);
	
	@Query(value = "SELECT * FROM sm_product_fg WHERE product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
    CSmProductFgModel FnShowParticularProductFgRecords(String product_fg_id );

	@Query(value = "Select * From smv_product_fg_rpt_summary", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "Select * From smv_product_fg_rpt_details", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

	@Modifying
	@Transactional
	@Query(value = "update CSmProductFgModel model set model.product_fg_qr_code = ?2 where model.product_fg_id = ?1 and model.is_delete = 0")
	void updateQrCodePath(String product_fg_id, String dataValue);

//	@Modifying
//	@Transactional
	@Query(value = "SELECT * FROM sm_product_fg WHERE product_fg_id = ?1", nativeQuery = true)
	Optional<CSmProductFgModel> findByProductFgId(String product_fg_id);

	@Query(value = "SELECT * FROM smv_product_rm_fg_sr  where is_delete =0 and product_material_code = ?1 and company_id =?2", nativeQuery = true)
	Map<String, Object>  CheckIfCodeExist(String productFgCode, int companyId);

	@Query(value = "Select * FROM sm_product_fg WHERE is_delete = 0 AND product_fg_id IN (:productIds)", nativeQuery = true)
	List<CSmProductFgModel> getProductFgDetails(@Param("productIds") List<String> productIdListSeparated);
}
