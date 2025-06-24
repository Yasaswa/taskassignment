

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_details_rpt` as
select
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:Y:T:') as `machine_name`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`weaving_production_set_no`, ''), ':Weaving Production Set No:Y:C:xtv_weaving_production_warping_details:O') as `weaving_production_set_no`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:cmv_plant:F') as `plant_name`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:T:') as `production_supervisor_name`,    
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`warping_production_status`, ''), ':Warping Production Status desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Inprogress,Partial)') as `warping_production_status_desc`,
    concat(ifnull(`v`.`actual_count`, ''), ':Actual Count:O:N:') as `actual_count`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:T') as `shift`,
    concat(ifnull(`v`.`creel_ends`, ''), ':Creel Ends:O:N:') as `creel_ends`,
    concat(ifnull(`v`.`weight_per_pkg`, ''), ':Weight Per Pkg:Y:T:') as `weight_per_pkg`,
    concat(ifnull(`v`.`t_ends`, ''), ':T-Ends:Y:T:') as `t_ends`,
    concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
    concat(ifnull(`v`.`breaks_per_million`, ''), ':Breaks Per Million:O:N:') as `breaks_per_million`,
    concat(ifnull(`v`.`warping_production_status`, ''), ':Warping Production Status:O:N:') as `warping_production_status`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status:O:N:') as `warping_production_master_status`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_details_id`, ''), ':Weaving Production Warping Details Id:N:N:') as `weaving_production_warping_details_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Warping Master Id:O:N:') as `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`production_operator_id`, ''), ':Production Operator Id:N:N:') as `production_operator_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`machine_id`, ''), ':Plant Id:N:N:') as `machine_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`
from
    `xtv_weaving_production_warping_details` `v`
limit 1;