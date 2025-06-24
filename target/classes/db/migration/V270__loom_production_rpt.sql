
create or replace
algorithm = UNDEFINED view `xtv_weaving_production_loom_master_rpt` as
select
    concat(ifnull(`v`.`loom_production_code`, ''), ':Loom Production Code:O:N:') as `loom_production_code`,
    concat(ifnull(`v`.`loom_production_date`, ''), ':Loom Production Date:Y:D:') as `loom_production_date`,
    concat(ifnull(`v`.`data_import_date`, ''), ':Data Import Date:Y:D:') as `data_import_date`,
    concat(ifnull(`v`.`data_import_time`, ''), ':Data Import Time:Y:D:') as `data_import_time`,
    concat(ifnull(`v`.`data_import_file`, ''), ':Data Import File:Y:D:') as `data_import_file`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`total_prodcut_1000pick`, ''), ':Total Prodcut 1000Pick:Y:T:') as `total_prodcut_1000pick`,
    concat(ifnull(`v`.`total_product_in_meter`, ''), ':Total Product In Meter:Y:T:') as `total_product_in_meter`,
    concat(ifnull(`v`.`total_run_in_min`, ''), ':Total Run In Min:Y:T:') as `total_run_in_min`,
    concat(ifnull(`v`.`total_stop_in_min`, ''), ':Total Stop In Min :Y:T:') as `total_stop_in_min`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
    concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `loom_production_master_status_desc`,
    concat(ifnull(`v`.`loom_production_remark`, ''), ':Loom Production Remark:O:N:)') as `loom_production_remark`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status:O:N:') as `loom_production_master_status`,
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
    concat(ifnull(`v`.`weaving_production_loom_master_id`, ''), ':Weaving Production Loom Master Id:O:N:') as `weaving_production_loom_master_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`
from
    `xtv_weaving_production_loom_master` `v`
limit 1;