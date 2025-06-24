package com.erp.StIndentIssueMaster.Repository;

import com.erp.StIndentIssueMaster.Model.CStIndentMaterialIssueMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStIndentMaterialIssueMasterViewRepository extends JpaRepository<CStIndentMaterialIssueMasterViewModel, Integer> {

}
