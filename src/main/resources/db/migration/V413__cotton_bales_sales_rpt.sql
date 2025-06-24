ALTER TABLE pt_cotton_bales_sales_master CHANGE vehical_no vehicle_no varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL;


create or replace
algorithm = UNDEFINED view `ptv_cotton_bales_sales_master` as
select
    `cbsm`.`pt_cotton_bales_sales_master_transaction_id` as `pt_cotton_bales_sales_master_transaction_id`,
    `cbsm`.`company_id` as `company_id`,
    `cbsm`.`financial_year` as `financial_year`,
    `cbsm`.`company_branch_id` as `company_branch_id`,
    `cbsm`.`sales_hsnTax_type` as `sales_hsnTax_type`,
    `cbsm`.`sales_voucher_type` as `sales_voucher_type`,
    `cbsm`.`sales_return_type_id` as `sales_return_type_id`,
    `cbsm`.`sales_return_type` as `sales_return_type`,
    `cbsm`.`sales_return_date` as `sales_return_date`,
    `cbsm`.`sales_return_no` as `sales_return_no`,
    `cbsm`.`customer_id` as `customer_id`,
    `cbsm`.`customer_state_id` as `customer_state_id`,
    `cbsm`.`customer_city_id` as `customer_city_id`,
    `cbsm`.`customer_address` as `customer_address`,
    `cbsm`.`customer_gst_no` as `customer_gst_no`,
    `cbsm`.`customer_cell_no` as `customer_cell_no`,
    `cbsm`.`goods_receipt_no` as `goods_receipt_no`,
    `cbsm`.`purchase_order_no` as `purchase_order_no`,
    `cbsm`.`consignee_id` as `consignee_id`,
    `cbsm`.`consignee_state_id` as `consignee_state_id`,
    `cbsm`.`consignee_city_id` as `consignee_city_id`,
    `cbsm`.`consignee_address` as `consignee_address`,
    `cbsm`.`consignee_gst_no` as `consignee_gst_no`,
    `cbsm`.`consignee_cell_no` as `consignee_cell_no`,
    `cbsm`.`invoice_no` as `invoice_no`,
    `cbsm`.`driver_name` as `driver_name`,
    `cbsm`.`vehicle_no` as `vehicle_no`,
    `cbsm`.`transport_mode` as `transport_mode`,
    `cbsm`.`return_by_id` as `return_by_id`,
    `cbsm`.`sales_return_status` as `sales_return_status`,
    `cbsm`.`approved_by_id` as `approved_by_id`,
    `cbsm`.`approved_date` as `approved_date`,
    `cbsm`.`sales_total` as `sales_total`,
    `cbsm`.`basic_total` as `basic_total`,
    `cbsm`.`freight_amount` as `freight_amount`,
    `cbsm`.`is_freight_taxable` as `is_freight_taxable`,
    `cbsm`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `cbsm`.`taxable_total` as `taxable_total`,
    `cbsm`.`transport_amount` as `transport_amount`,
    `cbsm`.`packing_amount` as `packing_amount`,
    `cbsm`.`other_amount` as `other_amount`,
    `cbsm`.`cgst_total` as `cgst_total`,
    `cbsm`.`sgst_total` as `sgst_total`,
    `cbsm`.`igst_total` as `igst_total`,
    `cbsm`.`roundoff` as `roundoff`,
    `cbsm`.`grand_total` as `grand_total`,
    `e`.`employee_name` as `approved_by_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `cust`.`customer_name` as `customer_name`,
    `cccty`.`city_name` as `cust_city_name`,
    `ccs`.`state_name` as `cust_state_name`,
    `ccctyg`.`city_name` as `consi_city_name`,
    `ccsg`.`state_name` as `consi_state_name`,
    `consi`.`customer_name` as `consignee_name`,
    `e1`.`employee_name` as `return_by_name`,
    case
        `cbsm`.`sales_return_status` when 'A' then 'Returned'
        else 'Pending'
    end as `sales_return_status_desc`,
    case
        when `cbsm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `cbsm`.`is_delete` as `is_delete`,
    `cbsm`.`created_by` as `created_by`,
    `cbsm`.`created_on` as `created_on`,
    `cbsm`.`modified_by` as `modified_by`,
    `cbsm`.`modified_on` as `modified_on`,
    `cbsm`.`deleted_by` as `deleted_by`,
    `cbsm`.`deleted_on` as `deleted_on`
