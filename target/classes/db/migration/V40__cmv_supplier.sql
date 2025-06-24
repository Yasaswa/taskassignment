-- cmv_supplier source

create or replace
algorithm = UNDEFINED view `cmv_supplier` as
select
    `c`.`supplier_name` as `supplier_name`,
    `c`.`supplier_short_name` as `supplier_short_name`,
    `c`.`supplier_sector` as `supplier_sector`,
    `c`.`supplier_type` as `supplier_type`,
    `c`.`supplier_code` as `supplier_code`,
    `c`.`nature_of_business` as `nature_of_business`,
    `c`.`supplier_accounts_id` as `supplier_accounts_id`,
    `p1`.`property_name` as `supplier_payment_terms`,
    `c`.`supplier_rating` as `supplier_rating`,
    `c`.`supplier_gl_codes` as `supplier_gl_codes`,
    `c`.`supplier_history` as `supplier_history`,
    `b`.`supp_branch_name` as `supp_branch_name`,
    `b`.`supp_branch_short_name` as `supp_branch_short_name`,
    `b`.`supp_branch_address1` as `supp_branch_address1`,
    `b`.`supp_branch_address2` as `supp_branch_address2`,
    `b`.`supp_branch_pincode` as `supp_branch_pincode`,
    `ct`.`city_name` as `city_name`,
    `d`.`district_name` as `district_name`,
    `s`.`state_name` as `state_name`,
    `c1`.`country_name` as `country_name`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `b`.`supp_branch_region` as `supp_branch_region`,
    `b`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `b`.`supp_branch_cell_no` as `supp_branch_cell_no`,
    `b`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `b`.`supp_branch_website` as `supp_branch_website`,
    `b`.`supp_branch_linkedin_profile` as `supp_branch_linkedin_profile`,
    `b`.`supp_branch_twitter_profile` as `supp_branch_twitter_profile`,
    `b`.`supp_branch_facebook_profile` as `supp_branch_facebook_profile`,
    `b`.`supp_branch_gst_no` as `supp_branch_gst_no`,
    `b`.`supp_branch_gst_division` as `supp_branch_gst_division`,
    `b`.`supp_branch_gst_range` as `supp_branch_gst_range`,
    `b`.`supp_branch_pan_no` as `supp_branch_pan_no`,
    `b`.`supp_branch_udyog_adhar_no` as `supp_branch_udyog_adhar_no`,
    `b`.`supp_branch_vat_no` as `supp_branch_vat_no`,
    `b`.`supp_branch_service_tax_no` as `supp_branch_service_tax_no`,
    `b`.`supp_branch_excise_no` as `supp_branch_excise_no`,
    `b`.`supp_branch_cst_no` as `supp_branch_cst_no`,
    `b`.`supp_branch_bst_no` as `supp_branch_bst_no`,
    `b`.`supp_branch_tally_id` as `supp_branch_tally_id`,
    `b`.`supp_branch_rating` as `supp_branch_rating`,
    `b`.`supp_branch_gl_codes` as `supp_branch_gl_codes`,
    `b`.`supp_branch_accounts_id` as `supp_branch_accounts_id`,
    `b`.`is_sez` as `is_sez`,
    `b`.`sez_name` as `sez_name`,
    `c`.`username` as `supplier_username`,
    `c`.`password` as `supplier_password`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `c`.`remark` as `remark`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `b`.`created_by` as `created_by`,
    `b`.`created_on` as `created_on`,
    `b`.`modified_by` as `modified_by`,
    `b`.`modified_on` as `modified_on`,
    `b`.`deleted_by` as `deleted_by`,
    `b`.`deleted_on` as `deleted_on`,
    `c`.`company_id` as `company_id`,
    `c`.`company_branch_id` as `company_branch_id`,
    `c`.`supplier_id` as `supplier_id`,
    `c`.`payment_term_id` as `payment_term_id`,
    `b`.`supp_branch_city_id` as `supp_branch_city_id`,
    `b`.`supp_branch_district_id` as `supp_branch_district_id`,
    `b`.`supp_branch_state_id` as `supp_branch_state_id`,
    `b`.`supp_branch_country_id` as `supp_branch_country_id`,
    `b`.`supp_branch_id` as `supp_branch_id`,
    `c`.`supplier_id` as `field_id`,
    `c`.`supplier_name` as `field_name`
