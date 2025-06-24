
create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_master` as
select
    `stm`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `stm`.`company_id` as `company_id`,
    `stm`.`company_branch_id` as `company_branch_id`,
    `stm`.`financial_year` as `financial_year`,
    case
        `stm`.`material_type` when 'UU' then 'Unused'
        else 'Used/Replace'
    end as `material_type_desc`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `stm`.`issue_return_date` as `issue_return_date`,
    `stm`.`indent_issue_return_type_id` as `indent_issue_return_type_id`,
    `stm`.`indent_issue_return_type` as `indent_issue_return_type`,
    `dpt`.`department_name` as `department_name`,
    `sdpt`.`department_name` as `sub_department_name`,
    `e`.`employee_name` as `received_by_name`,
    `e1`.`employee_name` as `return_by_name`,
    `stm`.`department_id` as `department_id`,
    `stm`.`sub_department_id` as `sub_department_id`,
    `stm`.`issue_no` as `issue_no`,
    `std`.`issue_date` as `issue_date`,
    `stm`.`return_by_id` as `return_by_id`,
    `stm`.`issue_return_status` as `issue_return_status`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `stm`.`product_category1_id` as `product_category1_id`,
    `stm`.`product_category2_id` as `product_category2_id`,
    case
        `stm`.`issue_return_status` when 'R' then 'Received'
        else 'Pending'
    end as `issue_return_status_desc`,
    `stm`.`received_by_id` as `received_by_id`,
    `stm`.`received_date` as `received_date`,
    `stm`.`remark` as `remark`,
    case
        when `stm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `stm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `stm`.`is_active` as `is_active`,
    `stm`.`is_delete` as `is_delete`,
    `stm`.`created_by` as `created_by`,
    `stm`.`created_on` as `created_on`,
    `stm`.`modified_by` as `modified_by`,
    `stm`.`modified_on` as `modified_on`,
    `stm`.`deleted_by` as `deleted_by`,
    `stm`.`deleted_on` as `deleted_on`,
    `stm`.`material_type` as `material_type`,
    `std`.`set_no` as `set_no`
from
    (((((((`st_indent_material_issue_return_master` `stm`
left join `st_indent_material_issue_master` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`is_delete` = 0))
left join `cmv_department` `dpt` on
    (`dpt`.`department_id` = `stm`.`department_id`))
left join `cmv_department` `sdpt` on
    (`sdpt`.`department_id` = `stm`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`is_delete` = 0
        and `e`.`employee_id` = `stm`.`received_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `stm`.`return_by_id`))
