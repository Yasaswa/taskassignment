INSERT INTO xt_production_settings (company_id,approx_warping_production_bottom,warping_plan_product_constant) VALUES
	 (2,1500.0000,1693.0000);

ALTER TABLE xt_warping_production_order_creels CHANGE production_count_id production_count bigint(20) NOT NULL;
