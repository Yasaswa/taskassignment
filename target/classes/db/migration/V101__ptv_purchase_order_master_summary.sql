-- ptv_purchase_order_master_summary source

create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_master_summary` as
select
    case
        `pt`.`purchase_order_creation_type` when 'M' then 'Manual'
        when 'R' then 'Reorder Based'
        else 'Auto'
    end as `purchase_order_creation_type_desc`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    case
        when `pt`.`purchase_order_status` = 'A' then 'Approved'
        when `pt`.`purchase_order_status` = 'R' then 'Rejected'
        when `pt`.`purchase_order_status` = 'I' then 'Partial Receipt'
        when `pt`.`purchase_order_status` = 'C' then 'Completed'
        when `pt`.`purchase_order_status` = 'X' then 'Canceled'
        when `pt`.`purchase_order_status` = 'Z' then 'Pree Closed'
        else 'Pending'
    end as `purchase_order_status_desc`,
    case
        `pt`.`purchase_order_life` when 'C' then 'Close'
        else 'Open'
    end as `purchase_order_life_desc`,
    case
        `pt`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `purchase_order_mail_sent_status_desc`,
    `s`.`supp_branch_name` as `supplier_name`,
    `st`.`state_name` as `state_name`,
    `s`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `s`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `cb`.`company_branch_name` as `expected_branch_name`,
    `st1`.`state_name` as `expected_branch_state_name`,
    `e`.`employee_name` as `approved_by`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`expected_schedule_date` as `expected_schedule_date`,
    `pt`.`quotation_no` as `quotation_no`,
    `pt`.`quotation_date` as `quotation_date`,
    `pt`.`basic_total` as `basic_total`,
    `pt`.`transport_amount` as `transport_amount`,
    `pt`.`freight_amount` as `freight_amount`,
    `pt`.`is_freight_taxable` as `is_freight_taxable`,
    `pt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `pt`.`packing_amount` as `packing_amount`,
    `pt`.`po_discount_percent` as `po_discount_percent`,
    `pt`.`po_discount_amount` as `po_discount_amount`,
    `pt`.`other_amount` as `other_amount`,
    `pt`.`taxable_total` as `taxable_total`,
    `pt`.`cgst_total` as `cgst_total`,
    `pt`.`sgst_total` as `sgst_total`,
    `pt`.`igst_total` as `igst_total`,
    `pt`.`roundoff` as `roundoff`,
    `pt`.`grand_total` as `grand_total`,
    `a`.`agent_name` as `agent_name`,
    `pt`.`agent_percent` as `agent_percent`,
    case
        `pt`.`agent_paid_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `agent_paid_status_desc`,
    case
        convert(`pt`.`purchase_order_acceptance_status`
            using utf8mb4)
        when 'A' then 'Approved'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `purchase_order_acceptance_status_desc`,
    `pt`.`purchase_order_acceptance_status` as `purchase_order_acceptance_status`,
    `pt`.`purchase_order_mail_sent_status` as `purchase_order_mail_sent_status`,
    `pt`.`status_remark` as `status_remark`,
    `pt`.`other_terms_conditions` as `other_terms_conditions`,
    `s`.`supp_branch_type` as `supplier_type`,
    `s`.`supp_branch_address1` as `supp_branch_address1`,
    `s`.`supp_branch_pincode` as `supp_branch_pincode`,
    `ct`.`city_name` as `city_name`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_short_name` as `expected_branch_short_name`,
    `cb`.`branch_address1` as `expected_branch_address1`,
    `cb`.`branch_pincode` as `expected_branch_pincode`,
    `ct1`.`city_name` as `expected_branch_city_name`,
    `cb`.`branch_phone_no` as `expected_branch_phone_no`,
    `cb`.`branch_EmailId` as `expected_branch_EmailId`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`purchase_process_entry` as `purchase_process_entry`,
    `pt`.`purchase_order_type` as `purchase_order_type`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `pt`.`purchase_order_life` as `purchase_order_life`,
    `pt`.`purchase_order_status` as `purchase_order_status`,
    `pt`.`grn_status` as `grn_status`,
    case
        `pt`.`purchase_process_entry` when 'M' then 'Manual'
        else 'Auto'
    end as `purchase_process_entry_desc`,
    `pt`.`purchase_order_creation_type` as `purchase_order_creation_type`,
    `pt`.`gate_pass_no` as `gate_pass_no`,
    `pt`.`gate_pass_date` as `gate_pass_date`,
    `pt`.`vehicale_no` as `vehicale_no`,
    `pt`.`vehicale_type` as `vehicale_type`,
    `pt`.`gross_weight` as `gross_weight`,
    `pt`.`tare_weight` as `tare_weight`,
    `pt`.`net_weight` as `net_weight`,
    `pt`.`variation_weight` as `variation_weight`,
    `pt`.`deduction1_name` as `deduction1_name`,
    `pt`.`deduction1_percent` as `deduction1_percent`,
    `pt`.`deduction1_amount` as `deduction1_amount`,
    `pt`.`deduction2_name` as `deduction2_name`,
    `pt`.`deduction2_percent` as `deduction2_percent`,
    `pt`.`deduction2_amount` as `deduction2_amount`,
    `pt`.`deduction3_name` as `deduction3_name`,
    `pt`.`deduction3_percent` as `deduction3_percent`,
    `pt`.`deduction3_amount` as `deduction3_amount`,
    `pt`.`deduction4_name` as `deduction4_name`,
    `pt`.`deduction4_percent` as `deduction4_percent`,
    `pt`.`deduction4_amount` as `deduction4_amount`,
    `pt`.`deduction5_name` as `deduction5_name`,
    `pt`.`deduction5_percent` as `deduction5_percent`,
    `pt`.`deduction5_amount` as `deduction5_amount`,
    `e1`.`employee_name` as `grader_by_name`,
    `pt`.`remark` as `remark`,
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
    `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
    `pt`.`supplier_id` as `supplier_id`,
    `pt`.`supplier_state_id` as `supplier_state_id`,
    `pt`.`supplier_city_id` as `supplier_city_id`,
    `pt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pt`.`expected_branch_id` as `expected_branch_id`,
    `pt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    `pt`.`agent_id` as `agent_id`,
    `cc`.`supp_branch_name` as `supplier_cosnignee_name`,
    `cc`.`supp_branch_address1` as `supp_consignee_address1`,
    `cc`.`supp_branch_address2` as `supp_consignee_address2`,
    `cc`.`supp_branch_pincode` as `supp_consignee_pincode`,
    `ct2`.`city_name` as `supp_consignee_city_name`,
    `st2`.`state_name` as `supp_consignee_state_name`,
    `pt`.`consignee_id` as `consignee_id`,
    `pt`.`consignee_state_id` as `consignee_state_id`,
    `pt`.`consignee_city_id` as `consignee_city_id`,
    `pt`.`grader_by_id` as `grader_by_id`,
    `pt`.`deduction5_id` as `deduction5_id`,
    `pt`.`deduction4_id` as `deduction4_id`,
    `pt`.`deduction3_id` as `deduction3_id`,
    `pt`.`deduction2_id` as `deduction2_id`,
    `pt`.`deduction1_id` as `deduction1_id`,
    `pt`.`agent_paid_status` as `agent_paid_status`
