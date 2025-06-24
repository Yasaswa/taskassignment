package com.erp.SmProductSr.Repository;

import com.erp.SmProductSr.Model.CSmProductSrModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;


public interface ISmProductSrRepository extends JpaRepository<CSmProductSrModel, Integer> {

	@Query(value = "FROM CSmProductSrModel model where  model.is_delete =0 and model.product_sr_id = ?1 and model.company_id = ?2")
	CSmProductSrModel FnShowParticularRecordForUpdate(int product_sr_id, int company_id);

	@Query(value = "FROM CSmProductSrModel model where model.is_delete =0 and model.product_sr_name = ?1 and model.company_id = ?2")
	CSmProductSrModel checkIfNameExist(String product_sr_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where product_sr_id = ?1 and company_id = ?2", nativeQuery = true)
	void deleteproductSrMasterRecords(String product_sr_id, int company_id, String deleted_by);

	@Query(value = "Select * From smv_product_sr_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "Select * From smv_product_sr_details_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

	@Modifying
	@Transactional
	@Query(value = "update CSmProductSrModel model set model.product_sr_qr_code = ?2 where model.product_sr_id = ?1 and model.is_delete = 0")
	void updateQrCodePath(String product_sr_id, Object dataValue);

	@Query(value = "FROM CSmProductSrModel model where model.is_delete = 0 and model.product_sr_id = ?1 and model.company_id = ?2")
	CSmProductSrModel FnShowProductServiceMasterRecords(String product_sr_id, int company_id);


	@Query(value = "SELECT * FROM sm_product_sr WHERE product_sr_id = ?1 ", nativeQuery = true)
	Optional<CSmProductSrModel> findByIdProduct_sr_id(String product_sr_id);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_sr WHERE (product_sr_name = ?1 or (?2 IS NOT NULL AND product_sr_short_name = ?2)) and company_id = ?3 order by product_id Desc limit 1")
	CSmProductSrModel getCheck(String product_sr_name, String product_sr_short_name, Integer company_id);
}
