package com.erp.FinishGoods.SmProductFgProcess.Repository;

import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductFgProcessRepository extends JpaRepository<CSmProductFgProcessModel, Integer> {

	@Query(value = "Select * from sm_product_fg_process where product_fg_id = ?1", nativeQuery = true)
	List<CSmProductFgProcessModel> checkRecordIfExist(int product_fg_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_process set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and company_id=?2", nativeQuery = true)
	Object updateProductFGProcessActiveStatus(String product_fg_id, int company_id);

	@Query(value = "FROM CSmProductFgProcessModel cfm where cfm.is_delete=0 and cfm.product_fg_id =?1")
	List<CSmProductFgProcessModel> FnShowParticularRecord(int product_fg_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_process set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteProcessRecords(String product_fg_id, String deleted_by);

	@Query(value = "FROM CSmProductFgProcessModel model WHERE model.product_fg_id = ?1 and model.is_delete = 0")
	List<CSmProductFgProcessModel> FnShowParticularProductFgProcessRecords(String product_fg_id );

//	@Query(value = "FROM CSmProductFgProcessModel model WHERE model.product_fg_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
//	List<CSmProductFgProcessModel> FnShowParticularProductFgProcessRecords(String product_fg_id, int company_id);


}
