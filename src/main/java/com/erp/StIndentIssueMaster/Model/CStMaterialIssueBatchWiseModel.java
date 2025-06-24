package com.erp.StIndentIssueMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "st_material_issue_batch_wise")
public class CStMaterialIssueBatchWiseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_issue_batch_wise_id")
    private int material_issue_batch_wise_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String transaction_no;
    private String transaction_date;
    private String set_no;
    private String goods_receipt_no;
    private String batch_no;
    private String indent_no;
    private Integer supplier_id;
    private String product_material_id;
    private Double cone_per_wt;
    private Integer requisition_no_boxes;
    private Double requisition_quantity;
    private Double requisition_weight;
    private Integer issue_no_boxes;
    private Double issue_quantity;
    private Double issue_weight;
    private Integer product_material_unit_id;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
    private String issue_status;
    private String issue_requisition_type;
    private String remark;
    private String financial_year;

    private Double return_quantity;
    private String issue_return_status;
    private Double return_weight;
    private Double receipt_quantity;
    private Integer return_boxes;
    private Double receipt_weight;
    private Integer receipt_boxes ;
    private Boolean is_delete = Boolean.FALSE;
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
    private Integer department_id;
    private Integer sub_department_id;

}
