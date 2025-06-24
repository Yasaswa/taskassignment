package com.erp.Common.MasterData.Repository;

import com.erp.Common.MasterData.Model.CMasterDataViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMasterDataViewRepository extends JpaRepository<CMasterDataViewModel, Integer> {

	@Query(value = "Select field_id, field_name from ?1 where is_delete =0", nativeQuery = true)
	List<String> FnShowParticularRecord(String view);

}
