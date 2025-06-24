package com.erp.HtAttendaceNew.Repository;


import com.erp.HmShiftManagement.Model.CHmAttLogModel;
import com.erp.HtAttendaceNew.Model.CHmAttLogNewModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IHmAttLogNewRepository extends JpaRepository<CHmAttLogNewModel, Integer> {


}
