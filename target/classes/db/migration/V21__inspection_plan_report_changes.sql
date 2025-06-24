
ALTER TABLE mt_dispatch_challan_details_trading MODIFY COLUMN dispatch_challan_version bigint(20) DEFAULT 1 NULL COMMENT 'will come from header section  *';
ALTER TABLE mt_dispatch_challan_details_trading MODIFY COLUMN expected_schedule_date date NULL COMMENT '* Data Grid  with Export/Import excel format Entry DTPicker show  data    from mt_sales_order_schedules_trading of respected materail ';
ALTER TABLE mt_dispatch_challan_details_trading MODIFY COLUMN dispatch_challan_master_transaction_id bigint(20) NULL COMMENT '* will come from header section';

ALTER TABLE mt_dispatch_inspection_details_trading ADD COLUMN dispatch_weight decimal(18,4) DEFAULT 0.0000 NULL;

ALTER TABLE mt_dispatch_inspection_details_trading MODIFY COLUMN dispatch_weight decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE mt_dispatch_inspection_details_trading MODIFY COLUMN dispatch_challan_version bigint(20) DEFAULT 1 NULL COMMENT 'will come from header section  *';
ALTER TABLE mt_dispatch_inspection_details_trading MODIFY COLUMN dispatch_challan_master_transaction_id bigint(20) NULL COMMENT '* will come from header section';

ALTER TABLE xt_weaving_production_inspection_details ADD COLUMN dispatch_weight decimal(18,4) DEFAULT 0.0000 NULL;

ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN dispatch_weight decimal(18,4) DEFAULT 0.0000 NULL;


ALTER TABLE xt_weaving_production_inspection_master MODIFY COLUMN plant_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_master MODIFY COLUMN section_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_master MODIFY COLUMN sub_section_id bigint(20) NULL;