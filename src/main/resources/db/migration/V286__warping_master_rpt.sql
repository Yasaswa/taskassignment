
create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master_rpt` as
select
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:O:N:') as `set_no`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `warping_production_master_status_desc`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status:O:N:') as `warping_production_master_status`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') as `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`
from
    `xtv_weaving_production_warping_master` `v`
limit 1;