-- erp_dev_temp.stv_indent_material_issue_details_rpt source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_details_rpt` as
select
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:Y:C:stv_indent_material_issue_summary:O') as `issue_no`,
    concat(ifnull(`v`.`issue_date`, ''), ':Issue Date:Y:D:') as `issue_date`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version:O:N:') as `issue_version`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:O:N:') as `godown_section_name`,
    concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:O:N:') as `godown_section_beans_name`,
    concat(ifnull(`v`.`issue_item_status_desc`, ''), ':Issue Status:Y:H:(Pending,Completed)') as `issue_item_status_desc`,
    concat(ifnull(`v`.`product_material_indent_quantity`, ''), ':Material Requisition Quantity:O:N:') as `product_material_indent_quantity`,
    concat(ifnull(`v`.`product_material_indent_weight`, ''), ':Material Requisition Weight:O:N:') as `product_material_indent_weight`,
    concat(ifnull(`v`.`product_material_approved_quantity`, ''), ':Material Approved Quantity:O:N:') as `product_material_approved_quantity`,
    concat(ifnull(`v`.`product_material_approved_weight`, ''), ':Material Approved Weight:O:N:') as `product_material_approved_weight`,
    concat(ifnull(`v`.`product_material_rejected_quantity`, ''), ':Material Rejected Quantity:O:N:') as `product_material_rejected_quantity`,
    concat(ifnull(`v`.`product_material_rejected_weight`, ''), ':Material Rejected Weight:O:N:') as `product_material_rejected_weight`,
    concat(ifnull(`v`.`product_material_issue_quantity`, ''), ':Material Issue Quantity:O:N:') as `product_material_issue_quantity`,
    concat(ifnull(`v`.`product_material_issue_weight`, ''), ':Material Issue Weight:O:N:') as `product_material_issue_weight`,
    concat(ifnull(`v`.`product_material_issue_return_quantity`, ''), ':Material Issue Return Quantity:O:N:') as `product_material_issue_return_quantity`,
    concat(ifnull(`v`.`product_material_issue_return_weight`, ''), ':Material Return Weight:O:N:)') as `product_material_issue_return_weight`,
    concat(ifnull(`v`.`product_material_receipt_quantity`, ''), ':Material Receipt Quantity:O:N:)') as `product_material_receipt_quantity`,
    concat(ifnull(`v`.`product_material_receipt_weight`, ''), ':Material Receipt Weight:O:N:)') as `product_material_receipt_weight`,
    concat(ifnull(`v`.`material_batch_rate`, ''), ':Material Rate:Y:T:') as `material_batch_rate`,
    concat(ifnull(`v`.`material_issue_amount`, ''), ':Material Issue Amount:Y:T:') as `material_issue_amount`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:C:stv_indent_master_summary:F') as `indent_no`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indented By Name:Y:C:cmv_employee_list:F') as `indented_by_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:C:cmv_customer:F') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:O:N:') as `customer_order_no`,
    concat(ifnull(`v`.`customer_state_name`, ''), ':State Name:O:N:') as `customer_state_name`,
    concat(ifnull(`v`.`cust_branch_gst_no`, ''), ':Branch Gst No:O:N:') as `cust_branch_gst_no`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(ifnull(`v`.`issue_remark`, ''), ':Remark:O:N:') as `issue_remark`,
    concat(ifnull(`v`.`issue_batch_no`, ''), ':Issue Batch No.:Y:T:') as `issue_batch_no`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`issue_master_transaction_id`, ''), ':Issue Master Transaction_Id:O:N:') as `issue_master_transaction_id`,
    concat(ifnull(`v`.`issue_details_transaction_id`, ''), ':Issue Details Transaction_Id:O:N:') as `issue_details_transaction_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id Id:N:N:') as `indented_by_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') as `issued_by_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
    concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') as `godown_section_id`,
    concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:N:N:') as `field_name`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version Id:N:N:') as `field_id`
from
    `stv_indent_material_issue_details` `v`
limit 1;



ALTER TABLE st_indent_material_issue_return_details ADD goods_receipt_no varchar(100) NULL;


-- erp_dev_temp.stv_indent_material_issue_return_details source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
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
    `st`.`goods_receipt_no` as `goods_receipt_no`
from
    ((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `st_indent_material_issue_details` `std` on
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
