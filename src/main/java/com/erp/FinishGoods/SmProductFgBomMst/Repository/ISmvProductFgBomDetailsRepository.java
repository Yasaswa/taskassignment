package com.erp.FinishGoods.SmProductFgBomMst.Repository;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmvProductFgBomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ISmvProductFgBomDetailsRepository extends JpaRepository<CSmvProductFgBomDetails, Integer> {

	@Query(value = "SELECT * from smv_product_fg_bom_details where is_delete = '0' and product_fg_bom_id = ?1", nativeQuery = true)
	List<Map<String, Object>> FnShowParticularRecordForUpdate(int product_fg_bom_id);


//	@Query(value = "select * from smv_product_fg_bom_summary  where is_delete = '0' and bom_applicable = 'yes' and product_parent_fg_id =?1 and company_id = ?2", nativeQuery = true)
//	Page<CSmvProductFgBomSummaryModel> FnShowProductFgBomMstRecord(int product_parent_fg_id, Pageable pageable, int company_id);


	@Query(value = "select * from smv_product_fg_bom_summary  where is_delete = '0' and bom_applicable = 'yes' and product_parent_fg_id =?1 and company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnShowProductFgBomSummaryRecord(String product_parent_fg_id, int company_id);

}

