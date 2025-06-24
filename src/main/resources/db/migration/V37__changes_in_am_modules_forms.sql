UPDATE am_modules_forms SET
    modules_forms_name = 'Attendance Register',display_name = 'Attendance Register', page_header = 'Attendance Register'
WHERE modules_forms_id = 620;

UPDATE am_modules_forms SET is_delete = 1
WHERE modules_forms_id = 637;


UPDATE am_modules_forms_user_access SET is_delete = 1
where modules_forms_id =637;