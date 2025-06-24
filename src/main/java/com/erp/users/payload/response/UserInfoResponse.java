/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.payload.response;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesModel;
import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesViewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

//@Data
@NoArgsConstructor
public class UserInfoResponse {

	private String user_code;
	private Integer user_id;
	private int company_id;
	private int department_id;
	private int company_branch_id;
	private String username;
	private String user;
	private String user_type;
	private String company_name;
	private String company_branch_name;
	private String company_branch_address;
	private String department_name;
	private String branch_short_name;
	private List<Map<String, Object>> pageRoutes;
	private List<CAmModulesFormsUserAccessViewModel> userAccessDetails;
	private List<CAmListingFiltersPreferancesViewModel> listingFilterPreferenceDetails;

	private String company_access;

	public UserInfoResponse(String user_code, Integer user_id, int company_id, int company_branch_id,int department_id, String username,
			String user, String user_type, String company_name, String company_branch_name, String department_name,
			String company_branch_address, String branch_short_name, List<Map<String, Object>> pageRoutes,List<CAmModulesFormsUserAccessViewModel> userAccessDetails,
			List<CAmListingFiltersPreferancesViewModel> listingFilterPreferenceDetails, String company_access) {
		super();
		this.user_code = user_code;
		this.user_id = user_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.department_id = department_id;
		this.username = username;
		this.user = user;
		this.user_type = user_type;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.company_branch_address = company_branch_address;
		this.department_name = department_name;
		this.branch_short_name = branch_short_name;
		this.pageRoutes = pageRoutes;
		this.userAccessDetails = userAccessDetails;
		this.listingFilterPreferenceDetails = listingFilterPreferenceDetails;
		this.company_access = company_access;
	}

	public String getBranch_short_name() {
		return branch_short_name;
	}

	public void setBranch_short_name(String branch_short_name) {
		this.branch_short_name = branch_short_name;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(int company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getCompany_branch_address() {
		return company_branch_address;
	}

	public void setCompany_branch_address(String company_branch_address) {
		this.company_branch_address = company_branch_address;
	}
	public String getDepartment_name() { return department_name; }

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public List<Map<String, Object>> getPageRoutes() {
		return pageRoutes;
	}

	public void setPageRoutes(List<Map<String, Object>> pageRoutes) {
		this.pageRoutes = pageRoutes;
	}

	public List<CAmModulesFormsUserAccessViewModel> getUserAccessDetails() {
		return userAccessDetails;
	}

	public void setUserAccessDetails(List<CAmModulesFormsUserAccessViewModel> userAccessDetails) {
		this.userAccessDetails = userAccessDetails;
	}

	public List<CAmListingFiltersPreferancesViewModel> getListingFilterPreferenceDetails() {
		return listingFilterPreferenceDetails;
	}

	public void setListingFilterPreferenceDetails(List<CAmListingFiltersPreferancesViewModel> listingFilterPreferenceDetails) {
		this.listingFilterPreferenceDetails = listingFilterPreferenceDetails;
	}

	public String getCompany_access() {
		return company_access;
	}

	public void setCompany_access(String company_access) {
		this.company_access = company_access;
	}
}

