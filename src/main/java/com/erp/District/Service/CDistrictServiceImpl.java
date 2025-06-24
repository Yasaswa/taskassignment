package com.erp.District.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.District.Model.CDistrictModel;
import com.erp.District.Model.CDistrictViewModel;
import com.erp.District.Repository.IDistrictRepository;
import com.erp.District.Repository.IDistrictViewRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Service
public class CDistrictServiceImpl implements IDistrictService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IDistrictRepository iDistrictRepository;

	@Autowired
	IDistrictViewRepository iDistrictViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CDistrictModel cDistrictModel) {
		JSONObject resp = new JSONObject();
		Optional<CDistrictModel> option = iDistrictRepository.findById(cDistrictModel.getDistrict_id());
		CDistrictModel MyModel = null;
		int company_id = cDistrictModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CDistrictModel json = iDistrictRepository.save(cDistrictModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("message", "Record updated successfully!..");
				resp.put("error", "");
				System.out.println("Record updated successfully!..");
				return resp;
			} else {
				CDistrictModel model = iDistrictRepository.getCheck(cDistrictModel.getDistrict_name());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "District Name is already exist!");
					System.out.println(" District  seveds successfully!..");
					return resp;
				} else {

					CDistrictModel json = iDistrictRepository.save(cDistrictModel);

					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record Added successfully!..");

					System.out.println("Record saved succesfully!..");
					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/district/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/district/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;
	}

	@Override
	public Object FnDeleteRecord(int district_id) {
		return iDistrictRepository.FnDeleteRecord(district_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CDistrictViewModel> data = iDistrictViewRepository.FnShowAllRecords(pageable);

			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

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

		return new Object[]{"", resp};

	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CDistrictViewModel> data = iDistrictViewRepository.FnShowAllActiveRecords(pageable);

			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

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

		return new Object[]{"", resp};

	}

	@Override
	public Object FnShowParticularRecordForUpdate(int district_id) {
		JSONObject resp = new JSONObject();
		try {

			CDistrictViewModel json = iDistrictViewRepository.FnShowParticularRecordForUpdate(district_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

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
	public Object FnShowParticularRecord(int company_id, int district_id) {
		JSONObject resp = new JSONObject();
		try {

			CDistrictViewModel json = iDistrictViewRepository.FnShowParticularRecord(company_id, district_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iDistrictViewRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

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

		return new Object[]{"", resp};
	}

}
