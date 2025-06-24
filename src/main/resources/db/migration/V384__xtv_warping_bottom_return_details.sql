ALTER TABLE xt_warping_bottom_return_details ADD actual_gross_weight decimal(18,2) DEFAULT 0.0000 NULL;
ALTER TABLE xt_warping_bottom_return_details ADD actual_net_weight decimal(18,2) DEFAULT 0.0000 NULL;
ALTER TABLE xt_warping_bottom_return_details MODIFY COLUMN net_weight decimal(18,2) DEFAULT 0.0000 NOT NULL;
ALTER TABLE xt_warping_bottom_return_details MODIFY COLUMN gross_weight decimal(18,2) DEFAULT 0.0000 NOT NULL;
ALTER TABLE xt_warping_bottom_return_details MODIFY COLUMN tare_weight decimal(18,2) DEFAULT 0.0000 NOT NULL;






-- xtv_warping_bottom_return_details source

create or replace
algorithm = UNDEFINED view `xtv_warping_bottom_return_details` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`bottom_return_no` as `bottom_return_no`,
    `xt`.`warping_bottom_return_details_id` as `warping_bottom_return_details_id`,
    `xt`.`weaving_production_warping_bottom_details_id` as `weaving_production_warping_bottom_details_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`bottom_receipt_date` as `bottom_receipt_date`,
    `xt`.`bottom_return_date` as `bottom_return_date`,
    `xt`.`warping_bottom_details_production_date` as `warping_bottom_details_production_date`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `xt`.`creel_no` as `creel_no`,
    `xt`.`no_of_package` as `no_of_package`,
    `xt`.`gross_weight` as `gross_weight`,
    `xt`.`tare_weight` as `tare_weight`,
    `xt`.`net_weight` as `net_weight`,
    `xt`.`actual_gross_weight` as `actual_gross_weight`,
    `xt`.`actual_net_weight` as `actual_net_weight`,
    `xt`.`package_type` as `package_type`,
    `xt`.`cone_type_value` as `cone_type_value`,
    `xt`.`sr_no` as `sr_no`,
    `xt`.`batch_no` as `batch_no`,
    `xt`.`weight_per_pkg` as `weight_per_pkg`,
    `xt`.`no_of_boxes` as `no_of_boxes`,
    `xt`.`customer_name` as `customer_name`,
    `xt`.`supplier_name` as `supplier_name`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`godown_section_id` as `godown_section_id`,
    `xt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `xt`.`bottom_return_type_id` as `bottom_return_type_id`,
    `xt`.`bottom_return_type` as `bottom_return_type`,
    `xt`.`return_by_id` as `return_by_id`,
    `xt`.`received_by_id` as `received_by_id`,
    `xt`.`department_id` as `department_id`,
    `xt`.`sub_department_id` as `sub_department_id`,
    `xt`.`goods_receipt_no` as `goods_receipt_no`,
    `xt`.`cone_type` as `cone_type`,
    `xt`.`bora_box` as `bora_box`,
    `e`.`employee_name` as `received_by`,
    `e1`.`employee_name` as `returned_by`,
    `gn`.`godown_name` as `godown_name`,
    `gns`.`godown_section_name` as `godown_section_name`,
    `gnss`.`godown_section_beans_name` as `godown_section_beans_name`,
    `xt`.`bottom_return_item_status` as `bottom_return_item_status`,
    `sm`.`product_rm_name` as `product_rm_name`,
    case
        when `xt`.`bottom_return_item_status` = 'P' then 'Pending'
        when `xt`.`bottom_return_item_status` = 'R' then 'Received'
    end as `bottom_return_item_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`remark` as `remark`,
    case
        when `xt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`warping_bottom_return_details_id` as `field_id`
from
    ((((((`xt_warping_bottom_return_details` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`return_by_id`
        and `e`.`is_delete` = 0))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`received_by_id`
        and `e1`.`is_delete` = 0))
left join `sm_product_rm` `sm` on
    (`sm`.`product_rm_id` = `xt`.`product_material_id`
        and `sm`.`is_delete` = 0))
left join `cm_godown` `gn` on
    (`gn`.`godown_id` = `xt`.`godown_id`
        and `gn`.`is_delete` = 0))
left join `cm_godown_section` `gns` on
    (`gns`.`godown_section_id` = `xt`.`godown_section_id`
        and `gns`.`is_delete` = 0))
left join `cm_godown_section_beans` `gnss` on
    (`gnss`.`godown_section_beans_id` = `xt`.`godown_section_beans_id`
        and `gnss`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;







-- xtv_warping_bottom_return_details_rpt source

create or replace
algorithm = UNDEFINED view `xtv_warping_bottom_return_details_rpt` as
select
    concat(ifnull(`psl`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`psl`.`bottom_return_no`, ''), ':Bottom Return No:Y:T:') as `bottom_return_no`,
    concat(ifnull(`psl`.`bottom_return_date`, ''), ':Bottom Return Date:Y:D:') as `bottom_return_date`,
    concat(ifnull(`psl`.`warping_production_code`, ''), ':Warping Production Code:Y:T:') as `warping_production_code`,
    concat(ifnull(`psl`.`sr_no`, ''), ':SR NO:Y:T:') as `sr_no`,
    concat(ifnull(`psl`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
    concat(ifnull(`psl`.`supplier_name`, ''), ':Supplier Name:Y:T:') as `supplier_name`,
    concat(ifnull(`psl`.`no_of_package`, ''), ':No of Package:Y:T:') as `no_of_package`,
    concat(ifnull(`psl`.`gross_weight`, ''), ':GR.WT:Y:T:') as `gross_weight`,
    concat(ifnull(`psl`.`tare_weight`, ''), ':TR.WT.:Y:T:') as `tare_weight`,
    concat(ifnull(`psl`.`net_weight`, ''), ':NT.WT.:Y:T:') as `net_weight`,
    concat(ifnull(`psl`.`actual_gross_weight`, ''), ':Rec.GR.WT.:Y:T:') as `actual_gross_weight`,
    concat(ifnull(`psl`.`actual_net_weight`, ''), ':Rec.NT.WT.:Y:T:') as `actual_net_weight`,
    concat(ifnull(`psl`.`cone_type`, ''), ':Cone Type:Y:T:') as `cone_type`,
    concat(ifnull(`psl`.`bora_box`, ''), ':Bora/Box:Y:T:') as `bora_box`,
    concat(ifnull(`psl`.`bottom_return_item_status_desc`, ''), ':Bottom Return Status:Y:T:') as `bottom_return_item_status_desc`,
    concat(ifnull(`psl`.`returned_by`, ''), ':Returned By:Y:T:') as `returned_by`,
    concat(ifnull(`psl`.`received_by`, ''), ':Received By:Y:T:') as `received_by`,
    concat(ifnull(`psl`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`psl`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`psl`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`psl`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(case when `psl`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`psl`.`deleted_by`, ''), ':Deleted By:N:N:') as `deleted_by`,
    concat(ifnull(`psl`.`deleted_on`, ''), ':Deleted On:N:N:') as `deleted_on`,
    concat(ifnull(`psl`.`warping_bottom_return_details_id`, ''), ':Warping Bottom Return Id:O:N:') as `warping_bottom_return_details_id`,
    concat(ifnull(`psl`.`weaving_production_warping_bottom_details_id`, ''), ':Warping Production Bottom Id:N:N:') as `weaving_production_warping_bottom_details_id`,
    concat(ifnull(`psl`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`psl`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`
from
    `xtv_warping_bottom_return_details` `psl`
limit 1;

