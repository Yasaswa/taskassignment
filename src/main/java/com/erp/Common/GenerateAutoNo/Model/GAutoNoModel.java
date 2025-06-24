package com.erp.Common.GenerateAutoNo.Model;

public class GAutoNoModel {

	public String tName;
	public String length;
	public String fieldName;
	public String autoNoGenerate;
	public String company_id;

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAutoNoGenerate() {
		return autoNoGenerate;
	}

	public void setAutoNoGenerate(String autoNoGenerate) {
		this.autoNoGenerate = autoNoGenerate;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "GAutoNoModel [tName=" + tName + ", length=" + length + ", fieldName=" + fieldName + ", autoNoGenerate="
				+ autoNoGenerate + ", company_id=" + company_id + "]";
	}

	public GAutoNoModel(String tName, String length, String fieldName, String autoNoGenerate, String company_id) {
		super();
		this.tName = tName;
		this.length = length;
		this.fieldName = fieldName;
		this.autoNoGenerate = autoNoGenerate;
		this.company_id = company_id;
	}

	public GAutoNoModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
