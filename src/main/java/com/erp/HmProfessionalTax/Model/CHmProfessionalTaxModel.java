package com.erp.HmProfessionalTax.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hm_professional_tax")
public class CHmProfessionalTaxModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "professional_tax_id")
	private int professional_tax_id;
	private Integer company_id;
	private String applicable_date;
	@Column(name = "gender")
	private String gender;
	private double lower_limit;
	private double upper_limit;
	private double professional_tax;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public String getApplicable_date() {
		return applicable_date;
	}

	public void setApplicable_date(String applicable_date) {
		if (applicable_date == null || applicable_date.isEmpty()) {
			this.applicable_date = null;
		} else {
			this.applicable_date = applicable_date;
		}
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

}
