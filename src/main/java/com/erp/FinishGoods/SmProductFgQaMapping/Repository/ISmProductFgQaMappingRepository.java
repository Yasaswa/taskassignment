package com.erp.FinishGoods.SmProductFgQaMapping.Repository;

import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductFgQaMappingRepository extends JpaRepository<CSmProductFgQaMappingModel, Integer> {

	@Query(value = "FROM CSmProductFgQaMappingModel csqm where csqm.is_delete =0 and csqm.product_fg_id = ?1")
	List<CSmProductFgQaMappingModel> checkRecordIfExist(int product_fg_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_qa_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and company_id=?2 and company_branch_id=?3", nativeQuery = true)
	Object updateProductRMQaMappingActiveStatus(String product_fg_id, int company_id, int company_branch_id);


	@Query(value = "FROM CSmProductFgQaMappingModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1 ")
	Page<CSmProductFgQaMappingModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_qa_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and company_id=?2", nativeQuery = true)
	void updateProductFgQaMappingActiveStatus(String product_fg_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_qa_mapping set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteQaMappingRecords(String product_fg_id, String deleted_by);

//	@Query(value = "FROM CSmProductFgQaMappingModel model WHERE model.product_fg_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
//	List<CSmProductFgQaMappingModel> FnShowParticularProductFGQaMappingRecords(String product_fg_id, int company_id);

	@Query(value = "FROM CSmProductFgQaMappingModel model WHERE model.product_fg_id = ?1 and model.is_delete = 0")
	List<CSmProductFgQaMappingModel> FnShowParticularProductFGQaMappingRecords(String product_fg_id );


}
