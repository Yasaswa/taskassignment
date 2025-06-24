create or replace
    algorithm = UNDEFINED view `ptv_mixing_chart_cotton_bales_summary` as
    select
        `pt`.`mixing_chart_no` as `mixing_chart_no`,
        `cp`.`plant_name` as `plant_name`,
        `pt`.`batch_no` as `batch_no`,
        `pt`.`mixing_chart_date` as `mixing_chart_date`,
        `e`.`employee_name` as `mixer_by_name`,
        sum(`pt`.`issue_quantity`) as `issue_quantity`,
        sum(`pt`.`issue_weight`) as `issue_weight`,
        sum(`pt`.`actual_issue_weight`) as `actual_issue_weight`,
        sum(`pt`.`difference_weight`) as `difference_weight`,
        `pt`.`plant_id` as `plant_id`,
        `pt`.`company_id` as `company_id`,
        `pt`.`mixing_by_id` as `mixing_by_id`,
        `pt`.`company_branch_id` as `company_branch_id`,
        `pt`.`mixing_chart_cotton_bales_transaction_id` as `mixing_chart_cotton_bales_transaction_id`,
        `pt`.`issue_flag` as `issue_flag`,
        `pt`.`is_delete` as `is_delete`
    from
        ((((`pt_mixing_chart_cotton_bales` `pt`
    left join `cm_company` `c` on
        (`c`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `cm_company_branch` `cb` on
        (`cb`.`company_branch_id` = `pt`.`company_branch_id`
            and `cb`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `cm_plant` `cp` on
        (`cp`.`plant_id` = `pt`.`plant_id`
            and `cp`.`is_delete` = 0))
    left join `cm_employee` `e` on
        (`e`.`employee_id` = `pt`.`mixing_by_id`
            and `e`.`is_delete` = 0))
    where
        `pt`.`is_delete` = 0
    group by
        `pt`.`mixing_chart_no`;


create or replace
    algorithm = UNDEFINED view `ptv_mixing_chart_cotton_bales_summary_rpt` as
    select
        concat(ifnull(`v`.`mixing_chart_no`, ''), ':Mixing Chart No:Y:T:') as `mixing_chart_no`,
        concat(ifnull(`v`.`mixing_chart_date`, ''), ':Mixing Chart Date:Y:D:') as `mixing_chart_date`,
        concat(ifnull(`v`.`mixer_by_name`, ''), ':Mixer By Name:Y:T') as `mixer_by_name`,
        concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:T') as `plant_name`,
        concat(`v`.`issue_quantity`, ':Issued Qty.:O:N:') as `issue_quantity`,
        concat(`v`.`issue_weight`, ':Issued Wt.:O:N:') as `issue_weight`,
        concat(`v`.`actual_issue_weight`, ':Actual Wt.:O:N:') as `actual_issue_weight`,
        concat(`v`.`difference_weight`, ':Difference Wt.:O:N:') as `difference_weight`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(`v`.`issue_flag`, ':Issue Flag:O:N:') as `issue_flag`,
        concat(ifnull(`v`.`mixing_chart_cotton_bales_transaction_id`, ''), ': Mixing Chart Id:O:N:') as `mixing_chart_cotton_bales_transaction_id`
    from
        `ptv_mixing_chart_cotton_bales_summary` `v`
    limit 1;