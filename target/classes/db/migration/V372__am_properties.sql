UPDATE am_properties
SET property_group = CASE
    WHEN property_value = 'Sale Purchase' THEN 'FTX'
    WHEN property_value = 'Job Work' THEN 'JTX'
    WHEN property_value = 'Beam Sale' THEN 'BTX'
    WHEN property_value = 'Yarn Sale' THEN 'YTX'
    WHEN property_value = 'Fabric Sale' THEN 'FTX'
    ELSE property_group
END
WHERE property_value IN ('Sale Purchase', 'Job Work', 'Beam Sale', 'Yarn Sale', 'Fabric Sale')
AND is_delete = 0;


ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD job_type_id bigint(20) DEFAULT 0 NOT NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_details ADD job_type_id bigint(20) DEFAULT 0 NOT NULL;


create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_master` as
select
    `mt`.`dispatch_challan_sized_yarn_id` as `dispatch_challan_sized_yarn_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`job_type_id` as `job_type_id`,
    `ap`.`property_name` as `job_type_name`,
    `mt`.`set_no` as `set_no`,
    `mt`.`yarn_count` as `yarn_count`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_name` as `product_material_name`,
    `mt`.`total_ends` as `total_ends`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `a`.`agent_address1` as `agent_address1`,
    `a`.`agent_cell_no` as `agent_cell_no`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`dispatch_date` as `dispatch_date`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`rate` as `rate`,
    `mt`.`weight` as `weight`,
    `mt`.`dispatch_payment_terms` as `dispatch_payment_terms`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `mt`.`vehicle_no` as `vehicle_no`,
    case
        `mt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `mt`.`is_delete` when 1 then 'Yes'
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
    `mt`.`driver_name` as `driver_name`,
    `mt`.`yarn_bill_no` as `yarn_bill_no`,
    `c`.`customer_name` as `customer_name`,
    `c`.`cust_branch_phone_no` as `customer_phone`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `ct`.`city_name` as `customer_city_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `cc`.`cust_branch_address1` as `consignee_address`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `st1`.`state_name` as `consignee_state_name`,
    `ct1`.`city_name` as `consignee_city_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_EmailId` as `company_emailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`
from
    (((((((((((`mt_dispatch_challan_sized_yarn_master` `mt`
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `am_properties` `ap` on
    (`ap`.`property_id`  = `mt`.`job_type_id` and `ap`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `mt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_customer` `cc` on
    (`cc`.`company_id` = `mt`.`company_id`
        and `cc`.`customer_id` = `mt`.`consignee_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`customer_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `mt`.`consignee_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`customer_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `mt`.`consignee_state_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;



    create or replace
    algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_details` as
    select
        `mt`.`dispatch_challan_sized_yarn_details_id` as `dispatch_challan_sized_yarn_details_id`,
        `mt`.`dispatch_challan_sized_yarn_id` as `dispatch_challan_sized_yarn_id`,
        `mt`.`company_id` as `company_id`,
        `mt`.`company_branch_id` as `company_branch_id`,
        `mt`.`financial_year` as `financial_year`,
        `mt`.`job_type_id` as `job_type_id`,
        `ap`.`property_name` as `job_type_name`,
        `mt`.`set_no` as `set_no`,
        `mt`.`total_ends` as `total_ends`,
        `mt`.`beam_id` as `beam_id`,
        `mt`.`product_material_name` as `product_material_name`,
        `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
        `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
        `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
        `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
        `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
        `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
        `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
        `mt`.`customer_id` as `customer_id`,
        `mt`.`customer_order_no` as `customer_order_no`,
        `mt`.`customer_name` as `customer_name`,
        `mt`.`dispatch_date` as `dispatch_date`,
        `mt`.`approved_by_id` as `approved_by_id`,
        `mt`.`approved_date` as `approved_date`,
        `mt`.`sizing_length` as `sizing_length`,
        `mt`.`gross_weight` as `gross_weight`,
        `mt`.`tare_weight` as `tare_weight`,
        `mt`.`net_weight` as `net_weight`,
        `mt`.`remark` as `remark`,
        `mmt`.`product_material_id` as `product_material_id`,
        `mmt`.`vehicle_no` as `vehicle_no`,
        `mmt`.`agent_id` as `agent_id`,
        `mmt`.`customer_contacts_ids` as `customer_contacts_ids`,
        `mmt`.`customer_state_id` as `customer_state_id`,
        `mmt`.`customer_city_id` as `customer_city_id`,
        `mmt`.`consignee_id` as `consignee_id`,
        `mmt`.`consignee_state_id` as `consignee_state_id`,
        `mmt`.`consignee_city_id` as `consignee_city_id`,
        `mmt`.`rate` as `rate`,
        `mmt`.`weight` as `weight`,
        `mmt`.`other_terms_conditions` as `other_terms_conditions`,
        `mmt`.`driver_name` as `driver_name`,
        `mmt`.`yarn_bill_no` as `yarn_bill_no`,
        `mt`.`sizing_production_code` as `sizing_production_code`,
        `mt`.`sizing_production_date` as `sizing_production_date`,
        `mt`.`beam_inward_type` as `beam_inward_type`,
        case
            `mt`.`is_active` when 1 then 'Active'
            else 'In Active'
        end as `Active`,
        case
            `mt`.`is_delete` when 1 then 'Yes'
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
        `c`.`cust_branch_phone_no` as `customer_phone`,
        `c`.`cust_branch_EmailId` as `customer_email`,
        `ct`.`city_name` as `customer_city_name`,
        `st`.`state_name` as `customer_state_name`,
        `e1`.`employee_name` as `approved_by_name`,
        `v`.`company_legal_name` as `company_name`,
        `cb`.`company_branch_name` as `company_branch_name`
    from
        ((((((((((`mt_dispatch_challan_sized_yarn_details` `mt`
    left join `cmv_company` `v` on
        (`v`.`company_branch_id` = `mt`.`company_branch_id`
            and `v`.`company_id` = `mt`.`company_id`))
    left join `am_properties` `ap` on
        (`ap`.`property_id`  = `mt`.`job_type_id` and `ap`.`is_delete` = 0))
    left join `cm_company_branch` `cb` on
        (`cb`.`company_branch_id` = `mt`.`company_branch_id`
            and `cb`.`is_delete` = 0))
    left join `cmv_customer` `c` on
        (`c`.`company_id` = `mt`.`company_id`
            and `c`.`customer_id` = `mt`.`customer_id`))
    left join `mt_dispatch_challan_sized_yarn_master` `mmt` on
        (`mmt`.`dispatch_challan_sized_yarn_id` = `mt`.`dispatch_challan_sized_yarn_id`
            and `mmt`.`is_delete` = 0))
    left join `cm_city` `ct` on
        (`ct`.`city_id` = `mmt`.`customer_city_id`))
    left join `cm_city` `ct1` on
        (`ct1`.`city_id` = `mmt`.`consignee_city_id`))
    left join `cm_state` `st` on
        (`st`.`state_id` = `mmt`.`customer_state_id`))
    left join `cm_state` `st1` on
        (`st1`.`state_id` = `mmt`.`consignee_state_id`))
    left join `cm_employee` `e1` on
        (`e1`.`company_branch_id` = `mt`.`company_branch_id`
            and `e1`.`company_id` = `mt`.`company_id`
            and `e1`.`employee_id` = `mt`.`approved_by_id`))
    where
        `mt`.`is_delete` = 0;


        create or replace
        algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_master_rpt` as
        select
            concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
            concat(ifnull(`v`.`yarn_count`, ''), ':Yarn Count:Y:T:') as `yarn_count`,
            concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:Y:T:') as `product_material_id`,
            concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:T:') as `product_material_name`,
            concat(ifnull(`v`.`dispatch_date`, ''), ':Dispatch Date:Y:D:') as `dispatch_date`,
            concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
            concat(ifnull(`v`.`job_type_name`, ''), ':Job Type:Y:T:') as `job_type_name`,
            concat(ifnull(`v`.`total_ends`, ''), ':Total Ends:O:N:') as `total_ends`,
            concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
            concat(ifnull(`v`.`dispatch_challan_no`, ''), ':Dispatch Challan Number:Y:T:') as `dispatch_challan_no`,
            concat(ifnull(`v`.`dispatch_challan_date`, ''), ':Dispatch Challan Date:Y:D:') as `dispatch_challan_date`,
            concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
            concat(ifnull(`v`.`yarn_bill_no`, ''), ':Yarn Bill No:Y:T:') as `yarn_bill_no`,
            concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
            concat(ifnull(`v`.`rate`, ''), ':Rate:Y:T') as `rate`,
            concat(ifnull(`v`.`weight`, ''), ':Weight:Y:T') as `weight`,
            concat(ifnull(`v`.`consignee_name`, ''), ':Consignee Name:Y:T:') as `consignee_name`,
            concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By Name:Y:T:') as `approved_by_name`,
            concat(case when ifnull(`v`.`is_delete`, 0) = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') as `customer_id`,
            concat(ifnull(`v`.`dispatch_challan_sized_yarn_id`, ''), ':Dispatch Challan Sized Yarn Id:O:N:') as `dispatch_challan_sized_yarn_id`
        from
            `mtv_dispatch_challan_sized_yarn_master` `v`
        limit 1;


        create or replace
        algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_details_rpt` as
        select
            concat(ifnull(`v`.`beam_inward_type`, ''), ':Beam Name:Y:T:') as `beam_inward_type`,
            concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
            concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:Y:T:') as `product_material_id`,
            concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:T:') as `product_material_name`,
            concat(ifnull(`v`.`dispatch_date`, ''), ':Dispatch Date:Y:D:') as `dispatch_date`,
            concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T:') as `customer_name`,
            concat(ifnull(`v`.`job_type_name`, ''), ':Job Type:Y:T:') as `job_type_name`,
            concat(ifnull(`v`.`total_ends`, ''), ':Total Ends:O:N:') as `total_ends`,
            concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
            concat(ifnull(`v`.`dispatch_challan_no`, ''), ':Dispatch Challan Number:Y:T:') as `dispatch_challan_no`,
            concat(ifnull(`v`.`dispatch_challan_date`, ''), ':Dispatch Challan Date:Y:D:') as `dispatch_challan_date`,
            concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
            concat(ifnull(`v`.`yarn_bill_no`, ''), ':Yarn Bill No:Y:T:') as `yarn_bill_no`,
            concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
            concat(ifnull(`v`.`sizing_length`, ''), ':Sizing Length:Y:T') as `sizing_length`,
            concat(ifnull(`v`.`gross_weight`, ''), ':Gross Weight:Y:T') as `gross_weight`,
            concat(ifnull(`v`.`tare_weight`, ''), ':Tare Weight:Y:T') as `tare_weight`,
            concat(ifnull(`v`.`net_weight`, ''), ':Net Weight:Y:T') as `net_weight`,
            concat(ifnull(`v`.`rate`, ''), ':Rate:Y:T') as `rate`,
            concat(ifnull(`v`.`weight`, ''), ':Weight:Y:T') as `weight`,
            concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By Name:Y:T:') as `approved_by_name`,
            concat(case when ifnull(`v`.`is_delete`, 0) = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') as `customer_id`,
            concat(ifnull(`v`.`dispatch_challan_sized_yarn_id`, ''), ':Dispatch Challan Sized Yarn Id:O:N:') as `dispatch_challan_sized_yarn_id`,
            concat(ifnull(`v`.`dispatch_challan_sized_yarn_details_id`, ''), ':Dispatch Challan Sized Yarn Details Id:O:N:') as `dispatch_challan_sized_yarn_details_id`
        from
            `mtv_dispatch_challan_sized_yarn_details` `v`
        limit 1;

