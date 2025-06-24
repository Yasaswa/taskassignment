
-- pt_grn_cotton_bales_master definition

CREATE TABLE `pt_grn_cotton_bales_master` (
  `grn_cotton_bales_master_transaction_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* Header part read only back end auto generated *',
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `financial_year` varchar(20) NOT NULL COMMENT '*backend from session  ',
  `goods_receipt_no` varchar(50) NOT NULL COMMENT '1/1 * Header part read only text box Combination of Company Short name/FyearShort name/ProductType_group / GR0000Last ID ',
  `goods_receipt_date` date NOT NULL COMMENT '1/1/2* Header part  DTPpicker Data Entry set todays date ',
  `goods_receipt_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* 1/1/3 Header part read only text box auto generated next Version *',
  `supplier_branch_id` bigint(20) DEFAULT NULL COMMENT '* 1/2 Header part combo box with cmv_supllier show address, Contact person list ,emails, supplier state combo,supplier city Combo ',
  `supplier_state_id` bigint(20) DEFAULT NULL COMMENT '* 1/3 Header part read only combo box with cmv_state show will change on selection of supllier combo ',
  `supplier_city_id` bigint(20) DEFAULT NULL COMMENT '* 1/4 Header part read only  combo box with cmv_city show will change on selection of supllier combo ',
  `supplier_contacts_ids` varchar(1000) DEFAULT NULL COMMENT '* 1/5  Header part read only  grid data show all supplier details saved in purchase order  will change on selection of supllier combo and saved in supplier_contact_id concanitate with :',
  `expected_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '* 2/2  Header part read only combo box fill with cmv_company_branch  and set on purchase order _combo selection from ptv_purchase_order_Details   *',
  `expected_branch_state_id` bigint(20) DEFAULT NULL COMMENT '* 2/3   Header part read only  combo box fill with cmv_state and set on purchase order _combo selection from ptv_purchase_order_Details   ',
  `expected_branch_city_id` bigint(20) DEFAULT NULL COMMENT ' * 2/4  Header part read only  combo box fill with cmv_city show and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `purchase_order_no` varchar(50) DEFAULT NULL COMMENT '1/6/1 *  Header part read only combo box with ptv_purchase_order_details show only order of selected supplier and having status with P or A  and set purchase order date & version of slected purchase order ',
  `purchase_order_date` date DEFAULT NULL COMMENT '* 1/6/2  Header part read only DTPpicker    and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `purchase_order_version` bigint(20) DEFAULT 1 COMMENT '* 1/6/3  Header part read only txt box and set on purchase order _combo selection from ptv_purchase_order_Details  *',
  `goods_receipt_type_id` bigint(20) DEFAULT 2 COMMENT ' * 2/5/1  Header part read only read only combo box with cmv_product_type  and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `goods_receipt_type` varchar(100) DEFAULT NULL COMMENT '2/5/2 * Header part read only  text box will set purchase_order_no combos selected from ptv_purchase_order_Details     and save product group ',
  `supplier_challan_no` varchar(500) DEFAULT NULL COMMENT '* 1/9/1  Header part text box data entry ',
  `supplier_challan_date` date NOT NULL COMMENT '* 1/9/2  Header part  DTPpicker data entry ',
  `approved_by_id` bigint(20) DEFAULT NULL COMMENT '  2/8/1 *  Header part combo box with cmv_employee with department id and deaprtment head filter  and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `approved_date` date DEFAULT NULL COMMENT '2/8/2* combo box with dtppicker data entry  and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `qa_by_id` bigint(20) DEFAULT NULL COMMENT '  2/9/1 *  Header part combo box with cmv_employee with department id ',
  `qa_date` date DEFAULT NULL COMMENT '2/9/2* combo box with dtppicker data entry   ',
  `basic_total` decimal(18,2) DEFAULT 0.00 COMMENT ' 1/1 *  footer part read only text box with all realeted sum',
  `is_freight_taxable` bit(1) DEFAULT b'0' COMMENT '1/3/2    footer part  check box   ',
  `transport_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '1/2    footer part  text box with data entry and numeric validation on chenge if is_freight_taxable selected calculate CGST & SGST /IGST as per states & Tax calcualtion logic and add that ',
  `freight_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '1/3/1    footer part  text box with data entry and numeric validation',
  `freight_hsn_code_id` bigint(20) DEFAULT NULL,
  `insurance_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '1/4  footer part   text box with data entry and numeric validation',
  `goods_receipt_discount_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '1/5/1   footer part  text box with data entry and on change calculates po_discount_amount and numeric validation',
  `goods_receipt_discount_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '1/5/2   footer part   text box with data entry and on change calculates po_discount_percent and numeric validation',
  `other_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '1/6  footer part   text box with data entry and numeric validation',
  `taxable_total` decimal(18,4) DEFAULT 0.0000 COMMENT '1/7  footer part read only text box ( auto calcualted formulla Basic Total-Discount AMount)   and numeric validation',
  `cgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* 2/1   footer part read only text box with sum of all realeted values cgst_amount cloumn in Detail Grid ',
  `sgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* 2/2   footer part read only text box with sum of all realeted values sgst_amount cloumn in Detail Grid ',
  `igst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '* 2/3   footer part read only text box with sum of all realeted values igst_amount cloumn in Detail Grid ',
  `grand_total` decimal(18,4) DEFAULT 0.0000 COMMENT '2/4/1  footer part read only text box ( auto calcualted formulla Basic Total-Discount AMount)   and numeric validation',
  `roundoff` decimal(18,4) DEFAULT 0.0000 COMMENT '2/4/2  footer part read only text box ( auto calcualted formulla  round up diffrance of grand Total  )   and numeric validation',
  `agent_id` bigint(20) DEFAULT NULL COMMENT '2/5/1  footer part read only combo box fill with cmv_customer show address, Contact person list ,emails, customer state combo,customer city Combo and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `agent_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '2/6/1  footer part read only  text box with data entry and numeric validation and set on purchase order _combo selection from ptv_purchase_order_Details',
  `agent_paid_status` varchar(1) DEFAULT 'P' COMMENT ' 2/6/2  footer part read only redio button  P-Pending,A-Aprroved,  C-Completed, X-Canceled  will changes according to trnasaction  done ',
  `goods_receipt_status` varchar(1) DEFAULT 'G' COMMENT '  2/7  footer part read only combo G-GRN Done, Q-QC Done, R-Rejected,I-Partial Receipt, C-Completed, X-Canceled  will changes according to trnasactions  done ',
  `lr_no` varchar(100) DEFAULT NULL COMMENT '3/2/1  footer part   text box with data entry  ',
  `lr_date` date DEFAULT NULL COMMENT ' 3/2/2  footer part     dtppicker   Data entry ',
  `vehicle_no` varchar(100) DEFAULT NULL COMMENT '3/3  footer part   text box with data entry  ',
  `supplier_invoice_no` varchar(50) DEFAULT NULL,
  `supplier_invoice_date` date DEFAULT NULL,
  `ev_bill_no` varchar(50) DEFAULT NULL,
  `ev_bill_date` date DEFAULT NULL,
  `gate_entry_no` varchar(50) DEFAULT NULL,
  `gate_entry_date` date DEFAULT NULL,
  `station` varchar(155) DEFAULT NULL,
  `invoice_amount` decimal(18,4) DEFAULT 0.0000,
  `amount_difference` decimal(18,4) DEFAULT 0.0000,
  `transporter_name` varchar(50) DEFAULT NULL,
  `is_preeclosed` bit(1) DEFAULT b'0',
  `remark` varchar(1000) DEFAULT NULL COMMENT '3/5 footer part   text box with data entry and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `is_active` bit(1) DEFAULT b'1' COMMENT '3/6  footer part   check box with data entry  ',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '*backend from deleted  slection  ',
  `created_by` varchar(255) DEFAULT '1' COMMENT '*backend from session user id  ',
  `created_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time   ',
  `modified_by` varchar(255) DEFAULT NULL COMMENT '*backend from session user id when update functionality done only  ',
  `modified_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time when update functionality done only  ',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '*backend from session user id when delete functionality done only  ',
  `deleted_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time when delete functionality done only  ',
  PRIMARY KEY (`grn_cotton_bales_master_transaction_id`)
);


-- pt_grn_cotton_bales_details definition

CREATE TABLE `pt_grn_cotton_bales_details` (
  `grn_cotton_bales_details_transaction_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* will used from  Header part data',
  `grn_cotton_bales_master_transaction_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `financial_year` varchar(20) NOT NULL COMMENT '* will used from  Header part data ',
  `goods_receipt_no` varchar(50) NOT NULL COMMENT ' * will used from  Header part data',
  `goods_receipt_date` date NOT NULL COMMENT '* will used from  Header part data ',
  `goods_receipt_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `purchase_order_no` varchar(50) NOT NULL COMMENT '* will used from  Header part data ',
  `purchase_order_date` date NOT NULL COMMENT '* will used from  Header part data ',
  `purchase_order_version` bigint(20) NOT NULL DEFAULT 1 COMMENT '* will used from  Header part data',
  `item_qa_by_id` bigint(20) DEFAULT NULL COMMENT '    *  Details data grid   combo box with cmv_employee with department id  and set header part  Qa by id by default',
  `item_qa_date` date DEFAULT NULL COMMENT 'Details data grid  dtppicker data entry  and set header part  Qa  Date by default  ',
  `goods_receipt_type_id` bigint(20) DEFAULT 2 COMMENT ' * Details part Data Grid Entry  with Export/Import excel combo box with cmv_product_type  and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `goods_receipt_type` varchar(100) DEFAULT NULL COMMENT ' * Details part  Data  Grid Entry  with Export/Import excel  text box will set purchase_order_no combos selected from ptv_purchase_order_Details     and save product group ',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '*MILL LOT NO*',
  `supplier_batch_no` varchar(50) DEFAULT NULL COMMENT '*SUPPLIER LOT NO*',
  `product_material_id` varchar(20) DEFAULT NULL COMMENT '* Details Part Data Grid Entry  with Export/Import excel format  combo box with cmv_product_rm/cmv_product_fg/cmv_product_sr with all technical parameter shows ing grids for selected material / and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_material_unit_id` bigint(20) DEFAULT NULL COMMENT '* Details Part Data Grid  with Export/Import excel format Entry combo box with cmv_unit  and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_purchase_unit_id` bigint(20) DEFAULT NULL,
  `product_material_conversion_factor` decimal(18,4) DEFAULT 1.0000 COMMENT '* Details Part Data Grid  with Export/Import excel format Entry txtbox will come from munit convert it to Basic Stock Unit  with validation /and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_material_packing_id` bigint(20) DEFAULT NULL COMMENT '* Details Part Data Grid  with Export/Import excel format Entry combo box with cmv_Packing and set on purchase order _combo selection from ptv_purchase_order_Details',
  `product_material_hsn_code_id` bigint(20) DEFAULT NULL COMMENT '* Details Part Data Grid  with Export/Import excel format Entry combo box with cmv_hsn_csac and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_material_po_approved_quantity` decimal(18,4) DEFAULT 1.0000 COMMENT '* Details Part Data Grid  with Export/Import excel format Entry txtbox data entry with validation and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_material_po_approved_weight` decimal(18,4) DEFAULT 1.0000 COMMENT '* Details Part Data Grid  with Export/Import excel format Entry txtbox data entry with validation and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `product_material_grn_quantity` decimal(18,4) DEFAULT 1.0000 COMMENT '* Details Part Data Grid  with Export/Import excel format Entry txtbox data entry with validation / and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `product_material_grn_weight` decimal(18,4) DEFAULT 1.0000 COMMENT '* Details Part Data Grid  with Export/Import excel format Entry txtbox data entry with validation / and set on purchase order _combo selection from ptv_purchase_order_Details   ',
  `product_material_grn_accepted_quantity` decimal(18,4) DEFAULT 0.0000 COMMENT '* Text box Data Entry with numeric validation ',
  `product_material_grn_accepted_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Text box Data Entry with numeric validation',
  `product_material_grn_rejected_quantity` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation / If On iNdent basesis Show all the Data of selected indent and vesrion ',
  `product_material_grn_rejected_weight` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation    ',
  `currency_id` bigint(20) DEFAULT 3 COMMENT 'default rupees given',
  `po_material_rate` decimal(18,2) DEFAULT 0.00 COMMENT 'po material rate will come from po',
  `material_rate` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation  / Show last Purchase rate by default',
  `grn_currency_id` bigint(20) DEFAULT 3 COMMENT 'default grn currency is rupees',
  `currency_exchange_rate` decimal(18,4) DEFAULT 0.0000 COMMENT 'exchange rate of base currency INR',
  `material_basic_amount` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox caculation rate* quantity with validation ',
  `material_freight_amount` decimal(18,4) DEFAULT NULL,
  `material_discount_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation ',
  `material_discount_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox caculation basic_amount* discount percent with validation ',
  `material_taxable_amount` decimal(18,4) DEFAULT 1.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox caculation basic_amount- discount amount with validation ',
  `material_cgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer Stae code with validation ',
  `material_cgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with caculation taxable_amount * cgst Percent percent and numeric validation',
  `material_sgst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer Stae code with validation ',
  `material_sgst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with caculation taxable_amount * sgst Percent percent and numeric validation',
  `material_igst_percent` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data on the basis of HSN Code & Customer Stae code with validation ',
  `material_igst_total` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with caculation taxable_amount * igst Percent percent and numeric validation',
  `material_total_amount` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox caculation taxable Amount +GST Amount+ SGst Amount + IGstAmount  with validation ',
  `material_schedule_date` date DEFAULT NULL COMMENT '*Data Grid  with Export/Import excel format Entry dtppicker data entry schedule expected date',
  `grn_item_status` varchar(1) DEFAULT 'G' COMMENT 'combo G-GRN Done, Q-QC Done, R-Rejected,I-Partial Receipt, C-Completed, X-Canceled Z-PreeClosed  will changes according to trnasactions  done  ',
  `excess_quantity` decimal(18,4) DEFAULT 0.0000 COMMENT '* Dispaly in purchase order tacking Tab Data Grid  with Export/Import excel format  will update on Perticular transaction done ',
  `excess_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Dispaly in purchase order tacking Tab Data Grid  with Export/Import excel format  will update on Perticular transaction done ',
  `pree_closed_grn_quantity` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation  as well as add previous preeclosed quantity ',
  `pree_closed_grn_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Data Grid  with Export/Import excel format Entry txtbox data entry with validation  as well as add previous preeclosed quantity  ',
  `invoice_gross_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '*Suppliers Gross weight Manual Entry ',
  `invoice_tare_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Suppliers Tare weight Manual Entry  ',
  `invoice_net_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Calculate Suppliers Gross weight - Suppliers Tare Weight',
  `mill_gross_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Manual Entry',
  `mill_tare_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Manual Entry',
  `mill_net_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Calculate Mill Gross weight - Mill Tare Weight',
  `difference_weight` decimal(18,4) DEFAULT 0.0000 COMMENT '* Shortage at mill- Auto calculate invoice_net_weight- mill_net_weight ',
  `godown_id` bigint(20) DEFAULT NULL,
  `godown_section_id` bigint(20) DEFAULT NULL,
  `godown_section_beans_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT 0 COMMENT '* Data Grid  with Export/Import excel format Entry combo box with cmv_department data entry with validation / and set on purchase order _combo selection from ptv_purchase_order_Details ',
  `grn_remark` varchar(1000) DEFAULT NULL COMMENT '3/5 footer part   text box with data entry and set on purchase order _combo selection from ptv_purchase_order_Details  ',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '*backend from deleted  slection  ',
  `created_by` varchar(255) DEFAULT '1' COMMENT '*backend from session user id  ',
  `created_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time   ',
  `modified_by` varchar(255) DEFAULT NULL COMMENT '*backend from session user id when update functionality done only  ',
  `modified_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time when update functionality done only  ',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '*backend from session user id when delete functionality done only  ',
  `deleted_on` datetime DEFAULT NULL COMMENT '*backend from session server current date time when delete functionality done only  ',
  `purchase_order_details_transaction_id` bigint(20) NOT NULL,
  `product_material_rejection_reason_id` bigint(20) DEFAULT NULL,
  `press_running_no_from` bigint(20) DEFAULT 0,
  `press_running_no_to` bigint(18) DEFAULT 0,
  `press_running_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`grn_cotton_bales_details_transaction_id`)
);

