package com.erp.Company.Company_Branch.Repository;

import com.erp.Company.Company_Branch.Model.CCompanyBranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ICompanyBranchRepository extends JpaRepository<CCompanyBranchModel, Integer> {
	@Query(value = "from CCompanyBranchModel cbm where cbm.company_branch_name =?1")
	CCompanyBranchModel checkIfExist(String company_branch_name);

	@Query(value = "FROM CCompanyBranchModel cbvm where cbvm.is_delete =0 and cbvm.company_branch_id = ?1")
	CCompanyBranchModel FnShowParticularRecordForUpdate(int company_branch_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_company_branch WHERE is_delete = 0")
	ArrayList<CCompanyBranchModel> getCompaniesBranchList();
}
