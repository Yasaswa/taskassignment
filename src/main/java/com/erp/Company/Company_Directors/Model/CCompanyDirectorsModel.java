package com.erp.Company.Company_Directors.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_company_directors")
public class CCompanyDirectorsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_director_id")
	private int company_director_id;
	private Integer company_id;
	private String company_director_name;
	private String director_address;
	private String director_qualification;
	private String director_cell_no1;
	private String director_cell_no2;
	private String director_phone_no;
	private String director_EmailId1;
	private String director_EmailId2;
	private String director_website;
	private String director_facebook_profile;
	private String director_linkedin_profile;
	private String director_twitter_profile;
	private String director_pan_no;
	private String director_adhar_no;
	private String director_din_no;
	private String rolesResposibilties;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

	public int getCompany_director_id() {
		return company_director_id;
	}

	public void setCompany_director_id(int company_director_id) {
		this.company_director_id = company_director_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getCompany_director_name() {
		return company_director_name;
	}

	public void setCompany_director_name(String company_director_name) {
		this.company_director_name = company_director_name;
	}

	public String getDirector_address() {
		return director_address;
	}

	public void setDirector_address(String director_address) {
		this.director_address = director_address;
	}

	public String getDirector_qualification() {
		return director_qualification;
	}

	public void setDirector_qualification(String director_qualification) {
		this.director_qualification = director_qualification;
	}

	public String getDirector_cell_no1() {
		return director_cell_no1;
	}

	public void setDirector_cell_no1(String director_cell_no1) {
		this.director_cell_no1 = director_cell_no1;
	}

	public String getDirector_cell_no2() {
		return director_cell_no2;
	}

	public void setDirector_cell_no2(String director_cell_no2) {
		this.director_cell_no2 = director_cell_no2;
	}

	public String getDirector_phone_no() {
		return director_phone_no;
	}

	public void setDirector_phone_no(String director_phone_no) {
		this.director_phone_no = director_phone_no;
	}

	public String getDirector_EmailId1() {
		return director_EmailId1;
	}

	public void setDirector_EmailId1(String director_EmailId1) {
		this.director_EmailId1 = director_EmailId1;
	}

	public String getDirector_EmailId2() {
		return director_EmailId2;
	}

	public void setDirector_EmailId2(String director_EmailId2) {
		this.director_EmailId2 = director_EmailId2;
	}

	public String getDirector_website() {
		return director_website;
	}

	public void setDirector_website(String director_website) {
		this.director_website = director_website;
	}

	public String getDirector_facebook_profile() {
		return director_facebook_profile;
	}

	public void setDirector_facebook_profile(String director_facebook_profile) {
		this.director_facebook_profile = director_facebook_profile;
	}

	public String getDirector_linkedin_profile() {
		return director_linkedin_profile;
	}

	public void setDirector_linkedin_profile(String director_linkedin_profile) {
		this.director_linkedin_profile = director_linkedin_profile;
	}

	public String getDirector_twitter_profile() {
		return director_twitter_profile;
	}

	public void setDirector_twitter_profile(String director_twitter_profile) {
		this.director_twitter_profile = director_twitter_profile;
	}

	public String getDirector_pan_no() {
		return director_pan_no;
	}

	public void setDirector_pan_no(String director_pan_no) {
		this.director_pan_no = director_pan_no;
	}

	public String getDirector_adhar_no() {
		return director_adhar_no;
	}

	public void setDirector_adhar_no(String director_adhar_no) {
		this.director_adhar_no = director_adhar_no;
	}

	public String getDirector_din_no() {
		return director_din_no;
	}

	public void setDirector_din_no(String director_din_no) {
		this.director_din_no = director_din_no;
	}

	public String getRolesResposibilties() {
		return rolesResposibilties;
	}

	public void setRolesResposibilties(String rolesResposibilties) {
		this.rolesResposibilties = rolesResposibilties;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}


	public CCompanyDirectorsModel(int company_director_id, Integer company_id, String company_director_name,
	                              String director_address, String director_qualification, String director_cell_no1, String director_cell_no2,
	                              String director_phone_no, String director_EmailId1, String director_EmailId2, String director_website,
	                              String director_facebook_profile, String director_linkedin_profile, String director_twitter_profile,
	                              String director_pan_no, String director_adhar_no, String director_din_no, String rolesResposibilties,
	                              boolean is_delete, boolean is_active, Date created_on, Date modified_on, Date deleted_on, String created_by,
	                              String modified_by, String deleted_by) {
		super();
		this.company_director_id = company_director_id;
		this.company_id = company_id;
		this.company_director_name = company_director_name;
		this.director_address = director_address;
		this.director_qualification = director_qualification;
		this.director_cell_no1 = director_cell_no1;
		this.director_cell_no2 = director_cell_no2;
		this.director_phone_no = director_phone_no;
		this.director_EmailId1 = director_EmailId1;
		this.director_EmailId2 = director_EmailId2;
		this.director_website = director_website;
		this.director_facebook_profile = director_facebook_profile;
		this.director_linkedin_profile = director_linkedin_profile;
		this.director_twitter_profile = director_twitter_profile;
		this.director_pan_no = director_pan_no;
		this.director_adhar_no = director_adhar_no;
		this.director_din_no = director_din_no;
		this.rolesResposibilties = rolesResposibilties;
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
	}

	public CCompanyDirectorsModel() {
		super();
	}

}
