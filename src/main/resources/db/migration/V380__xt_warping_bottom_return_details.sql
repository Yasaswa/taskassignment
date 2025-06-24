ALTER TABLE sm_warping_bottom_stock_details CHANGE bottom_return_id warping_bottom_return_details_id bigint(20) DEFAULT 0 NULL;


ALTER TABLE sm_warping_bottom_stock_details ADD bora_box varchar(100) NULL;

ALTER TABLE xt_weaving_production_warping_bottom_details ADD material_status varchar(100) DEFAULT 'Pending' NULL;



CREATE TABLE `xt_warping_bottom_return_details` (
  `company_id` int(11) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `company_branch_id` bigint(20) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `financial_year` varchar(20) NOT NULL COMMENT '* Header part will come from Session*',
  `set_no` varchar(250) DEFAULT NULL,
  `warping_bottom_return_details_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* Bottom Details auto generated *',
  `bottom_return_no` varchar(255) DEFAULT NULL,
  `bottom_receipt_date` date DEFAULT NULL,
  `bottom_return_date` date DEFAULT NULL,
  `product_material_id` varchar(55) DEFAULT NULL,
  `weaving_production_warping_bottom_details_id` bigint(20) DEFAULT NULL,
  `weaving_production_warping_master_id` bigint(20) DEFAULT 0,
  `warping_production_code` varchar(255) NOT NULL,
  `creel_no` varchar(255) DEFAULT NULL,
  `no_of_package` decimal(18,2) DEFAULT NULL,
  `gross_weight` decimal(18,2) DEFAULT NULL,
  `net_weight` decimal(18,2) DEFAULT NULL,
  `tare_weight` decimal(18,2) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `package_type` varchar(500) DEFAULT NULL,
  `cone_type_value` decimal(18,4) DEFAULT 0.0000,
  `warping_bottom_details_production_date` date NOT NULL,
  `sr_no` varchar(500) DEFAULT NULL,
  `batch_no` varchar(500) DEFAULT NULL,
  `bottom_return_item_status` varchar(3) DEFAULT 'P' COMMENT '* Pending, Received*',
  `weight_per_pkg` decimal(18,4) DEFAULT NULL,
  `no_of_boxes` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `godown_id` bigint(20) NOT NULL,
  `godown_section_id` bigint(20) DEFAULT NULL,
  `godown_section_beans_id` bigint(20) DEFAULT NULL,
  `bottom_return_type_id` bigint(20) DEFAULT NULL,
  `bottom_return_type` varchar(255) DEFAULT NULL,
  `return_by_id` bigint(20) DEFAULT NULL,
  `received_by_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `sub_department_id` bigint(20) DEFAULT NULL,
  `goods_receipt_no` varchar(500) DEFAULT NULL,
  `cone_type` varchar(100) DEFAULT NULL,
  `bora_box` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`warping_bottom_return_details_id`)
);


