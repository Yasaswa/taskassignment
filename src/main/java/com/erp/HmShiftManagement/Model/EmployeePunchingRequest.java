package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePunchingRequest {
    private int company_id;
    private String from_Date;
    private String to_Date;
    private String employee_type;
    private Integer department;
    private Integer sub_department;
    private String punching_code;
}
