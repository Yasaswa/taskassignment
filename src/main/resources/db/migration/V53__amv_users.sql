
-- amv_users source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `amv_users` AS
select
    'employee' AS `user_type`,
    `e1`.`employee_code` AS `user_code`,
    `e1`.`employee_id` AS `user_id`,
    `e1`.`employee_name` AS `user`,
    `e1`.`username` AS `username`,
    `e1`.`password` AS `password`,
    `c`.`company_name` AS `company_name`,
    `c`.`company_branch_name` AS `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) AS `company_branch_address`,
    `c`.`branch_short_name` AS `branch_short_name`,
    `e1`.`company_id` AS `company_id`,
    `e1`.`company_branch_id` AS `company_branch_id`,
    `a`.`department_id` AS `department_id`,
    `dp`.`department_name` AS `department_name`
from
    (((`cm_employee` `e1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `e1`.`company_id`
        and `c`.`company_branch_id` = `e1`.`company_branch_id`))
left join `cm_employees_workprofile` `a` on
    (`a`.`employee_id` = `e1`.`employee_id`))
left join `cm_department` `dp` on
    (`dp`.`department_id` = `a`.`department_id`))
where
`e1`.`is_active` = 1 and
    `e1`.`is_delete` = 0
    or `e1`.`username` = 'dakshabhiadmin'
union all
select
    'customer' AS `user_type`,
    `c1`.`customer_code` AS `user_code`,
    `c1`.`customer_id` AS `user_id`,
    `c1`.`customer_name` AS `user`,
    `c1`.`username` AS `username`,
    `c1`.`password` AS `password`,
    `c`.`company_name` AS `company_name`,
    `c`.`company_branch_name` AS `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) AS `company_branch_address`,
    `c`.`branch_short_name` AS `branch_short_name`,
    `c1`.`company_id` AS `company_id`,
    `c1`.`company_branch_id` AS `company_branch_id`,
    '0' AS `department_id`,
    '' AS `department_name`
from
    (`cm_customer` `c1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `c1`.`company_id`
        and `c`.`company_branch_id` = `c1`.`company_branch_id`))
where
    `c1`.`is_delete` = 0
union all
select
    'supplier' AS `user_type`,
    `s1`.`supplier_code` AS `user_code`,
    `s1`.`supplier_id` AS `user_id`,
    `s1`.`supplier_name` AS `user`,
    `s1`.`username` AS `username`,
    `s1`.`password` AS `password`,
    `c`.`company_name` AS `company_name`,
    `c`.`company_branch_name` AS `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) AS `company_branch_address`,
    `c`.`branch_short_name` AS `branch_short_name`,
    `s1`.`company_id` AS `company_id`,
    `s1`.`company_branch_id` AS `company_branch_id`,
    '0' AS `department_id`,
    '' AS `department_name`
from
    (`cm_supplier` `s1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `s1`.`company_id`
        and `c`.`company_branch_id` = `s1`.`company_branch_id`))
where
    `s1`.`is_delete` = 0
union all
select
    'agent' AS `user_type`,
    `a1`.`agent_vendor_code` AS `user_code`,
    `a1`.`agent_id` AS `user_id`,
    `a1`.`agent_name` AS `user`,
    `a1`.`username` AS `username`,
    `a1`.`password` AS `password`,
    `c`.`company_name` AS `company_name`,
    `c`.`company_branch_name` AS `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) AS `company_branch_address`,
    `c`.`branch_short_name` AS `branch_short_name`,
    `a1`.`company_id` AS `company_id`,
    `a1`.`company_branch_id` AS `company_branch_id`,
    '0' AS `department_id`,
    '' AS `department_name`
from
    (`cm_agent` `a1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `a1`.`company_id`
        and `c`.`company_branch_id` = `a1`.`company_branch_id`))
where
    `a1`.`is_delete` = 0
union all
select
    'transporter' AS `user_type`,
    `t1`.`transporter_vendor_code` AS `user_code`,
    `t1`.`transporter_id` AS `user_id`,
    `t1`.`transporter_name` AS `user`,
    `t1`.`username` AS `username`,
    `t1`.`password` AS `password`,
    `c`.`company_name` AS `company_name`,
    `c`.`company_branch_name` AS `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) AS `company_branch_address`,
    `c`.`branch_short_name` AS `branch_short_name`,
    `t1`.`company_id` AS `company_id`,
    `t1`.`company_branch_id` AS `company_branch_id`,
    '0' AS `department_id`,
    '' AS `department_name`
from
    (`cm_transporter` `t1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `t1`.`company_id`
        and `c`.`company_branch_id` = `t1`.`company_branch_id`))
where
    `t1`.`is_delete` = 0;


-- pt_goods_receipt_master
ALTER TABLE pt_goods_receipt_master ADD sales_type varchar(100) DEFAULT NULL NULL;

-- erp_development.ptv_goods_receipt_summary source

create or replace
algorithm = UNDEFINED view `ptv_goods_receipt_summary` as
select
    `sup`.`supplier_name` as `supplier_name`,
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
    `cb`.`company_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`state_name` as `company_branch_state`,
    `cb`.`city_name` as `company_branch_city`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cb`.`branch_address1` as `company_address1`,
    `cb`.`branch_address2` as `company_address2`,
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
    `pt`.`sales_type` as `sales_type`, 
    `sup`.`payment_term_id` as `payment_term_id`
from
    ((((((((`pt_goods_receipt_master` `pt`
left join `cm_company_branch` `v` on
    (`v`.`company_branch_id` = `pt`.`company_branch_id`
        and `v`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join `cmv_supplier` `sup` on
    (`sup`.`supplier_id` = `pt`.`supplier_id`
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
left join `cmv_company_branch_summary` `cb` on
    (`cb`.`company_branch_id` = `pt`.`expected_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`))
where
    `pt`.`is_delete` = 0;

    
