package com.erp.HmShiftManagement.Repository;

import com.erp.HmShiftManagement.Model.CHtMissPunchCorrectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IHtMissPunchCorrectionRepository extends JpaRepository<CHtMissPunchCorrectionModel, Long> {

    @Query("FROM CHtMissPunchCorrectionModel model WHERE model.is_delete = 0 AND model.att_date_time BETWEEN ?1 AND ?2  AND model.employee_code IN ?3")
    List<CHtMissPunchCorrectionModel> getMissPunchCorrectionWithinDateRange(String attendanceFromDate, String attendanceToDate, List<String> getEmployeeCodes);

    @Modifying
    @Transactional
    @Query(value = "update CHtMissPunchCorrectionModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.punch_code = ?1 AND model.att_date_time = ?2 AND company_id = ?3")
    void updateMissPunchCorrectionRecord(String punchCode, String date, int company_id);

//    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Yes' ELSE 'No' END FROM hm_leaves_applications " +
//            "WHERE employee_code = :employeeCode " +
//            "AND leave_status = 'Approved' " +
//            "AND (DATE(:attDateTime) BETWEEN DATE_SUB(leaves_approved_from_date, INTERVAL 1 DAY) " +
//            "AND DATE_ADD(leaves_approved_to_date, INTERVAL 1 DAY))",
//            nativeQuery = true)
//    String checkNearbyLeave(@Param("employeeCode") String employeeCode,
//                            @Param("attDateTime") String attDateTime);

//    @Query(value = "SELECT CASE " +
//            "WHEN EXISTS ( " +
//            "    SELECT 1 FROM hm_leaves_applications hmla " +
//            "    WHERE hmla.employee_code = :employeeCode " +
//            "    AND hmla.leave_status = 'Approved' " +
//            "    AND ( " +
//            "        ( " +
//            "            DATE(:attDateTime) = DATE_SUB(hmla.leaves_approved_from_date, INTERVAL 1 DAY) " +
//            "            AND NOT EXISTS ( " +
//            "                SELECT 1 FROM ht_attendance_daily had " +
//            "                WHERE had.employee_code = hmla.employee_code " +
//            "                AND DATE(had.att_date_time) = DATE(:attDateTime) " +
//            "                AND had.out_time IS NOT NULL " +
//            "                AND TRIM(had.out_time) != '' " +
//            "                AND had.is_delete = 0 " +
//            "            ) " +
//            "        ) " +
//            "        OR " +
//            "        ( " +
//            "            DATE(:attDateTime) = DATE_ADD(hmla.leaves_approved_to_date, INTERVAL 1 DAY) " +
//            "            AND NOT EXISTS ( " +
//            "                SELECT 1 FROM ht_attendance_daily had " +
//            "                WHERE had.employee_code = hmla.employee_code " +
//            "                AND DATE(had.att_date_time) = DATE(:attDateTime) " +
//            "                AND had.in_time IS NOT NULL " +
//            "                AND TRIM(had.in_time) != '' " +
//            "                AND had.is_delete = 0 " +
//            "            ) " +
//            "        ) " +
//            "    ) " +
//            ") THEN 'Yes' ELSE 'No' END",
//            nativeQuery = true)
//    String checkNearbyLeave(@Param("employeeCode") String employeeCode,
//                            @Param("attDateTime") String attDateTime);


//    @Query(value = "SELECT CASE " +
//            "WHEN EXISTS ( " +
//            "    SELECT 1 FROM ht_attendance_daily had1 " +
//            "    WHERE had1.employee_code = :employeeCode " +
//            "    AND DATE(had1.att_date_time) = DATE(:attDateTime) " +
//            "    AND (had1.out_time IS NULL OR TRIM(had1.out_time) = '') " +
//            "    AND had1.is_delete = 0 " +
//            "    AND ( " +
//            "        EXISTS ( " +
//            "            SELECT 1 FROM ht_attendance_daily had2 " +
//            "            WHERE had2.employee_code = had1.employee_code " +
//            "            AND DATE(had2.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 1 DAY) " +
//            "            AND had2.job_type = 'LV' " +
//            "            AND had2.is_delete = 0 " +
//            "        ) " +
//            "        OR ( " +
//            "            EXISTS ( " +
//            "                SELECT 1 FROM ht_attendance_daily had3 " +
//            "                WHERE had3.employee_code = had1.employee_code " +
//            "                AND DATE(had3.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 1 DAY) " +
//            "                AND had3.job_type = 'WO' " +
//            "                AND had3.is_delete = 0 " +
//            "            ) " +
//            "            AND EXISTS ( " +
//            "                SELECT 1 FROM ht_attendance_daily had4 " +
//            "                WHERE had4.employee_code = had1.employee_code " +
//            "                AND DATE(had4.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 2 DAY) " +
//            "                AND had4.job_type = 'LV' " +
//            "                AND had4.is_delete = 0 " +
//            "            ) " +
//            "        ) " +
//            "    ) " +
//            ") THEN 'Yes' ELSE 'No' END",
//            nativeQuery = true)
//    String checkNearbyLeave(@Param("employeeCode") String employeeCode,
//                            @Param("attDateTime") String attDateTime);


    @Query(value = "SELECT CASE " +
            "WHEN EXISTS ( " +
            "    SELECT 1 FROM ht_attendance_daily had " +
            "    WHERE had.employee_code = :employeeCode " +
            "    AND DATE(had.att_date_time) = DATE(:attDateTime) " +
            "    AND had.is_delete = 0 " +
            "    AND ( " +
            "        ( " + // BACKWARD CHECK
            "            (had.out_time IS NULL OR TRIM(had.out_time) = '') " +
            "            AND ( " +
            "                EXISTS ( " +
            "                    SELECT 1 FROM ht_attendance_daily d1 " +
            "                    WHERE d1.employee_code = had.employee_code " +
            "                    AND DATE(d1.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 1 DAY) " +
            "                    AND d1.job_type = 'LV' AND d1.is_delete = 0 " +
            "                ) OR ( " +
            "                    EXISTS ( " +
            "                        SELECT 1 FROM ht_attendance_daily d2 " +
            "                        WHERE d2.employee_code = had.employee_code " +
            "                        AND DATE(d2.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 1 DAY) " +
            "                        AND d2.job_type = 'WO' AND d2.is_delete = 0 " +
            "                    ) AND EXISTS ( " +
            "                        SELECT 1 FROM ht_attendance_daily d3 " +
            "                        WHERE d3.employee_code = had.employee_code " +
            "                        AND DATE(d3.att_date_time) = DATE_ADD(DATE(:attDateTime), INTERVAL 2 DAY) " +
            "                        AND d3.job_type = 'LV' AND d3.is_delete = 0 " +
            "                    ) " +
            "                ) " +
            "            ) " +
            "        ) OR ( " + // FORWARD CHECK
            "            (had.in_time IS NULL OR TRIM(had.in_time) = '') " +
            "            AND ( " +
            "                EXISTS ( " +
            "                    SELECT 1 FROM ht_attendance_daily d4 " +
            "                    WHERE d4.employee_code = had.employee_code " +
            "                    AND DATE(d4.att_date_time) = DATE_SUB(DATE(:attDateTime), INTERVAL 1 DAY) " +
            "                    AND d4.job_type = 'LV' AND d4.is_delete = 0 " +
            "                ) OR ( " +
            "                    EXISTS ( " +
            "                        SELECT 1 FROM ht_attendance_daily d5 " +
            "                        WHERE d5.employee_code = had.employee_code " +
            "                        AND DATE(d5.att_date_time) = DATE_SUB(DATE(:attDateTime), INTERVAL 1 DAY) " +
            "                        AND d5.job_type = 'WO' AND d5.is_delete = 0 " +
            "                    ) AND EXISTS ( " +
            "                        SELECT 1 FROM ht_attendance_daily d6 " +
            "                        WHERE d6.employee_code = had.employee_code " +
            "                        AND DATE(d6.att_date_time) = DATE_SUB(DATE(:attDateTime), INTERVAL 2 DAY) " +
            "                        AND d6.job_type = 'LV' AND d6.is_delete = 0 " +
            "                    ) " +
            "                ) " +
            "            ) " +
            "        ) " +
            "    ) " +
            ") THEN 'Yes' ELSE 'No' END",
            nativeQuery = true)
    String checkNearbyLeave(@Param("employeeCode") String employeeCode,
                            @Param("attDateTime") String attDateTime);

}
