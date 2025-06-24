-- erp_dev_temp.stv_indent_material_issue_return_details source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
select
    `st`.`issue_return_details_transaction_id` as `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` as `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` as `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` as `issue_return_item_status`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`issue_return_remark` as `issue_return_remark`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `is_active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`
from
    (`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
where
    `st`.`is_delete` = 0;



CREATE OR REPLACE VIEW stv_indent_material_issue_details AS
SELECT
    st.issue_no,
    st.issue_date,
    st.issue_version,
    v.department_name,
    v.sub_department_name,
    CASE st.issue_item_status 
        WHEN 'MI' THEN 'Material Issue'
        WHEN 'C' THEN 'Completed'
        WHEN 'I' THEN 'Partial Issue'
        WHEN 'AC' THEN 'Accepted'
        ELSE 'Pending'
    END AS issue_item_status_desc,
    e.employee_name AS indented_by_name,
    v.customer_name,
    v.expected_schedule_date,
    st.customer_order_no,
    rmfgsr.product_type_group,
    st.product_material_id,
    rmfgsr.product_material_name,
    rmfgsr.actual_count,
    st.product_material_indent_quantity,
    st.product_material_indent_weight,
    st.product_material_issue_quantity,
    st.product_material_issue_weight,
    st.product_material_issue_boxes,
    st.product_material_receipt_quantity,
    st.product_material_receipt_weight,
    rmfgsr.product_material_name AS product_rm_name,
    rmfgsr.product_material_drawing_no AS product_rm_drawing_no,
    rmfgsr.product_material_tech_spect AS product_rm_tech_spect,
    st.product_material_issue_return_quantity,
    rmfgsr.product_type_name,
    rmfgsr.product_material_make_name,
    rmfgsr.product_material_type_name,
    `rmfgsr`.`product_material_stock_unit_name` AS `product_material_unit_name`,
    rmfgsr.lead_time AS product_lead_time,
    ps.product_material_stock_quantity,
    ps.product_material_stock_weight,
    ps.product_Rawmaterial_stock_quantity,
    ps.product_Rawmaterial_stock_weight,
    ps.reserve_quantity,
    ps.reserve_weight,
    sd.closing_balance_quantity,
    sd.closing_balance_weight,
    sd.weight_per_box_item AS cone_per_wt,
    st.product_material_approved_quantity,
    st.product_material_approved_weight,
    st.product_material_rejected_quantity,
    st.product_material_rejected_weight,
    st.product_material_issue_return_weight,
    v.customer_state_name,
    v.cust_branch_gst_no,
    st.product_std_weight,
    st.indent_no,
    st.indent_date,
    st.indent_version,
    st.issue_item_status,
    st.issue_batch_no,
    st.material_batch_rate,
    st.product_material_issue_quantity * st.material_batch_rate AS material_issue_amount,
    fmp.profit_center_name,
    fm.cost_center_name,
    st.routing_code,
    v.company_name,
    v.company_branch_name,
    st.financial_year,
    st.issue_remark,
    gd.godown_name,
    gds.godown_section_name,
    gdsb.godown_section_beans_name,
    st.godown_id,
    st.godown_section_id,
    st.godown_section_beans_id,
    st.cost_center_id,
    st.goods_receipt_no,
    st.profit_center_id,
    CASE WHEN st.is_active = 1 THEN 'Active' ELSE 'Inactive' END AS Active,
    CASE WHEN st.is_delete = 1 THEN 'Yes' ELSE 'No' END AS Deleted,
    st.is_active,
    st.is_delete,
    st.created_by,
    st.created_on,
    st.modified_by,
    st.modified_on,
    st.deleted_by,
    st.deleted_on,
    v.customer_id,
    v.department_id,
    st.indented_by_id,
    v.issued_by_id,
    v.company_id,
    v.company_branch_id,
    v.indent_issue_type_id AS product_type_id,
    rmfgsr.product_material_type_id,
    rmfgsr.product_material_grade_id,
    rmfgsr.product_material_packing_id,
    st.product_material_unit_id,
    st.issue_details_transaction_id,
    st.issue_master_transaction_id,
    st.indent_details_id,
    v.sub_department_id,
    v.issue_no AS field_name,
    v.issue_version AS field_id
FROM
    st_indent_material_issue_details st
JOIN
    stv_indent_material_issue_summary v 
ON  v.company_branch_id = st.company_branch_id
    AND v.company_id = st.company_id
    AND v.issue_master_transaction_id = st.issue_master_transaction_id
    AND v.is_delete = 0
LEFT JOIN
    smv_product_rm_fg_sr rmfgsr 
ON  rmfgsr.product_material_id = st.product_material_id
LEFT JOIN
    cm_employee e 
ON  e.employee_id = st.indented_by_id
    AND e.is_delete = 0
LEFT JOIN
    cm_godown gd 
ON  gd.godown_id = st.godown_id
    AND gd.is_delete = 0
LEFT JOIN
    cm_godown_section gds 
ON  gds.godown_section_id = st.godown_section_id
    AND gds.is_delete = 0
LEFT JOIN
    cm_godown_section_beans gdsb 
ON  gdsb.godown_section_beans_id = st.godown_section_beans_id
    AND gdsb.is_delete = 0
LEFT JOIN
    fm_cost_center fm 
ON  fm.cost_center_id = st.cost_center_id
    AND fm.is_delete = 0
LEFT JOIN
    fm_profit_center fmp 
ON  fmp.profit_center_id = st.profit_center_id
    AND fmp.is_delete = 0
LEFT JOIN
    (SELECT
        product_rm_id,
        godown_id,
        company_id,
        SUM(closing_balance_quantity) AS product_material_stock_quantity,
        SUM(closing_balance_weight) AS product_material_stock_weight,
        SUM(closing_balance_quantity) AS product_Rawmaterial_stock_quantity,
        SUM(closing_balance_weight) AS product_Rawmaterial_stock_weight,
        SUM(reserve_quantity) AS reserve_quantity,
        SUM(reserve_weight) AS reserve_weight
     FROM
        sm_product_rm_stock_summary
     GROUP BY
        product_rm_id, godown_id, company_id
    ) ps
ON  ps.product_rm_id = st.product_material_id
    AND ps.godown_id = st.godown_id
    AND ps.company_id = st.company_id
LEFT JOIN
    (SELECT
        goods_receipt_no,
        batch_no,
        day_closed,
        closing_balance_quantity,
        closing_balance_weight,
        weight_per_box_item
     FROM
        sm_product_rm_stock_details
     WHERE day_closed = 0
    ) sd
ON  sd.goods_receipt_no = st.goods_receipt_no
    AND sd.batch_no = st.issue_batch_no
WHERE
    st.is_delete = 0;
    
   
 create index idx_product_rm_id on
sm_product_rm_stock_summary (product_rm_id);

create index idx_product_rm_id on
sm_product_rm_stock_details (product_rm_id);

alter table sm_product_rm_stock_details modify goods_receipt_no VARCHAR(255);



create index idx_goods_receipt_no on
sm_product_rm_stock_details (goods_receipt_no);


 create or replace
        algorithm = UNDEFINED view `stv_indent_material_issue_return_details_rpt` as
        select
        	concat(`v`.`issue_return_no`, ':Issue Return No:O:N:') as `issue_return_no`,
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
