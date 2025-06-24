


ALTER TABLE sm_product_stock_adjustment_details ADD opening_quantity DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'material opening_quantity of opening balance will be store in this column';
ALTER TABLE sm_product_stock_adjustment_details CHANGE opening_quantity opening_quantity DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'material opening_quantity of opening balance will be store in this column' AFTER stock_weight;
ALTER TABLE sm_product_stock_adjustment_details ADD opening_weight DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'material opening_weight of opening balance will be store in this column';
ALTER TABLE sm_product_stock_adjustment_details CHANGE opening_weight opening_weight DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'material opening_weight of opening balance will be store in this column' AFTER opening_quantity;




-- smv_product_stock_adjustment_details source

create or replace
algorithm = UNDEFINED view `smv_product_stock_adjustment_details` as
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



   -- smv_product_stock_adjustment_details_rpt source

create or replace
algorithm = UNDEFINED view `smv_product_stock_adjustment_details_rpt` as
select
    concat(ifnull(`v`.`product_material_code`, ''), ':Product Material Code:Y:T:') as `product_material_code`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`stock_adjustment_type`, ''), ':Stock Adjustment Type:O:N:') as `stock_adjustment_type`,
    concat(ifnull(`v`.`stock_quantity`, ''), ':Stock Quantity:Y:T:') as `stock_quantity`,
    concat(ifnull(`v`.`stock_weight`, ''), ':Stock Weight:Y:T:') as `stock_weight`,
    concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Balance Quantity:Y:T:') as `opening_quantity`,
    concat(ifnull(`v`.`opening_weight`, ''), ':Opening Balance Weight:Y:T:') as `opening_weight`,
    concat(ifnull(`v`.`stock_adjustment_quantity`, ''), ':Stock Adjustment Quantity:Y:T:') as `stock_adjustment_quantity`,
    concat(ifnull(`v`.`stock_adjustment_weight`, ''), ':Stock Adjustment Weight:Y:T:') as `stock_adjustment_weight`,
    concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
    concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
    concat(ifnull(`v`.`stock_date`, ''), ':Adjustment Date:Y:D:') as `stock_date`,
    concat(ifnull(`v`.`adjustment_by`, ''), ':Adjustment By:O:N:') as `adjustment_by`,
    concat(ifnull(`v`.`adjustment_reason`, ''), ':Adjustment Reason:O:N:') as `adjustment_reason`,
    concat(ifnull(`v`.`approved_by`, ''), ':Approved By:O:N:') as `approved_by`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`is_delete`, ''), ':Is Delete:O:N:') as `is_delete`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`stock_adjustment_transaction_id`, ''), ':Stock Adjustment Transaction Id:O:N:') as `stock_adjustment_transaction_id`,
    concat(ifnull(`v`.`stock_adjustment_details_transaction_id`, ''), ':Stock Adjustment Details Transaction Id:N:N:') as `stock_adjustment_details_transaction_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`
from
    `smv_product_stock_adjustment_details` `v`
limit 1;