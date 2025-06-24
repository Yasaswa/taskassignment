package com.erp.CmCommonParameters.Repository;

import com.erp.CmCommonParameters.Model.CCmCommonParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface ICmCommonParametersRepository extends JpaRepository<CCmCommonParametersModel, Integer> {

	@Query(value = "FROM CCmCommonParametersModel model where model.is_delete =0 and model.common_parameters_id = ?1 and model.company_id = ?2")
	CCmCommonParametersModel FnShowParticularRecordForUpdate(int common_parameters_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_common_parameters WHERE common_parameters_name LIKE %?1% and company_id = ?2 and is_delete = 0")
	CCmCommonParametersModel checkIfNameExist(String common_parameters_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_common_parameters set is_delete = 1, deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP()  where common_parameters_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int common_parameters_id, int company_id, String deleted_by);


}
