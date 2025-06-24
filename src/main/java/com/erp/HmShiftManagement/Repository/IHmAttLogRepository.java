package com.erp.HmShiftManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.HmShiftManagement.Model.CHmAttLogModel;

import java.util.List;


public interface IHmAttLogRepository extends JpaRepository<CHmAttLogModel, Integer> {

//	@Query(value = "FROM CHmAttLogModel model where model.is_delete =0 and model.att_log_transaction_id = ?1 and model.company_id = ?2")
//	CHmAttLogModel FnShowParticularRecordForUpdate(int att_log_transaction_id, int company_id);

//	@Query(value = "FROM CHmAttLogModel model where model.is_delete =0 and model.att_log_transaction_id = ?1 and model.company_id = ?2")
//    List<CHmAttLogModel> FnGetAttLogByMonthYear(int att_log_transaction_id, int company_id);

    @Query("FROM CHmAttLogModel model WHERE model.att_device_emp_code In ?1 AND YEAR(STR_TO_DATE(model.att_date_time, '%Y-%m-%d %H:%i:%s')) = ?2 AND MONTH(STR_TO_DATE(model.att_date_time, '%Y-%m-%d %H:%i:%s')) = ?3")
    List<CHmAttLogModel> attLogByMonthAndYear(List<String> emplPunchCodes, int year, int month);

}
