ALTER TABLE xt_warping_production_order MODIFY COLUMN warping_schedule_date varchar(20) DEFAULT NULL NULL;
ALTER TABLE xt_warping_production_order MODIFY COLUMN warping_plan_date varchar(20) DEFAULT NULL NULL;
ALTER TABLE xt_warping_production_order MODIFY COLUMN approved_date varchar(20) DEFAULT NULL NULL COMMENT '* combo box with dtpicker data entry';
