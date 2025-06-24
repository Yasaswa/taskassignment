package com.erp.users.service;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;

public interface Userservice {
	CAmModulesFormsUserAccessViewModel login(int company_id, int company_branch_id, String username, String password);
}
