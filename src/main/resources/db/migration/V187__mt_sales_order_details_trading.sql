DROP TABLE mt_sales_order_sauda_sheet;

 CREATE TABLE `mt_sales_order_sauda_sheet` (
   `sales_order_sauda_sheet_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* read only back end auto generated *',
   `sales_order_master_transaction_id` bigint(20) NOT NULL ,
   `company_id` bigint(20) NOT NULL DEFAULT 1 ,
   `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
   `financial_year` varchar(20) NOT NULL,
   `sales_order_no` varchar(50) NOT NULL,
   `warp_weft_crimp` decimal(18,4) DEFAULT 0.0000,
   `warp_weft_waste` decimal(18,4) DEFAULT 0.0000,
   `req_warp_and_weft_per_kg` decimal(18,4) DEFAULT 0.0000,
   `glm_without_size` decimal(18,4) DEFAULT 0.0000,
   `req_order_quantity` decimal(18,4) DEFAULT 0.0000,
   `count` varchar(55) NOT NULL,
   `count_type` varchar(55) NOT NULL ,
   `is_active` bit(1) DEFAULT b'1',
   `is_delete` bit(1) DEFAULT b'0',
   `created_by` varchar(255) DEFAULT '1',
   `created_on` datetime DEFAULT NULL,
   `modified_by` varchar(255) DEFAULT NULL,
   `modified_on` datetime DEFAULT NULL,
   `deleted_by` varchar(255) DEFAULT NULL,
   `deleted_on` datetime DEFAULT NULL,
   `product_id` bigint(20) NOT NULL,
   PRIMARY KEY (`sales_order_sauda_sheet_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

 ALTER TABLE mt_sales_order_details_trading MODIFY COLUMN weft_waste decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE mt_sales_order_details_trading MODIFY COLUMN warp_waste decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE mt_sales_order_details_trading MODIFY COLUMN weft_crimp decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE mt_sales_order_details_trading MODIFY COLUMN warp_crimp decimal(18,4) DEFAULT 0.0000 NULL;

ALTER TABLE mt_sales_order_details_trading ADD warp_count_1__wc1__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD warp_count_2__wc2__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD warp_count_3__wc3__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD warp_count_4__wc4__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD weft_count_1__fc1__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD weft_count_2__fc2__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD weft_count_3__fc3__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading ADD weft_count_4__fc4__actual_count DECIMAL(18,4) DEFAULT 0;
ALTER TABLE mt_sales_order_details_trading
ADD COLUMN epi_1 DECIMAL(18,4) DEFAULT 0;

ALTER TABLE mt_sales_order_details_trading
ADD COLUMN ppi_1 DECIMAL(18,4) DEFAULT 0;

ALTER TABLE mt_sales_order_details_trading
ADD COLUMN width DECIMAL(18,4) DEFAULT 0;



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
	    `sotv`.`department_name` as `department_name`,
	    `sotv`.`customer_name` as `customer_name`,
	    `mt`.`customer_order_no` as `customer_order_no`,
	    `mt`.`customer_order_Date` as `customer_order_Date`,
	    `sotv`.`customer_state_name` as `customer_state_name`,
	    `sotv`.`consignee_name` as `consignee_name`,
	    `sotv`.`consignee_state_name` as `consignee_state_name`,
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
	    `mt`.`product_material_print_name` as `product_material_print_name`,
	    `rmfgsr`.`product_material_packing_name` as `product_material_packing_name`,
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
	    `sotv`.`customer_email` as `customer_email`,
	    `sotv`.`customer_city_name` as `customer_city_name`,
	    `sotv`.`customer_district_name` as `customer_district_name`,
	    `sotv`.`consignee_city_name` as `consignee_city_name`,
	    `sotv`.`consignee_district_name` as `consignee_district_name`,
	    `sotv`.`approved_by_name` as `approved_by_name`,
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
	    `sotv`.`company_name` as `company_name`,
	    `sotv`.`company_branch_name` as `company_branch_name`,
	    `sotv`.`company_address1` as `company_address1`,
	    `sotv`.`company_address2` as `company_address2`,
	    `sotv`.`company_phone_no` as `company_phone_no`,
	    `sotv`.`company_cell_no` as `company_cell_no`,
	    `sotv`.`company_EmailId` as `company_EmailId`,
	    `sotv`.`company_website` as `company_website`,
	    `sotv`.`company_gst_no` as `company_gst_no`,
	    `sotv`.`company_pan_no` as `company_pan_no`,
	    `sotv`.`company_branch_state` as `company_state`,
	    `sotv`.`company_pincode` as `company_pincode`,
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
	    coalesce((select sum(`mtdispatch`.`actual_dispatch_weight`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) as `dispatched_weight`,
	    (
	    select
	        (
	        select
	            `cnt`.`production_count_id`
	        from
	            `xm_spinning_prod_count` `cnt`
	        where
	            `cnt`.`production_count_id` = `dp`.`product_parameter_value`)
	    from
	        `mtv_sales_order_product_dynamic_parameters` `dp`
	    where
	        `dp`.`product_type_id` = `mt`.`product_type_id`
	        and `dp`.`product_parameter_name` = 'COUNT'
	        and `dp`.`product_id` = `mt`.`product_material_id`
	        and `dp`.`customer_order_no` = `mt`.`customer_order_no`
	        and `dp`.`is_delete` = 0) as `production_count_id`,
	    (
	    select
	        (
	        select
	            `cnt`.`production_count_name`
	        from
	            `xm_spinning_prod_count` `cnt`
	        where
	            `cnt`.`production_count_id` = `dp`.`product_parameter_value`)
	    from
	        `mtv_sales_order_product_dynamic_parameters` `dp`
	    where
	        `dp`.`product_type_id` = `mt`.`product_type_id`
	        and `dp`.`product_parameter_name` = 'COUNT'
	        and `dp`.`product_id` = `mt`.`product_material_id`
	        and `dp`.`customer_order_no` = `mt`.`customer_order_no`
	        and `dp`.`is_delete` = 0) as `production_count_name`
	from
	    (((`mt_sales_order_details_trading` `mt`
	left join `smv_product_rm_fg_sr` `rmfgsr` on
	    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
	left join `mtv_sales_order_master_trading_summary` `sotv` on
	    (`sotv`.`company_branch_id` = `mt`.`company_branch_id`
	        and `sotv`.`company_id` = `mt`.`company_id`
	        and `sotv`.`sales_order_master_transaction_id` = `mt`.`sales_order_master_transaction_id`))
	left join `cm_hsn_sac` `hsn` on
	    (`hsn`.`hsn_sac_id` = `mt`.`product_material_hsn_code_id`
	        and `hsn`.`is_delete` = 0))
	where
	    `mt`.`is_delete` = 0
	order by
	    `mt`.`sales_order_details_transaction_id` desc;
