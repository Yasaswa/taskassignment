package com.erp.HmDeductionHeads.Repository;

import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IHmDeductionHeadsRepository extends JpaRepository<CHmDeductionHeadsModel, Integer> {

	@Query(value = "FROM CHmDeductionHeadsModel model where model.is_delete =0 and model.deduction_head_name = ?1 and model.company_id = ?2")
	CHmDeductionHeadsModel checkIfNameExist(String deduction_head_name, int company_id);


	@Query(value = "FROM CHmDeductionHeadsModel model where model.is_delete =0 and model.deduction_heads_id = ?1")
	CHmDeductionHeadsModel FnShowParticularRecordForUpdate(int deduction_heads_id, int company_id);


	@Query(nativeQuery = true, value = "SELECT * FROM hm_deduction_heads WHERE (deduction_head_name = ?1 or (?2 IS NOT NULL AND deduction_head_short_name = ?2)) and company_id = ?3 order by deduction_heads_id Desc limit 1")
	CHmDeductionHeadsModel getCheck(String deduction_head_name, String deduction_head_short_name, Integer company_id);

}
