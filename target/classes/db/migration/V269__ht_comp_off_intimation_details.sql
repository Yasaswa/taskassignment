ALTER TABLE ht_comp_off_intimation_details ADD comp_holiday_type varchar(100) DEFAULT 'C' NULL;
UPDATE am_modules_forms
SET
    modules_forms_name = "Comp.Off & Holiday Present Leave",
    display_name = "Comp.Off & Holiday Present Leave",
    page_header = "Comp.Off & Holiday Present Leave"
WHERE modules_forms_id = 655;

INSERT INTO hm_leave_type (company_id,leave_type_name,leave_type_code,leave_type_paid_flag,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (3,'Present on Holiday Leave','PHL','Paid',1,0,'9016569017','2025-01-09 16:41:05.000',NULL,'2025-01-09 16:41:05.000',NULL,NULL);



-- htv_comp_off_intimation_details source

create or replace
algorithm = UNDEFINED view `htv_comp_off_intimation_details` as
select
    `comp`.`company_id` as `company_id`,
    `comp`.`company_branch_id` as `company_branch_id`,
    `comp`.`comp_off_intimation_details_id` as `comp_off_intimation_details_id`,
    `comp`.`employee_id` as `employee_id`,
    `comp`.`punch_code` as `punch_code`,
    `comp`.`employee_code` as `employee_code`,
    `comp`.`att_date_time` as `att_date_time`,
    `comp`.`status` as `status`,
    `comp`.`weeklyoff_name` as `weeklyoff_name`,
    `comp`.`remark` as `remark`,
    `comp`.`approval_remark` as `approval_remark`,
    `comp`.`approved_by_id` as `approved_by_id`,
    `emp`.`employee_name` as `employee_name`,
    `emp`.`reporting_to` as `reporting_to`,
    `emp`.`reporting_to_name` as `reporting_to_name`,
    `empl`.`employee_name` as `approved_by`,
    case
        `comp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `is_delete`,
     case
        `comp`.`comp_holiday_type` when 'H' then 'Holiday Present'
        else 'CompOff'
    end as `CompOff_Holiday_type`,
    `comp`.`comp_holiday_type` as `comp_holiday_type`,
    `comp`.`created_by` as `created_by`,
    `comp`.`created_on` as `created_on`,
    `comp`.`modified_by` as `modified_by`,
    `comp`.`modified_on` as `modified_on`,
    `comp`.`deleted_by` as `deleted_by`,
    `comp`.`deleted_on` as `deleted_on`,
    `comp`.`employee_type` as `employee_type`,
    `comp`.`approved_date` as `approved_date`
from
    ((`ht_comp_off_intimation_details` `comp`
left join `cmv_employee` `emp` on
    (`comp`.`employee_id` = `emp`.`employee_id`
        and `comp`.`company_id` = `emp`.`company_id`))
left join `cmv_employee` `empl` on
    (`comp`.`approved_by_id` = `empl`.`employee_id`
        and `comp`.`company_id` = `empl`.`company_id`))
where
    `comp`.`is_delete` = 0;

-- htv_comp_off_intimation_details_rpt source


create or replace
algorithm = UNDEFINED view `htv_comp_off_intimation_details_rpt` as
select
    concat(`comp`.`CompOff_Holiday_type`, ':CompOff/Holiday Type:Y:N:') as `CompOff_Holiday_type`,
    concat(`comp`.`weeklyoff_name`, ':Weekly Off/Holiday Name:O:N:') as `weeklyoff_name`,
    concat(`comp`.`employee_code`, ':Employee Code:Y:T:') as `employee_code`,
    concat(`comp`.`punch_code`, ':Punch Code:O:N:') as `punch_code`,
    concat(`comp`.`employee_name`, ':Employee Name:Y:N:') as `employee_name`,
    concat(`comp`.`employee_id`, ':Employee ID:Y:N:') as `employee_id`,
    concat(`comp`.`reporting_to_name`, ':Reporting To:Y:N:') as `reporting_to_name`,
    concat(`comp`.`approved_by`, ':Approved By:Y:N:') as `approved_by`,
    concat(`comp`.`att_date_time`, ':Attendance Date:Y:D:') as `att_date_time`,
    concat(`comp`.`status`, ':Status:Y:H:(Pending, Approved, Rejected)') as `status`,
    concat(`comp`.`remark`, ':Remark:Y:T:') as `remark`,
    concat(`comp`.`approval_remark`, ':Approval Remark:O:N:') as `approval_remark`,
    concat(`comp`.`approved_by_id`, ':Approved By ID:O:N:') as `approved_by_id`,
    concat(`comp`.`approved_date`, ':Approved Date:O:N:') as `approved_date`,
    concat(ifnull(case when `comp`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(`comp`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`comp`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`comp`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`comp`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`comp`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`comp`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`comp`.`company_id`, ':Company ID:N:N:') as `company_id`,
    concat(`comp`.`company_branch_id`, ':Company Branch ID:N:N:') as `company_branch_id`,
    concat(`comp`.`comp_off_intimation_details_id`, ':Comp Off Intimation ID:O:N:') as `comp_off_intimation_details_id`,
    concat(`comp`.`reporting_to`, ':Reporting To:Y:N:') as `reporting_to`,
    concat(`comp`.`employee_type`, ':Employee Type:O:N:') as `employee_type`
from
    `htv_comp_off_intimation_details` `comp`
limit 1;


INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,3,1,1,'District',4,'District','Masters','<FrmDistrictList/>','import FrmDistrictList from "./Masters/MDistrict/FrmDistrictList";','/Masters/MDistrict/FrmDistrictList','<FrmDistrict />','import FrmDistrict from "./Masters/MDistrict/FrmDistrict";','/Masters/FrmDistrict','','District',1,1,0,NULL,'2024-01-24 17:21:24.000',NULL,'2024-01-24 17:21:24.000',NULL,NULL,0,1,1,'');




-- cmv_district source
create or replace
algorithm = UNDEFINED view `cmv_district` as
select
    `b`.`district_id` as `district_id`,
    `c1`.`country_name` as `country_name`,
    `s`.`state_name` as `state_name`,
    `b`.`district_name` as `district_name`,
    `b`.`district_short_name` as `district_short_name`,
    `b`.`district_code` as `district_code`,
    `c1`.`country_code` as `country_code`,
    `s`.`state_code` as `state_code`,
     case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `cm`.`company_name` as `company_name`,
    `b`.`company_id` as `company_id`,
    `b`.`country_id` as `country_id`,
    `b`.`state_id` as `state_id`,
    `b`.`district_id` as `field_id`,
    `b`.`district_name` as `field_name`
from
    (((`cm_district` `b`
left join `cm_country` `c1` on
    (`c1`.`country_id` = `b`.`country_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `b`.`state_id`))
 left join `cmv_company` `cm` on
    (`cm`.`company_id` = `b`.`company_id`));



create or replace
algorithm = UNDEFINED view `cmv_district_rpt` as
select
    concat(`v`.`district_name`, ':District Name:Y:C:cmv_city:O') as `district_name`,
    concat(`v`.`state_name`, ':State Name:Y:C:cmv_city:O') as `state_name`,
    concat(`c1`.`country_name`, ':Country Name:Y:C:cmv_city:O') as `country_name`,
    concat(`v`.`district_code`, ':District Code:O:N:') as `district_code`,
    concat(`v`.`district_short_name`, ':District Short Name:O:N:') as `district_short_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`district_id`, ':District Id:O:N:') as `district_id`,
    concat(`v`.`state_id`, ':State Id:N:N:') as `state_id`,
    concat(`v`.`district_id`, ': District Id:N:N:') as `field_id`,
    concat(`c1`.`country_code`, ':Country code:O:N:') as `country_code`,
    concat(`v`.`district_name`, ':District Name:N:N:') as `field_name`
from
    ((`cmv_district` `v`
left join `cm_country` `c1` on
    (`c1`.`country_id` = `v`.`country_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `v`.`state_id`))
limit 1;