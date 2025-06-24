alter table st_indent_material_issue_details
add column `product_material_issue_boxes` decimal(18,4) DEFAULT 0.0000;

alter table st_indent_material_issue_master
add column `set_no` varchar(50) DEFAULT null;