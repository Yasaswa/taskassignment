package com.erp.FinishGoods.SmProductFg.Repository;

import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISmProductFgViewRepository extends JpaRepository<CSmProductFgViewModel, Integer> {

	@Query(value = "FROM CSmProductFgViewModel cdvm where cdvm.is_delete =0  order by cdvm.product_fg_id Desc")
	Page<CSmProductFgViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CSmProductFgViewModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1 order by cdvm.product_fg_id Desc")
	Page<CSmProductFgViewModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);


}