from
    (((((((((`pt_cotton_bales_sales_master` `cbsm`
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `cbsm`.`approved_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_active` = 1
        and `e1`.`is_delete` = 0
        and `e1`.`employee_id` = `cbsm`.`return_by_id`))
left join `cm_company_branch` `v` on
    (`v`.`company_id` = `cbsm`.`company_id`))
left join `cm_customer` `cust` on
    (`cust`.`customer_id` = `cbsm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `cm_customer` `consi` on
    (`consi`.`customer_id` = `cbsm`.`consignee_id`
        and `consi`.`is_delete` = 0))
left join `cm_state` `ccs` on
    (`ccs`.`state_id` = `cbsm`.`customer_state_id`))
left join `cm_city` `cccty` on
    (`cccty`.`city_id` = `cbsm`.`customer_city_id`))
left join `cm_state` `ccsg` on
    (`ccsg`.`state_id` = `cbsm`.`consignee_state_id`))
left join `cm_city` `ccctyg` on
    (`ccctyg`.`city_id` = `cbsm`.`consignee_city_id`))
where
    `cbsm`.`is_delete` = 0;


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
    `rm`.`product_rm_id` as `product_rm_id`,
    `pcbsd`.`batch_no` as `batch_no`,
    `pcbsd`.`supplier_batch_no` as `supplier_batch_no`,
    `pcbsd`.`stock_quantity` as `stock_quantity`,
    `pcbsd`.`stock_weight` as `stock_weight`,
    `pcbsd`.`sales_return_quantity` as `sales_return_quantity`,
    `pcbsd`.`sales_return_weight` as `sales_return_weight`,
    `pcbsd`.`sales_return_rate` as `sales_return_rate`,
    `pcbsd`.`product_purchase_unit_id` as `product_purchase_unit_id`,
    `pcbsd`.`material_discount_percent` as `material_discount_percent`,
    `pcbsd`.`material_discount_amount` as `material_discount_amount`,
    `pcbsd`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
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
    (((((((`pt_cotton_bales_sales_details` `pcbsd`
left join `pt_cotton_bales_sales_master` `pcbsm` on
    (`pcbsd`.`pt_cotton_bales_sales_master_transaction_id` = `pcbsm`.`pt_cotton_bales_sales_master_transaction_id`
        and `pcbsd`.`company_id` = `pcbsm`.`company_id`
        and `pcbsd`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `pcbsd`.`approved_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_active` = 1
        and `e1`.`is_delete` = 0
        and `e1`.`employee_id` = `pcbsd`.`return_by_id`))
left join `cm_customer` `cust` on
    (`cust`.`customer_id` = `pcbsd`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_code` = `pcbsd`.`product_material_code`))
left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pcbsd`.`godown_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pcbsd`.`company_branch_id`
        and `vb`.`company_id` = `pcbsd`.`company_id`))
where
    `pcbsd`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `ptv_cotton_bales_sales_master_rpt` as
select
    concat(ifnull(`v`.`sales_return_no`, ''), ':Sales Return No:Y:T') as `sales_return_no`,
    concat(ifnull(`v`.`sales_return_date`, ''), ':Sales Return Date:Y:D:') as `sales_return_date`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T:') as `goods_receipt_no`,
    concat(ifnull(`v`.`sales_hsnTax_type`, ''), ':Sales HSN Tax:Y:T:') as `sales_hsnTax_type`,
    concat(ifnull(`v`.`sales_voucher_type`, ''), ':Sales Voucher Type:Y:T:') as `sales_voucher_type`,
    concat(ifnull(`v`.`sales_return_type`, ''), ':Sales Return Type:Y:T:') as `sales_return_type`,
    concat(ifnull(`v`.`sales_return_status_desc`, ''), ':Sales Return Status:Y:T:') as `sales_return_status_desc`,
    concat(ifnull(`v`.`return_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `return_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`invoice_no`, ''), ':Invoice No:Y:T:') as `invoice_no`,
    concat(ifnull(`v`.`driver_name`, ''), ':Driver Name:Y:T:') as `driver_name`,
    concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
    concat(ifnull(`v`.`transport_mode`, ''), ':Transport Mode:Y:T:') as `transport_mode`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:F') as `customer_name`,
    concat(ifnull(`v`.`customer_address`, ''), ':Customer Address:Y:T:') as `customer_address`,
    concat(ifnull(`v`.`consignee_name`, ''), ':Consignee Name:Y:C:cmv_customer:F') as `consignee_name`,
    concat(ifnull(`v`.`consignee_address`, ''), ':Customer Address:Y:T:') as `consignee_address`,
    concat(ifnull(`v`.`sales_total`, ''), ':Sales Total:O:N:') as `sales_total`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') as `packing_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') as `taxable_total`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') as `other_amount`,
    concat(ifnull(`v`.`cgst_total`, ''), ':Cgst Total:O:N:') as `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':Sgst Total:O:N:') as `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':Igst Total:O:N:') as `igst_total`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') as `grand_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':Roundoff:O:N:') as `roundoff`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`pt_cotton_bales_sales_master_transaction_id`, ''), ':Sales Master Transaction Id:O:N:') as `pt_cotton_bales_sales_master_transaction_id`
from
    `ptv_cotton_bales_sales_master` `v`
limit 1;


create or replace
algorithm = UNDEFINED view `ptv_cotton_bales_sales_details_rpt` as
select
    concat(ifnull(`v`.`sales_return_no`, ''), ':Sales Return No:Y:T') as `sales_return_no`,
    concat(ifnull(`v`.`sales_return_date`, ''), ':Sales Return Date:Y:D:') as `sales_return_date`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T:') as `goods_receipt_no`,
    concat(ifnull(`v`.`sales_hsnTax_type`, ''), ':Sales HSN Tax:Y:T:') as `sales_hsnTax_type`,
    concat(ifnull(`v`.`sales_voucher_type`, ''), ':Sales Voucher Type:Y:T:') as `sales_voucher_type`,
    concat(ifnull(`v`.`sales_return_type`, ''), ':Sales Return Type:Y:T:') as `sales_return_type`,
    concat(ifnull(`v`.`sales_return_status_desc`, ''), ':Sales Return Status:Y:T:') as `sales_return_status_desc`,
    concat(ifnull(`v`.`return_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `return_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':QA By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:F') as `customer_name`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:O:N:') as `product_material_name`,
    concat(ifnull(`v`.`batch_no`, ''), ':Batch No:O:N:') as `batch_no`,
    concat(ifnull(`v`.`supplier_batch_no`, ''), ':Supplier Batch No:O:N:') as `supplier_batch_no`,
    concat(ifnull(`v`.`stock_quantity`, ''), ':Stock Qty.:O:N:') as `stock_quantity`,
    concat(ifnull(`v`.`stock_weight`, ''), ':Stock Wt.:O:N:') as `stock_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Qty.:O:N:') as `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Wt.:O:N:') as `sales_return_weight`,
    concat(ifnull(`v`.`sales_return_rate`, ''), ':Sales Return Rate:O:N:') as `sales_return_rate`,
    concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') as `material_discount_percent`,
    concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') as `material_discount_amount`,
    concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') as `material_basic_amount`,
    concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
    concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') as `material_cgst_total`,
    concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
    concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') as `material_sgst_total`,
    concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') as `material_igst_percent`,
    concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') as `material_igst_total`,
    concat(ifnull(`v`.`material_freight_amount`, ''), ':Material Freight Amount:O:N:') as `material_freight_amount`,
    concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') as `material_total_amount`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`press_running_no_from`, ''), ':Press No Running From:Y:T:') as `press_running_no_from`,
    concat(ifnull(`v`.`press_running_no_to`, ''), ':Press No Running To:Y:T:') as `press_running_no_to`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`pt_cotton_bales_sales_master_transaction_id`, ''), ':Sales Master Transaction Id:O:N:') as `pt_cotton_bales_sales_master_transaction_id`
from
    `ptv_cotton_bales_sales_details` `v`
limit 1;