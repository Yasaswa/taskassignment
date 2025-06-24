package com.erp.FinishGoods.SmProductFgProcess.Repository;

import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductFgProcessViewRepository extends JpaRepository<CSmProductFgProcessViewModel, Integer> {

	@Query(value = "Select * FROM smv_product_fg_process", nativeQuery = true)
	List<CSmProductFgProcessViewModel> FnShowAllActiveRecords();


}
