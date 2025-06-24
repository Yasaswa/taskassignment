

-- add priority columns --

ALTER TABLE st_indent_master ADD indent_priority varchar(1) DEFAULT 'L' NULL COMMENT 'radio button for indent priority L-Low-priority , H- High-priority';



-- stv_indent_master_summary source

create or replace
algorithm = UNDEFINED view `stv_indent_master_summary` as
select
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    case
        `st`.`indent_priority` when 'H' then 'High-Priority'
        else 'Low-Priority'
    end as `indent_priority_desc`,
    case
        `st`.`indent_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end as `indent_source_desc`,
    `vp`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    `e`.`employee_name` as `indented_by_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `e2`.`employee_name` as `accepted_by_name`,
    `st`.`approved_date` as `approved_date`,
    case
        `st`.`indent_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
        else 'Pending'
    end as `indent_status_desc`,
    `c`.`customer_name` as `customer_name`,
    `c`.`state_name` as `customer_state_name`,
    `c`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `c`.`cust_branch_EmailId` as `cust_branch_EmailId`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`customer_order_Date` as `customer_order_date`,
    `st`.`expected_schedule_date` as `expected_schedule_date`,
    `fc`.`cost_center_name` as `cost_center_name`,
    `e`.`email_id1` as `indented_by_email`,
    `e1`.`email_id1` as `approved_by_email`,
    case
        `st`.`indent_transaction_type` when 'A' then 'BOM Based'
        else 'Manual'
    end as `indent_transaction_type_desc`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`remark` as `remark`,
    `st`.`indent_no` as `field_name`,
    `st`.`indent_version` as `field_id`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`indent_master_id` as `indent_master_id`,
    `st`.`indent_type_id` as `indent_type_id`,
    `st`.`customer_id` as `customer_id`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `c`.`cust_branch_pan_no` as `cust_branch_pan_no`,
    `st`.`department_id` as `department_id`,
    `st`.`sub_department_id` as `sub_department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `st`.`approved_by_id` as `approved_by_id`,
    `st`.`accepted_by_id` as `accepted_by_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`indent_status` as `indent_status`,
    `st`.`po_status` as `po_status`,
    `st`.`grn_status` as `grn_status`,
    `st`.`issue_status` as `issue_status`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`indent_transaction_type` as `indent_transaction_type`,
    `st`.`Indent_type` as `indent_type`,
    `p`.`product_type_short_name` as `indent_type_short_name`,
    `st`.`indent_source` as `indent_source`,
    `st`.`indent_priority` as `indent_priority`

from
    (((((((((`st_indent_master` `st`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`))
left join `cmv_customer` `c` on
    (`c`.`customer_id` = `st`.`customer_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `st`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `st`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`indented_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `st`.`approved_by_id`))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `st`.`accepted_by_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `st`.`indent_type_id`))
left join `fmv_cost_center` `fc` on
    (`fc`.`cost_center_id` = `st`.`cost_center_id`
        and `fc`.`company_id` = `st`.`company_id`))
where
    `st`.`is_delete` = 0
order by
    `st`.`indent_no`,
    `st`.`indent_version`;




    -- stv_indent_master_summary_rpt source

    create or replace
    algorithm = UNDEFINED view `stv_indent_master_summary_rpt` as
    select
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:C:stv_indent_master_summary:F') as `indent_no`,
        concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
        concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') as `indent_priority_desc`,
        concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
        concat(ifnull(`v`.`indent_status_desc`, ''), ':Indent Status:Y:H:(Pending,Approved,Rejected,Completed,Cenceled)') as `indent_status_desc`,
        concat(ifnull(`v`.`indent_source_desc`, ''), ':Indent Source:Y:H:(SO Based,Direct,Internal)') as `indent_source_desc`,
        concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
        concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
        concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
        concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
        concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
        concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:C:cmv_employee_list:F') as `indented_by_name`,
        concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
        concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
        concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
        concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
        concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
        concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
        concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
        concat(ifnull(`v`.`grn_status`, ''), ':GRN Status:O:N:') as `grn_status`,
        concat(ifnull(`v`.`indent_priority`, ''), ':Priority:O:N:') as `indent_priority`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') as `indent_master_id`,
        concat(ifnull(`v`.`indent_type_id`, ''), ':Intdent Type Id:N:N:') as `indent_type_id`,
        concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
        concat(ifnull(`v`.`indented_by_id`, ''), ':Indeneted By Id:N:N:') as `indented_by_id`,
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:N:N:') as `field_name`,
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:N:N:') as `field_id`
    from
        `stv_indent_master_summary` `v`
    limit 1;