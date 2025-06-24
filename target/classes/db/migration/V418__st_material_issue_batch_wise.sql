


	ALTER TABLE  st_material_issue_batch_wise ADD financial_year VARCHAR(20) NOT NULL;
	ALTER TABLE st_material_issue_batch_wise CHANGE financial_year financial_year VARCHAR(20) COMMENT '' AFTER company_branch_id;


