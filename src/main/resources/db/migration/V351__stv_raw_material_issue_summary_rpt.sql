-- stv_indent_material_issue_summary_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_raw_material_issue_summary_rpt` AS
select
    concat(ifnull(`v`.`issue_group_type`, ''), ':Issue Group Type:Y:P:IssueGroupTypes') AS `issue_group_type`,
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:Y:C:stv_indent_material_issue_summary:O') AS `issue_no`,
    concat(ifnull(`v`.`issue_date`, ''), ':Issue Date:Y:D:') AS `issue_date`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version:O:N:') AS `issue_version`,
    concat(ifnull(`v`.`issue_status_desc`, ''), ':Issue Status:Y:H:(Material Issue,Completed,Partial Issue,Accepted,Approved,Pending)') AS `issue_status_desc`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No :O:T:') AS `set_no`,
	concat(ifnull(`v`.`sales_type`, ''), ':Sales Type :O:T:') AS `sales_type`,
    concat(ifnull(`v`.`indent_source_desc`, ''), ':Issue Source:Y:H:(SO Based,Direct,Indent Based,)') AS `indent_source_desc`,
    concat(ifnull(`v`.`requisition_by_name`, ''), ':Requisition By Name:Y:C:cmv_employee:F') AS `requisition_by_name`,
    concat(ifnull(`v`.`requisition_date`, ''), ':Requisition Date:Y:D:') AS `requisition_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By Name:Y:C:cmv_employee:F') AS `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`issued_by_name`, ''), ':Issue By Name:Y:C:cmv_employee:F') AS `issued_by_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') AS `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') AS `expected_schedule_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:C:cmv_customer:F') AS `customer_name`,
    concat(ifnull(`v`.`customer_state_name`, ''), ':Customer State Name:O:N:') AS `customer_state_name`,
    concat(ifnull(`v`.`cust_branch_gst_no`, ''), ':Customer Branch Gst No:O:N:') AS `cust_branch_gst_no`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`issue_status`, ''), ':Issue Status:O:N:') AS `issue_status`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
    concat(ifnull(`v`.`issue_master_transaction_id`, ''), ':Issue Master Transaction_Id:O:N:') AS `issue_master_transaction_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') AS `issued_by_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') AS `customer_id`,
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:N:N:') AS `field_name`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version Id:N:N:') AS `field_id`
from
    `stv_indent_material_issue_summary` `v`
limit 1;



CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_summary_rpt` AS
select
    concat(ifnull(`v`.`issue_group_type`, ''), ':Issue Group Type:Y:P:IssueGroupTypes') AS `issue_group_type`,
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:Y:T') AS `issue_no`,
    concat(ifnull(`v`.`issue_date`, ''), ':Issue Date:Y:D:') AS `issue_date`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version:O:N:') AS `issue_version`,
    concat(ifnull(`v`.`issue_status_desc`, ''), ':Issue Status:Y:H:(Material Issue,Completed,Partial Issue,Accepted,Approved,Pending)') AS `issue_status_desc`,
    concat(ifnull(`v`.`indent_source_desc`, ''), ':Issue Source:Y:H:(SO Based,Direct,Indent Based,)') AS `indent_source_desc`,
    concat(ifnull(`v`.`requisition_by_name`, ''), ':Requisition By Name:Y:T') AS `requisition_by_name`,
    concat(ifnull(`v`.`requisition_date`, ''), ':Requisition Date:Y:D:') AS `requisition_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By Name:Y:T') AS `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`issued_by_name`, ''), ':Issue By Name:Y:T') AS `issued_by_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') AS `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') AS `expected_schedule_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:C:cmv_customer:F') AS `customer_name`,
    concat(ifnull(`v`.`customer_state_name`, ''), ':Customer State Name:O:N:') AS `customer_state_name`,
    concat(ifnull(`v`.`cust_branch_gst_no`, ''), ':Customer Branch Gst No:O:N:') AS `cust_branch_gst_no`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`issue_status`, ''), ':Issue Status:O:N:') AS `issue_status`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
    concat(ifnull(`v`.`issue_master_transaction_id`, ''), ':Issue Master Transaction_Id:O:N:') AS `issue_master_transaction_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') AS `issued_by_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') AS `customer_id`,
    concat(ifnull(`v`.`issue_no`, ''), ':Issue No:N:N:') AS `field_name`,
    concat(ifnull(`v`.`issue_version`, ''), ':Issue Version Id:N:N:') AS `field_id`
from
    `stv_indent_material_issue_summary` `v`
limit 1;