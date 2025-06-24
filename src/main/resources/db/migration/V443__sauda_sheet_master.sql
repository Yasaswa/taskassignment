CREATE TABLE `mt_sauda_sheet_master` (
  `sauda_sheet_master_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* read only back end auto generated *',
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `financial_year` varchar(20) NOT NULL,
  `sauda_sheet_no` varchar(50) NOT NULL,
  `sauda_sheet_date` date NOT NULL COMMENT '* DTPpicker Data Entry',
  `product_sort_no` varchar(50) NOT NULL,
  `product_type_id` varchar(20) NOT NULL,
  `warp_crimp` decimal(18,4) DEFAULT 0.0000,
  `weft_crimp` decimal(18,4) DEFAULT 0.0000,
  `warp_count` decimal(18,4) DEFAULT 0.0000,
  `weft_count` decimal(18,4) DEFAULT 0.0000,
  `warp_req_1_mtr_cloth` decimal(18,4) DEFAULT 0.0000,
  `weft_req_1_mtr_cloth` decimal(18,4) DEFAULT 0.0000,
  `warp_waste` decimal(18,4) DEFAULT 0.0000,
  `weft_waste` decimal(18,4) DEFAULT 0.0000,
  `warp_for_cost_Rs_Per_mtr` decimal(18,4) DEFAULT 0.0000,
  `weft_for_cost_Rs_Per_mtr` decimal(18,4) DEFAULT 0.0000,
  `weaving_cost_RS_per_Pick_per_inch_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `warping_sizing_cost_RS_per_kg` decimal(18,4) DEFAULT 0.0000,
  `stream_cost_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `weaving_cost_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `mending_cost_RS_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `packing_cost_RS_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `epi` decimal(18,4) DEFAULT 0.0000,
  `ppi` decimal(18,4) DEFAULT 0.0000,
  `finance_cost` decimal(18,4) DEFAULT 0.0000,
  `weft_yarn_price` decimal(18,4) DEFAULT 0.0000,
  `warp_yarn_price` decimal(18,4) DEFAULT 0.0000,
  `warping_sizing_cost_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `total_cost_yarn_for_1_mtr` decimal(18,4) DEFAULT 0.0000,
  `total_cost_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `value_loss_percent` decimal(18,4) DEFAULT 0.0000,
  `value_loss_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `cost_of_fabric_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `glm_without_size` decimal(18,4) DEFAULT 0.0000,
  `waste_realization_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `size_add_on` decimal(18,4) DEFAULT 0.0000,
  `glm_with_size` decimal(18,4) DEFAULT 0.0000,
  `gsm_with_size` decimal(18,4) DEFAULT 0.0000,
  `strem_cost_RS_per_Kg` decimal(18,4) DEFAULT 0.0000,
  `selling_price_without_commi_rs_pr_mtr` decimal(18,4) DEFAULT 0.0000,
  `commission_percent` decimal(18,4) DEFAULT 0.0000,
  `commission_Rs_per_mtr` decimal(18,4) DEFAULT 0.0000,
  `cost_of_fabric_Rs_mtr` decimal(18,4) DEFAULT 0.0000,
  `grey_width` decimal(18,4) DEFAULT 0.0000,
  `customer_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_customer show address, Contact person list ,emails, customer state combo,customer city Combo',
  `customer_state_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_state show will change on selection of customer combo',
  `customer_city_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_city show will change on selection of customer combo',
  `agent_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_customer show address, Contact person list ,emails, customer state combo,customer city Combo',
  `agent_state_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_state show will change on selection of customer combo',
  `agent_city_id` bigint(20) DEFAULT NULL COMMENT 'combo box with cmv_city show will change on selection of customer combo',
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `sauda_sheet_status` varchar(3) DEFAULT 'P',
  `approved_by_id` bigint(20) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  PRIMARY KEY (`sauda_sheet_master_id`)
);




