package com.erp.Company.Company_Directors.Repository;

import com.erp.Company.Company_Directors.Model.CCompanyDirectorsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICompanyDirectorsViewRepository extends JpaRepository<CCompanyDirectorsViewModel, Long> {

	@Query(value = "FROM CCompanyDirectorsViewModel cdvm order by cdvm.company_director_id Desc")
	Page<CCompanyDirectorsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "FROM CCompanyDirectorsViewModel cdvm where cdvm.is_delete =0  order by cdvm.company_director_id Desc")
	Page<CCompanyDirectorsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CCompanyDirectorsViewModel cdvm where cdvm.is_delete =0 and cdvm.company_id = ?1 order by cdvm.company_director_id Desc")
	Page<CCompanyDirectorsViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	@Query(value = "FROM  CCompanyDirectorsViewModel cdvm where cdvm.is_delete =0 and cdvm.company_director_id = ?1")
	CCompanyDirectorsViewModel FnShowParticularRecordForUpdate(int company_director_id);

}
