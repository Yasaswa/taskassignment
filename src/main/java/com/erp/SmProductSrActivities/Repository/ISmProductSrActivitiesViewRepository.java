package com.erp.SmProductSrActivities.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductSrActivities.Model.CSmProductSrActivitiesViewModel;


public interface ISmProductSrActivitiesViewRepository extends JpaRepository<CSmProductSrActivitiesViewModel, Integer> {

	@Query(value = "FROM CSmProductSrActivitiesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.product_sr_activity_id Desc")
	Page<CSmProductSrActivitiesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrActivitiesViewModel model where model.is_delete =0 and model.product_sr_activity_id = ?1 and model.company_id = ?2 order by model.product_sr_activity_id Desc")
	Page<CSmProductSrActivitiesViewModel> FnShowParticularRecord(int product_sr_activity_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrActivitiesViewModel model where model.is_delete = 0  and  model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrActivitiesViewModel> FnShowProductServiceActivitiesRecords(String product_sr_id, int company_id);	

}
