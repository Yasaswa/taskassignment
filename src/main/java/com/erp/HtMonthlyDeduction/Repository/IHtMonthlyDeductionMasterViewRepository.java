package com.erp.HtMonthlyDeduction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionMasterViewModel;


public interface IHtMonthlyDeductionMasterViewRepository extends JpaRepository<CHtMonthlyDeductionMasterViewModel, Integer> {

}
