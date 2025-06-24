ALTER TABLE mt_dispatch_schedule_details_trading ADD agent_id bigint(20) NULL;
ALTER TABLE mt_dispatch_schedule_details_trading ADD consignee_id bigint(20) NULL;
ALTER TABLE mt_dispatch_schedule_details_trading ADD product_material_code varchar(100) NULL;



-- erp_dev.mtv_dispatch_schedule_details_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_schedule_details_trading` as
select
    `mt`.`dispatch_schedule_type` as `dispatch_schedule_type`,
    case
        `mt`.`dispatch_schedule_creation_type` when 'M' then 'Manual'
        when 'S' then 'Sales Order Based'
    end as `dispatch_schedule_creation_type_desc`,
    `mt`.`dispatch_schedule_no` as `dispatch_schedule_no`,
    `mt`.`dispatch_schedule_date` as `dispatch_schedule_date`,
    `mt`.`dispatch_schedule_version` as `dispatch_schedule_version`,
    case
        `mt`.`dispatch_schedule_item_status` when 'P' then 'Pending'
        when 'A' then 'Aprroved'
        when 'R' then 'Rejected'
        when 'U' then 'Partial Sales Issue'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_schedule_item_status_desc`,

    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mtso`.`sales_order_no` as `sales_order_no`,
    `mtso`.`sales_order_version` as `sales_order_version`,
    `mtso`.`sales_order_date` as `sales_order_date`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`batch_no` as `batch_no`,
    `mt`.`product_material_print_name` as `product_material_print_name`,
    `mt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `mt`.`sr_no` as `sr_no`,
    `mtso`.`material_quantity` as `material_quantity`,
    `mtso`.`material_weight` as `material_weight`,
    `mtso`.`material_rate` as `material_rate`,
    `mtso`.`material_basic_amount` as `material_basic_amount`,
    `mtso`.`material_discount_percent` as `material_discount_percent`,
    `mtso`.`material_discount_amount` as `material_discount_amount`,
    `mtso`.`material_taxable_amount` as `material_taxable_amount`,
    `mtso`.`material_cgst_percent` as `material_cgst_percent`,
    `mtso`.`material_cgst_total` as `material_cgst_total`,
    `mtso`.`material_sgst_percent` as `material_sgst_percent`,
    `mtso`.`material_sgst_total` as `material_sgst_total`,
    `mtso`.`material_igst_percent` as `material_igst_percent`,
    `mtso`.`material_igst_total` as `material_igst_total`,
    `mtso`.`material_total_amount` as `material_total_amount`,
    `mtso`.`material_schedule_date` as `material_schedule_date`,
    `rmfg`.`product_fg_code` as `product_material_code`,
    `mtso`.`so_rate` as `so_rate`,
    `mt`.`expected_dispatch_quantity` as `expected_dispatch_quantity`,
    `mt`.`expected_dispatch_weight` as `expected_dispatch_weight`,
    `mt`.`actual_dispatch_quantity` as `actual_dispatch_quantity`,
    `mt`.`actual_dispatch_weight` as `actual_dispatch_weight`,
    `mt`.`dispatch_return_quantity` as `dispatch_return_quantity`,
    `mt`.`dispatch_return_weight` as `dispatch_return_weight`,
    `mt`.`pending_quantity` as `pending_quantity`,
    `mt`.`pending_weight` as `pending_weight`,
    `mt`.`expected_schedule_date` as `expected_schedule_date`,
    `mt`.`delayed_days` as `delayed_days`,
    `mt`.`dispatched_quantity` as `dispatched_quantity`,
    `mt`.`dispatched_weight` as `dispatched_weight`,
    `mt`.`invoice_quantity` as `invoice_quantity`,
    `mt`.`invoice_weight` as `invoice_weight`,
    `mt`.`dispatch_schedule_item_status` as `dispatch_schedule_item_status`,
    `mt`.`dispatch_schedule_creation_type` as `dispatch_schedule_creation_type`,
    `mt`.`dispatch_schedule_remark` as `dispatch_schedule_remark`,
    `dsmt`.`approved_date` as `approved_date`,
    `dsmt`.`total_quantity` as `total_quantity`,
    `dsmt`.`total_weight` as `total_weight`,
    `dsmt`.`actual_quantity` as `actual_quantity`,
    `dsmt`.`actual_weight` as `actual_weight`,
    `dsmt`.`dispatch_note_status` as `dispatch_note_status`,
    `dsmt`.`dispatch_note_remark` as `dispatch_note_remark`,
    `dsmt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `mt`.`lot_no` as `lot_no`,
    `mt`.`count_no` as `count_no`,
    `mt`.`roll_no` as `roll_no`,
    `mt`.`set_no` as `set_no`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`financial_year` as `financial_year`,
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
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`product_material_packing_id` as `product_material_packing_id`,
    `mt`.`dispatch_schedule_type_id` as `dispatch_schedule_type_id`,
    `mt`.`dispatch_schedule_master_transaction_id` as `dispatch_schedule_master_transaction_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`dispatch_schedule_details_transaction_id` as `dispatch_schedule_details_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `dsmt`.`customer_id` as `customer_id`,
    `dsmt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `dsmt`.`customer_state_id` as `customer_state_id`,
    `dsmt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`agent_id` as `agent_id`,
    `ag`.`agent_name` as `agent_name`,
    `ag`.`agent_address1` as `agent_address1`,
    `ag`.`city_name` as `agent_city_name`,
    `ag`.`state_name` as `agent_state_name`,
    `consi`.`customer_name` as `consi_customer_name`,
    `consi`.`cust_branch_address1` as `consi_branch_address1`,
    `consi`.`city_name` as `consi_city_name`,
    `consi`.`state_name` as `consi_state_name`,
    `dsmt`.`dispatch_supervisor_id` as `dispatch_supervisor_id`,
    `dsmt`.`approved_by_id` as `approved_by_id`
from
    ((((((`mt_dispatch_schedule_details_trading` `mt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `mt_dispatch_schedule_master_trading` `dsmt` on
    (`dsmt`.`dispatch_schedule_master_transaction_id` = `mt`.`dispatch_schedule_master_transaction_id`
        and `dsmt`.`company_id` = `mt`.`company_id`))
left join `mt_sales_order_details_trading` `mtso` on
    (`mtso`.`customer_order_no` = `mt`.`customer_order_no`
        and `mtso`.`sales_order_no` = `mt`.`sales_order_no`
        and `mtso`.`product_material_id` = `mt`.`product_material_id`
        and `mtso`.`so_sr_no` = `mt`.`so_sr_no`
        and `mtso`.`company_id` = `mt`.`company_id`
        and `mtso`.`company_branch_id` = `mt`.`company_branch_id`
        and `mtso`.`is_delete` = 0))
left join `sm_product_fg` `rmfg` on
    (`rmfg`.`product_fg_id` = `mt`.`product_material_id`))
left join `cmv_agent` `ag` on
    (`ag`.`agent_id` = `mt`.`agent_id`))
left join `cmv_customer_summary` `consi` on
    (`consi`.`customer_id` = `mt`.`consignee_id`))
where
    `mt`.`is_delete` = 0;





