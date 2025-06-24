package com.erp.PayRollSetting.Service;
import com.erp.PayRollSetting.Model.CPayRollModel;
import java.util.List;
import java.util.Map;

public interface IPayRollSettingService {

    List<Map<String, Object>> FnShowAllReportRecords();

    CPayRollModel FnUpdateRecord(Long id, CPayRollModel updatedModel);

}

