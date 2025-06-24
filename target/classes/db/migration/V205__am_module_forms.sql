-- dbmigration InterUnit Transfer Report create
UPDATE am_modules_forms
SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=8, modules_forms_name='Inter-Unit Transfer Requisition', display_sequence=14, display_name='Inter-Unit Transfer Requisition', menu_type='Purchase', listing_component_name='<FrmMaterialTransferRequisitionListing/>', listing_component_import_path='import FrmMaterialTransferRequisitionListing from "Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionListing";', listing_navigation_url='/Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionListing', form_component_name='<FrmMaterialTransferRequisitionEntry/>', form_component_import_path='import FrmMaterialTransferRequisitionEntry from "Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionEntry";', form_navigation_url='/Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionEntry', icon_class=NULL, page_header='Inter-Unit Transfer Requisition', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter=''
WHERE modules_forms_id=679;


-- dbmigration InterUnit Transfer Report create
INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,6,27,'Inter-Unit Transfer Report',24,'Inter-Unit Transfer','Registers','<FrmMaterialTransferRequisitionListing compType=''Register'' />','','/Transactions/TPurchaseOrder/MaterialTransferRequisition/FrmMaterialTransferRequisitionListing/reg','','','',NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


-- modifictaion in rpt material transfer for listing
create or replace
algorithm = UNDEFINED view `ptv_material_transfer_details_rpt` as
select
    concat(`v`.`transfer_no`, ':Transfer No:Y:T:') as `transfer_no`,
    concat(`v`.`transfer_date`, ':Transfer Date:Y:D:') as `transfer_date`,
    concat(`v`.`material_transfer_type_name`, ':Material Transfer Type Name:Y:T:') as `material_transfer_type_name`,
    concat(`v`.`product_rm_name`, ':Material Name:Y:T:') as `product_rm_name`,
    concat(`v`.`product_code`, ':Material Code:Y:T:') as `product_code`,
    concat(`v`.`stock_quantity`, ':Stock Quantity:O:N:') as `stock_quantity`,
    concat(`v`.`transfer_quantity`, ':Transfer Quantity:O:N:') as `transfer_quantity`,
    concat(`v`.`closing_balance_quantity`, ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
    concat(`v`.`product_material_std_weight`, ':Product Material STD Weight:N:N:') as `product_material_std_weight`,
    concat(`v`.`from_company_name`, ':From Company Name:O:N:') as `from_company_name`,
    concat(`v`.`to_company_name`, ':To Company Name:Y:T:') as `to_company_name`,
    concat(`v`.`product_material_id`, ':Product Id:N:N') as `product_material_id`,
    concat(`v`.`material_transfer_master_id`, ':Material Transfer Master Id:N:N:') as `material_transfer_master_id`,
    concat(`v`.`material_transfer_details_id`, ':Material Transfer Details Id:N:N:') as `material_transfer_details_id`,
    concat(`v`.`godown_name`, ':Godown Name:Y:T:') as `godown_name`,
    concat(`v`.`godown_section_name`, ':Godown Section Name:Y:T:') as `godown_section_name`,
    concat(`v`.`godown_section_beans_name`, ':Godown Section Beans Name:Y:T:') as `godown_section_beans_name`,
    concat(`v`.`stock_weight`, ':Stock Weight:O:N:') as `stock_weight`,
    concat(`v`.`transfer_weight`, ':Transfer Weight:O:N:') as `transfer_weight`,
    concat(`v`.`closing_balance_weight`, ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
    concat(`v`.`financial_year`, ':Financial Year:N:N:') as `financial_year`,
    concat(`v`.`godown_id`, ':Godown Id:N:N:') as `godown_id`,
    concat(`v`.`godown_section_id`, ':Godown Section Id:N:N:') as `godown_section_id`,
    concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
    concat(`v`.`from_company_id`, ':From Company Id:N:T') as `from_company_id`,
    concat(`v`.`from_company_branch_id`, ':From Company Branch Id:N:N:') as `from_company_branch_id`,
    concat(`v`.`to_company_id`, ':To Company Id:N:N') as `to_company_id`,
    concat(`v`.`to_company_branch_id`, ':From Company Branch Id:N:N:') as `to_company_branch_id`,
    concat(`v`.`product_unit_id`, ':Material Unit Id:N:T:') as `product_unit_id`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`material_transfer_type_id`, ':Product Type Id:N:N:') as `material_transfer_type_id`,
    concat(`v`.`transfer_by_id`, ':Transfer By Id:N:N:') as `transfer_by_id`
from
    `ptv_material_transfer_details` `v`
limit 1;