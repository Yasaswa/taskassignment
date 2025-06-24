package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface IStIndentMasterRepository extends JpaRepository<CStIndentMasterModel, Integer> {

	@Query(value = "Select * FROM st_indent_master WHERE is_delete = 0 and indent_no = ?1 and indent_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	CStIndentMasterModel findByIndentNo(String indent_no, Integer indent_version, String financial_year, int company_id);

	@Query(value = "Select * from st_indent_master where is_delete = 0 and indent_no = ?1 and company_id = ?2", nativeQuery = true)
	CStIndentMasterModel getLastPOIndentVersion(String filename, String company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set is_delete = 1 , deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteIndent(String indent_no, int indent_version, int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set indent_status = 'X', modified_by =?4, modified_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void cancelIndent(String indent_no, int indent_version, int company_id, String user_name);


	@Query(value = "SELECT * FROM stv_indent_master_summary  WHERE indent_no = ?1 and indent_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowIndentMasterRecord(String indent_no, int indent_version, String financial_year,
	                                             int company_id);
	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set indent_status = ?1 WHERE indent_no IN ?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
	void updateIndentMasterRecord(String issue_item_status, List<String> indent_no, int company_id);
	
//	@Modifying
//	@Transactional
//	@Query(value = "update st_indent_master set indent_status = ?1 WHERE indent_no IN ?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
//	void updateIndentMasterRecord(String status, List<String> indentNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set indent_status = 'Z',  modified_by =?3 , modified_on = CURRENT_TIMESTAMP() where indent_no = ?1  and company_id = ?2", nativeQuery = true)
	void updatePreClosedmastersRecord( String indent_no,  int company_id, int userId );




	@Query(value = "SELECT * FROM stv_indent_master_summary  WHERE indent_no IN ?1 and company_id= ?2 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnGetIndentMstByIndentNos(List<String> indent_nos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set issue_status = ?1 WHERE indent_master_id = ?2  AND company_id = ?3", nativeQuery = true)
    void updateIndentMasterByIssue(String status, int master_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set po_status = ?1 WHERE indent_no = ?2 AND company_id = ?3 AND is_delete = 0 ", nativeQuery = true)
	void revertIndentMasterPOStatus(String status , String indentNumber, int company_id);
}
