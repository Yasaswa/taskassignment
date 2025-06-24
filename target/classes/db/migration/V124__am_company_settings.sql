


ALTER TABLE am_company_settings ADD from_email_username varchar(100) NULL COMMENT 'from username';
ALTER TABLE am_company_settings CHANGE from_email_username from_email_username varchar(100) NULL COMMENT 'from username' AFTER from_email_id;



update am_company_settings set from_email_id = "admin@proerpsolution.com", from_email_username = "066cedb9543568fbc3c43b88878e3a05",
from_email_password = "87666c274f47d7d008f96a4bd0792bb7" , smtp_host_name = "in-v3.mailjet.com", smtp_port = 587 where is_delete = 0;