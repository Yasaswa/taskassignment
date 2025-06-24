
create or replace
algorithm = UNDEFINED view `xtv_weaving_production_loom_master` as
select
    `xt`.`loom_production_code` as `loom_production_code`,
    `xt`.`loom_production_date` as `loom_production_date`,
    `xt`.`data_import_date` as `data_import_date`,
    `xt`.`loom_production_shift` as `loom_production_shift`,
    `xt`.`data_import_time` as `data_import_time`,
    `xt`.`data_import_file` as `data_import_file`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`total_prodcut_1000pick` as `total_prodcut_1000pick`,
    `xt`.`total_product_in_meter` as `total_product_in_meter`,
    `xt`.`total_run_in_min` as `total_run_in_min`,
    `xt`.`total_stop_in_min` as `total_stop_in_min`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `e`.`employee_name` as `production_supervisor_name`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`loom_production_remark` as `loom_production_remark`,
    case
        `xt`.`loom_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `loom_production_master_status_desc`,
    `xt`.`loom_production_master_status` as `loom_production_master_status`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`weaving_production_loom_master_id` as `field_id`,
    `xt`.`loom_production_code` as `field_name`
from
    (((`xt_weaving_production_loom_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_supervisor_id`
        and `e`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;



    create or replace
    algorithm = UNDEFINED view `xtv_weaving_production_loom_master_rpt` as
    select
        concat(ifnull(`v`.`loom_production_code`, ''), ':Loom Production Code:O:N:') as `loom_production_code`,
        concat(ifnull(`v`.`loom_production_date`, ''), ':Loom Production Date:Y:D:') as `loom_production_date`,
        concat(ifnull(`v`.`data_import_date`, ''), ':Data Import Date:Y:D:') as `data_import_date`,
        concat(ifnull(`v`.`loom_production_shift`, ''), ':Loom Production Shift:Y:D:') as `loom_production_shift`,
        concat(ifnull(`v`.`data_import_time`, ''), ':Data Import Time:Y:D:') as `data_import_time`,
        concat(ifnull(`v`.`data_import_file`, ''), ':Data Import File:Y:D:') as `data_import_file`,
        concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
        concat(ifnull(`v`.`total_prodcut_1000pick`, ''), ':Total Prodcut 1000Pick:Y:T:') as `total_prodcut_1000pick`,
        concat(ifnull(`v`.`total_product_in_meter`, ''), ':Total Product In Meter:Y:T:') as `total_product_in_meter`,
        concat(ifnull(`v`.`total_run_in_min`, ''), ':Total Run In Min:Y:T:') as `total_run_in_min`,
        concat(ifnull(`v`.`total_stop_in_min`, ''), ':Total Stop In Min :Y:T:') as `total_stop_in_min`,
        concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
        concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
        concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
        concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
        concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
        concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `loom_production_master_status_desc`,
        concat(ifnull(`v`.`loom_production_remark`, ''), ':Loom Production Remark:O:N:)') as `loom_production_remark`,
        concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
        concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status:O:N:') as `loom_production_master_status`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`weaving_production_loom_master_id`, ''), ':Weaving Production Loom Master Id:O:N:') as `weaving_production_loom_master_id`,
        concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
        concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
        concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`,
        concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`
    from
        `xtv_weaving_production_loom_master` `v`
    limit 1;


    create or replace
    algorithm = UNDEFINED view `xtv_weaving_production_loom_details` as
    select
        `xt`.`loom_production_code` as `loom_production_code`,
        `xt`.`code` as `code`,
        `xt`.`loom_production_date` as `loom_production_date`,
        `xt`.`loom_production_shift` as `loom_production_shift`,
        `xt`.`prod_month` as `prod_month`,
        `xt`.`prod_year` as `prod_year`,
        `xt`.`set_no` as `set_no`,
        `xt`.`style` as `style`,
        `xt`.`sizing_beam_no` as `sizing_beam_no`,
        `xt`.`sort_no` as `sort_no`,
        `xt`.`epi` as `epi`,
        `xt`.`ppi` as `ppi`,
        `xt`.`width` as `width`,
        `m`.`machine_name` as `machine_name`,
        `p`.`plant_name` as `plant_name`,
        `e`.`employee_name` as `production_operator_name`,
        `e1`.`employee_name` as `production_supervisor_name`,
        `xt`.`target_rpm` as `target_rpm`,
        `xt`.`target_eff` as `target_eff`,
        `xt`.`weave` as `weave`,
        `xt`.`act_rmp` as `act_rmp`,
        `xt`.`act_eff` as `act_eff`,
        `xt`.`run_in_min` as `run_in_min`,
        `xt`.`stop_in_min` as `stop_in_min`,
        `xt`.`prodcut_1000pick` as `prodcut_1000pick`,
        `xt`.`product_in_meter` as `product_in_meter`,
        `xt`.`air_flow1` as `air_flow1`,
        `xt`.`air_flow2` as `air_flow2`,
        `xt`.`air_flow_cfm` as `air_flow_cfm`,
        `xt`.`warp_times` as `warp_times`,
        `xt`.`warp_minutes` as `warp_minutes`,
        `xt`.`warp_per_hour` as `warp_per_hour`,
        `xt`.`warp_per_day` as `warp_per_day`,
        `xt`.`warp_per_cmpx` as `warp_per_cmpx`,
        `xt`.`weft_times` as `weft_times`,
        `xt`.`weft_minutes` as `weft_minutes`,
        `xt`.`weft_per_hour` as `weft_per_hour`,
        `xt`.`weft_per_day` as `weft_per_day`,
        `xt`.`weft_per_cmpx` as `weft_per_cmpx`,
        `xt`.`leno_times` as `leno_times`,
        `xt`.`leno_minutes` as `leno_minutes`,
        `xt`.`leno_per_day` as `leno_per_day`,
        `xt`.`leno_per_hour` as `leno_per_hour`,
        `xt`.`leno_per_cmpx` as `leno_per_cmpx`,
        `xt`.`unselect_times` as `unselect_times`,
        `xt`.`unselect_minutes` as `unselect_minutes`,
        `xt`.`unselect2_times` as `unselect2_times`,
        `xt`.`unselect_per_hour` as `unselect_per_hour`,
        `xt`.`unselect_per_day` as `unselect_per_day`,
        `xt`.`unselect_per_cmpx` as `unselect_per_cmpx`,
        `xt`.`total_times` as `total_times`,
        `xt`.`total_minutes` as `total_minutes`,
        `s`.`godown_name` as `godown_name`,
        `xt`.`total2_times` as `total2_times`,
        `xt`.`total_per_hour` as `total_per_hour`,
        `xt`.`total_per_day` as `total_per_day`,
        `xt`.`total_per_cmpx` as `total_per_cmpx`,
        `sb`.`production_section_name` as `production_section_name`,
        `sb`.`production_sub_section_name` as `production_sub_section_name`,
        `xt`.`remarks` as `remarks`,
        case
            `xt`.`is_active` when 1 then 'Active'
            else 'In Active'
        end as `Active`,
        case
            `xt`.`is_delete` when 1 then 'Yes'
            else 'No'
        end as `Deleted`,
        `xt`.`is_active` as `is_active`,
        `xt`.`is_delete` as `is_delete`,
        `xt`.`created_by` as `created_by`,
        `xt`.`created_on` as `created_on`,
        `xt`.`modified_by` as `modified_by`,
        `xt`.`modified_on` as `modified_on`,
        `xt`.`deleted_by` as `deleted_by`,
        `xt`.`deleted_on` as `deleted_on`,
        `xt`.`financial_year` as `financial_year`,
        `xt`.`company_id` as `company_id`,
        `xt`.`company_branch_id` as `company_branch_id`,
        `xt`.`godown_id` as `godown_id`,
        `xt`.`weaving_production_loom_details_id` as `weaving_production_loom_details_id`,
        `xt`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
        `xt`.`plant_id` as `plant_id`,
        `xt`.`section_id` as `section_id`,
        `xt`.`sub_section_id` as `sub_section_id`,
        `xt`.`production_operator_id` as `production_operator_id`,
        `xt`.`production_supervisor_id` as `production_supervisor_id`,
        `xt`.`machine_id` as `machine_id`,
        `wm`.`loom_production_master_status` as `loom_production_master_status`,
        `xt`.`weaving_production_loom_details_id` as `field_id`,
        `xt`.`loom_production_code` as `field_name`
    from
        (((((((`xt_weaving_production_loom_details` `xt`
    left join `xt_weaving_production_loom_master` `wm` on
        (`wm`.`weaving_production_loom_master_id` = `xt`.`weaving_production_loom_master_id`
            and `wm`.`company_id` = `xt`.`company_id`))
    left join `cm_godown` `s` on
        (`s`.`godown_id` = `xt`.`godown_id`
            and `s`.`company_id` = `xt`.`company_id`))
    left join `cm_plant` `p` on
        (`p`.`plant_id` = `xt`.`plant_id`
            and `p`.`company_id` = `xt`.`company_id`))
    left join `cm_machine` `m` on
        (`m`.`machine_id` = `xt`.`machine_id`
            and `m`.`company_id` = `xt`.`company_id`))
    left join `xm_production_sub_section` `sb` on
        (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
            and `sb`.`company_id` = `xt`.`company_id`))
    left join `cm_employee` `e` on
        (`e`.`employee_id` = `xt`.`production_operator_id`
            and `e`.`company_id` = `xt`.`company_id`))
    left join `cm_employee` `e1` on
        (`e1`.`employee_id` = `xt`.`production_supervisor_id`
            and `e1`.`company_id` = `xt`.`company_id`))
    where
        `xt`.`is_delete` = 0;



        create or replace
        algorithm = UNDEFINED view `xtv_weaving_production_loom_details_rpt` as
        select
            concat(ifnull(`v`.`set_no`, ''), ':Set No:O:N:') as `set_no`,
            concat(ifnull(`v`.`style`, ''), ':Style:O:N:') as `style`,
            concat(ifnull(`v`.`loom_production_code`, ''), ':Loom Production Code:O:N') as `loom_production_code`,
            concat(ifnull(`v`.`loom_production_date`, ''), ':Loom Production Date:Y:D:') as `loom_production_date`,
            concat(ifnull(`v`.`loom_production_shift`, ''), ':Loom Production Shift:Y:C:xtv_weaving_production_loom_details:O') as `loom_production_shift`,
            concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:cmv_plant:F') as `plant_name`,
            concat(ifnull(`v`.`machine_name`, ''), ':Machine NO:O:N:') as `machine_name`,
            concat(ifnull(`v`.`sort_no`, ''), ':Sort No:O:N:') as `sort_no`,
            concat(ifnull(`v`.`sizing_beam_no`, ''), ':Sizing Beam No:O:N:') as `sizing_beam_no`,
            concat(ifnull(`v`.`run_in_min`, ''), ':Run In Min:Y:T:') as `run_in_min`,
            concat(ifnull(`v`.`stop_in_min`, ''), ':Stop In Min:Y:T:') as `stop_in_min`,
            concat(ifnull(`v`.`prodcut_1000pick`, ''), ':Prodcut 1000Pick:Y:T:') as `prodcut_1000pick`,
            concat(ifnull(`v`.`product_in_meter`, ''), ':Product In Meter:Y:T:') as `product_in_meter`,
            concat(ifnull(`v`.`act_rmp`, ''), ':Act RMP:Y:T:') as `act_rmp`,
            concat(ifnull(`v`.`act_eff`, ''), ':Act EFF:Y:T:') as `act_eff`,
            concat(ifnull(`v`.`air_flow1`, ''), ':Air Flow1:Y:T:') as `air_flow1`,
            concat(ifnull(`v`.`air_flow2`, ''), ':Air Flow2:Y:T:') as `air_flow2`,
            concat(ifnull(`v`.`air_flow_cfm`, ''), ':Air Flow CFM:Y:T:') as `air_flow_cfm`,
            concat(ifnull(`v`.`warp_times`, ''), ':Warp Times:Y:T:') as `warp_times`,
            concat(ifnull(`v`.`warp_minutes`, ''), ':Warp Minutes:Y:T:') as `warp_minutes`,
            concat(ifnull(`v`.`warp_per_hour`, ''), ':Warp Per Hour:Y:T:') as `warp_per_hour`,
            concat(ifnull(`v`.`warp_per_day`, ''), ':Warp Per Day:Y:T:') as `warp_per_day`,
            concat(ifnull(`v`.`warp_per_cmpx`, ''), ':Warp Per Cmpx:Y:T:') as `warp_per_cmpx`,
            concat(ifnull(`v`.`weft_times`, ''), ':Weft Times:Y:T:') as `weft_times`,
            concat(ifnull(`v`.`weft_minutes`, ''), 'Weft Minutes:Y:T:') as `weft_minutes`,
            concat(ifnull(`v`.`weft_per_hour`, ''), ':Weft Per Hour:Y:T:') as `weft_per_hour`,
            concat(ifnull(`v`.`weft_per_day`, ''), ':Weft Per Day:Y:T:') as `weft_per_day`,
            concat(ifnull(`v`.`weft_per_cmpx`, ''), ':Weft Per Cmpx:Y:T:') as `weft_per_cmpx`,
            concat(ifnull(`v`.`leno_times`, ''), ':Leno Times:Y:T:') as `leno_times`,
            concat(ifnull(`v`.`leno_minutes`, ''), ':Leno Minutes:Y:T:') as `leno_minutes`,
            concat(ifnull(`v`.`leno_per_day`, ''), ':Leno Per Day:Y:T:') as `leno_per_day`,
            concat(ifnull(`v`.`leno_per_hour`, ''), ':Leno Per Hour:Y:T:') as `leno_per_hour`,
            concat(ifnull(`v`.`leno_per_cmpx`, ''), ':Leno Per Cmpx:Y:T:') as `leno_per_cmpx`,
            concat(ifnull(`v`.`unselect_times`, ''), ':Unselect Times:Y:T:') as `unselect_times`,
            concat(ifnull(`v`.`unselect_minutes`, ''), ':Unselect Minutes:Y:T:') as `unselect_minutes`,
            concat(ifnull(`v`.`unselect2_times`, ''), ':Unselect2 Times:Y:T:') as `unselect2_times`,
            concat(ifnull(`v`.`unselect_per_hour`, ''), ':Unselect Per Hour:Y:T:') as `unselect_per_hour`,
            concat(ifnull(`v`.`unselect_per_day`, ''), ':Unselect Per Day:Y:T:') as `unselect_per_day`,
            concat(ifnull(`v`.`unselect_per_cmpx`, ''), ':Unselect Per Cmpx:Y:T:') as `unselect_per_cmpx`,
            concat(ifnull(`v`.`total_times`, ''), ':Total Times:Y:T:') as `total_times`,
            concat(ifnull(`v`.`total2_times`, ''), ':Total2 Times:Y:T:') as `total_minutes`,
            concat(ifnull(`v`.`total_per_hour`, ''), ':Total Per Hour:Y:T:') as `total_per_hour`,
            concat(ifnull(`v`.`total_per_day`, ''), ':Total Per Day:Y:T:') as `total_per_day`,
            concat(ifnull(`v`.`total_per_cmpx`, ''), ':Total Per Cmpx:Y:T:') as `total_per_cmpx`,
            concat(ifnull(`v`.`weave`, ''), ':Weave:Y:T:') as `weave`,
            concat(ifnull(`v`.`remarks`, ''), ':Remarks:O:N:') as `remarks`,
            concat(ifnull(`v`.`epi`, ''), ':EPI:Y:T:') as `epi`,
            concat(ifnull(`v`.`ppi`, ''), ':PPI:Y:T:') as `ppi`,
            concat(ifnull(`v`.`width`, ''), ':width:Y:T:') as `width`,
            concat(ifnull(`v`.`target_rpm`, ''), ':Target RPM:Y:T:') as `target_rpm`,
            concat(ifnull(`v`.`target_eff`, ''), ':Target EFF:Y:T:') as `target_eff`,
            concat(ifnull(`v`.`code`, ''), ':Code:O:N') as `code`,
            concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status Desc:Y:H:(Approved,Rejected,Completed,Canceled,Partial)') as `loom_production_master_status_desc`,
            concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
            concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
            concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
            concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:O:N:') as `production_supervisor_name`,
            concat(ifnull(`v`.`production_operator_name`, ''), ':Production Operator Name:O:N:') as `production_operator_name`,
            concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
            concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
            concat(ifnull(`v`.`loom_production_master_status`, ''), ':Loom Production Master Status Desc:O:N:') as `loom_production_master_status`,
            concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
            concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
            concat(ifnull(`v`.`weaving_production_loom_details_id`, ''), ':Weaving Production Loom Details Id:N:N:') as `weaving_production_loom_details_id`,
            concat(ifnull(`v`.`weaving_production_loom_master_id`, ''), ':Weaving Production Loom Master Id:O:N:') as `weaving_production_loom_master_id`,
            concat(ifnull(`v`.`production_operator_id`, ''), ':Production Operator Id:N:N:') as `production_operator_id`,
            concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`,
            concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
            concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
            concat(ifnull(`v`.`machine_id`, ''), ':Machine Id:N:N:') as `machine_id`,
            concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`
        from
            `xtv_weaving_production_loom_details` `v`
        limit 1;



        create or replace
        algorithm = UNDEFINED view `xtv_weaving_production_loom_material` as
        select
            `xt`.`loom_production_set_no` as `loom_production_set_no`,
            `xt`.`loom_production_order_no` as `loom_production_order_no`,
            `xt`.`loom_production_code` as `loom_production_code`,
            `xt`.`loom_production_date` as `loom_production_date`,
            `prf`.`product_material_name` as `product_material_name`,
            `p`.`plant_name` as `plant_name`,
            `pu`.`product_unit_name` as `product_material_unit_name`,
            `sb`.`production_section_name` as `production_section_name`,
            `sb`.`production_sub_section_name` as `production_sub_section_name`,
            `xt`.`shift` as `shift`,
            `xt`.`prod_month` as `prod_month`,
            `xt`.`prod_year` as `prod_year`,
            `xt`.`product_material_quantity` as `product_material_quantity`,
            `xt`.`consumption_quantity` as `consumption_quantity`,
            case
                `xt`.`material_status` when 'P' then 'Pending'
                when 'A' then 'Approved'
                when 'R' then 'Rejected'
                when 'C' then 'Completed'
                when 'Z' then 'Canceled'
                when 'I' then 'Inprogress'
                else 'Partial'
            end as `material_status_desc`,
            (
            select
                ifnull(`wi`.`product_material_quantity` - sum(`wi`.`consumption_quantity`), `wi`.`product_material_quantity`)
            from
                `xt_weaving_production_loom_material` `wi`
            where
                `wi`.`loom_production_set_no` = `xt`.`loom_production_set_no`
                and `wi`.`product_material_id` = `xt`.`product_material_id`
                and `wi`.`is_delete` = 0) as `product_loom_material_balance_quantity`,
            ifnull((select sum(`st`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `st` where `st`.`product_rm_id` = `xt`.`product_material_id` and `st`.`company_id` = `xt`.`company_id` and `st`.`is_delete` = 0), 0) as `stock_quantity`,
            `xt`.`material_status` as `material_status`,
            case
                `xt`.`is_active` when 1 then 'Active'
                else 'In Active'
            end as `Active`,
            case
                `xt`.`is_delete` when 1 then 'Yes'
                else 'No'
            end as `Deleted`,
            `xt`.`is_active` as `is_active`,
            `xt`.`is_delete` as `is_delete`,
            `xt`.`created_by` as `created_by`,
            `xt`.`created_on` as `created_on`,
            `xt`.`modified_by` as `modified_by`,
            `xt`.`modified_on` as `modified_on`,
            `xt`.`deleted_by` as `deleted_by`,
            `xt`.`deleted_on` as `deleted_on`,
            `v`.`company_legal_name` as `company_name`,
            `v`.`company_branch_name` as `company_branch_name`,
            `xt`.`financial_year` as `financial_year`,
            `v`.`company_id` as `company_id`,
            `v`.`company_branch_id` as `company_branch_id`,
            `xt`.`weaving_production_loom_material_id` as `weaving_production_loom_material_id`,
            `xt`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
            `xt`.`weaving_production_loom_details_id` as `weaving_production_loom_details_id`,
            `xt`.`product_material_id` as `product_material_id`,
            `xt`.`product_material_unit_id` as `product_material_unit_id`,
            `xt`.`plant_id` as `plant_id`,
            `xt`.`section_id` as `section_id`,
            `xt`.`sub_section_id` as `sub_section_id`
        from
            (((((`xt_weaving_production_loom_material` `xt`
        left join `cm_plant` `p` on
            (`p`.`plant_id` = `xt`.`plant_id`
                and `p`.`company_id` = `xt`.`company_id`))
        left join `xmv_production_sub_section` `sb` on
            (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
                and `sb`.`company_id` = `xt`.`company_id`))
        left join `cmv_company_summary` `v` on
            (`v`.`company_id` = `xt`.`company_id`))
        left join `sm_product_unit` `pu` on
            (`pu`.`product_unit_id` = `xt`.`product_material_unit_id`))
        left join `smv_product_rm_fg_sr` `prf` on
            (`prf`.`product_material_id` = `xt`.`product_material_id`))
        where
            `xt`.`is_delete` = 0
        order by
            `xt`.`weaving_production_loom_material_id` desc;



            create or replace
            algorithm = UNDEFINED view `xtv_weaving_production_loom_stoppage` as
            select
                `ls`.`loom_production_code` as `loom_production_code`,
                `ls`.`loom_production_date` as `loom_production_date`,
                `ls`.`shift` as `shift`,
                `ls`.`prod_month` as `prod_month`,
                `ls`.`prod_year` as `prod_year`,
                `cm`.`machine_name` as `machine_name`,
                `cm`.`machine_short_name` as `machine_short_name`,
                `st`.`production_stoppage_reasons_name` as `production_stoppage_reasons_name`,
                `st`.`production_stoppage_reasons_type` as `production_stoppage_reasons_type`,
                `st`.`std_stoppage_minutes` as `std_stoppage_minutes`,
                `ls`.`std_stoppage_loss_per_hour` as `std_stoppage_loss_per_hour`,
                `ls`.`loss_type` as `loss_type`,
                `ls`.`stoppage_time` as `stoppage_time`,
                `ls`.`std_stoppage_loss_kg` as `std_stoppage_loss_kg`,
                `ls`.`stoppage_production_loss_kg` as `stoppage_production_loss_kg`,
                `ls`.`actual_production_loss_kg` as `actual_production_loss_kg`,
                case
                    `ls`.`is_active` when 1 then 'Active'
                    else 'In Active'
                end as `Active`,
                case
                    `ls`.`is_delete` when 1 then 'Yes'
                    else 'No'
                end as `Deleted`,
                `lm`.`is_active` as `is_active`,
                `lm`.`is_delete` as `is_delete`,
                `lm`.`created_by` as `created_by`,
                `lm`.`created_on` as `created_on`,
                `lm`.`modified_by` as `modified_by`,
                `lm`.`modified_on` as `modified_on`,
                `lm`.`deleted_by` as `deleted_by`,
                `lm`.`deleted_on` as `deleted_on`,
                `lm`.`financial_year` as `financial_year`,
                `lm`.`company_id` as `company_id`,
                `lm`.`company_branch_id` as `company_branch_id`,
                `lm`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
                `ls`.`weaving_production_loom_stoppage_id` as `weaving_production_loom_stoppage_id`,
                `lm`.`plant_id` as `plant_id`,
                `lm`.`section_id` as `section_id`,
                `lm`.`sub_section_id` as `sub_section_id`,
                `lm`.`production_supervisor_id` as `production_supervisor_id`,
                `ls`.`machine_id` as `machine_id`,
                `ls`.`production_stoppage_reasons_id` as `production_stoppage_reasons_id`
            from
                (((`xt_weaving_production_loom_stoppage` `ls`
            left join `xt_weaving_production_loom_master` `lm` on
                (`ls`.`weaving_production_loom_master_id` = `lm`.`weaving_production_loom_master_id`
                    and `ls`.`company_id` = `lm`.`company_id`))
            left join `cm_machine` `cm` on
                (`ls`.`machine_id` = `cm`.`machine_id`
                    and `ls`.`company_id` = `cm`.`company_id`))
            left join `xm_production_stoppage_reasons` `st` on
                (`ls`.`production_stoppage_reasons_id` = `st`.`production_stoppage_reasons_id`
                    and `ls`.`company_id` = `st`.`company_id`))
            where
                `ls`.`is_delete` = 0;




                create or replace
                algorithm = UNDEFINED view `xtv_weaving_production_loom_wastage` as
                select
                    `lm`.`loom_production_code` as `loom_production_code`,
                    `lw`.`shift` as `shift`,
                    `lm`.`prod_month` as `prod_month`,
                    `lm`.`prod_year` as `prod_year`,
                    `lm`.`loom_production_date` as `loom_production_date`,
                    `s`.`godown_name` as `godown_name`,
                    `cm`.`machine_name` as `machine_name`,
                    `cm`.`machine_short_name` as `machine_short_name`,
                    `wt`.`production_wastage_types_name` as `production_wastage_types_name`,
                    `lw`.`production_wastage_types_type` as `production_wastage_types_type`,
                    `lw`.`wastage_quantity` as `wastage_quantity`,
                    case
                        `lw`.`is_active` when 1 then 'Active'
                        else 'In Active'
                    end as `Active`,
                    case
                        `lw`.`is_delete` when 1 then 'Yes'
                        else 'No'
                    end as `Deleted`,
                    `lm`.`is_active` as `is_active`,
                    `lm`.`is_delete` as `is_delete`,
                    `lm`.`created_by` as `created_by`,
                    `lm`.`created_on` as `created_on`,
                    `lm`.`modified_by` as `modified_by`,
                    `lm`.`modified_on` as `modified_on`,
                    `lm`.`deleted_by` as `deleted_by`,
                    `lm`.`deleted_on` as `deleted_on`,
                    `lm`.`financial_year` as `financial_year`,
                    `lm`.`company_id` as `company_id`,
                    `lm`.`company_branch_id` as `company_branch_id`,
                    `lw`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
                    `lw`.`weaving_production_loom_wastage_id` as `weaving_production_loom_wastage_id`,
                    `lm`.`plant_id` as `plant_id`,
                    `lm`.`section_id` as `section_id`,
                    `lw`.`godown_id` as `godown_id`,
                    `lm`.`sub_section_id` as `sub_section_id`,
                    `lm`.`production_supervisor_id` as `production_supervisor_id`,
                    `lw`.`machine_id` as `machine_id`,
                    `lw`.`production_wastage_types_id` as `production_wastage_types_id`
                from
                    ((((`xt_weaving_production_loom_wastage` `lw`
                left join `xt_weaving_production_loom_master` `lm` on
                    (`lw`.`weaving_production_loom_master_id` = `lm`.`weaving_production_loom_master_id`
                        and `lw`.`company_id` = `lm`.`company_id`))
                left join `cm_godown` `s` on
                    (`s`.`godown_id` = `lw`.`godown_id`
                        and `s`.`company_id` = `lw`.`company_id`))
                left join `cm_machine` `cm` on
                    (`lw`.`machine_id` = `cm`.`machine_id`
                        and `lw`.`company_id` = `cm`.`company_id`))
                left join `xm_production_wastage_types` `wt` on
                    (`lw`.`production_wastage_types_id` = `wt`.`production_wastage_types_id`
                        and `lw`.`company_id` = `wt`.`company_id`))
                where
                    `lw`.`is_delete` = 0
                order by
                    `lw`.`weaving_production_loom_wastage_id` desc;