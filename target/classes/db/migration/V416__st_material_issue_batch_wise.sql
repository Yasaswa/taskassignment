




CREATE TABLE `st_material_issue_batch_wise` (
  `material_issue_batch_wise_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '*read only back end auto generated*',
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session*',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session*',
  `transaction_no` varchar(50) NOT NULL,
  `transaction_date` date NOT NULL,
  `set_no` varchar(100) DEFAULT NULL,
  `goods_receipt_no` varchar(50) NOT NULL,
  `batch_no` varchar(100) DEFAULT NULL,
  `indent_no` varchar(255) DEFAULT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `product_material_id` varchar(20) NOT NULL,
  `cone_per_wt` decimal(18,4) DEFAULT 0.0000,
  `requisition_no_boxes` decimal(18,4) DEFAULT 0.0000,
  `requisition_quantity` bigint(20) NOT NULL,
  `requisition_weight` decimal(18,4) DEFAULT 0.0000,
  `issue_no_boxes` decimal(18,4) DEFAULT 0.0000,
  `issue_quantity` bigint(20) NOT NULL,
  `issue_weight` decimal(18,4) DEFAULT 0.0000,
  `product_material_unit_id` bigint(20) DEFAULT NULL,
  `godown_id` bigint(20) NOT NULL DEFAULT 2 COMMENT '* NO Front End Only  Back End Entry Data will come from respective transaction will updated on transaction to transaction  level  *',
  `godown_section_id` bigint(20) DEFAULT 2 COMMENT '* NO Front End Only  Back End Entry Data will come from respective transaction will updated on transaction to transaction  level  *',
  `godown_section_beans_id` bigint(20) DEFAULT 2 COMMENT '* NO Front End Only  Back End Entry Data will come from respective transaction will updated on transaction to transaction  level  *',
  `issue_status` varchar(2) DEFAULT 'P' COMMENT 'P-Pending, C-Completed, I- Partial Issue, MI- Material Issued',
  `issue_requisition_type` varchar(2) DEFAULT 'M',
  `remark` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT null,
  PRIMARY KEY (`material_issue_batch_wise_id`)
);



ALTER TABLE st_material_issue_batch_wise ADD department_id BIGINT(20) DEFAULT 0 NULL COMMENT 'department whome issued';
ALTER TABLE st_material_issue_batch_wise CHANGE department_id department_id BIGINT(20) DEFAULT 0 NULL COMMENT 'department whome issued' AFTER issue_status;
ALTER TABLE st_material_issue_batch_wise ADD sub_department_id BIGINT(20) DEFAULT 0 NULL COMMENT 'subdepartment to whome issued';
ALTER TABLE st_material_issue_batch_wise CHANGE sub_department_id sub_department_id BIGINT(20) DEFAULT 0 NULL COMMENT 'subdepartment to whome issued' AFTER department_id;





-- stv_indent_material_issue_details source

create or REPLACE algorithm = UNDEFINED view `stv_indent_material_issue_details` as
select
    `st`.`issue_no` as `issue_no`,
    `st`.`issue_date` as `issue_date`,
    `st`.`issue_version` as `issue_version`,
    `st`.`product_category1_id` as `product_category1_id`,
    `st`.`product_category2_id` as `product_category2_id`,
    `dp`.`department_name` as `department_name`,
    `sdp`.`department_name` as `sub_department_name`,
    case
        `st`.`issue_item_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        else 'Pending'
    end as `issue_item_status_desc`,
    `e`.`employee_name` as `indented_by_name`,
    `cu`.`customer_name` as `customer_name`,
    `v`.`expected_schedule_date` as `expected_schedule_date`,
    `st`.`customer_order_no` as `customer_order_no`,
    `pdt`.`product_type_group` as `product_type_group`,
    `st`.`product_material_id` as `product_material_id`,
    `rm`.`product_rm_code` as `product_material_code`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`actual_count` as `actual_count`,
    `st`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` as `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` as `product_material_issue_weight`,
    `st`.`product_material_issue_boxes` as `product_material_issue_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `rm`.`product_rm_name` as `product_rm_name`,
    `rm`.`product_rm_drawing_no` as `product_rm_drawing_no`,
    `rm`.`product_rm_tech_spect` as `product_rm_tech_spect`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `pdt`.`product_type_name` as `product_type_name`,
    `u`.`product_unit_name` as `product_material_unit_name`,
    `rmc`.`lead_time` as `product_lead_time`,
    `ps`.`product_material_stock_quantity` as `product_material_stock_quantity`,
    `ps`.`product_material_stock_weight` as `product_material_stock_weight`,
    `ps`.`reserve_quantity` as `reserve_quantity`,
    `ps`.`reserve_weight` as `reserve_weight`,
    ifnull(`sd`.`closing_balance_quantity`, 0) as `closing_balance_quantity`,
    ifnull(`sd`.`closing_balance_weight`, 0) as `closing_balance_weight`,
    ifnull(`st`.`cone_per_wt`, 0) as `cone_per_wt`,
    ifnull(`sd`.`closing_no_of_boxes`, 0) as `closing_no_of_boxes`,
    `st`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` as `product_material_approved_weight`,
    `st`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` as `product_material_rejected_weight`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_std_weight` as `product_std_weight`,
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    `st`.`issue_item_status` as `issue_item_status`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`material_batch_rate` as `material_batch_rate`,
    `st`.`product_material_issue_quantity` * `st`.`material_batch_rate` as `material_issue_amount`,
    `fmp`.`profit_center_name` as `profit_center_name`,
    `fm`.`cost_center_name` as `cost_center_name`,
    `st`.`routing_code` as `routing_code`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`issue_remark` as `issue_remark`,
    `gd`.`godown_name` as `godown_name`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`goods_receipt_no` as `goods_receipt_no`,
    `st`.`profit_center_id` as `profit_center_id`,
    `st`.`creel_no` as `creel_no`,
    `st`.`set_no` as `set_no`,
    `sd`.`supplier_id` as `supplier_id`,
    `st`.`issue_requisition_type` as `issue_requisition_type`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'Inactive'
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
    `v`.`customer_id` as `customer_id`,
    `v`.`department_id` as `department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `v`.`issued_by_id` as `issued_by_id`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `v`.`indent_issue_type_id` as `product_type_id`,
    `rmt`.`product_material_type_id` as `product_material_type_id`,
    `rmt`.`product_material_grade_id` as `product_material_grade_id`,
    `rm`.`product_rm_packing_id` as `product_material_packing_id`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `st`.`issue_details_transaction_id` as `issue_details_transaction_id`,
    `st`.`issue_master_transaction_id` as `issue_master_transaction_id`,
    `st`.`indent_details_id` as `indent_details_id`,
    `v`.`sub_department_id` as `sub_department_id`,
    `st`.`issue_no` as `field_name`,
    `st`.`issue_version` as `field_id`
from
    (((((((((((((((((((`st_indent_material_issue_details` `st`
left join `cm_company` `c` on
    (`c`.`company_id` = `st`.`company_id`
        and `st`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `st`.`company_branch_id`
        and `cb`.`company_id` = `st`.`company_id`
        and `st`.`is_delete` = 0))
join `st_indent_material_issue_master` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`
        and `v`.`issue_master_transaction_id` = `st`.`issue_master_transaction_id`
        and `v`.`is_delete` = 0))
left join `cm_customer` `cu` on
    (`cu`.`customer_id` = `v`.`customer_id`
        and `v`.`is_delete` = 0))
left join `cm_department` `dp` on
    (`dp`.`department_id` = `v`.`department_id`))
left join `cm_department` `sdp` on
    (`sdp`.`department_id` = `v`.`sub_department_id`))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `st`.`product_material_id`
        and `rm`.`is_delete` = 0))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `st`.`product_material_unit_id`
        and `st`.`is_delete` = 0))
left join `sm_product_rm_commercial` `rmc` on
    (`rmc`.`is_delete` = 0
        and `rmc`.`product_rm_id` = `st`.`product_material_id`
        and `rmc`.`is_delete` = 0))
left join `sm_product_rm_technical` `rmt` on
    (`rmt`.`product_rm_id` = `st`.`product_material_id`
        and `rmt`.`is_delete` = 0))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `v`.`indent_issue_type_id`
        and `pdt`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`indented_by_id`
        and `e`.`is_delete` = 0))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `st`.`godown_id`
        and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `st`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `fm_cost_center` `fm` on
    (`fm`.`cost_center_id` = `st`.`cost_center_id`
        and `fm`.`is_delete` = 0))
left join `fm_profit_center` `fmp` on
    (`fmp`.`profit_center_id` = `st`.`profit_center_id`
        and `fmp`.`is_delete` = 0
        and `fmp`.`is_active` = 1))
left join (
    select
        `sm_product_rm_stock_summary`.`product_rm_id` as `product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id` as `godown_id`,
        `sm_product_rm_stock_summary`.`company_id` as `company_id`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) as `product_material_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) as `product_material_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`reserve_quantity`) as `reserve_quantity`,
        sum(`sm_product_rm_stock_summary`.`reserve_weight`) as `reserve_weight`
    from
        `sm_product_rm_stock_summary`
    group by
        `sm_product_rm_stock_summary`.`product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id`,
        `sm_product_rm_stock_summary`.`company_id`) `ps` on
    (`ps`.`product_rm_id` = `st`.`product_material_id`
        and `ps`.`godown_id` = `st`.`godown_id`
        and `ps`.`company_id` = `st`.`company_id`))
left join (
    select
        `sm_product_rm_stock_details`.`product_rm_id` as `product_rm_id`,
        `sm_product_rm_stock_details`.`batch_no` as `batch_no`,
        `sm_product_rm_stock_details`.`day_closed` as `day_closed`,
        `sm_product_rm_stock_details`.`godown_id` as `godown_id`,
        `sm_product_rm_stock_details`.`godown_section_id` as `godown_section_id`,
        `sm_product_rm_stock_details`.`godown_section_beans_id` as `godown_section_beans_id`,
        `sm_product_rm_stock_details`.`supplier_id` as `supplier_id`,
        sum(`sm_product_rm_stock_details`.`closing_no_of_boxes`) as `closing_no_of_boxes`,
        sum(`sm_product_rm_stock_details`.`closing_balance_quantity`) as `closing_balance_quantity`,
        sum(`sm_product_rm_stock_details`.`closing_balance_weight`) as `closing_balance_weight`
    from
        `sm_product_rm_stock_details`
    where
        `sm_product_rm_stock_details`.`day_closed` = 0
        and `sm_product_rm_stock_details`.`is_delete` = 0
    group by
        `sm_product_rm_stock_details`.`company_id`,
        `sm_product_rm_stock_details`.`product_rm_id`,
        `sm_product_rm_stock_details`.`batch_no`,
        `sm_product_rm_stock_details`.`godown_id`,
        `sm_product_rm_stock_details`.`godown_section_id`,
        `sm_product_rm_stock_details`.`godown_section_beans_id`) `sd` on
    (`sd`.`product_rm_id` = `st`.`product_material_id`
        and `sd`.`batch_no` = `st`.`issue_batch_no`
        and `sd`.`godown_id` = `st`.`godown_id`
        and `sd`.`godown_section_id` = `st`.`godown_section_id`
        and `sd`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
where
    `st`.`is_delete` = 0;



 -- stv_indent_material_issue_details_rpt source

 create or REPLACE algorithm = UNDEFINED view `stv_indent_material_issue_details_rpt` as
 select
     concat(ifnull(`v`.`issue_no`, ''), ':Issue No:Y:T') as `issue_no`,
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