ALTER TABLE cm_plant ADD plant_short_name varchar(5) DEFAULT NULL NULL;

create or replace
algorithm = UNDEFINED view `cmv_plant` as
select
    `p`.`plant_name` as `plant_name`,
    `p`.`plant_status` as `plant_status`,
    `p`.`plant_short_name` as `plant_short_name`,
    `p`.`plant_area` as `plant_area`,
    `p`.`plant_start_date` as `plant_start_date`,
    `p`.`plant_production_capacity` as `plant_production_capacity`,
    `e`.`employee_name` as `plant_head_name`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    case
        `p`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `p`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `p`.`is_active` as `is_active`,
    `p`.`is_delete` as `is_delete`,
    `p`.`created_by` as `created_by`,
    `p`.`created_on` as `created_on`,
    `p`.`modified_by` as `modified_by`,
    `p`.`modified_on` as `modified_on`,
    `p`.`deleted_by` as `deleted_by`,
    `p`.`deleted_on` as `deleted_on`,
    `p`.`plant_id` as `plant_id`,
    `p`.`company_id` as `company_id`,
    `p`.`company_branch_id` as `company_branch_id`,
    `p`.`plant_head_id` as `plant_head_id`,
    `p`.`plant_id` as `field_id`,
    `p`.`plant_name` as `field_name`
from
    ((`cm_plant` `p`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `p`.`company_branch_id`
        and `v`.`company_id` = `p`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`company_branch_id` = `p`.`company_branch_id`
        and `e`.`company_id` = `p`.`company_id`
        and `e`.`employee_id` = `p`.`plant_head_id`))
where
    `p`.`is_delete` = 0;


INSERT INTO cm_plant (company_id,company_branch_id,plant_name,plant_area,plant_production_capacity,plant_start_date,plant_head_id,plant_status,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,plant_short_name) VALUES
	 (1,1,'Spinning Unit-1',7500.0000,200.0000,'2024-09-07',NULL,'Functional',1,0,'6260537025','2025-02-25 10:40:38.000',NULL,NULL,NULL,NULL,'U1'),
	 (1,1,'Spinning Unit-2',3500.0000,300.0000,'2024-09-25',NULL,'Functional',1,0,'6260537025','2025-02-25 10:43:03.000',NULL,NULL,NULL,NULL,'U2'),
	 (1,1,'Spinning Unit-3',10000.0000,2300.0000,'2022-06-02',NULL,'Functional',1,0,'6260537025','2025-02-25 10:44:13.000',NULL,NULL,NULL,NULL,'U3');
