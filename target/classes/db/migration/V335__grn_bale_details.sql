ALTER TABLE pt_grn_cotton_bales_details ADD product_category1_id bigint(20) DEFAULT 0 NULL;
ALTER TABLE pt_grn_cotton_bales_details ADD product_category2_id bigint(20) DEFAULT 0 NULL;


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
        else 'GRN'
    end as `goods_receipt_status_desc`,
    `sup`.`supp_branch_name` as `supp_branch_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
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
group by `pt`.`grn_cotton_bales_master_transaction_id`;



-- erp_development.ptv_grn_cotton_bales_summary_rpt source

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
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`,
    concat(ifnull(`v`.`grn_cotton_bales_master_transaction_id`, ''), ':Goods Receipt Master Transaction Id:O:N:') as `grn_cotton_bales_master_transaction_id`
from
    `ptv_grn_cotton_bales_summary` `v`
limit 1;