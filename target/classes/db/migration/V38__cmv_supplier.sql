
-- cmv_supplier source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `cmv_supplier` AS
select
    `C`.`supplier_name` AS `supplier_name`,
    `C`.`supplier_short_name` AS `supplier_short_name`,
    `C`.`supplier_sector` AS `supplier_sector`,
    `C`.`supplier_type` AS `supplier_type`,
    `C`.`supplier_code` AS `supplier_code`,
    `C`.`nature_of_business` AS `nature_of_business`,
    `C`.`supplier_accounts_id` AS `supplier_accounts_id`,
    `P1`.`property_name` AS `supplier_payment_terms`,
    `C`.`supplier_rating` AS `supplier_rating`,
    `C`.`supplier_gl_codes` AS `supplier_gl_codes`,
    `C`.`supplier_history` AS `supplier_history`,
    `b`.`supp_branch_short_name` AS `supp_branch_short_name`,
    `b`.`supp_branch_address1` AS `supp_branch_address1`,
    `b`.`supp_branch_address2` AS `supp_branch_address2`,
    `b`.`supp_branch_pincode` AS `supp_branch_pincode`,
    `ct`.`city_name` AS `city_name`,
    `d`.`district_name` AS `district_name`,
    `s`.`state_name` AS `state_name`,
    `c1`.`country_name` AS `country_name`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `b`.`supp_branch_region` AS `supp_branch_region`,
    `b`.`supp_branch_phone_no` AS `supp_branch_phone_no`,
    `b`.`supp_branch_cell_no` AS `supp_branch_cell_no`,
    `b`.`supp_branch_EmailId` AS `supp_branch_EmailId`,
    `b`.`supp_branch_website` AS `supp_branch_website`,
    `b`.`supp_branch_linkedin_profile` AS `supp_branch_linkedin_profile`,
    `b`.`supp_branch_twitter_profile` AS `supp_branch_twitter_profile`,
    `b`.`supp_branch_facebook_profile` AS `supp_branch_facebook_profile`,
    `b`.`supp_branch_gst_no` AS `supp_branch_gst_no`,
    `b`.`supp_branch_gst_division` AS `supp_branch_gst_division`,
    `b`.`supp_branch_gst_range` AS `supp_branch_gst_range`,
    `b`.`supp_branch_pan_no` AS `supp_branch_pan_no`,
    `b`.`supp_branch_udyog_adhar_no` AS `supp_branch_udyog_adhar_no`,
    `b`.`supp_branch_vat_no` AS `supp_branch_vat_no`,
    `b`.`supp_branch_service_tax_no` AS `supp_branch_service_tax_no`,
    `b`.`supp_branch_excise_no` AS `supp_branch_excise_no`,
    `b`.`supp_branch_cst_no` AS `supp_branch_cst_no`,
    `b`.`supp_branch_bst_no` AS `supp_branch_bst_no`,
    `b`.`supp_branch_tally_id` AS `supp_branch_tally_id`,
    `b`.`supp_branch_rating` AS `supp_branch_rating`,
    `b`.`supp_branch_gl_codes` AS `supp_branch_gl_codes`,
    `b`.`supp_branch_accounts_id` AS `supp_branch_accounts_id`,
    `b`.`is_sez` AS `is_sez`,
    `b`.`sez_name` AS `sez_name`,
    `C`.`username` AS `supplier_username`,
    `C`.`password` AS `supplier_password`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `C`.`remark` AS `remark`,
    `C`.`is_active` AS `is_active`,
    `C`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `C`.`company_id` AS `company_id`,
    `C`.`company_branch_id` AS `company_branch_id`,
    `C`.`supplier_id` AS `supplier_id`,
    `C`.`payment_term_id` AS `payment_term_id`,
    `b`.`supp_branch_city_id` AS `supp_branch_city_id`,
    `b`.`supp_branch_district_id` AS `supp_branch_district_id`,
    `b`.`supp_branch_state_id` AS `supp_branch_state_id`,
    `b`.`supp_branch_country_id` AS `supp_branch_country_id`,
    `C`.`supplier_id` AS `field_id`,
    `C`.`supplier_name` AS `field_name`
from
    (((((((`cm_supplier` `C`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `C`.`company_branch_id`
        and `v`.`company_id` = `C`.`company_id`))
left join `amv_properties` `P1` on
    (`P1`.`properties_master_name` = 'PaymentTermDays'
        and `P1`.`property_id` = `C`.`payment_term_id`))
left join `cm_supplier_branch` `b` on
    (`b`.`branch_type` = 'Main'
        and `C`.`supplier_id` = `b`.`supplier_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `b`.`supp_branch_city_id`))
left join `cm_district` `d` on
    (`d`.`district_id` = `b`.`supp_branch_district_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `b`.`supp_branch_state_id`))
left join `cm_country` `c1` on
    (`c1`.`country_id` = `b`.`supp_branch_country_id`))
where
    `C`.`is_delete` = 0;