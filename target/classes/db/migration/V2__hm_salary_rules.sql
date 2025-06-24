ALTER TABLE hm_salary_rules ADD employee_type varchar(100) NULL;
ALTER TABLE hm_salary_rules CHANGE employee_type employee_type varchar(100) NULL AFTER sub_department_id;
