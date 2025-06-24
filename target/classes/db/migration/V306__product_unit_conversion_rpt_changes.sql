-- erp.smv_product_unit_conversion_rpt source

create or replace
algorithm = UNDEFINED view `smv_product_unit_conversion_rpt` as
select
    concat(`v`.`product_from_unit_name`, ':Product From Unit Name:Y:C:smv_product_unit_conversion:O') as `product_from_unit_name`,
    concat(`v`.`product_from_unit_short_name`, ':Product From Unit Short Name:O:N:') as `product_from_unit_short_name`,
    concat(`v`.`product_to_unit_name`, ':Product To Unit Name:Y:C:smv_product_unit_conversion:O') as `product_to_unit_name`,
    concat(`v`.`product_to_unit_short_name`, ':Product To Unit Short Name:O:N:') as `product_to_unit_short_name`,
    concat(`v`.`convsersion_factor`, ':Convsersion Factor:O:N:') as `convsersion_factor`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`conversion_id`, ':Conversion Id:O:N:') as `conversion_id`,
    concat(`v`.`product_from_unit_id`, ':Product From Unit Id:O:N:') as `product_from_unit_id`,
    concat(`v`.`product_to_unit_id`, ':Product To Unit Id:O:N:') as `product_to_unit_id`
from
    `smv_product_unit_conversion` `v`
limit 1;