from
    (((((((((((((((`pt_purchase_order_master` `pt`
left join `cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `c`.`is_delete` = 0))

left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`is_delete` = 0))

left join `cm_supplier_branch` `s` on
    (`s`.`supp_branch_id` = `pt`.`supplier_id`))

left join `cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))

left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`grader_by_id`))

left join `cm_agent` `a` on
    (`a`.`agent_id` = `pt`.`agent_id`
        and `a`.`company_id` = `pt`.`company_id`))

left join `cm_city` `ct` on
    (`ct`.`city_id` = `pt`.`supplier_city_id`))

left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `pt`.`expected_branch_city_id`))

left join `cm_state` `st` on
    (`st`.`state_id` = `pt`.`supplier_state_id`))

left join `cm_state` `st1` on
    (`st1`.`state_id` = `pt`.`expected_branch_state_id`))

left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`freight_hsn_code_id`))

left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`purchase_order_type_id`))

left join `cm_supplier_branch` `cc` on
    (`cc`.`supp_branch_id` = `pt`.`consignee_id`))

left join `cm_city` `ct2` on
    (`ct2`.`city_id` = `pt`.`consignee_city_id`))

left join `cm_state` `st2` on
    (`st2`.`state_id` = `pt`.`consignee_state_id`))
where
    `pt`.`is_delete` = 0;


