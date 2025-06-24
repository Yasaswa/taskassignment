
ALTER TABLE mt_sort_transfer_details ADD to_sizing_beam_no varchar(50) NULL COMMENT 'to transfer beam no';
ALTER TABLE mt_sort_transfer_details CHANGE to_sizing_beam_no to_sizing_beam_no varchar(50) NULL COMMENT 'to transfer beam no' AFTER sizing_beam_no;
ALTER TABLE mt_sort_transfer_details ADD to_machine_id BIGINT(20) DEFAULT 0 NULL COMMENT 'to transfer machine';
ALTER TABLE mt_sort_transfer_details CHANGE to_machine_id to_machine_id BIGINT(20) DEFAULT 0 NULL COMMENT 'to transfer machine' AFTER machine_id;
ALTER TABLE mt_sort_transfer_details ADD product_material_stock_unit_id BIGINT(20) DEFAULT 0 NULL;
ALTER TABLE mt_sort_transfer_details ADD product_material_packing_id BIGINT(20) DEFAULT 0 NULL;


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
    `dt`.`from_style` as `from_style`,
    `dt`.`to_style` as `to_style`,
    `dt`.`inspection_mtr` as `inspection_mtr`,
    `dt`.`weight` as `weight`,
    `dt`.`average` as `average`,
    `dt`.`remark` as `remark`,
    `dt`.`to_sizing_beam_no` as `to_sizing_beam_no`,
    `dt`.`to_machine_id` as `to_machine_id`,
    `dt`.`machine_id` as `machine_id`,
    `dt`.`sizing_beam_no` as `sizing_beam_no`,
    `pt`.`product_type_name` as `product_type_name`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `dt`.`product_type_id` as `product_type_id`,
    `dt`.`is_delete` as `is_delete`,
    `dt`.`created_by` as `created_by`,
    `dt`.`created_on` as `created_on`,
    `dt`.`modified_by` as `modified_by`,
    `dt`.`modified_on` as `modified_on`,
    `dt`.`deleted_by` as `deleted_by`,
    `dt`.`deleted_on` as `deleted_on`,
    `mt`.`transfer_by` as `transfer_by`,
    `mt`.`created_on` as `transfer_date`,
        `e`.`employee_name` as `transfer_by_name`,

    `cm`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`
from
    (((((`mt_sort_transfer_details` `dt`
left join `mt_sort_transfer_master` `mt` on
    (`dt`.`sort_transfer_master_id` = `mt`.`sort_transfer_master_id`
        and `mt`.`is_delete` = 0))
left join `cm_company` `cm` on
    (`dt`.`company_id` = `cm`.`company_id`
        and `cm`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `dt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `dt`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `mt`.`transfer_by`
        and `e`.`is_delete` = 0))
where
    `dt`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `mtv_sort_transfer_master` as
select
    `mt`.`sort_transfer_master_id` as `sort_transfer_master_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`transfer_no` as `transfer_no`,
    `mt`.`transfer_date` as `transfer_date`,
    `mt`.`from_sort_no` as `from_sort_no`,
    `mt`.`to_sort_no` as `to_sort_no`,
    `mt`.`transfer_by` as `transfer_by`,
    `e`.`employee_name` as `transfer_by_name`,
    `mt`.`remark` as `remark`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `cm`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`
from
    (((`mt_sort_transfer_master` `mt`
left join `cm_company` `cm` on
    (`mt`.`company_id` = `cm`.`company_id`
        and `cm`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `mt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
 left join `cm_employee` `e` on
    (`e`.`employee_id` = `mt`.`transfer_by`
        and `e`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0
group by
    `mt`.`sort_transfer_master_id`;



create or replace
algorithm = UNDEFINED view `mtv_sort_transfer_details_rpt` as
select
    concat(ifnull(`mt`.`transfer_no`, ''), ':Transfer No:O:N:') as `transfer_no`,
    concat(ifnull(`mt`.`roll_no`, ''), ':Roll No:O:N:') as `roll_no`,
    concat(ifnull(`mt`.`from_sort_no`, ''), ':From Sort No:Y:T:') as `from_sort_no`,
    concat(ifnull(`mt`.`to_sort_no`, ''), ':To Sort No:Y:T:') as `to_sort_no`,
    concat(ifnull(`mt`.`from_set_no`, ''), ':From Set No:Y:T:') as `from_set_no`,
    concat(ifnull(`mt`.`to_set_no`, ''), ':To Set No:Y:T:') as `to_set_no`,
    concat(ifnull(`mt`.`from_style`, ''), ':From Style:Y:T:') as `from_style`,
    concat(ifnull(`mt`.`to_style`, ''), ':To Style:Y:T:') as `to_style`,
    concat(ifnull(`mt`.`sizing_beam_no`, ''), ':From Beam No:Y:T:') as `sizing_beam_no`,
    concat(ifnull(`mt`.`to_sizing_beam_no`, ''), ':To Beam No:Y:T:') as `to_sizing_beam_no`,
    concat(ifnull(`mt`.`inspection_mtr`, ''), ':Inspection Mtr.:Y:T:') as `inspection_mtr`,
    concat(ifnull(`mt`.`weight`, ''), ':Inspection Weight:Y:T:') as `weight`,
    concat(ifnull(`mt`.`transfer_by_name`, ''), ':Transfer By:Y:T:') as `transfer_by_name`,
    concat(ifnull(`mt`.`remark`, ''), ':Remarks:Y:T:') as `remark`,
    concat(ifnull(`mt`.`company_name`, ''), ':Company:Y:T:') as `company_name`,
    concat(ifnull(`mt`.`company_branch_name`, ''), ':Branch:Y:T:') as `company_branch_name`,
    concat(ifnull(`mt`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`mt`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`mt`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`mt`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`mt`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`mt`.`sort_transfer_master_id`, ''), ':ID:O:N:') as `sort_transfer_master_id`
from
    `mtv_sort_transfer_details` `mt`
limit 1;


create or replace
algorithm = UNDEFINED view `mtv_sort_transfer_master_rpt` as
select
    concat(ifnull(`mt`.`transfer_no`, ''), ':Transfer No:O:N:') as `transfer_no`,
    concat(ifnull(`mt`.`from_sort_no`, ''), ':From Sort:Y:T:') as `from_sort_no`,
    concat(ifnull(`mt`.`to_sort_no`, ''), ':To Sort:Y:T:') as `to_sort_no`,
    concat(ifnull(`mt`.`transfer_by_name`, ''), ':Transfer By:Y:T:') as `transfer_by_name`,
    concat(ifnull(`mt`.`remark`, ''), ':Remarks:Y:T:') as `remark`,
    concat(ifnull(`mt`.`company_name`, ''), ':Company:Y:T:') as `company_name`,
    concat(ifnull(`mt`.`company_branch_name`, ''), ':Branch:Y:T:') as `company_branch_name`,
    concat(ifnull(`mt`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`mt`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`mt`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`mt`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`mt`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`mt`.`sort_transfer_master_id`, ''), ':ID:O:N:') as `sort_transfer_master_id`
from
    `mtv_sort_transfer_master` `mt`
limit 1;