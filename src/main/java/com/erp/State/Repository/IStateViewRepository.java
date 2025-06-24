package com.erp.State.Repository;

import com.erp.State.Model.CStateViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStateViewRepository extends JpaRepository<CStateViewModel, Long> {

	@Query(value = "Select * FROM  cmv_state order by state_id Desc", nativeQuery = true)
	Page<CStateViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_state where is_delete =0  order by state_id Desc", nativeQuery = true)
	Page<CStateViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_state where is_delete =0 and state_id = ?1", nativeQuery = true)
	CStateViewModel FnShowParticularRecordForUpdate(int state_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_state where is_delete =0 and company_id = ?1 and state_id = ?2")
	CStateViewModel FnShowParticularRecord(int company_id, int state_id);


}
