

-- update company_id of profit center as per company

update fm_profit_center set company_id = 0 , company_branch_id = 0 where profit_center_name = 'Common  Profit Center' and company_id = 1;
update fm_profit_center set company_id = 3 , company_branch_id = 4 where profit_center_name = 'Ginning Profit Center' and company_id = 1;
update fm_profit_center set company_id = 3 , company_branch_id = 4 where profit_center_name = 'Oil Mills Profit Center' and company_id = 1;
update fm_profit_center set company_id = 2 , company_branch_id = 3 where profit_center_name = 'Weaving Profit Center' and company_id = 1;