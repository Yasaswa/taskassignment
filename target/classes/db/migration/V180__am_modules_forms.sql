INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,8,'Material Transfer Requisition',14,'Material Transfer Requisition','Purchase','<FrmMaterialTransferRequisitionListing/>','import FrmMaterialTransferRequisitionListing from "Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionListing";','/Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionListing','<FrmMaterialTransferRequisitionEntry/>','import FrmMaterialTransferRequisitionEntry from "Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionEntry";','/Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionEntry',NULL,'Material Transfer Requisition',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');



-- Table changes in Transfer Master and Details
ALTER TABLE pt_material_transfer_master ADD financial_year VARCHAR(55) NULL;

ALTER TABLE pt_material_transfer_details CHANGE godown_beans_id godown_section_beans_id bigint(20) DEFAULT NULL NULL;