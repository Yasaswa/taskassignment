package com.erp.Company.Companies.Repository;

import com.erp.Company.Companies.Model.CCompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ICompanyRepository extends JpaRepository<CCompanyModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_company set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int company_id);

	@Query(value = "Select * FROM cm_company cm where cm.is_delete = 0 and cm.company_id = ?1", nativeQuery = true)
	CCompanyModel FnShowParticularRecordForUpdate(int company_id);

	@Query(value = "FROM CCompanyModel  cm WHERE  cm.company_legal_name = ?1")
	CCompanyModel checkIfExistCompanyName(String company_legal_name);

	
	@Query(nativeQuery = true, value = "SELECT * FROM cm_company WHERE (company_legal_name = ?1 or company_short_name = ?2) and company_id = ?3 order by company_id Desc limit 1")
	CCompanyModel getCheck(String company_legal_name, String company_short_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_company WHERE is_delete = 0")
	ArrayList<CCompanyModel> getCompaniesList();
}
