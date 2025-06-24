-- xt_weaving_beam_issue_master_rpt source

create or REPLACE algorithm = UNDEFINED view `xt_weaving_beam_issue_master_rpt` as
select
    concat(ifnull(`v`.`beam_issue_no`, ''), ':Beam Issue No:Y:T') as `beam_issue_no`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`beam_name`, ''), ':Beam No:Y:T:') as `beam_name`,
    concat(ifnull(`v`.`loom_no`, ''), ':Loom No:Y:T:') as `loom_no`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`count`, ''), ':Count:Y:T:') as `count`,
    concat(ifnull(`v`.`loom_process_type`, ''), ':Process Type:Y:H:(KNOTTING,GAITING)') as `loom_process_type`,
    concat(ifnull(`v`.`loom_process_charge`, ''), ':Process Charge:Y:T:') as `loom_process_charge`,
    concat(ifnull(`v`.`shift`, ''), ':Shift Name:Y:H:(I, II)') as `shift`,
    concat(ifnull(`v`.`beam_issue_date`, ''), ':Beam Issue Date:Y:D:') as `beam_issue_date`,
    concat(ifnull(`v`.`beam_issue_time`, ''), ':Beam Issue Time:Y:D:') as `beam_issue_time`,
    concat(ifnull(`v`.`loom_beam_status`, ''), ':Loom Beam Status:Y:H:(Cut Beams,Full Length )') as `loom_beam_status`,
    concat(ifnull(`v`.`t_ends`, ''), ':No Of Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`reed`, ''), ':Reed:O:N::') as `reed`,
    concat(ifnull(`v`.`pick`, ''), ':Pick:O:N:') as `pick`,
    concat(ifnull(`v`.`rs`, ''), ':RS:O:N:') as `rs`,
    concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
    concat(ifnull(`v`.`financial_year`, ''), ':Finance Year:Y:T:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `is_active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_beam_issue_master_id`, ''), ':Weaving Beam Issue Master Id:O:N:') as `weaving_beam_issue_master_id`,
    concat(ifnull(`v`.`beam_issue_no`, ''), ':Beam Issue No:N:N:') as `field_name`,
    concat(ifnull(`v`.`weaving_beam_issue_master_id`, ''), ':Weaving Beam Issue Master Id:N:N:') as `field_id`
from
    `xtv_weaving_beam_issue_master` `v`
limit 1;