ALTER TABLE xt_weaving_production_warping_stoppage CHANGE weaving_production_warping_stoppage_id weaving_production_stoppage_id bigint(20) auto_increment NOT NULL COMMENT '* Text  Header part auto generated ';
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN weaving_production_warping_master_id bigint(20) NULL COMMENT '* will come from Text  Header part   ';
ALTER TABLE xt_weaving_production_warping_stoppage CHANGE warping_production_date production_date date NOT NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN warping_production_code varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '* Text  Header part redonly logic should be 23-24/PlantName/Shiftname/RF0001 ';
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN prod_month bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN prod_year bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN plant_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN from_time time NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN to_time time NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN total_time varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE xt_weaving_production_warping_stoppage ADD production_set_no varchar(100) NOT NULL;
ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN production_supervisor_id bigint(20) NULL;

ALTER TABLE xt_weaving_production_warping_wastage CHANGE weaving_production_warping_wastage_id weaving_production_wastage_id bigint(20) auto_increment NOT NULL COMMENT '* Text  Header part auto generated ';
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN weaving_production_warping_master_id bigint(20) NULL COMMENT '* will come from Text  Header part   ';
ALTER TABLE xt_weaving_production_warping_wastage CHANGE warping_production_date production_date date NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN production_date date NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN prod_month bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN prod_year bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN plant_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN godown_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN production_wastage_types_id bigint(20) NULL COMMENT '* combo box Data Come from xm_production_wastage_types master Entry with alphanumeric validation ';
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN company_id int(11) DEFAULT 1 NOT NULL COMMENT '* Header part will come from Session *';
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN company_branch_id bigint(20) DEFAULT 1 NOT NULL COMMENT '* Header part will come from Session *';
ALTER TABLE xt_weaving_production_warping_wastage ADD production_set_no varchar(100) NOT NULL;
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN warping_production_code varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '* Text  Header part redonly logic should be 23-24/PlantName/Shiftname/RF0001 ';
ALTER TABLE xt_weaving_production_warping_wastage MODIFY COLUMN weaving_production_warping_master_id bigint(20) DEFAULT NULL NULL COMMENT '* will come from Text  Header part   ';


-- erp_development.xtv_weaving_production_warping_stoppage source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_stoppage` as
select
    `sp`.`production_date` as `production_date`,
    `sp`.`warping_production_code` as `warping_production_code`,
    `sp`.`shift` as `shift`,
    `sp`.`prod_month` as `prod_month`,
    `sp`.`prod_year` as `prod_year`,
    `cm`.`machine_name` as `machine_name`,
    `cm`.`machine_short_name` as `machine_short_name`,
    `st`.`production_stoppage_reasons_name` as `production_stoppage_reasons_name`,
    `st`.`production_stoppage_reasons_type` as `production_stoppage_reasons_type`,
    `st`.`std_stoppage_minutes` as `std_stoppage_minutes`,
    `sp`.`std_stoppage_loss_per_hour` as `std_stoppage_loss_per_hour`,
    `sp`.`loss_type` as `loss_type`,
    `sp`.`stoppage_time` as `stoppage_time`,
    `sp`.`std_stoppage_loss_kg` as `std_stoppage_loss_kg`,
    `sp`.`stoppage_production_loss_kg` as `stoppage_production_loss_kg`,
    `sp`.`actual_production_loss_kg` as `actual_production_loss_kg`,
    `sp`.`production_set_no` as `production_set_no`,
    `sp`.`from_time` as `from_time`,
    `sp`.`to_time` as `to_time`,
    `sp`.`total_time` as `total_time`,
    case
        `sp`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sp`.`is_active` as `is_active`,
    `sp`.`is_delete` as `is_delete`,
    `sp`.`created_by` as `created_by`,
    `sp`.`created_on` as `created_on`,
    `sp`.`modified_by` as `modified_by`,
    `sp`.`modified_on` as `modified_on`,
    `sp`.`deleted_by` as `deleted_by`,
    `sp`.`deleted_on` as `deleted_on`,
    `sp`.`financial_year` as `financial_year`,
    `sp`.`company_id` as `company_id`,
    `sp`.`company_branch_id` as `company_branch_id`,
    `sp`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `sp`.`weaving_production_stoppage_id` as `weaving_production_stoppage_id`,
    `sp`.`plant_id` as `plant_id`,
    `sp`.`section_id` as `section_id`,
    `sp`.`sub_section_id` as `sub_section_id`,
    `sp`.`production_supervisor_id` as `production_supervisor_id`,
    `sp`.`machine_id` as `machine_id`,
    `sp`.`production_stoppage_reasons_id` as `production_stoppage_reasons_id`,
    `sp`.`weaving_production_stoppage_id` as `field_id`,
    `sp`.`warping_production_code` as `field_name`
