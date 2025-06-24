package com.erp.HmLeaveType.Repository;

import com.erp.HmLeaveType.Model.CHmLeaveTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IHmLeaveTypeRepository extends JpaRepository<CHmLeaveTypeModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_leave_type set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where leave_type_id=?1", nativeQuery = true)
	void FnDeleteRecord(int leave_type_id, String deleted_by);

	@Query(value = "FROM CHmLeaveTypeModel model where model.is_delete =0 and model.leave_type_name = ?1")
	CHmLeaveTypeModel checkIfNameExist(String leave_type_name);

	@Query(value = "FROM CHmLeaveTypeModel model where model.is_delete =0 and model.company_id=?1 and model.leave_type_id = ?2")
	CHmLeaveTypeModel FnShowParticularRecordForUpdate(int company_id, int leave_type_id);


}
