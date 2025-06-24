ALTER TABLE pt_goods_return_master ADD approved_by_id bigint(20) NULL;
ALTER TABLE pt_goods_return_master ADD approved_date date NULL;


-- pashupati_erp_qa.ptv_goods_return_master source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_goods_return_master` AS
select
    `grm`.`goods_receipt_no` AS `goods_receipt_no`,
    `grm`.`goods_return_date` AS `goods_return_date`,
    `grm`.`goods_version` AS `goods_version`,
    `grm`.`goods_return_no` AS `goods_return_no`,
    `grm`.`goods_return_master_id` AS `goods_return_master_id`,
    `grm`.`product_type_id` AS `product_type_id`,
    `grm`.`supplier_id` AS `supplier_id`,
    `sup`.`supp_branch_name` AS `supplier_name`,
    `grm`.`sales_type` AS `sales_type`,
    `pdt`.`product_type_name` AS `product_type_name`,
    `e`.`employee_name` AS `approved_by_name`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `grm`.`company_id` AS `company_id`,
    `grm`.`financial_year` AS `financial_year`,
    `grm`.`company_branch_id` AS `company_branch_id`,
    `grm`.`goods_return_status` AS `goods_return_status`,
    case
        `grm`.`goods_return_status` when 'A' then 'Approved'
        else 'Pending'
    end AS `goods_return_status_desc`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `grm`.`is_active` AS `is_active`,
    `grm`.`is_delete` AS `is_delete`,
    `grm`.`created_by` AS `created_by`,
    `grm`.`created_on` AS `created_on`,
    `grm`.`modified_by` AS `modified_by`,
    `grm`.`modified_on` AS `modified_on`,
    `grm`.`deleted_by` AS `deleted_by`,
    `grm`.`deleted_on` AS `deleted_on`,
    `grm`.approved_by_id,
	`grm`.approved_date
from
    ((((`pt_goods_return_master` `grm`
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1 and `e`.`is_delete` = 0
        and `e`.`employee_id` = `grm`.`approved_by_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `grm`.`company_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `sup`.`is_delete` = 0))
where
    `grm`.`is_delete` = 0;