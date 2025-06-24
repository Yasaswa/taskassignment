    ALTER TABLE hm_leaves_applications ADD punch_code varchar(100) NULL;
    ALTER TABLE hm_leaves_applications CHANGE punch_code punch_code varchar(100) NULL AFTER employee_code;


    -- erp_development.hmv_leaves_applications source

   create or replace
   algorithm = UNDEFINED view `hmv_leaves_applications` as
   select
       `la`.`leaves_applications_id` as `leaves_applications_id`,
       `la`.`leaves_application_date` as `leaves_application_date`,
       `la`.`financial_year` as `financial_year`,
       `la`.`employee_type` as `employee_type`,
       `la`.`employee_code` as `employee_code`,
       `la`.`punch_code` as `punch_code`,
       `e1`.`employee_name` as `employee_name`,
       `e1`.`designation_name` as `designation_name`,
       `e1`.`department_name` as `department_name`,
       `e1`.`sub_department_name` as `sub_department_name`,
       `lt`.`leave_type_name` as `leave_type_name`,
       `lt`.`leave_type_code` as `leave_type_code`,
       `lt`.`leave_type_paid_flag` as `leave_type_paid_flag`,
       `la`.`leaves_requested_from_date` as `leaves_requested_from_date`,
       `la`.`leaves_requested_to_date` as `leaves_requested_to_date`,
       `la`.`leaves_approved_from_date` as `leaves_approved_from_date`,
       `la`.`leaves_approved_to_date` as `leaves_approved_to_date`,
       `la`.`leave_sandwich` as `leave_sandwich`,
       `la`.`leaves_applied_days` as `leaves_applied_days`,
       `la`.`leaves_sanction_days` as `leaves_sanction_days`,
       `la`.`leaves_rejection_days` as `leaves_rejection_days`,
       `la`.`leave_status` as `leave_status`,
       `e2`.`employee_name` as `entered_by_name`,
       `e4`.`employee_name` as `work_handover_to`,
       `e3`.`employee_name` as `approved_by_name`,
       `e5`.`employee_name` as `reporting_to_name`,
       `la`.`approved_date` as `approved_date`,
       `la`.`leave_reason` as `leave_reason`,
       `la`.`leave_approve_remark` as `leave_approve_remark`,
       `la`.`leaves_transaction_id` as `leaves_transaction_id`,
       `la`.`employee_id` as `employee_id`,
       `la`.`leave_type_id` as `leave_type_id`,
       `la`.`work_handover_id` as `work_handover_id`,
       `la`.`reporting_to` as `reporting_to`,
       `la`.`sub_department_id` as `sub_department_id`,
       `la`.`department_id` as `department_id`,
       case
           `la`.`is_active` when 1 then 'Active'
           else 'In Active'
       end as `Active`,
       case
           `la`.`is_delete` when 1 then 'Yes'
           else 'No'
       end as `Deleted`,
       `la`.`is_active` as `is_active`,
       `la`.`is_delete` as `is_delete`,
       `la`.`created_by` as `created_by`,
       `la`.`created_on` as `created_on`,
       `la`.`modified_by` as `modified_by`,
       `la`.`modified_on` as `modified_on`,
       `la`.`deleted_by` as `deleted_by`,
       `la`.`deleted_on` as `deleted_on`,
       `la`.`company_id` as `company_id`,
       `la`.`leaves_transaction_id` as `field_id`,
       `la`.`leaves_applications_id` as `field_name`
   from
       (((((((`hm_leaves_applications` `la`
   left join `cmv_company_summary` `v` on
       (`v`.`company_id` = `la`.`company_id`))
   left join `hmv_leave_type` `lt` on
       (`la`.`leave_type_id` = `lt`.`leave_type_id`))
   left join `cmv_employee` `e1` on
       (`e1`.`company_id` = `la`.`company_id`
           and `e1`.`employee_code` = `la`.`employee_code`))
   left join `cmv_employee_list` `e2` on
       (`e2`.`company_id` = `la`.`company_id`
           and `e2`.`employee_id` = `la`.`applied_by_id`))
   left join `cmv_employee_list` `e3` on
       (`e3`.`employee_id` = `la`.`approved_by_id`))
   left join `cmv_employee_list` `e4` on
       (`e4`.`company_id` = `la`.`company_id`
           and `e4`.`employee_id` = `la`.`work_handover_id`))
   left join `cmv_employee_list` `e5` on
       (`e5`.`company_id` = `la`.`company_id`
           and `e5`.`employee_id` = `la`.`reporting_to`))
   where
       `la`.`is_delete` = 0;

