-- ptv_goods_receipt_summary source

create or REPLACE algorithm = UNDEFINED view`ptv_goods_receipt_summary` as
select
    `sup`.`supp_branch_name` as `supplier_name`,
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
        when 'B' then 'Bill Booked'
        else 'GRN'
    end as `goods_receipt_status_desc`,
    case
        `pt`.`purchase_order_life` when 'C' then 'Close'
        else 'Open'
    end as `purchase_order_life_desc`,
    (
    select
        distinct `po1`.`indent_no`
    from
       `pt_purchase_order_details` `po1`
    where
        `po1`.`purchase_order_no` in (
        select
            distinct `pd1`.`purchase_order_no`
        from
           `pt_goods_receipt_details` `pd1`
        where
            `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`)) as `indent_no`,
    (
    select
        distinct `po1`.`indented_by_id`
    from
       `pt_purchase_order_details` `po1`
    where
        `po1`.`purchase_order_no` in (
        select
            distinct `pd1`.`purchase_order_no`
        from
           `pt_goods_receipt_details` `pd1`
        where
            `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`)) as `indent_by_id`,
    (
    select
        distinct `pd1`.`purchase_order_no`
    from
       `pt_goods_receipt_details` `pd1`
    where
        `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
    limit 1) as `purchase_order_no`,
    (
    select
        distinct `pd1`.`purchase_order_date`
    from
       `pt_goods_receipt_details` `pd1`
    where
        `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
    limit 1) as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `pt`.`goods_receipt_type` as `goods_receipt_type`,
    `pt`.`supplier_invoice_no` as `supplier_invoice_no`,
    `pt`.`supplier_invoice_date` as `supplier_invoice_date`,
    `pt`.`supplier_challan_no` as `supplier_challan_no`,
    `pt`.`supplier_challan_date` as `supplier_challan_date`,
    `pt`.`expected_schedule_date` as `expected_schedule_date`,
    `a`.`agent_name` as `agent_name`,
    `pt`.`agent_percent` as `agent_percent`,
    `pt`.`agent_paid_status` as `agent_paid_status`,
    `e1`.`employee_name` as `qa_by_name`,
    `e`.`employee_name` as `approved_by_name`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`qa_date` as `qa_date`,
    `pt`.`basic_total` as `basic_total`,
    `pt`.`transport_amount` as `transport_amount`,
    `pt`.`freight_amount` as `freight_amount`,
    `pt`.`is_freight_taxable` as `is_freight_taxable`,
    `pt`.`packing_amount` as `packing_amount`,
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
    `pt`.`other_terms_conditions` as `other_terms_conditions`,
    `sup`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `sup`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `sup`.`supp_branch_address1` as `supp_branch_address1`,
    `sup`.`supp_branch_pincode` as `supp_branch_pincode`,
    `sup`.`supp_branch_gst_no` as `supp_branch_gst_no`,
    `sup`.`supp_branch_pan_no` as `supp_branch_pan_no`,
    `pt`.`ev_bill_no` as `ev_bill_no`,
    `pt`.`ev_bill_date` as `ev_bill_date`,
    `pt`.`remark` as `remark`,
    `cb`.`is_sez` as `is_sez`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `ct`.`city_name` as `supplier_city_name`,
    `s`.`state_name` as `supplier_state_name`,
    `pt`.`financial_year` as `financial_year`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`is_preeclosed` as `is_preeclosed`,
    `pt`.`purchase_order_life` as `purchase_order_life`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`goods_receipt_master_transaction_id` as `goods_receipt_master_transaction_id`,
    `pt`.`qa_by_id` as `qa_by_id`,
    `pt`.`agent_id` as `agent_id`,
    `pt`.`supplier_id` as `supplier_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    `pt`.`supplier_state_id` as `supplier_state_id`,
    `pt`.`supplier_city_id` as `supplier_city_id`,
    `pt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pt`.`expected_branch_id` as `expected_branch_id`,
    `pt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
    `pt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `sup`.`supp_branch_payment_term_id` as `payment_term_id`
from
    (((((((( `pt_goods_receipt_master` `pt`
left join`cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `c`.`is_delete` = 0))
left join`cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `pt`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join`cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))
left join`cm_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`qa_by_id`))
left join`cm_city` `ct` on
    (`ct`.`city_id` = `pt`.`supplier_city_id`))
left join`cm_state` `s` on
    (`s`.`state_id` = `pt`.`supplier_state_id`))
left join`cm_agent` `a` on
    (`a`.`agent_id` = `pt`.`agent_id`
        and `a`.`company_id` = `pt`.`company_id`))
left join`cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`expected_branch_id`
        and `cb`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;


