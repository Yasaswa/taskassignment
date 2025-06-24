package com.erp.XmProductionLotMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterViewModel;


public interface IXmProductionLotMasterViewRepository extends JpaRepository<CXmProductionLotMasterViewModel, Integer> {

	
	@Query(value = "FROM CXmProductionLotMasterViewModel model WHERE  model.is_delete = 0 and model.lot_id = ?1 and model.company_id = ?2")
	CXmProductionLotMasterViewModel FnShowParticularRecordForUpdate(int lot_id, int company_id);
	

}
