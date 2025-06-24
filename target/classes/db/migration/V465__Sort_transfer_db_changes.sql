INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,page_header,is_selected,is_active,is_delete,is_protected,header,footer,url_parameter)
	VALUES (1,1,1,27,5,21,'Sort Transfer',5,'Sort Transfer','Production','<FrmSortTransferListing />','import FrmSortTransferListing from "Transactions/TSortTransfer/FrmSortTransferListing";','/Transactions/TSortTransfer/FrmSortTransferListing','<FrmSortTransferEntry />','import FrmSortTransferEntry from "Transactions/TSortTransfer/FrmSortTransferEntry";','/Transactions/TSortTransfer/FrmSortTransferEntry','Sort Transfer',0,1,0,0,1,1,'');



CREATE TABLE `mt_sort_transfer_master` (
  `sort_transfer_master_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `financial_year` varchar(20) NOT NULL,
  `transfer_no` varchar(50) NOT NULL,
  `transfer_date` date NOT NULL,
  `from_sort_no` varchar(50) NOT NULL,
  `to_sort_no` varchar(50) NOT NULL,
  `transfer_by` varchar(50) NOT NULL,
  `remark` varchar(200) NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,

  PRIMARY KEY (`sort_transfer_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `mt_sort_transfer_details` (
  `sort_transfer_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort_transfer_master_id` bigint(20) NOT null,
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `financial_year` varchar(20) NOT NULL,
  `transfer_no` varchar(50) NOT NULL,
  `from_set_no` varchar(50) NOT NULL,
  `roll_no` varchar(50) NOT NULL,
  `to_set_no` varchar(50) NOT NULL,
  `from_sort_no` varchar(50) NOT NULL,
  `to_sort_no` varchar(50) NOT NULL,
  `style` varchar(50) NOT NULL,
  `inspection_mtr` varchar(50) NOT NULL,
  `dispatch_weight` varchar(50) NOT NULL,
  `average` varchar(50) NOT NULL,
  `remark` varchar(200) NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,

  PRIMARY KEY (`sort_transfer_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE OR REPLACE VIEW `mtv_sort_transfer_master` AS
SELECT
    `mt`.`sort_transfer_master_id` AS `sort_transfer_master_id`,
    `mt`.`company_id` AS `company_id`,
    `mt`.`financial_year` AS `financial_year`,
    `mt`.`company_branch_id` AS `company_branch_id`,
    `mt`.`transfer_no` AS `transfer_no`,
    `mt`.`transfer_date` as `transfer_date`,
    `mt`.`from_sort_no` AS `from_sort_no`,
    `mt`.`to_sort_no` AS `to_sort_no`,
    `mt`.`transfer_by` AS `transfer_by`,
    `mt`.`remark` AS `remark`,
    `mt`.`is_delete` AS `is_delete`,
    `mt`.`created_by` AS `created_by`,
    `mt`.`created_on` AS `created_on`,
    `mt`.`modified_by` AS `modified_by`,
    `mt`.`modified_on` AS `modified_on`,
    `mt`.`deleted_by` AS `deleted_by`,
    `mt`.`deleted_on` AS `deleted_on`,
    `cm`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`

FROM
    ((`mt_sort_transfer_master` `mt`
LEFT JOIN `cm_company` `cm` ON
    (`mt`.`company_id` = `cm`.`company_id` AND `cm`.`is_delete` = 0))
LEFT JOIN `cm_company_branch` `cb` ON
    (`cb`.`company_branch_id` = `mt`.`company_branch_id` AND `cb`.`is_delete` = 0))
WHERE
    `mt`.`is_delete` = 0
GROUP BY
    `mt`.`sort_transfer_master_id`;




CREATE OR REPLACE VIEW `mtv_sort_transfer_details` AS
SELECT
    `dt`.`sort_transfer_details_id` AS `sort_transfer_details_id`,
    `dt`.`sort_transfer_master_id` AS `sort_transfer_master_id`,
    `dt`.`company_id` AS `company_id`,
    `dt`.`financial_year` AS `financial_year`,
    `dt`.`company_branch_id` AS `company_branch_id`,
    `dt`.`roll_no` AS `roll_no`,
    `dt`.`transfer_no` AS `transfer_no`,
    `dt`.`from_set_no` AS `from_set_no`,
    `dt`.`to_set_no` AS `to_set_no`,
    `dt`.`from_sort_no` AS `from_sort_no`,
    `dt`.`to_sort_no` AS `to_sort_no`,
    `dt`.`style` AS `style`,
    `dt`.`inspection_mtr` AS `inspection_mtr`,
    `dt`.`dispatch_weight` AS `dispatch_weight`,
    `dt`.`average` AS `average`,
    `dt`.`remark` AS `remark`,
    `dt`.`is_delete` AS `is_delete`,
    `dt`.`created_by` AS `created_by`,
    `dt`.`created_on` AS `created_on`,
    `dt`.`modified_by` AS `modified_by`,
    `dt`.`modified_on` AS `modified_on`,
    `dt`.`deleted_by` AS `deleted_by`,
    `dt`.`deleted_on` AS `deleted_on`,
    `mt`.`transfer_by` AS `transfer_by`,
    `mt`.`created_on` AS `transfer_date`,
    `cm`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`

FROM
    (((`mt_sort_transfer_details` `dt`
LEFT JOIN `mt_sort_transfer_master` `mt` ON
    (`dt`.`sort_transfer_master_id` = `mt`.`sort_transfer_master_id` AND `mt`.`is_delete` = 0))
LEFT JOIN `cm_company` `cm` ON
    (`dt`.`company_id` = `cm`.`company_id` AND `cm`.`is_delete` = 0))
LEFT JOIN `cm_company_branch` `cb` ON
    (`cb`.`company_branch_id` = `dt`.`company_branch_id` AND `cb`.`is_delete` = 0))
WHERE
    `dt`.`is_delete` = 0;



CREATE OR REPLACE VIEW `mtv_sort_transfer_master_rpt` AS
SELECT
    CONCAT(IFNULL(`mt`.`transfer_no`, ''), ':Transfer No:O:N:') AS `transfer_no`,
    CONCAT(IFNULL(`mt`.`from_sort_no`, ''), ':From Sort:Y:T:') AS `from_sort_no`,
    CONCAT(IFNULL(`mt`.`to_sort_no`, ''), ':To Sort:Y:T:') AS `to_sort_no`,
    CONCAT(IFNULL(`mt`.`transfer_by`, ''), ':Transfer By:Y:T:') AS `transfer_by`,
    CONCAT(IFNULL(`mt`.`company_name`, ''), ':Company:Y:T:') AS `company_name`,
    CONCAT(IFNULL(`mt`.`company_branch_name`, ''), ':Branch:Y:T:') AS `company_branch_name`,
    CONCAT(IFNULL(`mt`.`financial_year`, ''), ':Financial Year:O:N:') AS `financial_year`,
    CONCAT(IFNULL(`mt`.`remark`, ''), ':Remarks:Y:T:') AS `remark`,
    CONCAT(IFNULL(`mt`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    CONCAT(IFNULL(`mt`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    CONCAT(IFNULL(`mt`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    CONCAT(IFNULL(`mt`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    CONCAT(IFNULL(`mt`.`sort_transfer_master_id`, ''), ':ID:O:N:') AS `sort_transfer_master_id`
FROM
    `mtv_sort_transfer_master` `mt`
LIMIT 1;