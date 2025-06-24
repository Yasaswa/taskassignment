package com.erp.Common.Properties.Repository;

import com.erp.Common.Properties.Model.CPropertiesModel;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPropertiesRepository extends JpaRepository<CPropertiesModel, Long> {

	@Query(value = "SELECT * FROM  amv_properties", nativeQuery = true)
	List<CPropertiesViewModel> FnShowAllRecords();

	@Query(nativeQuery = true, value = "SELECT * FROM am_properties WHERE property_name = ?1 and company_id = ?2 and properties_master_name = ?3  AND is_delete = 0")
	CPropertiesModel checkIfNameExist(String getproperty_name, int company_id, String properties_master_name);

	@Query(value = "SELECT * FROM am_properties where is_delete =0 and property_id = ?1" +
//			" and company_id = ?2" +
			"", nativeQuery = true)
	CPropertiesModel FnShowParticularRecordForUpdate(int property_id, int company_id);


}
