CREATE TABLE `pt_goods_return_master` (
  `company_id` int(11) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `company_branch_id` bigint(20) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `product_type_id` varchar(20) NOT NULL,
  `goods_return_master_id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `goods_return_no` varchar(255) NOT NULL,
  `goods_return_date` date NOT NULL,
  `goods_version` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `goods_receipt_no` varchar(250) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_return_master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `pt_goods_return_details` (
  `company_id` int(11) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `company_branch_id` bigint(20) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `goods_return_details_id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `goods_return_master_id` bigint(20) NOT NULL ,
  `product_rm_id` varchar(20) NOT NULL  ,
  `goods_return_quantity` decimal(18,4) DEFAULT 0.0000,
  `goods_return_weight` decimal(18,4) DEFAULT 0.0000,
  `goods_return_boxes` decimal(18,4) DEFAULT 0.0000,
  `goods_return_rate` decimal(18,4) DEFAULT 0.0000,
  `goods_receipt_no` varchar(250) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_return_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
    `ptv`.`supplier_name` as `supplier_name`,
    `pdt`.`product_type_name` as `product_type_name`,
    `ptv`.`remark` as `remark`,
    `ptv`.`product_material_name` as `product_material_name`,
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
        `grm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `is_active`,
    case
        `grm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`
from
   (((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id` ))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `ptv_goods_receipt_details` `ptv` on
    (`pt`.`goods_receipt_no` = `ptv`.`goods_receipt_no`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
        and `pt`.`is_delete` = 0))
 left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `pt`.`company_id`))
where
    `pt`.`is_delete` = 0;


    create or replace
    algorithm = UNDEFINED view `ptv_goods_return_details_rpt` as
    select
        concat(`v`.`goods_receipt_no`, ':Goods Receipt No:O:N:') as `goods_receipt_no`,
        concat(`v`.`product_type_name`, ':Product Type Name:O:N:') as `product_type_name`,
        concat(`v`.`supplier_name`, ':Supplier Name:O:N:') as `supplier_name`,
        concat(`v`.`product_material_name`, ':Product Material Name:O:N:') as `product_material_name`,
        concat(`v`.`batch_no`, ':Batch No:O:N:') as `batch_no`,
        concat(`v`.`product_material_grn_accepted_quantity`, ':GRN Accepted Quantity:O:N:') as `product_material_grn_accepted_quantity`,
        concat(`v`.`product_material_grn_accepted_weight`, ':GRN Accepted Weight:O:N:') as `product_material_grn_accepted_weight`,
        concat(`v`.`no_of_boxes`, ':No of Boxes:O:N:') as `no_of_boxes`,
        concat(`v`.`total_box_weight`, ':Total Box Weight:O:N:') as `total_box_weight`,
        concat(`v`.`total_quantity_in_box`, ':Total Quantity In Box:O:N:') as `total_quantity_in_box`,
        concat(`v`.`weight_per_box_item`, ':Weight Per Box:O:N:') as `weight_per_box_item`,
        concat(`v`.`material_rate`, ':Material Rate:O:N:') as `material_rate`,
        concat(`v`.`closing_balance_quantity`, ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
        concat(`v`.`closing_no_of_boxes`, ':Closing No of Boxes:O:N:') as `closing_no_of_boxes`,
        concat(`v`.`closing_balance_weight`, ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
        concat(`v`.`goods_return_date`, ':Goods Return Date:O:N:') as `goods_return_date`,
        concat(`v`.`goods_version`, ':Goods version:O:N:') as `goods_version`,
        concat(`v`.`goods_return_no`, ':Goods Return No:O:N:') as `goods_return_no`,
        concat(`v`.`goods_return_quantity`, ':Goods Return Quantity:O:N:') as `goods_return_quantity`,
        concat(`v`.`goods_return_weight`, ':Goods Return Weight:O:N:') as `goods_return_weight`,
        concat(`v`.`goods_return_boxes`, ':Goods Return Boxes:O:N:') as `goods_return_boxes`,
        concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
        concat(`v`.`product_rm_id`, ':Product Id:Y:D:') as `product_rm_id`,
        concat(`v`.`goods_return_master_id`, ':Goods Return Master Id:O:N:') as `goods_return_master_id`,
        concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
        concat(`v`.`company_branch_name`, ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
        concat(`v`.`goods_return_details_id`, ':Goods Return Details Id:O:N:') as `goods_return_details_id`,
        concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(`v`.`product_type_id`, ':Product Type Id:O:N:') as `product_type_id`,
        concat(`v`.`supplier_id`, ':Supplier Id:N:N:') as `supplier_id`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
        concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
        concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
        concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
        concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
        concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
        concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
    from
        `ptv_goods_return_details` `v`
    limit 1;


CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_master` AS
SELECT
    `stm`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
    `stm`.`company_id` AS `company_id`,
    `stm`.`company_branch_id` AS `company_branch_id`,
    `stm`.`financial_year` AS `financial_year`,
    `stm`.`issue_return_no` AS `issue_return_no`,
    `stm`.`issue_return_date` AS `issue_return_date`,
    `stm`.`indent_issue_return_type_id` AS `indent_issue_return_type_id`,
    `stm`.`indent_issue_return_type` AS `indent_issue_return_type`,
    `stm`.`department_id` AS `department_id`,
    `stm`.`sub_department_id` AS `sub_department_id`,
    `stm`.`issue_no` AS `issue_no`,
    `stm`.`return_by_id` AS `return_by_id`,
    `stm`.`issue_return_status` AS `issue_return_status`,
    `stm`.`received_by_id` AS `received_by_id`,
    `stm`.`received_date` AS `received_date`,
    `stm`.`remark` AS `remark`,
    CASE
        WHEN `stm`.`is_active` = 1 THEN 'Active'
        ELSE 'In Active'
    END AS `is_active`,
    CASE
        WHEN `stm`.`is_delete` = 1 THEN 'Yes'
        ELSE 'No'
    END AS `is_delete`,
    `stm`.`created_by` AS `created_by`,
    `stm`.`created_on` AS `created_on`,
    `stm`.`modified_by` AS `modified_by`,
    `stm`.`modified_on` AS `modified_on`,
    `stm`.`deleted_by` AS `deleted_by`,
    `stm`.`deleted_on` AS `deleted_on`
FROM
    `st_indent_material_issue_return_master` `stm`
WHERE
    `stm`.`is_delete` = 0;

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_details` AS
SELECT
    `st`.`issue_return_details_transaction_id` AS `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
    `st`.`company_id` AS `company_id`,
    `st`.`company_branch_id` AS `company_branch_id`,
    `st`.`financial_year` AS `financial_year`,
    `st`.`product_material_unit_id` AS `product_material_unit_id`,
    `st`.`product_material_id` AS `product_material_id`,
    `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` AS `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` AS `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` AS `issue_return_item_status`,
    `st`.`godown_id` AS `godown_id`,
    `st`.`godown_section_id` AS `godown_section_id`,
    `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `st`.`issue_return_remark` AS `issue_return_remark`,
    CASE
        WHEN `st`.`is_active` = 1 THEN 'Active'
        ELSE 'In Active'
    END AS `is_active`,
    CASE
        WHEN `st`.`is_delete` = 1 THEN 'Yes'
        ELSE 'No'
    END AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`
FROM
    `st_indent_material_issue_return_details` `st`
WHERE
    `st`.`is_delete` = 0;