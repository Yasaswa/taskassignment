package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomDetailsModel;
import org.springframework.data.repository.query.Param;

public interface IXtWeavingProductionLoomDetailsRepository extends JpaRepository<CXtWeavingProductionLoomDetailsModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionLoomDetailsModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWeavingProductionLoomDetailsRecord(int weaving_production_loom_master_id, int company_id,
			String deleted_by);

	
	@Query(value = "FROM CXtWeavingProductionLoomDetailsModel model WHERE model.is_delete = 0 and set_no IN ?1")
	List<CXtWeavingProductionLoomDetailsModel> getAllWeavingProductionLoomDetails(
			List<String> weavingLoomProductionSetNumbers);


	@Modifying
	@Query(value = "UPDATE am_properties ap SET ap.property_group = 'Available' WHERE ap.is_delete = 0 AND ap.properties_master_name = 'LoomMachineNo' AND ap.property_value IN :loomnos", nativeQuery = true)
	int updateLoomNoStatus(@Param("loomnos") List<String> loomnos);


//	@Query(value = "select xwpld.style , xwpld.sizing_beam_no ,xwpld.machine_id, xwpld.width ,xwpld.sort_no, xwpld.product_in_meter ,xwpld.prodcut_1000pick, spf.product_fg_name , spf.product_fg_id from xt_weaving_production_loom_details xwpld left join sm_product_fg spf on spf.product_fg_code = xwpld.sort_no where xwpld.set_no = ?1 AND xwpld.company_id = ?2 AND xwpld.is_delete = 0", nativeQuery = true)
//	ArrayList<Map<String, Object>> FnGetInspectionMasterData(String setNo, Integer companyId);

	@Query(value = "SELECT distinct xwpld.sizing_beam_no, xwpld.style, xwpld.machine_id, xwpld.sort_no, spf.product_fg_name, spf.product_fg_id from xt_weaving_production_loom_details xwpld LEFT JOIN sm_product_fg spf on spf.product_fg_code = xwpld.sort_no where xwpld.set_no = ?1 AND xwpld.company_id = ?2 AND xwpld.is_delete = 0 ", nativeQuery = true)
	ArrayList<Map<String, Object>> FnGetInspectionMasterData(String setNo, Integer companyId);


	@Query(value = "SELECT DISTINCT sort_no FROM xt_weaving_production_loom_details " +
			"WHERE loom_production_date BETWEEN :fromDate AND :toDate " +
			"AND is_delete = 0 AND company_id = :companyId", nativeQuery = true)
	List<String> FnFetchSortNos(@Param("fromDate") String fromDate,
								@Param("toDate") String toDate,
								@Param("companyId") Integer companyId);

}
