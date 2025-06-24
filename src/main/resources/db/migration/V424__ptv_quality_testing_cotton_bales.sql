-- erp.ptv_quality_testing_cotton_bales source

create or replace
algorithm = UNDEFINED view `ptv_quality_testing_cotton_bales` as
select
    `qt`.`quality_testing_cotton_bales_transaction_id` as `quality_testing_cotton_bales_transaction_id`,
    `qt`.`quality_testing_no` as `quality_testing_no`,
    `qt`.`financial_year` as `financial_year`,
    `qt`.`quality_testing_date` as `quality_testing_date`,
    `qt`.`batch_no` as `batch_no`,
    `pgcbd`.`goods_receipt_no` as `goods_receipt_no`,
    `qt`.`pr_no` as `pr_no`,
    `qt`.`uhml` as `uhml`,
    `qt`.`ul` as `ul`,
    `qt`.`mi` as `mi`,
    `qt`.`mic` as `mic`,
    `qt`.`str` as `str`,
    `qt`.`rd` as `rd`,
    `qt`.`b_plus` as `b_plus`,
    `qt`.`total_neps` as `total_neps`,
    `qt`.`sc_n` as `sc_n`,
    `qt`.`sfc_n` as `sfc_n`,
    `qt`.`trash` as `trash`,
    `qt`.`moisture` as `moisture`,
    `qt`.`cg` as `cg`,
    `qt`.`quality_testing_status` as `quality_testing_status`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `e1`.`employee_name` as `quality_tester_name`,
    case
        `qt`.`quality_testing_status` when 'P' then 'Pending'
        when 'C' then 'Consumable'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Rejected'
    end as `quality_testing_status_desc`,
    case
        `qt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `qt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `qt`.`is_active` as `is_active`,
    `qt`.`is_delete` as `is_delete`,
    `qt`.`created_by` as `created_by`,
    `qt`.`created_on` as `created_on`,
    `qt`.`modified_by` as `modified_by`,
    `qt`.`modified_on` as `modified_on`,
    `qt`.`deleted_by` as `deleted_by`,
    `qt`.`deleted_on` as `deleted_on`,
    `qt`.`company_id` as `company_id`,
    `qt`.`company_branch_id` as `company_branch_id`,
    `qt`.`quality_testing_cotton_bales_transaction_id` as `field_id`,
    `qt`.`quality_testing_no` as `field_name`
from
    ((((`pt_quality_testing_cotton_bales` `qt`
left join `cm_company` `c` on
    (`c`.`company_id` = `qt`.`company_id`))
left join `pt_grn_cotton_bales_master` `pgcbd` on
    (`pgcbd`.`batch_no` = `qt`.`batch_no`
        and `pgcbd`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`qt`.`company_id` = `cb`.`company_id`
        and `qt`.`company_branch_id` = `cb`.`company_branch_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `qt`.`quality_tester_id`))
where
    `qt`.`is_delete` = 0;