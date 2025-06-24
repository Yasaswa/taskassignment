package com.erp.Product_Grade.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category5.Model.CProductCategory5Model;
import com.erp.Product_Grade.Model.CProductGradeModel;
import com.erp.Product_Grade.Model.CProductGradeRptModel;
import com.erp.Product_Grade.Model.CProductGradeViewModel;
import com.erp.Product_Grade.Repository.IProductGradeRepository;
import com.erp.Product_Grade.Repository.IProductGradeRptRepository;
import com.erp.Product_Grade.Repository.IProductGradeViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;


@Service
public class CProductGradeServiceImpl implements IProductGradeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;


	@Autowired
	IProductGradeRepository iProductGradeRepository;

	@Autowired
	IProductGradeViewRepository iProductGradeViewRepository;

	@Autowired
	IProductGradeRptRepository iProductGradeRptRepository;


	@Override
	public JSONObject FnAddUpdateRecord(CProductGradeModel cProductGradeModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();

		Optional<CProductGradeModel> option = iProductGradeRepository.findById(cProductGradeModel.getProduct_grade_id());
		CProductGradeModel MyModel = null;
		int company_id = cProductGradeModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CProductGradeModel productGradeModel = iProductGradeRepository.save(cProductGradeModel);
				String json = objectMapper.writeValueAsString(productGradeModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");

				System.out.println(" Product Grade Master  updated successfully!..");

				return resp;
			} else {
				
				//Check similar Product Grade Name and short name is exist or not
				CProductGradeModel resultsProductGradeName = null;
				if (cProductGradeModel.getProduct_grade_short_name() == null || cProductGradeModel.getProduct_grade_short_name().isEmpty()) {
					resultsProductGradeName = iProductGradeRepository.getCheck(cProductGradeModel.getProduct_grade_name(),
							null, cProductGradeModel.getCompany_id());
				} else {
					resultsProductGradeName = iProductGradeRepository.getCheck(cProductGradeModel.getProduct_grade_name(),
							cProductGradeModel.getProduct_grade_short_name(),cProductGradeModel.getCompany_id());
				}

				if (resultsProductGradeName != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Product Grade Name or Short Name is already exist!");
					return resp;
				}else {

					CProductGradeModel productGradeModel = iProductGradeRepository.save(cProductGradeModel);
					String json = objectMapper.writeValueAsString(productGradeModel);

					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");

					System.out.println("Product  Grade Master  added successfully!..");

					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productgrade/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}
		} catch (Exception e) {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productgrade/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_grade_id) {
		return iProductGradeRepository.FnDeleteRecord(product_grade_id);
	}

	/* Added By Dipti (ERP DB Testing 1.1) */

//	@Override
//	public JSONObject FnAddUpdateRecord(CProductMaterialGradeModel cProductMaterialGradeModel) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		Optional<CProductMaterialGradeModel> option = iProductGradeRepository.findById(cProductMaterialGradeModel.getProduct_material_grade_id());
//		CProductMaterialGradeModel MyModel = null;
//		int company_id = cProductMaterialGradeModel.getCompany_id();
//		try {
//			if(option.isPresent()) {
//				CProductMaterialGradeModel productGradeModel  = iProductGradeRepository.save(cProductMaterialGradeModel);
//				String json = objectMapper.writeValueAsString(productGradeModel);
//				resp.put("success", "1");
//				resp.put("data", json);
//				resp.put("error", "");
//				
//				System.out.println(" Product Grade Master  updated successfully!..");
//				
//				return resp;
//			}else {
//				CProductMaterialGradeModel model = iProductGradeRepository.getCheck(cProductMaterialGradeModel.getProduct_material_grade_name());
//			    
//				if(model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", "Product  Grade Master  Name is already exist!");
//					
//					return resp;
//				}else {
//					
//					CProductMaterialGradeModel productGradeModel = iProductGradeRepository.save(cProductMaterialGradeModel);
//					String json = objectMapper.writeValueAsString(productGradeModel);
//					
//					resp.put("success", "1");
//					resp.put("data", json);
//					resp.put("error", "");
//					
//					System.out.println("Product  Grade Master  added successfully!..");
//					
//					return resp;
//				}
//			}
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/productgrade/FnAddUpdateRecord", sqlEx.getErrorCode(),sqlEx.getMessage());
//               	System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//				return resp;
//
//			}
//		} catch (Exception e) {
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id,"api", "/api/productgrade/FnAddUpdateRecord", 0, e.getMessage());
//            resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//
//			return resp;
//		}
//
//		return resp;
//		
//	}


//	@Override
//	public Object FnDeleteRecord(int product_material_grade_id) {
//		return iProductGradeRepository.FnDeleteRecord(product_material_grade_id);
//	}
//	
	/* Added By Dipti (ERP DB Testing 1.1) */


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductGradeViewModel> data = iProductGradeViewRepository.FnShowAllRecords(pageable);
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

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductGradeViewModel> data = iProductGradeViewRepository.FnShowAllActiveRecords(pageable);
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


	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductGradeRptModel> data = iProductGradeRptRepository.FnShowAllReportRecords(pageable);
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

	@Override
	public JSONObject FnShowParticularRecord(int company_id, int product_grade_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductGradeViewModel json = iProductGradeViewRepository.FnShowParticularRecord(company_id, product_grade_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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




	/* Added By Dipti (ERP DB Testing 1.1) */

//	@Override
//	public JSONObject FnShowParticularRecord(int company_id, int product_material_grade_id) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//
//			CProductGradeViewModel json = iProductGradeViewRepository.FnShowParticularRecord(company_id, product_material_grade_id);
//			String json1 = objectMapper.writeValueAsString(json);
//
//			resp.put("success", "1");
//			resp.put("data", json1);
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resp;
//	}

	/* Added By Dipti (ERP DB Testing 1.1) */


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_rm_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductGradeViewModel json = iProductGradeViewRepository.FnShowParticularRecordForUpdate(product_rm_id);
			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
			resp.put("data", json1);
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


	/* Added By Dipti (ERP DB Testing 1.1) */

//	@Override
//	public JSONObject FnShowParticularRecordForUpdate(int product_material_grade_id) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//
//			CProductGradeViewModel json = iProductGradeViewRepository.FnShowParticularRecordForUpdate(product_material_grade_id);
//			String json1 = objectMapper.writeValueAsString(json);
//			resp.put("success", "1");
//			resp.put("data", json1);
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resp;
//	}


	/* Added By Dipti (ERP DB Testing 1.1) */

}
