UPDATE am_modules_forms
SET modules_forms_name = 'Yarn Sale',
    display_name = 'Yarn Sale',
    page_header = 'Yarn Sale'
WHERE modules_forms_id = 676;


ALTER TABLE pt_goods_return_master
ADD COLUMN `driver_name` varchar(255) DEFAULT NULL,
ADD COLUMN `vehical_no` varchar(255) DEFAULT NULL,
ADD COLUMN `invoice_no` varchar(255) DEFAULT NULL,
ADD COLUMN `customer_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_customer show address, Contact person list ,emails, customer state combo,customer city Combo',
ADD COLUMN `customer_state_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_state show will change on selection of customer combo',
ADD COLUMN `customer_city_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_city show will change on selection of customer combo',
ADD COLUMN `consignee_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_customer show address, Contact person list ,emails, customer state combo,customer city Combo',
ADD COLUMN `consignee_state_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_state show will change on selection of customer combo',
ADD COLUMN `consignee_city_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_city show will change on selection of customer combo',
ADD COLUMN `basic_total` decimal(18,2) DEFAULT 0.00 COMMENT '* read only text box with all realeted sum',
ADD COLUMN `transport_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `freight_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `is_freight_taxable` bit(1) DEFAULT b'0',
ADD COLUMN `freight_hsn_code_id` bigint(20) DEFAULT NULL,
ADD COLUMN `packing_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `discount_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `discount_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `other_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `taxable_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `cgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `cgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `sgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `sgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `igst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `igst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `roundoff` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `grand_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with data entry and numeric validation',
ADD COLUMN `transport_mode` varchar(255) DEFAULT NULL;

ALTER TABLE pt_goods_return_master ADD yarn_type varchar(10) NULL;
-- erp_development.pt_goods_return_bottom_details definition

CREATE TABLE `pt_goods_return_bottom_details` (
  `company_id` int(11) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `company_branch_id` bigint(20) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `financial_year` varchar(20) NOT NULL COMMENT '* Header part will come from Session*',
  `goods_return_bottom_details_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* bottom Details auto generated *',
  `goods_return_master_id` bigint(20) NOT NULL,
  `bottom_return_no` varchar(255) DEFAULT NULL,
  `goods_return_no` varchar(255) DEFAULT NULL,
  `goods_return_bottom_date` date DEFAULT NULL,
  `set_no` varchar(250) DEFAULT NULL,
  `creel_no` varchar(255) DEFAULT NULL,
  `no_of_package` decimal(18,2) DEFAULT 0.00,
  `gross_weight` decimal(18,2) DEFAULT 0.00,
  `net_weight` decimal(18,2) DEFAULT 0.00,
  `tare_weight` decimal(18,2) DEFAULT 0.00,
  `package_type` varchar(255) DEFAULT NULL,
  `cone_type` varchar(100) DEFAULT NULL,
  `bora_box` varchar(100) DEFAULT NULL,
  `cone_type_value` decimal(18,4) DEFAULT 0.0000,
  `sr_no` varchar(255) DEFAULT NULL,
  `batch_no` varchar(255) DEFAULT NULL,
  `weight_per_pkg` decimal(18,4) DEFAULT NULL,
  `no_of_boxes` bigint(20) DEFAULT NULL,
  `product_material_id` varchar(55) DEFAULT NULL,
  `product_material_name` varchar(255) DEFAULT NULL,
  `godown_id` bigint(20) NOT NULL,
  `godown_name` varchar(100) NOT NULL,
  `godown_section_id` bigint(20) DEFAULT NULL,
  `godown_section_beans_id` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `warping_bottom_stock_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`goods_return_bottom_details_id`)
);

ALTER TABLE pt_goods_return_master ADD sales_type_id bigint(20) DEFAULT 0 NOT NULL;

ALTER TABLE xt_weaving_production_warping_bottom_details ADD material_status varchar(25) NULL;