-- ptv_goods_receipt_details source

create or REPLACE algorithm = UNDEFINED view`ptv_goods_receipt_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `pt`.`goods_receipt_date` as `goods_receipt_date`,
    `pt`.`goods_receipt_version` as `goods_receipt_version`,
    case
        `pt`.`grn_item_status` when 'G' then 'GRN'
        when 'Q' then 'QC Done'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'GRN'
    end as `grn_item_status_desc`,
    `d`.`department_name` as `department_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    case
        `po`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end as `purchase_order_item_status_desc`,
    `cu`.`customer_name` as `customer_name`,
    `pt`.`customer_order_no` as `customer_order_no`,
    `pt`.`customer_order_Date` as `customer_order_Date`,
    `grm`.`purchase_order_life` as `purchase_order_life`,
    `grm`.`supplier_challan_no` as `supplier_challan_no`,
    `grm`.`supplier_challan_date` as `supplier_challan_date`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `ct`.`city_name` as `supplier_city_name`,
    `ss`.`state_name` as `supplier_state_name`,
    `pt`.`item_qa_by_id` as `item_qa_by_id`,
    `pt`.`item_qa_date` as `item_qa_date`,
    `pt`.`goods_receipt_type` as `goods_receipt_type`,
    `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `pt`.`lead_time` as `lead_time`,
    `pt`.`sr_no` as `sr_no`,
    `pt`.`batch_no` as `batch_no`,
    `po`.`purchase_order_item_status` as `purchase_order_item_status`,
    `rm_fg`.`product_material_name` as `product_material_name`,
    `u`.`product_unit_name` as `product_material_stock_unit_name`,
    `rm_fg`.`product_material_std_weight` as `product_material_std_weight`,
    `rm_fg`.`product_material_category1_name` as `product_category1_name`,
    `rm_fg`.`product_material_category2_name` as `product_category2_name`,
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
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from`pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from`pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_weight`,
    `so`.`sales_order_no` as `sales_order_no`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_prev_accepted_weight`,
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
    `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `pt`.`purchase_return_weight` as `purchase_return_weight`,
    `pt`.`production_issue_quantity` as `production_issue_quantity`,
    `pt`.`production_issue_weight` as `production_issue_weight`,
    `pt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `pt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `pt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `pt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `pt`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `pt`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `pt`.`sales_quantity` as `sales_quantity`,
    `pt`.`sales_weight` as `sales_weight`,
    `pt`.`sales_return_quantity` as `sales_return_quantity`,
    `pt`.`sales_return_weight` as `sales_return_weight`,
    `pt`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `pt`.`sales_rejection_weight` as `sales_rejection_weight`,
    `pt`.`transfer_issue_weight` as `transfer_issue_weight`,
    `pt`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `pt`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `pt`.`outsources_out_quantity` as `outsources_out_quantity`,
    `pt`.`outsources_out_weight` as `outsources_out_weight`,
    `pt`.`outsources_in_quantity` as `outsources_in_quantity`,
    `pt`.`outsources_in_weight` as `outsources_in_weight`,
    `pt`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `pt`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `pt`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `pt`.`loan_receipt_weight` as `loan_receipt_weight`,
    `pt`.`loan_issue_quantity` as `loan_issue_quantity`,
    `pt`.`loan_issue_weight` as `loan_issue_weight`,
    `pt`.`cancel_quantity` as `cancel_quantity`,
    `pt`.`cancel_weight` as `cancel_weight`,
    `pt`.`difference_quantity` as `difference_quantity`,
    `pt`.`difference_weight` as `difference_weight`,
    `pt`.`total_box_weight` as `total_box_weight`,
    `pt`.`total_quantity_in_box` as `total_quantity_in_box`,
    `pt`.`weight_per_box_item` as `weight_per_box_item`,
    `pt`.`remark` as `remark`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`expiry_date` as `expiry_date`,
    `po`.`indent_no` as `indent_no`,
    `e1`.`employee_name` as `indented_by_name`,
    `e2`.`employee_name` as `approved_by_name`,
    `g`.`godown_name` as `godown_name`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `pdt`.`product_type_group` as `product_material_type_group`,
    `pdt`.`product_type_name` as `product_type_name`,
    `pdt`.`product_type_short_name` as `product_type_short_name`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`goods_receipt_details_transaction_id` as `goods_receipt_details_transaction_id`,
    `pt`.`goods_receipt_master_transaction_id` as `goods_receipt_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
    `pt`.`department_id` as `department_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`product_material_rejection_reason_id` as `product_material_rejection_reason_id`,
    `grm`.`goods_receipt_status` as `goods_receipt_status`,
    `grm`.`supplier_id` as `supplier_id`,
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
    `po`.`purchase_order_type_id` as `product_type_id`,
    `rm_fg`.`product_material_category1_id` as `product_category1_id`,
    `rm_fg`.`product_material_category2_id` as `product_category2_id`,
    `po`.`indented_by_id` as `indent_by_id`
