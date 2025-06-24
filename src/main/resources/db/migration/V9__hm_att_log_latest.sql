CREATE TABLE `hm_att_log2` (
  `att_log2_transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `att_device_id` int(2) NOT NULL,
  `att_device_ip` varchar(100) DEFAULT NULL,
  `att_device_emp_code` varchar(20) DEFAULT NULL,
  `att_device_transaction_id` bigint(20) DEFAULT NULL,
  `att_date_time` varchar(50) DEFAULT NULL,
  `att_date_added` datetime DEFAULT current_timestamp(),
  `att_punch_status` varchar(10) DEFAULT NULL,
  `att_punch_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`att_log2_transaction_id`),
  KEY `ind_device_id` (`att_device_id`),
  KEY `ind_emp_code` (`att_device_emp_code`),
  KEY `ind_dateadded` (`att_date_added`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
