ALTER TABLE xt_warping_production_order_creels MODIFY COLUMN per_creel_ends decimal(18,4) DEFAULT 0 NULL;
ALTER TABLE xt_warping_production_order_creels MODIFY COLUMN creel_bottom decimal(18,4) DEFAULT 0 NULL;
ALTER TABLE xt_warping_production_order ADD bottom_value bigint(20) DEFAULT 1500 NULL;