ALTER TABLE pt_goods_return_details
ADD COLUMN `product_material_unit_id` bigint(20) DEFAULT NULL COMMENT '* Data Grid  with Export/Import excel format Entry combo box with cmv_unit',
ADD COLUMN `product_material_packing_id` bigint(20) DEFAULT NULL COMMENT '* Data Grid  with Export/Import excel format Entry combo box with cmv_Packing',
ADD COLUMN `product_hsn_sac_code_id` bigint(20) DEFAULT NULL COMMENT '* Data Grid  with Export/Import excel format Entry combo box with cmv_hsn_csac',
ADD COLUMN `material_basic_amount` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox calculation rate* quantity with validation',
ADD COLUMN `material_discount_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation',
ADD COLUMN `material_discount_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox calculation basic_amount* discount percent with validation',
ADD COLUMN `material_taxable_amount` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox calculation basic_amount- discount amount with validation',
ADD COLUMN `material_cgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer State code with validation',
ADD COLUMN `material_cgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with calculation taxable_amount * cgst Percent percent and numeric validation',
ADD COLUMN `material_sgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer State code with validation',
ADD COLUMN `material_sgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with calculation taxable_amount * sgst Percent percent and numeric validation',
ADD COLUMN `material_igst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer State code with validation',
ADD COLUMN `material_igst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* text box with calculation taxable_amount * igst Percent percent and numeric validation',
ADD COLUMN `material_freight_amount` decimal(18,4) DEFAULT NULL,
ADD COLUMN `product_hsn_sac_rate` decimal(18,4) DEFAULT 0.0000,
ADD COLUMN `material_total_amount` decimal(18,4) DEFAULT 0.0000;




-- erp_development.ptv_goods_return_master source

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

-- erp_development.ptv_goods_return_details source

