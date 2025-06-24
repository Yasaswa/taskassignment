package com.erp.JobType.Repository;

import com.erp.JobType.Model.CJobTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IJobTypeRepository extends JpaRepository<CJobTypeModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM hm_job_type WHERE job_type_name = ?1 and is_delete = 0")
	CJobTypeModel getCheck(String job_type_name);

	@Modifying
	@Transactional
	@Query(value = "update hm_job_type set is_delete = 1, deleted_by = ?2,deleted_on = CURRENT_TIMESTAMP() where job_type_id=?1", nativeQuery = true)
	void FnDeleteRecord(int job_type_id, String deleted_by);

//	@Query(nativeQuery = true, value = "SELECT * FROM hm_job_type WHERE ( job_type_name = ?1 or job_type_short_name = ?2 ) and company_id = ?3 and is_delete = 0")
//	CJobTypeModel getCheck(String job_type_name, String job_type_short_name, Integer company_id);

	@Query(nativeQuery = true, value = "SELECT job_type_id FROM hm_job_type where job_type_short_name = ?1")
	int getAttendanceType(String attendanceType);

	@Query(value = "FROM CJobTypeModel model WHERE model.is_delete = false and model.company_id = ?1")
	List<CJobTypeModel> getJobTypeDetails(int company_id);


	@Query(nativeQuery = true, value = "SELECT * FROM hm_job_type WHERE ( job_type_name = ?1 or job_type_short_name = ?2 ) and is_delete = 0")
	CJobTypeModel getCheck(String jobTypeName, String jobTypeShortName);

	@Query(nativeQuery = true, value = "SELECT * FROM hm_job_type WHERE job_type_name = ?1 and job_type_id != ?2 and is_delete = 0")
	CJobTypeModel getCheckForUpdate(String jobTypeName, int job_type_id);
}
