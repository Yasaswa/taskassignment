package com.erp.HtSalaryRules.Repository;

import com.erp.HtSalaryRules.Model.CHtvSalaryRulesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHtvSalaryRulesViewModelRepository extends JpaRepository<CHtvSalaryRulesViewModel, Integer> {
	@Query(value = "FROM CHtvSalaryRulesViewModel model WHERE model.property_name = ?1 and model.is_delete = false and company_id = ?2 ORDER BY salary_rule_id DESC")
	List<CHtvSalaryRulesViewModel> getLateMarkRules(String late_coming, int company_id);
}