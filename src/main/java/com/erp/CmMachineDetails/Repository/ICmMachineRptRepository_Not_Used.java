package com.erp.CmMachineDetails.Repository;

import com.erp.CmMachineDetails.Model.CCmMachineRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICmMachineRptRepository_Not_Used extends JpaRepository<CCmMachineRptModel_Not_Used, Integer> {

//    @Query(value ="select * FROM cmv_machine_rpt", nativeQuery = true)
//	Page<List<Map<String, Object>>> FnShowAllMachineReportRecords(Pageable pageable, int company_id);

}
