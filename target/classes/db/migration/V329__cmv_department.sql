update cm_department
set parent_department_id = 19
where department_id = 67
and is_delete = 0
and department_type = "S";

update cm_department
set parent_department_id = 19
where department_id = 68
and is_delete = 0
and department_type = "S";

UPDATE fm_cost_center fcc
SET fcc.company_id = 1,
    fcc.company_branch_id = '1'
WHERE fcc.cost_center_name = 'Carding MT CDG'
AND fcc.company_id = 3;


UPDATE fm_cost_center fcc
SET fcc.company_id = 1,
    fcc.company_branch_id = '1'
WHERE fcc.cost_center_name = 'GIN - ROOF TOP SOLAR PLANT'
AND fcc.company_id = 3;


UPDATE fm_cost_center fcc
SET fcc.company_id = 1,
    fcc.company_branch_id = '1'
WHERE fcc.cost_center_name = 'WVG - ROOF TOP SOLAR PLANT '
AND fcc.company_id = 2;


UPDATE fm_cost_center
SET is_delete = 1
WHERE company_id IN (2, 3);


UPDATE fm_profit_center
SET is_delete = 1
WHERE company_id IN (2, 3);

