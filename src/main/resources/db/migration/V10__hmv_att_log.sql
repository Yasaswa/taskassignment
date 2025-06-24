-- 20-08-2024
create or replace view hmv_att_log as
select
    att_log_transaction_id as transaction_id,
    att_device_id,
    att_device_ip,
    att_device_emp_code,
    att_device_transaction_id,
    att_date_time,
    att_date_added,
    att_punch_status,
    att_punch_type,
    'hm_att_log' as source_table
from
    hm_att_log
union all
select
    att_log2_transaction_id as transaction_id,
    att_device_id,
    att_device_ip,
    att_device_emp_code,
    att_device_transaction_id,
    att_date_time,
    att_date_added,
    att_punch_status,
    att_punch_type,
    'hm_att_log2' as source_table
from
    hm_att_log2;

-- Changes from vishal
insert into am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) values
     (22,'ProductTypeGroups',1,'PRM','Product Raw Material',1,0,'dakshabhiadmin','2024-08-18 13:32:15.000',null,'2024-08-18 13:32:15.000',null,null,'','');
insert into sm_product_type (company_id,product_type_name,product_type_short_name,group_code,product_type_group,remark,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) values
     (1,'Raw Material','PRM','','PRM','',1,0,'dakshabhiadmin','2024-08-18 13:34:18.000',null,'2024-08-18 13:34:18.000',null,null);
insert into am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) values
     (1,1,1,3,1,6,'Raw Materials',2,'Raw Materials','Masters','<FrmMRawMaterialProductsListing />','import FrmMRawMaterialProductsListing from "./Masters/MRawMaterialProducts/FrmMRawMaterialProductsListing";','/Masters/MRawMaterialProducts/FrmMRawMaterialProductsListing','<FrmMRawMaterialProducts />','import FrmMRawMaterialProducts from "./Masters/MRawMaterialProducts/FrmMRawMaterialProducts";','/Masters/MRawMaterialProducts/FrmMRawMaterialProducts',null,'Raw Materials',0,1,0,null,null,null,null,null,null,0,1,1,'');

update am_modules_forms
set url_parameter = "RM"
where modules_forms_id = 83;
update am_modules_forms
set url_parameter = "PRM"
where modules_forms_id = 642;

insert into am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) values
     (1,'yarnSubClassificationOfValueAddedProductAndFibers',0,0,null,null,null,null,null,null,null);
insert into am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) values
     (1,'yarnDenierOrCount',0,0,null,null,null,null,null,null,null);

alter table sm_product_type_dynamic_controls add product_category_id BIGINT(20) NULL;

-- smv_product_type_dynamic_controls source
create or replace
algorithm = UNDEFINED view `smv_product_type_dynamic_controls` as
select
    `sm`.`control_master` as `control_master`,
    `sm`.`control_name` as `control_name`,
    `sm`.`control_type` as `control_type`,
    `p`.`product_type_name` as `product_type_name`,
    `p`.`product_type_group` as `product_type_group`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `v`.`company_legal_name` as `company_name`,
    `sm`.`display_sequence` as `display_sequence`,
    `sm`.`remark` as `remark`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_type_dynamic_controls_id` as `product_type_dynamic_controls_id`,
    `sm`.`product_category_id` as `product_category_id`,
    `sm`.`product_type_dynamic_controls_id` as `field_id`,
    `sm`.`control_name` as `field_name`
from
    ((`sm_product_type_dynamic_controls` `sm`
left join `sm_product_type` `p` on
    (`p`.`company_id` = `sm`.`company_id`
        and `p`.`product_type_id` = `sm`.`product_type_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `sm`.`company_id`))
where
    `sm`.`is_delete` = 0;