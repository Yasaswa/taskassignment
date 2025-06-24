ALTER TABLE hm_hrpayroll_settings ADD night_allowance_days decimal(18,4) NULL;
ALTER TABLE hm_hrpayroll_settings CHANGE night_allowance_days night_allowance_days decimal(18,4) NULL AFTER attendance_allowance_days;

INSERT INTO hm_hrpayroll_settings(night_allowance_days) VALUES (26);