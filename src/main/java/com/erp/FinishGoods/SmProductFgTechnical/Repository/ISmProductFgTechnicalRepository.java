package com.erp.FinishGoods.SmProductFgTechnical.Repository;

import com.erp.FinishGoods.SmProductFgTechnical.Model.CSmProductFgTechnicalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface ISmProductFgTechnicalRepository extends JpaRepository<CSmProductFgTechnicalModel, Integer> {

	@Query(value = "FROM  CSmProductFgTechnicalModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1")
	CSmProductFgTechnicalModel FnShowParticularRecordForUpdate(int product_fg_id);

	@Query(value = "FROM  CSmProductFgTechnicalModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_technical_name = ?1 and cdvm.company_id = ?2")
	CSmProductFgTechnicalModel checkIfExist(String product_fg_technical_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_technical set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteTechnicalRecords(String product_fg_id, String deleted_by);

	@Query(value = "SELECT * FROM sm_product_fg_technical WHERE product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowParticularProductFgTechnicalRecords(String product_fg_id );
	
//	@Query(value = "SELECT * FROM sm_product_fg_technical WHERE product_fg_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//	Map<String, Object> FnShowParticularProductFgTechnicalRecords(String product_fg_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_technical set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_fg_technical_id=?1", nativeQuery = true)
	void updateActiveStatusProductTechnical(int product_fg_technical_id);


}
