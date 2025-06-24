package com.erp.RawMaterial.Product_Rm_Supplier.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from smv_product_rm_supplier")
public class CProductRmSupplierViewModel {
	@Id
	private Integer product_rm_supplier_id;
	private Integer supplier_id;
	private String supplier_name;
	private String supplier_code;
	private String supplier_payment_terms;
	private String supp_branch_rating;
	private String city_name;
	private String state_name;
	private String supp_branch_phone_no;
	private String supp_branch_cell_no;
	private String supp_branch_EmailId;
	private String supp_branch_website;
	private String supp_branch_gst_no;
	private Integer company_id;
	private String product_rm_id;
	private Integer field_id;
	private String field_name;

}
