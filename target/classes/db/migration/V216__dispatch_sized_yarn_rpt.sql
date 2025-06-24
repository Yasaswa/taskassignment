ALTER TABLE mt_dispatch_challan_sized_yarn_details ADD beam_inward_type varchar(100) NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master DROP COLUMN dispatch_by_id;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD dispatch_date date NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_details DROP COLUMN dispatch_by_id;
ALTER TABLE mt_dispatch_challan_sized_yarn_details ADD dispatch_date date NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master MODIFY COLUMN dispatch_challan_type_id bigint(20) DEFAULT NULL NULL COMMENT ' * combo box with cmv_product_type show only NA, Raw Material,Finish Goods,General,Spares Parts,Consumables,Assets, Plant & Machinery,Brought Out,Non Stock Material,Others,- save product group';
