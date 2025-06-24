package com.erp.SmProductSrSupplier.Repository;

import com.erp.SmProductSrSupplier.Model.CSmProductSrSupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductSrSupplierRepository extends JpaRepository<CSmProductSrSupplierModel, Integer> {

	@Query(value = "FROM CSmProductSrSupplierModel model where model.is_delete =0 and model.product_sr_id = ?1 and model.company_id = ?2")
	CSmProductSrSupplierModel FnShowParticularRecordForUpdate(int product_sr_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_supplier set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(String product_sr_id, int company_branch_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_supplier set is_delete = 1, deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_id = ?2", nativeQuery = true)
	void deleteproductSrSupplierRecords(String product_sr_id, int company_id, String deleted_by);

	@Query(value = "FROM CSmProductSrSupplierModel model where model.is_delete = 0  and  model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrSupplierModel> FnShowProductServiceSupplierRecords(String product_sr_id, int company_id);


}
