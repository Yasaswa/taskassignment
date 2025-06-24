package com.erp.Common.MasterData.Model;

import java.util.List;

public class CGlobalQueryObjectModel {
	public String operation;
	public List<String> columns;
	public String table;
	public List<CConditionModel> conditions;
	public List<CJoinModel> joins;
	public List<String> groupBy; // List of columns for GROUP BY
	public List<String> orderBy; // List of columns for ORDER BY

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<CConditionModel> getConditions() {
		return conditions;
	}

	public void setConditions(List<CConditionModel> conditions) {
		this.conditions = conditions;
	}

	public List<CJoinModel> getJoins() {
		return joins;
	}

	public void setJoins(List<CJoinModel> joins) {
		this.joins = joins;
	}

	public List<String> getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
	}

	public List<String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<String> orderBy) {
		this.orderBy = orderBy;
	}


}
