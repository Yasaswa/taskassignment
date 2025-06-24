package com.erp.Common.GenerateAutoNo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CProdTransactionNo {
	public String entity_name;
	@Transient
	public String sub_section;
	public String sub_section_short_name;
	public String field_name;
	public String transaction_date;
	public String production_code;
	public String trans_no_length;
	public String company_id;
}
