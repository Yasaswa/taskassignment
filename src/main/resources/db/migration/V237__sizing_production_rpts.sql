

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_master_rpt` as
select
    concat(ifnull(`v`.`set_no`, ''), ':Weaving Production Set No:Y:C:xtv_weaving_production_sizing_details:O') as `set_no`,
    concat(ifnull(`v`.`sizing_production_date`, ''), ':Sizing Production Date:Y:D:') as `sizing_production_date`,
    concat(ifnull(`v`.`sizing_production_code`, ''), ':Sizing Production Code:O:N:') as `sizing_production_code`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `sizing_production_master_status_desc`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:O:N:') as `sizing_production_master_status`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`weaving_production_sizing_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') as `weaving_production_sizing_master_id`
from
    `xtv_weaving_production_sizing_master` `v`
limit 1;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_details_rpt` as
select
    concat(ifnull(`v`.`set_no`, ''), ':Weaving Production Set No:Y:C:xtv_weaving_production_sizing_details:O') as `set_no`,
    concat(ifnull(`v`.`sizing_production_date`, ''), ':sizing Production Date:Y:D:') as `sizing_production_date`,
    concat(ifnull(`v`.`warping_length`, ''), ':Warping Length:Y:T:') as `warping_length`,
    concat(ifnull(`v`.`sizing_beam_name`, ''), ':Sizing Beam:O:N:') as `sizing_beam_name`,
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
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`sizing_production_status`, ''), ':Warping Production Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial)') as `sizing_production_status_desc`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `sizing_production_master_status_desc`,
    concat(ifnull(`v`.`sizing_production_status`, ''), ':Sizing Production Status:O:N:)') as `sizing_production_status`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:O:N:') as `sizing_production_master_status`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:)') as `status_remark`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`is_active`, ''), ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(`v`.`is_delete`, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_sizing_master_id`, ''), ':Production Id:O:N:') as `weaving_production_sizing_master_id`
from
    `xtv_weaving_production_sizing_details` `v`
limit 1;