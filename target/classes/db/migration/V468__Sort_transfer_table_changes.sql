ALTER TABLE mt_sort_transfer_details ADD to_style VARCHAR(50);

create or replace
algorithm = UNDEFINED view `mtv_sort_transfer_details` as
select
    `dt`.`sort_transfer_details_id` as `sort_transfer_details_id`,
    `dt`.`sort_transfer_master_id` as `sort_transfer_master_id`,
    `dt`.`company_id` as `company_id`,
    `dt`.`financial_year` as `financial_year`,
    `dt`.`company_branch_id` as `company_branch_id`,
    `dt`.`roll_no` as `roll_no`,
    `dt`.`transfer_no` as `transfer_no`,
    `dt`.`from_set_no` as `from_set_no`,
    `dt`.`to_set_no` as `to_set_no`,
    `dt`.`from_sort_no` as `from_sort_no`,
    `dt`.`to_sort_no` as `to_sort_no`,
    `dt`.`style` as `style`,
    `dt`.`to_style` as `to_style`,
    `dt`.`inspection_mtr` as `inspection_mtr`,
    `dt`.`dispatch_weight` as `dispatch_weight`,
    `dt`.`average` as `average`,
    `dt`.`remark` as `remark`,
    `dt`.`is_delete` as `is_delete`,
    `dt`.`created_by` as `created_by`,
    `dt`.`created_on` as `created_on`,
    `dt`.`modified_by` as `modified_by`,
    `dt`.`modified_on` as `modified_on`,
    `dt`.`deleted_by` as `deleted_by`,
    `dt`.`deleted_on` as `deleted_on`,
    `mt`.`transfer_by` as `transfer_by`,
    `mt`.`created_on` as `transfer_date`,
    `cm`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`
from
    (((`mt_sort_transfer_details` `dt`
left join `mt_sort_transfer_master` `mt` on
    (`dt`.`sort_transfer_master_id` = `mt`.`sort_transfer_master_id`
        and `mt`.`is_delete` = 0))
left join `cm_company` `cm` on
    (`dt`.`company_id` = `cm`.`company_id`
        and `cm`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `dt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `dt`.`is_delete` = 0;