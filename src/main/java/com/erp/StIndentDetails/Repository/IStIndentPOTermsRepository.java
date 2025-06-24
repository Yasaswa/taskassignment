package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentPOTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IStIndentPOTermsRepository extends JpaRepository<CStIndentPOTermsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update st_indent_po_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateIndentPOTermsStatus(String indent_no, Integer indent_version, int company_id);

	@Query(value = "from CStIndentPOTermsModel model where model.is_delete = 0 and model.indent_no = ?1 and model.indent_version = ?2 and model.company_id = ?3")
	List<CStIndentPOTermsModel> FnShowIndentPoTerms(String indent_no, int indent_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_po_terms set is_delete = 1,deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteIndentPOTerms(String indent_no, int indent_version, int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_po_terms set indent_item_status = 'X' ,deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void cancelIndentPOTerms(String indent_no, int indent_version, int company_id, String user_name);

}
