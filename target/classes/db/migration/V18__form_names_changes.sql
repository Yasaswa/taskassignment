UPDATE am_modules_forms
SET url_parameter = "RM",display_name ="Purch. Indent (General Stores & Spares)"
WHERE modules_forms_id = 103;


UPDATE am_modules_forms
SET url_parameter = "PRM",display_sequence =2,is_delete =0
WHERE modules_forms_id = 154;

UPDATE am_modules_forms
SET url_parameter = "PRM",display_sequence =2,is_delete =0,
modules_forms_name="Purch. Indent (Raw Materials)",
display_name="Purch. Indent (Raw Materials)",page_header="Purch. Indent (Raw Materials)"
WHERE modules_forms_id = 154;