CREATE TABLE `mt_sauda_sheet_payment_terms` (
  `sauda_sheet_payment_terms_transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `sauda_sheet_master_id` bigint(20) NOT NULL COMMENT '* read only back end auto generated from master id *',
  `sauda_sheet_no` varchar(50) NOT NULL COMMENT '*read only text box Combination of Company Short name/FyearShort name/ProductType_group / SO0000Last ID ',
  `sauda_sheet_date` date NOT NULL COMMENT '* DTPpicker Data Entry',
  `payment_terms_id` bigint(20) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_name` varchar(255) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_days` varchar(255) NOT NULL COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_grace_days` bigint(20) DEFAULT 0 COMMENT '*  Data Grid  with  Dispaly from cm_payment_terms *',
  `payment_terms_Milestome` varchar(255) DEFAULT NULL COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_percent` varchar(255) DEFAULT NULL COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_expected_value` decimal(18,4) DEFAULT 1.0000 COMMENT '*  Data Grid  with text box Data Entry with validation*',
  `payment_expected_date` date DEFAULT NULL COMMENT '*  Data Grid  with DTPpicker Data Entry with validation*',
  `payment_paid_flag` varchar(50) DEFAULT 'P' COMMENT ' * combo box  P-Pending, T-Partail,  R-Receipt',
  `payment_paid_transaction_id` varchar(1000) DEFAULT NULL COMMENT '* read only back end will get on : separation basis update once Payment Receipt Id*',
  `payment_paid_date` varchar(1000) DEFAULT NULL COMMENT '*   read only back end on : separation basis will get update once Payment Receipt Date *',
  `remark` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`sauda_sheet_payment_terms_transaction_id`)
) ;

-- erp_new.mtv_sauda_sheet_master source

create or replace
algorithm = UNDEFINED view `mtv_sauda_sheet_master` as
select
    `pt`.`sauda_sheet_no` as `sauda_sheet_no`,
    `pt`.`sauda_sheet_date` as `sauda_sheet_date`,
    `pt`.`product_sort_no` as `product_sort_no`,
    `pt`.`sauda_sheet_master_id` as `sauda_sheet_master_id`,
    `pt`.`weft_crimp` as `weft_crimp`,
    `pt`.`warp_crimp` as `warp_crimp`,
    `pt`.`warp_count` as `warp_count`,
    `pt`.`weft_count` as `weft_count`,
    `pt`.`warp_req_1_mtr_cloth` as `warp_req_1_mtr_cloth`,
    `pt`.`weft_req_1_mtr_cloth` as `weft_req_1_mtr_cloth`,
    `pt`.`product_type_id` as `product_type_id`,
    `pt`.`warp_waste` as `warp_waste`,
    `pt`.`weft_waste` as `weft_waste`,
    `pt`.`warp_for_cost_Rs_Per_mtr` as `warp_for_cost_Rs_Per_mtr`,
    `pt`.`weft_for_cost_Rs_Per_mtr` as `weft_for_cost_Rs_Per_mtr`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`weaving_cost_RS_per_Pick_per_inch_per_mtr` as `weaving_cost_RS_per_Pick_per_inch_per_mtr`,
    `pt`.`warping_sizing_cost_RS_per_kg` as `warping_sizing_cost_RS_per_kg`,
    `pt`.`stream_cost_Rs_per_mtr` as `stream_cost_Rs_per_mtr`,
    `pt`.`weaving_cost_Rs_per_mtr` as `weaving_cost_Rs_per_mtr`,
    `pt`.`mending_cost_RS_per_mtr` as `mending_cost_RS_per_mtr`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`packing_cost_RS_per_mtr` as `packing_cost_RS_per_mtr`,
    `pt`.`epi` as `epi`,
    `pt`.`ppi` as `ppi`,
    `pt`.`finance_cost` as `finance_cost`,
    `pt`.`weft_yarn_price` as `weft_yarn_price`,
    `pt`.`warp_yarn_price` as `warp_yarn_price`,
    `pt`.`warping_sizing_cost_Rs_per_mtr` as `warping_sizing_cost_Rs_per_mtr`,
    `pt`.`total_cost_yarn_for_1_mtr` as `total_cost_yarn_for_1_mtr`,
    `pt`.`total_cost_Rs_per_mtr` as `total_cost_Rs_per_mtr`,
    `pt`.`value_loss_percent` as `value_loss_percent`,
    `pt`.`value_loss_Rs_per_mtr` as `value_loss_Rs_per_mtr`,
    `pt`.`cost_of_fabric_Rs_per_mtr` as `cost_of_fabric_Rs_per_mtr`,
    `pt`.`glm_without_size` as `glm_without_size`,
    `pt`.`waste_realization_Rs_per_mtr` as `waste_realization_Rs_per_mtr`,
    `pt`.`size_add_on` as `size_add_on`,
    `pt`.`glm_with_size` as `glm_with_size`,
    `pt`.`gsm_with_size` as `gsm_with_size`,
    `pt`.`strem_cost_RS_per_Kg` as `strem_cost_RS_per_Kg`,
    `pt`.`selling_price_without_commi_rs_pr_mtr` as `selling_price_without_commi_rs_pr_mtr`,
    `pt`.`commission_percent` as `commission_percent`,
    `pt`.`commission_Rs_per_mtr` as `commission_Rs_per_mtr`,
    `pt`.`cost_of_fabric_Rs_mtr` as `cost_of_fabric_Rs_mtr`,
    `pt`.`grey_width` as `grey_width`,
    `pdt`.`product_type_name` as `product_type_name`,
    `pt`.`sauda_sheet_status` as `sauda_sheet_status`,
    `vb`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`company_address1` as `company_address1`,
    `vb`.`company_address2` as `company_address2`,
    `vb`.`company_phone_no` as `company_phone_no`,
    `vb`.`company_cell_no` as `company_cell_no`,
    `vb`.`company_EmailId` as `company_EmailId`,
    `vb`.`company_website` as `company_website`,
    `vb`.`company_gst_no` as `company_gst_no`,
    `vb`.`company_pan_no` as `company_pan_no`,
    `vb`.`company_pincode` as `company_pincode`,
    `cbnk`.`company_branch_bank_name` as `company_branch_bank_name`,
    `cbnk`.`company_branch_bank_account_no` as `company_branch_bank_account_no`,
    `cbnk`.`company_branch_bank_ifsc_code` as `company_branch_bank_ifsc_code`,
    `cust`.`customer_name` as `customer_name`,
    `e`.`employee_name` as `approved_by_name`,
    `cust`.`cust_branch_address1` as `cust_branch_address1`,
    `cust`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `cust`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `cccty`.`city_name` as `cust_city_name`,
    `ccs`.`state_name` as `cust_state_name`,
    `ccctyg`.`city_name` as `agent_city_name`,
    `ccsg`.`state_name` as `agent_state_name`,
    `ag`.`agent_name` as `agent_name`,
    `ag`.`agent_address1` as `agent_address1`,
    `ag`.`agent_gst_no` as `agent_gst_no`,
    `ag`.`agent_cell_no` as `agent_cell_no`,
    `pt`.`agent_id` as `agent_id`,
    `pt`.`agent_state_id` as `agent_state_id`,
    `pt`.`agent_city_id` as `agent_city_id`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`customer_state_id` as `customer_state_id`,
    `pt`.`customer_city_id` as `customer_city_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    `pt`.`approved_date` as `approved_date`,
    case
        `pt`.`sauda_sheet_status` when 'A' then 'Approved'
        else 'Pending'
    end as `sauda_sheet_status_desc`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`
