package com.erp.SmProductProcess.Repository;

import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPmProductProcessViewRepository extends JpaRepository<CPmProductProcessViewModel, Integer> {

	@Query(value = "FROM CPmProductProcessViewModel cdvm where cdvm.is_delete =0  order by cdvm.product_process_id Desc")
	Page<CPmProductProcessViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CPmProductProcessViewModel cdvm where cdvm.is_delete =0 and cdvm.product_process_id = ?1 order by cdvm.product_process_id Desc")
	Page<CPmProductProcessViewModel> FnShowParticularRecord(int product_process_id, Pageable pageable);

	@Query(value = "FROM CPmProductProcessViewModel model where model.is_delete =0 and company_id=?1 order by model.product_process_id Desc")
	List<CPmProductProcessViewModel> FnShowProductSrProcessActiveRecords(int company_id);


	@Query(value = "FROM CPmProductProcessViewModel model where model.is_delete=0 and model.company_id = ?1")
	List<CPmProductProcessViewModel> FnShowAllProductProcessRecords(int company_id);

	@Query(value = "SELECT * FROM smv_product_process_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
