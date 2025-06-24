package com.erp.RawMaterial.SmProductRmBomMst.Repository;

import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISmvProductRmBomDetailsRepository extends JpaRepository<CSmvProductRmBomDetails, Integer> {

	@Query("from CSmvProductRmBomDetails pbomdetails where pbomdetails.product_rm_bom_id = ?1")
	Page<CSmvProductRmBomDetails> FnShowParticularRecordForUpdate(int product_rm_bom_id, Pageable pageable);

}
