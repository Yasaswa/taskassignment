package com.erp.SmProductServiceActivityMaster.Repository;



import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterViewModel;

import antlr.collections.List;

public interface ISmProductServiceActivityMasterViewRepository
		extends JpaRepository<CSmProductServiceActivityMasterViewModel, Integer> {

	@Query(value = "FROM CSmProductServiceActivityMasterViewModel model where model.is_delete = 0 and model.product_service_activity_master_id = ?1 and model.company_id = ?2")
	CSmProductServiceActivityMasterViewModel FnShowParticularRecordForUpdate(int product_service_activity_master_id,
			int company_id);

	@Query(value = "FROM CSmProductServiceActivityMasterViewModel model where model.is_delete = 0 and model.company_id = ?1")
	ArrayList<CSmProductServiceActivityMasterViewModel> FnShowAllActiveRecords(int company_id);

}
