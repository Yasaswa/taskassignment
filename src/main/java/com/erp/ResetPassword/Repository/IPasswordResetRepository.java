package com.erp.ResetPassword.Repository;

import com.erp.schedule_Ledger.Model.CScheduleLedgerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Repository
public interface IPasswordResetRepository extends JpaRepository<CScheduleLedgerModel, Integer>  {

//    @Query(value = "select * from cm_employee where employee_code = ?1 and  is_delete  = 1  and is_active  = 1")
//    Object checkCredentialInDatabase(String inputCred);

    @Query(value = "SELECT employee_name, employee_code, email_id1, company_id FROM cm_employee WHERE (employee_code = ?1 OR old_employee_code = ?1) AND is_delete = 0 AND is_active = 1", nativeQuery = true)
    List<Object[]> checkCredentialInDatabase(String inputCred);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CEmployeesModel model SET model.password = ?1 WHERE (model.employee_code=?2 OR model.old_employee_code = ?2) AND model.company_id = ?3 AND model.is_delete = false")
    int updateUserCode(String confirmEncryptedPass, String user_code, int company_id);

}
