package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IDesignationsRepository extends JpaRepository<CDesignationsModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_designation set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where designation_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int designation_id, String deleted_by);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_designation WHERE designation_name = ?1 and company_id = ?2 and is_delete = 0")
	CDesignationsModel getCheck(String designation_name, int company_id);

}
