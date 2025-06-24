ALTER TABLE xt_weaving_production_warping_material ADD consumption_weight decimal(18, 4) DEFAULT 0.0000 NULL;
ALTER TABLE xt_weaving_production_warping_material ADD goods_receipt_no varchar(500) NULL;
ALTER TABLE xt_weaving_production_warping_material MODIFY COLUMN product_material_unit_id bigint(20) NULL COMMENT '*  grid data entry   combo box with smv_product_unit box auto with validation';

