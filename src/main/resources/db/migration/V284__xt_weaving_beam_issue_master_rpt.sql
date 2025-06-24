-- xtv_weaving_beam_issue_master source

create or REPLACE algorithm = UNDEFINED view `xtv_weaving_beam_issue_master` as
select
	`xt`.`beam_issue_no` as `beam_issue_no`,
	`xt`.`beam_issue_date` as `beam_issue_date`,
    `xt`.`set_no` as `set_no`,
    `xbit`.`beam_inward_type` as `beam_name`,
    `xt`.`loom_no` as `loom_no`,
    `xt`.`shift_name` as `shift_name`,
    `xt`.`beam_issue_time` as `beam_issue_time`,
    `xt`.`loom_process_type` as `loom_process_type`,
    `xt`.`loom_process_charge` as `loom_process_charge`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`count` as `count`,
    `xt`.`t_ends` as `t_ends`,
    `xt`.`reed` as `reed`,
    `xt`.`pick` as `pick`,
    `xt`.`rs` as `rs`,
    `xt`.`length` as `length`,
    `xt`.`remaining_length` as `remaining_length`,
    `xt`.`cut_beam_date` as `cut_beam_date`,
    `xt`.`cut_beam_reason` as `cut_beam_reason`,
    `xt`.`loom_beam_status` as `loom_beam_status`,
    `xt`.`cut_beam_time` as `cut_beam_time`,
    `ap`.`property_name` as `shift`,
    `e1`.`employee_name` as `beam_issuer_name`,
    `e2`.`employee_name` as `beam_cutter_name`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`branch_address1` as `company_address`,
    `vb`.`branch_pincode` as `company_pincode`,
    `vb`.`branch_phone_no` as `company_phone_no`,
    `vb`.`branch_EmailId` as `company_EmailId`,
    `vb`.`branch_website` as `company_website`,
    `vb`.`branch_gst_no` as `company_gst_no`,
    `vb`.`branch_pan_no` as `company_pan_no`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_beam_issue_master_id` as `weaving_beam_issue_master_id`,
    `xt`.`beam_issuer_id` as `beam_issuer_id`,
    `xt`.`beam_cutter_id` as `beam_cutter_id`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`beam_no` as `beam_no`
from
    ((((((`xt_weaving_beam_issue_master` `xt`
left join `xt_beam_inwards_table` `xbit` on
    (`xbit`.`beam_inwards_id` = `xt`.`beam_no`
        and `xbit`.`company_id` = `xt`.`company_id`
        and `xbit`.`is_delete` = 0))
left join `am_properties` `ap` on
    (`ap`.`property_id` = `xt`.`shift_name`
        and `ap`.`is_delete` = 0))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `xt`.`company_branch_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`beam_issuer_id`
        and `e1`.`is_delete` = 0))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `xt`.`beam_cutter_id`
        and `e2`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;




    -- xt_weaving_beam_issue_master_rpt source
    
    create or REPLACE algorithm = UNDEFINED view `xt_weaving_beam_issue_master_rpt` as
    select
        concat(ifnull(`v`.`beam_issue_no`, ''), ':Beam Issue No:Y:T') as `beam_issue_no`,
        concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
        concat(ifnull(`v`.`beam_name`, ''), ':Beam No:Y:T:') as `beam_name`,
        concat(ifnull(`v`.`loom_no`, ''), ':Loom No:Y:T:') as `loom_no`,
        concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
        concat(ifnull(`v`.`count`, ''), ':Count:Y:T:') as `count`,
        concat(ifnull(`v`.`loom_process_type`, ''), ':Loom Process Type:Y:H:') as `loom_process_type`,
        concat(ifnull(`v`.`loom_process_charge`, ''), ':Loom Process Charge:Y:T:') as `loom_process_charge`,
        concat(ifnull(`v`.`shift`, ''), ':Shift Name:Y:H:(I, II)') as `shift`,
        concat(ifnull(`v`.`beam_issue_date`, ''), ':Beam Issue Date:Y:D:') as `beam_issue_date`,
        concat(ifnull(`v`.`beam_issue_time`, ''), ':Beam Issue Time:Y:D:') as `beam_issue_time`,
        concat(ifnull(`v`.`loom_beam_status`, ''), ':Loom Beam Status:Y:H:(Cut Beams,Full Length )') as `loom_beam_status`,
        concat(ifnull(`v`.`t_ends`, ''), ':No Of Ends:O:N:') as `t_ends`,
        concat(ifnull(`v`.`reed`, ''), ':Reed:O:N::') as `reed`,
        concat(ifnull(`v`.`pick`, ''), ':Pick:O:N:') as `pick`,
        concat(ifnull(`v`.`rs`, ''), ':RS:O:N:') as `rs`,
        concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
        concat(ifnull(`v`.`financial_year`, ''), ':Finance Year:Y:T:amv_financial_year:F') as `financial_year`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `is_active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`weaving_beam_issue_master_id`, ''), ':Weaving Beam Issue Master Id:O:N:') as `weaving_beam_issue_master_id`,
        concat(ifnull(`v`.`beam_issue_no`, ''), ':Beam Issue No:N:N:') as `field_name`,
        concat(ifnull(`v`.`weaving_beam_issue_master_id`, ''), ':Weaving Beam Issue Master Id:N:N:') as `field_id`
    from
        `xtv_weaving_beam_issue_master` `v`
    limit 1;




ALTER TABLE xt_weaving_production_inspection_details ADD dispatch_date varchar(100) NULL;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_details` as
select
    `xt`.`inspection_production_code` as `inspection_production_code`,
    `msomt`.`sales_order_no` as `sales_order_no`,
    `xt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `xt`.`inspection_order_no` as `inspection_order_no`,
    `xt`.`inspection_production_date` as `inspection_production_date`,
    `xt`.`machine_id` as `machine_name`,
    `s`.`godown_name` as `godown_name`,
    case
        `xt`.`inspection_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        when 'DG' then 'Dispatch Note Generated'
        when 'PD' then 'Partial Dispatch'
        when 'D' then 'Dispatch Done'
        else 'Pending'
    end as `inspection_production_status_desc`,
    `xt`.`inspection_production_status` as `inspection_production_status`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`sizing_beam_no` as `sizing_beam_no`,
    `e`.`employee_name` as `production_operator_name`,
    `xt`.`shift` as `shift`,
    `xt`.`product_in_meter` as `product_in_meter`,
    `xt`.`inspection_mtr` as `inspection_mtr`,
    `xt`.`product_pick` as `product_pick`,
    `xt`.`difference` as `difference`,
    `xt`.`width` as `width`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`style` as `style`,
    `xt`.`roll_no` as `roll_no`,
    `xt`.`average` as `average`,
    `xt`.`weight` as `weight`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`product_material_name` as `product_material_name`,
    (
    select
        ifnull(sum(`wpi`.`product_in_meter`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi`
    where
        `wpi`.`inspection_production_status` = 'A'
        and `wpi`.`is_delete` = 0) as `total_product_in_meter`,
    (
    select
        ifnull(sum(`wpi1`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi1`
    where
        `wpi1`.`inspection_production_status` = 'A'
        and `wpi1`.`is_delete` = 0) as `total_inspection_mtr`,
    `xt`.`product_in_meter` - `xt`.`inspection_mtr` as `balance_product_in_meter`,
    (
    select
        ifnull(sum(`wpi2`.`weight`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi2`
    where
        `wpi2`.`inspection_production_status` = 'A'
        and `wpi2`.`is_delete` = 0) as `total_weight`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status_desc`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`status_remark` as `status_remark`,
    `xt`.`dispatch_quantity` as `dispatch_quantity`,
    `xt`.`dispatch_weight` as `dispatch_weight`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_inspection_details_id` as `weaving_production_inspection_details_id`,
    `xt`.`weaving_production_inspection_master_id` as `weaving_production_inspection_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `spt`.`product_type_group` as `product_type_group`,
    `spf`.`product_fg_packing_id` as `product_material_packing_id`,
    `spf`.`product_type_id` as `product_type_id`,
    `spf`.`product_fg_stock_unit_id` as `product_material_stock_unit_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`godown_section_id` as `godown_section_id`,
    `xt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`machine_id` as `machine_id`,
    `xwpo`.`t_ends` as `total_ends`,
    `xt`.`dispatch_date` as `dispatch_date`
from
    ((((((((((`xt_weaving_production_inspection_details` `xt`
left join `xt_weaving_production_inspection_master` `wm` on
    (`wm`.`weaving_production_inspection_master_id` = `xt`.`weaving_production_inspection_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `cm_godown` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `xt`.`product_material_id`
        and `spf`.`is_delete` = 0))
left join `sm_product_type` `spt` on
    (`spt`.`product_type_id` = `spf`.`product_type_id`
        and `spt`.`is_delete` = 0))
left join `xt_warping_production_order` `xwpo` on
    (`xwpo`.`set_no` = `xt`.`inspection_production_set_no`
        and `xwpo`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `msomt` on
    (`msomt`.`customer_order_no` = `xwpo`.`customer_order_no`
        and `msomt`.`is_delete` = 0
        and `msomt`.`company_id` = `xwpo`.`company_id`))
where
    `xt`.`is_delete` = 0;



INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,17,6,31,'Fabric Production Report',6,'Fabric Production Report','Registers','<FrmFabricStockReport/>','import FrmFabricProductionReport from "./Transactions/TPurchaseReports/FabricProductionReport";','Transactions/TPurchaseReports/FabricProductionReport','','','',NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');






