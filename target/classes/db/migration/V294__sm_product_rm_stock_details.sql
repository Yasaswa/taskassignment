ALTER TABLE mt_dispatch_challan_batchwise_trading ADD batch_dispatch_roll bigint(20) NULL;
ALTER TABLE mt_dispatch_challan_details_trading ADD set_no varchar(100) NULL;


-- mtv_dispatch_challan_details_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_details_trading` as
select
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `mt`.`dispatch_challan_status_desc` as `dispatch_challan_status_desc`,
    `mt`.`customer_name` as `customer_name`,
    `mt`.`customer_state_name` as `customer_state_name`,
    `mt`.`consignee_name` as `consignee_name`,
    `mt`.`consignee_state_name` as `consignee_state_name`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`customer_gst_no` as `customer_gst_no`,
    `mt`.`consignee_gst_no` as `consignee_gst_no`,
    `mt`.`customer_pan_no` as `customer_pan_no`,
    `mt`.`consignee_pan_no` as `consignee_pan_no`,
    `mt`.`transporter_gst_no` as `transporter_gst_no`,
    `mt`.`vehicle_no` as `vehicle_no`,
    `mt`.`vehicle_type` as `vehicle_type`,
    `mt`.`transporter_challan_no` as `transporter_challan_no`,
    `mt`.`transporter_challan_date` as `transporter_challan_date`,
    `mt`.`loading_time` as `loading_time`,
    `mt`.`transport_mode` as `transport_mode`,
    `mt`.`transaction_type` as `transaction_type`,
    `mt`.`is_service` as `is_service`,
    `mt`.`is_sez` as `is_sez`,
    `mt`.`interim` as `interim`,
    `mt`.`overall_schedule_date` as `overall_schedule_date`,
    `mt`.`basic_total` as `basic_total`,
    `mt`.`transport_amount` as `transport_amount`,
    `mt`.`freight_amount` as `freight_amount`,
    `mt`.`is_freight_taxable` as `is_freight_taxable`,
    `mt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `mt`.`freight_fg_hsn_sac_code` as `freight_fg_hsn_sac_code`,
    `mt`.`freight_fg_hsn_sac_rate` as `freight_fg_hsn_sac_rate`,
    `mt`.`packing_amount` as `packing_amount`,
    `mt`.`discount_percent` as `discount_percent`,
    `mt`.`discount_amount` as `discount_amount`,
    `mt`.`other_amount` as `other_amount`,
    `mt`.`taxable_total` as `taxable_total`,
    `mt`.`cgst_percent` as `cgst_percent`,
    `mt`.`cgst_total` as `cgst_total`,
    `mt`.`sgst_percent` as `sgst_percent`,
    `mt`.`sgst_total` as `sgst_total`,
    `mt`.`igst_percent` as `igst_percent`,
    `mt`.`igst_total` as `igst_total`,
    `mt`.`roundoff` as `roundoff`,
    `mt`.`grand_total` as `grand_total`,
    `mt`.`agent_id` as `agent_id`,
    `mt`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    `mt`.`agent_amount` as `agent_amount`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    `mt`.`dispatch_challan_creation_type_desc` as `dispatch_challan_creation_type_desc`,
    case
        `dt`.`dispatch_challan_item_status` when 'A' then 'Aprroved'
        when 'U' then 'Partial Sales Issue'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_challan_item_status_desc`,
    `dt`.`sales_order_no` as `sales_order_no`,
    `dt`.`sales_order_Date` as `sales_order_Date`,
    `dt`.`sales_order_version` as `sales_order_version`,
    `dt`.`customer_order_no` as `customer_order_no`,
    `dt`.`customer_order_Date` as `customer_order_Date`,
    `dt`.`dispatch_schedule_no` as `dispatch_schedule_no`,
    `dt`.`dispatch_schedule_date` as `dispatch_schedule_date`,
    `dt`.`dispatch_schedule_version` as `dispatch_schedule_version`,
    `dt`.`sr_no` as `sr_no`,
    `dt`.`product_material_id` as `product_material_id`,
    `dt`.`so_sr_no` as `so_sr_no`,
    `rm`.`product_material_name` as `product_material_name`,
    `rm`.`product_type_group` as `product_type_group`,
    `rm`.`product_material_std_weight` as `product_material_std_weight`,
    `dt`.`product_material_print_name` as `product_material_print_name`,
    `dt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `dt`.`product_material_unit_id` as `product_material_unit_id`,
    `u`.`product_unit_name` as `product_unit_name`,
    `dt`.`product_material_packing_id` as `product_material_packing_id`,
    `p`.`product_packing_name` as `product_packing_name`,
    `dt`.`dispatch_quantity` as `dispatch_quantity`,
    `dt`.`dispatch_weight` as `dispatch_weight`,
    `dt`.`hsn_sac_id` as `hsn_sac_id`,
    `h1`.`hsn_sac_code` as `hsn_sac_code`,
    `h1`.`hsn_sac_rate` as `hsn_sac_rate`,
    `dt`.`hsn_sac_percent` as `hsn_sac_percent`,
    `dt`.`item_rate` as `item_rate`,
    `dt`.`item_amount` as `item_amount`,
    `dt`.`item_discount_percent` as `item_discount_percent`,
    `dt`.`item_discount_amount` as `item_discount_amount`,
    `dt`.`item_taxable_amount` as `item_taxable_amount`,
    `dt`.`item_cgst_percent` as `item_cgst_percent`,
    `dt`.`item_cgst_amount` as `item_cgst_amount`,
    `dt`.`item_sgst_percent` as `item_sgst_percent`,
    `dt`.`item_sgst_amount` as `item_sgst_amount`,
    `dt`.`item_igst_percent` as `item_igst_percent`,
    `dt`.`item_igst_amount` as `item_igst_amount`,
    `dt`.`item_total_amount` as `item_total_amount`,
    `dt`.`item_freight_amount` as `item_freight_amount`,
    `dt`.`dispatch_return_quantity` as `dispatch_return_quantity`,
    `dt`.`dispatch_return_weight` as `dispatch_return_weight`,
    `dt`.`pending_quantity` as `pending_quantity`,
    `dt`.`pending_weight` as `pending_weight`,
    `dt`.`expected_schedule_date` as `expected_schedule_date`,
    `dt`.`delayed_days` as `delayed_days`,
    `dt`.`invoice_quantity` as `invoice_quantity`,
    `dt`.`invoice_weight` as `invoice_weight`,
    `dt`.`pree_close_flag` as `pree_close_flag`,
    `dt`.`pree_close_quantity` as `pree_close_quantity`,
    `dt`.`set_no` as `set_no`,
    `dt`.`dispatch_challan_item_status` as `dispatch_challan_item_status`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`product_type_short_name` as `product_type_short_name`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `v`.`company_address1` as `company_address1`,
    `v`.`company_address2` as `company_address2`,
    `v`.`company_cell_no` as `company_cell_no`,
    `v`.`company_phone_no` as `company_phone_no`,
    `v`.`company_EmailId` as `company_EmailId`,
    `v`.`company_website` as `company_website`,
    `v`.`company_gst_no` as `company_gst_no`,
    `v`.`company_pan_no` as `company_pan_no`,
    `v`.`company_state` as `company_state`,
    `v`.`company_pincode` as `company_pincode`,
    `v`.`company_udyog_adhar_no` as `company_udyog_adhar_no`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`customer_email` as `customer_email`,
    `mt`.`customer_city_name` as `customer_city_name`,
    `mt`.`consignee_email` as `consignee_email`,
    `mt`.`consignee_city_name` as `consignee_city_name`,
    case
        when `mt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
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
    `mt`.`dispatch_challan_master_transaction_id` as `dispatch_challan_master_transaction_id`,
    `dt`.`dispatch_challan_details_transaction_id` as `dispatch_challan_details_transaction_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `rm`.`product_type_id` as `product_type_id`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`transporter_id` as `transporter_id`,
    `mt`.`approved_by_id` as `approved_by_id`