-- ptv_purchase_order_master_summary_rpt source

create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_master_summary_rpt` as
select
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') as `purchase_order_version`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending)') as `purchase_order_status_desc`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:T') as `supplier_name`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') as `is_freight_taxable`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') as `packing_amount`,
    concat(ifnull(`v`.`po_discount_percent`, ''), ':PO Discount Percent:O:N:') as `po_discount_percent`,
    concat(ifnull(`v`.`po_discount_amount`, ''), ':PO Discount Amount:O:N:') as `po_discount_amount`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') as `other_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') as `taxable_total`,
    concat(ifnull(`v`.`cgst_total`, ''), ':CGST Total:O:N:') as `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':SGST Total:O:N:') as `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':IGST Total:O:N:') as `igst_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':RoundOff:O:N:') as `roundoff`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') as `grand_total`,
    concat(ifnull(`v`.`quotation_no`, ''), ':Quotation No:Y:T') as `quotation_no`,
    concat(ifnull(`v`.`quotation_date`, ''), ':Quotation Date:Y:D:') as `quotation_date`,
    concat(ifnull(`v`.`approved_by`, ''), ':Approved By:Y:C:cmv_employee_list:F') as `approved_by`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(ifnull(`v`.`purchase_order_acceptance_status`, ''), ':Purchase Order Acceptance Status:Y:H:(Approved,Canceled,Pending)') as `purchase_order_acceptance_status_desc`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') as `purchase_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`purchase_order_creation_type_desc`, ''), ':Purchase Order Creation Type:Y:H:(Manual,Auto)') as `purchase_order_creation_type_desc`,
    concat(ifnull(`v`.`purchase_order_life_desc`, ''), ':Purchase Order Life:Y:H:(Close,Open)') as `purchase_order_life_desc`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:') as `status_remark`,
    concat(ifnull(`v`.`supplier_type`, ''), ':Supplier Type:O:N:') as `supplier_type`,
    concat(ifnull(`v`.`supp_branch_address1`, ''), ':Supplier Branch Address1:O:N:') as `supp_branch_address1`,
    concat(ifnull(`v`.`supp_branch_pincode`, ''), ':Supplier Branch Pincode:O:N:') as `supp_branch_pincode`,
    concat(ifnull(`v`.`city_name`, ''), ':Supplier City Name:O:N:') as `city_name`,
    concat(ifnull(`v`.`state_name`, ''), ':Supplier State Name:O:N:') as `state_name`,
    concat(ifnull(`v`.`supp_branch_phone_no`, ''), ':Supplier Branch Phone No:O:N:') as `supp_branch_phone_no`,
    concat(ifnull(`v`.`supp_branch_EmailId`, ''), ':Supplier Branch EmailId:O:N:') as `supp_branch_EmailId`,
    concat(ifnull(`v`.`expected_branch_name`, ''), ':Company Branch Name:O:N:') as `expected_branch_name`,
    concat(ifnull(`v`.`expected_branch_short_name`, ''), ':Company Branch Short Name:O:N:') as `expected_branch_short_name`,
    concat(ifnull(`v`.`expected_branch_address1`, ''), ':Company Branch Address1:O:N:') as `expected_branch_address1`,
    concat(ifnull(`v`.`expected_branch_pincode`, ''), ':Company Branch Pincode:O:N:') as `expected_branch_pincode`,
    concat(ifnull(`v`.`expected_branch_city_name`, ''), ':Company Branch City Name:O:N:') as `expected_branch_city_name`,
    concat(ifnull(`v`.`expected_branch_state_name`, ''), ':Company Branch State Name:O:N:') as `expected_branch_state_name`,
    concat(ifnull(`v`.`expected_branch_phone_no`, ''), ':Company Branch Phone No:O:N:') as `expected_branch_phone_no`,
    concat(ifnull(`v`.`expected_branch_EmailId`, ''), ':Company Expected Branch EmailId:O:N:') as `expected_branch_EmailId`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') as `agent_percent`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:Y:H:(Approved,Completed,Canceled,Pending)') as `agent_paid_status_desc`,
    concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Freight Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Freight Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction_id:O:N:') as `purchase_order_master_transaction_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') as `purchase_order_status`,
    concat(ifnull(`v`.`grn_status`, ''), ':GRN Status:O:N:') as `grn_status`,
    concat(ifnull(`v`.`purchase_order_creation_type`, ''), ':Purchase Order Creation Type:N:N:') as `purchase_order_creation_type`,
    concat(ifnull(`v`.`purchase_order_life`, ''), ':Purchase Order Life:O:N:') as `purchase_order_life`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:N:N:') as `agent_paid_status`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') as `approved_by_id`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year :O:N:') as `financial_year`
