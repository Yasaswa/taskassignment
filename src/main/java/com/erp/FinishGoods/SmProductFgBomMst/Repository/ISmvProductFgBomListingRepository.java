package com.erp.FinishGoods.SmProductFgBomMst.Repository;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmvProductFgBomListingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmvProductFgBomListingRepository extends JpaRepository<CSmvProductFgBomListingModel, Integer> {

	@Query("from CSmvProductFgBomListingModel pfgnomlist where pfgnomlist.product_parent_fg_id =?1")
	List<CSmvProductFgBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_fg_id);

	@Query("from CSmvProductFgBomListingModel pfgnomlist where pfgnomlist.product_parent_fg_id =?1")
	Page<CSmvProductFgBomListingModel> FnShowParticularRecords(String product_parent_fg_id, Pageable pageable);

}
