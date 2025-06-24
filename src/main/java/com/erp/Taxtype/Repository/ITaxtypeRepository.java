package com.erp.Taxtype.Repository;

import com.erp.Taxtype.Model.CTaxtypeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ITaxtypeRepository extends JpaRepository<CTaxtypeModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_taxtype set is_delete = 1 , deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where taxtype_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int taxtype_id, String deleted_by);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_taxtype WHERE taxtype_name = ?1 or taxtype_short_name= ?2 and company_id = ?3")
//	CTaxtypeModel getCheck(String taxtype_name, String taxtype_short_name, Integer company_id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cm_taxtype WHERE (taxtype_name = ?1 OR (?2 IS NOT NULL AND taxtype_short_name = ?2)) and company_id = ?3 order by taxtype_id Desc limit 1")
	CTaxtypeModel getCheck(String taxtype_name, String taxtype_short_name, int company_id);

}