left join `cm_company` `c` on
    (`c`.`company_id` = `stm`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `stm`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `stm`.`is_delete` = 0;


-- stv_indent_material_issue_return_details source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
select
    `st`.`issue_return_details_transaction_id` as `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `p`.`product_rm_name` as `product_material_name`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`material_batch_rate` as `material_batch_rate`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` as `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` as `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` as `issue_return_item_status`,
     case
        `st`.`issue_return_item_status`
        when 'R' then 'Returned'
        else 'Pending'
    end as `issue_return_item_status_desc`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`issue_return_remark` as `issue_return_remark`,
    `g`.`godown_name` as `godown_name`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `std`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `std`.`product_material_indent_weight` as `product_material_indent_weight`,
    `std`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `std`.`product_material_approved_weight` as `product_material_approved_weight`,
    `std`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `std`.`product_material_issue_weight` as `product_material_issue_weight`,
    `std`.`product_material_issue_boxes` as `product_material_issue_boxes`,
    `std`.`closing_balance_quantity` as `closing_balance_quantity`,
    `std`.`closing_balance_weight` as `closing_balance_weight`,
    `st`.`product_category1_id` as `product_category1_id`,
    `st`.`product_category2_id` as `product_category2_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `st`.`cone_per_wt` as `cone_per_wt`,
    `st`.`goods_receipt_no` as `goods_receipt_no`,
    `st`.`supplier_id` as `supplier_id`,
    `st`.`creel_no` as `creel_no`
from
    ((((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `stv_indent_material_issue_details` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`product_material_id` = `st`.`product_material_id`
        and `st`.`issue_batch_no` = `std`.`issue_batch_no`
        and (`st`.`creel_no` is null
            or `st`.`creel_no` = `std`.`creel_no`)
        and `st`.`godown_id` = `std`.`godown_id`
        and `st`.`godown_section_id` = `std`.`godown_section_id`
        and `st`.`godown_section_beans_id` = `std`.`godown_section_beans_id`
        and `std`.`is_delete` = 0))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
        and `gsb`.`is_delete` = 0))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `st`.`godown_section_id`
        and `gs`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `st`.`godown_id`
        and `g`.`is_delete` = 0))
left join `sm_product_rm` `p` on
    (`p`.`product_rm_id` = `st`.`product_material_id`
        and `p`.`is_delete` = 0))
where
    `st`.`is_delete` = 0;




-- stv_indent_material_return_to_store_master_rpt source

create or replace
algorithm = UNDEFINED view `stv_indent_material_return_to_store_master_rpt` as
select
    concat(`v`.`issue_return_no`, ':Issue Return No:Y:T:') as `issue_return_no`,
    concat(`v`.`issue_return_date`, ':Issue Return Date :Y:D:') as `issue_return_date`,
    concat(`v`.`material_type_desc`, ':Material Type:Y:H:(Used/Replace,Unused)') as `material_type_desc`,
    concat(`v`.`indent_issue_return_type`, ':Issue Return Type:O:N:') as `indent_issue_return_type`,
    concat(`v`.`department_name`, ':Department:Y:T:') as `department_name`,
    concat(`v`.`sub_department_name`, ':Sub Department:Y:T:') as `sub_department_name`,
    concat(`v`.`issue_no`, ':Issue No:Y:T:') as `issue_no`,
    concat(`v`.`return_by_name`, ':Return By:Y:T:') as `return_by_name`,
    concat(`v`.`issue_return_status_desc`, ':Return Status:Y:H:(Pending,Returned)') as `issue_return_status_desc`,
    concat(`v`.`received_date`, ':Issue Received Date:Y:D:') as `received_date`,
    concat(`v`.`received_by_name`, ':Received By:Y:T:') as `received_by_name`,
    concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`issue_return_status`, ':Issue Return Status:N:N:') as `issue_return_status`,
    concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') as `issue_return_master_transaction_id`
from
    `stv_indent_material_issue_return_master` `v`
limit 1;


create or replace
algorithm = UNDEFINED view `stv_indent_material_return_to_store_details_rpt` as
select
    concat(`v`.`issue_return_no`, ':Issue Return No:Y:T:') as `issue_return_no`,
    concat(`v`.`product_material_name`, ':Product Material Name:Y:T:') as `product_material_name`,
    concat(`v`.`product_material_issue_return_quantity`, ':Return Quantity :O:N:') as `product_material_issue_return_quantity`,
    concat(`v`.`product_material_receipt_quantity`, ': Receipt Quantity :O:N:') as `product_material_receipt_quantity`,
    concat(`v`.`material_batch_rate`, ': Batch Rate :Y:T:') as `material_batch_rate`,
    concat(`v`.`godown_section_beans_name`, ':Godown(Location):Y:T:') as `godown_section_beans_name`,
    concat(`v`.`issue_return_item_status_desc`, ':Return Status:Y:H:(Pending,Returned)') as `issue_return_item_status_desc`,
    concat(`v`.`issue_return_remark`, ':Remark:Y:N:') as `issue_return_remark`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:N:N:') as `issue_return_master_transaction_id`,
    concat(`v`.`issue_return_details_transaction_id`, ':Issue Return Details Id:O:N:') as `issue_return_details_transaction_id`,
    concat(`v`.`product_material_id`, ':Product Material Id:N:N:') as `product_material_id`,
    concat(`v`.`godown_id`, ':Godown Id:N:N:') as `godown_id`,
    concat(`v`.`godown_section_id`, ':Godown Section Id:N:N:') as `godown_section_id`,
    concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
from
    `stv_indent_material_issue_return_details` `v`
limit 1;