package com.erp.RawMaterial.SmProductRmBomMst.Repository;

import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomListingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmvProductRmBomListingRepository extends JpaRepository<CSmvProductRmBomListingModel, Integer> {

	@Query("from CSmvProductRmBomListingModel pdm where pdm.product_parent_rm_id =?1")
	List<CSmvProductRmBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_rm_id);

	@Query("from CSmvProductRmBomListingModel pdm where pdm.product_parent_rm_id =?1")
	Page<CSmvProductRmBomListingModel> FnShowParticularRecords(String product_parent_rm_id, Pageable pageable);
}
