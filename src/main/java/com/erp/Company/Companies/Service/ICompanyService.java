/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.Company.Companies.Service;

import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.Company.Companies.Model.CCompanyViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ICompanyService {

	Map<String, Object> FnAddUpdateRecord(CCompanyModel cCompanyModel);

	Object FnDeleteRecord(int company_id);

	ArrayList<CCompanyViewModel> FnShowAllRecordsToExport();

	Page<CCompanyViewModel> FnShowAllActiveRecords(Pageable pageable);

	CCompanyViewModel FnShowParticularRecord(int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int company_id);


}
