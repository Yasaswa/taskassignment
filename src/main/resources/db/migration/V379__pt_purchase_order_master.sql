

ALTER TABLE pt_purchase_order_master ADD sales_type varchar(50) DEFAULT null NULL COMMENT 'If Job Work Or Sales Purchase Rate Will be 0';
ALTER TABLE pt_purchase_order_master CHANGE sales_type sales_type varchar(50) DEFAULT null NULL COMMENT 'If Job Work Or Sales Purchase Rate Will be 0' AFTER quotation_date;


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
    `pt`.`sales_type` as `sales_type`,
    `s`.`supp_branch_name` as `supplier_name`,
    `st`.`state_name` as `state_name`,
    `s`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `s`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `cb`.`company_branch_name` as `expected_branch_name`,
    `st1`.`state_name` as `expected_branch_state_name`,
    `e`.`employee_name` as `approved_by`,
    `pt`.`product_category2_id` as `product_category2_id`,
    `pt`.`product_category2_name` as `product_category2_name`,
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
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending)') as `purchase_order_status_desc`,
    concat(ifnull(`v`.`sales_type`, ''), ':Sales Type:Y:T') as `sales_type`,
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
    concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') as `purchase_order_version`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year :O:N:') as `financial_year`
from
    `ptv_purchase_order_master_summary` `v`
limit 1;