from
    (((((((`cm_supplier` `c`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `c`.`company_branch_id`
        and `v`.`company_id` = `c`.`company_id`))
left join `amv_properties` `p1` on
    (`p1`.`properties_master_name` = 'PaymentTermDays'
        and `p1`.`property_id` = `c`.`payment_term_id`))
left join `cm_supplier_branch` `b` on
    (`b`.`branch_type` = 'Main'
        and `c`.`supplier_id` = `b`.`supplier_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `b`.`supp_branch_city_id`))
left join `cm_district` `d` on
    (`d`.`district_id` = `b`.`supp_branch_district_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `b`.`supp_branch_state_id`))
left join `cm_country` `c1` on
    (`c1`.`country_id` = `b`.`supp_branch_country_id`))
where
    `c`.`is_delete` = 0;

    --
     -- cmv_supplier_branch source

    create or replace
    algorithm = UNDEFINED view `cmv_supplier_branch` as
    select
        `v`.`company_legal_name` as `company_name`,
        `v`.`company_branch_name` as `company_branch_name`,
        `c`.`supplier_name` as `supplier_name`,
        `c`.`supplier_short_name` as `supplier_short_name`,
        `c`.`supplier_type` as `supplier_type`,
        `c`.`supplier_code` as `supplier_code`,
        `c`.`supplier_sector` as `supplier_sector`,
        `c`.`nature_of_business` as `nature_of_business`,
        `p1`.`property_name` as `supplier_payment_terms`,
        `c`.`supplier_rating` as `supplier_rating`,
        `c`.`supplier_gl_codes` as `supplier_gl_codes`,
        `c`.`supplier_accounts_id` as `supplier_accounts_id`,
        `c`.`supplier_history` as `supplier_history`,
        `b`.`supp_branch_name` as `supp_branch_name`,
        `b`.`supp_branch_short_name` as `supp_branch_short_name`,
        `b`.`supp_branch_vendor_code` as `supp_branch_vendor_code`,
        `b`.`branch_type` as `branch_type`,
        `b`.`supp_branch_type` as `supp_branch_type`,
        `p3`.`property_name` as `supp_branch_payment_terms`,
        `b`.`supp_branch_address1` as `supp_branch_address1`,
        `b`.`supp_branch_address2` as `supp_branch_address2`,
        `b`.`supp_branch_pincode` as `supp_branch_pincode`,
        `ct`.`city_name` as `city_name`,
        `d`.`district_name` as `district_name`,
        `s`.`state_name` as `state_name`,
        `c1`.`country_name` as `country_name`,
        `b`.`supp_branch_region` as `supp_branch_region`,
        `b`.`supp_branch_phone_no` as `supp_branch_phone_no`,
        `b`.`supp_branch_cell_no` as `supp_branch_cell_no`,
        `b`.`supp_branch_EmailId` as `supp_branch_EmailId`,
        `b`.`supp_branch_website` as `supp_branch_website`,
        `b`.`supp_branch_linkedin_profile` as `supp_branch_linkedin_profile`,
        `b`.`supp_branch_twitter_profile` as `supp_branch_twitter_profile`,
        `b`.`supp_branch_facebook_profile` as `supp_branch_facebook_profile`,
        `b`.`supp_branch_gst_no` as `supp_branch_gst_no`,
        `b`.`supp_branch_gst_division` as `supp_branch_gst_division`,
        `b`.`supp_branch_gst_range` as `supp_branch_gst_range`,
        `b`.`supp_branch_pan_no` as `supp_branch_pan_no`,
        `b`.`supp_branch_udyog_adhar_no` as `supp_branch_udyog_adhar_no`,
        `b`.`supp_branch_vat_no` as `supp_branch_vat_no`,
        `b`.`supp_branch_service_tax_no` as `supp_branch_service_tax_no`,
        `b`.`supp_branch_excise_no` as `supp_branch_excise_no`,
        `b`.`supp_branch_cst_no` as `supp_branch_cst_no`,
        `b`.`supp_branch_bst_no` as `supp_branch_bst_no`,
        `b`.`supp_branch_tally_id` as `supp_branch_tally_id`,
        `b`.`supp_branch_rating` as `supp_branch_rating`,
        `b`.`supp_branch_gl_codes` as `supp_branch_gl_codes`,
        `b`.`supp_branch_city_id` as `supp_branch_city_id`,
        `b`.`supp_branch_district_id` as `supp_branch_district_id`,
        `b`.`supp_branch_state_id` as `supp_branch_state_id`,
        `b`.`supp_branch_country_id` as `supp_branch_country_id`,
        `b`.`supp_branch_accounts_id` as `supp_branch_accounts_id`,
        `b`.`supp_branch_blocked` as `supp_branch_blocked`,
        `b`.`supp_branch_blocked_remark` as `supp_branch_blocked_remark`,
        `b`.`supp_branch_payment_blocked` as `supp_branch_payment_blocked`,
        `b`.`supp_branch_payment_blocked_remark` as `supp_branch_payment_blocked_remark`,
        `b`.`is_sez` as `is_sez`,
        `b`.`sez_name` as `sez_name`,
        case
            `b`.`is_active` when 1 then 'Active'
            else 'In Active'
        end as `Active`,
        case
            `b`.`is_delete` when 1 then 'Yes'
            else 'No'
        end as `Deleted`,
        `b`.`is_active` as `is_active`,
        `b`.`is_delete` as `is_delete`,
        `b`.`created_by` as `created_by`,
        `b`.`created_on` as `created_on`,
        `b`.`modified_by` as `modified_by`,
        `b`.`modified_on` as `modified_on`,
        `b`.`deleted_by` as `deleted_by`,
        `b`.`deleted_on` as `deleted_on`,
        `b`.`supp_branch_id` as `supp_branch_id`,
        `b`.`supplier_id` as `supplier_id`,
        `b`.`company_id` as `company_id`,
        `b`.`company_branch_id` as `company_branch_id`,
        `b`.`supp_branch_payment_term_id` as `supp_branch_payment_term_id`,
        `c`.`payment_term_id` as `payment_term_id`,
        `b`.`supp_branch_id` as `field_id`,
        `b`.`supp_branch_name` as `field_name`
    from
        ((((((((`cm_supplier` `c`
    left join `cm_supplier_branch` `b` on
        (`c`.`supplier_id` = `b`.`supplier_id`))
    left join `cmv_company_summary` `v` on
        (`v`.`company_branch_id` = `b`.`company_branch_id`
            and `v`.`company_id` = `b`.`company_id`))
    left join `amv_properties` `p1` on
        (`p1`.`properties_master_name` = 'PaymentTermDays'
            and `p1`.`property_id` = `c`.`payment_term_id`))
    left join `amv_properties` `p3` on
        (`p3`.`properties_master_name` = 'PaymentTermDays'
            and `p3`.`property_id` = `b`.`supp_branch_payment_term_id`))
    left join `cm_city` `ct` on
        (`ct`.`city_id` = `b`.`supp_branch_city_id`))
    left join `cm_district` `d` on
        (`d`.`district_id` = `b`.`supp_branch_district_id`))
    left join `cm_state` `s` on
        (`s`.`state_id` = `b`.`supp_branch_state_id`))
    left join `cm_country` `c1` on
        (`c1`.`country_id` = `b`.`supp_branch_country_id`))
    where
        `b`.`is_delete` = 0;


        --
-- ptv_purchase_order_master_summary source

create or replace
algorithm = UNDEFINED view `ptv_purchase_order_master_summary` as
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
    `s`.`supplier_type` as `supplier_type`,
    `s`.`supplier_code` as `supplier_code`,
    `s`.`supplier_short_name` as `supplier_short_name`,
    `s`.`supp_branch_address1` as `supp_branch_address1`,
    `s`.`supp_branch_pincode` as `supp_branch_pincode`,
    `ct`.`city_name` as `city_name`,
    `s`.`district_name` as `district_name`,
    `s`.`country_name` as `country_name`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `cb`.`branch_short_name` as `expected_branch_short_name`,
    `cb`.`branch_address1` as `expected_branch_address1`,
    `cb`.`branch_pincode` as `expected_branch_pincode`,
    `ct1`.`city_name` as `expected_branch_city_name`,
    `cb`.`district_name` as `expected_branch_district_name`,
    `cb`.`country_name` as `expected_branch_country_name`,
    `cb`.`branch_phone_no` as `expected_branch_phone_no`,
    `cb`.`branch_EmailId` as `expected_branch_EmailId`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
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
    `cc`.`city_name` as `supp_consignee_city_name`,
    `cc`.`district_name` as `supp_consignee_district_name`,
    `cc`.`state_name` as `supp_consignee_state_name`,
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
    (((((((((((((`pt_purchase_order_master` `pt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `pt`.`company_branch_id`
        and `v`.`company_id` = `pt`.`company_id`))
left join `cmv_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`))
left join `cmv_supplier_branch` `s` on
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
left join `cmv_supplier_branch` `cc` on
    (`cc`.`supp_branch_id` = `pt`.`consignee_id`))
where
    `pt`.`is_delete` = 0;

    --


    -- ptv_goods_receipt_summary source

    create or replace
    algorithm = UNDEFINED view `ptv_goods_receipt_summary` as
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
        (
        select
            distinct `pd1`.`purchase_order_no`
        from
            `pt_goods_receipt_details` `pd1`
        where
            `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
        limit 1) as `purchase_order_no`,
        (
        select
            distinct `pd1`.`purchase_order_date`
        from
            `pt_goods_receipt_details` `pd1`
        where
            `pd1`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
        limit 1) as `purchase_order_date`,
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
        `cb`.`company_name` as `company_name`,
        `cb`.`company_branch_name` as `company_branch_name`,
        `cb`.`branch_pincode` as `company_pincode`,
        `cb`.`branch_phone_no` as `company_phone_no`,
        `cb`.`state_name` as `company_branch_state`,
        `cb`.`city_name` as `company_branch_city`,
        `cb`.`branch_cell_no` as `company_cell_no`,
        `cb`.`branch_EmailId` as `company_EmailId`,
        `cb`.`branch_gst_no` as `company_gst_no`,
        `cb`.`branch_pan_no` as `company_pan_no`,
        `cb`.`branch_address1` as `company_address1`,
        `cb`.`branch_address2` as `company_address2`,
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
        `sup`.`payment_term_id` as `payment_term_id`
    from
        ((((((((`pt_goods_receipt_master` `pt`
    left join `cm_company_branch` `v` on
        (`v`.`company_branch_id` = `pt`.`company_branch_id`
            and `v`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `cmv_supplier_branch` `sup` on
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
    left join `cmv_company_branch_summary` `cb` on
        (`cb`.`company_branch_id` = `pt`.`expected_branch_id`
            and `cb`.`company_id` = `pt`.`company_id`))
    where
        `pt`.`is_delete` = 0;