package com.erp.PtSupplierBillBookingMaster.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("select * from ftv_purchase_bill_tax_summary")
public class CFtPurchaseBillTaxSummaryViewModel {


	@Id
	private int purchase_bill_tax_summary_transaction_id;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private String supplier_name;
	private String supplier_gst_no;
	private String suplier_city_name;
	private String suplier_state_name;
	private String hsn_sac_code;
	private double hsn_sac_rate;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer hsn_code_id;
	private Integer supplier_id;
	private Integer purchase_bill_type_id;
	private String purchase_bill_type;
	private Integer supplier_bill_booking_master_transaction_id;
	private String supplier_contacts_ids;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;

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
