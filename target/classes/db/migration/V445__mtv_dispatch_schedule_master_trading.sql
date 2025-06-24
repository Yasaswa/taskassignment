-- erp_dev.mtv_dispatch_schedule_master_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_schedule_master_trading` as
select
    case
        `mt`.`dispatch_schedule_creation_type` when 'M' then 'Manual'
        when 'S' then 'Sales Order Based'
    end as `dispatch_schedule_creation_type_desc`,
    case
        `mt`.`dispatch_note_status` when 'A' then 'Aprroved'
        when 'I' then 'Partial Sales Issue'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_note_status_desc`,
    `mt`.`dispatch_schedule_no` as `dispatch_schedule_no`,
    `mt`.`dispatch_schedule_date` as `dispatch_schedule_date`,
    `mt`.`dispatch_schedule_version` as `dispatch_schedule_version`,
    `c`.`customer_name` as `customer_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `st1`.`state_name` as `consignee_state_name`,
    `e`.`employee_name` as `dispatch_supervisor_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `p`.`product_type_short_name` as `dispatch_type_short_name`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`total_quantity` as `total_quantity`,
    `mt`.`total_weight` as `total_weight`,
    `mt`.`actual_quantity` as `actual_quantity`,
    `mt`.`actual_weight` as `actual_weight`,
    `mt`.`dispatch_note_remark` as `dispatch_note_remark`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `mt`.`gate_pass_no` as `gate_pass_no`,
    `mt`.`gate_pass_date` as `gate_pass_date`,
    `mt`.`vehical_no` as `vehical_no`,
    `mt`.`container_no` as `container_no`,
    `mt`.`seal_no` as `seal_no`,
    `mt`.`net_weight` as `net_weight`,
    `mt`.`driver_name` as `driver_name`,
    `mt`.`driver_contact_no` as `driver_contact_no`,
    `mt`.`vehicle_reporting_time` as `vehicle_reporting_time`,
    `mt`.`vehicle_loading_started_time` as `vehicle_loading_started_time`,
    `mt`.`vehicle_loading_finish_time` as `vehicle_loading_finish_time`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`dispatch_note_status` as `dispatch_note_status`,
    `mt`.`dispatch_schedule_creation_type` as `dispatch_schedule_creation_type`,
    `mt`.`dispatch_type` as `dispatch_type`,
    `c`.`cust_branch_phone_no` as `customer_phone`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `ct`.`city_name` as `customer_city_name`,
    `c`.`cust_branch_gst_no` as `customer_gst_no`,
    `c`.`cust_branch_pan_no` as `customer_pan_no`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `cc`.`cust_branch_address1` as `consignee_address`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `ct1`.`city_name` as `consignee_city_name`,
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
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`dispatch_schedule_master_transaction_id` as `dispatch_schedule_master_transaction_id`,
    `mt`.`dispatch_schedule_type_id` as `dispatch_schedule_type_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`dispatch_supervisor_id` as `dispatch_supervisor_id`,
    `mt`.`approved_by_id` as `approved_by_id`
from
    ((((((((((`mt_dispatch_schedule_master_trading` `mt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `mt`.`dispatch_schedule_type_id`))
left join `cmv_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_customer` `cc` on
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
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`employee_id` = `mt`.`dispatch_supervisor_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_active` = 1
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;



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
    `dsmt`.`consignee_id` as `consignee_id`,
    `dsmt`.`consignee_state_id` as `consignee_state_id`,
    `dsmt`.`consignee_city_id` as `consignee_city_id`,
    `dsmt`.`dispatch_supervisor_id` as `dispatch_supervisor_id`,
    `dsmt`.`approved_by_id` as `approved_by_id`
