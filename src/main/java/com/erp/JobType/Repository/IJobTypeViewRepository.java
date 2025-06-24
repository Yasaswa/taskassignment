package com.erp.JobType.Repository;

import com.erp.JobType.Model.CJobTypeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IJobTypeViewRepository extends JpaRepository<CJobTypeViewModel, Integer> {


    @Query(value = "Select * FROM  hmv_job_type order by job_type_id Desc", nativeQuery = true)
    Page<CJobTypeViewModel> FnShowAllRecords(Pageable pageable);

    @Query(value = "Select * FROM  hmv_job_type where is_delete =0  order by job_type_id Desc", nativeQuery = true)
    Page<CJobTypeViewModel> FnShowAllActiveRecords(Pageable pageable);

//	@Query(nativeQuery = true, value = "Select * FROM  hmv_job_type where is_delete =0 and company_id = ?1 and job_type_id = ?2")
//	CJobTypeViewModel FnShowParticularRecord(int company_id, int job_type_id);

    @Query(nativeQuery = true, value = "Select * FROM  hmv_job_type where is_delete =0  and job_type_id = ?1")
    CJobTypeViewModel FnShowParticularRecord(int job_type_id);

    @Query(value = "Select * FROM  hmv_job_type where is_delete =0 and job_type_id = ?1", nativeQuery = true)
    CJobTypeViewModel FnShowParticularRecordForUpdate(int job_type_id);

    @Query(value = "Select * From hmv_job_type_rpt", nativeQuery = true)
    Map<String, Object> FnShowAllReportRecords();


}
