
-- For st_indent_master
UPDATE st_indent_master
SET Indent_type = 'General Stores & Spares'
WHERE Indent_type = 'Raw Material';

-- For st_indent_material_issue_master
UPDATE st_indent_material_issue_master
SET indent_issue_type = 'General Stores & Spares'
WHERE indent_issue_type = 'Raw Material';

-- For pt_purchase_order_master
UPDATE pt_purchase_order_master
SET purchase_order_type = 'General Stores & Spares'
WHERE purchase_order_type = 'Raw Material';

-- For pt_purchase_order_details
UPDATE pt_purchase_order_details
SET purchase_order_type = 'General Stores & Spares'
WHERE purchase_order_type = 'Raw Material';

-- For pt_purchase_order_schedules
UPDATE pt_purchase_order_schedules
SET product_type = 'General Stores & Spares'
WHERE product_type = 'Raw Material';

-- For pt_purchase_order_tax_summary
UPDATE pt_purchase_order_tax_summary
SET purchase_order_type = 'General Stores & Spares'
WHERE purchase_order_type = 'Raw Material';

-- For pt_goods_receipt_master
UPDATE pt_goods_receipt_master
SET goods_receipt_type = 'General Stores & Spares'
WHERE goods_receipt_type = 'Raw Material';

-- For pt_goods_receipt_details
UPDATE pt_goods_receipt_details
SET goods_receipt_type = 'General Stores & Spares'
WHERE goods_receipt_type = 'Raw Material';
