ALTER TABLE pt_inter_material_transfer_details ADD product_unit_name varchar(100) NULL;

create or replace
algorithm = UNDEFINED view `ptv_inter_material_transfer_details` as
select
    `pt`.`inter_material_transfer_no` as `inter_material_transfer_no`,
    `pt`.`inter_material_transfer_date` as `inter_material_transfer_date`,
    `rm`.`product_rm_name` as `product_material_name`,
    `pt`.`inter_material_transfer_quantity` as `inter_material_transfer_quantity`,
    `pt`.`inter_material_transfer_weight` as `inter_material_transfer_weight`,
    `pt`.`inter_material_transfer_boxes` as `inter_material_transfer_boxes`,
    `pt`.`batch_no` as `batch_no`,
    `pt`.`product_code` as `product_code`,
    `pt`.`from_godown_id` as `from_godown_id`,
    `pt`.`from_godown_section_id` as `from_godown_section_id`,
    `pt`.`from_godown_section_beans_id` as `from_godown_section_beans_id`,
    `pt`.`to_godown_id` as `to_godown_id`,
    `pt`.`to_godown_section_id` as `to_godown_section_id`,
    `pt`.`to_godown_section_beans_id` as `to_godown_section_beans_id`,
    `pt`.`product_unit_name` as `product_unit_name`,
    `gd`.`godown_name` as `from_godown_name`,
    `gds`.`godown_section_name` as `from_godown_section_name`,
    `gdsb`.`godown_section_beans_name` as `from_godown_section_beans_name`,
    `gdw1`.`godown_name` as `to_godown_name`,
    `gds1`.`godown_section_name` as `to_godown_section_name`,
    `gdsb1`.`godown_section_beans_name` as `to_godown_section_beans_name`,
    `pt`.`internal_material_transfer_remark` as `internal_material_transfer_remark`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`product_unit_id` as `product_unit_id`,
    `pt`.`product_material_id` as `product_material_id`,
    `pt`.`inter_material_transfer_details_id` as `inter_material_transfer_details_id`,
    `pt`.`inter_material_transfer_master_id` as `inter_material_transfer_master_id`,
    `pm`.`from_department_id` as `from_department_id`,
    `pm`.`from_sub_department_id` as `from_sub_department_id`,
    `pm`.`to_department_id` as `to_department_id`,
    `pm`.`to_sub_department_id` as `to_sub_department_id`,
    `pt`.`cone_per_wt` as `cone_per_wt`,
    `pt`.`supplier_id` as `supplier_id`,
    `sprsd`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sprsd`.`closing_balance_weight` as `closing_balance_weight`,
    `sprsd`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `pt`.`goods_receipt_no` as `goods_receipt_no`
from
    (((((((((`pt_inter_material_transfer_details` `pt`
left join `pt_inter_material_transfer_master` `pm` on
    (`pm`.`inter_material_transfer_master_id` = `pt`.`inter_material_transfer_master_id`))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_material_id`
        and `rm`.`company_id` = `pm`.`company_id`))
left join `sm_product_rm_stock_details` `sprsd` on
    (`sprsd`.`product_rm_id` = `pt`.`product_material_id`
        and `sprsd`.`is_delete` = 0
        and `sprsd`.`supplier_id` = `pt`.`supplier_id`
        and `pt`.`from_godown_id` = `sprsd`.`godown_id`
        and `pt`.`from_godown_section_id` = `sprsd`.`godown_section_id`
        and `pt`.`from_godown_section_beans_id` = `sprsd`.`godown_section_beans_id`
        and `sprsd`.`day_closed` = 0))
left join `cmv_godown` `gd` on
    (`gd`.`godown_id` = `pt`.`from_godown_id`
        and `gd`.`company_id` = `pm`.`company_id`))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `pt`.`from_godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `pt`.`from_godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `cmv_godown` `gdw1` on
    (`gdw1`.`godown_id` = `pt`.`to_godown_id`
        and `gdw1`.`company_id` = `pm`.`company_id`))
left join `cm_godown_section` `gds1` on
    (`gds1`.`godown_section_id` = `pt`.`to_godown_section_id`
        and `gds1`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb1` on
    (`gdsb1`.`godown_section_beans_id` = `pt`.`to_godown_section_beans_id`
        and `gdsb1`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;