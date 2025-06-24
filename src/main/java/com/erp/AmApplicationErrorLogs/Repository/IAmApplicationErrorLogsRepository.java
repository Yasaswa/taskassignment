package com.erp.AmApplicationErrorLogs.Repository;

import com.erp.AmApplicationErrorLogs.Model.CAmApplicationErrorLogsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAmApplicationErrorLogsRepository extends JpaRepository<CAmApplicationErrorLogsModel, Integer> {

	@Query(value = "FROM CAmApplicationErrorLogsModel model where model.is_delete =0 and model.company_id = ?1 ORDER BY model.application_error_id DESC")
	Page<CAmApplicationErrorLogsModel> FngetamApplicationErrorLogs(int company_id, Pageable pageable);

}
