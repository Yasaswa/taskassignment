package com.erp.Product_Unit.Service;

import com.erp.Product_Unit.Model.CProductUnitModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IProductUnitService {

	Map<String, Object> FnAddUpdateRecord(CProductUnitModel cProductUnitModel);

	Object FnDeleteRecord(int product_unit_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllRecords(Pageable pageable);

	Map<String, Object> FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_unit_id, int company_id);

	Map<String, Object> FnShowParticularRecord(int company_id, int product_unit_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
