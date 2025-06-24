//package com.erp.SmProductTypeGroupParameters.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersModel;
//
//public interface ISmProductTypeGroupParametersRepository
//		extends JpaRepository<CSmProductTypeGroupParametersModel, Integer> {
//
//	@Query(value = "FROM CSmProductTypeGroupParametersModel model where model.is_delete =0 and model.product_type_group_parameters_id = ?1 and model.company_id = ?2")
//	CSmProductTypeGroupParametersModel FnShowParticularRecordForUpdate(int product_type_group_parameters_id,
//			int company_id);
//
//	@Modifying
//	@Transactional
//	@Query(value = "update CSmProductTypeGroupParametersModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_type_id = ?1")
//	void FnDeleteProductTypeGroupParametersRecord(int product_type_id, String deleted_by);
//
//	@Query(value = "FROM CSmProductTypeGroupParametersModel model where model.is_delete =0 and model.product_type_group_parameters_id = ?1 and model.company_id = ?2")
//	Map<String, Object> FnShowProductTypeGroupParametersRecordForUpdate(int product_type_group_parameters_id,
//			int company_id);
//
//	@Modifying
//	@Transactional
//	@Query(value = "update CSmProductTypeGroupParametersModel model set model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() where model.company_id = ?1 and model.product_type_id = ?2")
//	void updateProductTypeGroupParametersRecord(int company_id, int product_type_id);
//	
//    @Query("SELECT p.product_type_group_parameters_id FROM CSmProductTypeGroupParametersModel p WHERE p.is_delete = 0 AND p.product_type_group_parameters_name = ?1")
//	int getProductTypeParameterId(String group_name);
//
//	
//}
