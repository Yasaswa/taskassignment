package com.erp.Taxation.Repository;

import com.erp.Taxation.Model.CTaxationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ITaxationRepository extends JpaRepository<CTaxationModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_taxation set is_delete = 1,deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where taxation_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int taxation_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update cm_taxation set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where taxation_id=?1", nativeQuery = true)
	void delete(int taxation_id);

	@Query(value = "SELECT * FROM cm_taxation WHERE tax_Applicable_on=?1", nativeQuery = true)
	CTaxationModel getCheck(String tax_Applicable_on);


	@Modifying
	@Transactional
	@Query(value = "update cm_taxation set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where taxtype_id=?1 and company_id=?2 and is_delete=0", nativeQuery = true)
	void updateTaxtypeActiveStatus(Integer taxtype_id, Integer company_id);
}
