ALTER TABLE xt_weaving_beam_issue_master ADD beam_issuer_id bigint(20) NOT NULL;
ALTER TABLE xt_weaving_beam_issue_master ADD beam_cutter_id bigint(20) NULL;



create or replace
algorithm = UNDEFINED view `xtv_weaving_beam_issue_master` as
select
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_beam_issue_master_id` as `weaving_beam_issue_master_id`,
    `xt`.`beam_issuer_id` as `beam_issuer_id`,
    `xt`.`beam_cutter_id` as `beam_cutter_id`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`set_no` as `set_no`,
    `xt`.`beam_issue_date` as `beam_issue_date`,
    `xt`.`beam_issue_no` as `beam_issue_no`,
    `xt`.`beam_no` as `beam_no`,
    `xt`.`loom_no` as `loom_no`,
    `xt`.`shift_name` as `shift_name`,
    `xt`.`beam_issue_time` as `beam_issue_time`,
    `xt`.`loom_process_type` as `loom_process_type`,
    `xt`.`loom_process_charge` as `loom_process_charge`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`count` as `count`,
    `xt`.`t_ends` as `t_ends`,
    `xt`.`reed` as `reed`,
    `xt`.`pick` as `pick`,
    `xt`.`rs` as `rs`,
    `xt`.`length` as `length`,
    `xt`.`remaining_length` as `remaining_length`,
    `xt`.`cut_beam_date` as `cut_beam_date`,
    `xt`.`cut_beam_reason` as `cut_beam_reason`,
    `xt`.`loom_beam_status` as `loom_beam_status`,
    `xt`.`cut_beam_time` as `cut_beam_time`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`branch_address1` as `company_address`,
    `vb`.`branch_pincode` as `company_pincode`,
    `vb`.`branch_phone_no` as `company_phone_no`,
    `vb`.`branch_EmailId` as `company_EmailId`,
    `vb`.`branch_website` as `company_website`,
    `vb`.`branch_gst_no` as `company_gst_no`,
    `vb`.`branch_pan_no` as `company_pan_no`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `ap`.`property_name` as `shift`,
    `xbit`.`beam_inward_type` as `beam_name`,
    `e1`.`employee_name` as `beam_issuer_name`,
    `e2`.`employee_name` as `beam_cutter_name`
from
    ((((((`xt_weaving_beam_issue_master` `xt`
left join `xt_beam_inwards_table` `xbit` on
    (`xbit`.`beam_inwards_id` = `xt`.`beam_no`
        and `xbit`.`company_id` = `xt`.`company_id`
        and `xbit`.`is_delete` = 0))
left join `am_properties` `ap` on
    (`ap`.`property_id` = `xt`.`shift_name`
        and `ap`.`is_delete` = 0))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `xt`.`company_branch_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`beam_issuer_id` and `e1`.`is_delete` = 0))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `xt`.`beam_cutter_id` and `e2`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;