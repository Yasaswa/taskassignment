
ALTER TABLE xt_weaving_production_warping_master ADD calculative_bottom_kg decimal(18,4) NOT NULL;
ALTER TABLE xt_weaving_production_warping_master ADD calculative_bottom_percent decimal(18,4) NOT NULL;
ALTER TABLE xt_weaving_production_warping_master ADD actual_bottom_kg decimal(18,4) NOT NULL;
ALTER TABLE xt_weaving_production_warping_master ADD actual_bottom_percent decimal(18,4) NOT NULL;
ALTER TABLE xt_weaving_production_warping_master ADD difference_bottom_kg decimal(18,4) NOT NULL;
ALTER TABLE xt_weaving_production_warping_master ADD difference_bottom_percent decimal(18,4) NOT NULL;


ALTER TABLE xt_weaving_production_warping_details ADD speed bigint(20) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD cut_cones decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD guccha decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD thin_places decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD week_places decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD week_splice decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD sluff_off decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD slub_yarn decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_details ADD total_breaks decimal(18,4) NULL;
