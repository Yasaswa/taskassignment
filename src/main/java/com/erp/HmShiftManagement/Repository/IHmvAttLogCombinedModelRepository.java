package com.erp.HmShiftManagement.Repository;

import com.erp.HmShiftManagement.Model.CHmvAttLogCombinedModel;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface IHmvAttLogCombinedModelRepository extends JpaRepository<CHmvAttLogCombinedModel, Integer> {

    @Query("FROM CHmvAttLogCombinedModel MODEL WHERE MODEL.att_device_emp_code IN ?1 AND DATE(MODEL.att_date_time) BETWEEN ?2 AND ?3")
    List<CHmvAttLogCombinedModel> getPunchingDetailsByPunchCodes(List<String> distinctPunchingCodes, Date parse, Date parse1);
}

//public interface IHmvAttLogCombinedModelRepository extends JpaRepository<CHmvAttLogCombinedModel, Long> {
//
//    @Query("FROM CHmvAttLogCombinedModel MODEL WHERE MODEL.att_device_emp_code IN :distinctPunchingCodes AND DATE(MODEL.att_date_time) BETWEEN :fromDate AND :toDate")
//    Page<CHmvAttLogCombinedModel> getPunchingDetailsByPunchCodes(
//            @Param("distinctPunchingCodes") List<String> distinctPunchingCodes,
//            @Param("fromDate") Date fromDate,
//            @Param("toDate") Date toDate,
//            Pageable pageable); // Add Pageable parameter
//}