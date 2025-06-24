package com.erp.Company.Company_Directors.Repository;

import com.erp.Company.Company_Directors.Model.CCompanyDirectorsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICompanyDirectorsRepository extends JpaRepository<CCompanyDirectorsModel, Integer> {
	@Query(value = "FROM CCompanyDirectorsModel cdm WHERE  cdm.company_director_name = ?1")
	CCompanyDirectorsModel checkIfExistDirectorName(String company_director_name);

}
