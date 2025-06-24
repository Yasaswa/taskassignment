INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'SalesAndDispatch',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'DispatchHSNTaxType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'DispatchSalesType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'DispatchVoucherType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD');




INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (195,'SalesAndDispatch',2,'Cotton Yarn Sales','Cotton Yarn Sales',1,0,'6260537025','2025-03-28 13:56:09.000','6260537025','2025-03-28 15:23:51.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (196,'DispatchHSNTaxType',2,'Cotton Yarn Sales-IGST','Cotton Yarn Sales-IGST',1,0,'6260537025','2025-03-28 13:57:03.000',NULL,'2025-03-28 13:57:03.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (197,'DispatchSalesType',2,'Yarn Cotton','Yarn Cotton',1,0,'6260537025','2025-03-28 14:00:03.000',NULL,'2025-03-28 14:00:03.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (198,'DispatchVoucherType',2,'Yarn Sales','Yarn Sales',1,0,'6260537025','2025-03-28 14:01:35.000','6260537025','2025-03-28 14:02:26.000',NULL,NULL,'Cotton Yarn Sales','YTX'),
	 (196,'DispatchHSNTaxType',2,'Yarn Sale GST 12%','Yarn Sale GST 12%',1,0,'6260537025','2025-03-28 14:03:46.000',NULL,'2025-03-28 14:03:46.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (197,'DispatchSalesType',2,'Sized Yarn','Sized Yarn',1,0,'6260537025','2025-03-28 14:05:38.000',NULL,'2025-03-28 14:05:38.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (198,'DispatchVoucherType',2,'Grey Cotton Sales','Grey Cotton Sales',1,0,'6260537025','2025-03-28 14:06:51.000',NULL,'2025-03-28 14:06:51.000',NULL,NULL,'Cotton Yarn Sales','FTX'),
	 (196,'DispatchHSNTaxType',2,'Yarn Sale (GST 5%)','Yarn Sale (GST 5%)',1,0,'6260537025','2025-03-28 14:07:52.000',NULL,'2025-03-28 14:07:52.000',NULL,NULL,'Cotton Yarn Sales ','CYS'),
	 (195,'SalesAndDispatch',2,'Export Sales','Export Sales',1,0,'6260537025','2025-03-28 14:10:12.000','6260537025','2025-03-28 15:23:20.000',NULL,NULL,'Export Sales','ES'),
	 (196,'DispatchHSNTaxType',2,'Cotton Combers Export Sales','Cotton Combers Export Sales',1,0,'6260537025','2025-03-28 14:11:06.000',NULL,'2025-03-28 14:11:06.000',NULL,NULL,'Export Sales','ES');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (196,'DispatchHSNTaxType',2,'Cotton Yarn Export Sales','Cotton Yarn Export Sales',1,0,'6260537025','2025-03-28 14:12:03.000',NULL,'2025-03-28 14:12:03.000',NULL,NULL,'Export Sales','ES'),
	 (197,'DispatchSalesType',2,'Cotton Combers','Cotton Combers',1,0,'6260537025','2025-03-28 14:12:41.000',NULL,'2025-03-28 14:12:41.000',NULL,NULL,'Export Sales','ES'),
	 (197,'DispatchSalesType',2,'Yarn Cotton (Trading)','Yarn Cotton (Trading)',1,0,'6260537025','2025-03-28 14:13:29.000',NULL,'2025-03-28 14:13:29.000',NULL,NULL,'Export Sales','ES'),
	 (198,'DispatchVoucherType',2,'EXPORT SALES','EXPORT SALES',1,0,'6260537025','2025-03-28 14:25:39.000',NULL,'2025-03-28 14:25:39.000',NULL,NULL,'EXPORT SALES','PT/YN'),
	 (195,'SalesAndDispatch',2,'Grey Fabric Sales','Grey Fabric Sales',1,0,'6260537025','2025-03-28 14:43:58.000',NULL,'2025-03-28 14:43:58.000',NULL,NULL,'Grey Fabric Sales','GFS'),
	 (196,'DispatchHSNTaxType',2,'Grey Cloth-5%','Grey Cloth-5%',1,0,'6260537025','2025-03-28 14:44:43.000',NULL,'2025-03-28 14:44:43.000',NULL,NULL,'Grey Fabric Sales',''),
	 (196,'DispatchHSNTaxType',2,'Grey Cloth Sales Return-GST','Grey Cloth Sales Return-GST',1,0,'6260537025','2025-03-28 14:45:23.000',NULL,'2025-03-28 14:45:23.000',NULL,NULL,'Grey Fabric Sales',''),
	 (197,'DispatchSalesType',2,'Grey Cloth /Greige Fabric','Grey Cloth /Greige Fabric',1,0,'6260537025','2025-03-28 14:46:22.000',NULL,'2025-03-28 14:46:22.000',NULL,NULL,'Grey Fabric Sales',''),
	 (197,'DispatchSalesType',2,'Grey Cloth','Grey Cloth',1,0,'6260537025','2025-03-28 14:46:54.000',NULL,'2025-03-28 14:46:54.000',NULL,NULL,'Grey Fabric Sales',''),
	 (198,'DispatchVoucherType',2,'Grey Cloth Sales','Grey Cloth Sales',1,0,'6260537025','2025-03-28 14:48:03.000',NULL,'2025-03-28 14:48:03.000',NULL,NULL,'Grey Fabric Sales','FTX');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (198,'DispatchVoucherType',2,'Credit Note-Sales','Credit Note-Sales',1,0,'6260537025','2025-03-28 14:48:44.000',NULL,'2025-03-28 14:48:44.000',NULL,NULL,'Grey Fabric Sales','CRS'),
	 (195,'SalesAndDispatch',2,'Trading Sales','Trading Sales',1,0,'6260537025','2025-03-28 14:51:14.000',NULL,'2025-03-28 14:51:14.000',NULL,NULL,'Trading Sales','TS'),
	 (196,'DispatchHSNTaxType',2,'Cotton Rui Sales Bales-SGST','Cotton Rui Sales Bales-SGST',1,0,'6260537025','2025-03-28 14:52:37.000',NULL,'2025-03-28 14:52:37.000',NULL,NULL,'Trading Sales',''),
	 (197,'DispatchSalesType',2,'Shankar Rui Bales','Shankar Rui Bales',1,0,'6260537025','2025-03-28 14:53:24.000',NULL,'2025-03-28 14:53:24.000',NULL,NULL,'Trading Sales',''),
	 (198,'DispatchVoucherType',2,'Bales Sales','Bales Sales',1,0,'6260537025','2025-03-28 14:54:35.000',NULL,'2025-03-28 14:54:35.000',NULL,NULL,'Trading Sales','PBTX'),
	 (195,'SalesAndDispatch',2,'Finish Fabric','Finish Fabric',1,0,'6260537025','2025-03-28 14:57:49.000','6260537025','2025-03-28 15:23:01.000',NULL,NULL,'Finish Fabric','FF'),
	 (197,'DispatchSalesType',2,'Finish Grey Cloth','Finish Grey Cloth',1,0,'6260537025','2025-03-28 15:00:01.000',NULL,'2025-03-28 15:00:01.000',NULL,NULL,'Finish Fabric',''),
	 (198,'DispatchVoucherType',2,'Grey Cloth Sale','Grey Cloth Sale',1,0,'6260537025','2025-03-28 15:04:10.000','6260537025','2025-03-28 17:49:08.000',NULL,NULL,'Finish Fabric','FTX'),
	 (195,'SalesAndDispatch',2,'Job Work Income','Job Work Income',1,0,'6260537025','2025-03-28 15:05:11.000','6260537025','2025-03-28 15:07:31.000',NULL,NULL,'Job Work Income','JWI'),
	 (197,'DispatchSalesType',2,'Sizing-Jobwork','Sizing-Jobwork',1,0,'6260537025','2025-03-28 15:05:50.000','6260537025','2025-03-28 15:07:53.000',NULL,NULL,'Job Work Income','');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (198,'DispatchVoucherType',2,'Job Work Income Sales','Job Work Income Sales',1,0,'6260537025','2025-03-28 15:08:52.000',NULL,'2025-03-28 15:08:52.000',NULL,NULL,'Job Work Income','JTX'),
	 (195,'SalesAndDispatch',2,'Scrap Sales@18%','Scrap Sales@18%',1,0,'6260537025','2025-03-28 15:17:03.000',NULL,'2025-03-28 15:17:03.000',NULL,NULL,'Scrap Sales@18%','SS18%'),
	 (197,'DispatchSalesType',2,'Scrap /Scrap Waste','Scrap /Scrap Waste',1,0,'6260537025','2025-03-28 15:17:47.000',NULL,'2025-03-28 15:17:47.000',NULL,NULL,'Scrap Sales@18%',''),
	 (198,'DispatchVoucherType',2,'Scrap Sales','Scrap Sales',1,0,'6260537025','2025-03-28 15:18:22.000',NULL,'2025-03-28 15:18:22.000',NULL,NULL,'Scrap Sales@18%','STX'),
	 (195,'SalesAndDispatch',2,'Scrap Sales@5%','Scrap Sales@5%',1,0,'6260537025','2025-03-28 15:19:43.000','6260537025','2025-03-28 15:22:36.000',NULL,NULL,'Scrap Sales@5%','SS5%'),
	 (197,'DispatchSalesType',2,'Scrap 5%','Scrap 5%',1,0,'6260537025','2025-03-28 15:20:08.000',NULL,'2025-03-28 15:20:08.000',NULL,NULL,'Scrap Sales@5%',''),
	 (198,'DispatchVoucherType',2,'Scrap Sale','Scrap Sale',1,0,'6260537025','2025-03-28 15:21:35.000','6260537025','2025-03-28 15:21:46.000',NULL,NULL,'Scrap Sales@5%','STX');








ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD sales_dispatch_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD dispatch_hsnTax_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD dispatch_sales_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD dispatch_voucher_type varchar(100) NULL;





-- mtv_dispatch_challan_sized_yarn_master source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_master` as
select
    `mt`.`dispatch_challan_sized_yarn_id` as `dispatch_challan_sized_yarn_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`job_type_id` as `job_type_id`,
    `ap`.`property_name` as `job_type_name`,
    `mt`.`set_no` as `set_no`,
    `mt`.`yarn_count` as `yarn_count`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_name` as `product_material_name`,
    `mt`.`sales_dispatch_type` as `sales_dispatch_type`,
    `mt`.`dispatch_hsnTax_type` as `dispatch_hsnTax_type`,
    `mt`.`dispatch_sales_type` as `dispatch_sales_type`,
    `mt`.`dispatch_voucher_type` as `dispatch_voucher_type`,
    `mt`.`total_ends` as `total_ends`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `a`.`agent_address1` as `agent_address1`,
    `a`.`agent_cell_no` as `agent_cell_no`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`dispatch_date` as `dispatch_date`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`rate` as `rate`,
    `mt`.`weight` as `weight`,
    `mt`.`dispatch_payment_terms` as `dispatch_payment_terms`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `mt`.`vehicle_no` as `vehicle_no`,
    case
        `mt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `mt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`driver_name` as `driver_name`,
    `mt`.`yarn_bill_no` as `yarn_bill_no`,
    `c`.`customer_name` as `customer_name`,
    `c`.`cust_branch_phone_no` as `customer_phone`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `ct`.`city_name` as `customer_city_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `cc`.`cust_branch_address1` as `consignee_address`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `st1`.`state_name` as `consignee_state_name`,
    `ct1`.`city_name` as `consignee_city_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_EmailId` as `company_emailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`
from
    (((((((((((`mt_dispatch_challan_sized_yarn_master` `mt`
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `am_properties` `ap` on
    (`ap`.`property_id` = `mt`.`job_type_id`
        and `ap`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `mt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_customer` `cc` on
    (`cc`.`company_id` = `mt`.`company_id`
        and `cc`.`customer_id` = `mt`.`consignee_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`customer_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `mt`.`consignee_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`customer_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `mt`.`consignee_state_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;




-- mtv_dispatch_challan_sized_yarn_master_rpt source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_master_rpt` as
select
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`dispatch_challan_no`, ''), ':Dispatch Challan Number:Y:T:') as `dispatch_challan_no`,
    concat(ifnull(`v`.`dispatch_challan_date`, ''), ':Dispatch Challan Date:Y:D:') as `dispatch_challan_date`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Dispatch Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`job_type_name`, ''), ':Job Type:Y:T:') as `job_type_name`,
    concat(ifnull(`v`.`sales_dispatch_type`, ''), ':Sales Dispatch Type:Y:T:') as `sales_dispatch_type`,
    concat(ifnull(`v`.`dispatch_hsnTax_type`, ''), ':Dispatch Tax Type:Y:T:') as `dispatch_hsnTax_type`,
    concat(ifnull(`v`.`dispatch_sales_type`, ''), ':Dispatch Sales Type:Y:T:') as `dispatch_sales_type`,
    concat(ifnull(`v`.`dispatch_voucher_type`, ''), ':Dispatch Voucher Type:Y:T:') as `dispatch_voucher_type`,
    concat(ifnull(`v`.`total_ends`, ''), ':Total Ends:O:N:') as `total_ends`,
    concat(ifnull(`v`.`yarn_bill_no`, ''), ':Yarn Bill No:Y:T:') as `yarn_bill_no`,
    concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
    concat(ifnull(`v`.`rate`, ''), ':Rate:Y:T') as `rate`,
    concat(ifnull(`v`.`weight`, ''), ':Weight:Y:T') as `weight`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
    concat(ifnull(`v`.`consignee_name`, ''), ':Consignee Name:Y:T:') as `consignee_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By Name:Y:T:') as `approved_by_name`,
    concat(case when ifnull(`v`.`is_delete`, 0) = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`dispatch_challan_sized_yarn_id`, ''), ':Dispatch Challan Sized Yarn Id:O:N:') as `dispatch_challan_sized_yarn_id`
from
    `mtv_dispatch_challan_sized_yarn_master` `v`
limit 1;



CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_goods_return_details_rpt` AS
select
    concat(`v`.`goods_return_no`, ':Yarn Sale No:Y:T:') AS `goods_return_no`,
    concat(`v`.`goods_return_date`, ':Yarn Sale Date:Y:D:') AS `goods_return_date`,
    concat(`v`.`sales_type`, ':Sales Type:Y:T:') AS `sales_type`,
    concat(`v`.`goods_receipt_no`, ':Good Receipt No:Y:T:') AS `goods_receipt_no`,
    concat(`v`.`issue_batch_no`, ':Lot No:Y:T:') AS `issue_batch_no`,
    concat(`v`.`product_material_name`, ':Product Material Name:Y:T:') AS `product_material_name`,
    concat(`v`.`product_material_code`, ':Product Material Code:Y:T:') AS `product_material_code`,
    concat(`v`.`cone_per_wt`, ':Weight Per Cone:Y:T:') AS `cone_per_wt`,
    concat(`v`.`goods_return_quantity`, ':Goods Return Quantity:O:N:') AS `goods_return_quantity`,
    concat(`v`.`goods_return_weight`, ':Goods Return Weight:O:N:') AS `goods_return_weight`,
    concat(`v`.`goods_return_boxes`, ':Goods Return Boxes:O:N:') AS `goods_return_boxes`,
    concat(`v`.`goods_return_rate`, ':Yarn Sale Rate:Y:T:') AS `goods_return_rate`,
    concat(`v`.`product_hsn_sac_code`, ':HSN Code:Y:T:') AS `product_hsn_sac_code`,
    concat(`v`.`material_basic_amount`, ':Basic Amount:O:N:') AS `material_basic_amount`,
    concat(`v`.`material_discount_amount`, ':Discount Amount:O:N:') AS `material_discount_amount`,
    concat(`v`.`material_taxable_amount`, ':Taxable Amount:O:N:') AS `material_taxable_amount`,
    concat(`v`.`material_total_amount`, ':Grand Total:O:N:') AS `material_total_amount`,
    concat(`v`.`product_unit_name`, ':Product Unit:Y:T:') AS `product_unit_name`,
    concat(`v`.`goods_return_remark`, ':Goods Return Remark:O:N:') AS `goods_return_remark`,
    concat(`v`.`goods_return_status`, ':Yarn Sale Status:Y:T:') AS `goods_return_status`,
    concat(`v`.`product_rm_id`, ':Product Id:N:N') AS `product_rm_id`,
    concat(`v`.`godown_name`, ':Godown Name:Y:T:') AS `godown_name`,
    concat(`v`.`godown_section_name`, ':Godown Section Name:Y:T:') AS `godown_section_name`,
    concat(`v`.`godown_section_beans_name`, ':Godown Section Beans Name:Y:T:') AS `godown_section_beans_name`,
    concat(`v`.`product_type_name`, ':Product Type Name:Y:T:') AS `product_type_name`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') AS `financial_year`,
    concat(`v`.`goods_return_master_id`, ':Yarn Sale Master Id:O:N:') AS `goods_return_master_id`,
    concat(`v`.`godown_id`, ':Godown Id:O:N:') AS `godown_id`,
    concat(`v`.`godown_section_id`, ':Godown Section Id:N:N:') AS `godown_section_id`,
    concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:N:N:') AS `godown_section_beans_id`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') AS `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') AS `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(`v`.`product_type_id`, ':Product Type Id:N:N:') AS `product_type_id`
from
    `ptv_goods_return_details` `v`
limit 1;



ALTER TABLE pt_goods_return_master ADD sales_dispatch_type varchar(100) NULL;
ALTER TABLE pt_goods_return_master ADD dispatch_hsnTax_type varchar(100) NULL;
ALTER TABLE pt_goods_return_master ADD dispatch_sales_type varchar(100) NULL;
ALTER TABLE pt_goods_return_master ADD dispatch_voucher_type varchar(100) NULL;





-- ptv_goods_return_master source

create or replace
algorithm = UNDEFINED view `ptv_goods_return_master` as
select
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`invoice_no` as `invoice_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`sales_type` as `sales_type`,
    `grm`.`sales_dispatch_type` as `sales_dispatch_type`,
    `grm`.`dispatch_hsnTax_type` as `dispatch_hsnTax_type`,
    `grm`.`dispatch_sales_type` as `dispatch_sales_type`,
    `grm`.`dispatch_voucher_type` as `dispatch_voucher_type`,
    `grm`.`sales_type_id` as `sales_type_id`,
    `grm`.`yarn_type` as `yarn_type`,
    `pdt`.`product_type_name` as `product_type_name`,
    `e`.`employee_name` as `approved_by_name`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `grm`.`company_id` as `company_id`,
    `grm`.`financial_year` as `financial_year`,
    `grm`.`company_branch_id` as `company_branch_id`,
    `grm`.`goods_return_status` as `goods_return_status`,
    `grm`.`driver_name` as `driver_name`,
    `grm`.`vehical_no` as `vehical_no`,
    `grm`.`transport_mode` as `transport_mode`,
    `grm`.`customer_id` as `customer_id`,
    `grm`.`customer_state_id` as `customer_state_id`,
    `grm`.`customer_city_id` as `customer_city_id`,
    `grm`.`consignee_id` as `consignee_id`,
    `grm`.`consignee_state_id` as `consignee_state_id`,
    `grm`.`consignee_city_id` as `consignee_city_id`,
    `grm`.`basic_total` as `basic_total`,
    `grm`.`transport_amount` as `transport_amount`,
    `grm`.`freight_amount` as `freight_amount`,
    `grm`.`is_freight_taxable` as `is_freight_taxable`,
    `grm`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `grm`.`packing_amount` as `packing_amount`,
    `grm`.`discount_percent` as `discount_percent`,
    `grm`.`discount_amount` as `discount_amount`,
    `grm`.`other_amount` as `other_amount`,
    `grm`.`taxable_total` as `taxable_total`,
    `grm`.`cgst_percent` as `cgst_percent`,
    `grm`.`cgst_total` as `cgst_total`,
    `grm`.`sgst_percent` as `sgst_percent`,
    `grm`.`sgst_total` as `sgst_total`,
    `grm`.`igst_percent` as `igst_percent`,
    `grm`.`igst_total` as `igst_total`,
    `grm`.`roundoff` as `roundoff`,
    `grm`.`grand_total` as `grand_total`,
    `cust`.`customer_name` as `customer_name`,
    `cust`.`cust_branch_address1` as `cust_branch_address1`,
    `cust`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `cust`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `cccty`.`city_name` as `cust_city_name`,
    `ccs`.`state_name` as `cust_state_name`,
    `ccctyg`.`city_name` as `consi_city_name`,
    `ccsg`.`state_name` as `consi_state_name`,
    `consi`.`customer_name` as `consignee_name`,
    `consi`.`cust_branch_address1` as `consi_branch_address1`,
    `consi`.`cust_branch_gst_no` as `consi_branch_gst_no`,
    `consi`.`cust_branch_phone_no` as `consi_branch_phone_no`,
    case
        `grm`.`goods_return_status` when 'A' then 'Dispatched'
        else 'Pending'
    end as `goods_return_status_desc`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `grm`.`is_active` as `is_active`,
    `grm`.`is_delete` as `is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`,
    `grm`.`approved_by_id` as `approved_by_id`,
    `grm`.`approved_date` as `approved_date`
from
    (((((((((`pt_goods_return_master` `grm`
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `grm`.`approved_by_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `grm`.`company_id`))
left join `cmv_customer` `cust` on
    (`cust`.`customer_id` = `grm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `cmv_customer` `consi` on
    (`consi`.`customer_id` = `grm`.`consignee_id`
        and `consi`.`is_delete` = 0))
left join `cm_state` `ccs` on
    (`ccs`.`state_id` = `grm`.`customer_state_id`))
left join `cm_city` `cccty` on
    (`cccty`.`city_id` = `grm`.`customer_city_id`))
left join `cm_state` `ccsg` on
    (`ccsg`.`state_id` = `grm`.`consignee_state_id`))
left join `cm_city` `ccctyg` on
    (`ccctyg`.`city_id` = `grm`.`consignee_city_id`))
where
    `grm`.`is_delete` = 0;



-- ptv_goods_return_master_rpt source

create or replace
algorithm = UNDEFINED view `ptv_goods_return_master_rpt` as
select
    concat(ifnull(`v`.`goods_return_no`, ''), ':Yarn Sale No:Y:T:') as `goods_return_no`,
    concat(ifnull(`v`.`invoice_no`, ''), ':Invoice Ref.no:Y:T:') as `invoice_no`,
    concat(ifnull(`v`.`goods_return_date`, ''), ':Yarn Sale Date:Y:D:') as `goods_return_date`,
    concat(ifnull(`v`.`sales_type`, ''), ':Sales Type:Y:T:') as `sales_type`,
    concat(ifnull(`v`.`yarn_type`, ''), ':Yarn Type:Y:T:') as `yarn_type`,
    concat(ifnull(`v`.`sales_dispatch_type`, ''), ':Sales Challan Type:Y:T:') as `sales_dispatch_type`,
    concat(ifnull(`v`.`dispatch_hsnTax_type`, ''), ':Challan Tax Type:Y:T:') as `dispatch_hsnTax_type`,
    concat(ifnull(`v`.`dispatch_sales_type`, ''), ':Challan Sales Type:Y:T:') as `dispatch_sales_type`,
    concat(ifnull(`v`.`dispatch_voucher_type`, ''), ':Challan Voucher Type:Y:T:') as `dispatch_voucher_type`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
    concat(ifnull(`v`.`goods_return_status_desc`, ''), ':Yarn Sale Status:Y:T:') as `goods_return_status_desc`,
    concat(ifnull(`v`.`transport_mode`, ''), ':Transport Mode:Y:T:') as `transport_mode`,
    concat(ifnull(`v`.`driver_name`, ''), ':Driver Name:Y:T:') as `driver_name`,
    concat(ifnull(`v`.`vehical_no`, ''), ':Vehical No:Y:T:') as `vehical_no`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
    concat(ifnull(`v`.`consignee_name`, ''), ':Consignee Name:Y:T:') as `consignee_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T:') as `approved_by_name`,
    concat(ifnull(`v`.`goods_return_master_id`, ''), ':Yarn Sale Master Id:O:N:') as `goods_return_master_id`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`
from
    `ptv_goods_return_master` `v`
limit 1;


ALTER TABLE mt_dispatch_challan_master_trading ADD sales_dispatch_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_master_trading ADD dispatch_hsnTax_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_master_trading ADD dispatch_sales_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_master_trading ADD dispatch_voucher_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_master_trading ADD job_type varchar(100) NULL;




-- mtv_dispatch_challan_master_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_master_trading` as
select
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    case
        `mt`.`dispatch_challan_status` when 'A' then 'Aprroved'
        when 'U' then 'Partial Sales Issue'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_challan_status_desc`,
     `mt`.`sales_dispatch_type` as `sales_dispatch_type`,
    `mt`.`dispatch_hsnTax_type` as `dispatch_hsnTax_type`,
    `mt`.`dispatch_sales_type` as `dispatch_sales_type`,
    `mt`.`dispatch_voucher_type` as `dispatch_voucher_type`,
     `mt`.`job_type` as `job_type`,
    `c`.`customer_name` as `customer_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `st1`.`state_name` as `consignee_state_name`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`dispatch_challan_remark` as `dispatch_challan_remark`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`customer_gst_no` as `customer_gst_no`,
    `mt`.`consignee_gst_no` as `consignee_gst_no`,
    `mt`.`customer_pan_no` as `customer_pan_no`,
    `mt`.`consignee_pan_no` as `consignee_pan_no`,
    `mt`.`transporter_gst_no` as `transporter_gst_no`,
    `mt`.`vehicle_no` as `vehicle_no`,
    `mt`.`vehicle_type` as `vehicle_type`,
    `mt`.`transporter_challan_no` as `transporter_challan_no`,
    `mt`.`transporter_challan_date` as `transporter_challan_date`,
    `mt`.`loading_time` as `loading_time`,
    `mt`.`transport_mode` as `transport_mode`,
    `mt`.`transaction_type` as `transaction_type`,
    `mt`.`is_service` as `is_service`,
    `mt`.`is_sez` as `is_sez`,
    `mt`.`interim` as `interim`,
    `mt`.`overall_schedule_date` as `overall_schedule_date`,
    `mt`.`basic_total` as `basic_total`,
    `mt`.`transport_amount` as `transport_amount`,
    `mt`.`freight_amount` as `freight_amount`,
    `mt`.`is_freight_taxable` as `is_freight_taxable`,
    `mt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `h`.`hsn_sac_code` as `freight_fg_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `freight_fg_hsn_sac_rate`,
    `mt`.`packing_amount` as `packing_amount`,
    `mt`.`discount_percent` as `discount_percent`,
    `mt`.`discount_amount` as `discount_amount`,
    `mt`.`other_amount` as `other_amount`,
    `mt`.`taxable_total` as `taxable_total`,
    `mt`.`cgst_percent` as `cgst_percent`,
    `mt`.`cgst_total` as `cgst_total`,
    `mt`.`sgst_percent` as `sgst_percent`,
    `mt`.`sgst_total` as `sgst_total`,
    `mt`.`igst_percent` as `igst_percent`,
    `mt`.`igst_total` as `igst_total`,
    `mt`.`roundoff` as `roundoff`,
    `mt`.`grand_total` as `grand_total`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    `mt`.`agent_amount` as `agent_amount`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    case
        `mt`.`dispatch_challan_creation_type` when 'M' then 'Mannual'
        when 'D' then 'Dispatch Schedule Based'
        when 'O' then 'Sales Order Based'
    end as `dispatch_challan_creation_type_desc`,
    case
        `mt`.`mail_sent_status` when 'S' then 'Sent'
        when 'F' then 'Failed'
    end as `mail_sent_status_desc`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`remark` as `remark`,
    `v`.`company_name` as `company_name`,
    `mt`.`financial_year` as `financial_year`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`cust_branch_address1` as `customer_address1`,
    `ct`.`city_name` as `customer_city_name`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `ct1`.`city_name` as `consignee_city_name`,
    `cc`.`cust_branch_address1` as `consignee_address1`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    case
        when `mt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `mt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`dispatch_challan_master_transaction_id` as `dispatch_challan_master_transaction_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`transporter_id` as `transporter_id`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`mail_sent_status` as `mail_sent_status`,
    `mt`.`dispatch_type` as `dispatch_type`,
    (
    select
        sum(`dt1`.`dispatch_quantity`)
    from
        `mt_dispatch_challan_details_trading` `dt1`
    where
        `dt1`.`dispatch_challan_no` = `mt`.`dispatch_challan_no`
        and `dt1`.`is_delete` = 0) as `total_dispatch_quantity`,
    (
    select
        sum(`dt1`.`dispatch_weight`)
    from
        `mt_dispatch_challan_details_trading` `dt1`
    where
        `dt1`.`dispatch_challan_no` = `mt`.`dispatch_challan_no`
        and `dt1`.`is_delete` = 0) as `total_dispatch_weight`
from
    ((((((((((`mt_dispatch_challan_master_trading` `mt`
left join `cmv_company_branch_summary` `v` on
    (`v`.`company_branch_id` = `mt`.`expected_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `cmv_customer_summary` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_agent` `a` on
    (`a`.`company_id` = `mt`.`company_id`
        and `a`.`agent_id` = `mt`.`agent_id`))
left join `cmv_customer_summary` `cc` on
    (`cc`.`company_id` = `mt`.`company_id`
        and `cc`.`customer_id` = `mt`.`consignee_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`customer_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `mt`.`consignee_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`customer_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `mt`.`consignee_state_id`))
left join `cmv_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `mt`.`dispatch_challan_type_id`
        and `pt`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;



-- mtv_dispatch_challan_master_trading_rpt source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_master_trading_rpt` as
select
    concat(`v`.`dispatch_challan_no`, ':Challan No:Y:C:mtv_dispatch_challan_master_trading:F') as `dispatch_challan_no`,
    concat(`v`.`dispatch_challan_date`, ':Challan Date:Y:D:') as `dispatch_challan_date`,
    concat(`v`.`dispatch_challan_version`, ':Challan Version:O:N:') as `dispatch_challan_version`,
    concat('', ':Total Dispatch Quantity:O:N:') as `total_dispatch_quantity`,
    concat('', ':Total Dispatch Weight:O:N:') as `total_dispatch_weight`,
     concat(ifnull(`v`.`job_type`, ''), ':Job Type:Y:T:') as `job_type`,
    concat(ifnull(`v`.`sales_dispatch_type`, ''), ':Sales Challan Type:Y:T:') as `sales_dispatch_type`,
    concat(ifnull(`v`.`dispatch_hsnTax_type`, ''), ':Challan Tax Type:Y:T:') as `dispatch_hsnTax_type`,
    concat(ifnull(`v`.`dispatch_sales_type`, ''), ':Challan Sales Type:Y:T:') as `dispatch_sales_type`,
    concat(ifnull(`v`.`dispatch_voucher_type`, ''), ':Challan Voucher Type:Y:T:') as `dispatch_voucher_type`,
    concat(`v`.`customer_name`, ':Customer Name:Y:C:cmv_customer:F') as `customer_name`,
    concat(`v`.`dispatch_challan_status_desc`, ':Challan Status:Y:H:(Aprroved,Partial Sales Issue,Rejected,Completed,Canceled,Dispath Challan Created,Invoice Created,Pending)') as `dispatch_challan_status_desc`,
    concat(`v`.`dispatch_challan_type`, ':Challan Type:O:N:') as `dispatch_challan_type`,
    concat(`v`.`transporter_challan_no`, ':Transporter Challan No:Y:C:mtv_dispatch_challan_master_trading:O') as `transporter_challan_no`,
    concat(`v`.`transporter_challan_date`, ':Transporter Challan Date:Y:D:') as `transporter_challan_date`,
    concat(`v`.`vehicle_no`, ':Vehicle No:O:N:') as `vehicle_no`,
    concat(`v`.`vehicle_type`, ':Vehicle Type:O:N:') as `vehicle_type`,
    concat(`v`.`loading_time`, ':Loading Time:O:N:') as `loading_time`,
    concat(`v`.`transport_mode`, ':Transport Mode:O:N:') as `transport_mode`,
    concat(`v`.`transaction_type`, ':Transaction Type:O:N:') as `transaction_type`,
    concat(`v`.`is_service`, ':Is Service:Y:H:(Yes,No)') as `is_service`,
    concat(`v`.`is_sez`, ':Is Sez:Y:H:(Yes,No)') as `is_sez`,
    concat(`v`.`interim`, ':Interim:Y:H:(Yes,No)') as `interim`,
    concat(`v`.`overall_schedule_date`, ':Overall Schedule Date:Y:D:') as `overall_schedule_date`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') as `basic_total`,
    concat(`v`.`transport_amount`, ':Transport Amount:O:N:') as `transport_amount`,
    concat(`v`.`freight_amount`, ':Freight Amount:O:N:') as `freight_amount`,
    concat(`v`.`is_freight_taxable`, ':Is Freight Taxable:Y:H:(Yes,No)') as `is_freight_taxable`,
    concat(`v`.`freight_fg_hsn_sac_code`, ':FG Hsn Sac Code:O:N:') as `freight_fg_hsn_sac_code`,
    concat(`v`.`freight_fg_hsn_sac_rate`, ':FG Hsn Sac Rate:O:N:') as `freight_fg_hsn_sac_rate`,
    concat(`v`.`packing_amount`, ':Packing Amount:O:N:') as `packing_amount`,
    concat(`v`.`discount_percent`, ':Discount Percent:O:N:') as `discount_percent`,
    concat(`v`.`discount_amount`, ':Discount Amount:O:N:') as `discount_amount`,
    concat(`v`.`other_amount`, ':Other Amount:O:N:') as `other_amount`,
    concat(`v`.`taxable_total`, ':Taxable Total:O:N:') as `taxable_total`,
    concat(`v`.`cgst_percent`, ':CGST Percent:O:N:') as `cgst_percent`,
    concat(`v`.`cgst_total`, ':CGST Total:O:N:') as `cgst_total`,
    concat(`v`.`sgst_percent`, ':SGST Percent:O:N:') as `sgst_percent`,
    concat(`v`.`sgst_total`, ':SGST Total:O:N:') as `sgst_total`,
    concat(`v`.`igst_percent`, ':IGST Percent:O:N:') as `igst_percent`,
    concat(`v`.`igst_total`, ':IGST Total:O:N:') as `igst_total`,
    concat(`v`.`roundoff`, ':RoundOff:O:N:') as `roundoff`,
    concat(`v`.`grand_total`, ':Grand Total:O:N:') as `grand_total`,
    concat(`v`.`agent_name`, ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(`v`.`agent_percent`, ':Agent Percent:O:N:') as `agent_percent`,
    concat(`v`.`agent_amount`, ':Agent Amount:O:N:') as `agent_amount`,
    concat(`v`.`agent_paid_status`, ':Agent Paid Status:O:N:') as `agent_paid_status`,
    concat(`v`.`dispatch_challan_creation_type`, ':Challan Creation Type:Y:H:(Mannual,Dispatch Schedule Based,Sales Order Based)') as `dispatch_challan_creation_type_desc`,
    concat(`v`.`dispatch_challan_status`, ':Dispatch Challan Status:O:N:') as `dispatch_challan_status`,
    concat(`v`.`dispatch_challan_creation_type`, ':Dispatch Challan Creation Type:O:N:') as `dispatch_challan_creation_type`,
    concat(`v`.`customer_email`, ':Customer Email:O:N:') as `customer_email`,
    concat(`v`.`customer_city_name`, ':Customer City:O:N:') as `customer_city_name`,
    concat(`v`.`customer_gst_no`, ':Customer GST No:O:N:') as `customer_gst_no`,
    concat(`v`.`customer_pan_no`, ':Customer Pan No:O:N:') as `customer_pan_no`,
    concat(`v`.`customer_state_name`, ':Customer State:O:N:') as `customer_state_name`,
    concat(`v`.`consignee_name`, ':Consignee Name:O:N:') as `consignee_name`,
    concat(`v`.`consignee_email`, ':Consignee Email:O:N:') as `consignee_email`,
    concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') as `consignee_city_name`,
    concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') as `consignee_state_name`,
    concat(`v`.`consignee_gst_no`, ':Consignee GST No:O:N:') as `consignee_gst_no`,
    concat(`v`.`consignee_pan_no`, ':Consignee Pan No:O:N:') as `consignee_pan_no`,
    concat(`v`.`transporter_gst_no`, ':Transporter GST No:O:N:') as `transporter_gst_no`,
    concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch:Y:C:cmv_company_branch:F') as `company_branch_name`,
    concat(`v`.`financial_year`, ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`dispatch_challan_master_transaction_id`, ':Dispatch Challan Master Transaction Id:O:N:') as `dispatch_challan_master_transaction_id`,
    concat(`v`.`customer_id`, ':Customer Id:N:N:') as `customer_id`,
    concat(`v`.`customer_contacts_ids`, ':Customer Contacts Ids:N:N:') as `customer_contacts_ids`,
    concat(`v`.`customer_state_id`, ':Customer State Id:N:N:') as `customer_state_id`,
    concat(`v`.`customer_city_id`, ':Customer City Id:N:N:') as `customer_city_id`,
    concat(`v`.`consignee_id`, ':Consignee Id:N:N:') as `consignee_id`,
    concat(`v`.`consignee_state_id`, ':Consignee State Id:N:N:') as `consignee_state_id`,
    concat(`v`.`consignee_city_id`, ':Consignee City Id:N:N:') as `consignee_city_id`,
    concat(`v`.`approved_by_id`, ':Approved By Id:N:N:') as `approved_by_id`,
    concat(`v`.`agent_id`, ':Agent Id:N:N:') as `agent_id`,
    concat(`v`.`freight_hsn_code_id`, ':Freight Hsn Code Id:N:N:') as `freight_hsn_code_id`,
    concat(`v`.`transporter_id`, ':Transporter Id:N:N:') as `transporter_id`,
    concat(`v`.`dispatch_challan_type_id`, ':Dispatch Challan Type Id:N:N:') as `dispatch_challan_type_id`
from
    `mtv_dispatch_challan_master_trading` `v`
limit 1;


