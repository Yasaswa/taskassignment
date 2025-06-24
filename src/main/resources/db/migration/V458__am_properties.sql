INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'YardValueForDispatchFabric',1,0,NULL,NULL,NULL,NULL,NULL,NULL,''),
	 (2,'TareWeightForDispatchFabric',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'');

INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
     	 (205,'YardValueForDispatchFabric',2,'Yard','1.09361',1,0,'dakshabhiadmin','2025-05-27 14:38:40.000',NULL,'2025-05-27 14:38:40.000',NULL,NULL,'',''),
     	 (206,'TareWeightForDispatchFabric',2,'Tare Weight','0.5',1,0,'dakshabhiadmin','2025-05-27 14:42:27.000',NULL,'2025-05-27 14:42:27.000',NULL,NULL,'','');

ALTER TABLE mt_dispatch_inspection_details_trading ADD width decimal(18,4) NULL;

-- mtv_dispatch_inspection_details_trading source

create or replace
algorithm = UNDEFINED view `mtv_dispatch_inspection_details_trading` as
select
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `c`.`customer_name` as `customer_name`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_Date` as `sales_order_Date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `rmfgsr`.`product_fg_name` as `product_material_name`,
    `rmfgsr`.`product_fg_name` as `product_material_tech_spect`,
    `u`.`product_unit_name` as `product_unit_name`,
    `mt`.`inspection_production_code` as `inspection_production_code`,
    `mt`.`inspection_order_no` as `inspection_order_no`,
    `mt`.`inspection_production_date` as `inspection_production_date`,
    `mt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `mt`.`inspection_mtr` as `inspection_mtr`,
    `mt`.`dispatch_quantity` as `dispatch_quantity`,
    `mt`.`sort_no` as `sort_no`,
    `mt`.`roll_no` as `roll_no`,
    `mt`.`remark` as `remark`,
    `mt`.`glm` as `glm`,
    `mt`.`style` as `style`,
    `mt`.`width` as `width`,
    `mt`.`dispatch_challan_remark` as `dispatch_challan_remark`,
    `v`.`company_name` as `company_name`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`dispatch_challan_master_transaction_id` as `dispatch_challan_master_transaction_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`godown_id` as `godown_id`,
    `gd`.`godown_name` as `godown_name`,
    `mt`.`dispatch_inspection_details_transaction_id` as `dispatch_inspection_details_transaction_id`,
    `mt`.`weaving_production_inspection_details_id` as `weaving_production_inspection_details_id`,
    `mt`.`dispatch_weight` as `dispatch_weight`
from
    (((((`mt_dispatch_inspection_details_trading` `mt`
left join `cm_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `sm_product_fg` `rmfgsr` on
    (`rmfgsr`.`product_fg_id` = `mt`.`product_material_id`
        and `rmfgsr`.`company_id` = `mt`.`company_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `mt`.`product_material_unit_id`
        and `u`.`company_id` = `mt`.`company_id`))
left join `cmv_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `mt`.`godown_id`
        and `gd`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;