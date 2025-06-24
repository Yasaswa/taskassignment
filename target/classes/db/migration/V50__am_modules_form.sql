update am_modules_forms
 set is_delete = 1
 where modules_forms_id = 134;


UPDATE am_modules_forms_user_access
SET is_delete = 1
WHERE modules_forms_id = 134;