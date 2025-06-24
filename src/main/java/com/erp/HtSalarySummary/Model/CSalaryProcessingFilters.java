package com.erp.HtSalarySummary.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSalaryProcessingFilters {
    private int company_id;
    private String employee_type;
    private String employee_group;
    private int salary_month;
    private int salary_year;
}