from
    `ptv_purchase_order_master_summary` `v`
limit 1;

-- ptv_purchase_order_details source

create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_details` as
select
    `pt`.`purchase_order_type` as `purchase_order_type`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `pt`.`indent_no` as `indent_no`,
    `pt`.`indent_date` as `indent_date`,
    `pt`.`indent_version` as `indent_version`,
    case
        `pom`.`purchase_order_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `purchase_order_status_desc`,
    `vp`.`department_name` as `department_name`,
    `sdp`.`department_name` as `sub_department_name`,
    case
        `pt`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end as `purchase_order_item_status_desc`,
    case
        `pom`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `purchase_order_mail_sent_status_desc`,
    `pom`.`supplier_name` as `supplier_name`,
    `pom`.`state_name` as `supplier_state_name`,
    `c`.`customer_name` as `customer_name`,
    `e1`.`employee_name` as `indented_by_name`,
    `e`.`employee_name` as `approved_by_name`,
    `pt`.`sr_no` as `sr_no`,
    `pt`.`so_sr_no` as `so_sr_no`,
    `p`.`product_type_name` as `product_type_name`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `p`.`product_type_group` as `product_type_group`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `pt`.`product_material_print_name` as `product_material_print_name`,
    `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `pt`.`lead_time` as `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) as `available_rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) as `available_rawmaterial_stock_weight`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `rmfgsr`.`product_material_make_name` as `product_make_name`,
    `rmfgsr`.`product_material_category1_name` as `product_category1_name`,
    `rmfgsr`.`product_material_category2_name` as `product_category2_name`,
    `rmfgsr`.`product_material_category3_name` as `product_category3_name`,
    `rmfgsr`.`product_material_category4_name` as `product_category4_name`,
    `rmfgsr`.`product_material_category5_name` as `product_category5_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` as `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` as `product_material_our_part_code`,
    `rmfgsr`.`product_material_drawing_no` as `product_material_drawing_no`,
    `rmfgsr`.`product_material_hsn_sac_code` as `product_material_hsn_sac_code`,
    `rmfgsr`.`godown_name` as `godown_name`,
    `rmfgsr`.`godown_short_name` as `godown_short_name`,
    `rmfgsr`.`godown_area` as `godown_area`,
    `rmfgsr`.`godown_section_name` as `godown_section_name`,
    `rmfgsr`.`godown_section_short_name` as `godown_section_short_name`,
    `rmfgsr`.`godown_section_area` as `godown_section_area`,
    `rmfgsr`.`godown_section_beans_name` as `godown_section_beans_name`,
    `rmfgsr`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `rmfgsr`.`godown_section_beans_area` as `godown_section_beans_area`,
    `pt`.`product_material_po_quantity_qunital` as `product_material_po_quantity_qunital`,
    `pt`.`product_material_po_quantity` as `product_material_po_quantity`,
    `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
    `pt`.`product_material_po_weight` as `product_material_po_weight`,
    `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
    `pt`.`product_material_po_rejected_quantity` as `product_material_po_rejected_quantity`,
    `pt`.`product_material_po_rejected_weight` as `product_material_po_rejected_weight`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`customer_order_no` as `customer_order_no`,
    `pt`.`customer_order_Date` as `customer_order_Date`,
    `pt`.`pree_closed_quantity` as `pree_closed_quantity`,
    `pt`.`pree_closed_weight` as `pree_closed_weight`,
    `pt`.`pre_closed_remark` as `pre_closed_remark`,
    `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `pt`.`purchase_return_weight` as `purchase_return_weight`,
    `pt`.`material_rate` as `material_rate`,
    `pt`.`material_basic_amount` as `material_basic_amount`,
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
    `pt`.`material_freight_amount` as `material_freight_amount`,
    `pt`.`pending_quantity` as `pending_quantity`,
    `pt`.`pending_weight` as `pending_weight`,
    `pt`.`excess_quantity` as `excess_quantity`,
    `pt`.`excess_weight` as `excess_weight`,
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
    `pt`.`transfer_issue_quantity` as `transfer_issue_quantity`,
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
    `pt`.`material_po_approval_remark` as `material_po_approval_remark`,
    `pom`.`purchase_order_status` as `purchase_order_status`,
    `pt`.`purchase_order_item_status` as `purchase_order_item_status`,
    `pt`.`grn_item_status` as `grn_item_status`,
    `pom`.`purchase_order_mail_sent_status` as `purchase_order_mail_sent_status`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`remark` as `remark`,
    `cm`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cs`.`state_name` as `company_state`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_address1` as `company_address1`,
    `pt`.`financial_year` as `financial_year`,
    `pom`.`city_name` as `city_name`,
    case
        `pt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `pt`.`is_delete` when 1 then 'Yes'
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
    `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
    `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
    `pom`.`supplier_id` as `supplier_id`,
    `pom`.`supplier_state_id` as `supplier_state_id`,
    `pom`.`supplier_city_id` as `supplier_city_id`,
    `pom`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pom`.`expected_branch_id` as `expected_branch_id`,
    `pom`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pom`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`cost_center_id` as `cost_center_id`,
    `rmfgsr`.`product_material_type_id` as `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` as `product_material_grade_id`,
    `rmfgsr`.`product_material_shape_id` as `product_material_shape_id`,
    `rmfgsr`.`product_material_stock_unit_id` as `product_material_stock_unit_id`,
    `rmfgsr`.`product_material_category1_id` as `product_category1_id`,
    `rmfgsr`.`product_material_category2_id` as `product_category2_id`,
    `rmfgsr`.`product_material_category3_id` as `product_category3_id`,
    `rmfgsr`.`product_material_category4_id` as `product_category4_id`,
    `rmfgsr`.`product_material_category5_id` as `product_category5_id`,
    `rmfgsr`.`godown_id` as `godown_id`,
    `rmfgsr`.`godown_section_id` as `godown_section_id`,
    `rmfgsr`.`godown_section_beans_id` as `godown_section_beans_id`,
    `pt`.`product_material_id` as `product_material_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pt`.`department_id` as `department_id`,
    `pt`.`sub_department_id` as `sub_department_id`,
    `pt`.`indented_by_id` as `indented_by_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_weight`
from
    (((((((((((((`pt_purchase_order_details` `pt`
left join `ptv_purchase_order_master_summary` `pom` on
    (`pom`.`company_branch_id` = `pt`.`company_branch_id`
        and `pom`.`company_id` = `pt`.`company_id`
        and `pom`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `pom`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `pom`.`is_delete` = 0))

left join `cm_company` `cm` on
    (`cm`.`company_id` = `pt`.`company_id`
        and `cm`.`is_delete` = 0))

left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`is_delete` = 0))

left join `cm_state` `cs` on
    (`cs`.`state_id` = `cb`.`branch_state_id`
        and `cs`.`state_id` = 0))

left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`purchase_order_type_id`
    and `p`.`is_delete` = 0))

left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `pt`.`product_material_id`))

left join `sm_product_packing` `pp` on
    (`pp`.`product_packing_id` = `pt`.`product_material_packing_id`))

left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`))

