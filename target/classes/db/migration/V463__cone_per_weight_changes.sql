ALTER TABLE st_indent_material_issue_return_details MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT 0.0000 NULL;
ALTER TABLE st_material_issue_batch_wise MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT 0.0000 NULL;
ALTER TABLE st_indent_material_issue_details MODIFY COLUMN cone_per_wt double(18,10) DEFAULT 0.0000 NULL;

ALTER TABLE sm_product_rm_stock_details MODIFY COLUMN weight_per_box_item decimal(18,10) DEFAULT 0.0000 NULL COMMENT 'text box for weight of per cone in box';
ALTER TABLE sm_product_rm_stock_summary MODIFY COLUMN weight_per_box_item decimal(18,10) DEFAULT 0.0000 NULL COMMENT 'text box for weight of per cone in box';

ALTER TABLE xt_weaving_production_warping_bottom_details MODIFY COLUMN weight_per_pkg decimal(18,10) DEFAULT NULL NULL;
ALTER TABLE xt_warping_bottom_return_details MODIFY COLUMN weight_per_pkg decimal(18,10) DEFAULT NULL NULL;


ALTER TABLE xt_warping_production_order_details MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT 0.0000 NULL;
ALTER TABLE xt_warping_production_order_creels MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT NULL NULL;


ALTER TABLE pt_goods_return_details MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT NULL NULL;
ALTER TABLE pt_inter_material_transfer_details MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT 0.0000 NULL;
ALTER TABLE xt_warping_production_order_stock_details MODIFY COLUMN cone_per_wt decimal(18,10) DEFAULT 0.0000 NULL;

ALTER TABLE pt_goods_receipt_details MODIFY COLUMN weight_per_box_item decimal(18,10) DEFAULT 0.0000 NULL COMMENT 'text box for weight of per cone in box';
ALTER TABLE pt_goods_receipt_details_customer MODIFY COLUMN weight_per_box_item decimal(18,10) DEFAULT 0.0000 NULL COMMENT 'text box for weight of per cone in box';


ALTER TABLE sm_warping_bottom_stock_details MODIFY COLUMN weight_per_pkg decimal(18,10) DEFAULT NULL NULL;
ALTER TABLE xt_weaving_production_warping_details MODIFY COLUMN weight_per_pkg decimal(18,10) DEFAULT 0.0000 NULL COMMENT '*  grid data entry   text box  with validation';