package com.erp.RawMaterial.Product_Rm_Process.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from smv_product_rm_process")
public class CProductRmProcessViewModel {
	@Id
	private int product_process_id;
	private String product_rm_id;
	private String product_process_name;
	private String product_parent_process_name;
	private String product_process_std_scrap_percent;
	private String product_process_std_production_hrs;
	private Integer product_parent_process_id;
	private Integer field_id;
	private String field_name;


}
