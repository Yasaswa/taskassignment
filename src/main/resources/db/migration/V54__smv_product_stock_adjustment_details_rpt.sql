
-- smv_product_stock_adjustment_details_rpt source

create or replace
algorithm = UNDEFINED view `smv_product_stock_adjustment_details_rpt` as
select
    concat(ifnull(`v`.`product_material_code`, ''), ':Product Material Code:Y:T:') as `product_material_code`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`stock_adjustment_type`, ''), ':Stock Adjustment Type:O:N:') as `stock_adjustment_type`,
    concat(ifnull(`v`.`stock_quantity`, ''), ':Stock Quantity:Y:T:') as `stock_quantity`,
    concat(ifnull(`v`.`stock_weight`, ''), ':Stock Weight:Y:T:') as `stock_weight`,
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