//package com.erp.SmProductTypeGroupParameters.Repository;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersValuesModel;
//
//public interface ISmProductTypeGroupParametersValueRepository extends JpaRepository<CSmProductTypeGroupParametersValuesModel, Integer>{
//
//	@Modifying
//	@Transactional
//	@Query(value = "update CSmProductTypeGroupParametersValuesModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_type_id = ?1")
//	void FnDeleteProductTypeGroupParametersValueRecord(int product_type_id, String deleted_by);
//
//	
//	@Query(value = "FROM CSmProductTypeGroupParametersValuesModel model where model.is_delete =0 and model.product_type_group_parameters_id = ?1 and model.company_id = ?2")
//	List<CSmProductTypeGroupParametersValuesModel> FnShowProductTypeGroupParametersValueRecordForUpdate(
//			int product_type_group_parameters_id, int company_id);
//
//
//	@Modifying
//	@Transactional
//	@Query(value = "update CSmProductTypeGroupParametersValuesModel model set model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() where model.company_id = ?1 and model.product_type_id = ?2")
//	void updateProductTypeGroupParametersValueRecord(int company_id,int product_type_id);
//
//}
