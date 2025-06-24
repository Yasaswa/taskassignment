package com.erp.FmProfitCenter.Repository;

import com.erp.FmProfitCenter.Model.CFmProfitCenterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFmProfitCenterRepository extends JpaRepository<CFmProfitCenterModel, Integer> {

	@Query(value = "FROM  CFmProfitCenterModel cdvm where cdvm.is_delete =0 and cdvm.profit_center_id = ?1 and cdvm.company_id = ?2")
	CFmProfitCenterModel FnShowParticularRecordForUpdate(int profit_center_id, int company_id);

	@Query(value = "FROM  CFmProfitCenterModel cdvm where cdvm.is_delete =0 and cdvm.profit_center_name = ?1")
	CFmProfitCenterModel checkIfExist(String profit_center_name);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_profit_center WHERE (profit_center_name = ?1 or profit_center_short_name = ?2 ) and company_id = ?3 and is_delete = 0")
	CFmProfitCenterModel checkIfExist(String profit_center_name, String profit_center_short_name, Integer company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_profit_center WHERE (profit_center_name = ?1 or (?2 IS NOT NULL AND profit_center_short_name = ?2)) and company_id = ?3 order by profit_center_id Desc limit 1")
	CFmProfitCenterModel getCheck(String profit_center_name, String profit_center_short_name, int company_id);

}
