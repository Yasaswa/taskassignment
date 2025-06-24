package com.erp.Supplier.Repository;

import com.erp.Supplier.Model.CSupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface ISupplierRepository extends JpaRepository<CSupplierModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_supplier set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where supplier_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int supplier_id, String deleted_by);

	@Query(value = "Select * FROM cmv_supplier_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	
	@Query(nativeQuery = true,value = "SELECT * FROM cm_supplier WHERE supplier_name = ?1  AND company_id = ?2 and is_delete = 0 ORDER BY supplier_id DESC LIMIT 1")
	CSupplierModel getCheck(String supplier_name, Integer company_id);


}
