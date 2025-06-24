ALTER TABLE pt_quality_testing_cotton_bales CHANGE unit_testing_no quality_testing_no varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;
ALTER TABLE pt_quality_testing_cotton_bales CHANGE unit_testing_date quality_testing_date date NOT NULL;


create or replace
algorithm = UNDEFINED view `ptv_quality_testing_cotton_bales` as
select
    `qt`.`quality_testing_cotton_bales_transaction_id` as `quality_testing_cotton_bales_transaction_id`,
    `qt`.`quality_testing_no` as `quality_testing_no`,
    `qt`.`financial_year` as `financial_year`,
    `qt`.`quality_testing_date` as `quality_testing_date`,
    `qt`.`batch_no` as `batch_no`,
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
    (((`pt_quality_testing_cotton_bales` `qt`
left join `cm_company` `c` on
    (`c`.`company_id` = `qt`.`company_id`))
left join `cm_company_branch` `cb` on
    (`qt`.`company_id` = `cb`.`company_id`
        and `qt`.`company_branch_id` = `cb`.`company_branch_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `qt`.`quality_tester_id`))
where
    `qt`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `ptv_quality_testing_cotton_bales_rpt` as
select
    concat(`v`.`quality_testing_no`, ':Quality Testing No:Y:T:') as `quality_testing_no`,
    concat(`v`.`quality_testing_date`, ':Quality Testing Date:Y:D:') as `quality_testing_date`,
    concat(`v`.`quality_testing_status_desc`, ':Quality Testing Status:Y:C:ptv_quality_testing_cotton_bales:O') as `quality_testing_status_desc`,
    concat(`v`.`quality_tester_name`, ':Quality Tester Name:Y:T:') as `quality_tester_name`,
    concat(`v`.`batch_no`, ':Lot No:Y:T:') as `batch_no`,
    concat(`v`.`pr_no`, ':PR No:Y:T:') as `pr_no`,
    concat(`v`.`uhml`, ':UHML:O:N:') as `uhml`,
    concat(`v`.`ul`, ':UL:O:N:') as `ul`,
    concat(`v`.`mi`, ':MI:O:N:') as `mi`,
    concat(`v`.`mic`, ':MIC:O:N:') as `mic`,
    concat(`v`.`str`, ':STR:O:N:') as `str`,
    concat(`v`.`rd`, ':RD:O:N:') as `rd`,
    concat(`v`.`b_plus`, ':B+:O:N:') as `b_plus`,
    concat(`v`.`total_neps`, ':TOTAL NEPS:O:N:') as `total_neps`,
    concat(`v`.`sc_n`, ':SC(N):O:N:') as `sc_n`,
    concat(`v`.`sfc_n`, ':SFC(N):O:N:') as `sfc_n`,
    concat(`v`.`trash`, ':TRASH:O:N:') as `trash`,
    concat(`v`.`moisture`, ':MOISTURE:O:N:') as `moisture`,
    concat(`v`.`cg`, ':CG:O:N:') as `cg`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cm_company:F') as `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch:Y:C:cm_company_branch:F') as `company_branch_name`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:O:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:O:N:') as `company_branch_id`,
    concat(`v`.`is_active`, ':Active:N:N:') as `is_active`,
    concat(`v`.`is_delete`, ':Delete:N:N:') as `is_delete`,
    concat(`v`.`quality_testing_cotton_bales_transaction_id`, 'Quality Testing Id:O:N:') as `quality_testing_cotton_bales_transaction_id`
from
    `ptv_quality_testing_cotton_bales` `v`
limit 1;