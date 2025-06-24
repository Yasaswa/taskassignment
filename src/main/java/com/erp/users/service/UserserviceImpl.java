package com.erp.users.service;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessViewRepository;
import com.erp.Employee.Employees.Repository.IEmployeesRepository;
import com.erp.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements Userservice {

	@Autowired
	IEmployeesRepository iEmployeesRepository;

	@Autowired
	IAmModulesFormsUserAccessViewRepository iAmModulesFormsUserAccessViewRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public CAmModulesFormsUserAccessViewModel login(int company_id, int company_branch_id, String username, String password) {
		return iAmModulesFormsUserAccessViewRepository.login(company_id, company_branch_id, username, password);
	}


//	@Override
//	public boolean employeeIsExist(int company_id, int company_branch_id, String username, String password) {
//		CEmployeesModel checkUserIsExist = iEmployeesRepository.checkUserIsExist(company_id,company_branch_id,username, password);
//		
//		if (checkUserIsExist != null) {
//	        System.out.println("Employee name: " + checkUserIsExist.getEmployee_name());
//	        return true; // Employee exists
//	    } else {
//	        return false; // Employee does not exist
//	    }
//	}


//	@Override
//	public boolean modulesuseraccessIsExist(int company_id, int company_branch_id, String username, String password) {
//		CAmModulesFormsUserAccessViewModel modulesuseraccessIsExist = iAmModulesFormsUserAccessRepository.modulesuseraccessIsExist(company_id,company_branch_id,username, password);
//		
//		if (modulesuseraccessIsExist != null) {
//	     //   System.out.println("Employee name: " + modulesuseraccessIsExist.getEmployee_name());
//	        return true; // Employee exists
//	    } else {
//	        return false; // Employee does not exist
//	    }
//	}


}