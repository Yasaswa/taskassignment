package com.erp.PtGoodsReturnFabricDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReceiptDetails.Model.*;
import com.erp.PtGoodsReturnFabricDetails.Model.*;
import com.erp.PtGoodsReturnFabricDetails.Repository.*;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsPaymentTermsModel;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CPtGoodsReturnFabricServiceImpl implements IPtGoodsReturnFabricService {
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IPtGoodsReturnFabricDetailsRepository iPtGoodsReturnFabricDetailsRepository;

    @Autowired
    IPtGoodsReturnFabricMasterRepository iPtGoodsReturnFabricMasterRepository;

    @Autowired
    IPtGoodsReturnFabricPaymentTermRepository iPtGoodsReturnFabricPaymentTermRepository;

    @Autowired
    IPtGoodsReturnFabricTaxSummaryRepository iPtGoodsReturnFabricTaxSummaryRepository;

    @Autowired
    IPtGoodsReturnFabricRollsDetailsRepository iPtGoodsReturnFabricRollsDetailsRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject GoodsReturnFabricMaster = jsonObject.getJSONObject("GoodsReturnMasterDetails");
        JSONArray GoodsReturnFabricDetails = (JSONArray) jsonObject.get("GoodsReturnFabricDetails");
        JSONArray GoodsReturnFabricPaymentTermArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
        JSONArray GoodReturnTaxSummaryArray = (JSONArray) jsonObject.get("TransTaxSummaryData");
        JSONArray GoodsReturnRollsDetailsArray = (JSONArray) jsonObject.get("TransRollsDetailsData");
        int company_id = GoodsReturnFabricMaster.getInt("company_id");
        String goods_return_fabric_no = GoodsReturnFabricMaster.getString("goods_return_fabric_no");
        int goods_return_fabric_master_id=GoodsReturnFabricMaster.getInt("goods_return_fabric_master_id");
        String goods_return_fabric_status = GoodsReturnFabricMaster.getString("goods_return_fabric_status");

        try {
            CPtGoodsReturnFabricMasterModel cPtGoodsReturnsMasterModel = objectMapper
                    .readValue(GoodsReturnFabricMaster.toString(), CPtGoodsReturnFabricMasterModel.class);

            CPtGoodsReturnFabricMasterModel respGoodsReturnFabricMasterData = iPtGoodsReturnFabricMasterRepository.save(cPtGoodsReturnsMasterModel);

            List<CPtGoodsReturnFabricDetailsModel> cPtGoodsReturnFabricDetailsModels = objectMapper.readValue(GoodsReturnFabricDetails.toString(),
                    new TypeReference<List<CPtGoodsReturnFabricDetailsModel>>() {
                    });

            cPtGoodsReturnFabricDetailsModels.forEach(item -> {
                item.setGoods_return_fabric_master_id(respGoodsReturnFabricMasterData.getGoods_return_fabric_master_id());
            });

            if (goods_return_fabric_master_id != 0) {

                List<Integer> distinctGoodsReturnFabricMasterIds = cPtGoodsReturnFabricDetailsModels.parallelStream()
                        .map(CPtGoodsReturnFabricDetailsModel::getGoods_return_fabric_master_id).distinct()
                        .collect(Collectors.toList());

                iPtGoodsReturnFabricDetailsRepository.updateGoodsReturnFabricRecords(distinctGoodsReturnFabricMasterIds);
            }


            iPtGoodsReturnFabricDetailsRepository.saveAll(cPtGoodsReturnFabricDetailsModels);
            if (!GoodsReturnFabricPaymentTermArray.isEmpty() && "P".equals(goods_return_fabric_status)) {
                iPtGoodsReturnFabricPaymentTermRepository.updatePaymentTermsStatus(goods_return_fabric_master_id,
                        GoodsReturnFabricMaster.getInt("goods_return_fabric_version"), company_id);

                List<CPtGoodsReturnFabricPaymentTermModel> tradingOrderPaymentTerms = objectMapper.readValue(
                        GoodsReturnFabricPaymentTermArray.toString(),
                        new TypeReference<List<CPtGoodsReturnFabricPaymentTermModel>>() {
                        });
                tradingOrderPaymentTerms.forEach(paymentTerm -> {
                    paymentTerm.setGoods_return_fabric_version(respGoodsReturnFabricMasterData.getGoods_return_fabric_version());
                    paymentTerm.setGoods_return_fabric_master_id(respGoodsReturnFabricMasterData.getGoods_return_fabric_master_id());
                });
                iPtGoodsReturnFabricPaymentTermRepository.saveAll(tradingOrderPaymentTerms);

            }
            if (!GoodReturnTaxSummaryArray.isEmpty() && "P".equals(goods_return_fabric_status)) {
//			 Goods Receipts Notes Tax Summary
                iPtGoodsReturnFabricTaxSummaryRepository.updateStatus(goods_return_fabric_no,
                        GoodsReturnFabricMaster.getInt("goods_return_fabric_version"), respGoodsReturnFabricMasterData.getFinancial_year(), company_id);

                List<CPtGoodsReturnFabricTaxSummaryModel> cPtGoodsReturnFabricTaxSummaryModels = objectMapper
                        .readValue(GoodReturnTaxSummaryArray.toString(),
                                new TypeReference<List<CPtGoodsReturnFabricTaxSummaryModel>>() {
                                });
                cPtGoodsReturnFabricTaxSummaryModels.forEach(items -> {
                    items.setGoods_return_fabric_version(cPtGoodsReturnsMasterModel.getGoods_return_fabric_version());
                    items.setGoods_return_fabric_master_id(
                            respGoodsReturnFabricMasterData.getGoods_return_fabric_master_id());
                });

                iPtGoodsReturnFabricTaxSummaryRepository.saveAll(cPtGoodsReturnFabricTaxSummaryModels);

            }
			 if (!GoodsReturnRollsDetailsArray.isEmpty() && "P".equals(goods_return_fabric_status)) {
                 iPtGoodsReturnFabricRollsDetailsRepository.updateStatus(goods_return_fabric_no,
                         GoodsReturnFabricMaster.getInt("goods_return_fabric_version"), respGoodsReturnFabricMasterData.getFinancial_year(), company_id);

                 List<CPtGoodsReturnFabricRollsDatailsModel> cPtGoodsReturnFabricRollsDatailsModels = objectMapper.readValue(
                            GoodsReturnRollsDetailsArray.toString(), new TypeReference<List<CPtGoodsReturnFabricRollsDatailsModel>>() {
							});
                 cPtGoodsReturnFabricRollsDatailsModels.forEach(items -> {
						items.setGoods_return_fabric_version(respGoodsReturnFabricMasterData.getGoods_return_fabric_version());
						items.setGoods_return_fabric_master_id(
                                respGoodsReturnFabricMasterData.getGoods_return_fabric_master_id());
					});
					iPtGoodsReturnFabricRollsDetailsRepository.saveAll(cPtGoodsReturnFabricRollsDatailsModels);

				}
            if ("A".equals(goods_return_fabric_status)) {
                //				UPDATE STOCK DETAILS
            Map<String, Object> goodsStockResponse = FnAddUpdateGoodsStockDetails(respGoodsReturnFabricMasterData, GoodsReturnFabricDetails, GoodsReturnRollsDetailsArray); // STOCK ISSUE
                responce.put("stockResponse", goodsStockResponse);
            }

            responce.put("success", 1);
            responce.put("data", respGoodsReturnFabricMasterData);
            responce.put("error", "");
//            responce.put("message", goods_return_master_id_atomic.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");
            if ("A".equals(goods_return_fabric_status)) {
                responce.put("message", "Record Approved successfully!...");
            } else if (goods_return_fabric_master_id == 0) {
                responce.put("message", "Record added successfully!...");
            } else {
                responce.put("message", "Record updated successfully!...");
            }
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;

    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goods_return_fabric_no, int goods_return_fabric_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> goodsReturnMasterRecords = iPtGoodsReturnFabricMasterRepository
                    .FnShowGoodsReturnMasterRecord(goods_return_fabric_no, goods_return_fabric_version, financial_year,
                            company_id);

            List<Map<String, Object>> goodsReturnDetailsRecords = iPtGoodsReturnFabricDetailsRepository
                    .FnShowGoodsReturnDetailsRecords(goods_return_fabric_no, goods_return_fabric_version, financial_year,
                            company_id);

            List<CPtGoodsReturnFabricTaxSummaryModel> goodsReturnTaxSummaryRecords = iPtGoodsReturnFabricTaxSummaryRepository
                    .FnShowGoodsReturnTaxSummaryRecords(goods_return_fabric_no, goods_return_fabric_version, financial_year,
                            company_id);

            List<CPtGoodsReturnFabricPaymentTermModel> goodsReturnPaymentTermsRecords = iPtGoodsReturnFabricPaymentTermRepository
                    .FnShowGoodsReturnPaymentTermsRecords(goods_return_fabric_no, goods_return_fabric_version, financial_year,
                            company_id);
            List<CPtGoodsReturnFabricRollsDatailsModel> goodsReturnRollDetailsRecords = iPtGoodsReturnFabricRollsDetailsRepository
                    .FnShowGoodsReturnRollDetailsRecords(goods_return_fabric_no, goods_return_fabric_version, financial_year,
                    company_id);

            responce.put("GoodsReturnMasterRecord", goodsReturnMasterRecords);
            responce.put("GoodsReturnDetailsRecords", goodsReturnDetailsRecords);
            responce.put("GoodsReturnTaxSummaryRecords", goodsReturnTaxSummaryRecords);
                responce.put("GoodsReturnPaymentTermsRecords", goodsReturnPaymentTermsRecords);
            responce.put("GoodsReturnRollDetailsRecords", goodsReturnRollDetailsRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }


    private Map<String, Object> FnAddUpdateGoodsStockDetails(CPtGoodsReturnFabricMasterModel cPtGoodsReturnFabricMasterModel, JSONArray goodsFabricDetailsList, JSONArray goodsReturnFabricRollDetails) {

        Map<String, Object> batchresponse = new HashMap<>();
        Map<String, Object> stockDetails = new HashMap<>();
        Map<String, Object> responce = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();
        List<CPtGoodsReturnFabricRollsDatailsModel> itemList = new ArrayList<>();
        try {
            itemList = objectMapper.readValue(
                    goodsReturnFabricRollDetails.toString(),
                    new TypeReference<List<CPtGoodsReturnFabricRollsDatailsModel>>() {
                    }
            );

//		Create list to add object to save stock details & Summary
            List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
            List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

            for (CPtGoodsReturnFabricRollsDatailsModel goodsReturnRollwiseData : itemList) {

                String materialId = goodsReturnRollwiseData.getProduct_material_id();
                String targetSortNo = goodsReturnRollwiseData.getSort_no(); // sort_no as String


                CPtGoodsReturnFabricDetailsModel matchedItem = null;

                for (int i = 0; i < goodsFabricDetailsList.length(); i++) {
                    JSONObject obj = goodsFabricDetailsList.getJSONObject(i);
                    String currentSortNo = obj.getString("sort_no"); // get sort_no as String

                    if (currentSortNo.equals(targetSortNo)) {
                        matchedItem = objectMapper.readValue(obj.toString(), CPtGoodsReturnFabricDetailsModel.class);
                        break;
                    }
                }
                int unitId = 0;
                int packingId = 0;
                int godownId = 0;
                int godownSectionId = 0;
                int godownSectionBeanId = 0;
                double productRate = 0.0;
                if (matchedItem != null) {
                    unitId = matchedItem.getProduct_material_unit_id();
                    packingId = matchedItem.getProduct_material_packing_id();
                    godownId = matchedItem.getGodown_id();
                    godownSectionId = matchedItem.getGodown_section_id();
                    godownSectionBeanId = matchedItem.getGodown_section_beans_id();
                    productRate = matchedItem.getMaterial_rate();
                }


                // Get updated values
                double goodsReturnMtr = goodsReturnRollwiseData.getGoods_return_roll_mtr();
                double goodsReturnWt = goodsReturnRollwiseData.getGoods_return_roll_weight();
                Integer goodsReturnNoOfRolls = 1;

                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
                final int finalGodownId = godownId;

                Optional<CSmProductRmStockSummaryModel> checkTheMaterialAlreadyExist = addProductRmStockSummaryList.stream()
                        .filter(item -> item.getProduct_rm_id().equals(materialId) &&
                                item.getGodown_id().equals(finalGodownId)).findFirst();

                if (checkTheMaterialAlreadyExist.isPresent()) {
                    productRmStockSummaryModel = checkTheMaterialAlreadyExist.get();

                    productRmStockSummaryModel.setClosing_balance_quantity(productRmStockSummaryModel.getClosing_balance_quantity() + goodsReturnMtr);
                    productRmStockSummaryModel.setClosing_balance_weight(productRmStockSummaryModel.getClosing_balance_weight() + goodsReturnWt);
                    productRmStockSummaryModel.setClosing_no_of_boxes(productRmStockSummaryModel.getClosing_no_of_boxes() + goodsReturnNoOfRolls);
                    productRmStockSummaryModel.setPurchase_no_of_boxes(productRmStockSummaryModel.getPurchase_no_of_boxes() + goodsReturnNoOfRolls);
                    productRmStockSummaryModel.setPurchase_quantity(productRmStockSummaryModel.getPurchase_quantity() + goodsReturnMtr);
                    productRmStockSummaryModel.setPurchase_weight(productRmStockSummaryModel.getPurchase_weight() + goodsReturnWt);
                } else {
                    productRmStockSummaryModel.setCompany_id(goodsReturnRollwiseData.getCompany_id());
                    productRmStockSummaryModel.setCompany_branch_id(goodsReturnRollwiseData.getCompany_branch_id());
                    productRmStockSummaryModel.setFinancial_year(goodsReturnRollwiseData.getFinancial_year());
                    productRmStockSummaryModel.setProduct_type_group(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_type());
                    productRmStockSummaryModel.setProduct_type_id(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_type_id());
                    productRmStockSummaryModel.setProduct_rm_id(materialId);
                    productRmStockSummaryModel.setProduct_material_unit_id(unitId);
                    productRmStockSummaryModel.setProduct_material_packing_id(packingId);
                    productRmStockSummaryModel.setMaterial_rate(productRate);

                    productRmStockSummaryModel.setClosing_no_of_boxes(goodsReturnNoOfRolls);
                    productRmStockSummaryModel.setClosing_balance_quantity(goodsReturnMtr);
                    productRmStockSummaryModel.setClosing_balance_weight(goodsReturnWt);
                    productRmStockSummaryModel.setPurchase_no_of_boxes(goodsReturnNoOfRolls);
                    productRmStockSummaryModel.setPurchase_quantity(goodsReturnMtr);
                    productRmStockSummaryModel.setPurchase_weight(goodsReturnWt);

                    productRmStockSummaryModel.setGodown_id(godownId);
                    productRmStockSummaryModel.setGodown_section_id(godownSectionId);
                    productRmStockSummaryModel.setGodown_section_beans_id(godownSectionBeanId);
                    productRmStockSummaryModel.setCreated_by(goodsReturnRollwiseData.getCreated_by());
                    productRmStockSummaryModel.setModified_by(goodsReturnRollwiseData.getCreated_by());
                    productRmStockSummaryModel.setModified_on(goodsReturnRollwiseData.getModified_on());

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                }

//			smv_product_rm_stock_details
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

                String batchNo = (goodsReturnRollwiseData.getStyle() == null || goodsReturnRollwiseData.getStyle().isEmpty())
                        ? goodsReturnRollwiseData.getSort_no()
                        : goodsReturnRollwiseData.getSort_no() + "-" + goodsReturnRollwiseData.getStyle();

                Optional<CSmProductRmStockDetailsModel> checkTheMaterialDertailAlreadyExist = addProductRmStockDetailsList.stream()
                        .filter(item -> item.getProduct_rm_id().equals(goodsReturnRollwiseData.getProduct_material_id()) &&
                                item.getGodown_id().equals(finalGodownId) &&
                                item.getBatch_no().equals(batchNo) &&
                                item.getGoods_receipt_no().equals(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_no())).findFirst();

                if (checkTheMaterialDertailAlreadyExist.isPresent()) {
                    productRmStockDetailsModel = checkTheMaterialDertailAlreadyExist.get();
                    productRmStockDetailsModel.setClosing_no_of_boxes(productRmStockDetailsModel.getClosing_no_of_boxes() + goodsReturnNoOfRolls);
                    productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + goodsReturnMtr);
                    productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + goodsReturnWt);
                    productRmStockDetailsModel.setPurchase_no_of_boxes(productRmStockDetailsModel.getPurchase_no_of_boxes() + goodsReturnNoOfRolls);
                    productRmStockDetailsModel.setPurchase_quantity(productRmStockDetailsModel.getPurchase_quantity() + goodsReturnMtr);
                    productRmStockDetailsModel.setPurchase_weight(productRmStockDetailsModel.getPurchase_weight() + goodsReturnWt);
                } else {
                    productRmStockDetailsModel.setCompany_id(goodsReturnRollwiseData.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(goodsReturnRollwiseData.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(goodsReturnRollwiseData.getFinancial_year());
                    productRmStockDetailsModel.setProduct_type_group(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_type());
                    productRmStockDetailsModel.setProduct_type_id(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_type_id());
                    productRmStockDetailsModel.setProduct_rm_id(goodsReturnRollwiseData.getProduct_material_id());
                    productRmStockDetailsModel.setProduct_material_unit_id(unitId);
                    productRmStockDetailsModel.setProduct_material_packing_id(packingId);
                    productRmStockDetailsModel.setBatch_rate(productRate);
                    productRmStockDetailsModel.setStock_date(cPtGoodsReturnFabricMasterModel.getGoods_return_fabric_date());

                    productRmStockDetailsModel.setGoods_receipt_no(goodsReturnRollwiseData.getGoods_return_fabric_no());
                    productRmStockDetailsModel.setBatch_no(batchNo);
                    productRmStockDetailsModel.setPurchase_no_of_boxes(goodsReturnNoOfRolls);
                    productRmStockDetailsModel.setPurchase_quantity(goodsReturnMtr);
                    productRmStockDetailsModel.setPurchase_weight(goodsReturnWt);
                    productRmStockDetailsModel.setClosing_no_of_boxes(goodsReturnNoOfRolls);
                    productRmStockDetailsModel.setClosing_balance_quantity(goodsReturnMtr);
                    productRmStockDetailsModel.setClosing_balance_weight(goodsReturnWt);
                    productRmStockDetailsModel.setGodown_id(godownId);
                    productRmStockDetailsModel.setCreated_by(goodsReturnRollwiseData.getCreated_by());
                    productRmStockDetailsModel.setModified_by(goodsReturnRollwiseData.getCreated_by());

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                }
            }


            stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
            stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
            batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Increase", cPtGoodsReturnFabricMasterModel.getCompany_id());
//            batchresponse.put("FGStockAddDetails", batchresponse);
            responce.put("FGStockAddDetails", batchresponse);

            return responce;
//
//
//            System.out.println("batchresponse" + batchresponse);
//            return batchresponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            batchresponse.put("error", "Invalid JSON input format");
            return batchresponse;
            // handle or log error appropriately
        }
    }
}
