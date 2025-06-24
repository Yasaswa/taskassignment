
-- add product raw materials urls

 INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
 	 (1,1,1,16,2,10,'Goods Receipt Notes',1,'Goods Receipt Notes(Product Raw Materials)','Purchase','<FrmGoodsReceiptNoteListing />','import FrmGoodsReceiptNoteListing from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteListing";','/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteListing','<FrmGoodsReceiptNoteEntry />','import FrmGoodsReceiptNoteEntry from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteEntry";','/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteEntry',NULL,'Goods Receipt Notes',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'PRM'),
 	 (1,1,1,16,6,27,'Goods Receipt Notes',4,'Goods Receipt Notes(Product Raw Materials)','Registers','<FrmGoodsReceiptNoteListing compType="Register" />',' ','/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteListing/reg','','','',NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'PRM');






-- update grn master with po no and date version

UPDATE pt_goods_receipt_details pt
SET
    pt.goods_receipt_type_id = (
        SELECT pmm.product_type_id
        FROM smv_product_rm pmm
        WHERE pmm.product_rm_id = pt.product_material_id
    ),
    pt.goods_receipt_type = (
        SELECT pmm.product_type_name
        FROM smv_product_rm pmm
        WHERE pmm.product_rm_id = pt.product_material_id
    )
WHERE EXISTS (
    SELECT 1
    FROM smv_product_rm pmm
    WHERE pmm.product_rm_id = pt.product_material_id
);





UPDATE pt_goods_receipt_master pt
SET
    pt.goods_receipt_type_id = (
        SELECT MAX(pmm.goods_receipt_type_id)
        FROM pt_goods_receipt_details pmm
        WHERE pmm.goods_receipt_no = pt.goods_receipt_no
    ),
    pt.goods_receipt_type = (
        SELECT MAX(pmm.goods_receipt_type)
        FROM pt_goods_receipt_details pmm
        WHERE pmm.goods_receipt_no = pt.goods_receipt_no
    )
WHERE EXISTS (
    SELECT 1
    FROM pt_goods_receipt_details pmm
    WHERE pmm.goods_receipt_no = pt.goods_receipt_no
);



UPDATE pt_goods_receipt_master pt
SET
    pt.purchase_order_no = (
        SELECT MAX(purchase_order_no)
        FROM pt_goods_receipt_details pmm
        WHERE pmm.goods_receipt_no = pt.goods_receipt_no
    ),
    pt.purchase_order_version = (
        SELECT MAX(purchase_order_version)
        FROM pt_goods_receipt_details pmm
        WHERE pmm.goods_receipt_no = pt.goods_receipt_no
    ),
    pt.purchase_order_date = (
        SELECT MAX(purchase_order_date)
        FROM pt_goods_receipt_details pmm
        WHERE pmm.goods_receipt_no = pt.goods_receipt_no
    )
WHERE EXISTS (
    SELECT 1
    FROM pt_goods_receipt_details pmm
    WHERE pmm.goods_receipt_no = pt.goods_receipt_no
);




-- ptv_goods_receipt_summary source

create or REPLACE algorithm = UNDEFINED view `ptv_goods_receipt_summary` as
select
    `sup`.`supp_branch_name` as `supplier_name`,
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
        distinct `po1`.`indent_no`
    from
        `pt_purchase_order_details` `po1`
    where
        `po1`.`purchase_order_no` in (
        select
            distinct `pd1`.`purchase_order_no`
        from
            `pt_goods_receipt_details` `pd1`
        where
            `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`)) as `indent_no`,
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
    `pt`.`purchase_order_no`   as `purchase_order_no`,
    `pt`.`purchase_order_date`  as `purchase_order_date`,
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
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
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
    `sup`.`supp_branch_payment_term_id` as `payment_term_id`,
    `p`.`product_type_short_name` as `product_type_short_name`