from
    ((((((((((`mt_sauda_sheet_master` `pt`
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `pt`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `cm_company_banks` `cbnk` on
    (`cbnk`.`company_id` = `pt`.`company_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pt`.`company_branch_id`
        and `vb`.`company_id` = `pt`.`company_id`))
left join `cmv_customer` `cust` on
    (`cust`.`customer_id` = `pt`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `cmv_agent` `ag` on
    (`ag`.`agent_id` = `pt`.`agent_id`
        and `ag`.`is_delete` = 0))
left join `cm_state` `ccs` on
    (`ccs`.`state_id` = `pt`.`customer_state_id`))
left join `cm_city` `cccty` on
    (`cccty`.`city_id` = `pt`.`customer_city_id`))
left join `cm_state` `ccsg` on
    (`ccsg`.`state_id` = `pt`.`agent_state_id`))
left join `cm_city` `ccctyg` on
    (`ccctyg`.`city_id` = `pt`.`agent_city_id`))
left join `cm_employee` `e` on
    (`e`.`is_active` = 1
        and `e`.`is_delete` = 0
        and `e`.`employee_id` = `pt`.`approved_by_id`))
where
    `pt`.`is_delete` = 0;



-- erp_new.mtv_sauda_sheet_master_rpt source

create or replace
algorithm = UNDEFINED view `mtv_sauda_sheet_master_rpt` as
select
    concat(`pt`.`sauda_sheet_no`, ':Sauda Sheet No:Y:T:') as `sauda_sheet_no`,
    concat(`pt`.`product_sort_no`, ':Product Sort No:Y:T:') as `product_sort_no`,
    concat(`pt`.`sauda_sheet_date`, ':Sauda Sheet Date:Y:D:') as `sauda_sheet_date`,
    concat(`pt`.`sauda_sheet_status_desc`, ':Sauda Sheet Status:Y:T:') as `sauda_sheet_status_desc`,
    concat(`pt`.`approved_by_name`, ':Approved By:Y:T:') as `approved_by_name`,
    concat(`pt`.`weft_crimp`, ':Weft Crimp:O:N:') as `weft_crimp`,
    concat(`pt`.`warp_crimp`, ':Warp Crimp:O:N:') as `warp_crimp`,
    concat(`pt`.`warp_count`, ':Warp Count:O:N:') as `warp_count`,
    concat(`pt`.`weft_count`, ':Weft Count:O:N:') as `weft_count`,
    concat(`pt`.`warp_req_1_mtr_cloth`, ':Warp Req/Mtr Cloth:O:N:') as `warp_req_1_mtr_cloth`,
    concat(`pt`.`weft_req_1_mtr_cloth`, ':Weft Req/Mtr Cloth:O:N:') as `weft_req_1_mtr_cloth`,
    concat(`pt`.`product_type_id`, ':Product Type Id:O:N:') as `product_type_id`,
    concat(`pt`.`product_type_name`, ':Product Type:Y:T:') as `product_type_name`,
    concat(`pt`.`warp_waste`, ':Warp Waste:O:N:') as `warp_waste`,
    concat(`pt`.`weft_waste`, ':Weft Waste:O:N:') as `weft_waste`,
    concat(`pt`.`warp_yarn_price`, ':Warp Yarn Price:O:N:') as `warp_yarn_price`,
    concat(`pt`.`weft_yarn_price`, ':Weft Yarn Price:O:N:') as `weft_yarn_price`,
    concat(`pt`.`warp_for_cost_Rs_Per_mtr`, ':Warp Cost/Per Mtr:O:N:') as `warp_for_cost_Rs_Per_mtr`,
    concat(`pt`.`weft_for_cost_Rs_Per_mtr`, ':Weft Cost/Per Mtr:O:N:') as `weft_for_cost_Rs_Per_mtr`,
    concat(`pt`.`total_cost_yarn_for_1_mtr`, ':Total Yarn Cost/Mtr:O:N:') as `total_cost_yarn_for_1_mtr`,
    concat(`pt`.`total_cost_Rs_per_mtr`, ':Total Cost/Mtr:O:N:') as `total_cost_Rs_per_mtr`,
    concat(`pt`.`value_loss_percent`, ':Value Loss (%):O:N:') as `value_loss_percent`,
    concat(`pt`.`value_loss_Rs_per_mtr`, ':Value Loss (Rs/Mtr):O:N:') as `value_loss_Rs_per_mtr`,
    concat(`pt`.`cost_of_fabric_Rs_per_mtr`, ':Fabric Cost/Mtr:O:N:') as `cost_of_fabric_Rs_per_mtr`,
    concat(`pt`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`pt`.`customer_name`, ':Customer Name:Y:T:') as `customer_name`,
    concat(`pt`.`agent_name`, ':Agent Name:Y:T:') as `agent_name`,
    concat(`pt`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`pt`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`pt`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`pt`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`pt`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`pt`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`pt`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`pt`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`pt`.`sauda_sheet_master_id`, ':Sauda Sheet Master Id:O:N:') as `sauda_sheet_master_id`
from
    `mtv_sauda_sheet_master` `pt`
limit 1;


INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (2,3,1,19,3,16,'Sauda Sheet',1,'Sauda Sheet','Masters','<FrmSaudaSheetListing />','import FrmSaudaSheetListing from "/Masters/MSaudaSheet/FrmSaudaSheetListing";','/Masters/MSaudaSheet/FrmSaudaSheetListing','<FrmSaudaSheetEntry />','import FrmSaudaSheetEntry from "/Masters/MSaudaSheet/FrmSaudaSheetEntry";','/Masters/MSaudaSheet/FrmSaudaSheetEntry',NULL,'Sauda Sheet',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');
