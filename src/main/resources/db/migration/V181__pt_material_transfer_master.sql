-- erp_development.ptv_material_transfer_master source

create or replace
algorithm = UNDEFINED view `ptv_material_transfer_master` as
select
    `gtm`.`transfer_no` as `transfer_no`,
    `gtm`.`transfer_date` as `transfer_date`,
    `gtm`.`transfer_version` as `transfer_version`,
    `gtm`.`from_company_id` as `from_company_id`,
    `fc`.`company_legal_name` as `from_company_name`,
    `gtm`.`from_company_branch_id` as `from_company_branch_id`,
    `gtm`.`to_company_id` as `to_company_id`,
    `tc`.`company_legal_name` as `to_company_name`,
    `gtm`.`to_company_branch_id` as `to_company_branch_id`,
    `gtm`.`company_id` as `company_id`,
    `gtm`.`financial_year` as `financial_year`,
    `gtm`.`company_branch_id` as `company_branch_id`,
    `spt`.`product_type_name` as `material_transfer_type_name`,
    `spt`.`product_type_group` as `material_transfer_type_group`,
    `ce`.`employee_name` as `transfer_by_name`,
    `gtm`.`transfer_by_id` as `transfer_by_id`,
    `gtm`.`material_transfer_type_id` as `material_transfer_type_id`,
    `gtm`.`material_transfer_master_id` as `material_transfer_master_id`,
    case
        when `gtm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `gtm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `gtm`.`is_active` as `is_active`,
    `gtm`.`is_delete` as `is_delete`,
    `gtm`.`created_by` as `created_by`,
    `gtm`.`created_on` as `created_on`,
    `gtm`.`modified_by` as `modified_by`,
    `gtm`.`modified_on` as `modified_on`,
    `gtm`.`deleted_by` as `deleted_by`,
    `gtm`.`deleted_on` as `deleted_on`
from
    (((((`pt_material_transfer_master` `gtm`
left join `cm_company` `v` on
    (`v`.`company_id` = `gtm`.`company_id`))
left join `cm_company` `fc` on
    (`fc`.`company_id` = `gtm`.`from_company_id`))
left join `cm_company` `tc` on
    (`tc`.`company_id` = `gtm`.`to_company_id`))
left join `sm_product_type` `spt` on
    (`spt`.`product_type_id` = `gtm`.`material_transfer_type_id`
        and `spt`.`is_delete` = 0))
left join `cm_employee` `ce` on
    (`ce`.`employee_id` = `gtm`.`transfer_by_id`
        and `ce`.`is_delete` = 0))
