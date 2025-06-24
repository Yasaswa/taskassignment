create or replace
algorithm = UNDEFINED view `ptv_goods_return_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `pt`.`product_rm_id` as `product_rm_id`,
    `pt`.`goods_return_quantity` as `goods_return_quantity`,
    `pt`.`goods_return_weight` as `goods_return_weight`,
    `pt`.`goods_return_boxes` as `goods_return_boxes`,
    `pt`.`goods_return_details_id` as `goods_return_details_id`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`supplier_id` as `supplier_id`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `grm`.`sales_type` as `sales_type`,
    `pdt`.`product_type_name` as `product_type_name`,
    `ptv`.`remark` as `remark`,
     `pt`.`goods_return_rate` as `goods_return_rate`,
    `rm`.`product_rm_name` as `product_material_name`,
    `ptv`.`batch_no` as `batch_no`,
    `ptv`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `ptv`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    `ptv`.`no_of_boxes` as `no_of_boxes`,
    `ptv`.`total_box_weight` as `total_box_weight`,
    `ptv`.`total_quantity_in_box` as `total_quantity_in_box`,
    `ptv`.`weight_per_box_item` as `weight_per_box_item`,
    `ptv`.`material_rate` as `material_rate`,
    `smv`.`closing_balance_quantity` as `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` as `closing_balance_weight`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `grm`.`company_id` as `company_id`,
    `grm`.`company_branch_id` as `company_branch_id`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `grm`.`is_active` as `is_active`,
    `grm`.`is_delete` as `is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`
from
    (((((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `pt_goods_receipt_details` `ptv` on
    (`pt`.`goods_receipt_no` = `ptv`.`goods_receipt_no`
  and `pt`.`product_rm_id` = `ptv`.`product_material_id`
        and `pt`.`is_delete` = 0))
left join `sm_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
    and `pt`.`product_rm_id` = `smv`.`product_rm_id`
     and `smv`.`day_closed` = 0
        and `pt`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `pt`.`company_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;


   create or replace
algorithm = UNDEFINED view `ptv_goods_return_master` as
select
    `grm`.`goods_receipt_no` as `goods_receipt_no`,
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `pt`.`product_rm_id` as `product_rm_id`,
    `pt`.`goods_return_quantity` as `goods_return_quantity`,
    `pt`.`goods_return_weight` as `goods_return_weight`,
    `pt`.`goods_return_boxes` as `goods_return_boxes`,
    `pt`.`goods_return_details_id` as `goods_return_details_id`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`supplier_id` as `supplier_id`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `grm`.`sales_type` as `sales_type`,
    `pdt`.`product_type_name` as `product_type_name`,
    `rm`.`product_rm_name` as `product_material_name`,
    `smv`.`closing_balance_quantity` as `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` as `closing_balance_weight`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `grm`.`company_id` as `company_id`,
    `grm`.`company_branch_id` as `company_branch_id`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `grm`.`is_active` as `is_active`,
    `grm`.`is_delete` as `is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`
from
    ((((((`pt_goods_return_master` `grm`
left join `pt_goods_return_details` `pt` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))

left join `sm_product_rm_stock_details` `smv` on
    (`grm`.`goods_receipt_no` = `smv`.`goods_receipt_no`
    and `pt`.`product_rm_id` = `smv`.`product_rm_id`
     and `smv`.`day_closed` = 0
        and `pt`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `grm`.`company_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
where
    `grm`.`is_delete` = 0;



   create or replace
algorithm = UNDEFINED view `ptv_goods_return_master_rpt` as
select
    concat(`v`.`goods_receipt_no`, ':Good Receipt No:O:N:') as `goods_receipt_no`,
    concat(`v`.`product_type_name`, ':Product Type Name:O:N:') as `product_type_name`,
    concat(`v`.`supplier_name`, ':Supplier Name:O:N:') as `supplier_name`,
    concat(`v`.`product_material_name`, ':Product Material Name:O:N:') as `product_material_name`,
    concat(`v`.`sales_type`, ':Sales Type:O:N:') as `sales_type`,
    concat(`v`.`closing_balance_quantity`, ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
    concat(`v`.`closing_no_of_boxes`, ':Closing No of Boxes:O:N:') as `closing_no_of_boxes`,
    concat(`v`.`closing_balance_weight`, ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
    concat(`v`.`goods_return_date`, ':Goods Return Date:Y:D:') as `goods_return_date`,
    concat(`v`.`goods_version`, ':Goods version:O:N:') as `goods_version`,
    concat(`v`.`goods_return_no`, ':Goods Return No:O:N:') as `goods_return_no`,
    concat(`v`.`goods_return_quantity`, ':Goods Return Quantity:O:N:') as `goods_return_quantity`,
    concat(`v`.`product_rm_id`, ':Product Id:Y:D:') as `product_rm_id`,
    concat(`v`.`goods_return_master_id`, ':Goods Return Master Id:O:N:') as `goods_return_master_id`,
    concat(`v`.`goods_return_weight`, ':Goods Return Weight:O:N:') as `goods_return_weight`,
    concat(`v`.`goods_return_boxes`, ':Goods Return Boxes:O:N:') as `goods_return_boxes`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(`v`.`goods_return_details_id`, ':Financial Year:Y:C:amv_financial_year:F') as `goods_return_details_id`,
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
    concat(`v`.`product_type_id`, ':Product Type Id:O:N:') as `product_type_id`,
    concat(`v`.`supplier_id`, ':Supplier Id:N:N:') as `supplier_id`
from
    `ptv_goods_return_master` `v`
limit 1;
