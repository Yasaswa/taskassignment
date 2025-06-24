
UPDATE am_modules_forms
SET
    modules_forms_name = 'Monthly Attendance Register',
    display_name = 'Monthly Attendance Register',
    page_header = 'Monthly Attendance Register'
WHERE modules_forms_id = 618;

UPDATE am_modules_forms
SET is_delete = 1
WHERE modules_forms_id IN (642, 643);


UPDATE am_modules_forms_user_access 
SET is_delete = 1
WHERE modules_forms_id IN (642, 643);