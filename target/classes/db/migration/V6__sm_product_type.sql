UPDATE sm_product_type
SET product_type_name = 'General Stores & Spares'
WHERE product_type_id = 2;

UPDATE sm_product_category1
SET product_type_id = 12
WHERE product_category1_id = 1;

UPDATE am_modules_forms
SET
    modules_forms_name = 'General Stores & Spares',
    display_name = 'General Stores & Spares',
    page_header = 'General Stores & Spares'
WHERE modules_forms_id = 83;