package com.erp.XmSpinningProdCount.Repository;

import com.erp.XmSpinningProdCount.Model.CxmSpinningProdCountViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IXmSpinningProdCountViewRepository extends JpaRepository<CxmSpinningProdCountViewModel, Integer> {

	@Query(value = "FROM CxmSpinningProdCountViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_count_id Desc")
	Page<CxmSpinningProdCountViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CxmSpinningProdCountViewModel model where model.is_delete =0 and model.production_count_id = ?1 and model.company_id = ?2 order by model.production_count_id Desc")
	Page<CxmSpinningProdCountViewModel> FnShowParticularRecord(int production_count_id, int company_id,
	                                                           Pageable pageable);

	@Query(value = "SELECT * FROM xmv_spinning_prod_count_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
