package com.erp.XmSpinningProdCount.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmSpinningProdCount.Model.CXmSpinningProdCountLevelsViewModel;

public interface IXmSpinningProdCountLevelsViewRepository extends JpaRepository<CXmSpinningProdCountLevelsViewModel, Integer>{

	
	@Query(value = "FROM CXmSpinningProdCountLevelsViewModel model where model.is_delete = 0 and model.production_count_id = ?1 and model.company_id = ?2")
	List<CXmSpinningProdCountLevelsViewModel> FnShowSpinningProdCountLevelsForUpdate(int production_count_id,
			int company_id);

	
}
