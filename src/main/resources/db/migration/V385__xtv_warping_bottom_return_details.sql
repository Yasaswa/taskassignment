
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
    `vb`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`company_address1` as `company_address1`,
    `vb`.`company_address2` as `company_address2`,
    `vb`.`company_phone_no` as `company_phone_no`,
    `vb`.`company_cell_no` as `company_cell_no`,
    `vb`.`company_EmailId` as `company_EmailId`,
    `vb`.`company_website` as `company_website`,
    `vb`.`company_gst_no` as `company_gst_no`,
    `vb`.`company_pan_no` as `company_pan_no`,
    `vb`.`company_pincode` as `company_pincode`,
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
    (((((((`xt_warping_bottom_return_details` `xt`
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
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `xt`.`company_branch_id`
        and `vb`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;