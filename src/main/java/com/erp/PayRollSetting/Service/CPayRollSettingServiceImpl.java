package com.erp.PayRollSetting.Service;
import com.erp.PayRollSetting.Model.CPayRollModel;
import com.erp.PayRollSetting.Repository.IPayRollSettingRepository;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CPayRollSettingServiceImpl implements IPayRollSettingService {

    @Autowired
    private IPayRollSettingRepository ipayrollSettingRepository;


    @Override
    public CPayRollModel FnUpdateRecord(Long id, CPayRollModel updatedModel) {
        CPayRollModel existingModel = ipayrollSettingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payroll setting not found"));

        // Update fields
        existingModel.setPf_limit(updatedModel.getPf_limit());
        existingModel.setNight_allowance_days(updatedModel.getNight_allowance_days());
        existingModel.setCompany_id(updatedModel.getCompany_id());
        existingModel.set_delete(updatedModel.is_delete());
        existingModel.setAttendance_allowance_days(updatedModel.getAttendance_allowance_days());
        existingModel.setWorker_minimum_wages(updatedModel.getWorker_minimum_wages());
        existingModel.setShort_leave_hours(updatedModel.getShort_leave_hours());

        return ipayrollSettingRepository.save(existingModel);
    }


    @Override
    public List<Map<String, Object>> FnShowAllReportRecords() {
        return ipayrollSettingRepository.FnShowAllReportRecords();  // Call the repository method
   }

}