INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,17,6,31,'Cotton Bales Daily Stock Issue Report',9,'Cotton Bales Daily Stock Issue Report','Registers','<FrmCottonBalesDayWiseStockIssueReport/>','import FrmCottonBalesDayWiseStockIssueReport from "Transactions/TPurchaseReports/CottonBalesDayWiseStockIssueReport";','Transactions/TPurchaseReports/CottonBalesDayWiseStockIssueReport','','','',NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


create or replace
algorithm = UNDEFINED view `xtv_beam_inwards_table_rpt` as
select
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
    concat(ifnull(`v`.`beam_inwards_date`, ''), ':Beam Inwards Date:Y:D:') as `beam_inwards_date`,
    concat(ifnull(`v`.`beam_inward_type`, ''), ':Beam Name:Y:T') as `beam_inward_type`,
    concat(ifnull(`v`.`property_value`, ''), ':Beam Type:Y:T') as `property_value`,
    concat(ifnull(`v`.`beam_no`, ''), ':Beam No:Y:T') as `beam_no`,
    concat(ifnull(`v`.`section`, ''), ':Section:O:N:') as `section`,
    concat(ifnull(`v`.`beam_width`, ''), ':Beam Width:Y:T') as `beam_width`,
    concat(ifnull(`v`.`tare_weight`, ''), ':Tare Weight:Y:T') as `tare_weight`,
    concat(ifnull(`v`.`beam_status`, ''), ':Beam Status:Y:H:(Empty,Completed)') as `beam_status_desc`,
    concat(ifnull(`v`.`customer_short_name`, ''), ':Customer Short Name:O:N:') as `customer_short_name`,
    concat(case when ifnull(`v`.`is_delete`, 0) = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') as `customer_id`,
    concat(ifnull(`v`.`beam_inwards_id`, ''), ':Beam Inwards Id:O:N:') as `beam_inwards_id`
from
    `xtv_beam_inwards_table` `v`
limit 1;