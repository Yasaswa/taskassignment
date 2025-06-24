package com.erp.HmLeavesApplications.Repository;

import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IHmLeavesApplicationsRepository extends JpaRepository<CHmLeavesApplicationsModel, Integer> {

    @Query(value = "FROM CHmLeavesApplicationsModel model where model.is_delete =0 and model.company_id = ?1 and model.leaves_transaction_id = ?2")
    CHmLeavesApplicationsModel FnShowParticularRecordForUpdate(int company_id, int leaves_transaction_id);

//	@Modifying
//	@Transactional
//	@Query(value = "update hm_leaves_applications set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where leaves_transaction_id=?1", nativeQuery = true)
//	void FnDeleteRecord(int leaves_transaction_id, String deleted_by);

    @Query(value = "SELECT COUNT(*) AS exsitingCount FROM hmv_leaves_applications  WHERE is_delete = '0' AND employee_code = ?1 AND ((leaves_requested_from_date >= ?2 AND leaves_requested_from_date <= ?3)  OR (leaves_requested_to_date >= ?2 AND leaves_requested_to_date <= ?3)) AND leave_status !='Cancelled'", nativeQuery = true)
    Map<String, Object> FnShowExisitingCount(String employee_code, String leaves_requested_from_date,
                                             String leaves_requested_to_date);

    @Query(value = "SELECT * FROM hmv_leaves_applications WHERE ?1 BETWEEN leaves_approved_from_date AND leaves_approved_to_date AND leave_status = 'Approved'", nativeQuery = true)
    List<Map<String, Object>> getLeavesApplicationDetails(String attendanceDate);

    @Query(nativeQuery = true, value = "SELECT property_value FROM amv_properties where property_name = ?1")
    String getEmployeeType(String employeeType);

    @Query(nativeQuery = true, value = "SELECT property_name FROM amv_properties WHERE property_value = ?1 AND properties_master_name = 'EmployeeTypeGroup'")
    String getEmployeeTypeGroup(String propertyValue);

    @Query("SELECT model FROM CEmployeesViewModel model WHERE model.employee_code = :employeeCode")
    CEmployeesViewModel getEmployeeDetails(@Param("employeeCode") String employeeCode);


    @Query("SELECT model FROM CEmployeesViewModel model WHERE model.old_employee_code = :punchingCode")
    List<CEmployeesViewModel> employeeDetailsForSamePunchCode(@Param("punchingCode") String punchingCode);
//	@Query("SELECT model FROM CHmLeavesApplicationsModel model WHERE ((leaves_requested_from_date >= ?2 AND leaves_requested_from_date <= ?3)  OR (leaves_requested_to_date >= ?2 AND leaves_requested_to_date <= ?3)) and model.punch_code = :punchingCode")
//	List<CHmLeavesApplicationsModel> leaveApplicationForSamePunchCode(String punchingCode);

    @Query("SELECT model FROM CHmLeavesApplicationsModel model WHERE " +
            "((model.leaves_requested_from_date >= :startDate AND model.leaves_requested_from_date <= :endDate) " +
            "OR (model.leaves_requested_to_date >= :startDate AND model.leaves_requested_to_date <= :endDate)) " +
            "AND model.punch_code = :punchingCode " +
            "AND model.is_delete = 0 " +
            "AND model.leave_status != 'Cancelled'")
    List<CHmLeavesApplicationsModel> leaveApplicationForSamePunchCode(
            @Param("punchingCode") String punchingCode,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    @Modifying
    @Transactional
    @Query(value = "update hm_leaves_applications set is_delete = 1 ,deleted_by=?4 , deleted_on = CURRENT_TIMESTAMP() where punch_code =?1 and leaves_requested_from_date =?2 and leaves_requested_to_date =?3 and leave_status = 'Pending'", nativeQuery = true)
    void FnDeleteRecord(String punch_code, String leavesRequestedFromDate, String leavesRequestedToDate, String deletedBy);

    @Query(value = "SELECT * FROM hmv_leaves_applications WHERE ((leaves_approved_from_date BETWEEN ?1 AND ?2) OR (leaves_approved_to_date BETWEEN ?1 AND ?2)) AND leave_status = 'Approved' and employee_code IN ?3", nativeQuery = true)
    List<Map<String, Object>> getLeavesApplicationDetails(LocalDate fromDate, LocalDate toDate, List<String> getEmployeeCodes);


    @Query(value = "SELECT COUNT(*) AS existingCount FROM xm_production_holiday WHERE is_delete = '0' AND (production_holiday_date >= ?1 AND production_holiday_date <= ?2)", nativeQuery = true)
    Map<String, Object> FnShowExistingCountOfHoliday(LocalDate approveFromDt, LocalDate approveToDt);


//    @Query("SELECT model FROM CXmProductionHolidayModel model WHERE model.is_delete = '0'")
//    List<CXmProductionHolidayModel> findAllHolidays();
}
