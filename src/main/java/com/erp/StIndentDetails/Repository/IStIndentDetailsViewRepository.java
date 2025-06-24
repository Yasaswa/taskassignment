package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentDetailsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IStIndentDetailsViewRepository extends JpaRepository<CStIndentDetailsViewModel, Integer> {

	@Query(value = "FROM CStIndentDetailsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.indent_details_id Desc")
	Page<CStIndentDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CStIndentDetailsViewModel model where model.is_delete =0 and model.indent_details_id = ?1 and model.company_id = ?2 order by model.indent_details_id Desc")
	Page<CStIndentDetailsViewModel> FnShowParticularRecord(int indent_details_id, Pageable pageable, int company_id);

	@Query(value = "select * FROM stv_indent_details  where is_delete = 0 and indent_no IN ?1 and company_id = ?2 and department_id = ?3 and sub_department_id = ?4", nativeQuery = true)
	List<CStIndentDetailsViewModel> FnShowIndentDetails(List<String> indentNoList, int company_id, int department_id, int sub_department_id);

	@Query(value = "select * FROM stv_indent_details  where is_delete = 0 and indent_no IN ?1 and company_id = ?2 ", nativeQuery = true)
	List<CStIndentDetailsViewModel> FngetExistingIndentMaterials(List<String> distinctIndentNumbers, int companyId);

//	@Query(value = "FROM CStIndentDetailsViewModel model where model.is_delete =0 and model.indent_no IN ?1 and model.company_id = ?2")
//	List<CStIndentDetailsViewModel> FnShowIndentDetails(List<String> indentNoList, int company_id);
	
	


}