from
    ((((`mt_dispatch_schedule_details_trading` `mt`
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
where
    `mt`.`is_delete` = 0;


-- erp_dev.mtv_dispatch_schedule_master_trading_rpt source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_schedule_master_trading_rpt` as
select
    concat(`v`.`dispatch_schedule_no`, ':Dispatch Schedule No:Y:T:') as `dispatch_schedule_no`,
    concat(`v`.`dispatch_schedule_date`, ':Dispatch Schedule Date:Y:D:') as `dispatch_schedule_date`,
    concat(`v`.`dispatch_schedule_version`, ':Dispatch Schedule Version:O:N:') as `dispatch_schedule_version`,
    concat(`v`.`customer_name`, ':Customer Name:Y:T:') as `customer_name`,
    concat(`v`.`dispatch_note_status`, ':Dispatch Note Status:Y:H:(Aprroved,Partial Sales Issue,Rejected,Completed,Canceled,Dispath Challan Created,Invoice Created,Pending)') as `dispatch_note_status_desc`,
    concat(`v`.`dispatch_supervisor_name`, ':Dispatch Supervisor:Y:T:') as `dispatch_supervisor_name`,
    concat(`v`.`total_quantity`, ':Total Quantity:O:N:') as `total_quantity`,
    concat(`v`.`actual_quantity`, ':Actual Quantity:O:N:') as `actual_quantity`,
    concat(`v`.`dispatch_type_short_name`, ':Dispatch Type Short Name:O:N:') as `dispatch_type_short_name`,
    concat(`v`.`dispatch_schedule_creation_type`, ':Dispatch Schedule Creation Type:Y:H:(Closed,Open)') as `dispatch_schedule_creation_type_desc`,
    concat(`v`.`approved_by_name`, ':Approved By:O:N:') as `approved_by_name`,
    concat(`v`.`approved_date`, ':Approved Date:Y:D:') as `approved_date`,
    concat(`v`.`customer_email`, ':Customer Email:O:N:') as `customer_email`,
    concat(`v`.`customer_city_name`, ':Customer City:O:N:') as `customer_city_name`,
    concat(`v`.`customer_phone`, ':Customer Phone:O:N:') as `customer_phone`,
    concat(`v`.`customer_gst_no`, ':Customer GST No:O:N:') as `customer_gst_no`,
    concat(`v`.`customer_pan_no`, ':Customer Pan No:O:N:') as `customer_pan_no`,
    concat(`v`.`cust_branch_address1`, ':Branch Address-1:O:N:') as `cust_branch_address1`,
    concat(`v`.`customer_state_name`, ':Customer State:O:N:') as `customer_state_name`,
    concat(`v`.`consignee_name`, ':Consignee Name:O:N:') as `consignee_name`,
    concat(`v`.`consignee_email`, ':Consignee Email:O:N:') as `consignee_email`,
    concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') as `consignee_city_name`,
    concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') as `consignee_state_name`,
    concat(`v`.`dispatch_note_status`, ':Dispatch Note Status:O:N:') as `dispatch_note_status`,
    concat(`v`.`dispatch_note_remark`, ':Dispatch Note Remark:O:N:') as `dispatch_note_remark`,
    concat(`v`.`other_terms_conditions`, ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
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
    concat(`v`.`customer_id`, ':Customer Id:N:N:') as `customer_id`,
    concat(`v`.`dispatch_schedule_master_transaction_id`, ':Dispatch Schedule Master Transaction Id:O:N:') as `dispatch_schedule_master_transaction_id`,
    concat(`v`.`dispatch_schedule_type_id`, ':Dispatch Schedule Type Id:N:N:') as `dispatch_schedule_type_id`,
    concat(`v`.`customer_contacts_ids`, ':Customer Contacts Ids:N:N:') as `customer_contacts_ids`,
    concat(`v`.`customer_state_id`, ':Customer State Id:N:N:') as `customer_state_id`,
    concat(`v`.`customer_city_id`, ':Customer City Id:N:N:') as `customer_city_id`,
    concat(`v`.`consignee_id`, ':Consignee Id:N:N:') as `consignee_id`,
    concat(`v`.`consignee_state_id`, ':Consignee State Id:N:N:') as `consignee_state_id`,
    concat(`v`.`consignee_city_id`, ':Consignee City Id:N:N:') as `consignee_city_id`,
    concat(`v`.`dispatch_supervisor_id`, ':Dispatch Supervisor Id:N:N:') as `dispatch_supervisor_id`,
    concat(`v`.`approved_by_id`, ':Approved By Id:N:N:') as `approved_by_id`
from
    `mtv_dispatch_schedule_master_trading` `v`
limit 1;

-- erp_dev.mtv_dispatch_schedule_details_trading_rpt source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_schedule_details_trading_rpt` as
select
    concat(`v`.`dispatch_schedule_no`, ':Dispatch Schedule No:Y:T:') as `dispatch_schedule_no`,
    concat(`v`.`dispatch_schedule_date`, ':Dispatch Schedule Date:Y:D:') as `dispatch_schedule_date`,
    concat(`v`.`dispatch_schedule_version`, ':Dispatch Schedule Version:O:N:') as `dispatch_schedule_version`,
    concat(`v`.`customer_order_no`, ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(`v`.`dispatch_schedule_type`, ':Dispatch Schedule Type:Y:T:') as `dispatch_schedule_type`,
    concat(`v`.`dispatch_schedule_item_status`, ':Dispatch Schedule Item Status:Y:H:(Pending,Aprroved,Rejected,Partial Sales Issue,Completed,Canceled,Dispath Challan Created,Invoice Created,Pending)') as `dispatch_schedule_item_status_desc`,
    concat(`v`.`expected_dispatch_quantity`, ':Dispatch Quantity:O:N:') as `expected_dispatch_quantity`,
    concat(`v`.`actual_dispatch_quantity`, ':Actual Dispatch Quantity:O:N:') as `actual_dispatch_quantity`,
    concat(`v`.`customer_order_Date`, ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(`v`.`product_material_print_name`, ':Product Name:Y:T:') as `product_material_print_name`,
    concat(`v`.`dispatch_return_quantity`, ':Dispatch Return Quantity:O:N:') as `dispatch_return_quantity`,
    concat(`v`.`expected_schedule_date`, ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(`v`.`delayed_days`, ':Delayed Days:O:N:') as `delayed_days`,
    concat(`v`.`dispatched_quantity`, ':Dispatched Quantity:O:N:') as `dispatched_quantity`,
    concat(`v`.`dispatched_weight`, ':Dispatched Weight:O:N:') as `dispatched_weight`,
    concat(`v`.`invoice_quantity`, ':Invoice Quantity:O:N:') as `invoice_quantity`,
    concat(`v`.`invoice_weight`, ':Invoice Weight:O:N:') as `invoice_weight`,
    concat(`v`.`dispatch_schedule_creation_type`, ':Dispatch Schedule Creation Type:Y:H:(Closed,Open)') as `dispatch_schedule_creation_type`,
    concat(`v`.`dispatch_schedule_item_status`, ':Dispatch Schedule Item Status:O:N:') as `dispatch_schedule_item_status`,
    concat(`v`.`dispatch_schedule_remark`, ':Dispatch Schedule Remark:O:N:') as `dispatch_schedule_remark`,
    concat(`v`.`approved_date`, ':Approved Date:Y:D:') as `approved_date`,
    concat(`v`.`total_quantity`, ':Total Quantity:O:N:') as `total_quantity`,
    concat(`v`.`actual_quantity`, ':Actual Quantity:O:N:') as `actual_quantity`,
    concat(`v`.`so_sr_no`, ':So Sr No:O:N:') as `so_sr_no`,
    concat(`v`.`batch_no`, ':Batch No:O:N:') as `batch_no`,
    concat(`v`.`dispatch_note_status`, ':Dispatch Note Status:O:N:') as `dispatch_note_status`,
    concat(`v`.`dispatch_note_remark`, ':Dispatch Note Remark:O:N:') as `dispatch_note_remark`,
    concat(`v`.`other_terms_conditions`, ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
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
    concat(`v`.`product_material_id`, ':Product Material Id:N:N:') as `product_material_id`,
    concat(`v`.`product_material_unit_id`, ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(`v`.`product_material_packing_id`, ':Material Packing Id:N:N:') as `product_material_packing_id`,
    concat(`v`.`dispatch_schedule_type_id`, ':Dispatch Schedule Type Id:N:N:') as `dispatch_schedule_type_id`,
    concat(`v`.`dispatch_schedule_master_transaction_id`, ':Dispatch Schedule Master Transaction Id:O:N:') as `dispatch_schedule_master_transaction_id`,
    concat(`v`.`dispatch_schedule_details_transaction_id`, ':Dispatch Schedule Details Transaction Id:N:N:') as `dispatch_schedule_details_transaction_id`,
    concat(`v`.`customer_id`, ':Customer Id:N:N:') as `customer_id`,
    concat(`v`.`customer_contacts_ids`, ':Customer Contacts Ids:N:N:') as `customer_contacts_ids`,
    concat(`v`.`customer_state_id`, ':Customer State Id:N:N:') as `customer_state_id`,
    concat(`v`.`customer_city_id`, ':Customer City Id:N:N:') as `customer_city_id`,
    concat(`v`.`consignee_id`, ':Consignee Id:N:N:') as `consignee_id`,
    concat(`v`.`consignee_state_id`, ':Consignee State Id:N:N:') as `consignee_state_id`,
    concat(`v`.`consignee_city_id`, ':Consignee City Id:N:N:') as `consignee_city_id`,
    concat(`v`.`dispatch_supervisor_id`, ':Dispatch Supervisor Id:N:N:') as `dispatch_supervisor_id`,
    concat(`v`.`approved_by_id`, ':Approved By Id:N:N:') as `approved_by_id`
from
    `mtv_dispatch_schedule_details_trading` `v`
limit 1;