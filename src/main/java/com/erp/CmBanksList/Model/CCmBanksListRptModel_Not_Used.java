package com.erp.CmBanksList.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cmv_banks_List_rpt")
public class CCmBanksListRptModel_Not_Used {

	@Id
	private String bank_id;
	private String bank_name;
	private String bank_short_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String field_id;
	private String field_name;

}
