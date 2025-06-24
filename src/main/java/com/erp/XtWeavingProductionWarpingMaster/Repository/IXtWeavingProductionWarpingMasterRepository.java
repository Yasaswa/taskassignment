package com.erp.XtWeavingProductionWarpingMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMasterModel;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IXtWeavingProductionWarpingMasterRepository extends JpaRepository<CXtWeavingProductionWarpingMasterModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionWarpingMasterModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.weaving_production_warping_master_id = ?1")
	void FnDeleteProductionWarpingMasterRecord(int weaving_production_warping_master_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionWarpingMasterModel model set model.print_status = 1  where model.weaving_production_warping_master_id = ?1")
	void FnUpdatePrintStatusWarpingMasterRecord(int weaving_production_warping_master_id);

	@Query(value = "From CXtWeavingProductionWarpingMasterModel model where model.set_no = :set_no and model.company_id = :company_id and model.is_delete = 0")
	Optional<CXtWeavingProductionWarpingMasterModel> FnGetWarpingModel(@Param("set_no") String set_no ,@Param("company_id") Integer company_id);

}
