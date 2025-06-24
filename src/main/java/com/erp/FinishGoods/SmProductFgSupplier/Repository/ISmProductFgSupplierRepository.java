package com.erp.FinishGoods.SmProductFgSupplier.Repository;

import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductFgSupplierRepository extends JpaRepository<CSmProductFgSupplierModel, Integer> {

	@Query(value = "FROM CSmProductFgSupplierModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1 order by cdvm.product_fg_id Desc")
	Page<CSmProductFgSupplierModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);

	@Query(value = "Select * FROM sm_product_fg_supplier smfg where smfg.is_delete = 0 and smfg.product_fg_id = ?1", nativeQuery = true)
	List<CSmProductFgSupplierModel> checkRecordIfExist(int product_fg_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_supplier set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and company_id = ?2", nativeQuery = true)
	Object updateProductFgSupplierActiveStatus(String product_fg_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_supplier set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteSupplierRecords(String product_fg_id, String deleted_by);


//	@Query(value = "FROM CSmProductFgSupplierModel model WHERE model.product_fg_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
//	List<CSmProductFgSupplierModel> FnShowParticularProductFgSupplierRecords(String product_fg_id, int company_id);

	@Query(value = "FROM CSmProductFgSupplierModel model WHERE model.product_fg_id = ?1 and model.is_delete = 0")
	List<CSmProductFgSupplierModel> FnShowParticularProductFgSupplierRecords(String product_fg_id);


}
