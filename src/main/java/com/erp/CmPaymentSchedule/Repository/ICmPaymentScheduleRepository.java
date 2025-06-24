package com.erp.CmPaymentSchedule.Repository;

import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICmPaymentScheduleRepository extends JpaRepository<CCmPaymentScheduleModel, Integer> {

	@Query(value = "FROM CCmPaymentScheduleModel model where model.is_delete =0 and model.payment_schedule_id = ?1 and model.company_id = ?2")
	CCmPaymentScheduleModel FnShowParticularRecordForUpdate(int payment_schedule_id, int company_id);

	@Query(value = "FROM CCmPaymentScheduleModel model where model.is_delete =0 and model.payment_schedule_id = ?1 and model.company_id = ?2")
	CCmPaymentScheduleModel findByPaymentScheduleId(int payment_schedule_id, int company_id);

	@Query(value = "FROM CCmPaymentScheduleModel model where model.is_delete =0 and model.payment_schedule_name = ?1 and model.company_id = ?2")
	CCmPaymentScheduleModel checkIfNameExist(String payment_schedule_name, int company_id);

}
