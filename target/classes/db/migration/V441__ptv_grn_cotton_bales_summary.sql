
-- erp_new.ptv_grn_cotton_bales_details source

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
    ifnull(`pmc`.`issue_flag`, 0) as `issue_flag`,
    `pt`.`supplier_batch_no` as `supplier_batch_no`
from
    (((((((((((((((((((((((((`pt_grn_cotton_bales_details` `pt`
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
left join (
    select
        `pt_mixing_chart_cotton_bales`.`batch_no` as `batch_no`,
        case
            when max(ifnull(`pt_mixing_chart_cotton_bales`.`issue_flag`, 0)) = 0 then 0
            else 1
        end as `issue_flag`
    from
        `pt_mixing_chart_cotton_bales`
    where
        `pt_mixing_chart_cotton_bales`.`is_delete` = 0
    group by
        `pt_mixing_chart_cotton_bales`.`batch_no`) `pmc` on
    (`pmc`.`batch_no` = `pt`.`batch_no`))
where
    `pt`.`is_delete` = 0;



-- erp_new.ptv_grn_cotton_bales_summary source

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
    ifnull(`pmc`.`issue_flag`, 0) as `issue_flag`,
    `p`.`product_type_short_name` as `product_type_short_name`
from
    (((((((((((`pt_grn_cotton_bales_master` `pt`
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
left join (
    select
        `ptv_grn_cotton_bales_details`.`goods_receipt_no` as `goods_receipt_no`,
        case
            when max(ifnull(`ptv_grn_cotton_bales_details`.`issue_flag`, 0)) = 0 then 0
            else 1
        end as `issue_flag`
    from
        `ptv_grn_cotton_bales_details`
    where
        `ptv_grn_cotton_bales_details`.`is_delete` = 0
    group by
        `ptv_grn_cotton_bales_details`.`goods_receipt_no`) `pmc` on
    (`pmc`.`goods_receipt_no` = `pt`.`goods_receipt_no`))
where
    `pt`.`is_delete` = 0
group by
    `pt`.`grn_cotton_bales_master_transaction_id`;




-- erp_new.ptv_grn_cotton_bales_details_rpt source

create or replace
algorithm = UNDEFINED view `ptv_grn_cotton_bales_details_rpt` as
select
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T') as `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_date`, ''), ':Goods Receipt Date:Y:D:') as `goods_receipt_date`,
    concat(ifnull(`v`.`goods_receipt_status`, ''), ':Grn Item Status:Y:H:(GRN,Done,Rejected,Partial Receipt,Completed,Canceled,Pree Closed)') as `goods_receipt_status`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`batch_no`, ''), ':Batch No:Y:T') as `batch_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') as `product_material_name`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:C:ptv_goods_receipt_summary:O') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Supplier Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
    concat(ifnull(`v`.`supplier_city_name`, ''), ':Supplier City Name:O:N:') as `supplier_city_name`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:Y:C:cmv_state:F') as `supplier_state_name`,
    concat(ifnull(`v`.`item_qa_by_id`, ''), ':Item QA By Id:O:N:') as `item_qa_by_id`,
    concat(ifnull(`v`.`item_qa_date`, ''), ':Item QA Date:Y:D:') as `item_qa_date`,
    concat(ifnull(`v`.`product_material_std_weight`, ''), ':Material Std Weight:O:N:') as `product_material_std_weight`,
    concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') as `product_material_stock_unit_name`,
    concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') as `product_material_po_approved_quantity`,
    concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') as `product_material_po_approved_weight`,
    concat(ifnull(`v`.`product_material_grn_quantity`, ''), ':Material GRN Quantity:O:N:') as `product_material_grn_quantity`,
    concat(ifnull(`v`.`product_material_grn_weight`, ''), ':Material GRN Weight:O:N:') as `product_material_grn_weight`,
    concat(ifnull(`v`.`material_rate`, ''), ':GRN Material Rate:O:N:') as `material_rate`,
    concat(ifnull(`v`.`grn_currency_name`, ''), ':GRN Currency Name:O:N:') as `grn_currency_name`,
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
    concat(ifnull(`v`.`product_category1_name`, ''), ':Material Category1 Name:O:N:') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Material Category2 Name:O:N:') as `product_category2_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Material Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':Grn Item Status:O:N:') as `grn_item_status`,
    concat(ifnull(`v`.`press_running_no_from`, ''), ':Press No Running From:Y:T:') as `press_running_no_from`,
    concat(ifnull(`v`.`press_running_no_to`, ''), ':Press No Running To:Y:T:') as `press_running_no_to`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
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
    concat(ifnull(`v`.`grn_cotton_bales_details_transaction_id`, ''), 'grn_cotton_bales_details_transaction_id:N:N:') as `grn_cotton_bales_details_transaction_id`,
    concat(ifnull(`v`.`grn_cotton_bales_master_transaction_id`, ''), 'grn_cotton_bales_master_transaction_id:N:N:') as `grn_cotton_bales_master_transaction_id`,
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
    concat(ifnull(`v`.`issue_flag`, ''), ':Issue Flag:O:N:') as `issue_flag`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`
from
    `ptv_grn_cotton_bales_details` `v`
limit 1;




-- erp_new.ptv_grn_cotton_bales_summary_rpt source

create or replace
algorithm = UNDEFINED view `ptv_grn_cotton_bales_summary_rpt` as
select
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T') as `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_date`, ''), ':Goods Receipt Date:Y:D:') as `goods_receipt_date`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:C:ptv_grn_cotton_bales_summary:O') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Supplier Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') as `is_freight_taxable`,
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
    concat(ifnull(`v`.`qa_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `qa_by_name`,
    concat(ifnull(`v`.`qa_date`, ''), ':QA Date:Y:D:') as `qa_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`lr_no`, ''), ':LR No:O:N:') as `lr_no`,
    concat(ifnull(`v`.`lr_date`, ''), ':LR Date:Y:D:') as `lr_date`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') as `agent_percent`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:O:N:') as `supplier_state_name`,
    concat(ifnull(`v`.`supplier_city_name`, ''), ':Supplier City Name:O:N:') as `supplier_city_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`goods_receipt_status`, ''), ':Goods Receipt Status:O:N:') as `goods_receipt_status`,
    concat(ifnull(`v`.`is_preeclosed`, ''), ':Pre closed:N:N:') as `is_preeclosed`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`,
    concat(ifnull(`v`.`issue_flag`, ''), ':Issue Flag:O:N:') as `issue_flag`,
    concat(ifnull(`v`.`grn_cotton_bales_master_transaction_id`, ''), ':Goods Receipt Master Transaction Id:O:N:') as `grn_cotton_bales_master_transaction_id`
from
    `ptv_grn_cotton_bales_summary` `v`
limit 1;