from
    ((((((((((((((((((((( `pt_goods_receipt_details` `pt`
left join`cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join`cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join`pt_goods_receipt_master` `grm` on
    (`grm`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
        and `grm`.`company_id` = `pt`.`company_id`
        and `grm`.`company_branch_id` = `pt`.`company_branch_id`))
left join`cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join`cm_city` `ct` on
    (`ct`.`city_id` = `grm`.`supplier_city_id`))
left join`cm_state` `ss` on
    (`ss`.`state_id` = `grm`.`supplier_state_id`))
left join`pt_purchase_order_details` `po` on
    (`po`.`company_branch_id` = `pt`.`company_branch_id`
        and `po`.`company_id` = `pt`.`company_id`
        and `po`.`product_material_id` = `pt`.`product_material_id`
        and `po`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `po`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `po`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id`))
left join`sm_product_unit` `u` on
    (`u`.`product_unit_id` = `pt`.`product_material_unit_id`
        and `pt`.`is_delete` = 0))
left join`smv_product_rm_fg_sr` `rm_fg` on
    (`rm_fg`.`product_material_id` = `pt`.`product_material_id`))
left join`sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `po`.`purchase_order_type_id`
        and `pdt`.`is_delete` = 0))
left join`sm_product_packing` `pk` on
    (`pk`.`product_packing_id` = `pt`.`product_material_packing_id`
        and `pk`.`is_delete` = 0))
left join`cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`
        and `hsn`.`is_delete` = 0))
left join`cm_employee` `e1` on
    (`e1`.`employee_id` = `po`.`indented_by_id`
        and `e1`.`is_delete` = 0))
left join`cm_employee` `e2` on
    (`e2`.`employee_id` = `grm`.`approved_by_id`
        and `e2`.`is_delete` = 0))
left join`cm_customer` `cu` on
    (`cu`.`customer_id` = `po`.`customer_id`
        and `cu`.`is_delete` = 0))
left join`cm_department` `d` on
    (`d`.`department_id` = `pt`.`department_id`))
left join`sm_product_rejection_parameters` `rp` on
    (`rp`.`company_id` = `pt`.`company_id`
        and `rp`.`product_rejection_parameters_id` = `pt`.`product_material_rejection_reason_id`))
left join`mt_sales_order_master_trading` `so` on
    (`so`.`company_id` = `pt`.`company_id`
        and `so`.`customer_order_no` = `pt`.`customer_order_no`))
left join`cm_godown` `g` on
    (`g`.`godown_id` = `pt`.`godown_id`))
left join`cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `pt`.`godown_section_id`))
left join`cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
where
    `pt`.`is_delete` = 0;


-- ptv_goods_receipt_summary_rpt source

create or REPLACE algorithm = UNDEFINED view`ptv_goods_receipt_summary_rpt` as
select
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T') as `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_date`, ''), ':Goods Receipt Date:Y:D:') as `goods_receipt_date`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt version:O:N:') as `goods_receipt_version`,
    concat(ifnull(`v`.`goods_receipt_status`, ''), ':Goods Receipt Status:Y:H:(GRN Done,QC Done,Rejected,Partial Receipt,Completed,Canceled,GRN)') as `goods_receipt_status_desc`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier_summary:F') as `supplier_name`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:C:ptv_goods_receipt_summary:O') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Supplier Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') as `is_freight_taxable`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') as `packing_amount`,
    concat(ifnull(`v`.`goods_receipt_discount_percent`, ''), ':Discount Percent:O:N:') as `goods_receipt_discount_percent`,
    concat(ifnull(`v`.`goods_receipt_discount_amount`, ''), ':Discount Amount:O:N:') as `goods_receipt_discount_amount`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') as `other_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') as `taxable_total`,
    concat(ifnull(`v`.`cgst_total`, ''), ':Cgst Total:O:N:') as `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':Sgst Total:O:N:') as `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':Igst Total:O:N:') as `igst_total`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') as `grand_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':Roundoff:O:N:') as `roundoff`,
    concat(ifnull(`v`.`supplier_invoice_no`, ''), ':Supplier Invoice No:O:N:') as `supplier_invoice_no`,
    concat(ifnull(`v`.`supplier_invoice_date`, ''), ':Supplier Invoice Date:Y:D:') as `supplier_invoice_Date`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(ifnull(`v`.`qa_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `qa_by_name`,
    concat(ifnull(`v`.`qa_date`, ''), ':QA Date:Y:D:') as `qa_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`lr_no`, ''), ':LR No:O:N:') as `lr_no`,
    concat(ifnull(`v`.`lr_date`, ''), ':LR Date:Y:D:') as `lr_date`,
    concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:O:N:') as `vehicle_no`,
    concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') as `agent_percent`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:O:N:') as `supplier_state_name`,
    concat(ifnull(`v`.`supplier_city_name`, ''), ':Supplier City Name:O:N:') as `supplier_city_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:O:N:') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:O:N:') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`v`.`purchase_order_life_desc`, ''), ':Purchase Order Life:Y:H:(Close,Open)') as `purchase_order_life_desc`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`goods_receipt_status`, ''), ':Goods Receipt Status:O:N:') as `goods_receipt_status`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`goods_receipt_master_transaction_id`, ''), ':Goods Receipt Master Transaction Id:O:N:') as `goods_receipt_master_transaction_id`,
    concat(ifnull(`v`.`indent_by_id`, ''), ':Indent By Id:N:N:') as `indent_by_id`
from
   `ptv_goods_receipt_summary` `v`
limit 1;


-- ptv_goods_receipt_details_rpt source

create or REPLACE algorithm = UNDEFINED view`ptv_goods_receipt_details_rpt` as
select
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T') as `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_date`, ''), ':Goods Receipt Date:Y:D:') as `goods_receipt_date`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':Grn Item Status:Y:H:(GRN,Done,Rejected,Partial Receipt,Completed,Canceled,Pree Closed)') as `grn_item_status_desc`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending,Pree Closed)') as `purchase_order_item_status_desc`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:C:ptv_goods_receipt_summary:O') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Supplier Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') as `product_material_name`,
    concat(ifnull(`v`.`product_material_std_weight`, ''), ':Material Std Weight:O:N:') as `product_material_std_weight`,
    concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') as `product_material_stock_unit_name`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
    concat(ifnull(`v`.`supplier_city_name`, ''), ':Supplier City Name:O:N:') as `supplier_city_name`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:Y:C:cmv_state:F') as `supplier_state_name`,
    concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') as `product_material_po_approved_quantity`,
    concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') as `product_material_po_approved_weight`,
    concat(ifnull(`v`.`product_material_grn_quantity`, ''), ':Material GRN Quantity:O:N:') as `product_material_grn_quantity`,
    concat(ifnull(`v`.`product_material_grn_weight`, ''), ':Material GRN Weight:O:N:') as `product_material_grn_weight`,
    concat(ifnull(`v`.`product_material_grn_accepted_quantity`, ''), ':Material GRN Accepted Quantity:O:N:') as `product_material_grn_accepted_quantity`,
    concat(ifnull(`v`.`product_material_grn_accepted_weight`, ''), ':Material GRN Accepted Weight:O:N:') as `product_material_grn_accepted_weight`,
    concat(ifnull(`v`.`material_rate`, ''), ':Material Rate:O:N:') as `material_rate`,
    concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') as `material_basic_amount`,
    concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') as `material_discount_percent`,
    concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') as `material_discount_amount`,
    concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
    concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') as `material_cgst_total`,
    concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
    concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') as `material_sgst_total`,
    concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') as `material_igst_percent`,
    concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') as `material_igst_total`,
    concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') as `material_total_amount`,
    concat(ifnull(`v`.`item_qa_by_id`, ''), ':Item QA By Id:O:N:') as `item_qa_by_id`,
    concat(ifnull(`v`.`item_qa_date`, ''), ':Item QA Date:Y:D:') as `item_qa_date`,
    concat(ifnull(`v`.`product_material_tech_spect`, ''), ':Product Material Tech Spect:O:N:') as `product_material_tech_spect`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:O') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:O:N:') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_Date`, ''), ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(ifnull(`v`.`material_schedule_date`, ''), ':Material Schedule Date:Y:D:') as `material_schedule_date`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Packing Name:O:N:') as `product_packing_name`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Material Category1 Name:O:N:') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Material Category2 Name:O:N:') as `product_category2_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Material Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`prev_grn_quantity`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_quantity`,
    concat(ifnull(`v`.`prev_grn_weight`, ''), ':Prev GRN Weight:O:N:') as `prev_grn_weight`,
    concat(ifnull(`v`.`product_material_prev_accepted_quantity`, ''), ':Material Prev Accepted Quantity:O:N:') as `product_material_prev_accepted_quantity`,
    concat(ifnull(`v`.`product_material_prev_accepted_weight`, ''), ':Material Prev Accepted Weight:O:N:') as `product_material_prev_accepted_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
    concat(ifnull(`v`.`pree_closed_grn_quantity`, ''), ':Pree Closed Grn Quantity:O:N:') as `pree_closed_grn_quantity`,
    concat(ifnull(`v`.`pree_closed_grn_weight`, ''), ':Pree Closed Grn Weight:O:N:') as `pree_closed_grn_weight`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue weight:O:N:') as `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
    concat(ifnull(`v`.`purchase_order_life`, ''), ':Purchase Order Life:O:N:') as `purchase_order_life`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') as `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') as `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') as `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') as `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') as `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') as `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') as `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') as `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') as `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') as `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') as `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') as `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') as `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') as `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') as `cancel_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
    concat(ifnull(`v`.`expiry_date`, ''), ':Expiry Date:Y:D:') as `expiry_date`,
    concat(ifnull(`v`.`remark`, ''), ':remark:O:N:') as `remark`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':Grn Item Status:O:N:') as `grn_item_status`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:O:N:') as `purchase_order_item_status`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`goods_receipt_details_transaction_id`, ''), 'goods_receipt_details_transaction_id:N:N:') as `goods_receipt_details_transaction_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing_id:N:N:') as `product_material_packing_id`,
    concat(ifnull(`v`.`product_material_hsn_code_id`, ''), ':Product Material Hsn Code_id:N:N:') as `product_material_hsn_code_id`,
    concat(ifnull(`v`.`goods_receipt_type_id`, ''), ':Goods Receipt Type Id:N:N:') as `goods_receipt_type_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By:N:N:') as `approved_by_id`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
    concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') as `supplier_contacts_ids`,
    concat(ifnull(`v`.`expected_branch_id`, ''), ':Expected Branch Id:N:N:') as `expected_branch_id`,
    concat(ifnull(`v`.`expected_branch_state_id`, ''), ':Expected Branch State Id:N:N:') as `expected_branch_state_id`,
    concat(ifnull(`v`.`expected_branch_city_id`, ''), ':Expected Branch City Id:N:N:') as `expected_branch_city_id`,
    concat(ifnull(`v`.`payment_term_id`, ''), ':Payment Term Id:N:N:') as `payment_term_id`,
    concat(ifnull(`v`.`agent_id`, ''), ':Agent Id:N:N:') as `agent_id`,
    concat(ifnull(`v`.`product_category1_id`, ''), ':Product Category1 Id:N:N:') as `product_category1_id`,
    concat(ifnull(`v`.`product_category2_id`, ''), ':Product Category2 Id:N:N:') as `product_category2_id`,
    concat(ifnull(`v`.`indent_by_id`, ''), ':Indent By Id:N:N:') as `indent_by_id`
from
   `ptv_goods_receipt_details` `v`
limit 1;