create or replace
algorithm = UNDEFINED view `xtv_warping_bottom_return_details` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`bottom_return_no` as `bottom_return_no`,
    `xt`.`warping_bottom_return_details_id` as `warping_bottom_return_details_id`,
    `xt`.`weaving_production_warping_bottom_details_id` as `weaving_production_warping_bottom_details_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`bottom_receipt_date` as `bottom_receipt_date`,
    `xt`.`bottom_return_date` as `bottom_return_date`,
    `xt`.`warping_bottom_details_production_date` as `warping_bottom_details_production_date`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `xt`.`creel_no` as `creel_no`,
    `xt`.`no_of_package` as `no_of_package`,
    `xt`.`gross_weight` as `gross_weight`,
    `xt`.`net_weight` as `net_weight`,
    `xt`.`tare_weight` as `tare_weight`,
    `xt`.`package_type` as `package_type`,
    `xt`.`cone_type_value` as `cone_type_value`,
    `xt`.`sr_no` as `sr_no`,
    `xt`.`batch_no` as `batch_no`,
    `xt`.`weight_per_pkg` as `weight_per_pkg`,
    `xt`.`no_of_boxes` as `no_of_boxes`,
    `xt`.`customer_name` as `customer_name`,
    `xt`.`supplier_name` as `supplier_name`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`godown_section_id` as `godown_section_id`,
    `xt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `xt`.`bottom_return_type_id` as `bottom_return_type_id`,
    `xt`.`bottom_return_type` as `bottom_return_type`,
    `xt`.`return_by_id` as `return_by_id`,
    `xt`.`received_by_id` as `received_by_id`,
    `xt`.`department_id` as `department_id`,
    `xt`.`sub_department_id` as `sub_department_id`,
    `xt`.`goods_receipt_no` as `goods_receipt_no`,
    `xt`.`cone_type` as `cone_type`,
    `xt`.`bora_box` as `bora_box`,
    `e`.`employee_name` as `received_by`,
    `e1`.`employee_name` as `returned_by`,
    `gn`.`godown_name` as `godown_name`,
    `gns`.`godown_section_name` as `godown_section_name`,
    `gnss`.`godown_section_beans_name` as `godown_section_beans_name`,
    `xt`.`bottom_return_item_status` as `bottom_return_item_status`,
    `sm`.`product_rm_name` as `product_rm_name`,
    case
        when `xt`.`bottom_return_item_status` = 'P' then 'Pending'
        when `xt`.`bottom_return_item_status` = 'A' then 'Received'
    end as `bottom_return_item_status_desc`,
    `xt`.`financial_year` as `financial_year`,
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
    `xt`.`warping_bottom_return_details_id` as `field_id`
from
    ((((((`xt_warping_bottom_return_details` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`return_by_id`
        and `e`.`is_delete` = 0))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`received_by_id`
        and `e1`.`is_delete` = 0))
left join `sm_product_rm` `sm` on
    (`sm`.`product_rm_id` = `xt`.`product_material_id`
        and `sm`.`is_delete` = 0))
left join `cm_godown` `gn` on
    (`gn`.`godown_id` = `xt`.`godown_id`
        and `gn`.`is_delete` = 0))
left join `cm_godown_section` `gns` on
    (`gns`.`godown_section_id` = `xt`.`godown_section_id`
        and `gns`.`is_delete` = 0))
