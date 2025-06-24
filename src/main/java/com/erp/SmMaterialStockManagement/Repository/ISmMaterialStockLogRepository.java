package com.erp.SmMaterialStockManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;


public interface ISmMaterialStockLogRepository extends JpaRepository<CSmMaterialStockLogModel, Integer> {

	@Query(value = "FROM CSmMaterialStockLogModel model where model.is_delete =0 and model.stock_log_transaction_id = ?1 and model.company_id = ?2")
	CSmMaterialStockLogModel FnShowParticularRecordForUpdate(int stock_log_transaction_id, int company_id);

	@Query(value = "SELECT * FROM sm_material_stock_log WHERE is_delete = 0 AND batch_no = ?1 AND company_id = ?2 LIMIT 1", nativeQuery = true)
	CSmMaterialStockLogModel FnGetStockModelAgainstLotNo(String batch_no, Integer company_id);
}
