INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (183,'Sales Type',2,'Goods Return','Goods Return',1,0,'dakshabhiadmin','2025-06-19 15:13:23.000',NULL,'2025-06-19 15:13:23.000',NULL,NULL,'',''),
	 (183,'Sales Type',2,'Goods Purchase','Goods Purchase',1,0,'dakshabhiadmin','2025-06-19 15:14:11.000',NULL,'2025-06-19 15:14:11.000',NULL,NULL,'','');


create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_master` as
select
    `mt`.`goods_return_fabric_master_id` as `goods_return_fabric_master_id`,
    `mt`.`goods_return_fabric_no` as `goods_return_fabric_no`,
    `mt`.`goods_return_fabric_date` as `goods_return_fabric_date`,
    `mt`.`goods_return_fabric_version` as `goods_return_fabric_version`,
    `mt`.`goods_purchase_type` as `goods_purchase_type`,
    `mt`.`goods_purchase_tax_type` as `goods_purchase_tax_type`,
    `mt`.`goods_purchase_sales_type` as `goods_purchase_sales_type`,
    `mt`.`goods_purchase_voucher_type` as `goods_purchase_voucher_type`,
    `mt`.`fabric_type` as `fabric_type`,
    `mt`.`supplier_id` as `supplier_id`,
    `cs`.`supp_branch_name` as `supplier_name`,
    `mt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `mt`.`supplier_state_id` as `supplier_state_id`,
    `ct`.`city_name` as `supplier_city_name`,
    `mt`.`supplier_city_id` as `supplier_city_id`,
    `st`.`state_name` as `supplier_state_name`,
    `mt`.`expected_branch_id` as `expected_branch_id`,
    `mt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `mt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `mt`.`goods_return_fabric_type_id` as `goods_return_fabric_type_id`,
    `mt`.`goods_return_fabric_type` as `goods_return_fabric_type`,
    `mt`.`supplier_challan_no` as `supplier_challan_no`,
    `mt`.`supplier_challan_date` as `supplier_challan_date`,
    `mt`.`supplier_invoice_no` as `supplier_invoice_no`,
    `mt`.`supplier_invoice_date` as `supplier_invoice_date`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`qa_by_id` as `qa_by_id`,
    `mt`.`qa_date` as `qa_date`,
    `mt`.`basic_total` as `basic_total`,
    `mt`.`transport_amount` as `transport_amount`,
    `mt`.`freight_amount` as `freight_amount`,
    `mt`.`is_freight_taxable` as `is_freight_taxable`,
    `mt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `h`.`hsn_sac_code` as `hsn_sac_code`,
    `h`.`hsn_sac_rate` as `hsn_sac_rate`,
    `mt`.`packing_amount` as `packing_amount`,
    `mt`.`goods_return_discount_amount` as `goods_return_discount_amount`,
    `mt`.`goods_return_discount_percent` as `goods_return_discount_percent`,
    `mt`.`other_amount` as `other_amount`,
    `mt`.`taxable_total` as `taxable_total`,
    `mt`.`cgst_total` as `cgst_total`,
    `mt`.`sgst_total` as `sgst_total`,
    `mt`.`igst_total` as `igst_total`,
    `mt`.`roundoff` as `roundoff`,
    `mt`.`grand_total` as `grand_total`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    `mt`.`lr_no` as `lr_no`,
    `mt`.`lr_date` as `lr_date`,
    `mt`.`goods_return_fabric_status` as `goods_return_fabric_status`,
    case
        `mt`.`goods_return_fabric_status` when 'A' then 'Approved'
        else 'Pending'
    end as `goods_return_fabric_status_desc`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`vehicle_no` as `vehicle_no`,
    `mt`.`ev_bill_no` as `ev_bill_no`,
    `mt`.`ev_bill_date` as `ev_bill_date`,
    `e`.`employee_name` as `approved_by_name`,
    `e1`.`employee_name` as `qa_by_name`,
    case
        when `mt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`purchase_type` as `purchase_type`,
    `mt`.`financial_year` as `financial_year`,
    `cmc`.`company_branch_name` as `company_name`,
    `cmc`.`branch_address1` as `company_address`
from
    ((((((((`pt_goods_return_fabric_master` `mt`
left join `cm_company_branch` `cmc` on
    (`cmc`.`company_branch_id` = `mt`.`expected_branch_id`
        and `cmc`.`company_id` = `mt`.`company_id`
        and `cmc`.`is_delete` = 0))
left join `cm_supplier_branch` `cs` on
    (`cs`.`supplier_id` = `mt`.`supplier_id`
        and `cs`.`is_delete` = 0))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`supplier_city_id`
        and `ct`.`is_delete` = 0))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`supplier_state_id`
        and `st`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`
        and `h`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `mt`.`approved_by_id`
        and `e`.`is_delete` = 0))
 left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `mt`.`qa_by_id`
        and `e1`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0;





create or replace
algorithm = UNDEFINED view `ptv_goods_return_fabric_master_rpt` as
select
    concat(`v`.`goods_return_fabric_no`, ':Goods Return No:Y:T:') as `goods_return_fabric_no`,
    concat(`v`.`goods_return_fabric_version`, ':Goods Return Version:Y:T:') as `goods_return_fabric_version`,
    concat(`v`.`goods_return_fabric_type`, ':Fabric Type:Y:T:') as `goods_return_fabric_type`,
    concat(`v`.`goods_return_fabric_date`, ':Goods Return Date:Y:D:') as `goods_return_fabric_date`,
    concat(`v`.`purchase_type`, ':Purcahse Type:Y:T:') as `purchase_type`,
    concat(`v`.`supplier_name`, ':Supplier Name:Y:T:') as `supplier_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T:') as `approved_by_name`,
    concat(`v`.`goods_return_fabric_status_desc`, ':Goods Return Status:Y:H:(Aprroved,Pending)') as `goods_return_fabric_status_desc`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:T:') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Sales Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`supplier_invoice_no`, ''), ':Supplier Invoice No:Y:T:') as `supplier_invoice_no`,
    concat(ifnull(`v`.`supplier_invoice_date`, ''), ':Supplier Invoice Date:Y:D:') as `supplier_invoice_date`,
    concat(`v`.`vehicle_no`, ':Vehicle No:Y:T:') as `vehicle_no`,
    concat(`v`.`lr_no`, ':Lr. No:Y:T:') as `lr_no`,
    concat(`v`.`lr_date`, ':Lr. Date:Y:D:') as `lr_date`,
    concat(`v`.`ev_bill_no`, ':Ev Bill No:O:N:') as `ev_bill_no`,
    concat(`v`.`ev_bill_date`, ':Ev Bill Date:O:N:') as `ev_bill_date`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') as `basic_total`,
    concat(`v`.`agent_name`, ':Agent Name:O:N:') as `agent_name`,
    concat(`v`.`financial_year`, ': Financial Year:Y:T:') as `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`goods_return_fabric_master_id`, ':Return Fabric Master Transaction Id:O:N:') as `goods_return_fabric_master_id`
from
    `ptv_goods_return_fabric_master` `v`
limit 1;