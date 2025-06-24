package com.erp.HmEarningHeads.Repository;

import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IHmEarningHeadsRepository extends JpaRepository<CHmEarningHeadsModel, Integer> {

	@Query(value = "FROM CHmEarningHeadsModel model where model.is_delete =0 and model.earning_heads_id = ?1")
	CHmEarningHeadsModel FnShowParticularRecordForUpdate(int earning_heads_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM hm_earning_heads WHERE (earning_head_name = ?1 or (?2 IS NOT NULL AND earning_head_short_name = ?2)) and company_id = ?3 order by earning_heads_id Desc limit 1")
	CHmEarningHeadsModel getCheck(String earning_head_name, String earning_head_short_name, Integer company_id);


}
