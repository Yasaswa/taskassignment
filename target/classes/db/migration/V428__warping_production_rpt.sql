ALTER TABLE xt_warping_production_order ADD creel_type varchar(50) NULL;


create or replace
algorithm = UNDEFINED view `xtv_warping_production_order` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`job_type` as `job_type`,
    `xt`.`warping_order_status` as `warping_order_status`,
    `xt`.`warping_schedule_date` as `warping_schedule_date`,
    `xt`.`warping_plan_date` as `warping_plan_date`,
    `xt`.`schedule_quantity` as `schedule_quantity`,
    `xt`.`order_quantity` as `order_quantity`,
    `xt`.`creel_type` as `creel_type`,
    `xt`.`other_terms_conditions` as `other_terms_conditions`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`set_length` as `set_length`,
    case
        when `xt`.`production_order_type_id` in (2, 11, 12, 13) then `spr`.`product_rm_name`
        else `spf`.`product_fg_name`
    end as `product_material_name`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`production_order_type_id` as `production_order_type_id`,
    `xt`.`product_material_style` as `product_material_style`,
    `xt`.`t_ends` as `t_ends`,
    case
        when `xt`.`warping_order_status` = 'P' then 'Pending'
        when `xt`.`warping_order_status` = 'A' then 'Approved'
        when `xt`.`warping_order_status` = 'R' then 'Rejected'
        when `xt`.`warping_order_status` = 'C' then 'Completed'
        when `xt`.`warping_order_status` = 'X' then 'Canceled'
        when `xt`.`warping_order_status` = 'I' then 'Partial'
    end as `warping_order_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`status_remark` as `status_remark`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `xt`.`bottom_value` as `bottom_value`,
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
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`approved_by_id` as `approved_by_id`,
    `xt`.`warping_production_order_id` as `field_id`
from
    ((((`xt_warping_production_order` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `mt` on
    (`mt`.`customer_order_no` = `xt`.`customer_order_no`
        and `mt`.`is_delete` = 0))
left join `sm_product_rm` `spr` on
    (`spr`.`product_rm_id` = `xt`.`product_material_id`
        and `xt`.`production_order_type_id` in (2, 11, 12, 13)
            and `spr`.`is_delete` = 0))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `xt`.`product_material_id`
        and `xt`.`production_order_type_id` not in (2, 11, 12, 13)
            and `spf`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`warping_production_order_id` desc;


create or replace
algorithm = UNDEFINED view `xtv_warping_production_order_rpt` as
select
    concat(ifnull(`v`.`warping_order_no`, ''), ':Warping Order No:Y:T:') as `warping_order_no`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(ifnull(`v`.`set_length`, ''), ':Set Length:O:N:') as `set_length`,
    concat(ifnull(`v`.`t_ends`, ''), ':Total Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:C:xtv_warping_production_order:O') as `product_material_name`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`schedule_quantity`, ''), ':Schedule Quantity:O:N:') as `schedule_quantity`,
    concat(ifnull(`v`.`warping_order_status_desc`, ''), ':Warping Order Status:Y:C:xtv_warping_production_order:O') as `warping_order_status_desc`,
        concat(ifnull(`v`.`warping_schedule_date`, ''), ':Schedule Date:Y:D:') as `warping_schedule_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:C:xtv_warping_production_order:O:') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`warping_production_order_id`, ''), ':Warping Production Order Id:O:N:') as `warping_production_order_id`
from
    `xtv_warping_production_order` `v`
limit 1;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master_rpt` as
select
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:O:N:') as `set_no`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
    concat(ifnull(`v`.`no_of_beams`, ''), ':No Of Beams:O:N:') as `no_of_beams`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
        concat(ifnull(`v`.`set_length`, ''), ':Set Length:O:N:') as `set_length`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') as `weaving_production_warping_master_id`
from
    `xtv_weaving_production_warping_master` `v`
limit 1;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_details_rpt` as
select
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:Y:C:xtv_weaving_production_warping_details:O') as `machine_name`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:T:') as `production_supervisor_name`,
    concat(ifnull(`v`.`actual_count`, ''), ':Actual Count:O:N:') as `actual_count`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:C:xtv_weaving_production_warping_details:O') as `shift`,
    concat(ifnull(`v`.`creel_ends`, ''), ':Creel Ends:O:N:') as `creel_ends`,
    concat(ifnull(`v`.`weight_per_pkg`, ''), ':Weight Per Pkg:O:N:') as `weight_per_pkg`,
    concat(ifnull(`v`.`t_ends`, ''), ':T-Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
    concat(ifnull(`v`.`breaks_per_million`, ''), ':Breaks Per Million:O:N:') as `breaks_per_million`,
     concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:xtv_weaving_production_warping_details:O') as `plant_name`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:Y:C:xt_weaving_production_warping_details:O') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`warping_production_status`, ''), ':Warping Production Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Inprogress,Partial)') as `warping_production_status_desc`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Warping Master Id:O:N:') as `weaving_production_warping_master_id`
from
    `xtv_weaving_production_warping_details` `v`
limit 1;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_master_rpt` as
select
    concat(ifnull(`v`.`set_no`, ''), ':Weaving Production Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`sizing_production_date`, ''), ':Sizing Production Date:Y:D:') as `sizing_production_date`,
    concat(ifnull(`v`.`sizing_production_code`, ''), ':Sizing Production Code:O:N:') as `sizing_production_code`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:xtv_weaving_production_sizing_master:O') as `production_supervisor_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `sizing_production_master_status_desc`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`weaving_production_sizing_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') as `weaving_production_sizing_master_id`
from
    `xtv_weaving_production_sizing_master` `v`
limit 1;



create or replace
algorithm = UNDEFINED view `xt_weaving_beam_issue_master_rpt` as
select
    concat(ifnull(`v`.`beam_issue_no`, ''), ':Beam Issue No:Y:T') as `beam_issue_no`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`beam_name`, ''), ':Beam No:Y:T:') as `beam_name`,
    concat(ifnull(`v`.`beam_issue_date`, ''), ':Beam Issue Date:Y:D:') as `beam_issue_date`,
    concat(ifnull(`v`.`beam_issue_time`, ''), ':Beam Issue Time:Y:D:') as `beam_issue_time`,
    concat(ifnull(`v`.`loom_beam_status`, ''), ':Loom Beam Status:Y:H:(Cut Beams,Full Length )') as `loom_beam_status`,
    concat(ifnull(`v`.`loom_no`, ''), ':Loom No:Y:T:') as `loom_no`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`count`, ''), ':Count:Y:T:') as `count`,
    concat(ifnull(`v`.`loom_process_type`, ''), ':Loom Process Type:Y:H:') as `loom_process_type`,
    concat(ifnull(`v`.`loom_process_charge`, ''), ':Loom Process Charge:Y:T:') as `loom_process_charge`,
    concat(ifnull(`v`.`shift`, ''), ':Shift Name:Y:H:(I, II)') as `shift`,
    concat(ifnull(`v`.`t_ends`, ''), ':No Of Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`reed`, ''), ':Reed:O:N::') as `reed`,
    concat(ifnull(`v`.`pick`, ''), ':Pick:O:N:') as `pick`,
    concat(ifnull(`v`.`rs`, ''), ':RS:O:N:') as `rs`,
    concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
    concat(ifnull(`v`.`financial_year`, ''), ':Finance Year:Y:T:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_beam_issue_master_id`, ''), ':Weaving Beam Issue Master Id:O:N:') as `weaving_beam_issue_master_id`
from
    `xtv_weaving_beam_issue_master` `v`
limit 1;