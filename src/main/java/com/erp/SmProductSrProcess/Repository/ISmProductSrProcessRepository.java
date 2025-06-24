package com.erp.SmProductSrProcess.Repository;

import com.erp.SmProductSrProcess.Model.CSmProductSrProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductSrProcessRepository extends JpaRepository<CSmProductSrProcessModel, Integer> {

	@Query(value = "FROM CSmProductSrProcessModel model where model.is_delete =0 and model.product_sr_id = ?1 and model.company_id = ?2")
	CSmProductSrProcessModel FnShowParticularRecordForUpdate(int product_sr_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_process set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(String product_sr_id, int company_branch_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_process set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_id = ?2", nativeQuery = true)
	void deleteproductSrProcessRecords(String product_sr_id, int company_id, String deleted_by);


	@Query(value = "FROM CSmProductSrProcessModel model where model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrProcessModel> FnShowProductServiceProcessRecords(String product_sr_id, int company_id);


}
