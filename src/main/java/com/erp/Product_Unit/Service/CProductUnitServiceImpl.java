package com.erp.Product_Unit.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Unit.Model.CProductUnitModel;
import com.erp.Product_Unit.Model.CProductUnitViewModel;
import com.erp.Product_Unit.Repository.IProductUnitRepository;
import com.erp.Product_Unit.Repository.IProductUnitViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CProductUnitServiceImpl implements IProductUnitService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductUnitRepository iProductUnitRepository;

	@Autowired
	IProductUnitViewRepository iProductUnitViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CProductUnitModel cProductUnitModel) {
		Map<String, Object> resp = new HashMap<>();
		CProductUnitModel MyModel = null;
		int company_id = cProductUnitModel.getCompany_id();
		try {
			Optional<CProductUnitModel> option = iProductUnitRepository
					.findById(cProductUnitModel.getProduct_unit_id());

			if (option.isPresent()) {
				CProductUnitModel mymodel = iProductUnitRepository.save(cProductUnitModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("message", "Record updated succesfully!...");
			}

//			Check similar Product Make Name or short name is exist or not

			CProductUnitModel resultsProductUnitName = null;

			if (cProductUnitModel.getProduct_unit_short_name() == null
					|| cProductUnitModel.getProduct_unit_short_name().isEmpty()) {

				resultsProductUnitName = iProductUnitRepository.getCheck(cProductUnitModel.getProduct_unit_name(), null,
						cProductUnitModel.getCompany_id());
			} else {
				resultsProductUnitName = iProductUnitRepository.getCheck(cProductUnitModel.getProduct_unit_name(),
						cProductUnitModel.getProduct_unit_short_name(), cProductUnitModel.getCompany_id());
			}

			if (resultsProductUnitName != null) {
				resp.put("success", "0");
				resp.put("error", "Product Unit Name or Short Name is already exist!");
				return resp;
			} else {

				CProductUnitModel ProductUnitModel = iProductUnitRepository.save(cProductUnitModel);
				resp.put("success", "1");
				resp.put("data", ProductUnitModel);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {

				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productunit/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productunit/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_unit_id, int company_id, String deleted_by) {
		return iProductUnitRepository.FnDeleteRecord(product_unit_id, company_id, deleted_by);

	}

	@Override
	public Map<String, Object> FnShowAllRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CProductUnitViewModel> data = iProductUnitViewRepository.FnShowAllRecords(pageable);
			resp.put("data", data);
			resp.put("success", "1");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CProductUnitViewModel> data = iProductUnitViewRepository.FnShowAllActiveRecords(pageable);
			resp.put("data", data);
			resp.put("success", "1");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_unit_id, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CProductUnitViewModel json = iProductUnitViewRepository.FnShowParticularRecordForUpdate(product_unit_id,
					company_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Map<String, Object> FnShowParticularRecord(int company_id, int product_unit_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			CProductUnitViewModel json = iProductUnitViewRepository.FnShowParticularRecord(company_id, product_unit_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iProductUnitViewRepository.FnShowAllReportRecords(pageable);
	}

}