from
    (((((((`mt_dispatch_challan_details_trading` `dt`
left join `mtv_dispatch_challan_master_trading` `mt` on
    (`mt`.`dispatch_challan_master_transaction_id` = `dt`.`dispatch_challan_master_transaction_id`
        and `mt`.`company_id` = `dt`.`company_id`))
left join `cm_hsn_sac` `h1` on
    (`h1`.`hsn_sac_id` = `dt`.`hsn_sac_id`
        and `h1`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`
        and `h1`.`is_delete` = 0))
left join `sm_product_packing` `p` on
    (`p`.`product_packing_id` = `dt`.`product_material_packing_id`
        and `p`.`is_delete` = 0))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `dt`.`product_material_unit_id`
        and `u`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `dt`.`product_material_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `dt`.`company_id`))
where
    `mt`.`is_delete` = 0;


-- mtv_dispatch_challan_master_trading_rpt source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_master_trading_rpt` as
select
    concat(`v`.`dispatch_challan_no`, ':Challan No:Y:C:mtv_dispatch_challan_master_trading:F') as `dispatch_challan_no`,
    concat(`v`.`dispatch_challan_date`, ':Challan Date:Y:D:') as `dispatch_challan_date`,
    concat(`v`.`dispatch_challan_version`, ':Challan Version:O:N:') as `dispatch_challan_version`,
    concat('', ':Total Dispatch Quantity:O:N:') as `total_dispatch_quantity`,
    concat('', ':Total Dispatch Weight:O:N:') as `total_dispatch_weight`,
    concat(`v`.`customer_name`, ':Customer Name:Y:C:cmv_customer:F') as `customer_name`,
    concat(`v`.`dispatch_challan_status_desc`, ':Challan Status:Y:H:(Aprroved,Partial Sales Issue,Rejected,Completed,Canceled,Dispath Challan Created,Invoice Created,Pending)') as `dispatch_challan_status_desc`,
    concat(`v`.`dispatch_challan_type`, ':Challan Type:O:N:') as `dispatch_challan_type`,
    concat(`v`.`transporter_challan_no`, ':Transporter Challan No:Y:C:mtv_dispatch_challan_master_trading:O') as `transporter_challan_no`,
    concat(`v`.`transporter_challan_date`, ':Transporter Challan Date:Y:D:') as `transporter_challan_date`,
    concat(`v`.`vehicle_no`, ':Vehicle No:O:N:') as `vehicle_no`,
    concat(`v`.`vehicle_type`, ':Vehicle Type:O:N:') as `vehicle_type`,
    concat(`v`.`loading_time`, ':Loading Time:O:N:') as `loading_time`,
    concat(`v`.`transport_mode`, ':Transport Mode:O:N:') as `transport_mode`,
    concat(`v`.`transaction_type`, ':Transaction Type:O:N:') as `transaction_type`,
    concat(`v`.`is_service`, ':Is Service:Y:H:(Yes,No)') as `is_service`,
    concat(`v`.`is_sez`, ':Is Sez:Y:H:(Yes,No)') as `is_sez`,
    concat(`v`.`interim`, ':Interim:Y:H:(Yes,No)') as `interim`,
    concat(`v`.`overall_schedule_date`, ':Overall Schedule Date:Y:D:') as `overall_schedule_date`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') as `basic_total`,
    concat(`v`.`transport_amount`, ':Transport Amount:O:N:') as `transport_amount`,
    concat(`v`.`freight_amount`, ':Freight Amount:O:N:') as `freight_amount`,
    concat(`v`.`is_freight_taxable`, ':Is Freight Taxable:Y:H:(Yes,No)') as `is_freight_taxable`,
    concat(`v`.`freight_fg_hsn_sac_code`, ':FG Hsn Sac Code:O:N:') as `freight_fg_hsn_sac_code`,
    concat(`v`.`freight_fg_hsn_sac_rate`, ':FG Hsn Sac Rate:O:N:') as `freight_fg_hsn_sac_rate`,
    concat(`v`.`packing_amount`, ':Packing Amount:O:N:') as `packing_amount`,
    concat(`v`.`discount_percent`, ':Discount Percent:O:N:') as `discount_percent`,
    concat(`v`.`discount_amount`, ':Discount Amount:O:N:') as `discount_amount`,
    concat(`v`.`other_amount`, ':Other Amount:O:N:') as `other_amount`,
    concat(`v`.`taxable_total`, ':Taxable Total:O:N:') as `taxable_total`,
    concat(`v`.`cgst_percent`, ':CGST Percent:O:N:') as `cgst_percent`,
    concat(`v`.`cgst_total`, ':CGST Total:O:N:') as `cgst_total`,
    concat(`v`.`sgst_percent`, ':SGST Percent:O:N:') as `sgst_percent`,
    concat(`v`.`sgst_total`, ':SGST Total:O:N:') as `sgst_total`,
    concat(`v`.`igst_percent`, ':IGST Percent:O:N:') as `igst_percent`,
    concat(`v`.`igst_total`, ':IGST Total:O:N:') as `igst_total`,
    concat(`v`.`roundoff`, ':RoundOff:O:N:') as `roundoff`,
    concat(`v`.`grand_total`, ':Grand Total:O:N:') as `grand_total`,
    concat(`v`.`agent_name`, ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(`v`.`agent_percent`, ':Agent Percent:O:N:') as `agent_percent`,
    concat(`v`.`agent_amount`, ':Agent Amount:O:N:') as `agent_amount`,
    concat(`v`.`agent_paid_status`, ':Agent Paid Status:O:N:') as `agent_paid_status`,
    concat(`v`.`dispatch_challan_creation_type`, ':Challan Creation Type:Y:H:(Mannual,Dispatch Schedule Based,Sales Order Based)') as `dispatch_challan_creation_type_desc`,
    concat(`v`.`dispatch_challan_status`, ':Dispatch Challan Status:O:N:') as `dispatch_challan_status`,
    concat(`v`.`dispatch_challan_creation_type`, ':Dispatch Challan Creation Type:O:N:') as `dispatch_challan_creation_type`,
    concat(`v`.`customer_email`, ':Customer Email:O:N:') as `customer_email`,
    concat(`v`.`customer_city_name`, ':Customer City:O:N:') as `customer_city_name`,
    concat(`v`.`customer_gst_no`, ':Customer GST No:O:N:') as `customer_gst_no`,
    concat(`v`.`customer_pan_no`, ':Customer Pan No:O:N:') as `customer_pan_no`,
    concat(`v`.`customer_state_name`, ':Customer State:O:N:') as `customer_state_name`,
    concat(`v`.`consignee_name`, ':Consignee Name:O:N:') as `consignee_name`,
    concat(`v`.`consignee_email`, ':Consignee Email:O:N:') as `consignee_email`,
    concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') as `consignee_city_name`,
    concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') as `consignee_state_name`,
    concat(`v`.`consignee_gst_no`, ':Consignee GST No:O:N:') as `consignee_gst_no`,
    concat(`v`.`consignee_pan_no`, ':Consignee Pan No:O:N:') as `consignee_pan_no`,
    concat(`v`.`transporter_gst_no`, ':Transporter GST No:O:N:') as `transporter_gst_no`,
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
    concat(`v`.`dispatch_challan_master_transaction_id`, ':Dispatch Challan Master Transaction Id:O:N:') as `dispatch_challan_master_transaction_id`,
    concat(`v`.`customer_id`, ':Customer Id:N:N:') as `customer_id`,
    concat(`v`.`customer_contacts_ids`, ':Customer Contacts Ids:N:N:') as `customer_contacts_ids`,
    concat(`v`.`customer_state_id`, ':Customer State Id:N:N:') as `customer_state_id`,
    concat(`v`.`customer_city_id`, ':Customer City Id:N:N:') as `customer_city_id`,
    concat(`v`.`consignee_id`, ':Consignee Id:N:N:') as `consignee_id`,
    concat(`v`.`consignee_state_id`, ':Consignee State Id:N:N:') as `consignee_state_id`,
    concat(`v`.`consignee_city_id`, ':Consignee City Id:N:N:') as `consignee_city_id`,
    concat(`v`.`approved_by_id`, ':Approved By Id:N:N:') as `approved_by_id`,
    concat(`v`.`agent_id`, ':Agent Id:N:N:') as `agent_id`,
    concat(`v`.`freight_hsn_code_id`, ':Freight Hsn Code Id:N:N:') as `freight_hsn_code_id`,
    concat(`v`.`transporter_id`, ':Transporter Id:N:N:') as `transporter_id`,
    concat(`v`.`dispatch_challan_type_id`, ':Dispatch Challan Type Id:N:N:') as `dispatch_challan_type_id`
from
    `mtv_dispatch_challan_master_trading` `v`
limit 1;






-- mtv_dispatch_challan_batchwise_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_batchwise_trading` as
select
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    case
        `mt`.`dispatch_challan_batch_status` when 'A' then 'Aprroved'
        when 'I' then 'Partial Sales Issue'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_challan_batch_status_desc`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_Date` as `sales_order_Date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`dispatch_schedule_no` as `dispatch_schedule_no`,
    `mt`.`dispatch_schedule_date` as `dispatch_schedule_date`,
    `mt`.`dispatch_schedule_version` as `dispatch_schedule_version`,
    `mtvdt`.`customer_name` as `customer_name`,
    `mtvdt`.`customer_state_name` as `customer_state_name`,
    `mtvdt`.`customer_name` as `consignee_name`,
    `mtvdt`.`consignee_state_name` as `consignee_state_name`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`batch_no` as `batch_no`,
    `mtvdt`.`product_type_group` as `product_type_group`,
    `mtvdt`.`product_material_name` as `product_material_name`,
    `mt`.`batch_dispatch_quantity` as `batch_dispatch_quantity`,
    `mt`.`batch_dispatch_weight` as `batch_dispatch_weight`,
    `mt`.`batch_dispatch_roll` as `batch_dispatch_roll`,
    `mt`.`dispatch_challan_batch_status` as `dispatch_challan_batch_status`,
    `mt`.`dispatch_challan_batch_remark` as `dispatch_challan_batch_remark`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`financial_year` as `financial_year`,
    `mtvdt`.`customer_email` as `customer_email`,
    `mtvdt`.`customer_city_name` as `customer_city_name`,
    `mtvdt`.`consignee_email` as `consignee_email`,
    `mtvdt`.`consignee_city_name` as `consignee_city_name`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`dispatch_challan__batchwise_transaction_id` as `dispatch_challan__batchwise_transaction_id`,
    `mt`.`dispatch_challan_details_transaction_id` as `dispatch_challan_details_transaction_id`,
    `mt`.`dispatch_challan_master_transaction_id` as `dispatch_challan_master_transaction_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`goods_receipt_no` as `goods_receipt_no`,
    `mt`.`godown_id` as `godown_id`,
    `mt`.`godown_section_id` as `godown_section_id`,
    `mt`.`godown_section_beans_id` as `godown_section_beans_id`
from
    ((`mt_dispatch_challan_batchwise_trading` `mt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `mtv_dispatch_challan_details_trading` `mtvdt` on
    (`mtvdt`.`company_id` = `mt`.`company_id`
        and `mtvdt`.`dispatch_challan_details_transaction_id` = `mt`.`dispatch_challan_details_transaction_id`
        and `mtvdt`.`dispatch_challan_master_transaction_id` = `mt`.`dispatch_challan_master_transaction_id`))
where
    `mt`.`is_delete` = 0;



ALTER TABLE sm_product_rm_stock_summary ADD sales_rejection_no_of_boxes bigint(20) DEFAULT 0 NULL;
ALTER TABLE sm_product_rm_stock_summary CHANGE sales_rejection_no_of_boxes sales_rejection_no_of_boxes bigint(20) DEFAULT 0 NULL AFTER sales_rejection_weight;

ALTER TABLE sm_product_rm_stock_details ADD sales_rejection_no_of_boxes bigint(20) DEFAULT 0 NULL;
ALTER TABLE sm_product_rm_stock_details CHANGE sales_rejection_no_of_boxes sales_rejection_no_of_boxes bigint(20) DEFAULT 0 NULL AFTER sales_rejection_weight;



-- smv_product_rm_stock_details source

create or replace
algorithm = UNDEFINED view `smv_product_rm_stock_details` as
select
    `sm`.`stock_date` as `stock_date`,
    `sm`.`day_closed` as `day_closed`,
    `sm`.`batch_no` as `batch_no`,
    `supp`.`supp_branch_name` as `supplier_name`,
    `sm`.`batch_expiry_date` as `batch_expiry_date`,
    `sm`.`goods_receipt_no` as `goods_receipt_no`,
    `sm`.`goods_receipt_version` as `goods_receipt_version`,
    `cm`.`customer_code` as `customer_code`,
    `cm`.`customer_name` as `customer_name`,
    `sm`.`sales_order_no` as `sales_order_no`,
    `sm`.`sales_order_version` as `sales_order_version`,
    `sm`.`customer_goods_receipt_no` as `customer_goods_receipt_no`,
    `sm`.`customer_goods_receipt_version` as `customer_goods_receipt_version`,
    `sm`.`so_sr_no` as `so_sr_no`,
    `sm`.`customer_order_no` as `customer_order_no`,
    `rm`.`product_material_code` as `product_rm_code`,
    `rm`.`product_material_name` as `product_rm_name`,
    `rm`.`product_material_technical_name` as `product_rm_technical_name`,
    `rm`.`product_type_name` as `product_type_name`,
    `rm`.`product_material_category1_name` as `product_material_category1_name`,
    `rm`.`product_material_category2_name` as `product_material_category2_name`,
    `rm`.`product_material_short_name` as `product_rm_short_name`,
    `su`.`product_unit_name` as `product_material_unit_name`,
    `rm`.`product_material_stock_unit_name` as `product_rm_purchase_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `sm`.`batch_rate` as `batch_rate`,
    `sm`.`order_quantity` as `order_quantity`,
    `sm`.`order_weight` as `order_weight`,
    `sm`.`pending_quantity` as `pending_quantity`,
    `sm`.`pending_weight` as `pending_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`batch_rate` * `sm`.`closing_balance_quantity` as `closing_amount`,
    `sm`.`reserve_quantity` as `reserve_quantity`,
    `sm`.`reserve_weight` as `reserve_weight`,
    `sm`.`excess_quantity` as `excess_quantity`,
    `sm`.`excess_weight` as `excess_weight`,
    `sm`.`pree_closed_quantity` as `pree_closed_quantity`,
    `sm`.`pree_closed_weight` as `pree_closed_weight`,
    `sm`.`purchase_quantity` as `purchase_quantity`,
    `sm`.`purchase_weight` as `purchase_weight`,
    `sm`.`purchase_return_quantity` as `purchase_return_quantity`,
    `sm`.`purchase_return_weight` as `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` as `jobcard_quantity`,
    `sm`.`jobcard_weight` as `jobcard_weight`,
    `sm`.`production_issue_quantity` as `production_issue_quantity`,
    `sm`.`production_issue_weight` as `production_issue_weight`,
    `sm`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` as `production_issue_return_weight`,
    `sm`.`production_issue_return_boxes` as `production_issue_return_boxes`,
    `sm`.`supplier_return_quantity` as `supplier_return_quantity`,
    `sm`.`supplier_return_weight` as `supplier_return_weight`,
    `sm`.`supplier_return_boxes` as `supplier_return_boxes`,
    `sm`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sm`.`production_quantity` as `production_quantity`,
    `sm`.`production_weight` as `production_weight`,
    `sm`.`production_no_of_boxes` as `production_no_of_boxes`,
    `sm`.`production_return_quantity` as `production_return_quantity`,
    `sm`.`production_return_weight` as `production_return_weight`,
    `sm`.`production_rejection_quantity` as `production_rejection_quantity`,
    `sm`.`production_rejection_weight` as `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `sm`.`sales_quantity` as `sales_quantity`,
    `sm`.`sales_weight` as `sales_weight`,
    `sm`.`sales_no_of_boxes` as `sales_no_of_boxes`,
    `sm`.`sales_return_quantity` as `sales_return_quantity`,
    `sm`.`sales_return_weight` as `sales_return_weight`,
    `sm`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` as `sales_rejection_weight`,
    `sm`.`sales_rejection_no_of_boxes` as `sales_rejection_no_of_boxes`,
    `sm`.`customer_receipt_quantity` as `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` as `customer_receipt_weight`,
    `sm`.`customer_return_quantity` as `customer_return_quantity`,
    `sm`.`customer_return_weight` as `customer_return_weight`,
    `sm`.`customer_rejection_quantity` as `customer_rejection_quantity`,
    `sm`.`customer_rejection_weight` as `customer_rejection_weight`,
    `sm`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` as `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` as `outsources_out_quantity`,
    `sm`.`outsources_out_weight` as `outsources_out_weight`,
    `sm`.`outsources_in_quantity` as `outsources_in_quantity`,
    `sm`.`outsources_in_weight` as `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` as `loan_receipt_weight`,
    `sm`.`loan_receipt_boxes` as `loan_receipt_boxes`,
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`loan_issue_boxes` as `loan_issue_boxes`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` as `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` as `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` as `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` as `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_area` as `godown_area`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gds`.`godown_section_area` as `godown_section_area`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `gdsb`.`godown_section_beans_area` as `godown_section_beans_area`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `pp`.`weight_per_packing` as `weight_per_packing`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `sm`.`financial_year` as `financial_year`,
    `sm`.`product_type_group` as `product_type_group`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`stock_transaction_id` as `stock_transaction_id`,
    `sm`.`product_rm_id` as `product_rm_id`,
    `sm`.`customer_id` as `customer_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`supplier_id` as `supplier_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_material_unit_id` as `product_material_unit_id`,
    `sm`.`product_material_packing_id` as `product_material_packing_id`,
    `rm`.`product_material_category1_id` as `product_material_category1_id`,
    `rm`.`product_material_category2_id` as `product_material_category2_id`
from
    ((((((((((`sm_product_rm_stock_details` `sm`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`company_branch_id` = `sm`.`company_branch_id`))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`is_delete` = 0
        and `rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join `cm_customer` `cm` on
    (`cm`.`is_delete` = 0
        and `cm`.`company_branch_id` = `sm`.`company_branch_id`
        and `cm`.`customer_id` = `sm`.`customer_id`))
left join `cmv_godown` `gd` on
    (`gd`.`is_delete` = 0
        and `gd`.`godown_id` = `sm`.`godown_id`))
left join `cmv_godown_section` `gds` on
    (`gds`.`is_delete` = 0
        and `gds`.`godown_section_id` = `sm`.`godown_section_id`))
left join `cmv_godown_section_beans` `gdsb` on
    (`gdsb`.`is_delete` = 0
        and `gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`))
left join `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
left join `smv_product_unit` `pu` on
    (`pu`.`is_delete` = 0
        and `pu`.`product_unit_id` = `sm`.`product_material_unit_id`))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `sm`.`supplier_id`))
left join `sm_product_unit` `su` on
    (`su`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `su`.`is_delete` = 0))
where
    `sm`.`is_delete` = 0
    and `sm`.`day_closed` = 0;



-- smv_product_rm_stock_summary source

create or replace
algorithm = UNDEFINED view `smv_product_rm_stock_summary` as
select
    `sm`.`financial_year` as `financial_year`,
    `supp`.`supp_branch_name` as `supplier_name`,
    `cust`.`customer_name` as `customer_name`,
    `pt`.`product_type_name` as `product_type_name`,
    `sm`.`product_type_group` as `product_type_group`,
    `rm`.`product_material_code` as `product_rm_code`,
    `rm`.`product_material_name` as `product_rm_name`,
    `rm`.`product_material_category1_name` as `product_material_category1_name`,
    `rm`.`product_material_category2_name` as `product_material_category2_name`,
    `pu`.`product_unit_name` as `product_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `sm`.`order_quantity` as `order_quantity`,
    `sm`.`order_weight` as `order_weight`,
    `sm`.`pending_quantity` as `pending_quantity`,
    `sm`.`pending_weight` as `pending_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`reserve_quantity` as `reserve_quantity`,
    `sm`.`reserve_weight` as `reserve_weight`,
    `sm`.`excess_quantity` as `excess_quantity`,
    `sm`.`excess_weight` as `excess_weight`,
    `sm`.`pree_closed_quantity` as `pree_closed_quantity`,
    `sm`.`pree_closed_weight` as `pree_closed_weight`,
    `sm`.`purchase_quantity` as `purchase_quantity`,
    `sm`.`purchase_weight` as `purchase_weight`,
    `sm`.`purchase_return_quantity` as `purchase_return_quantity`,
    `sm`.`purchase_return_weight` as `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` as `jobcard_quantity`,
    `sm`.`jobcard_weight` as `jobcard_weight`,
    `sm`.`production_issue_quantity` as `production_issue_quantity`,
    `sm`.`production_issue_weight` as `production_issue_weight`,
    `sm`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` as `production_issue_return_weight`,
    `sm`.`production_issue_return_boxes` as `production_issue_return_boxes`,
    `sm`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sm`.`production_quantity` as `production_quantity`,
    `sm`.`production_weight` as `production_weight`,
    `sm`.`production_no_of_boxes` as `production_no_of_boxes`,
    `sm`.`production_return_quantity` as `production_return_quantity`,
    `sm`.`production_return_weight` as `production_return_weight`,
    `sm`.`production_rejection_quantity` as `production_rejection_quantity`,
    `sm`.`production_rejection_weight` as `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `sm`.`sales_quantity` as `sales_quantity`,
    `sm`.`sales_weight` as `sales_weight`,
    `sm`.`sales_no_of_boxes` as `sales_no_of_boxes`,
    `sm`.`sales_return_quantity` as `sales_return_quantity`,
    `sm`.`sales_return_weight` as `sales_return_weight`,
    `sm`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` as `sales_rejection_weight`,
    `sm`.`sales_rejection_no_of_boxes` as `sales_rejection_no_of_boxes`,
    `sm`.`customer_receipt_quantity` as `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` as `customer_receipt_weight`,
    `sm`.`customer_return_quantity` as `customer_return_quantity`,
    `sm`.`customer_return_weight` as `customer_return_weight`,
    `sm`.`customer_rejection_weight` as `customer_rejection_weight`,
    `sm`.`customer_rejection_quantity` as `customer_rejection_quantity`,
    `sm`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` as `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` as `outsources_out_quantity`,
    `sm`.`outsources_out_weight` as `outsources_out_weight`,
    `sm`.`outsources_in_quantity` as `outsources_in_quantity`,
    `sm`.`outsources_in_weight` as `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` as `loan_receipt_weight`,
    `sm`.`loan_receipt_boxes` as `loan_receipt_boxes`,
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`loan_issue_boxes` as `loan_issue_boxes`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` as `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` as `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` as `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` as `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `sm`.`supplier_return_quantity` as `supplier_return_quantity`,
    `sm`.`supplier_return_weight` as `supplier_return_weight`,
    `sm`.`supplier_return_boxes` as `supplier_return_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `sm`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `sm`.`material_rate` as `material_rate`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`stock_transaction_id` as `stock_transaction_id`,
    `sm`.`supplier_id` as `supplier_id`,
    `sm`.`customer_id` as `customer_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_rm_id` as `product_rm_id`,
    `sm`.`product_material_unit_id` as `product_material_unit_id`,
    `sm`.`product_material_packing_id` as `product_material_packing_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `rm`.`product_material_category1_id` as `product_material_category1_id`,
    `rm`.`product_material_category2_id` as `product_material_category2_id`
from
    (((((((((((`sm_product_rm_stock_summary` `sm`
left join `cm_company` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_id` = `sm`.`company_id`
        and `cb`.`company_branch_id` = `sm`.`company_branch_id`
        and `v`.`is_delete` = 0))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `sm`.`supplier_id`))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `sm`.`godown_id`
        and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `sm`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `cm_customer` `cust` on
    (`cust`.`company_branch_id` = `sm`.`company_branch_id`
        and `cust`.`company_id` = `sm`.`company_id`
        and `cust`.`customer_id` = `sm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `sm`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join `sm_product_unit` `pu` on
    (`pu`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `pu`.`is_delete` = 0))
left join `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
where
    `sm`.`is_delete` = 0;



ALTER TABLE mt_sales_order_details_trading ADD set_no varchar(100) NULL;

-- mtv_sales_order_details_trading source

create or replace
algorithm = UNDEFINED view `mtv_sales_order_details_trading` as
select
    case
        `sotv`.`sales_order_life` when 'C' then 'Closed'
        when 'O' then 'Open'
    end as `sales_order_life_desc`,
    case
        `sotv`.`sales_order_creation_type` when 'M' then 'Mannual'
        when 'A' then 'Auto'
        when 'R' then 'Reorder'
    end as `sales_order_creation_type_desc`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_quotation_no` as `sales_quotation_no`,
    case
        `sotv`.`sales_order_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'Z' then 'PreeClosed'
        else 'Pending'
    end as `sales_order_status_desc`,
    case
        `mt`.`sales_order_item_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'Z' then 'PreeClosed'
        when 'O' then 'PO'
        when 'G' then 'GRN'
        else 'Pending'
    end as `sales_order_item_status_desc`,
    case
        `sotv`.`sales_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `sales_order_mail_sent_status_desc`,
    `mt`.`sales_quotation_date` as `sales_quotation_date`,
    `vp`.`department_name` as `department_name`,
    `cc`.`customer_name` as `customer_name`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `ccs`.`state_name` as `customer_state_name`,
    `csg`.`customer_name` as `consignee_name`,
    `csgs`.`state_name` as `consignee_state_name`,
    `sotv`.`approved_date` as `approved_date`,
    `sotv`.`overall_schedule_date` as `overall_schedule_date`,
    `mt`.`invoice_nos` as `invoice_nos`,
    `mt`.`sales_quantity` as `previous_dispatch_quantity`,
    `mt`.`sales_weight` as `previous_dispatch_weight`,
    '0' as `stock_quantity`,
    '0' as `stock_weight`,
    `mt`.`product_type` as `product_type`,
    `rmfgsr`.`product_type_group` as `product_type_group`,
    `rmfgsr`.`product_material_type_short_name` as `product_type_short_name`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` as `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` as `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` as `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` as `product_material_our_part_code`,
    `rmfgsr`.`product_material_packing_name` as `product_material_packing_name`,
    `mt`.`product_material_print_name` as `product_material_print_name`,
    `hsn`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `mt`.`sr_no` as `sr_no`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`material_quantity` as `material_quantity`,
    `mt`.`material_weight` as `material_weight`,
    `mt`.`quotation_rate` as `quotation_rate`,
    `mt`.`material_rate` as `material_rate`,
    `mt`.`material_basic_amount` as `material_basic_amount`,
    `mt`.`material_discount_percent` as `material_discount_percent`,
    `mt`.`material_discount_amount` as `material_discount_amount`,
    `mt`.`so_rate` as `so_rate`,
    `mt`.`material_taxable_amount` as `material_taxable_amount`,
    `mt`.`material_cgst_percent` as `material_cgst_percent`,
    `mt`.`material_cgst_total` as `material_cgst_total`,
    `mt`.`material_sgst_percent` as `material_sgst_percent`,
    `mt`.`material_sgst_total` as `material_sgst_total`,
    `mt`.`material_igst_percent` as `material_igst_percent`,
    `mt`.`material_igst_total` as `material_igst_total`,
    `mt`.`material_freight_amount` as `material_freight_amount`,
    `mt`.`material_total_amount` as `material_total_amount`,
    `mt`.`material_schedule_date` as `material_schedule_date`,
    `mt`.`product_recommendation` as `product_recommendation`,
    `mt`.`special_remark` as `special_remark`,
    `mt`.`epi_1` as `epi_1`,
    `mt`.`ppi_1` as `ppi_1`,
    `mt`.`width` as `width`,
    `mt`.`warp_crimp` as `warp_crimp`,
    `mt`.`weft_crimp` as `weft_crimp`,
    `mt`.`warp_waste` as `warp_waste`,
    `mt`.`weft_waste` as `weft_waste`,
    `mt`.`warp_count_1__wc1__actual_count` as `warp_count_1__wc1__actual_count`,
    `mt`.`warp_count_2__wc2__actual_count` as `warp_count_2__wc2__actual_count`,
    `mt`.`warp_count_3__wc3__actual_count` as `warp_count_3__wc3__actual_count`,
    `mt`.`warp_count_4__wc4__actual_count` as `warp_count_4__wc4__actual_count`,
    `mt`.`weft_count_1__fc1__actual_count` as `weft_count_1__fc1__actual_count`,
    `mt`.`weft_count_2__fc2__actual_count` as `weft_count_2__fc2__actual_count`,
    `mt`.`weft_count_3__fc3__actual_count` as `weft_count_3__fc3__actual_count`,
    `mt`.`weft_count_4__fc4__actual_count` as `weft_count_4__fc4__actual_count`,
    case
        `mt`.`monogram_is_applicable` when 1 then 'Yes'
        else 'No'
    end as `monogram_is_applicable_desc`,
    `mt`.`monogram_is_applicable` as `monogram_is_applicable`,
    `mt`.`weft_color` as `weft_color`,
    `mt`.`material_style` as `material_style`,
    `mt`.`material_style_value` as `material_style_value`,
    `mt`.`material_style_abbrevation` as `material_style_abbrevation`,
    `mt`.`dispatch_note_nos` as `dispatch_note_nos`,
    `mt`.`dispatch_challan_nos` as `dispatch_challan_nos`,
    `mt`.`production_issue_quantity` as `production_issue_quantity`,
    `mt`.`production_issue_weight` as `production_issue_weight`,
    `mt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `mt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `mt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `mt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sotv`.`sales_order_status` as `sales_order_status`,
    `sotv`.`sales_order_life` as `sales_order_life`,
    `sotv`.`sales_order_creation_type` as `sales_order_creation_type`,
    `mt`.`sales_order_item_status` as `sales_order_item_status`,
    `sotv`.`sales_order_mail_sent_status` as `sales_order_mail_sent_status`,
    `ccb`.`cust_branch_EmailId` as `customer_email`,
    `cccty`.`city_name` as `customer_city_name`,
    `ccd`.`district_name` as `customer_district_name`,
    `csgcty`.`city_name` as `consignee_city_name`,
    `csgd`.`district_name` as `consignee_district_name`,
    `e`.`employee_name` as `approved_by_name`,
    `sotv`.`basic_total` as `basic_total`,
    `sotv`.`transport_amount` as `transport_amount`,
    `sotv`.`freight_amount` as `freight_amount`,
    `sotv`.`packing_amount` as `packing_amount`,
    `sotv`.`discount_percent` as `discount_percent`,
    `sotv`.`discount_amount` as `discount_amount`,
    `sotv`.`other_amount` as `other_amount`,
    `sotv`.`taxable_total` as `taxable_total`,
    `sotv`.`cgst_percent` as `cgst_percent`,
    `sotv`.`cgst_total` as `cgst_total`,
    `sotv`.`sgst_percent` as `sgst_percent`,
    `sotv`.`sgst_total` as `sgst_total`,
    `sotv`.`igst_percent` as `igst_percent`,
    `sotv`.`igst_total` as `igst_total`,
    `sotv`.`roundoff` as `roundoff`,
    `sotv`.`grand_total` as `grand_total`,
    `sotv`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`branch_address1` as `company_address1`,
    `vb`.`branch_address2` as `company_address2`,
    `vb`.`branch_phone_no` as `company_phone_no`,
    `vb`.`branch_cell_no` as `company_cell_no`,
    `vb`.`branch_EmailId` as `company_EmailId`,
    `vb`.`branch_website` as `company_website`,
    `vb`.`branch_gst_no` as `company_gst_no`,
    `vb`.`branch_pan_no` as `company_pan_no`,
    `vbs`.`state_name` as `company_state`,
    `vb`.`branch_pincode` as `company_pincode`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`set_no` as `set_no`,
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
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`product_type_id` as `product_type_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`product_material_packing_id` as `product_material_packing_id`,
    `mt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `sotv`.`customer_id` as `customer_id`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_quantity`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) as `dispatched_quantity`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_weight`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) as `dispatched_weight`
from
    ((((((((((((((((((`mt_sales_order_details_trading` `mt`
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
left join `mt_sales_order_master_trading` `sotv` on
    (`sotv`.`company_branch_id` = `mt`.`company_branch_id`
        and `sotv`.`company_id` = `mt`.`company_id`
        and `sotv`.`sales_order_master_transaction_id` = `mt`.`sales_order_master_transaction_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `sotv`.`department_id`
        and `vp`.`is_delete` = 0))
left join `cm_customer` `cc` on
    (`cc`.`company_id` = `sotv`.`company_id`
        and `cc`.`customer_id` = `sotv`.`customer_id`
        and `cc`.`is_delete` = 0))
left join `cm_customer_branch` `ccb` on
    (`ccb`.`company_id` = `mt`.`company_id`
        and `ccb`.`customer_id` = `sotv`.`customer_id`))
left join `cm_state` `ccs` on
    (`ccs`.`state_id` = `ccb`.`cust_branch_state_id`))
left join `cm_city` `cccty` on
    (`cccty`.`city_id` = `ccb`.`cust_branch_city_id`))
left join `cm_district` `ccd` on
    (`ccd`.`district_id` = `ccb`.`cust_branch_district_id`))
left join `cm_customer` `csg` on
    (`csg`.`company_id` = `sotv`.`company_id`
        and `csg`.`customer_id` = `sotv`.`customer_id`
        and `csg`.`is_delete` = 0))
left join `cm_customer_branch` `csgcb` on
    (`csgcb`.`company_id` = `mt`.`company_id`
        and `csgcb`.`customer_id` = `sotv`.`customer_id`))
left join `cm_state` `csgs` on
    (`csgs`.`state_id` = `csgcb`.`cust_branch_state_id`))
left join `cm_city` `csgcty` on
    (`csgcty`.`city_id` = `csgcb`.`cust_branch_city_id`))
left join `cm_district` `csgd` on
    (`csgd`.`district_id` = `csgcb`.`cust_branch_district_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `sotv`.`approved_by_id`
        and `e`.`company_id` = `sotv`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `mt`.`company_branch_id`
        and `vb`.`company_id` = `mt`.`company_id`))
left join `cm_state` `vbs` on
    (`vbs`.`state_id` = `vb`.`branch_state_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `mt`.`product_material_hsn_code_id`
        and `hsn`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0
order by
    `mt`.`sales_order_details_transaction_id` desc;


