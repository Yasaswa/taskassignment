package com.erp.Contractors.Repository;

import com.erp.Contractors.Model.CContractorsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IContractorsRepository extends JpaRepository<CContractorsModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_contractor set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where contractor_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int contractor_id, String deleted_by);

	//	@Query(nativeQuery = true, value = "SELECT * FROM cm_contractor WHERE contractor_name = ?1")
//	CContractorsModel getCheck(String contractor_name);
	@Query(nativeQuery = true, value = "SELECT * FROM cm_contractor WHERE (contractor_name = ?1 or contractor_short_name = ?2) and company_id = ?3 order by contractor_id Desc limit 1")
	CContractorsModel getCheck(String contractor_name, String contractor_short_name, int company_id);

}
