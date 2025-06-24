package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentSchedulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IStIndentSchedulesRepository extends JpaRepository<CStIndentSchedulesModel, Integer> {

	@Query(value = "FROM CStIndentSchedulesModel model where model.is_delete =0 and model.indent_schedule_id = ?1 and model.company_id = ?2")
	CStIndentSchedulesModel FnShowParticularRecordForUpdate(int indent_schedule_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM st_indent_schedules WHERE indent_no = ?1")
	CStIndentSchedulesModel getCheck(String getindent_no);


	@Modifying
	@Transactional
	@Query(value = "update st_indent_schedules set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateIndentSchedulesStatus(String indent_no, int indent_version, int company_id);

	@Query(value = "from CStIndentSchedulesModel model where model.is_delete = 0 and model.indent_no = ?1 and model.indent_version = ?2 and model.company_id = ?3")
	List<CStIndentSchedulesModel> FnShowIndentSchedules(String indent_no, int indent_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_schedules set is_delete = 1,deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteIndentSchedules(String indent_no, int indent_version, int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_schedules set  indent_item_status = 'X' ,deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	void cancelIndentSchedules(String indent_no, int indent_version, int company_id, String user_name);


}
