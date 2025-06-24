-- erp_development.xt_weaving_production_warping_bottom_details definition

ALTER TABLE xt_warping_production_order_creels CHANGE creel_name creel_no varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL;


CREATE TABLE `xt_weaving_production_warping_bottom_details` (
  `company_id` int(11) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `company_branch_id` bigint(20) DEFAULT 1 COMMENT '* Header part will come from Session *',
  `financial_year` varchar(20) NOT NULL COMMENT '* Header part will come from Session*',
  `set_no` varchar(250) DEFAULT NULL,
  `weaving_production_warping_bottom_details_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* Bottom Details auto generated *',
  `weaving_production_warping_master_id` bigint(20) DEFAULT NULL,
  `warping_production_code` varchar(255) NOT NULL,
  `creel_no` varchar(255) DEFAULT NULL,
  `no_of_package` decimal(18,2) DEFAULT NULL,
  `gross_weight` decimal(18,2) DEFAULT NULL,
  `net_weight` decimal(18,2) DEFAULT NULL,
  `tare_weight` decimal(18,2) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`weaving_production_warping_bottom_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;