left join `cm_department` `vp` on
    (`vp`.`department_id` = `pt`.`department_id`))

left join `cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))

left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`indented_by_id`
        and `e1`.`company_id` = `pt`.`company_id`))

left join `cm_customer` `c` on
    (`c`.`customer_id` = `pt`.`customer_id`))

left join `cm_department` `sdp` on
    (`sdp`.`department_id` = `pt`.`sub_department_id`))

where
    `pt`.`is_delete` = 0;

-- ptv_purchase_order_details_rpt source

create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_details_rpt` as
select
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') as `purchase_order_version`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Bill Booked)') as `purchase_order_item_status_desc`,
    concat(ifnull(`v`.`purchase_order_type`, ''), ':Purchase Order Type:O:N:') as `purchase_order_type`,
    concat(ifnull(`v`.`product_material_code`, ''), ':Material Code:Y:T') as `product_material_code`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') as `product_material_name`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
    concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Unit Name:O:N:') as `product_material_stock_unit_name`,
    concat(ifnull(`v`.`product_material_po_quantity`, ''), ':Material Po Quantity:O:N:') as `product_material_po_quantity`,
    concat(ifnull(`v`.`product_material_po_weight`, ''), ':Material Po Weight:O:N:') as `product_material_po_weight`,
    concat(ifnull(`v`.`material_rate`, ''), ':Material Rate:O:N:') as `material_rate`,
    concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') as `material_basic_amount`,
    concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') as `material_discount_percent`,
    concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') as `material_discount_amount`,
    concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
    concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') as `material_cgst_total`,
    concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
    concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') as `material_sgst_total`,
    concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') as `material_igst_percent`,
    concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') as `material_igst_total`,
    concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') as `material_total_amount`,
    concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') as `product_material_po_approved_quantity`,
    concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') as `product_material_po_approved_weight`,
    concat(ifnull(`v`.`product_material_po_rejected_quantity`, ''), ':Material Po Rejected Quantity:O:N:') as `product_material_po_rejected_quantity`,
    concat(ifnull(`v`.`product_material_po_rejected_weight`, ''), ':Material Po Rejected Weight:O:N:') as `product_material_po_rejected_weight`,
    concat(ifnull(`v`.`material_po_approval_remark`, ''), ':Material Po Approval Remark:O:N:') as `material_po_approval_remark`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') as `purchase_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`material_schedule_date`, ''), ':Material Schedule Date:Y:D:') as `material_schedule_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T') as `approved_by_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree-Closed Quantity:O:N:') as `pree_closed_quantity`,
    concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree-Closed Weight:O:N:') as `pree_closed_weight`,
    concat(ifnull(`v`.`pre_closed_remark`, ''), ':Pree-Closed Remark:O:N:') as `pre_closed_remark`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
    concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') as `pending_quantity`,
    concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') as `pending_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') as `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') as `transfer_issue_quantity`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') as `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') as `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') as `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') as `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsourcess Out Weight:O:N:') as `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') as `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') as `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') as `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') as `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') as `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') as `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') as `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') as `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') as `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') as `cancel_weight`,
    concat(ifnull(`v`.`prev_grn_quantity`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_quantity`,
    concat(ifnull(`v`.`prev_grn_weight`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:T') as `supplier_name`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:O:N:') as `supplier_state_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:N:') as `customer_name`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indented By:Y:T') as `indented_by_name`,
    concat(ifnull(`v`.`sr_no`, ''), ':Sr No:O:N:') as `sr_no`,
    concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') as `so_sr_no`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') as `product_type_group`,
    concat(ifnull(`v`.`product_material_print_name`, ''), ':Material Print Name:O:N:') as `product_material_print_name`,
    concat(ifnull(`v`.`product_material_tech_spect`, ''), ':Material Tech Spect:O:N:') as `product_material_tech_spect`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:O:N:') as `product_packing_name`,
    concat(ifnull(`v`.`hsn_sac_type`, ''), ':Hsn Sac Type:O:N:') as `hsn_sac_type`,
    concat(ifnull(`v`.`hsn_sac_description`, ''), ':Hsn Sac Description:O:N:') as `hsn_sac_description`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') as `purchase_order_status`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:O:N:') as `purchase_order_item_status`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') as `grn_item_status`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:O:N:') as `purchase_order_mail_sent_status`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`city_name`, ''), ':City Name:O:N:') as `city_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
    concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') as `supplier_contacts_ids`,
    concat(ifnull(`v`.`purchase_order_type_id`, ''), ':Purchase Order Type Id:N:N:') as `purchase_order_type_id`,
    concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction Id:O:N:') as `purchase_order_master_transaction_id`,
    concat(ifnull(`v`.`purchase_order_details_transaction_id`, ''), ':Purchase Order Details Transaction_id:N:N:') as `purchase_order_details_transaction_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') as `product_material_packing_id`,
    concat(ifnull(`v`.`product_material_hsn_code_id`, ''), ':Product Material Hsn Code Id:N:N:') as `product_material_hsn_code_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') as `indented_by_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') as `approved_by_id`
from
    `ptv_purchase_order_details` `v`
limit 1;