package com.erp.SmProductFgStockDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductFgStockDetails.Model.CSmProductFgStockDetailsModel;
import com.erp.SmProductFgStockDetails.Model.CSmProductFgStockSummaryModel;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgStockDetailsRepository;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgStockSummaryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CSmProductFgStockDetailsServiceImpl implements ISmProductFgStockDetailsService {

	@Autowired
	ISmProductFgStockSummaryRepository iSmProductFgStockSummaryRepository;

	@Autowired
	ISmProductFgStockDetailsRepository iSmProductFgStockDetailsRepository;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Override
	public Map<String, Object> FnUpdateScheduledFgStock() {
//		Get the current date
		Date currentDate = new Date();

//		Formatting the date to display only the date portion
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(currentDate);
		try {
//			Get FG Stock Summary data
			List<CSmProductFgStockSummaryModel> getAllStockMaterials = iSmProductFgStockSummaryRepository.getAllStockMaterials();
//			Get FG Stock Details data
			List<CSmProductFgStockDetailsModel> getAllStockDetail = iSmProductFgStockDetailsRepository.getAllStockDetail();

			List<CSmProductFgStockSummaryModel> addNewSummaryMaterialsBydate = new ArrayList<>();
			List<CSmProductFgStockDetailsModel> addNewStockDetailsMaterialsBydate = new ArrayList<>();

			getAllStockMaterials.stream().forEach(item -> {
				CSmProductFgStockSummaryModel modifiedItem = new CSmProductFgStockSummaryModel();
				BeanUtils.copyProperties(item, modifiedItem);
				modifiedItem.setOpening_quantity(item.getClosing_balance_quantity());
				modifiedItem.setOpening_weight(item.getClosing_balance_weight());
				modifiedItem.setClosing_balance_quantity(item.getClosing_balance_quantity());
				modifiedItem.setClosing_balance_weight(item.getClosing_balance_weight());
				modifiedItem.setPurchase_quantity(0);
				modifiedItem.setPurchase_weight(0);
				modifiedItem.setStock_transaction_id(0);
//				modifiedItem.setStock_date(todayDate);
				addNewSummaryMaterialsBydate.add(modifiedItem);
			});
//
			getAllStockDetail.stream().forEach(item -> {
				CSmProductFgStockDetailsModel modifiedDetails = new CSmProductFgStockDetailsModel();
				BeanUtils.copyProperties(item, modifiedDetails);
				modifiedDetails.setOpening_quantity(item.getClosing_balance_quantity());
				modifiedDetails.setOpening_weight(item.getClosing_balance_weight());
				modifiedDetails.setClosing_balance_quantity(item.getClosing_balance_quantity());
				modifiedDetails.setClosing_balance_weight(item.getClosing_balance_weight());
				modifiedDetails.setPurchase_quantity(0);
				modifiedDetails.setPurchase_weight(0);
				modifiedDetails.setStock_transaction_id(0);
				modifiedDetails.setStock_date(todayDate);
				addNewStockDetailsMaterialsBydate.add(modifiedDetails);
			});
//
			if (!addNewSummaryMaterialsBydate.isEmpty()) {
				//changes by dipti (because of day_closed not present in table sm_product_fg_stock_summary)
//				iSmProductFgStockSummaryRepository.FnUpdateStockDayClosed();
				iSmProductFgStockSummaryRepository.saveAll(addNewSummaryMaterialsBydate);
			} else {
				System.out.println("Finish Good stock summary is empty!...");
			}

//
			if (!addNewStockDetailsMaterialsBydate.isEmpty()) {
				iSmProductFgStockDetailsRepository.FnUpdateStockDetailsDayClosed();
				iSmProductFgStockDetailsRepository.saveAll(addNewStockDetailsMaterialsBydate);
			} else {
				System.out.println("Finish Good stock details is empty!...");
			}


			System.out.println("Finish Good Stock updated succesfully!....." + " " + todayDate);
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(1, "api",
						"Finish Good Stock Updation scheduler", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				System.out.println("Finish Good  Stock updation failed!....." + " " + todayDate);
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(1, "api",
					"Finish Good Stock Updation scheduler", e.hashCode(), e.getMessage());

		}

		return null;
	}
}
