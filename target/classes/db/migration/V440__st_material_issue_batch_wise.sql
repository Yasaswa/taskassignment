ALTER TABLE st_material_issue_batch_wise MODIFY COLUMN return_weight decimal(18,4) DEFAULT 0 NULL;
ALTER TABLE st_material_issue_batch_wise MODIFY COLUMN return_boxes bigint(20) DEFAULT 0.0000 NULL;