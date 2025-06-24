create or replace
algorithm = UNDEFINED view `xt_production_standard_losses_rpt` as
select
    concat(ifnull(`psl`.`weave_design_name`, ''), ':Weave Design Name:O:N:') as `weave_design_name`,
    concat(ifnull(`psl`.`traget_efficiency`, ''), ':Target Efficiency:O:N:') as `traget_efficiency`,
    concat(ifnull(`psl`.`warp_break`, ''), ':Warp Break:O:N:') as `warp_break`,
    concat(ifnull(`psl`.`weft_break`, ''), ':Weft Break:O:N:') as `weft_break`,
    concat(ifnull(`psl`.`knotting`, ''), ':Knotting:O:N:') as `knotting`,
    concat(ifnull(`psl`.`gaiting`, ''), ':Gaiting:O:N:') as `gaiting`,
    concat(ifnull(`psl`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(case when `psl`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`psl`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`psl`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`psl`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`psl`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`psl`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`psl`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`psl`.`production_standard_losses_id`, ''), ':Production Standard Losses Id Id:O:N:') as `production_standard_losses_id`,
    concat(ifnull(`psl`.`weave_design_id`, ''), ':Weave Design Id:O:N:') as `weave_design_id`,
    concat(ifnull(`psl`.`company_id`, ''), ':Company Id:O:N:') as `company_id`,
    concat(ifnull(`psl`.`company_branch_id`, ''), ':Company Branch Id:O:N:') as `company_branch_id`
from
    `xt_production_standard_losses` `psl`
limit 1;