from
    ((`xt_weaving_production_warping_stoppage` `sp`
left join `cmv_machine` `cm` on
    (`sp`.`machine_id` = `cm`.`machine_id`
        and `sp`.`company_id` = `cm`.`company_id`))
left join `xmv_production_stoppage_reasons` `st` on
    (`sp`.`production_stoppage_reasons_id` = `st`.`production_stoppage_reasons_id`
        and `sp`.`company_id` = `st`.`company_id`))
where
    `sp`.`is_delete` = 0
order by
    `sp`.`weaving_production_stoppage_id` desc;


    -- erp_development.xtv_weaving_production_warping_wastage source

    create or replace
    algorithm = UNDEFINED view `xtv_weaving_production_warping_wastage` as
    select
        `wp`.`warping_production_code` as `warping_production_code`,
        `wp`.`shift` as `shift`,
        `wp`.`prod_month` as `prod_month`,
        `wp`.`prod_year` as `prod_year`,
        `wp`.`production_date` as `production_date`,
        `s`.`godown_name` as `godown_name`,
        `cm`.`machine_name` as `machine_name`,
        `cm`.`machine_short_name` as `machine_short_name`,
        `wt`.`production_wastage_types_name` as `production_wastage_types_name`,
        `wp`.`production_wastage_types_type` as `production_wastage_types_type`,
        `wp`.`wastage_quantity` as `wastage_quantity`,
        `wp`.`production_set_no` as `production_set_no`,
        case
            `wp`.`is_active` when 1 then 'Active'
            else 'In Active'
        end as `Active`,
        case
            `wp`.`is_delete` when 1 then 'Yes'
            else 'No'
        end as `Deleted`,
        `wp`.`is_active` as `is_active`,
        `wp`.`is_delete` as `is_delete`,
        `wp`.`created_by` as `created_by`,
        `wp`.`created_on` as `created_on`,
        `wp`.`modified_by` as `modified_by`,
        `wp`.`modified_on` as `modified_on`,
        `wp`.`deleted_by` as `deleted_by`,
        `wp`.`deleted_on` as `deleted_on`,
        `wp`.`financial_year` as `financial_year`,
        `wp`.`company_id` as `company_id`,
        `wp`.`company_branch_id` as `company_branch_id`,
        `wp`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
        `wp`.`weaving_production_wastage_id` as `weaving_production_wastage_id`,
        `wp`.`plant_id` as `plant_id`,
        `wp`.`section_id` as `section_id`,
        `wp`.`godown_id` as `godown_id`,
        `wp`.`sub_section_id` as `sub_section_id`,
        `wp`.`machine_id` as `machine_id`,
        `wp`.`production_wastage_types_id` as `production_wastage_types_id`
    from
        (((`xt_weaving_production_warping_wastage` `wp`
    left join `xmv_production_sub_section_godown_mapping` `s` on
        (`s`.`production_sub_section_godown_id` = `wp`.`godown_id`
            and `s`.`company_id` = `wp`.`company_id`))
    left join `cmv_machine` `cm` on
        (`wp`.`machine_id` = `cm`.`machine_id`
            and `wp`.`company_id` = `cm`.`company_id`))
    left join `xmv_production_wastage_types` `wt` on
        (`wp`.`production_wastage_types_id` = `wt`.`production_wastage_types_id`
            and `wp`.`company_id` = `wt`.`company_id`))
    where
        `wp`.`is_delete` = 0
    order by
        `wp`.`weaving_production_wastage_id` desc;


        -- erp_development.xtv_weaving_production_warping_stoppage_rpt source

        create or replace
        algorithm = UNDEFINED view `xtv_weaving_production_warping_stoppage_rpt` as
        select
            concat(`sp`.`production_date`, ':Production Date:Y:D:') as `production_date`,
            concat(`sp`.`shift`, ':Shift:Y:C:') as `shift`,
            concat(`sp`.`machine_name`, ':Machine Name:Y:C:') as `machine_name`,
            concat(`sp`.`machine_short_name`, ':Machine Short Name:Y:C:') as `machine_short_name`,
            concat(`sp`.`production_stoppage_reasons_name`, ':Stoppage Reason Name:Y:C:') as `production_stoppage_reasons_name`,
            concat(`sp`.`production_stoppage_reasons_type`, ':Stoppage Reason Type:Y:C:') as `production_stoppage_reasons_type`,
            concat(`sp`.`production_set_no`, ':Production Set No:Y:C:') as `production_set_no`,
            concat(`sp`.`std_stoppage_loss_per_hour`, ':Standard Stoppage Loss per Hour:Y:N:') as `std_stoppage_loss_per_hour`,
            concat(`sp`.`loss_type`, ':Loss Type:Y:C:') as `loss_type`,
            concat(`sp`.`std_stoppage_loss_kg`, ':Standard Stoppage Loss (Kg):Y:N:') as `std_stoppage_loss_kg`,
            concat(`sp`.`stoppage_production_loss_kg`, ':Stoppage Production Loss (Kg):Y:N:') as `stoppage_production_loss_kg`,
            concat(`sp`.`actual_production_loss_kg`, ':Actual Production Loss (Kg):Y:N:') as `actual_production_loss_kg`,
            concat(`sp`.`from_time`, ':From Time:Y:T:') as `from_time`,
            concat(`sp`.`to_time`, ':To Time:Y:T:') as `to_time`,
            concat(`sp`.`total_time`, ':Total Time:Y:N:') as `total_time`,
            concat(`sp`.`created_by`, ':Created By:O:N:') as `created_by`,
            concat(`sp`.`created_on`, ':Created On:O:N:') as `created_on`,
            concat(`sp`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
            concat(`sp`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
            concat(`sp`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
            concat(`sp`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
            concat(`sp`.`financial_year`, ':Financial Year:Y:N:') as `financial_year`,
            concat(`sp`.`company_id`, ':Company Id:Y:N:') as `company_id`,
            concat(`sp`.`company_branch_id`, ':Company Branch Id:Y:N:') as `company_branch_id`,
            concat(`sp`.`weaving_production_stoppage_id`, ':Weaving Production Stoppage Id:Y:N:') as `weaving_production_stoppage_id`,
            concat(`sp`.`section_id`, ':Section Id:Y:N:') as `section_id`,
            concat(`sp`.`sub_section_id`, ':Sub Section Id:Y:N:') as `sub_section_id`,
            concat(`sp`.`machine_id`, ':Machine Id:Y:N:') as `machine_id`,
            concat(`sp`.`production_stoppage_reasons_id`, ':Production Stoppage Reason Id:Y:N:') as `production_stoppage_reasons_id`,
            concat(`sp`.`weaving_production_stoppage_id`, ':Field Id:Y:N:') as `field_id`
        from
            `xtv_weaving_production_warping_stoppage` `sp`
        limit 1;


        -- erp_development.xtv_weaving_production_warping_wastage_rpt source

        create or replace
        algorithm = UNDEFINED view `xtv_weaving_production_warping_wastage_rpt` as
        select
            concat(`wp`.`shift`, ':Shift:Y:C:') as `shift`,
            concat(`wp`.`production_date`, ':Production Date:Y:D:') as `production_date`,
            concat(`wp`.`godown_name`, ':Godown Name:Y:C:') as `godown_name`,
            concat(`wp`.`machine_name`, ':Machine Name:Y:C:') as `machine_name`,
            concat(`wp`.`machine_short_name`, ':Machine Short Name:Y:C:') as `machine_short_name`,
            concat(`wp`.`production_wastage_types_name`, ':Production Wastage Types Name:Y:C:') as `production_wastage_types_name`,
            concat(`wp`.`production_wastage_types_type`, ':Production Wastage Types Type:Y:C:') as `production_wastage_types_type`,
            concat(`wp`.`production_set_no`, ':Production Set No:Y:C:') as `production_set_no`,
            concat(`wp`.`wastage_quantity`, ':Wastage Quantity:Y:N:') as `wastage_quantity`,
            concat(`wp`.`created_by`, ':Created By:O:N:') as `created_by`,
            concat(`wp`.`created_on`, ':Created On:O:N:') as `created_on`,
            concat(`wp`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
            concat(`wp`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
            concat(`wp`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
            concat(`wp`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
            concat(`wp`.`financial_year`, ':Financial Year:Y:N:') as `financial_year`,
            concat(`wp`.`company_id`, ':Company Id:Y:N:') as `company_id`,
            concat(`wp`.`company_branch_id`, ':Company Branch Id:Y:N:') as `company_branch_id`,
            concat(`wp`.`weaving_production_wastage_id`, ':Weaving Production Wastage Id:Y:N:') as `weaving_production_wastage_id`,
            concat(`wp`.`section_id`, ':Section Id:Y:N:') as `section_id`,
            concat(`wp`.`sub_section_id`, ':Sub Section Id:Y:N:') as `sub_section_id`,
            concat(`wp`.`machine_id`, ':Machine Id:Y:N:') as `machine_id`,
            concat(`wp`.`production_wastage_types_id`, ':Production Wastage Types Id:Y:N:') as `production_wastage_types_id`
        from
            `xtv_weaving_production_warping_wastage` `wp`
        where
            `wp`.`is_delete` = 0
        order by
            `wp`.`weaving_production_wastage_id` desc
        limit 1;