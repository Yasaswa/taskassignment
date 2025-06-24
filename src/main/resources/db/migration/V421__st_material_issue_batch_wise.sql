ALTER TABLE st_material_issue_batch_wise ADD issue_return_status varchar(2) DEFAULT 'P' NULL;
ALTER TABLE st_material_issue_batch_wise ADD return_quantity decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE st_material_issue_batch_wise ADD return_weight bigint(20) DEFAULT 0 NULL;
ALTER TABLE st_material_issue_batch_wise ADD return_boxes decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE st_material_issue_batch_wise ADD receipt_quantity decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE st_material_issue_batch_wise ADD receipt_weight decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE st_material_issue_batch_wise ADD receipt_boxes bigint(20) DEFAULT 0 NULL;


INSERT INTO st_material_issue_batch_wise (
    company_id,
    company_branch_id,
    financial_year,
    transaction_no,
    transaction_date,
    goods_receipt_no,
    batch_no,
    indent_no,
    supplier_id,
    product_material_id,
    cone_per_wt,
    requisition_no_boxes,
    requisition_quantity,
    requisition_weight,
    issue_no_boxes,
    issue_quantity,
    issue_weight,
    return_boxes,
    return_quantity,
    return_weight,
    receipt_boxes,
    receipt_quantity,
    receipt_weight,
    product_material_unit_id,
    godown_id,
    godown_section_id,
    godown_section_beans_id,
    issue_status,
    issue_return_status,
    department_id,
    sub_department_id,
    issue_requisition_type,
    remark,
    created_by,
    created_on
)
SELECT
    d.company_id,
    d.company_branch_id,
    d.financial_year,
    COALESCE(m.issue_return_no, '') AS transaction_no,
    m.issue_return_date AS transaction_date,
    d.goods_receipt_no,
    d.issue_batch_no AS batch_no,
    '' as indent_no,
    COALESCE(d.supplier_id, 0) AS supplier_id, -- Handle NULL supplier_id
    d.product_material_id,
    d.cone_per_wt,
    0 AS requisition_no_boxes,
    0 AS requisition_quantity,
    0 AS requisition_weight,
    0 AS issue_no_boxes,
    0 AS issue_quantity,
    0 AS issue_weight,
    d.product_material_receipt_boxes AS return_boxes,
    d.product_material_receipt_quantity AS return_quantity,
    d.product_material_receipt_weight AS return_weight,
    d.product_material_receipt_boxes AS receipt_boxes,
    d.product_material_receipt_quantity AS receipt_quantity,
    d.product_material_receipt_weight AS receipt_weight,
    d.product_material_unit_id,
    CAST(d.godown_id AS UNSIGNED),
    CAST(d.godown_section_id AS UNSIGNED),
    CAST(d.godown_section_beans_id AS UNSIGNED),
    'MI' AS issue_status,
    d.issue_return_item_status AS issue_return_status,
    COALESCE(m.department_id, 0),
    COALESCE(m.sub_department_id, 0),
    '' as issue_requisition_type,
    d.issue_return_remark,
    d.created_by,
    d.created_on
FROM st_indent_material_issue_return_details d
JOIN st_indent_material_issue_return_master m
    ON d.issue_return_master_transaction_id = m.issue_return_master_transaction_id

WHERE
    d.is_delete = 0
    AND d.issue_return_item_status IN ('R')
    AND m.indent_issue_return_type_id = 12;


   INSERT INTO st_material_issue_batch_wise (
       company_id,
       company_branch_id,
       financial_year,
       transaction_no,
       transaction_date,
       set_no,
       goods_receipt_no,
       batch_no,
       indent_no,
       supplier_id,
       product_material_id,
       cone_per_wt,
       requisition_no_boxes,
       requisition_quantity,
       requisition_weight,
       issue_no_boxes,
       issue_quantity,
       issue_weight,
       product_material_unit_id,
       godown_id,
       godown_section_id,
       godown_section_beans_id,
       issue_status,
       issue_return_status,
       department_id,
       sub_department_id,
       issue_requisition_type,
       remark,
       created_by,
       created_on
   )
   SELECT
       d.company_id,
       d.company_branch_id,
       d.financial_year,
       d.issue_no AS transaction_no,
       d.issue_date AS transaction_date,
       d.set_no,
       d.goods_receipt_no,
       d.issue_batch_no AS batch_no,
       d.indent_no,
       0 AS supplier_id,
       d.product_material_id,
       d.cone_per_wt,
       d.product_material_issue_boxes AS requisition_no_boxes,
       d.product_material_issue_quantity AS requisition_quantity,
       d.product_material_issue_weight AS requisition_weight,
       d.product_material_issue_boxes AS issue_no_boxes,
       d.product_material_issue_quantity AS issue_quantity,
       d.product_material_issue_weight AS issue_weight,
       d.product_material_unit_id,
       CAST(d.godown_id AS UNSIGNED),
       CAST(d.godown_section_id AS UNSIGNED),
       CAST(d.godown_section_beans_id AS UNSIGNED),
       d.issue_item_status as issue_status,
       'P' AS issue_return_status,
       COALESCE(m.department_id, 0),
       COALESCE(m.sub_department_id, 0),
       d.issue_requisition_type,
       d.issue_remark,
       d.created_by,
       d.created_on
   FROM st_indent_material_issue_details d
   JOIN st_indent_material_issue_master m
       ON d.issue_master_transaction_id = m.issue_master_transaction_id
   WHERE
       d.is_delete = 0
       AND d.issue_item_status IN ('MI', 'I')
       AND m.indent_issue_type_id = 12;