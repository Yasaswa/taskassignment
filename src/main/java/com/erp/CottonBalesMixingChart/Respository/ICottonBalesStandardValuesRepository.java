package com.erp.CottonBalesMixingChart.Respository;

import com.erp.CottonBalesMixingChart.Model.CMixingChartStandardValuesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICottonBalesStandardValuesRepository extends JpaRepository<CMixingChartStandardValuesModel, Integer> {

    @Query(value = "SELECT * FROM pt_mixing_chart_standard_values WHERE company_id = ?1 AND mixing_chart_no = ?2 AND is_delete = 0 LIMIT 1", nativeQuery = true)
    CMixingChartStandardValuesModel FnGetMixingChartStandardValues(Integer companyId, String mixingChartNo);

}