left join `cm_godown_section_beans` `gnss` on
    (`gnss`.`godown_section_beans_id` = `xt`.`godown_section_beans_id`
        and `gnss`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `xtv_warping_bottom_return_details_rpt` as
select
    concat(ifnull(`psl`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`psl`.`bottom_return_no`, ''), ':Bottom Return No:Y:T:') as `bottom_return_no`,
    concat(ifnull(`psl`.`bottom_return_date`, ''), ':Bottom Return Date:Y:D:') as `bottom_return_date`,
    concat(ifnull(`psl`.`warping_production_code`, ''), ':Warping Production Code:Y:T:') as `warping_production_code`,
    concat(ifnull(`psl`.`sr_no`, ''), ':SR NO:Y:T:') as `sr_no`,
    concat(ifnull(`psl`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
    concat(ifnull(`psl`.`supplier_name`, ''), ':Supplier Name:Y:T:') as `supplier_name`,
    concat(ifnull(`psl`.`no_of_boxes`, ''), ':No Of Boxes:Y:T:') as `no_of_boxes`,
    concat(ifnull(`psl`.`no_of_package`, ''), ':No of Package:Y:T:') as `no_of_package`,
    concat(ifnull(`psl`.`gross_weight`, ''), ':GR.WT:Y:T:') as `gross_weight`,
    concat(ifnull(`psl`.`tare_weight`, ''), ':TR.WT.:Y:T:') as `tare_weight`,
    concat(ifnull(`psl`.`net_weight`, ''), ':NT.WT.:Y:T:') as `net_weight`,
    concat(ifnull(`psl`.`cone_type`, ''), ':Cone Type:Y:T:') as `cone_type`,
    concat(ifnull(`psl`.`bora_box`, ''), ':Bora/Box:Y:T:') as `bora_box`,
    concat(ifnull(`psl`.`bottom_return_item_status`, ''), ':Bottom Return Status:Y:T:') as `bottom_return_item_status`,
    concat(ifnull(case when `psl`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`psl`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`psl`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`psl`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`psl`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`psl`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`psl`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`psl`.`warping_bottom_return_details_id`, ''), ':Production Standard Losses Id Id:O:N:') as `warping_bottom_return_details_id`,
    concat(ifnull(`psl`.`weaving_production_warping_bottom_details_id`, ''), ':Weave Design Id:O:N:') as `weaving_production_warping_bottom_details_id`,
    concat(ifnull(`psl`.`company_id`, ''), ':Company Id:O:N:') as `company_id`,
    concat(ifnull(`psl`.`company_branch_id`, ''), ':Company Branch Id:O:N:') as `company_branch_id`
from
    `xtv_warping_bottom_return_details` `psl`
limit 1;



CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `mtv_sales_order_details_trading_rpt` AS
select
    concat(`v`.`sales_order_no`, ':Sales Order No:Y:T') AS `sales_order_no`,
    concat(`v`.`sales_order_date`, ':Sales Order Date:Y:D:') AS `sales_order_date`,
    concat(`v`.`sales_order_status`, ':Sales Order Status:Y:H:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,Pending)') AS `sales_order_status_desc`,
    concat(`v`.`customer_name`, ':Customer Name:Y:T') AS `customer_name`,
    concat(`v`.`customer_order_no`, ':Customer Order No:Y:T') AS `customer_order_no`,
    concat(`v`.`customer_order_Date`, ':Customer Order Date:Y:D:') AS `customer_order_Date`,
    concat(`v`.`department_name`, ':Department Name:Y:T') AS `department_name`,
    concat(`v`.`consignee_name`, ':Consignee Name:Y:T:') AS `consignee_name`,
    concat(`v`.`approved_by_name`, ':Approved By:Y:C:cmv_employee_list:F') AS `approved_by_name`,
    concat(`v`.`approved_date`, ':Approved Date:Y:D:') AS `approved_date`,
    concat(`v`.`product_type`, ':Product Type:O:N:') AS `product_type`,
    concat(`v`.`product_material_name`, ':Material Name:Y:T:') AS `product_material_name`,
    concat(`v`.`product_material_code`, ':Material Code:Y:T:') AS `product_material_code`,
    concat(`v`.`product_material_stock_unit_name`, ':Material Stock Unit Name:O:N:') AS `product_material_stock_unit_name`,
    concat(`v`.`product_material_std_weight`, ':Material STD Weight:O:N:') AS `product_material_std_weight`,
    concat(`v`.`sales_order_item_status`, ':Sales Order Item Status Desc:O:N:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,PO,GRN,Pending)') AS `sales_order_item_status_desc`,
    concat(`v`.`sales_order_mail_sent_status`, ':Sales Order Mail Sent Status:O:N:') AS `sales_order_mail_sent_status`,
    concat(`v`.`material_quantity`, ':Material Quantity:O:N:') AS `material_quantity`,
    concat(`v`.`material_weight`, ':Material Weight:O:N:') AS `material_weight`,
    concat(`v`.`material_rate`, ':Material Rate:O:N:') AS `material_rate`,
    concat(`v`.`material_basic_amount`, ':Material Basic Amount:O:N:') AS `material_basic_amount`,
    concat(`v`.`material_discount_percent`, ':Material Discount Percent:O:N:') AS `material_discount_percent`,
    concat(`v`.`material_discount_amount`, ':Material Discount Amount:O:N:') AS `material_discount_amount`,
    concat(`v`.`material_taxable_amount`, ':Material Taxable Amount:O:N:') AS `material_taxable_amount`,
    concat(`v`.`material_cgst_percent`, ':Material Cgst Percent:O:N:') AS `material_cgst_percent`,
    concat(`v`.`material_cgst_total`, ':Material Cgst Total:O:N:') AS `material_cgst_total`,
    concat(`v`.`material_sgst_percent`, ':Material Sgst Percent:O:N:') AS `material_sgst_percent`,
    concat(`v`.`material_sgst_total`, ':Material SGST Total:O:N:') AS `material_sgst_total`,
    concat(`v`.`material_igst_percent`, ':Material IGST Percent:O:N:') AS `material_igst_percent`,
    concat(`v`.`material_igst_total`, ':Material IGST Total:O:N:') AS `material_igst_total`,
    concat(`v`.`material_total_amount`, ':Material Total Amount:O:N:') AS `material_total_amount`,
    concat(`v`.`dispatch_note_nos`, ':Dispatch Note Nos:O:N:') AS `dispatch_note_nos`,
    concat(`v`.`dispatch_challan_nos`, ':Dispatch Challan Nos:O:N:') AS `dispatch_challan_nos`,
    concat(`v`.`invoice_nos`, ':Invoice Nos:O:N:(Closed,Open)') AS `invoice_nos`,
    '0' AS `previous_dispatch_quantity`,
    '0' AS `previous_dispatch_weight`,
    '0' AS `stock_quantity`,
    '0' AS `stock_weight`,
    concat(`v`.`dispatched_quantity`, ':Dispatched Quantity:O:N:') AS `dispatched_quantity`,
    concat(`v`.`dispatched_weight`, ':Dispatched Weight:O:N:') AS `dispatched_weight`,
    concat(`v`.`production_issue_quantity`, ':Issue Quantity:O:N:') AS `production_issue_quantity`,
    concat(`v`.`production_issue_weight`, ':Issue Weight:O:N:') AS `production_issue_weight`,
    concat(`v`.`production_issue_return_quantity`, ':Issue Return Quantity:O:N:') AS `production_issue_return_quantity`,
    concat(`v`.`production_issue_return_weight`, ':Issue Return Weight:O:N:') AS `production_issue_return_weight`,
    concat(`v`.`production_issue_rejection_quantity`, ':Issue Rejection Quantity:O:N:') AS `production_issue_rejection_quantity`,
    concat(`v`.`production_issue_rejection_weight`, ':Issue Rejection Weight:O:N:') AS `production_issue_rejection_weight`,
    concat(`v`.`product_material_hsn_sac_code`, ':Hsn Sac Code:O:N:') AS `product_material_hsn_sac_code`,
    concat(`v`.`product_material_hsn_sac_rate`, ':Hsn Sac Rate:O:N:') AS `product_material_hsn_sac_rate`,
    concat(`v`.`customer_email`, ':Customer Email:O:N:') AS `customer_email`,
    concat(`v`.`customer_state_name`, ':Customer State:O:N:') AS `customer_state_name`,
    concat(`v`.`customer_district_name`, ':Customer District:O:N:') AS `customer_district_name`,
    concat(`v`.`customer_city_name`, ':Customer City:O:N:') AS `customer_city_name`,
    concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') AS `consignee_state_name`,
    concat(`v`.`consignee_district_name`, ':Consignee District:O:N:') AS `consignee_district_name`,
    concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') AS `consignee_city_name`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') AS `basic_total`,
    concat(`v`.`transport_amount`, ':Transport Amount:O:N:') AS `transport_amount`,
    concat(`v`.`material_freight_amount`, ':Freight Amount:O:N:') AS `material_freight_amount`,
    concat(`v`.`freight_amount`, ':Freight Amount:O:N:') AS `freight_amount`,
    concat(`v`.`packing_amount`, ':Packing Amount:O:N:') AS `packing_amount`,
    concat(`v`.`discount_percent`, ':Discount Percent:O:N:') AS `discount_percent`,
    concat(`v`.`discount_amount`, ':Discount Amount:O:N:') AS `discount_amount`,
    concat(`v`.`other_amount`, ':Other Amount:O:N:') AS `other_amount`,
    concat(`v`.`taxable_total`, ':Taxable Total:O:N:') AS `taxable_total`,
    concat(`v`.`cgst_percent`, ':CGST Percent:O:N:') AS `cgst_percent`,
    concat(`v`.`cgst_total`, ':CGST Total:O:N:') AS `cgst_total`,
    concat(`v`.`sgst_percent`, ':SGST Percent:O:N:') AS `sgst_percent`,
    concat(`v`.`sgst_total`, ':SGST Total:O:N:') AS `sgst_total`,
    concat(`v`.`igst_percent`, ':IGST Percent:O:N:') AS `igst_percent`,
    concat(`v`.`igst_total`, ':IGST Total:O:N:') AS `igst_total`,
    concat(`v`.`grand_total`, ':Grand Total:O:N:') AS `grand_total`,
    concat(`v`.`roundoff`, ':RoundOff:O:N:') AS `roundoff`,
    concat(`v`.`sales_order_version`, ':Sales Order Version:O:N:') AS `sales_order_version`,
    concat(`v`.`overall_schedule_date`, ':Overall Schedule Date:Y:D:') AS `overall_schedule_date`,
    concat(`v`.`other_terms_conditions`, ':Other Terms Conditions:O:N:') AS `other_terms_conditions`,
    concat(`v`.`product_type_short_name`, ':Product Type Short Name:O:N:') AS `product_type_short_name`,
    concat(`v`.`sales_order_creation_type`, ':Sales Order Creation Type:O:N:') AS `sales_order_creation_type`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
    concat(`v`.`financial_year`, ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`v`.`created_on`, ':Modified On:O:N:') AS `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(`v`.`sales_order_item_status`, ':Sales Order Item Status:O:N:') AS `sales_order_item_status`,
    concat(`v`.`sales_order_status`, ':Sales Order Status:O:N:') AS `sales_order_status`,
    concat(`v`.`company_id`, ':Company Id:N:N:') AS `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(`v`.`sales_order_master_transaction_id`, ':Sales Order Master Transaction Id:O:N:') AS `sales_order_master_transaction_id`,
    concat(`v`.`sales_order_details_transaction_id`, ':Sales Order Details Transaction Id:N:N:') AS `sales_order_details_transaction_id`,
    concat(`v`.`product_type_id`, ':Product Type Id:N:N:') AS `product_type_id`,
    concat(`v`.`product_material_id`, ':Product Material Id:N:N:') AS `product_material_id`,
    concat(`v`.`product_material_unit_id`, ':Product Material Unit Id:N:N:') AS `product_material_unit_id`,
    concat(`v`.`product_material_packing_id`, ':Product Material Packing Id:N:N:') AS `product_material_packing_id`,
    concat(`v`.`product_material_hsn_code_id`, ':Product Material Hsn Code Id:N:N:') AS `product_material_hsn_code_id`
from
    `mtv_sales_order_details_trading` `v`
limit 1;



INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,12,'Warping Bottom Return',4,'Warping Bottom Return','Purchase','<FrmWarpingBottomReturnListing/>','import FrmWarpingBottomReturnListing from "/Transactions/TWarpingBottomReturn/FrmWarpingBottomReturnListing";','/Transactions/TWarpingBottomReturn/FrmWarpingBottomReturnListing','<FrmWarpingBottomReturnEntry />','import FrmWarpingBottomReturnEntry from "/Transactions/TWarpingBottomReturn/FrmWarpingBottomReturnEntry";','/Transactions/TWarpingBottomReturn/FrmWarpingBottomReturnEntry',NULL,'Warping Bottom Return',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
	 (1,1,'Administrators','1',714,1,3,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0'),
	 (3,4,'Administrators','595',714,1,16,1,1,1,1,1,1,1,1,0,'6260537025','2025-03-19 18:38:20.000',NULL,'2025-03-19 18:38:20.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S30017','0');


