package com.erp.HmAttendanceStatus.Repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HmAttendanceStatus.Model.CHmAttendanceStatusModel;


public interface IHmAttendanceStatusRepository extends JpaRepository<CHmAttendanceStatusModel, Integer> {

	@Query(value = "FROM CHmAttendanceStatusModel model WHERE model.is_delete = false")
	List<CHmAttendanceStatusModel> getAttendanceStatusDetails();
}
