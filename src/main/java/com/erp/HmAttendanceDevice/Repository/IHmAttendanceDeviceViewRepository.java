package com.erp.HmAttendanceDevice.Repository;

import com.erp.HmAttendanceDevice.Model.CHmAttendanceDeviceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IHmAttendanceDeviceViewRepository extends JpaRepository<CHmAttendanceDeviceViewModel, Integer> {

//	@Query(value = "FROM CHmAttendanceDeviceViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.company_id Desc")
//	Page<CHmAttendanceDeviceViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	@Query(value = "FROM CHmAttendanceDeviceViewModel model where model.is_delete =0 and model.company_id = ?1 and model.company_id = ?2 order by model.company_id Desc")
//	Page<CHmAttendanceDeviceViewModel> FnShowParticularRecord(int company_id, Pageable pageable, int company_id);
//	

}
