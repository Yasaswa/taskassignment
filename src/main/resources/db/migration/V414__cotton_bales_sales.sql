ALTER TABLE pt_cotton_bales_sales_master MODIFY COLUMN customer_address varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT ' * will used from  Header part data';
ALTER TABLE pt_cotton_bales_sales_master MODIFY COLUMN customer_cell_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '* will used from  Header part data ';
ALTER TABLE pt_cotton_bales_sales_master MODIFY COLUMN customer_gst_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '* will used from  Header part data ';
ALTER TABLE pt_cotton_bales_sales_details ADD product_rm_id VARCHAR(20);


ALTER TABLE pt_grn_cotton_bales_master MODIFY COLUMN goods_receipt_status varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'P' NULL COMMENT '  2/7  footer part read only combo G-GRN Done, Q-QC Done, R-Rejected,I-Partial Receipt, C-Completed, X-Canceled  will changes according to trnasactions  done ';
ALTER TABLE pt_grn_cotton_bales_details MODIFY COLUMN grn_item_status varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'G' NULL COMMENT 'combo G-GRN Done, Q-QC Done, R-Rejected,I-Partial Receipt, C-Completed, X-Canceled Z-PreeClosed  will changes according to trnasactions  done  ';


create or replace
algorithm = UNDEFINED view `ptv_cotton_bales_sales_details` as
select
    `pcbsd`.`pt_cotton_bales_sales_details_transaction_id` as `pt_cotton_bales_sales_details_transaction_id`,
    `pcbsd`.`pt_cotton_bales_sales_master_transaction_id` as `pt_cotton_bales_sales_master_transaction_id`,
    `pcbsd`.`company_id` as `company_id`,
    `pcbsd`.`financial_year` as `financial_year`,
    `pcbsd`.`company_branch_id` as `company_branch_id`,
    `pcbsd`.`sales_hsnTax_type` as `sales_hsnTax_type`,
    `pcbsd`.`sales_voucher_type` as `sales_voucher_type`,
    `pcbsm`.`sales_return_type_id` as `sales_return_type_id`,
    `pcbsm`.`sales_return_type` as `sales_return_type`,
    `pcbsd`.`sales_return_date` as `sales_return_date`,
    `pcbsd`.`sales_return_no` as `sales_return_no`,
    `pcbsd`.`customer_id` as `customer_id`,
    `pcbsd`.`consignee_id` as `consignee_id`,
    `pcbsd`.`return_by_id` as `return_by_id`,
    `pcbsd`.`sales_return_status` as `sales_return_status`,
    `pcbsd`.`approved_by_id` as `approved_by_id`,
    `pcbsd`.`approved_date` as `approved_date`,
    `pcbsd`.`goods_receipt_no` as `goods_receipt_no`,
    `pcbsd`.`purchase_order_no` as `purchase_order_no`,
    `pcbsd`.`product_material_code` as `product_material_code`,
    `pcbsd`.`product_material_name` as `product_material_name`,
    `pcbsd`.`product_rm_id` as `product_rm_id`,
    `pcbsd`.`batch_no` as `batch_no`,
    `pcbsd`.`supplier_batch_no` as `supplier_batch_no`,
    `pcbsd`.`stock_quantity` as `stock_quantity`,
    `pcbsd`.`stock_weight` as `stock_weight`,
    `pcbsd`.`sales_return_quantity` as `sales_return_quantity`,
    `pcbsd`.`sales_return_weight` as `sales_return_weight`,
    `pcbsd`.`sales_return_rate` as `sales_return_rate`,
    `pcbsd`.`product_purchase_unit_id` as `product_purchase_unit_id`,
    `pcbsd`.`material_discount_percent` as `material_discount_percent`,
    `pcbsd`.`material_discount_amount` as `material_discount_amount`,
    `pcbsd`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pcbsd`.`product_hsn_sac_rate` as `product_hsn_sac_rate`,
    `pcbsd`.`material_taxable_amount` as `material_taxable_amount`,
    `pcbsd`.`material_basic_amount` as `material_basic_amount`,
    `pcbsd`.`material_cgst_percent` as `material_cgst_percent`,
    `pcbsd`.`material_cgst_total` as `material_cgst_total`,
    `pcbsd`.`material_sgst_percent` as `material_sgst_percent`,
    `pcbsd`.`material_sgst_total` as `material_sgst_total`,
    `pcbsd`.`material_igst_percent` as `material_igst_percent`,
    `pcbsd`.`material_igst_total` as `material_igst_total`,
    `pcbsd`.`material_freight_amount` as `material_freight_amount`,
    `pcbsd`.`material_total_amount` as `material_total_amount`,
    `pcbsd`.`sales_return_remark` as `sales_return_remark`,
    `pcbsd`.`godown_id` as `godown_id`,
    `pcbsd`.`press_running_no_from` as `press_running_no_from`,
    `pcbsd`.`press_running_no_to` as `press_running_no_to`,
    `cust`.`customer_name` as `customer_name`,
    `e`.`employee_name` as `approved_by_name`,
    `e1`.`employee_name` as `return_by_name`,
    `gs`.`godown_name` as `godown_name`,
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
        `pcbsd`.`sales_return_status` when 'A' then 'Returned'
        else 'Pending'
    end as `sales_return_status_desc`,
    case
        when `pcbsd`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pcbsd`.`is_delete` as `is_delete`,
    `pcbsd`.`created_by` as `created_by`,
    `pcbsd`.`created_on` as `created_on`,
    `pcbsd`.`modified_by` as `modified_by`,
    `pcbsd`.`modified_on` as `modified_on`,
    `pcbsd`.`deleted_by` as `deleted_by`,
    `pcbsd`.`deleted_on` as `deleted_on`