from
    (((((((((`pt_goods_receipt_master` `pt`
left join `cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `pt`.`supplier_id`
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
where
    `pt`.`is_delete` = 0;


-- ptv_goods_receipt_details source

create or REPLACE algorithm = UNDEFINED view `ptv_goods_receipt_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `pt`.`goods_receipt_date` as `goods_receipt_date`,
    `pt`.`goods_receipt_version` as `goods_receipt_version`,
    case
        `pt`.`grn_item_status` when 'G' then 'GRN Done'
        when 'Q' then 'QC Done'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'GRN'
    end as `grn_item_status_desc`,
    `d`.`department_name` as `department_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    case
        `po`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end as `purchase_order_item_status_desc`,
    `cu`.`customer_name` as `customer_name`,
    `pt`.`customer_order_no` as `customer_order_no`,
    `pt`.`customer_order_Date` as `customer_order_Date`,
    `grm`.`purchase_order_life` as `purchase_order_life`,
    `grm`.`supplier_challan_no` as `supplier_challan_no`,
    `grm`.`supplier_challan_date` as `supplier_challan_date`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `ct`.`city_name` as `supplier_city_name`,
    `ss`.`state_name` as `supplier_state_name`,
    `pt`.`item_qa_by_id` as `item_qa_by_id`,
    `pt`.`item_qa_date` as `item_qa_date`,
    `pt`.`goods_receipt_type` as `goods_receipt_type`,
    `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `pt`.`lead_time` as `lead_time`,
    `pt`.`sr_no` as `sr_no`,
    `pt`.`batch_no` as `batch_no`,
    `po`.`purchase_order_item_status` as `purchase_order_item_status`,
    `rm_fg`.`product_material_name` as `product_material_name`,
    `u`.`product_unit_name` as `product_material_stock_unit_name`,
    `rm_fg`.`product_material_std_weight` as `product_material_std_weight`,
    `rm_fg`.`product_material_category1_name` as `product_category1_name`,
    `rm_fg`.`product_material_category2_name` as `product_category2_name`,
    `pk`.`product_packing_name` as `product_packing_name`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
    `pt`.`product_material_grn_quantity` as `product_material_grn_quantity`,
    `pt`.`product_material_grn_weight` as `product_material_grn_weight`,
    `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
    `pt`.`product_material_grn_rejected_quantity` as `product_material_grn_rejected_quantity`,
    `pt`.`product_material_grn_rejected_weight` as `product_material_grn_rejected_weight`,
    `rp`.`product_rejection_type` as `product_rejection_type`,
    `rp`.`product_rejection_parameters_name` as `product_rejection_parameters_name`,
    `rp`.`product_rejection_parameters_short_name` as `product_rejection_parameters_short_name`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_weight`,
    `so`.`sales_order_no` as `sales_order_no`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_prev_accepted_weight`,
    `pt`.`material_rate` as `material_rate`,
    `pt`.`material_basic_amount` as `material_basic_amount`,
    `pt`.`material_freight_amount` as `material_freight_amount`,
    `pt`.`material_discount_percent` as `material_discount_percent`,
    `pt`.`material_discount_amount` as `material_discount_amount`,
    `pt`.`material_taxable_amount` as `material_taxable_amount`,
    `pt`.`material_cgst_percent` as `material_cgst_percent`,
    `pt`.`material_cgst_total` as `material_cgst_total`,
    `pt`.`material_sgst_percent` as `material_sgst_percent`,
    `pt`.`material_sgst_total` as `material_sgst_total`,
    `pt`.`material_igst_percent` as `material_igst_percent`,
    `pt`.`material_igst_total` as `material_igst_total`,
    `pt`.`material_total_amount` as `material_total_amount`,
    `pt`.`material_schedule_date` as `material_schedule_date`,
    `pt`.`grn_item_status` as `grn_item_status`,
    `pt`.`excess_quantity` as `excess_quantity`,
    `pt`.`excess_weight` as `excess_weight`,
    `pt`.`pree_closed_grn_quantity` as `pree_closed_grn_quantity`,
    `pt`.`pree_closed_grn_weight` as `pree_closed_grn_weight`,
    `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `pt`.`purchase_return_weight` as `purchase_return_weight`,
    `pt`.`production_issue_quantity` as `production_issue_quantity`,
    `pt`.`production_issue_weight` as `production_issue_weight`,
    `pt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `pt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `pt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `pt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `pt`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `pt`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `pt`.`sales_quantity` as `sales_quantity`,
    `pt`.`sales_weight` as `sales_weight`,
    `pt`.`sales_return_quantity` as `sales_return_quantity`,
    `pt`.`sales_return_weight` as `sales_return_weight`,
    `pt`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `pt`.`sales_rejection_weight` as `sales_rejection_weight`,
    `pt`.`transfer_issue_weight` as `transfer_issue_weight`,
    `pt`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `pt`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `pt`.`outsources_out_quantity` as `outsources_out_quantity`,
    `pt`.`outsources_out_weight` as `outsources_out_weight`,
    `pt`.`outsources_in_quantity` as `outsources_in_quantity`,
    `pt`.`outsources_in_weight` as `outsources_in_weight`,
    `pt`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `pt`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `pt`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `pt`.`loan_receipt_weight` as `loan_receipt_weight`,
    `pt`.`loan_issue_quantity` as `loan_issue_quantity`,
    `pt`.`loan_issue_weight` as `loan_issue_weight`,
    `pt`.`cancel_quantity` as `cancel_quantity`,
    `pt`.`cancel_weight` as `cancel_weight`,
    `pt`.`difference_quantity` as `difference_quantity`,
    `pt`.`difference_weight` as `difference_weight`,
    `pt`.`total_box_weight` as `total_box_weight`,
    `pt`.`total_quantity_in_box` as `total_quantity_in_box`,
    `pt`.`weight_per_box_item` as `weight_per_box_item`,
    `pt`.`no_of_boxes` as `no_of_boxes`,
    `pt`.`remark` as `remark`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`expiry_date` as `expiry_date`,
    `po`.`indent_no` as `indent_no`,
    `e1`.`employee_name` as `indented_by_name`,
    `e2`.`employee_name` as `approved_by_name`,
    `g`.`godown_name` as `godown_name`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `pdt`.`product_type_group` as `product_material_type_group`,
    `pdt`.`product_type_name` as `product_type_name`,
    `pdt`.`product_type_short_name` as `product_type_short_name`,
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
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`goods_receipt_details_transaction_id` as `goods_receipt_details_transaction_id`,
    `pt`.`goods_receipt_master_transaction_id` as `goods_receipt_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
    `pt`.`department_id` as `department_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`product_material_rejection_reason_id` as `product_material_rejection_reason_id`,
    `grm`.`goods_receipt_status` as `goods_receipt_status`,
    `grm`.`supplier_id` as `supplier_id`,
    `grm`.`approved_by_id` as `approved_by_id`,
    `grm`.`supplier_state_id` as `supplier_state_id`,
    `grm`.`supplier_city_id` as `supplier_city_id`,
    `grm`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `grm`.`expected_branch_id` as `expected_branch_id`,
    `grm`.`expected_branch_state_id` as `expected_branch_state_id`,
    `grm`.`expected_branch_city_id` as `expected_branch_city_id`,
    `sup`.`supp_branch_payment_term_id` as `payment_term_id`,
    `grm`.`agent_id` as `agent_id`,
    `pt`.`product_material_id` as `product_material_id`,
    `pt`.`godown_id` as `godown_id`,
    `pt`.`godown_section_id` as `godown_section_id`,
    `pt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `po`.`purchase_order_type_id` as `product_type_id`,
    `rm_fg`.`product_material_category1_id` as `product_category1_id`,
    `rm_fg`.`product_material_category2_id` as `product_category2_id`,
    `po`.`indented_by_id` as `indent_by_id`
from
    (((((((((((((((((((((`pt_goods_receipt_details` `pt`
left join `cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join `pt_goods_receipt_master` `grm` on
    (`grm`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
        and `grm`.`company_id` = `pt`.`company_id`
        and `grm`.`company_branch_id` = `pt`.`company_branch_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `grm`.`supplier_city_id`))
left join `cm_state` `ss` on
    (`ss`.`state_id` = `grm`.`supplier_state_id`))
left join `pt_purchase_order_details` `po` on
    (`po`.`company_branch_id` = `pt`.`company_branch_id`
        and `po`.`company_id` = `pt`.`company_id`
        and `po`.`product_material_id` = `pt`.`product_material_id`
        and `po`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `po`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `po`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `pt`.`product_material_unit_id`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm_fg` on
    (`rm_fg`.`product_material_id` = `pt`.`product_material_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `po`.`purchase_order_type_id`
        and `pdt`.`is_delete` = 0))
left join `sm_product_packing` `pk` on
    (`pk`.`product_packing_id` = `pt`.`product_material_packing_id`
        and `pk`.`is_delete` = 0))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`
        and `hsn`.`is_delete` = 0))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `po`.`indented_by_id`
        and `e1`.`is_delete` = 0))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `grm`.`approved_by_id`
        and `e2`.`is_delete` = 0))
left join `cm_customer` `cu` on
    (`cu`.`customer_id` = `po`.`customer_id`
        and `cu`.`is_delete` = 0))
left join `cm_department` `d` on
    (`d`.`department_id` = `pt`.`department_id`))
left join `sm_product_rejection_parameters` `rp` on
    (`rp`.`company_id` = `pt`.`company_id`
        and `rp`.`product_rejection_parameters_id` = `pt`.`product_material_rejection_reason_id`))
left join `mt_sales_order_master_trading` `so` on
    (`so`.`company_id` = `pt`.`company_id`
        and `so`.`customer_order_no` = `pt`.`customer_order_no`))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `pt`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `pt`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
where
    `pt`.`is_delete` = 0;
