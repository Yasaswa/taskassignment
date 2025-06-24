package com.erp.HtShortLeave.Service;

import com.erp.HtShortLeave.Model.CHtShortLeaveModel;
import org.springframework.stereotype.Service;

import java.util.Map;
public interface IHtShortLeaveService {
    Map<String, Object> FnAddUpdateRecord(CHtShortLeaveModel cHtShortLeaveModel, boolean isApprove);

    Map<String, Object> FnDeleteRecord(int short_leave_transaction_id, String deleted_by);

    Map<String, Object> FnShowParticularRecordForUpdate(int shortLeaveTransactionId, int companyId);
}
