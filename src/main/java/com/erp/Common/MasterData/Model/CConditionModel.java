package com.erp.Common.MasterData.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CConditionModel {
	public String field;
	public String operator;
	public String value;
	public List<String> values; // For "IN" or "NOT IN"

}
