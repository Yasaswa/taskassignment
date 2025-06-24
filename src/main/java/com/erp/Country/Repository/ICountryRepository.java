package com.erp.Country.Repository;

import com.erp.Country.Model.CCountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICountryRepository extends JpaRepository<CCountryModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM cm_country WHERE (country_name = ?1 OR (?2 IS NOT NULL AND country_short_name = ?2)) and company_id = ?3 order by country_id Desc limit 1")
	CCountryModel getCheck(String country_name, String country_short_name, int company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_country WHERE country_name = ?1")
//	CCountryModel getCheck(String country_name);

}