-- ptv_grn_cotton_bales_summary source

create or REPLACE algorithm = UNDEFINED view `ptv_grn_cotton_bales_summary` as
select
    `pt`.`goods_receipt_type` as `goods_receipt_type`,
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `pt`.`goods_receipt_date` as `goods_receipt_date`,
    `pt`.`goods_receipt_version` as `goods_receipt_version`,
    case
        `pt`.`goods_receipt_status` when 'G' then 'GRN Done'
        when 'Q' then 'QC Done'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'GRN'
    end as `goods_receipt_status_desc`,
    `sup`.`supp_branch_name` as `supp_branch_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `pt`.`supplier_invoice_no` as `supplier_invoice_no`,
    `pt`.`supplier_invoice_date` as `supplier_invoice_date`,
    `pt`.`supplier_challan_no` as `supplier_challan_no`,
    `pt`.`supplier_challan_date` as `supplier_challan_date`,
    `a`.`agent_name` as `agent_name`,
    `pt`.`agent_percent` as `agent_percent`,
    `pt`.`agent_paid_status` as `agent_paid_status`,
    `e1`.`employee_name` as `qa_by_name`,
    `pt`.`qa_date` as `qa_date`,
    `e`.`employee_name` as `approved_by_name`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`basic_total` as `basic_total`,
    `pt`.`transport_amount` as `transport_amount`,
    `hsn`.`hsn_sac_code` as `freight_hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `freight_hsn_sac_rate`,
    `pt`.`freight_amount` as `freight_amount`,
    `pt`.`is_freight_taxable` as `is_freight_taxable`,
    `pt`.`insurance_amount` as `insurance_amount`,
    `pt`.`goods_receipt_discount_percent` as `goods_receipt_discount_percent`,
    `pt`.`goods_receipt_discount_amount` as `goods_receipt_discount_amount`,
    `pt`.`other_amount` as `other_amount`,
    `pt`.`taxable_total` as `taxable_total`,
    `pt`.`cgst_total` as `cgst_total`,
    `pt`.`sgst_total` as `sgst_total`,
    `pt`.`igst_total` as `igst_total`,
    `pt`.`grand_total` as `grand_total`,
    `pt`.`roundoff` as `roundoff`,
    `pt`.`goods_receipt_status` as `goods_receipt_status`,
    `pt`.`lr_no` as `lr_no`,
    `pt`.`lr_date` as `lr_date`,
    `pt`.`vehicle_no` as `vehicle_no`,
    `pt`.`gate_entry_no` as `gate_entry_no`,
    `pt`.`gate_entry_date` as `gate_entry_date`,
    `pt`.`station` as `station`,
    `pt`.`invoice_amount` as `invoice_amount`,
    `pt`.`amount_difference` as `amount_difference`,
    `pt`.`transporter_name` as `transporter_name`,
    `sup`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `sup`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `sup`.`supp_branch_address1` as `supp_branch_address1`,
    `sup`.`supp_branch_pincode` as `supp_branch_pincode`,
    `sup`.`supp_branch_gst_no` as `supp_branch_gst_no`,
    `sup`.`supp_branch_pan_no` as `supp_branch_pan_no`,
    `pt`.`ev_bill_no` as `ev_bill_no`,
    `pt`.`ev_bill_date` as `ev_bill_date`,
    `pt`.`remark` as `remark`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `ct`.`city_name` as `supplier_city_name`,
    `s`.`state_name` as `supplier_state_name`,
    `pt`.`financial_year` as `financial_year`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`is_preeclosed` as `is_preeclosed`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`grn_cotton_bales_master_transaction_id` as `grn_cotton_bales_master_transaction_id`,
    `pt`.`qa_by_id` as `qa_by_id`,
    `pt`.`agent_id` as `agent_id`,
    `pt`.`supplier_branch_id` as `supplier_branch_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    `pt`.`supplier_state_id` as `supplier_state_id`,
    `pt`.`supplier_city_id` as `supplier_city_id`,
    `pt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pt`.`expected_branch_id` as `expected_branch_id`,
    `pt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
    `pt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `sup`.`supp_branch_payment_term_id` as `payment_term_id`,
    `p`.`product_type_short_name` as `product_type_short_name`
from
    ((((((((((`pt_grn_cotton_bales_master` `pt`
left join `cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `pt`.`supplier_branch_id`
        and `pt`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`qa_by_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `pt`.`supplier_city_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `pt`.`supplier_state_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `pt`.`agent_id`
        and `a`.`company_id` = `pt`.`company_id`))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`expected_branch_id`
        and `cb`.`is_delete` = 0))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`goods_receipt_type_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`freight_hsn_code_id`
        and `hsn`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;



    -- ptv_grn_cotton_bales_details source

    create or REPLACE algorithm = UNDEFINED view `ptv_grn_cotton_bales_details` as
    select
        `pt`.`goods_receipt_no` as `goods_receipt_no`,
        `pt`.`goods_receipt_date` as `goods_receipt_date`,
        `pt`.`goods_receipt_version` as `goods_receipt_version`,
        case
            `pt`.`grn_item_status` when 'G' then 'GRN Done'
            when 'Q' then 'QC Done'
            when 'R' then 'Rejected'
            when 'I' then 'Partial Receipt'
            when 'C' then 'Completed'
            when 'X' then 'Canceled'
            when 'Z' then 'Pree Closed'
            else 'GRN'
        end as `grn_item_status_desc`,
        `pt`.`purchase_order_no` as `purchase_order_no`,
        `pt`.`purchase_order_date` as `purchase_order_date`,
        `pt`.`purchase_order_version` as `purchase_order_version`,
        `grm`.`supplier_challan_no` as `supplier_challan_no`,
        `grm`.`supplier_challan_date` as `supplier_challan_date`,
        `sup`.`supp_branch_name` as `supplier_name`,
        `ct`.`city_name` as `supplier_city_name`,
        `ss`.`state_name` as `supplier_state_name`,
        `pt`.`item_qa_by_id` as `item_qa_by_id`,
        `pt`.`item_qa_date` as `item_qa_date`,
        `pt`.`goods_receipt_type` as `goods_receipt_type`,
        `pt`.`batch_no` as `batch_no`,
        `rm`.`product_rm_code` as `product_material_code`,
        `rm`.`product_rm_name` as `product_material_name`,
        `u`.`product_unit_name` as `product_material_stock_unit_name`,
        `mcom`.`product_rm_std_weight` as `product_material_std_weight`,
        `cat1`.`product_category1_name` as `product_category1_name`,
        `cat2`.`product_category2_name` as `product_category2_name`,
        `pk`.`product_packing_name` as `product_packing_name`,
        `hsn`.`hsn_sac_code` as `hsn_sac_code`,
        `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
        `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
        `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
        `pt`.`product_material_grn_quantity` as `product_material_grn_quantity`,
        `pt`.`product_material_grn_weight` as `product_material_grn_weight`,
        `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
        `pt`.`product_material_grn_rejected_quantity` as `product_material_grn_rejected_quantity`,
        `pt`.`product_material_grn_rejected_weight` as `product_material_grn_rejected_weight`,
        `rp`.`product_rejection_type` as `product_rejection_type`,
        `rp`.`product_rejection_parameters_name` as `product_rejection_parameters_name`,
        `rp`.`product_rejection_parameters_short_name` as `product_rejection_parameters_short_name`,
        `pt`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
        `pt`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
        coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_grn_cotton_bales_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_quantity`,
        coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_grn_cotton_bales_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_weight`,
        `pt`.`product_material_grn_accepted_quantity` as `product_material_prev_accepted_quantity`,
        `pt`.`product_material_grn_accepted_weight` as `product_material_prev_accepted_weight`,
        `pt`.`po_material_rate` as `po_material_rate`,
        `fpo`.`currency_name` as `currency_name`,
        `pt`.`material_rate` as `material_rate`,
        `fgrn`.`currency_name` as `grn_currency_name`,
        `pt`.`material_basic_amount` as `material_basic_amount`,
        `pt`.`material_freight_amount` as `material_freight_amount`,
        `pt`.`material_discount_percent` as `material_discount_percent`,
        `pt`.`material_discount_amount` as `material_discount_amount`,
        `pt`.`material_taxable_amount` as `material_taxable_amount`,
        `pt`.`material_cgst_percent` as `material_cgst_percent`,
        `pt`.`material_cgst_total` as `material_cgst_total`,
        `pt`.`material_sgst_percent` as `material_sgst_percent`,
        `pt`.`material_sgst_total` as `material_sgst_total`,
        `pt`.`material_igst_percent` as `material_igst_percent`,
        `pt`.`material_igst_total` as `material_igst_total`,
        `pt`.`material_total_amount` as `material_total_amount`,
        `pt`.`material_schedule_date` as `material_schedule_date`,
        `pt`.`grn_item_status` as `grn_item_status`,
        `pt`.`excess_quantity` as `excess_quantity`,
        `pt`.`excess_weight` as `excess_weight`,
        `pt`.`pree_closed_grn_quantity` as `pree_closed_grn_quantity`,
        `pt`.`pree_closed_grn_weight` as `pree_closed_grn_weight`,
        `pt`.`grn_remark` as `grn_remark`,
        `c`.`company_legal_name` as `company_name`,
        `cb`.`company_branch_name` as `company_branch_name`,
        `pt`.`financial_year` as `financial_year`,
        `e2`.`employee_name` as `approved_by_name`,
        `g`.`godown_name` as `godown_name`,
        `gs`.`godown_section_name` as `godown_section_name`,
        `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
        `pdt`.`product_type_group` as `product_material_type_group`,
        `pdt`.`product_type_name` as `product_type_name`,
        `pdt`.`product_type_short_name` as `product_type_short_name`,
        `fpc`.`cost_center_name` as `cost_center_name`,
        `pt`.`currency_exchange_rate` as `currency_exchange_rate`,
        `fgrn`.`currency_code` as `grn_currency_code`,
        `fgrn`.`currency_symbol` as `currency_symbol`,
        case
            when `pt`.`is_delete` = 1 then 'Yes'
            else 'No'
        end as `Deleted`,
        `pt`.`is_delete` as `is_delete`,
        `pt`.`created_by` as `created_by`,
        `pt`.`created_on` as `created_on`,
        `pt`.`modified_by` as `modified_by`,
        `pt`.`modified_on` as `modified_on`,
        `pt`.`deleted_by` as `deleted_by`,
        `pt`.`deleted_on` as `deleted_on`,
        `pt`.`company_id` as `company_id`,
        `pt`.`company_branch_id` as `company_branch_id`,
        `pt`.`grn_cotton_bales_details_transaction_id` as `grn_cotton_bales_details_transaction_id`,
        `pt`.`grn_cotton_bales_master_transaction_id` as `grn_cotton_bales_master_transaction_id`,
        `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
        `pt`.`department_id` as `department_id`,
        `pt`.`product_material_unit_id` as `product_material_unit_id`,
        `pt`.`product_material_packing_id` as `product_material_packing_id`,
        `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
        `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
        `pt`.`product_material_rejection_reason_id` as `product_material_rejection_reason_id`,
        `grm`.`goods_receipt_status` as `goods_receipt_status`,
        `grm`.`supplier_branch_id` as `supplier_branch_id`,
        `grm`.`approved_by_id` as `approved_by_id`,
        `grm`.`supplier_state_id` as `supplier_state_id`,
        `grm`.`supplier_city_id` as `supplier_city_id`,
        `grm`.`supplier_contacts_ids` as `supplier_contacts_ids`,
        `grm`.`expected_branch_id` as `expected_branch_id`,
        `grm`.`expected_branch_state_id` as `expected_branch_state_id`,
        `grm`.`expected_branch_city_id` as `expected_branch_city_id`,
        `sup`.`supp_branch_payment_term_id` as `payment_term_id`,
        `grm`.`agent_id` as `agent_id`,
        `pt`.`product_material_id` as `product_material_id`,
        `pt`.`godown_id` as `godown_id`,
        `pt`.`godown_section_id` as `godown_section_id`,
        `pt`.`godown_section_beans_id` as `godown_section_beans_id`,
        `rm`.`product_category1_id` as `product_category1_id`,
        `mtech`.`product_category2_id` as `product_category2_id`,
        `pt`.`currency_id` as `currency_id`,
        `pt`.`grn_currency_id` as `grn_currency_id`
    from
        ((((((((((((((((((((((((`pt_grn_cotton_bales_details` `pt`
    left join `cm_company` `c` on
        (`c`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `cm_company_branch` `cb` on
        (`cb`.`company_branch_id` = `pt`.`company_branch_id`
            and `cb`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `pt_grn_cotton_bales_master` `grm` on
        (`grm`.`grn_cotton_bales_master_transaction_id` = `pt`.`grn_cotton_bales_master_transaction_id`
            and `grm`.`company_id` = `pt`.`company_id`
            and `grm`.`company_branch_id` = `pt`.`company_branch_id`))
    left join `cm_supplier_branch` `sup` on
        (`sup`.`supp_branch_id` = `grm`.`supplier_branch_id`
            and `pt`.`is_delete` = 0))
    left join `cm_city` `ct` on
        (`ct`.`city_id` = `grm`.`supplier_city_id`))
    left join `cm_state` `ss` on
        (`ss`.`state_id` = `grm`.`supplier_state_id`))
    left join `sm_product_unit` `u` on
        (`u`.`product_unit_id` = `pt`.`product_material_unit_id`
            and `pt`.`is_delete` = 0))
    left join `sm_product_rm` `rm` on
        (`rm`.`product_rm_id` = `pt`.`product_material_id`
            and `rm`.`is_delete` = 0))
    left join `sm_product_rm_commercial` `mcom` on
        (`mcom`.`product_rm_id` = `rm`.`product_rm_id`
            and `mcom`.`is_delete` = 0))
    left join `sm_product_rm_technical` `mtech` on
        (`mtech`.`product_rm_id` = `rm`.`product_rm_id`
            and `mtech`.`is_delete` = 0))
    left join `sm_product_category1` `cat1` on
        (`cat1`.`product_category1_id` = `rm`.`product_category1_id`
            and `cat1`.`is_delete` = 0))
    left join `sm_product_category2` `cat2` on
        (`cat2`.`product_category2_id` = `mtech`.`product_category2_id`
            and `cat2`.`is_delete` = 0))
    left join `sm_product_type` `pdt` on
        (`pdt`.`product_type_id` = `pt`.`goods_receipt_type_id`
            and `pdt`.`is_delete` = 0))
    left join `sm_product_packing` `pk` on
        (`pk`.`product_packing_id` = `pt`.`product_material_packing_id`
            and `pk`.`is_delete` = 0))
    left join `cm_hsn_sac` `hsn` on
        (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`
            and `hsn`.`is_delete` = 0))
    left join `cm_employee` `e2` on
        (`e2`.`employee_id` = `grm`.`approved_by_id`
            and `e2`.`is_delete` = 0))
    left join `sm_product_rejection_parameters` `rp` on
        (`rp`.`company_id` = `pt`.`company_id`
            and `rp`.`product_rejection_parameters_id` = `pt`.`product_material_rejection_reason_id`))
    left join `cm_godown` `g` on
        (`g`.`godown_id` = `pt`.`godown_id`))
    left join `cm_godown_section` `gs` on
        (`gs`.`godown_section_id` = `pt`.`godown_section_id`))
    left join `cm_godown_section_beans` `gsb` on
        (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
    left join `pt_purchase_order_details` `po` on
        (`po`.`company_branch_id` = `pt`.`company_branch_id`
            and `po`.`company_id` = `pt`.`company_id`
            and `po`.`product_material_id` = `pt`.`product_material_id`
            and `po`.`purchase_order_no` = `pt`.`purchase_order_no`
            and `po`.`purchase_order_version` = `pt`.`purchase_order_version`
            and `po`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id`))
    left join `fm_cost_center` `fpc` on
        (`fpc`.`cost_center_id` = `po`.`cost_center_id`))
    left join `fm_currency` `fpo` on
        (`fpo`.`currency_id` = `pt`.`currency_id`))
    left join `fm_currency` `fgrn` on
        (`fgrn`.`currency_id` = `pt`.`grn_currency_id`))
    where
        `pt`.`is_delete` = 0;