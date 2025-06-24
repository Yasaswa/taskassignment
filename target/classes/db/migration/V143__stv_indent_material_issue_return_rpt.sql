create or replace
    algorithm = UNDEFINED view `stv_indent_material_issue_return_master_rpt` as
    select
        concat(`v`.`issue_return_no`, ':Issue Return No:O:N:') as `issue_return_no`,
        concat(`v`.`issue_return_date`, ':Issue Return Date :Y:D:') as `issue_return_date`,
        concat(`v`.`issue_no`, ':Issue No:O:N:') as `issue_no`,
        concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
        concat(`v`.`indent_issue_return_type`, ':Issue Return Type:O:N:') as `indent_issue_return_type`,
        concat(`v`.`issue_return_status`, ':Issue Return Status:O:N:') as `issue_return_status`,
        concat(`v`.`received_date`, ':Issue Received Date:Y:D:') as `received_date`,
        concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
        concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') as `issue_return_master_transaction_id`,
        concat(`v`.`return_by_id`, ':Issue Return By Id:O:N:') as `return_by_id`,
        concat(`v`.`department_id`, ':Department Id:O:N:') as `department_id`,
        concat(`v`.`sub_department_id`, ':Sub Department Id:O:N:') as `sub_department_id`,
        concat(`v`.`indent_issue_return_type_id`, ':Issue Return Type Id:O:N:') as `indent_issue_return_type_id`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
        concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
        concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
        concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
        concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
        concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
        concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
    from
        `stv_indent_material_issue_return_master` `v`
    limit 1;




    create or replace
        algorithm = UNDEFINED view `stv_indent_material_issue_return_details_rpt` as
        select
            concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
            concat(`v`.`product_material_issue_return_quantity`, ':Material Issued Return Quantity :O:N:') as `product_material_issue_return_quantity`,
            concat(`v`.`product_material_issue_return_weight`, ':Material Issued Return Weight:O:N:') as `product_material_issue_return_weight`,
            concat(`v`.`product_material_issue_return_boxes`, ':Material Issued Return Boxes:O:N:') as `product_material_issue_return_boxes`,
            concat(`v`.`product_material_receipt_quantity`, ':Material Receipt Quantity Id:O:N:') as `product_material_receipt_quantity`,
            concat(`v`.`product_material_receipt_weight`, ':Material Receipt Weight:O:N:') as `product_material_receipt_weight`,
            concat(`v`.`product_material_receipt_boxes`, ':Material Receipt Boxes Id:O:N:') as `product_material_receipt_boxes`,
            concat(`v`.`issue_return_remark`, ':Issue Return No:O:N:') as `issue_return_remark`,
            concat(`v`.`issue_return_item_status`, ':Issue No:O:N:') as `issue_return_item_status`,
            concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') as `issue_return_master_transaction_id`,
            concat(`v`.`issue_return_details_transaction_id`, ':Issue Return Details Id:O:N:') as `issue_return_details_transaction_id`,
            concat(`v`.`product_material_unit_id`, ':Material Unit Id:O:N:') as `product_material_unit_id`,
            concat(`v`.`product_material_id`, ':Product Material Id:O:N:') as `product_material_id`,
            concat(`v`.`godown_id`, ':Godown Id:O:N:') as `godown_id`,
            concat(`v`.`godown_section_id`, ':Godown Section Id:O:N:') as `godown_section_id`,
            concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:O:N:') as `godown_section_beans_id`,
            concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
            concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
            concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
            concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
            concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
            concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
            concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
        from
            `stv_indent_material_issue_return_details` `v`
        limit 1;
