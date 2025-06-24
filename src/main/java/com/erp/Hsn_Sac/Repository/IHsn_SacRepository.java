package com.erp.Hsn_Sac.Repository;

import com.erp.Hsn_Sac.Model.CHsn_SacModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IHsn_SacRepository extends JpaRepository<CHsn_SacModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_hsn_sac set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where hsn_sac_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int hsn_sac_id, String deleted_by);


	@Query(nativeQuery = true, value = "SELECT * FROM cm_hsn_sac WHERE hsn_sac_code = ?1 and company_id = ?2 ")
	CHsn_SacModel getCheck(String hsn_sac_code, int company_id);

}
