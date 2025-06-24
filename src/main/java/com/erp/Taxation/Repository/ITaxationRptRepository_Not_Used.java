package com.erp.Taxation.Repository;


import com.erp.Taxation.Model.CTaxationRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxationRptRepository_Not_Used extends JpaRepository<CTaxationRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM cmv_taxation_rpt", nativeQuery = true)
//	Page<CTaxationRptModel> FnShowAllReportRecords(Pageable pageable);

}

