package com.erp.RawMaterial.SmProductRmQaMapping.Repository;

import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductRmQaMappingRepository extends JpaRepository<CSmProductRmQaMappingModel, Integer> {

    @Query(value = "FROM CSmProductRmQaMappingModel csqm where csqm.is_delete =0 and csqm.product_rm_id = ?1")
    List<CSmProductRmQaMappingModel> checkRecordIfExist(int product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_qa_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1 and company_id = ?2 and is_delete =0", nativeQuery = true)
    Object updateProductRMQaMappingActiveStatus(String product_rm_id, int company_id);


    @Query(value = "FROM CSmProductRmQaMappingModel cdvm where cdvm.is_delete =0 and cdvm.product_rm_id = ?1 and cdvm.company_id = ?2")
    Page<CSmProductRmQaMappingModel> FnShowParticularRecord(int product_rm_id, Pageable pageable, int company_id);

    //	@Query(value = "FROM CSmProductRmQaMappingModel cdvm where cdvm.is_delete =0 and cdvm.product_rm_id = ?1 and cdvm.company_id = ?2")
//	List<CSmProductRmQaMappingModel> FnShowParticularRmmaterialQaMappingRecords(String product_rm_id, int company_id);
    @Query(value = "FROM CSmProductRmQaMappingModel cdvm where cdvm.is_delete =0 and cdvm.product_rm_id = ?1")
    List<CSmProductRmQaMappingModel> FnShowParticularRmmaterialQaMappingRecords(String product_rm_id);

    @Modifying
    @Transactional
    @Query(value = "update sm_product_rm_qa_mapping set is_delete = 1, deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteProductRmQaMappingRecords(String product_rm_id, String deleted_by);
}
