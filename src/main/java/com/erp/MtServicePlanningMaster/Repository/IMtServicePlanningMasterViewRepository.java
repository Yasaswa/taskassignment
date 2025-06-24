package com.erp.MtServicePlanningMaster.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningMasterViewModel;


public interface IMtServicePlanningMasterViewRepository extends JpaRepository<CMtServicePlanningMasterViewModel, Integer> {

}
