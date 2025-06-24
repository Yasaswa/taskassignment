CREATE TABLE `pt_goods_return_fabric_tax_summary` (
  `goods_return_fabric_tax_summary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `financial_year` varchar(20) NOT NULL,
  `supplier_id` bigint(20) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `supplier_state_id` bigint(20) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `supplier_city_id` bigint(20) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `supplier_contacts_ids` varchar(1000) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `expected_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT ' Data will come from Header  Data Grid Entry  ',
  `expected_branch_state_id` bigint(20) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `expected_branch_city_id` bigint(20) DEFAULT NULL COMMENT ' Data will come from Header  Data Grid Entry  ',
  `hsn_code_id` bigint(20) DEFAULT NULL COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `hsn_sac_code` varchar(100) DEFAULT NULL,
  `summary_taxable_amount` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry    ',
  `summary_cgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_cgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_sgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_sgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_igst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_igst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `summary_total_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry shows HSN Wise /Tax percent wise cummulative Totals from Details Data Grid Entry  ',
  `tax_upload__status` varchar(1) DEFAULT 'P' COMMENT 'P-Pending,  C-Completed, X-Canceled  Data Grid   Will update when uploadted to Government sit ',
  `tax_upload_id` varchar(1000) DEFAULT NULL COMMENT '*Data Grid   Will update when uploadted to Government site',
  `tax_upload_date` date DEFAULT NULL COMMENT '* Data Grid   Will update when uploadted to Government sit',
  `remark` varchar(1000) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `goods_return_fabric_no` varchar(50) NOT NULL COMMENT ' * will used from  Header part data',
  `goods_return_fabric_date` date NOT NULL COMMENT '* will used from  Header part data ',
  `goods_return_fabric_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `goods_return_fabric_master_id` bigint(20) NOT NULL,
  PRIMARY KEY (`goods_return_fabric_tax_summary_id`)
) ;


CREATE TABLE `pt_goods_return_fabric_rolls_details` (
  `goods_return_fabric_rolls_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_return_fabric_master_id` bigint(20) DEFAULT NULL COMMENT '* read only back end auto generated id of Master*',
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session*',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch and set default backend from session*',
  `financial_year` varchar(20) NOT NULL,
  `goods_return_fabric_no` varchar(50) NOT NULL COMMENT ' * will used from  Header part data',
  `goods_return_fabric_date` date NOT NULL COMMENT '* will used from  Header part data ',
  `goods_return_fabric_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `style` varchar(50) NOT NULL COMMENT '* will be used from Header part data',
  `roll_no` varchar(50) NOT NULL,
  `sort_no` varchar(50) NOT NULL,
  `product_material_id` varchar(20) DEFAULT NULL COMMENT '* Details Part Data Grid Entry shows grids for selected material id*',
  `goods_return_roll_mtr` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid with Export/Import excel format Entry txtbox data entry with validation*',
  `goods_return_roll_weight` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid with Export/Import excel format Entry txtbox data entry with validation*',
  `goods_return_date` date NOT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_return_fabric_rolls_details_id`)
);



CREATE TABLE `pt_goods_return_fabric_payment_terms` (
  `goods_return_fabric_payment_terms_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `financial_year` varchar(20) DEFAULT NULL COMMENT 'financial year ',
  `goods_return_fabric_master_id` bigint(20) NOT NULL COMMENT '* read only back end auto generated from master id *',
  `goods_return_fabric_details_id` bigint(20) DEFAULT NULL COMMENT '* read only back end auto generated *',
  `goods_return_fabric_no` varchar(50) NOT NULL COMMENT ' * will used from  Header part data',
  `goods_return_fabric_date` date NOT NULL COMMENT '* will used from  Header part data ',
  `goods_return_fabric_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
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
  PRIMARY KEY (`goods_return_fabric_payment_terms_id`)
);




create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_master` as
select
    `mt`.`goods_return_fabric_master_id` as `goods_return_fabric_master_id`,
    `mt`.`goods_return_fabric_no` as `goods_return_fabric_no`,
    `mt`.`goods_return_fabric_date` as `goods_return_fabric_date`,
    `mt`.`goods_return_fabric_version` as `goods_return_fabric_version`,
    `mt`.`goods_purchase_type` as `goods_purchase_type`,
    `mt`.`goods_purchase_tax_type` as `goods_purchase_tax_type`,
    `mt`.`goods_purchase_sales_type` as `goods_purchase_sales_type`,
    `mt`.`goods_purchase_voucher_type` as `goods_purchase_voucher_type`,
    `mt`.`fabric_type` as `fabric_type`,
    `mt`.`supplier_id` as `supplier_id`,
    `cs`.`supp_branch_name` as `supplier_name`,
    `mt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `mt`.`supplier_state_id` as `supplier_state_id`,
    `ct`.`city_name` as `supplier_city_name`,
    `mt`.`supplier_city_id` as `supplier_city_id`,
    `st`.`state_name` as `supplier_state_name`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `mt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `mt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `mt`.`goods_return_fabric_type_id` as `goods_return_fabric_type_id`,
    `mt`.`goods_return_fabric_type` as `goods_return_fabric_type`,
    `mt`.`supplier_challan_no` as `supplier_challan_no`,
    `mt`.`supplier_challan_date` as `supplier_challan_date`,
    `mt`.`supplier_invoice_no` as `supplier_invoice_no`,
    `mt`.`supplier_invoice_date` as `supplier_invoice_date`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`qa_by_id` as `qa_by_id`,
    `mt`.`qa_date` as `qa_date`,
    `mt`.`basic_total` as `basic_total`,
    `mt`.`transport_amount` as `transport_amount`,
    `mt`.`freight_amount` as `freight_amount`,
    `mt`.`is_freight_taxable` as `is_freight_taxable`,
    `mt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `h`.`hsn_sac_code` as `hsn_sac_code`,
    `h`.`hsn_sac_rate` as `hsn_sac_rate`,
    `mt`.`packing_amount` as `packing_amount`,
    `mt`.`goods_return_discount_amount` as `goods_return_discount_amount`,
    `mt`.`goods_return_discount_percent` as `goods_return_discount_percent`,
    `mt`.`other_amount` as `other_amount`,
    `mt`.`taxable_total` as `taxable_total`,
    `mt`.`cgst_total` as `cgst_total`,
    `mt`.`sgst_total` as `sgst_total`,
    `mt`.`igst_total` as `igst_total`,
    `mt`.`roundoff` as `roundoff`,
    `mt`.`grand_total` as `grand_total`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    `mt`.`lr_no` as `lr_no`,
    `mt`.`lr_date` as `lr_date`,
    `mt`.`goods_return_fabric_status` as `goods_return_fabric_status`,
    case
        `mt`.`goods_return_fabric_status` when 'A' then 'Approved'
        else 'Pending'
    end as `goods_return_fabric_status_desc`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`vehicle_no` as `vehicle_no`,
    `mt`.`ev_bill_no` as `ev_bill_no`,
    `mt`.`ev_bill_date` as `ev_bill_date`,
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
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`purchase_type` as `purchase_type`,
    `mt`.`financial_year` as `financial_year`,
    `cmc`.`company_branch_name` as `company_name`,
    `cmc`.`branch_address1` as `company_address`
from
    (((((((`pt_goods_return_fabric_master` `mt`
left join `cm_company_branch` `cmc` on
    (`cmc`.`company_branch_id` = `mt`.`expected_branch_id`
        and `cmc`.`company_id` = `mt`.`company_id`
        and `cmc`.`is_delete` = 0))
left join `cm_supplier_branch` `cs` on
    (`cs`.`supplier_id` = `mt`.`supplier_id`
        and `cs`.`is_delete` = 0))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`supplier_city_id`
        and `ct`.`is_delete` = 0))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`supplier_state_id`
        and `st`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`
        and `h`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `mt`.`approved_by_id`
        and `e`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_details` as
select
    `mt`.`goods_return_fabric_details_id` as `goods_return_fabric_details_id`,
    `mt`.`goods_return_fabric_master_id` as `goods_return_fabric_master_id`,
    `mt`.`goods_return_fabric_no` as `goods_return_fabric_no`,
    `mt`.`goods_return_fabric_date` as `goods_return_fabric_date`,
    `mt`.`goods_return_fabric_version` as `goods_return_fabric_version`,
    `mt`.`item_qa_by_id` as `qa_by_id`,
    `mt`.`item_qa_date` as `qa_date`,
    `mt`.`goods_return_fabric_type_id` as `goods_return_fabric_type_id`,
    `mt`.`goods_return_fabric_type` as `goods_return_fabric_type`,
    `mt`.`sort_no` as `sort_no`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_name` as `product_material_name`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `un`.`product_unit_name` as `product_unit_name`,
    `mt`.`product_material_packing_id` as `product_material_packing_id`,
    `mt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `h`.`hsn_sac_code` as `product_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_hsn_sac_rate`,
    `mt`.`product_goods_return_quantity` as `product_goods_return_quantity`,
    `mt`.`product_goods_return_weight` as `product_goods_return_weight`,
    `mt`.`product_return_no_of_rolls` as `product_return_no_of_rolls`,
    `mt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
    `mt`.`currency_id` as `currency_id`,
    `fc`.`currency_name` as `currency_name`,
    `fc`.`currency_code` as `currency_code`,
    `fc`.`currency_symbol` as `currency_symbol`,
    `mt`.`material_rate` as `material_rate`,
    `mt`.`currency_exchange_rate` as `currency_exchange_rate`,
    `mt`.`material_basic_amount` as `material_basic_amount`,
    `mt`.`material_freight_amount` as `material_freight_amount`,
    `mt`.`material_discount_percent` as `material_discount_percent`,
    `mt`.`material_discount_amount` as `material_discount_amount`,
    `mt`.`material_taxable_amount` as `material_taxable_amount`,
    `mt`.`material_cgst_percent` as `material_cgst_percent`,
    `mt`.`material_cgst_total` as `material_cgst_total`,
    `mt`.`material_sgst_percent` as `material_sgst_percent`,
    `mt`.`material_sgst_total` as `material_sgst_total`,
    `mt`.`material_igst_percent` as `material_igst_percent`,
    `mt`.`material_igst_total` as `material_igst_total`,
    `mt`.`material_total_amount` as `material_total_amount`,
    `mt`.`goods_return_fabric_product_status` as `goods_return_fabric_product_status`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`godown_id` as `godown_id`,
    `cg`.`godown_name` as `godown_name`,
    `mt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `mt`.`godown_section_id` as `godown_section_id`,
    `mt`.`remark` as `remark`,
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
    case
        `pgrfm`.`goods_return_fabric_status` when 'A' then 'Approved'
        else 'Pending'
    end as `goods_return_fabric_status_desc`,
    case
        `mt`.`goods_return_fabric_product_status` when 'A' then 'Approved'
        else 'Pending'
    end as `goods_return_fabric_product_status_desc`,
    case
        when `mt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `pgrfm`.`supplier_id` as `supplier_id`,
    `csb`.`supp_branch_name` as `supplier_name`
from
    (((((((`pt_goods_return_fabric_details` `mt`
left join `pt_goods_return_fabric_master` `pgrfm` on
    (`pgrfm`.`goods_return_fabric_master_id` = `mt`.`goods_return_fabric_master_id`
        and `pgrfm`.`is_delete` = 0))
left join `fm_currency` `fc` on
    (`fc`.`currency_id` = `mt`.`currency_id`
        and `fc`.`is_delete` = 0))
left join `cm_godown` `cg` on
    (`cg`.`godown_id` = `mt`.`godown_id`
        and `cg`.`is_delete` = 0))
left join `cm_supplier_branch` `csb` on
    (`csb`.`supplier_id` = `pgrfm`.`supplier_id`
        and `csb`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`product_material_hsn_code_id`
        and `h`.`is_delete` = 0))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `mt`.`company_branch_id`
        and `vb`.`company_id` = `mt`.`company_id`))
left join `sm_product_unit` `un` on
    (`un`.`product_unit_id` = `mt`.`product_material_unit_id`
        and `un`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;

create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_master_rpt` as
select
    concat(`v`.`goods_return_fabric_no`, ':Goods Return No:Y:T:') as `goods_return_fabric_no`,
    concat(`v`.`goods_return_fabric_version`, ':Goods Return Version:Y:T:') as `goods_return_fabric_version`,
    concat(`v`.`goods_return_fabric_type`, ':Fabric Type:Y:T:') as `goods_return_fabric_type`,
    concat(`v`.`goods_return_fabric_date`, ':Goods Return Date:Y:D:') as `goods_return_fabric_date`,
    concat(`v`.`purchase_type`, ':Purcahse Type:Y:T:') as `purchase_type`,
    concat(`v`.`supplier_name`, ':Supplier Name:Y:T:') as `supplier_name`,
    concat(`v`.`goods_return_fabric_status_desc`, ':Goods Return Status:Y:H:(Aprroved,Pending)') as `goods_return_fabric_status_desc`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:T:') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Sales Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`supplier_invoice_no`, ''), ':Supplier Invoice No:Y:T:') as `supplier_invoice_no`,
    concat(ifnull(`v`.`supplier_invoice_date`, ''), ':Supplier Invoice Date:Y:D:') as `supplier_invoice_date`,
    concat(`v`.`vehicle_no`, ':Vehicle No:Y:T:') as `vehicle_no`,
    concat(`v`.`lr_no`, ':Lr. No:Y:T:') as `lr_no`,
    concat(`v`.`lr_date`, ':Lr. Date:Y:D:') as `lr_date`,
    concat(`v`.`ev_bill_no`, ':Ev Bill No:O:N:') as `ev_bill_no`,
    concat(`v`.`ev_bill_date`, ':Ev Bill Date:O:N:') as `ev_bill_date`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') as `basic_total`,
    concat(`v`.`agent_name`, ':Agent Name:O:N:') as `agent_name`,
    concat(`v`.`financial_year`, ': Financial Year:Y:T:') as `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`goods_return_fabric_master_id`, ':Return Fabric Master Transaction Id:O:N:') as `goods_return_fabric_master_id`
from
    `ptv_goods_return_fabric_master` `v`
limit 1;


create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_details_rpt` as
select
    concat(`v`.`goods_return_fabric_no`, ':Goods Return No:Y:T:') as `goods_return_fabric_no`,
    concat(`v`.`goods_return_fabric_version`, ':Goods Return Version:Y:T:') as `goods_return_fabric_version`,
    concat(`v`.`goods_return_fabric_type`, ':Fabric Type:Y:T:') as `goods_return_fabric_type`,
    concat(`v`.`goods_return_fabric_date`, ':Goods Return Date:Y:D:') as `goods_return_fabric_date`,
    concat(`v`.`supplier_name`, ':Supplier Name:Y:T:') as `supplier_name`,
    concat(`v`.`goods_return_fabric_status_desc`, ':Goods Return Status:Y:H:(Aprroved,Pending)') as `goods_return_fabric_status_desc`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`product_hsn_sac_code`, ''), ':Product HSN Code:Y:T:') as `product_hsn_sac_code`,
    concat(`v`.`product_goods_return_quantity`, ':Goods Return Qty.:O:N:') as `product_goods_return_quantity`,
    concat(`v`.`product_goods_return_weight`, ':Goods Return Wt.:O:N:') as `product_goods_return_weight`,
    concat(`v`.`material_basic_amount`, ':Basic Amount:O:N:') as `material_basic_amount`,
    concat(`v`.`material_total_amount`, ':Total Amount:O:N:') as `material_total_amount`,
    concat(`v`.`currency_name`, ':Currency Name:O:N:') as `currency_name`,
    concat(`v`.`godown_name`, ':Godown Name:O:N:') as `godown_name`,
    concat(`v`.`financial_year`, ': Financial Year:Y:T:') as `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`goods_return_fabric_details_id`, ':Return Fabric Details Transaction Id:O:N:') as `goods_return_fabric_details_id`,
    concat(`v`.`goods_return_fabric_master_id`, ':Return Fabric Master Transaction Id:O:N:') as `goods_return_fabric_master_id`
from
    `ptv_goods_return_fabric_details` `v`
limit 1;