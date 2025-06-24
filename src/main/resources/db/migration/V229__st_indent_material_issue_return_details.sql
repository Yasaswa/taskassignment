


ALTER TABLE st_indent_material_issue_return_details ADD creel_no varchar(100) NULL;


-- stv_indent_material_issue_return_details source

create or REPLACE algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
select
    `st`.`issue_return_details_transaction_id` as `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `p`.`product_rm_name` as `product_material_name`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` as `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` as `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` as `issue_return_item_status`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`issue_return_remark` as `issue_return_remark`,
    `gsb`.`godown_name` as `godown_name`,
    `gsb`.`godown_section_name` as `godown_section_name`,
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
    `st`.`creel_no` as `creel_no`
from
    ((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `stv_indent_material_issue_details` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`product_material_id` = `st`.`product_material_id`
        and `st`.`issue_batch_no` = `std`.`issue_batch_no`
        and `st`.`goods_receipt_no` = `std`.`goods_receipt_no`
        and `std`.`is_delete` = 0))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
left join `sm_product_rm` `p` on
    (`p`.`product_rm_id` = `st`.`product_material_id`))
where
    `st`.`is_delete` = 0;




    -- stv_indent_material_issue_return_details_rpt source
    
    create or REPLACE algorithm = UNDEFINED view `stv_indent_material_issue_return_details_rpt` as
    select
        concat(`v`.`issue_return_no`, ':Issue Return No:O:N:') as `issue_return_no`,
        concat(`v`.`product_material_name`, ':Product Material Name:O:N:') as `product_material_name`,
        concat(`v`.`creel_no`, ':Creel No:O:N:') as `creel_no`,
        concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
        concat(`v`.`product_material_issue_return_quantity`, ':Material Issued Return Quantity :O:N:') as `product_material_issue_return_quantity`,
        concat(`v`.`product_material_issue_return_weight`, ':Material Issued Return Weight:O:N:') as `product_material_issue_return_weight`,
        concat(`v`.`product_material_issue_return_boxes`, ':Material Issued Return Boxes:O:N:') as `product_material_issue_return_boxes`,
        concat(`v`.`product_material_receipt_quantity`, ':Material Receipt Quantity Id:O:N:') as `product_material_receipt_quantity`,
        concat(`v`.`product_material_receipt_weight`, ':Material Receipt Weight:O:N:') as `product_material_receipt_weight`,
        concat(`v`.`product_material_receipt_boxes`, ':Material Receipt Boxes Id:O:N:') as `product_material_receipt_boxes`,
        concat(`v`.`issue_return_remark`, ':Issue Return No:O:N:') as `issue_return_remark`,
        concat(`v`.`issue_return_item_status`, ':Issue No:O:N:') as `issue_return_item_status`,
        concat(`v`.`product_material_indent_quantity`, ':Material Indent Quantity:O:N:') as `product_material_indent_quantity`,
        concat(`v`.`product_material_indent_weight`, ':Material Indent Weight:O:N:') as `product_material_indent_weight`,
        concat(`v`.`product_material_approved_quantity`, ':Material Approved Quantity:O:N:') as `product_material_approved_quantity`,
        concat(`v`.`product_material_approved_weight`, ':Material Approved Weight:O:N:') as `product_material_approved_weight`,
        concat(`v`.`product_material_issue_quantity`, ':Material Issue Quantity:O:N:') as `product_material_issue_quantity`,
        concat(`v`.`product_material_issue_weight`, ':Material Isuue Weight:O:N:') as `product_material_issue_weight`,
        concat(`v`.`product_material_issue_boxes`, ':Material Isuue Boxes:O:N:') as `product_material_issue_boxes`,
        concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') as `issue_return_master_transaction_id`,
        concat(`v`.`issue_return_details_transaction_id`, ':Issue Return Details Id:O:N:') as `issue_return_details_transaction_id`,
        concat(`v`.`product_material_id`, ':Product Material Id:O:N:') as `product_material_id`,
        concat(`v`.`godown_name`, ':Godown Name:O:N:') as `godown_name`,
        concat(`v`.`godown_section_name`, ':Godown Section Name:O:N:') as `godown_section_name`,
        concat(`v`.`godown_section_beans_name`, ':Godown Section Beans Name:O:N:') as `godown_section_beans_name`,
        concat(`v`.`godown_id`, ':Godown Id:O:N:') as `godown_id`,
        concat(`v`.`godown_section_id`, ':Godown Section Id:O:N:') as `godown_section_id`,
        concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:O:N:') as `godown_section_beans_id`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
        concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
        concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
        concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
        concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
        concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
        concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
    from
        `stv_indent_material_issue_return_details` `v`
    limit 1;
