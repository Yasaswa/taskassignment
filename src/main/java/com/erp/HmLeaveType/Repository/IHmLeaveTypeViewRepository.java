package com.erp.HmLeaveType.Repository;

import com.erp.HmLeaveType.Model.CHmLeaveTypeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IHmLeaveTypeViewRepository extends JpaRepository<CHmLeaveTypeViewModel, Integer> {


	@Query(value = "FROM CHmLeaveTypeViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.leave_type_id Desc")
	Page<CHmLeaveTypeViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


}