where
    `gtm`.`is_delete` = 0;


    -- erp_development.ptv_material_transfer_details source

    create or replace
    algorithm = UNDEFINED view `ptv_material_transfer_details` as
    select
        `gtd`.`transfer_no` as `transfer_no`,
        `gtd`.`transfer_date` as `transfer_date`,
        `gtd`.`transfer_version` as `transfer_version`,
        `gtmv`.`transfer_by_id` as `transfer_by_id`,
        `gtmv`.`from_company_id` as `from_company_id`,
        `gtmv`.`from_company_name` as `from_company_name`,
        `gtmv`.`from_company_branch_id` as `from_company_branch_id`,
        `gtmv`.`to_company_id` as `to_company_id`,
        `gtmv`.`to_company_name` as `to_company_name`,
        `gtmv`.`to_company_branch_id` as `to_company_branch_id`,
        `gtmv`.`company_id` as `company_id`,
        `gtmv`.`financial_year` as `financial_year`,
        `gtmv`.`company_branch_id` as `company_branch_id`,
        `gtd`.`material_transfer_type_id` as `material_transfer_type_id`,
        `gtmv`.`material_transfer_master_id` as `material_transfer_master_id`,
        `gtd`.`material_transfer_details_id` as `material_transfer_details_id`,
        `gtmv`.`material_transfer_type_name` as `material_transfer_type_name`,
        `gtmv`.`transfer_by_name` as `transfer_by_name`,
        `spr`.`product_rm_name` as `product_rm_name`,
        `gtd`.`product_code` as `product_code`,
        `gtd`.`product_material_id` as `product_material_id`,
        `gtd`.`godown_id` as `godown_id`,
        `gtd`.`godown_section_id` as `godown_section_id`,
        `gtd`.`godown_section_beans_id` as `godown_section_beans_id`,
        `gs`.`godown_name` as `godown_name`,
        `g`.`godown_section_name` as `godown_section_name`,
        `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
        `gtd`.`product_unit_id` as `product_unit_id`,
        `gtd`.`material_rate` as `material_rate`,
        `gtd`.`product_material_std_weight` as `product_material_std_weight`,
        `gtd`.`stock_quantity` as `stock_quantity`,
        `gtd`.`stock_weight` as `stock_weight`,
        `gtd`.`transfer_quantity` as `transfer_quantity`,
        `gtd`.`transfer_weight` as `transfer_weight`,
        `gtd`.`closing_balance_quantity` as `closing_balance_quantity`,
        `gtd`.`closing_balance_weight` as `closing_balance_weight`,
        case
            when `gtd`.`is_active` = 1 then 'Active'
            else 'In Active'
        end as `Active`,
        case
            when `gtd`.`is_delete` = 1 then 'Yes'
            else 'No'
        end as `Deleted`,
        `gtd`.`is_active` as `is_active`,
        `gtd`.`is_delete` as `is_delete`,
        `gtd`.`created_by` as `created_by`,
        `gtd`.`created_on` as `created_on`,
        `gtd`.`modified_by` as `modified_by`,
        `gtd`.`modified_on` as `modified_on`,
        `gtd`.`deleted_by` as `deleted_by`,
        `gtd`.`deleted_on` as `deleted_on`
    from
        (((((`pt_material_transfer_details` `gtd`
    left join `ptv_material_transfer_master` `gtmv` on
        (`gtd`.`material_transfer_master_id` = `gtmv`.`material_transfer_master_id`
            and `gtmv`.`is_delete` = 0))
    left join `sm_product_rm` `spr` on
        (`spr`.`product_rm_code` = `gtd`.`product_code`
            and `spr`.`product_rm_id` = `gtd`.`product_material_id`
            and `spr`.`is_delete` = 0))
    left join `cmv_godown_section_beans` `gsb` on
        (`gsb`.`godown_section_beans_id` = `gtd`.`godown_section_beans_id`))
    left join `cm_godown` `gs` on
        (`gs`.`godown_id` = `gtd`.`godown_id`))
    left join `cm_godown_section` `g` on
        (`g`.`godown_section_id` = `gtd`.`godown_section_id`))
    where
        `gtd`.`is_delete` = 0;


        -- erp_development.ptv_material_transfer_details_rpt source
        
        create or replace
        algorithm = UNDEFINED view `ptv_material_transfer_details_rpt` as
        select
            concat(`v`.`transfer_no`, ':Transfer No:O:N:') as `transfer_no`,
            concat(`v`.`transfer_date`, ':Transfer Date:Y:D:') as `transfer_date`,
            concat(`v`.`material_transfer_type_name`, ':Material Transfer Type Name:O:N:') as `material_transfer_type_name`,
            concat(`v`.`product_rm_name`, ':Product Name:O:N:') as `product_rm_name`,
            concat(`v`.`product_code`, ':Product Code:O:N:') as `product_code`,
            concat(`v`.`product_unit_id`, ':Product Unit Id:O:N:') as `product_unit_id`,
            concat(`v`.`material_rate`, ':Material Rate:O:N:') as `material_rate`,
            concat(`v`.`product_material_std_weight`, ':Product Material STD Weight:O:N:') as `product_material_std_weight`,
            concat(`v`.`stock_quantity`, ':Stock Quantity:O:N:') as `stock_quantity`,
            concat(`v`.`stock_weight`, ':Stock Weight:O:N:') as `stock_weight`,
            concat(`v`.`transfer_quantity`, ':Transfer Quantity:O:N:') as `transfer_quantity`,
            concat(`v`.`transfer_weight`, ':Transfer Weight:O:N:') as `transfer_weight`,
            concat(`v`.`closing_balance_quantity`, ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
            concat(`v`.`closing_balance_weight`, ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
            concat(`v`.`from_company_id`, ':From Company Id:N:N') as `from_company_id`,
            concat(`v`.`from_company_branch_id`, ':From Company Branch Id:N:N:') as `from_company_branch_id`,
            concat(`v`.`from_company_name`, ':From Company Name:O:N:') as `from_company_name`,
            concat(`v`.`to_company_id`, ':To Company Id:N:N') as `to_company_id`,
            concat(`v`.`to_company_branch_id`, ':From Company Branch Id:N:N:') as `to_company_branch_id`,
            concat(`v`.`to_company_name`, ':To Company Name:O:N:') as `to_company_name`,
            concat(`v`.`product_material_id`, ':Product Id:N:N') as `product_material_id`,
            concat(`v`.`material_transfer_master_id`, ':Material Transfer Master Id:O:N:') as `material_transfer_master_id`,
            concat(`v`.`material_transfer_details_id`, ':Material Transfer Details Id:O:N:') as `material_transfer_details_id`,
            concat(`v`.`godown_name`, ':Godown Name:O:N:') as `godown_name`,
            concat(`v`.`godown_section_name`, ':Godown Section Name:O:N:') as `godown_section_name`,
            concat(`v`.`godown_section_beans_name`, ':Godown Section Beans Name:O:N:') as `godown_section_beans_name`,
            concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
            concat(`v`.`godown_id`, ':Godown Id:O:N:') as `godown_id`,
            concat(`v`.`godown_section_id`, ':Godown Section Id:N:N:') as `godown_section_id`,
            concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
            concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
            concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
            concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
            concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
            concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
            concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
            concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
            concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(`v`.`material_transfer_type_id`, ':Product Type Id:N:N:') as `material_transfer_type_id`,
            concat(`v`.`transfer_by_id`, ':Transfer By Id:N:N:') as `transfer_by_id`
        from
            `ptv_material_transfer_details` `v`
        limit 1;