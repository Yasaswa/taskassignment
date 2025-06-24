INSERT INTO sm_product_type (company_id,product_type_name,product_type_short_name,group_code,product_type_group,remark,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,'Sized Yarn','SB','','PRM','',1,0,'6260537025','2024-11-18 16:37:11.000','6260537025','2024-11-18 16:37:28.000',NULL,NULL);

UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=19, modules_menu_id=3, modules_sub_menu_id=16, modules_forms_name='Sales Order (Trading)', display_sequence=1, display_name='Sales Order (Trading)', menu_type='Sales', listing_component_name='<SalesOrderListing />', listing_component_import_path='import SalesOrderListing from "Transactions/TSalesOrder/SalesOrderListing";', listing_navigation_url='/Transactions/TSalesOrder/SalesOrderListing', form_component_name='<SalesOrderEntry />', form_component_import_path='import SalesOrderEntry from "Transactions/TSalesOrder/SalesOrderEntry";', form_navigation_url='/Transactions/TSalesOrder/SalesOrderEntry', icon_class=NULL, page_header='', is_selected=0, is_active=1, is_delete=1, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='NA' WHERE modules_forms_id=120;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=19, modules_menu_id=3, modules_sub_menu_id=16, modules_forms_name='Work Order (Trading)', display_sequence=6, display_name='Work Order (Trading)', menu_type='Sales', listing_component_name='<FrmWorkOrderListing />', listing_component_import_path='import FrmWorkOrderListing from "Transactions/TWorkOrderTrading/FrmWorkOrderListing";', listing_navigation_url='/Transactions/TWorkOrder/Trading/WorkOrderListing', form_component_name='', form_component_import_path='', form_navigation_url='', icon_class=NULL, page_header='', is_selected=0, is_active=1, is_delete=1, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter=NULL WHERE modules_forms_id=123;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=19, modules_menu_id=3, modules_sub_menu_id=16, modules_forms_name='Sales Order (Yarn)', display_sequence=2, display_name='Sales Order (Yarn)', menu_type='Sales', listing_component_name='<SalesOrderListing />', listing_component_import_path='import SalesOrderListing from "Transactions/TSalesOrder/SalesOrderListing";', listing_navigation_url='/Transactions/TSalesOrder/SalesOrderListing', form_component_name='<SalesOrderEntry />', form_component_import_path='import SalesOrderEntry from "Transactions/TSalesOrder/SalesOrderEntry";', form_navigation_url='/Transactions/TSalesOrder/SalesOrderEntry', icon_class=NULL, page_header='', is_selected=0, is_active=1, is_delete=1, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='YN' WHERE modules_forms_id=286;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=19, modules_menu_id=3, modules_sub_menu_id=16, modules_forms_name='Sales Order (Finish Fabric)', display_sequence=5, display_name='Sales Order (Finish Fabric)', menu_type='Sales', listing_component_name='<SalesOrderListing />', listing_component_import_path='import SalesOrderListing from "Transactions/TSalesOrder/SalesOrderListing";', listing_navigation_url='/Transactions/TSalesOrder/SalesOrderListing', form_component_name='<SalesOrderEntry />', form_component_import_path='import SalesOrderEntry from "Transactions/TSalesOrder/SalesOrderEntry";', form_navigation_url='/Transactions/TSalesOrder/SalesOrderEntry', icon_class=NULL, page_header='', is_selected=0, is_active=1, is_delete=1, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='FF' WHERE modules_forms_id=287;

UPDATE am_modules_forms_user_access SET is_delete = 1 WHERE modules_forms_id=120;
UPDATE am_modules_forms_user_access SET is_delete = 1 WHERE modules_forms_id=123;
UPDATE am_modules_forms_user_access SET is_delete = 1 WHERE modules_forms_id=286;
UPDATE am_modules_forms_user_access SET is_delete = 1 WHERE modules_forms_id=287;


UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=19, modules_menu_id=3, modules_sub_menu_id=16, modules_forms_name='Sales Order (Sized Yarn)', display_sequence=4, display_name='Sales Order (Sized Yarn)', menu_type='Sales', listing_component_name='<SalesOrderListing />', listing_component_import_path='import SalesOrderListing from "Transactions/TSalesOrder/SalesOrderListing";', listing_navigation_url='/Transactions/TSalesOrder/SalesOrderListing', form_component_name='<SalesOrderEntry />', form_component_import_path='import SalesOrderEntry from "Transactions/TSalesOrder/SalesOrderEntry";', form_navigation_url='/Transactions/TSalesOrder/SalesOrderEntry', icon_class=NULL, page_header='Sales Order (Sized Beam)', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='SB' WHERE modules_forms_id=290;

ALTER TABLE xt_warping_production_order ADD production_order_type_id bigint(20) NULL;

-- erp_dev_temp.xtv_warping_production_order source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `xt`.`warping_order_status` as `warping_order_status`,
    `xt`.`warping_schedule_date` as `warping_schedule_date`,
    `xt`.`warping_plan_date` as `warping_plan_date`,
    `xt`.`schedule_quantity` as `schedule_quantity`,
    `xt`.`order_quantity` as `order_quantity`,
    `xt`.`other_terms_conditions` as `other_terms_conditions`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`set_length` as `set_length`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `xt`.`product_material_style` as `product_material_style`,
    case
        `xt`.`warping_order_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'I' then 'Partial'
    end as `warping_order_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`status_remark` as `status_remark`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `xt`.`remark` as `remark`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
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
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`production_order_type_id` as `production_order_type_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`approved_by_id` as `approved_by_id`,
    `xt`.`warping_production_order_id` as `field_id`
from
    (((`xt_warping_production_order` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `mt` on
    (`mt`.`customer_order_no` = `xt`.`customer_order_no`
        and `mt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;