package com.erp.Destinations.Repository;

import com.erp.Destinations.Model.CDestinationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IDestinationsRepository extends JpaRepository<CDestinationsModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_destination set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where destination_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int destination_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_destination WHERE destination_name = ?1 and company_id = ?2")
	CDestinationsModel getCheck(String destination_name, int company_id);

}
