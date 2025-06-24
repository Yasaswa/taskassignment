package com.erp.Country.Repository;


import com.erp.Country.Model.CCountryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICountryViewRepository extends JpaRepository<CCountryViewModel, Long> {

	@Query(value = "Select country_code from cmv_country", nativeQuery = true)
	List<String> FnFetchCountryCodes();


}
