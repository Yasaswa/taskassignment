



ALTER TABLE sm_product_stock_adjustment_details ADD material_rate DOUBLE(18,4) DEFAULT 0 NULL COMMENT 'material can be update from stock adjustment';



-- smv_product_stock_adjustment_details source

create or REPLACE algorithm = UNDEFINED view `smv_product_stock_adjustment_details` as
select
    `rm_fg_sr`.`product_material_code` as `product_material_code`,
    `rm_fg_sr`.`product_material_name` as `product_material_name`,
    `sm`.`stock_adjustment_type` as `stock_adjustment_type`,
    `sm`.`stock_quantity` as `stock_quantity`,
    `sm`.`stock_weight` as `stock_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`stock_adjustment_quantity` as `stock_adjustment_quantity`,
    `sm`.`stock_adjustment_weight` as `stock_adjustment_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`material_rate` as `material_rate`,
    `sm`.`stock_date` as `stock_date`,
    `sa`.`adjustment_by` as `adjustment_by`,
    `sa`.`adjustment_reason` as `adjustment_reason`,
    `sa`.`approved_by` as `approved_by`,
    `gd`.`godown_name` as `godown_name`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `sm`.`goods_receipt_no` as `goods_receipt_no`,
    `sm`.`batch_expiry_date` as `batch_expiry_date`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`financial_year` as `financial_year`,
    `sm`.`stock_adjustment_transaction_id` as `stock_adjustment_transaction_id`,
    `sm`.`stock_adjustment_details_transaction_id` as `stock_adjustment_details_transaction_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `sm`.`product_material_id` as `product_material_id`
from
    (((((`sm_product_stock_adjustment_details` `sm`
left join `sm_product_stock_adjustment_master` `sa` on
    (`sa`.`stock_adjustment_transaction_id` = `sm`.`stock_adjustment_transaction_id`
        and `sa`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm_fg_sr` on
    (`rm_fg_sr`.`product_material_id` = `sm`.`product_material_id`))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `sm`.`godown_id`))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `sm`.`godown_section_id`))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`));