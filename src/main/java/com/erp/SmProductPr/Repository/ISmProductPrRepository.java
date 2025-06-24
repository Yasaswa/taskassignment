package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface ISmProductPrRepository extends JpaRepository<CSmProductPrModel, Integer> {

	@Query(value = "FROM CSmProductPrModel model where model.is_delete =0 and model.product_id = ?1 and model.company_id = ?2")
	CSmProductPrModel FnShowParticularRecordForUpdate(int product_id, int company_id);


	@Query(value = "FROM  CSmProductPrModel cdvm where cdvm.is_delete =0 and cdvm.product_pr_name = ?1 and cdvm.company_id =?2")
	CSmProductPrModel CheckIfExist(String product_pr_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CSmProductPrModel model set model.product_pr_qr_code = ?2 where model.product_pr_id = ?1 and model.is_delete = 0")
	void updateQrCodePath(String product_pr_id, String dataValue);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_pr_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteProductPrRecords(String product_pr_id, String deleted_by);
}
