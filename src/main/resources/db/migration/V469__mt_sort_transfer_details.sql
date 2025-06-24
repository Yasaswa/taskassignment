

ALTER TABLE mt_sort_transfer_master MODIFY COLUMN remark varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN `style` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN average varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN remark varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN roll_no bigint(20) NOT NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN dispatch_weight DECIMAL(18,4) NOT NULL;
ALTER TABLE mt_sort_transfer_details CHANGE dispatch_weight weight decimal(18,4) NOT NULL;
ALTER TABLE mt_sort_transfer_details ADD from_product_material_id varchar(50) NULL COMMENT 'fg from fg id';
ALTER TABLE mt_sort_transfer_details ADD to_product_material_id varchar(50) NULL COMMENT 'to fg id';
ALTER TABLE mt_sort_transfer_details ADD product_type_id BIGINT(20) DEFAULT 0 NULL COMMENT 'product type of fg';
ALTER TABLE mt_sort_transfer_details CHANGE remark remark varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL AFTER product_type_id;
ALTER TABLE mt_sort_transfer_details CHANGE is_delete is_delete bit(1) DEFAULT b'0' NULL AFTER remark;
ALTER TABLE mt_sort_transfer_details CHANGE created_by created_by varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' NULL AFTER is_delete;
ALTER TABLE mt_sort_transfer_details CHANGE created_on created_on datetime DEFAULT NULL NULL AFTER created_by;
ALTER TABLE mt_sort_transfer_details CHANGE modified_by modified_by varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL AFTER created_on;
ALTER TABLE mt_sort_transfer_details CHANGE modified_on modified_on datetime DEFAULT NULL NULL AFTER modified_by;
ALTER TABLE mt_sort_transfer_details CHANGE deleted_by deleted_by varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL AFTER modified_on;
ALTER TABLE mt_sort_transfer_details CHANGE deleted_on deleted_on datetime DEFAULT NULL NULL AFTER deleted_by;

ALTER TABLE mt_sort_transfer_details ADD godown_id BIGINT(20) DEFAULT 0 NULL;
ALTER TABLE mt_sort_transfer_details CHANGE godown_id godown_id BIGINT(20) DEFAULT 0 NULL AFTER product_type_id;
ALTER TABLE mt_sort_transfer_details ADD godown_section_id BIGINT(20) DEFAULT 0 NULL;
ALTER TABLE mt_sort_transfer_details CHANGE godown_section_id godown_section_id BIGINT(20) DEFAULT 0 NULL AFTER godown_id;
ALTER TABLE mt_sort_transfer_details ADD godown_section_beans_id BIGINT(20) NULL;
ALTER TABLE mt_sort_transfer_details CHANGE godown_section_beans_id godown_section_beans_id BIGINT(20) NULL AFTER godown_section_id;

ALTER TABLE mt_sort_transfer_details ADD to_product_material_name varchar(255) NULL COMMENT 'transfer to fg name';
ALTER TABLE mt_sort_transfer_details CHANGE to_product_material_name to_product_material_name varchar(255) NULL COMMENT 'transfer to fg name' AFTER to_product_material_id;
ALTER TABLE mt_sort_transfer_details ADD inspection_production_date DATE NULL COMMENT 'roll production date';
ALTER TABLE mt_sort_transfer_details CHANGE inspection_production_date inspection_production_date DATE NULL COMMENT 'roll production date' AFTER godown_section_beans_id;
ALTER TABLE mt_sort_transfer_details CHANGE `style` from_style varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL;

ALTER TABLE mt_sort_transfer_details MODIFY COLUMN inspection_mtr decimal(18,4) NOT NULL;
ALTER TABLE mt_sort_transfer_details MODIFY COLUMN average decimal(18,4) DEFAULT NULL NULL;

ALTER TABLE mt_sort_transfer_details ADD sizing_beam_no varchar(50) NULL COMMENT 'to transfer beam no';
ALTER TABLE mt_sort_transfer_details CHANGE sizing_beam_no sizing_beam_no varchar(50) NULL COMMENT 'to transfer beam no' AFTER inspection_production_date;
ALTER TABLE mt_sort_transfer_details ADD machine_id BIGINT(20) DEFAULT 0 NULL COMMENT 'to transfer machine';
ALTER TABLE mt_sort_transfer_details CHANGE machine_id machine_id BIGINT(20) DEFAULT 0 NULL COMMENT 'to transfer machine' AFTER sizing_beam_no;



