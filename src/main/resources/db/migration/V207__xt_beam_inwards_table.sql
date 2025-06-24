

    delete from xt_beam_inwards_table  where beam_inward_type = 'RAPIER';
    delete from xt_beam_inwards_table  where beam_inward_type = 'AIR JET';
    delete from xt_beam_inwards_table  where beam_inward_type = 'T-190';
    delete from xt_beam_inwards_table  where beam_inward_type = 'T-110';


     INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
         (176,'SizedBeams',2,'IN-HOUSE','IN-HOUSE',1,0,'6260537025','2024-11-28 16:19:18',NULL,'2024-11-28 16:19:18',NULL,NULL,'BI','');


	-- Make sure prperty id of upper insert query passed to update in xt_beam_inwards_table
	update xt_beam_inwards_table set beam_type = 1031 where beam_inward_type like "%SPF%";


        -- xtv_beam_inwards_table_rpt source

       
        CREATE OR REPLACE
        ALGORITHM = UNDEFINED VIEW `xtv_beam_inwards_table_rpt` AS
        select
            concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') AS `customer_name`,
            concat(ifnull(`v`.`beam_inwards_date`, ''), ':Beam Inwards Date:Y:D:') AS `beam_inwards_date`,
            concat(ifnull(`v`.`beam_inward_type`, ''), ':Beam Name:Y:T') AS `beam_inward_type`,
            concat(ifnull(`v`.`property_value`, ''), ':Beam Type:Y:T') AS `property_value`,
            concat(ifnull(`v`.`beam_no`, ''), ':Beam No:Y:T') AS `beam_no`,
            concat(ifnull(`v`.`section`, ''), ':Section:O:N:') AS `section`,
            concat(ifnull(`v`.`beam_width`, ''), ':Beam Width:Y:T') AS `beam_width`,
            concat(ifnull(`v`.`beam_status`, ''), ':Beam Status:Y:H:(Empty,Completed)') AS `beam_status_desc`,
            concat(ifnull(`v`.`customer_short_name`, ''), ':Customer Short Name:O:N:') AS `customer_short_name`,
            concat(case when ifnull(`v`.`is_delete`, 0) = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
            concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') AS `customer_id`,
            concat(ifnull(`v`.`beam_inwards_id`, ''), ':Beam Inwards Id:N:N:') AS `beam_inwards_id`
        from
            `xtv_beam_inwards_table` `v`
        limit 1;



    update xt_beam_inwards_table set beam_no = beam_inward_type  where beam_type = 1031;
