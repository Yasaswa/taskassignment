package com.erp.SmProductProcess.Repository;

import com.erp.SmProductProcess.Model.CPmProductProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPmProductProcessRepository extends JpaRepository<CPmProductProcessModel, Integer> {

	@Query(value = "FROM CPmProductProcessModel cdvm where cdvm.is_delete =0 and cdvm.product_process_id = ?1")
	CPmProductProcessModel FnShowParticularRecordForUpdate(int product_process_id);

	@Query(value = "FROM CPmProductProcessModel cppm where cppm.is_delete =0 and cppm.product_process_name = ?1")
	CPmProductProcessModel checkIfNameExist(String getproduct_process_name);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_process WHERE (product_process_name = ?1 or product_process_short_name = ?2 ) and company_id = ?3 and is_delete = 0")
	CPmProductProcessModel checkIfNameExist(String product_process_name, String product_process_short_name,
	                                        Integer company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_process WHERE (product_process_name = ?1 or (?2 IS NOT NULL AND product_process_short_name = ?2)) and company_id = ?3 order by product_process_id Desc limit 1")
	CPmProductProcessModel getCheck(String product_process_name, String product_process_short_name, Integer company_id);

}
