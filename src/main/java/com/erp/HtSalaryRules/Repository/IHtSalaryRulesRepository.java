package com.erp.HtSalaryRules.Repository;

import com.erp.HtSalaryRules.Model.CHtSalaryRulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IHtSalaryRulesRepository extends JpaRepository<CHtSalaryRulesModel, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM hm_salary_rules WHERE property_id = ?1 and company_id = ?2  and is_delete = 0")
    List<CHtSalaryRulesModel> FnShowParticularRecord(Integer property_id, Integer companyId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE hm_salary_rules hmr SET hmr.is_delete = 1, hmr.deleted_on = CURRENT_TIMESTAMP(), hmr.deleted_by = ?3 WHERE hmr.salary_rule_id = ?1 AND hmr.company_id = ?2")
    void deleteRecords(int salary_rule_id, Integer company_id, String deleted_by);
}
