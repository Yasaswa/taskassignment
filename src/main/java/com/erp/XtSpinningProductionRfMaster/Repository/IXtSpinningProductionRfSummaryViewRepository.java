package com.erp.XtSpinningProductionRfMaster.Repository;


import java.util.List;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfSummaryViewModel;

public interface IXtSpinningProductionRfSummaryViewRepository extends JpaRepository<CXtSpinningProductionRfSummaryViewModel, Integer>{

		
}
