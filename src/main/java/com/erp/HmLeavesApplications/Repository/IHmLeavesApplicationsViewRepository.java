package com.erp.HmLeavesApplications.Repository;

import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IHmLeavesApplicationsViewRepository extends JpaRepository<CHmLeavesApplicationsViewModel, Integer> {

	@Query(value = "FROM CHmLeavesApplicationsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.company_id Desc")
	Page<CHmLeavesApplicationsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CHmLeavesApplicationsViewModel model where model.is_delete =0 and model.company_id = ?1 and model.leaves_applications_id = ?2 order by model.leaves_applications_id Desc")
	Page<CHmLeavesApplicationsViewModel> FnShowParticularRecord(int company_id, Pageable pageable, int leaves_applications_id);


}
