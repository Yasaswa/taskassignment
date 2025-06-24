INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (192,'LoomProcessType',2,'RE-KNOTTING','RKN',1,0,'6260537025','2025-04-21 16:21:40.000',NULL,'2025-04-21 16:21:40.000',NULL,NULL,'','');


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_production_date` as `warping_production_date`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `e`.`employee_name` as `production_supervisor_name`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `xt`.`warping_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `warping_production_master_status_desc`,
    `xt`.`warping_production_master_status` as `warping_production_master_status`,
    (
    select
        ifnull(sum(`cr`.`no_of_beams`), 0)
    from
        `xt_warping_production_order_creels` `cr`
    where
        `cr`.`set_no` = `xt`.`set_no`
        and `cr`.`is_delete` = 0) as `no_of_beams`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `cs`.`supp_branch_name` as `supplier_name`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`weaving_production_warping_master_id` as `field_id`,
    `xt`.`warping_production_code` as `field_name`,
    `xt`.`calculative_bottom_kg` as `calculative_bottom_kg`,
    `xt`.`calculative_bottom_percent` as `calculative_bottom_percent`,
    `xt`.`actual_bottom_kg` as `actual_bottom_kg`,
    `xt`.`actual_bottom_percent` as `actual_bottom_percent`,
    `xt`.`difference_bottom_kg` as `difference_bottom_kg`,
    `xt`.`difference_bottom_percent` as `difference_bottom_percent`,
    `simis`.`warping_issue_kg` as `warping_issue_kg`,
    `simis`.`warping_issue_quantity` as `warping_issue_quantity`,
    `simis`.`warping_issue_boxes` as `warping_issue_boxes`,
    `xt`.`batch_no` as `batch_no`,
    `xt`.`print_status` as `print_status`,
    `wo`.`customer_order_no` as `customer_order_no`,
    `wo`.`warping_order_no` as `warping_order_no`,
    `wo`.`product_material_id` as `product_material_id`,
    case
        when `wo`.`production_order_type_id` in (2, 11, 12, 13) then `spr`.`product_rm_name`
        else `spf`.`product_fg_name`
    end as `product_material_name`,
    `wo`.`product_material_style` as `product_material_style`,
    `wo`.`schedule_quantity` as `schedule_quantity`,
    `wo`.`sort_no` as `sort_no`,
    `wo`.`no_of_creels` as `no_of_creels`,
    `wo`.`set_length` as `set_length`
from
    ((((((((((`xt_weaving_production_warping_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `cm_supplier_branch` `cs` on
    (`cs`.`supp_branch_id` = `xt`.`supplier_id`
        and `cs`.`is_delete` = 0))
left join `xt_warping_production_order` `wo` on
    (`wo`.`set_no` = `xt`.`set_no`
        and `wo`.`company_id` = `xt`.`company_id`))
left join (
    select
        `st_indent_material_issue_details`.`set_no` as `set_no`,
        sum(`st_indent_material_issue_details`.`product_material_issue_weight`) as `warping_issue_kg`,
        sum(`st_indent_material_issue_details`.`product_material_issue_quantity`) as `warping_issue_quantity`,
        sum(`st_indent_material_issue_details`.`product_material_issue_boxes`) as `warping_issue_boxes`
    from
        `st_indent_material_issue_details`
    where
        `st_indent_material_issue_details`.`is_delete` = 0
    group by
        `st_indent_material_issue_details`.`set_no`) `simis` on
    (`simis`.`set_no` = `xt`.`set_no`))
left join `sm_product_rm` `spr` on
    (`spr`.`product_rm_id` = `wo`.`product_material_id`
        and `wo`.`production_order_type_id` in (2, 11, 12, 13)
            and `spr`.`is_delete` = 0))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `wo`.`product_material_id`
        and `wo`.`production_order_type_id` not in (2, 11, 12, 13)
            and `spf`.`is_delete` = 0))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `xt`.`company_branch_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_supervisor_id`
        and `e`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_master_id` desc;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_details_rpt` as
select
    concat(ifnull(`v`.`set_no`, ''), ':Weaving Production Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`sizing_production_date`, ''), ':sizing Production Date:Y:D:') as `sizing_production_date`,
    concat(ifnull(`v`.`warping_length`, ''), ':Warping Length:Y:T:') as `warping_length`,
    concat(ifnull(`v`.`sizing_beam_name`, ''), ':Sizing Beam:Y:T:') as `sizing_beam_name`,
    concat(ifnull(`v`.`sizing_length`, ''), ':Sizing Length:Y:T:') as `sizing_length`,
    concat(ifnull(`v`.`yarn_count`, ''), ':Yarn Count:Y:T:') as `yarn_count`,
    concat(ifnull(`v`.`actual_count`, ''), ':Actual Count:Y:T:') as `actual_count`,
    concat(ifnull(`v`.`total_ends`, ''), ':Total Ends:O:N:') as `total_ends`,
    concat(ifnull(`v`.`gross_weight`, ''), ':Gross Weight:Y:T:') as `gross_weight`,
    concat(ifnull(`v`.`tare_weight`, ''), ':Tare Weight:Y:T:') as `tare_weight`,
    concat(ifnull(`v`.`net_weight`, ''), ':Net Weight:Y:T:') as `net_weight`,
    concat(ifnull(`v`.`production_operator_name`, ''), ':Production Operator Name:O:N:') as `production_operator_name`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:C:xtv_weaving_production_warping_details:O') as `shift`,
    concat(ifnull(`v`.`rate`, ''), ':Rate:Y:T:') as `rate`,
    concat(ifnull(`v`.`amount`, ''), ':Amount:Y:T:') as `amount`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:cmv_plant:F') as `plant_name`,
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:O:N:') as `machine_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:O:N:') as `production_supervisor_name`,
    concat(ifnull(`v`.`sizing_production_code`, ''), ':Sizing Production Code:O:N:') as `sizing_production_code`,
    concat(ifnull(`v`.`sizing_production_status`, ''), ':Warping Production Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial)') as `sizing_production_status_desc`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `sizing_production_master_status_desc`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`is_active`, ''), ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(`v`.`is_delete`, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_sizing_master_id`, ''), ':Production Id:O:N:') as `weaving_production_sizing_master_id`
from
    `xtv_weaving_production_sizing_details` `v`
limit 1;