create or replace
algorithm = UNDEFINED view `ptv_goods_return_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `pt`.`product_rm_id` as `product_rm_id`,
    `pt`.`goods_return_quantity` as `goods_return_quantity`,
    `pt`.`goods_return_weight` as `goods_return_weight`,
    `pt`.`goods_return_boxes` as `goods_return_boxes`,
    `pt`.`goods_return_details_id` as `goods_return_details_id`,
    `pt`.`goods_return_remark` as `goods_return_remark`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`sales_type` as `sales_type`,
    `pdt`.`product_type_name` as `product_type_name`,
    `ptv`.`remark` as `remark`,
    `pt`.`goods_return_rate` as `goods_return_rate`,
    `pt`.`issue_batch_no` as `issue_batch_no`,
    `pt`.`cone_per_wt` as `cone_per_wt`,
    `pt`.`financial_year` as `financial_year`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`product_rm_code` as `product_material_code`,
    `ptv`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `ptv`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    `ptv`.`no_of_boxes` as `no_of_boxes`,
    `ptv`.`total_box_weight` as `total_box_weight`,
    `ptv`.`total_quantity_in_box` as `total_quantity_in_box`,
    `ptv`.`weight_per_box_item` as `weight_per_box_item`,
    `smv`.`closing_balance_quantity` as `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` as `closing_balance_weight`,
    `grm`.`company_id` as `company_id`,
    `grm`.`company_branch_id` as `company_branch_id`,
    `grm`.`goods_return_status` as `goods_return_status`,
    `pt`.`godown_id` as `godown_id`,
    `pt`.`godown_section_id` as `godown_section_id`,
    `pt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_hsn_sac_code_id` as `product_hsn_sac_code_id`,
    `pt`.`product_hsn_sac_rate` as `product_hsn_sac_rate`,
    `pt`.`material_basic_amount` as `material_basic_amount`,
    `pt`.`material_discount_percent` as `material_discount_percent`,
    `pt`.`material_discount_amount` as `material_discount_amount`,
    `pt`.`material_taxable_amount` as `material_taxable_amount`,
    `pt`.`material_cgst_percent` as `material_cgst_percent`,
    `pt`.`material_cgst_total` as `material_cgst_total`,
    `pt`.`material_sgst_percent` as `material_sgst_percent`,
    `pt`.`material_sgst_total` as `material_sgst_total`,
    `pt`.`material_igst_percent` as `material_igst_percent`,
    `pt`.`material_igst_total` as `material_igst_total`,
    `pt`.`material_freight_amount` as `material_freight_amount`,
    `pt`.`material_total_amount` as `material_total_amount`,
    `gs`.`godown_name` as `godown_name`,
    `g`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `vb`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`company_address1` as `company_address1`,
    `vb`.`company_address2` as `company_address2`,
    `vb`.`company_phone_no` as `company_phone_no`,
    `vb`.`company_cell_no` as `company_cell_no`,
    `vb`.`company_EmailId` as `company_EmailId`,
    `vb`.`company_website` as `company_website`,
    `vb`.`company_gst_no` as `company_gst_no`,
    `vb`.`company_pan_no` as `company_pan_no`,
    `vb`.`company_pincode` as `company_pincode`,
    `cbnk`.`company_branch_bank_name` as `company_branch_bank_name`,
    `cbnk`.`company_branch_bank_account_no` as `company_branch_bank_account_no`,
    `cbnk`.`company_branch_bank_ifsc_code` as `company_branch_bank_ifsc_code`,
    `hsn`.`hsn_sac_code` as `product_hsn_sac_code`,
    `un`.`product_unit_name` as `product_unit_name`,
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
    `grm`.`deleted_on` as `deleted_on`
from
    ((((((((((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `pt_goods_receipt_details` `ptv` on
    (`pt`.`goods_receipt_no` = `ptv`.`goods_receipt_no`
        and `pt`.`product_rm_id` = `ptv`.`product_material_id`
        and `ptv`.`is_delete` = 0))
left join `sm_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
        and `pt`.`product_rm_id` = `smv`.`product_rm_id`
        and `smv`.`day_closed` = 0
        and `pt`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pt`.`godown_id`))
left join `cm_godown_section` `g` on
    (`g`.`godown_section_id` = `pt`.`godown_section_id`))
left join `cm_company_banks` `cbnk` on
    (`cbnk`.`company_id` = `pt`.`company_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pt`.`company_branch_id`
        and `vb`.`company_id` = `pt`.`company_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_hsn_sac_code_id`
        and `hsn`.`is_delete` = 0))
left join `sm_product_unit` `un` on
    (`un`.`product_unit_id` = `pt`.`product_material_unit_id`
        and `un`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `ptv_goods_return_master_rpt` as
select
    concat(ifnull(`v`.`goods_return_no`, ''), ':Yarn Sale No:Y:T:') as `goods_return_no`,
    concat(ifnull(`v`.`invoice_no`, ''), ':Invoice Ref.no:Y:T:') as `invoice_no`,
    concat(ifnull(`v`.`goods_return_date`, ''), ':Yarn Sale Date:Y:D:') as `goods_return_date`,
    concat(ifnull(`v`.`sales_type`, ''), ':Sales Type:Y:T:') as `sales_type`,
    concat(ifnull(`v`.`yarn_type`, ''), ':Yarn Type:Y:T:') as `yarn_type`,
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


-- ptv_goods_return_details_rpt source

create or replace
algorithm = UNDEFINED view `ptv_goods_return_details_rpt` as
select
    concat(`v`.`goods_return_no`, ':Yarn Sale No:Y:T:') as `goods_return_no`,
    concat(`v`.`goods_return_date`, ':Yarn Sale Date:Y:D:') as `goods_return_date`,
    concat(`v`.`sales_type`, ':Sales Type:Y:T:') as `sales_type`,
    concat(`v`.`goods_receipt_no`, ':Good Receipt No:Y:T:') as `goods_receipt_no`,
    concat(`v`.`issue_batch_no`, ':Lot No:Y:T:') as `issue_batch_no`,
    concat(`v`.`product_material_name`, ':Product Material Name:Y:T:') as `product_material_name`,
    concat(`v`.`product_material_code`, ':Product Material Code:Y:T:') as `product_material_code`,
    concat(`v`.`weight_per_box_item`, ':Weight per cone:Y:T:') as `weight_per_box_item`,
    concat(`v`.`goods_return_quantity`, ':Goods Return Quantity:O:N:') as `goods_return_quantity`,
    concat(`v`.`goods_return_weight`, ':Goods Return Weight:O:N:') as `goods_return_weight`,
    concat(`v`.`goods_return_boxes`, ':Goods Return Boxes:O:N:') as `goods_return_boxes`,
    concat(`v`.`goods_return_rate`, ':Yarn Sale Rate:Y:T:') as `goods_return_rate`,
    concat(`v`.`product_hsn_sac_code`, ':HSN Code:Y:T:') as `product_hsn_sac_code`,
    concat(`v`.`material_basic_amount`, ':Basic Amount:O:N:') as `material_basic_amount`,
    concat(`v`.`material_discount_amount`, ':Discount Amount:O:N:') as `material_discount_amount`,
    concat(`v`.`material_taxable_amount`, ':Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(`v`.`material_total_amount`, ':Grand Total:O:N:') as `material_total_amount`,
    concat(`v`.`product_unit_name`, ':Product Unit:Y:T:') as `product_unit_name`,
    concat(`v`.`goods_return_remark`, ':Goods Return Remark:O:N:') as `goods_return_remark`,
    concat(`v`.`goods_return_status`, ':Yarn Sale Status:Y:T:') as `goods_return_status`,
    concat(`v`.`product_rm_id`, ':Product Id:N:N') as `product_rm_id`,
    concat(`v`.`godown_name`, ':Godown Name:Y:T:') as `godown_name`,
    concat(`v`.`godown_section_name`, ':Godown Section Name:Y:T:') as `godown_section_name`,
    concat(`v`.`godown_section_beans_name`, ':Godown Section Beans Name:Y:T:') as `godown_section_beans_name`,
    concat(`v`.`product_type_name`, ':Product Type Name:Y:T:') as `product_type_name`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`v`.`goods_return_master_id`, ':Yarn Sale Master Id:O:N:') as `goods_return_master_id`,
    concat(`v`.`godown_id`, ':Godown Id:O:N:') as `godown_id`,
    concat(`v`.`godown_section_id`, ':Godown Section Id:N:N:') as `godown_section_id`,
    concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
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
    concat(`v`.`product_type_id`, ':Product Type Id:N:N:') as `product_type_id`

from
    `ptv_goods_return_details` `v`
limit 1;

-- erp_development.pt_goods_return_payment_terms definition

CREATE TABLE `pt_goods_return_payment_terms` (
  `goods_return_payment_terms_transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `goods_return_master_id` bigint(20) NOT NULL COMMENT '* read only back end auto generated from master id *',
  `goods_return_details_id` bigint(20) DEFAULT NULL COMMENT '* read only back end auto generated *',
  `goods_return_no` varchar(50) NOT NULL COMMENT '*read only text box Combination of Company Short name/FyearShort name/ProductType_group / SO0000Last ID ',
  `goods_return_date` date NOT NULL COMMENT '* DTPpicker Data Entry',
  `goods_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* read only text box auto generated next Version *',
  `payment_terms_id` bigint(20) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_name` varchar(255) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_days` varchar(255) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_grace_days` bigint(20) DEFAULT 0 COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_Milestome` varchar(255) DEFAULT NULL COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_percent` varchar(255) DEFAULT NULL COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_expected_value` decimal(18,4) DEFAULT 1.0000 COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_expected_date` date DEFAULT NULL COMMENT '*  Data Grid  with DTPpicker Data Entry with validation*',
  `payment_paid_flag` varchar(50) DEFAULT 'P' COMMENT ' * combo box  P-Pending, T-Partail,  R-Receipt',
  `payment_paid_transaction_id` varchar(1000) DEFAULT NULL COMMENT '* read only back end will get on : separation basis update once Payment Receipt Id*',
  `payment_paid_date` varchar(1000) DEFAULT NULL COMMENT '*   read only back end on : separation basis will get update once Payment Receipt Date *',
  `remark` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_return_payment_terms_transaction_id`)
);



