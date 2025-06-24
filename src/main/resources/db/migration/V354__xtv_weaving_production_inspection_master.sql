
create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_master` as
select
    `xt`.`inspection_production_date` as `inspection_production_date`,
    `xt`.`inspection_production_code` as `inspection_production_code`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `xt`.`inspection_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `inspection_production_master_status_desc`,
    `xt`.`inspection_production_master_status` as `inspection_production_master_status`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `wm`.`stock_status` as `stock_status`,
    `wm`.`stock_status_description` as `stock_status_description`,
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
    `vb`.`company_branch_name` as `company_branch_name`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_inspection_master_id` as `weaving_production_inspection_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`
from
    ((((((`xt_weaving_production_inspection_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xt_weaving_production_inspection_details` `wm` on
    (`wm`.`weaving_production_inspection_master_id` = `xt`.`weaving_production_inspection_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `vb` on
    (`vb`.`company_id` = `xt`.`company_id`
        and `vb`.`company_branch_id` = `xt`.`company_branch_id`
        and `vb`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_master_rpt` as
select
    concat(ifnull(`v`.`inspection_production_date`, ''), ':Production Date:Y:D:') as `inspection_production_date`,
    concat(ifnull(`v`.`inspection_production_code`, ''), ':Production Code:Y:T:') as `inspection_production_code`,
    concat(ifnull(`v`.`inspection_production_master_status_desc`, ''), ':Production Status:Y:T:(Pending,Approved,Rejected, Completed,Canceled,Partial Approved)') as `inspection_production_master_status_desc`,
    concat(ifnull(`v`.`stock_status_description`, ''), ':Stock Status:Y:T:') as `stock_status_description`,
    concat(ifnull(`v`.`inspection_production_set_no`, ''), ':Set No:Y:T:') as `inspection_production_set_no`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T:') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`weaving_production_inspection_master_id`, ''), ':Inspection Master Id:O:N:') as `weaving_production_inspection_master_id`
from
    `xtv_weaving_production_inspection_master` `v`
limit 1;




create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_details_rpt` as
select
    concat(ifnull(`v`.`inspection_production_date`, ''), ':Production Date:Y:D:') as `inspection_production_date`,
    concat(ifnull(`v`.`inspection_production_code`, ''), ':Production Code:Y:T:') as `inspection_production_code`,
    concat(ifnull(`v`.`inspection_production_status`, ''), ':Roll Status:Y:H:(Pending, Approved, Rejected, Completed, Canceled, Partial Approved)') as `inspection_production_status_desc`,
    concat(ifnull(`v`.`stock_status_description`, ''), ':Stock Status:Y:T:') as `stock_status_description`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`inspection_production_set_no`, ''), ':Set No:Y:T:') as `inspection_production_set_no`,
    concat(ifnull(`v`.`sizing_beam_no`, ''), ':Sizing Beam No:Y:T:') as `sizing_beam_no`,
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:Y:T:') as `machine_name`,
    concat(ifnull(`v`.`roll_no`, ''), ':Roll No:Y:T:') as `roll_no`,
    concat(ifnull(`v`.`book_type_name`, ''), ':Book Name:O:N') as `book_type_name`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:H:(I,II)') as `shift`,
    concat(ifnull(`v`.`width`, ''), ':Width:O:N:') as `width`,
    concat(ifnull(`v`.`product_pick`, ''), ':Product Pick:O:N:') as `product_pick`,
    concat(ifnull(`v`.`product_in_meter`, ''), ':Product In Meter:O:N:') as `product_in_meter`,
    concat(ifnull(`v`.`inspection_mtr`, ''), ':Inspection Meter:O:N:') as `inspection_mtr`,
    concat(ifnull(`v`.`difference`, ''), ':Difference:O:N:') as `difference`,
    concat(ifnull(`v`.`weight`, ''), ':Weight:O:N:') as `weight`,
    concat(ifnull(`v`.`average`, ''), ':Average:O:N:') as `average`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:Y:T:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:)') as `status_remark`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`weaving_production_inspection_details_id`, ''), ':Inspection Details Id:O:N:') as `weaving_production_inspection_details_id`,
    concat(ifnull(`v`.`weaving_production_inspection_master_id`, ''), ':Inspection Master Id:O:N:') as `weaving_production_inspection_master_id`
from
    `xtv_weaving_production_inspection_details` `v`
limit 1;