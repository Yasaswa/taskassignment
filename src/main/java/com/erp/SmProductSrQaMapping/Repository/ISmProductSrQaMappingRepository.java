package com.erp.SmProductSrQaMapping.Repository;

import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductSrQaMappingRepository extends JpaRepository<CSmProductSrQaMappingModel, Integer> {

	@Query(value = "FROM CSmProductSrQaMappingModel model where model.product_sr_qa_mapping_id = ?1 and model.company_id = ?2")
	CSmProductSrQaMappingModel FnShowParticularRecordForUpdate(int product_sr_qa_mapping_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_qa_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(String product_sr_id, int company_branch_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_qa_mapping set is_delete = 1, deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_id = ?2", nativeQuery = true)
	void deleteproductSrQaMappingRecords(String product_sr_id, int company_id, String deleted_by);

	@Query(value = "FROM CSmProductSrQaMappingModel model where model.is_delete = 0  and  model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrQaMappingModel> FnShowProductServiceQaMappingRecords(String product_sr_id, int company_id);


}
