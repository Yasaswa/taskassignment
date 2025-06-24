-- erp_development.ht_misspunch_correction definition

CREATE TABLE `ht_misspunch_correction` (
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `misspunch_correction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `punch_code` varchar(100) DEFAULT NULL,
  `employee_code` varchar(255) NOT NULL,
  `att_date_time` date NOT NULL,
  `in_time` varchar(50) DEFAULT NULL,
  `out_time` varchar(50) DEFAULT NULL,
  `punch_type` varchar(100) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`misspunch_correction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;