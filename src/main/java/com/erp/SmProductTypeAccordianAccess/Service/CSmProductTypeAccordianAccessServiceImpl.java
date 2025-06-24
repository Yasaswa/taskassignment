package com.erp.SmProductTypeAccordianAccess.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductTypeAccordianAccess.Model.CSmProductTypeAccordianAccessModel;
import com.erp.SmProductTypeAccordianAccess.Repository.ISmProductTypeAccordianAccessRepository;

@Service
public class CSmProductTypeAccordianAccessServiceImpl implements ISmProductTypeAccordianAccessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductTypeAccordianAccessRepository iSmProductTypeAccordianAccessRepository;

	@Override
	public Map<String, Object> FnShowAllActiveRecords(String product_type_short_name, int modules_forms_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			List<Map<String, Object>>  productTypeAccordianAccessData  =
					iSmProductTypeAccordianAccessRepository.FnShowProductTypeAccordianAccessDetails(product_type_short_name,modules_forms_id);
	
			responce.put("ProductTypeAccordianAccessData", productTypeAccordianAccessData);
			responce.put("success", 1);
			
			}  catch (DataAccessException e) {
				if (e.getRootCause() instanceof SQLException) {
					SQLException sqlEx = (SQLException) e.getRootCause();
					System.out.println(sqlEx.getMessage());
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", e.getMessage());
					return responce;
				}
	
			} catch (Exception e) {
				e.printStackTrace();
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
	            return responce;
			}
			
			return responce;
		}

	
	}
