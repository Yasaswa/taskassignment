package com.erp.HmEarningHeads.Service;

import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IHmEarningHeadsService {

	Map<String, Object> FnAddUpdateRecord(CHmEarningHeadsModel cHmEarningHeadsModel);

	Object FnDeleteRecord(int earning_heads_id, int company_id, String deleted_by);

	Page<CHmEarningHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	//
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int earning_heads_id, int company_id);

	Page<CHmEarningHeadsViewModel> FnShowParticularRecord(int earning_heads_id, Pageable pageable, int company_id);

}
