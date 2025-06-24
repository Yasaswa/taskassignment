package com.erp.HmDeductionHeads.Service;

import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsModel;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IHmDeductionHeadsService {

	Map<String, Object> FnAddUpdateRecord(CHmDeductionHeadsModel chmDeductionHeadsModel);

	Object FnDeleteRecord(int deduction_heads_id, int company_id, String deleted_by);

	Page<CHmDeductionHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int deduction_heads_id, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Page<CHmDeductionHeadsViewModel> FnShowParticularRecord(int deduction_heads_id, Pageable pageable, int company_id);

}
