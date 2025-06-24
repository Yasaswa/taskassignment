ALTER TABLE hm_hrpayroll_settings ADD short_leave_hours decimal(18, 2) DEFAULT 0.0000 NULL;
UPDATE hm_hrpayroll_settings SET company_id=1, pf_limit=15000.0000, is_delete=0, attendance_allowance_days=25.00, night_allowance_days=26.0000, worker_minimum_wages=580.0000, short_leave_hours=2.00 WHERE hrpayroll_settings_id=1;
UPDATE hm_hrpayroll_settings SET company_id=2, pf_limit=15000.0000, is_delete=0, attendance_allowance_days=25.00, night_allowance_days=26.0000, worker_minimum_wages=580.0000, short_leave_hours=2.00 WHERE hrpayroll_settings_id=2;
UPDATE hm_hrpayroll_settings SET company_id=3, pf_limit=15000.0000, is_delete=0, attendance_allowance_days=25.00, night_allowance_days=26.0000, worker_minimum_wages=580.0000, short_leave_hours=2.00 WHERE hrpayroll_settings_id=3;
