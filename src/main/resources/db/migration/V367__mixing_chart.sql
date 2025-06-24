ALTER TABLE pt_mixing_chart_cotton_bales ADD actual_issue_weight decimal(18,4) DEFAULT 0 NULL;
ALTER TABLE pt_mixing_chart_cotton_bales ADD difference_weight decimal(18,4) DEFAULT 0 NULL;


create or replace
algorithm = UNDEFINED view .`ptv_mixing_chart_cotton_bales` as
select
    `pt`.`mixing_chart_no` as `mixing_chart_no`,
    `cp`.`plant_name` as `plant_name`,
    `pt`.`batch_no` as `batch_no`,
    `pt`.`mixing_chart_date` as `mixing_chart_date`,
    `pt`.`quality_testing_no` as `quality_testing_no`,
    `e`.`employee_name` as `mixer_by_name`,
    `pt`.`pr_no` as `pr_no`,
    `pt`.`uhml` as `uhml`,
    `pt`.`ul` as `ul`,
    `pt`.`mi` as `mi`,
    `pt`.`mic` as `mic`,
    `pt`.`str` as `str`,
    `pt`.`rd` as `rd`,
    `pt`.`b_plus` as `b_plus`,
    `pt`.`total_neps` as `total_neps`,
    `pt`.`sc_n` as `sc_n`,
    `pt`.`sfc_n` as `sfc_n`,
    `pt`.`trash` as `trash`,
    `pt`.`moisture` as `moisture`,
    `pt`.`cg` as `cg`,
    `pt`.`quality_testing_status` as `quality_testing_status`,
    `pt`.`closing_balance_quantity` as `closing_balance_quantity`,
    `pt`.`closing_balance_weight` as `closing_balance_weight`,
    `pt`.`issue_quantity` as `issue_quantity`,
    `pt`.`issue_weight` as `issue_weight`,
    `pt`.`actual_issue_weight` as `actual_issue_weight`,
    `pt`.`difference_weight` as `difference_weight`,
    `pt`.`stock_quantity` as `stock_quantity`,
    `pt`.`stock_weight` as `stock_weight`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`plant_id` as `plant_id`,
    `pt`.`company_id` as `company_id`,
    `pt`.`mixing_by_id` as `mixing_by_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`mixing_chart_cotton_bales_transaction_id` as `mixing_chart_cotton_bales_transaction_id`
from
    ((((.`pt_mixing_chart_cotton_bales` `pt`
left join .`cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join .`cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join .`cm_plant` `cp` on
    (`cp`.`plant_id` = `pt`.`plant_id`
        and `cp`.`is_delete` = 0))
left join .`cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`mixing_by_id`
        and `e`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;