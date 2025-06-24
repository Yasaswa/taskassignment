-- xt_beam_inwards_table definition

CREATE TABLE `xt_production_standard_losses` (
  `production_standard_losses_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* read only back end auto generated *',
  `weave_design_id` bigint(20) NOT NULL COMMENT '* combo box with am_properties   *',
  `weave_design_name` varchar(500) NOT NULL DEFAULT 1 COMMENT '*combo box with am_properties   ',
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cm_company_branch  and set default backend from session  *',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cm_company_branch  and set default backend from session  *',
  `financial_year` varchar(20) NOT NULL,
  `traget_efficiency` decimal(18,4) NOT NULL COMMENT '*  Data Entry',
  `warp_break` decimal(18,4) DEFAULT NULL COMMENT ' text box with data entry and numeric validation',
  `weft_break` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with data entry and numeric validation',
  `knotting` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with data entry and numeric validation',
  `gaiting` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with data entry and numeric validation',
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`production_standard_losses_id`)
)
