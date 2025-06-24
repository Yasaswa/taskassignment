package com.erp.PayRollSetting.Repository;

import com.erp.PayRollSetting.Model.CPayRollModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IPayRollSettingRepository extends JpaRepository<CPayRollModel, Long> {

    @Query(value = "SELECT * FROM hm_hrpayroll_settings", nativeQuery = true)
    List<Map<String, Object>> FnShowAllReportRecords();
}
