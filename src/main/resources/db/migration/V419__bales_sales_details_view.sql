
create or replace
algorithm = UNDEFINED view `ptv_cotton_bales_sales_details` as
select
    `pcbsd`.`pt_cotton_bales_sales_details_transaction_id` as `pt_cotton_bales_sales_details_transaction_id`,
    `pcbsd`.`pt_cotton_bales_sales_master_transaction_id` as `pt_cotton_bales_sales_master_transaction_id`,
    `pcbsd`.`company_id` as `company_id`,
    `pcbsd`.`financial_year` as `financial_year`,
    `pcbsd`.`company_branch_id` as `company_branch_id`,
    `pcbsd`.`sales_hsnTax_type` as `sales_hsnTax_type`,
    `pcbsd`.`sales_voucher_type` as `sales_voucher_type`,
    `pcbsd`.`sales_dispatch_type` as `sales_dispatch_type`,
    `pcbsd`.`dispatch_sales_type` as `dispatch_sales_type`,
    `pcbsm`.`sales_return_type_id` as `sales_return_type_id`,
    `pcbsm`.`sales_return_type` as `sales_return_type`,
    `pcbsd`.`sales_return_date` as `sales_return_date`,
    `pcbsd`.`sales_return_no` as `sales_return_no`,
    `pcbsd`.`customer_id` as `customer_id`,
    `pcbsd`.`consignee_id` as `consignee_id`,
    `pcbsd`.`return_by_id` as `return_by_id`,
    `pcbsd`.`sales_return_status` as `sales_return_status`,
    `pcbsd`.`approved_by_id` as `approved_by_id`,
    `pcbsd`.`approved_date` as `approved_date`,
    `pcbsd`.`goods_receipt_no` as `goods_receipt_no`,
    `pcbsd`.`purchase_order_no` as `purchase_order_no`,
    `pcbsd`.`product_material_code` as `product_material_code`,
    `pcbsd`.`product_material_name` as `product_material_name`,
    `pcbsd`.`product_material_id` as `product_material_id`,
    `pcbsd`.`batch_no` as `batch_no`,
    `pcbsd`.`supplier_batch_no` as `supplier_batch_no`,
    `pcbsd`.`stock_quantity` as `stock_quantity`,
    `pcbsd`.`stock_weight` as `stock_weight`,
    `pcbsd`.`sales_return_quantity` as `sales_return_quantity`,
    `pcbsd`.`sales_return_weight` as `sales_return_weight`,
    `pcbsd`.`sales_return_rate` as `sales_return_rate`,
    `pcbsd`.`product_rm_std_weight` as `product_rm_std_weight`,
    `pcbsd`.`product_purchase_unit_id` as `product_purchase_unit_id`,
    `unit`.`product_unit_name` as `product_unit_name`,
    `pcbsd`.`material_discount_percent` as `material_discount_percent`,
    `pcbsd`.`material_discount_amount` as `material_discount_amount`,
    `pcbsd`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `pcbsd`.`product_hsn_sac_rate` as `product_hsn_sac_rate`,
    `pcbsd`.`material_taxable_amount` as `material_taxable_amount`,
    `pcbsd`.`material_basic_amount` as `material_basic_amount`,
    `pcbsd`.`material_cgst_percent` as `material_cgst_percent`,
    `pcbsd`.`material_cgst_total` as `material_cgst_total`,
    `pcbsd`.`material_sgst_percent` as `material_sgst_percent`,
    `pcbsd`.`material_sgst_total` as `material_sgst_total`,
    `pcbsd`.`material_igst_percent` as `material_igst_percent`,
    `pcbsd`.`material_igst_total` as `material_igst_total`,
    `pcbsd`.`material_freight_amount` as `material_freight_amount`,
    `pcbsd`.`material_total_amount` as `material_total_amount`,
    `pcbsd`.`sales_return_remark` as `sales_return_remark`,
    `pcbsd`.`godown_id` as `godown_id`,
    `pcbsd`.`press_running_no_from` as `press_running_no_from`,
    `pcbsd`.`press_running_no_to` as `press_running_no_to`,
    `cust`.`customer_name` as `customer_name`,
    `e`.`employee_name` as `approved_by_name`,
    `e1`.`employee_name` as `return_by_name`,
    `gs`.`godown_name` as `godown_name`,
    `vb`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`company_address1` as `company_address1`,
    `vb`.`company_address2` as `company_address2`,
    `vb`.`company_phone_no` as `company_phone_no`,
    `vb`.`company_cell_no` as `company_cell_no`,
    `vb`.`company_EmailId` as `company_EmailId`,
    `vb`.`company_website` as `company_website`,
    `vb`.`company_gst_no` as `company_gst_no`,
    `vb`.`company_pan_no` as `company_pan_no`,
    `vb`.`company_pincode` as `company_pincode`,
    `ccb`.`company_branch_bank_name` as `company_branch_bank_name`,
    `ccb`.`company_branch_bank_account_no` as `company_branch_bank_account_no`,
    `ccb`.`company_branch_bank_ifsc_code` as `company_branch_bank_ifsc_code`,
    case
        `pcbsd`.`sales_return_status` when 'A' then 'Returned'
        else 'Pending'
    end as `sales_return_status_desc`,
    case
        when `pcbsd`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pcbsd`.`is_delete` as `is_delete`,
    `pcbsd`.`created_by` as `created_by`,
    `pcbsd`.`created_on` as `created_on`,
    `pcbsd`.`modified_by` as `modified_by`,
    `pcbsd`.`modified_on` as `modified_on`,
    `pcbsd`.`deleted_by` as `deleted_by`,
    `pcbsd`.`deleted_on` as `deleted_on`
from
    ((((((((`pt_cotton_bales_sales_details` `pcbsd`
left join `pt_cotton_bales_sales_master` `pcbsm` on
    (`pcbsd`.`pt_cotton_bales_sales_master_transaction_id` = `pcbsm`.`pt_cotton_bales_sales_master_transaction_id`
        and `pcbsd`.`company_id` = `pcbsm`.`company_id`
        and `pcbsd`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `pcbsd`.`approved_by_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pcbsd`.`product_material_hsn_code_id` and `hsn`.`is_delete` = 0))
left join `cm_employee` `e1` on
    (`e1`.`is_active` = 1
        and `e1`.`is_delete` = 0
        and `e1`.`employee_id` = `pcbsd`.`return_by_id`))
left join `cm_customer` `cust` on
    (`cust`.`customer_id` = `pcbsd`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `sm_product_unit` `unit` on
    (`unit`.`product_unit_id` = `pcbsd`.`product_purchase_unit_id` and `unit`.`company_id` = `pcbsd`.`company_id` and `unit`.`is_delete` = 0)
left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pcbsd`.`godown_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pcbsd`.`company_branch_id`
        and `vb`.`company_id` = `pcbsd`.`company_id`))
left join `cm_company_banks` `ccb` on 
    (`ccb`.`company_id` = `pcbsd`.`company_id` and `ccb`.`company_branch_id` = `pcbsd`.`company_branch_id` and `ccb`.`is_delete` = 0))
where
    `pcbsd`.`is_delete` = 0;