package com.erp.PtGoodsReceiptMasterCustomer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from ptv_goods_receipt_customer_summary")
public class CPtGoodsReceiptCustomerSummaryViewModel {

	@Id
	private int customer_goods_receipt_master_transaction_id;
	private String customer_name;
	private String customer_goods_receipt_no;
	private String customer_goods_receipt_date;
	private Integer customer_goods_receipt_version;
	private String customer_goods_receipt_status_desc;
	private String customer_order_no;
	private String customer_order_date;
	private String customer_goods_receipt_type;
	private String customer_challan_no;
	private String customer_challan_Date;
	private String ewb_no;
	private String ewb_valid_till;
	private String approved_by_name;
	private String approved_date;
	private Integer qa_by_id;
	private String qa_date;
	private String customer_goods_receipt_status;
	private Double grand_total;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String remark;
	private String customer_state_name;
	private String customer_city_name;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer approved_by_id;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer customer_goods_receipt_type_id;
	private Integer field_id;
	private String field_name;


}
