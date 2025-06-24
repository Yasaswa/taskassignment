ALTER TABLE hm_hrpayroll_settings ADD lock_date varchar(100) NULL;
ALTER TABLE hm_hrpayroll_settings ADD lock_status varchar(100) NULL;
ALTER TABLE hm_hrpayroll_settings MODIFY COLUMN lock_status bit(1) DEFAULT b'1' NULL;
ALTER TABLE hm_hrpayroll_settings MODIFY COLUMN lock_status bit(1) DEFAULT b'0' NULL;