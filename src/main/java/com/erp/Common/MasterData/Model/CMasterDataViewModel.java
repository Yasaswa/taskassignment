package com.erp.Common.MasterData.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CMasterDataViewModel {
	@Id
	private int field_id;

	private String field_name;


}
