package com.erp.PtGoodsReceiptDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "pt_goods_receipt_master")
public class CPtGoodsReceiptMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goods_receipt_master_transaction_id")
	private int goods_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String supplier_invoice_no;
	private String supplier_invoice_date;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private Integer goods_receipt_type_id;
	private String goods_receipt_type;
	private String purchase_order_life;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private Integer approved_by_id;
	private String approved_date;
	private Integer qa_by_id;
	private String qa_date;
	private String expected_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable;
	private Integer freight_hsn_code_id;
	private double packing_amount;
	private double goods_receipt_discount_percent;
	private double goods_receipt_discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_total;
	private double sgst_total;
	private double igst_total;
	private double grand_total;
	private double roundoff;
	private Integer agent_id;
	private double agent_percent;
	private String agent_paid_status;
	private String goods_receipt_status;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String other_terms_conditions;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_preeclosed = Boolean.FALSE;
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
	private String ev_bill_no;
	private String ev_bill_date;
    private String sales_type;

    public String getSupplier_invoice_no() {
        return supplier_invoice_no;
    }

    public int getGoods_receipt_master_transaction_id() {
		return goods_receipt_master_transaction_id;
	}

	public void setGoods_receipt_master_transaction_id(int goods_receipt_master_transaction_id) {
		this.goods_receipt_master_transaction_id = goods_receipt_master_transaction_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getGoods_receipt_no() {
		return goods_receipt_no;
	}

	public void setGoods_receipt_no(String goods_receipt_no) {
		this.goods_receipt_no = goods_receipt_no;
	}

	public String supplier_invoice_no() {
		return supplier_invoice_no;
	}

	public void setSupplier_invoice_no(String supplier_invoice_no) {
		this.supplier_invoice_no = supplier_invoice_no;
	}

	public String getSupplier_invoice_date() {
		return supplier_invoice_date;
	}

	public void setSupplier_invoice_date(String supplier_invoice_date) {
		if (supplier_invoice_date == null || supplier_invoice_date.isEmpty()) {
			this.supplier_invoice_date = null;
		} else {
			this.supplier_invoice_date = supplier_invoice_date;
		}
	}

	public String getGoods_receipt_date() {
		return goods_receipt_date;
	}

	public void setGoods_receipt_date(String goods_receipt_date) {
		this.goods_receipt_date = goods_receipt_date;
	}

	public Integer getGoods_receipt_version() {
		return goods_receipt_version;
	}

	public void setGoods_receipt_version(Integer goods_receipt_version) {
		this.goods_receipt_version = goods_receipt_version;
	}

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Integer getSupplier_state_id() {
		return supplier_state_id;
	}

	public void setSupplier_state_id(Integer supplier_state_id) {
		this.supplier_state_id = supplier_state_id;
	}

	public Integer getSupplier_city_id() {
		return supplier_city_id;
	}

	public void setSupplier_city_id(Integer supplier_city_id) {
		this.supplier_city_id = supplier_city_id;
	}

	public String getSupplier_contacts_ids() {
		return supplier_contacts_ids;
	}

	public void setSupplier_contacts_ids(String supplier_contacts_ids) {
		this.supplier_contacts_ids = supplier_contacts_ids;
	}

	public Integer getExpected_branch_id() {
		return expected_branch_id;
	}

	public void setExpected_branch_id(Integer expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}

	public Integer getExpected_branch_state_id() {
		return expected_branch_state_id;
	}

	public void setExpected_branch_state_id(Integer expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}

	public Integer getExpected_branch_city_id() {
		return expected_branch_city_id;
	}

	public void setExpected_branch_city_id(Integer expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}

	public String getPurchase_order_no() {
		return purchase_order_no;
	}

	public void setPurchase_order_no(String purchase_order_no) {
		this.purchase_order_no = purchase_order_no;
	}

	public String getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(String purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}

	public Integer getPurchase_order_version() {
		return purchase_order_version;
	}

	public void setPurchase_order_version(Integer purchase_order_version) {
		this.purchase_order_version = purchase_order_version;
	}

	public Integer getGoods_receipt_type_id() {
		return goods_receipt_type_id;
	}

	public void setGoods_receipt_type_id(Integer goods_receipt_type_id) {
		this.goods_receipt_type_id = goods_receipt_type_id;
	}

	public String getGoods_receipt_type() {
		return goods_receipt_type;
	}

	public void setGoods_receipt_type(String goods_receipt_type) {
		this.goods_receipt_type = goods_receipt_type;
	}

	public String getPurchase_order_life() {
		return purchase_order_life;
	}

	public void setPurchase_order_life(String purchase_order_life) {
		this.purchase_order_life = purchase_order_life;
	}

	public String getSupplier_challan_no() {
		return supplier_challan_no;
	}

	public void setSupplier_challan_no(String supplier_challan_no) {
		this.supplier_challan_no = supplier_challan_no;
	}

	public String getSupplier_challan_date() {
		return supplier_challan_date;
	}

	public void setSupplier_challan_date(String supplier_challan_date) {
		this.supplier_challan_date = supplier_challan_date;
	}

	public Integer getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(Integer approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

	public Integer getQa_by_id() {
		return qa_by_id;
	}

	public void setQa_by_id(Integer qa_by_id) {
		this.qa_by_id = qa_by_id;
	}

	public String getQa_date() {
		return qa_date;
	}

	public void setQa_date(String qa_date) {
		this.qa_date = qa_date;
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public double getBasic_total() {
		return basic_total;
	}

	public void setBasic_total(double basic_total) {
		this.basic_total = basic_total;
	}

	public double getTransport_amount() {
		return transport_amount;
	}

	public void setTransport_amount(double transport_amount) {
		this.transport_amount = transport_amount;
	}

	public double getFreight_amount() {
		return freight_amount;
	}

	public void setFreight_amount(double freight_amount) {
		this.freight_amount = freight_amount;
	}

	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}

	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
	}

	public Integer getFreight_hsn_code_id() {
		return freight_hsn_code_id;
	}

	public void setFreight_hsn_code_id(Integer freight_hsn_code_id) {
		this.freight_hsn_code_id = freight_hsn_code_id;
	}

	public double getPacking_amount() {
		return packing_amount;
	}

	public void setPacking_amount(double packing_amount) {
		this.packing_amount = packing_amount;
	}

	public double getGoods_receipt_discount_percent() {
		return goods_receipt_discount_percent;
	}

	public void setGoods_receipt_discount_percent(double goods_receipt_discount_percent) {
		this.goods_receipt_discount_percent = goods_receipt_discount_percent;
	}

	public double getGoods_receipt_discount_amount() {
		return goods_receipt_discount_amount;
	}

	public void setGoods_receipt_discount_amount(double goods_receipt_discount_amount) {
		this.goods_receipt_discount_amount = goods_receipt_discount_amount;
	}

	public double getOther_amount() {
		return other_amount;
	}

	public void setOther_amount(double other_amount) {
		this.other_amount = other_amount;
	}

	public double getTaxable_total() {
		return taxable_total;
	}

	public void setTaxable_total(double taxable_total) {
		this.taxable_total = taxable_total;
	}

	public double getCgst_total() {
		return cgst_total;
	}

	public void setCgst_total(double cgst_total) {
		this.cgst_total = cgst_total;
	}

	public double getSgst_total() {
		return sgst_total;
	}

	public void setSgst_total(double sgst_total) {
		this.sgst_total = sgst_total;
	}

	public double getIgst_total() {
		return igst_total;
	}

	public void setIgst_total(double igst_total) {
		this.igst_total = igst_total;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public double getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(double agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getAgent_paid_status() {
		return agent_paid_status;
	}

	public void setAgent_paid_status(String agent_paid_status) {
		this.agent_paid_status = agent_paid_status;
	}

	public String getGoods_receipt_status() {
		return goods_receipt_status;
	}

	public void setGoods_receipt_status(String goods_receipt_status) {
		this.goods_receipt_status = goods_receipt_status;
	}

	public String getLr_no() {
		return lr_no;
	}

	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	public String getLr_date() {
		return lr_date;
	}

	public void setLr_date(String lr_date) {
		if (lr_date == null || lr_date.isEmpty()) {
			this.lr_date = null;
		} else {
			this.lr_date = lr_date;
		}
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getOther_terms_conditions() {
		return other_terms_conditions;
	}

	public void setOther_terms_conditions(String other_terms_conditions) {
		this.other_terms_conditions = other_terms_conditions;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public boolean isIs_preeclosed() {
		return is_preeclosed;
	}

	public void setIs_preeclosed(boolean is_preeclosed) {
		this.is_preeclosed = is_preeclosed;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}


	public String getEv_bill_no() {
		return ev_bill_no;
	}

	public void setEv_bill_no(String ev_bill_no) {
		this.ev_bill_no = ev_bill_no;
	}

	public String getEv_bill_date() {
		return ev_bill_date;
	}

	public void setEv_bill_date(String ev_bill_date) {
		if (ev_bill_date == null || ev_bill_date.isEmpty()) {
			this.ev_bill_date = null;
		} else {
			this.ev_bill_date = ev_bill_date;
		}
	}

	public CPtGoodsReceiptMasterModel(int goods_receipt_master_transaction_id, Integer company_id,
									  Integer company_branch_id, String financial_year, String goods_receipt_no, String goods_receipt_date,
									  Integer goods_receipt_version, String supplier_invoice_no, String supplier_invoice_date,
									  Integer supplier_id, Integer supplier_state_id, Integer supplier_city_id, String supplier_contacts_ids,
									  Integer expected_branch_id, Integer expected_branch_state_id, Integer expected_branch_city_id,
									  String purchase_order_no, String purchase_order_date, Integer purchase_order_version,
									  Integer goods_receipt_type_id, String goods_receipt_type, String purchase_order_life,
									  String supplier_challan_no, String supplier_challan_date, Integer approved_by_id, String approved_date,
									  Integer qa_by_id, String qa_date, String expected_schedule_date, double basic_total,
									  double transport_amount, double freight_amount, boolean is_freight_taxable, Integer freight_hsn_code_id,
									  double packing_amount, double goods_receipt_discount_percent, double goods_receipt_discount_amount,
									  double other_amount, double taxable_total, double cgst_total, double sgst_total, double igst_total,
									  double grand_total, double roundoff, Integer agent_id, double agent_percent, String agent_paid_status,
									  String goods_receipt_status, String lr_no, String lr_date, String vehicle_no, String other_terms_conditions,
									  String remark, boolean is_active, boolean is_delete, boolean is_preeclosed, String created_by,
									  Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on, String ev_bill_no, String ev_bill_date, String sales_type) {
		super();
		this.goods_receipt_master_transaction_id = goods_receipt_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.goods_receipt_no = goods_receipt_no;
		this.goods_receipt_date = goods_receipt_date;
		this.goods_receipt_version = goods_receipt_version;
		this.supplier_invoice_no = supplier_invoice_no;
		this.supplier_invoice_date = supplier_invoice_date;
		this.supplier_id = supplier_id;
		this.supplier_state_id = supplier_state_id;
		this.supplier_city_id = supplier_city_id;
		this.supplier_contacts_ids = supplier_contacts_ids;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.goods_receipt_type_id = goods_receipt_type_id;
		this.goods_receipt_type = goods_receipt_type;
		this.purchase_order_life = purchase_order_life;
		this.supplier_challan_no = supplier_challan_no;
		this.supplier_challan_date = supplier_challan_date;
		this.approved_by_id = approved_by_id;
		this.approved_date = approved_date;
		this.qa_by_id = qa_by_id;
		this.qa_date = qa_date;
		this.expected_schedule_date = expected_schedule_date;
		this.basic_total = basic_total;
		this.transport_amount = transport_amount;
		this.freight_amount = freight_amount;
		this.is_freight_taxable = is_freight_taxable;
		this.freight_hsn_code_id = freight_hsn_code_id;
		this.packing_amount = packing_amount;
		this.goods_receipt_discount_percent = goods_receipt_discount_percent;
		this.goods_receipt_discount_amount = goods_receipt_discount_amount;
		this.other_amount = other_amount;
		this.taxable_total = taxable_total;
		this.cgst_total = cgst_total;
		this.sgst_total = sgst_total;
		this.igst_total = igst_total;
		this.grand_total = grand_total;
		this.roundoff = roundoff;
		this.agent_id = agent_id;
		this.agent_percent = agent_percent;
		this.agent_paid_status = agent_paid_status;
		this.goods_receipt_status = goods_receipt_status;
		this.lr_no = lr_no;
		this.lr_date = lr_date;
		this.vehicle_no = vehicle_no;
		this.other_terms_conditions = other_terms_conditions;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.is_preeclosed = is_preeclosed;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.ev_bill_no = ev_bill_no;
		this.ev_bill_date = ev_bill_date;
        this.sales_type = sales_type;
	}

	public CPtGoodsReceiptMasterModel() {
		super();
	}

}
