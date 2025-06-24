INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (1,'Dispatch Type',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);


INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (185,'Dispatch Type',2,'Cotton Bales','CB',1,0,'6260537025','2024-11-19 11:49:22.000',NULL,'2024-11-19 11:49:22.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Wash Oil','WO',1,0,'6260537025','2024-11-19 11:49:52.000',NULL,'2024-11-19 11:49:52.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Yarn','YN',1,0,'6260537025','2024-11-19 11:50:12.000',NULL,'2024-11-19 11:50:12.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Waste','WS',1,0,'6260537025','2024-11-19 11:50:32.000',NULL,'2024-11-19 11:50:32.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Sized Beam','SB',1,0,'6260537025','2024-11-19 11:50:47.000',NULL,'2024-11-19 11:50:47.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Grey Fabric','GF',1,0,'6260537025','2024-11-19 11:51:00.000',NULL,'2024-11-19 11:51:00.000',NULL,NULL,'DT',''),
	 (185,'Dispatch Type',2,'Finishe Fabric','FF',1,0,'6260537025','2024-11-19 11:52:55.000',NULL,'2024-11-19 11:52:55.000',NULL,NULL,'DT','');



ALTER TABLE mt_dispatch_schedule_master_trading ADD dispatch_type varchar(100) NULL;

-- erp_development.mtv_dispatch_schedule_master_trading source

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
left join `cmv_employee` `e` on
    (`e`.`company_branch_id` = `mt`.`company_branch_id`
        and `e`.`company_id` = `mt`.`company_id`
        and `e`.`employee_id` = `mt`.`dispatch_supervisor_id`))
left join `cmv_employee` `e1` on
    (`e1`.`company_branch_id` = `mt`.`company_branch_id`
        and `e1`.`company_id` = `mt`.`company_id`
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;




-- erp_development.mtv_dispatch_challan_tax_summary source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_tax_summary` as
select
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `c`.`customer_name` as `customer_name`,
    `st`.`state_name` as `customer_state_name`,
    `c`.`cust_branch_gst_no` as `customer_gst_no`,
    `mt`.`hsn_code_id` as `hsn_code_id`,
    `h`.`hsn_sac_code` as `hsn_sac_code`,
    `h`.`hsn_sac_rate` as `hsn_sac_rate`,
    `mt`.`summary_taxable_amount` as `summary_taxable_amount`,
    `mt`.`summary_cgst_percent` as `summary_cgst_percent`,
    `mt`.`summary_cgst_total` as `summary_cgst_total`,
    `mt`.`summary_sgst_percent` as `summary_sgst_percent`,
    `mt`.`summary_sgst_total` as `summary_sgst_total`,
    `mt`.`summary_igst_percent` as `summary_igst_percent`,
    `mt`.`summary_igst_total` as `summary_igst_total`,
    `mt`.`summary_total_amount` as `summary_total_amount`,
    `mt`.`remark` as `remark`,
    `v`.`company_name` as `company_name`,
    `mt`.`financial_year` as `financial_year`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `ct`.`city_name` as `customer_city_name`,
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
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`dispatch_challan_tax_summary_transaction_id` as `dispatch_challan_tax_summary_transaction_id`
from
    (((((`mt_dispatch_challan_tax_summary` `mt`
left join `cmv_company_branch_summary` `v` on
    (`v`.`company_branch_id` = `mt`.`expected_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `cmv_customer_summary` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`customer_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`customer_state_id`))
left join `cmv_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`hsn_code_id`))
where
    `mt`.`is_delete` = 0;


ALTER TABLE mt_dispatch_challan_master_trading ADD dispatch_type varchar(100) NULL;

-- erp_development.mtv_dispatch_challan_master_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_master_trading` as
select
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    case
        `mt`.`dispatch_challan_status` when 'A' then 'Aprroved'
        when 'U' then 'Partial Sales Issue'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'D' then 'Dispath Challan Created'
        when 'I' then 'Invoice Created'
        else 'Pending'
    end as `dispatch_challan_status_desc`,
    `c`.`customer_name` as `customer_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `st1`.`state_name` as `consignee_state_name`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`dispatch_challan_remark` as `dispatch_challan_remark`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
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
    `h`.`hsn_sac_code` as `freight_fg_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `freight_fg_hsn_sac_rate`,
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
    `a`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    `mt`.`agent_amount` as `agent_amount`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    case
        `mt`.`dispatch_challan_creation_type` when 'M' then 'Mannual'
        when 'D' then 'Dispatch Schedule Based'
        when 'O' then 'Sales Order Based'
    end as `dispatch_challan_creation_type_desc`,
    case
        `mt`.`mail_sent_status` when 'S' then 'Sent'
        when 'F' then 'Failed'
    end as `mail_sent_status_desc`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`remark` as `remark`,
    `v`.`company_name` as `company_name`,
    `mt`.`financial_year` as `financial_year`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`cust_branch_address1` as `customer_address1`,
    `ct`.`city_name` as `customer_city_name`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `ct1`.`city_name` as `consignee_city_name`,
    `cc`.`cust_branch_address1` as `consignee_address1`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
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
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`transporter_id` as `transporter_id`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`mail_sent_status` as `mail_sent_status`,
    `mt`.`dispatch_type` as`dispatch_type`,
    (
    select
        sum(`dt1`.`dispatch_quantity`)
    from
        `mt_dispatch_challan_details_trading` `dt1`
    where
        `dt1`.`dispatch_challan_no` = `mt`.`dispatch_challan_no`
        and `dt1`.`is_delete` = 0) as `total_dispatch_quantity`,
    (
    select
        sum(`dt1`.`dispatch_weight`)
    from
        `mt_dispatch_challan_details_trading` `dt1`
    where
        `dt1`.`dispatch_challan_no` = `mt`.`dispatch_challan_no`
        and `dt1`.`is_delete` = 0) as `total_dispatch_weight`
from
    ((((((((((`mt_dispatch_challan_master_trading` `mt`
left join `cmv_company_branch_summary` `v` on
    (`v`.`company_branch_id` = `mt`.`expected_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `cmv_customer_summary` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_agent` `a` on
    (`a`.`company_id` = `mt`.`company_id`
        and `a`.`agent_id` = `mt`.`agent_id`))
left join `cmv_customer_summary` `cc` on
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
left join `cmv_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `mt`.`dispatch_challan_type_id`
        and `pt`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;