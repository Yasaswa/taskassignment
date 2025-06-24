package com.erp.Common.Properties.Repository;

import com.erp.Common.Properties.Model.CPropertiesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPropertiesViewRepository extends JpaRepository<CPropertiesViewModel, Long> {

	@Query("FROM CPropertiesViewModel prop where prop.is_delete = 0 and prop.properties_master_name = ?1")
	List<CPropertiesViewModel> FnShowParticularRecord(String controlName, int company_id);

	@Query(value = "FROM CPropertiesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.property_id Desc")
	Page<CPropertiesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM amv_properties where is_delete =0 and property_id = ?1 and company_id = ?2 order by property_id Desc", nativeQuery = true)
	Page<CPropertiesViewModel> FnShowParticularRecordById(int property_id, Pageable pageable, int company_id);

	@Query(value = "Select * FROM amv_properties_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