from
    ((((((`pt_cotton_bales_sales_details` `pcbsd`
left join `pt_cotton_bales_sales_master` `pcbsm` on
    (`pcbsd`.`pt_cotton_bales_sales_master_transaction_id` = `pcbsm`.`pt_cotton_bales_sales_master_transaction_id`
        and `pcbsd`.`company_id` = `pcbsm`.`company_id`
        and `pcbsd`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `pcbsd`.`approved_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_active` = 1
        and `e1`.`is_delete` = 0
        and `e1`.`employee_id` = `pcbsd`.`return_by_id`))
left join `cm_customer` `cust` on
    (`cust`.`customer_id` = `pcbsd`.`customer_id`
        and `cust`.`is_delete` = 0))

left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pcbsd`.`godown_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pcbsd`.`company_branch_id`
        and `vb`.`company_id` = `pcbsd`.`company_id`))
where
    `pcbsd`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `ptv_grn_cotton_bales_summary` as
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
        when 'RE' then 'Returned'
        else 'GRN'
    end as `goods_receipt_status_desc`,
    `sup`.`supp_branch_name` as `supp_branch_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`batch_no` as `batch_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `pt`.`supplier_id` as `supplier_id`,
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
    `pt`.`is_delete` = 0
group by
    `pt`.`grn_cotton_bales_master_transaction_id`;



create or replace
algorithm = UNDEFINED view `ptv_grn_cotton_bales_details` as
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
        when 'RE' then 'Returned'
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
    `pt`.`product_purchase_unit_id` as `product_purchase_unit_id`,
    `pt`.`product_material_grn_rejected_quantity` as `product_material_grn_rejected_quantity`,
    `pt`.`product_material_grn_rejected_weight` as `product_material_grn_rejected_weight`,
    `rp`.`product_rejection_type` as `product_rejection_type`,
    `rp`.`product_rejection_parameters_name` as `product_rejection_parameters_name`,
    `rp`.`product_rejection_parameters_short_name` as `product_rejection_parameters_short_name`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_grn_cotton_bales_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_bales_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_grn_cotton_bales_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_bales_weight`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_prev_accepted_weight`,
    `pt`.`po_material_rate` as `po_material_rate`,
    `pt`.`material_rate` as `material_rate`,
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
    `pt`.`grn_currency_id` as `grn_currency_id`,
    `fgrn`.`currency_name` as `grn_currency_name`,
    `fgrn`.`currency_code` as `grn_currency_code`,
    `fgrn`.`currency_symbol` as `grn_currency_symbol`,
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
    `grm`.`supplier_id` as `supplier_id`,
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
    `fpo`.`currency_name` as `currency_name`,
    `fpo`.`currency_code` as `currency_code`,
    `pt`.`invoice_gross_weight` as `invoice_gross_weight`,
    `pt`.`invoice_tare_weight` as `invoice_tare_weight`,
    `pt`.`invoice_net_weight` as `invoice_net_weight`,
    `pt`.`difference_weight` as `difference_weight`,
    `pt`.`mill_gross_weight` as `mill_gross_weight`,
    `pt`.`mill_tare_weight` as `mill_tare_weight`,
    `pt`.`mill_net_weight` as `mill_net_weight`,
    `pt`.`press_running_no_from` as `press_running_no_from`,
    `pt`.`press_running_remark` as `press_running_remark`,
    `pt`.`press_running_no_to` as `press_running_no_to`,
    `pt`.`supplier_batch_no` as `supplier_batch_no`
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