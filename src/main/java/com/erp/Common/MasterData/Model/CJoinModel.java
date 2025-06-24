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
public class CJoinModel {

	public String table;
	public String type;
	public List<CJoinConditionModel> on;
}
