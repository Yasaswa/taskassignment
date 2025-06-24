ALTER TABLE xt_beam_inwards_table MODIFY COLUMN beam_type bigint(20) DEFAULT NULL NULL COMMENT 'P-Pending,A-Aprroved, R-Rejected,I-Partial Issue, C-Completed, X-Canceled Z-PreeClosed';
ALTER TABLE xt_beam_inwards_table ADD beam_inward_type varchar(100) NOT NULL;


-- xtv_beam_inwards_table source

create or replace
algorithm = UNDEFINED view `xtv_beam_inwards_table` as
select
    `xbi`.`beam_inwards_id` as `beam_inwards_id`,
    `xbi`.`company_id` as `company_id`,
    `xbi`.`financial_year` as `financial_year`,
    `xbi`.`company_branch_id` as `company_branch_id`,
    `xbi`.`beam_inwards_date` as `beam_inwards_date`,
    `xbi`.`customer_id` as `customer_id`,
    `xbi`.`section` as `section`,
    `xbi`.`beam_type` as `beam_type`,
    `xbi`.`beam_no` as `beam_no`,
    `xbi`.`beam_width` as `beam_width`,
    `xbi`.`beam_status` as `beam_status`,
    `xbi`.`beam_inward_type` as `beam_inward_type`,
    case
    	when `xbi`.`beam_status` = 'E' then 'Empty'
    	else 'Completed'
    end as `beam_status_desc`,
    case
        when `xbi`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `xbi`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xbi`.`is_active` as `is_active`,
    `xbi`.`is_delete` as `is_delete`,
    `xbi`.`created_by` as `created_by`,
    `xbi`.`created_on` as `created_on`,
    `xbi`.`modified_by` as `modified_by`,
    `xbi`.`modified_on` as `modified_on`,
    `xbi`.`deleted_by` as `deleted_by`,
    `xbi`.`deleted_on` as `deleted_on`,
    `cc`.`customer_name` as `customer_name`,
    `cc`.`customer_short_name` as `customer_short_name`,
    `amp`.`property_value` as `property_value`
from
    ((`xt_beam_inwards_table` `xbi`
left join `cm_customer` `cc` on
    (`xbi`.`customer_id` = `cc`.`customer_id`
        and `cc`.`is_delete` = 0))
left join `am_properties` `amp` on
    (`amp`.`property_id` = `xbi`.`beam_type`
        and `amp`.`is_delete` = 0))
where
    `xbi`.`is_delete` = 0;



-- xtv_beam_inwards_table_rpt source

create or replace
algorithm = UNDEFINED view `xtv_beam_inwards_table_rpt` as
select
    concat(`v`.`customer_name`, ':Customer Name:O:N:') as `customer_name`,
    concat(`v`.`beam_no`, ':Beam No:O:N:') as `beam_no`,
    concat(`v`.`beam_inwards_date`, ':Beam Inwards Date:Y:D:') as `beam_inwards_date`,
    concat(`v`.`section`, ':Section:O:N:') as `section`,
    concat(`v`.`property_value`, ':Beam Type:O:N:') as `property_value`,
    concat(`v`.`beam_width`, ':Beam Width:O:N:') as `beam_width`,
    concat(`v`.`beam_status_desc`, ':Beam Status Desc:O:N:') as `beam_status_desc`,
    concat(`v`.`beam_status`, ':Beam Status:O:N:') as `beam_status`,
    concat(`v`.`customer_short_name`, ':Customer Short Name:O:N:') as `customer_short_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
     concat(`v`.`customer_id`, ':Customer Id:O:N:') as `customer_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`beam_inwards_id`, ':Beam Inwards Id:N:N:') as `beam_inwards_id`
from
    `xtv_beam_inwards_table` `v`
limit 1;