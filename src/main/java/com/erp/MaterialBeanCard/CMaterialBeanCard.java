package com.erp.MaterialBeanCard;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api/MaterialBeanCard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMaterialBeanCard {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/getMaterialTransactions/{company_id}")
    public Map<String, Object> MaterialTransactions(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable Integer company_id) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        try {
            String material_id = commonIdsObj.getString("material_id");
            String from_date = commonIdsObj.getString("from_date");
            String to_date = commonIdsObj.getString("to_date");

            // Define the stored procedure cal
            List<Map<String, Object>> materialTransactionByProcedure = CallGetMaterialTransactionsByDate(material_id, from_date, to_date, company_id);

            List<Map<String, Object>> materialTransactionByCalculated = calculateClosingBalTransaction(materialTransactionByProcedure,from_date);

            response.put("success", 1);
            response.put("materialTransactionData", materialTransactionByCalculated);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }


    private List<Map<String, Object>> CallGetMaterialTransactionsByDate(String materialId, String fromDate, String toDate, Integer company_id) {
        List<Map<String, Object>> getData = new ArrayList<>();
        String storedProcedure = "{call GetMaterialTransactionsByDate(?,?,?,?)}";

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(storedProcedure);
                    cs.setString(1, materialId);
                    cs.setString(2, fromDate);
                    cs.setString(3, toDate);
                    cs.setInt(4, company_id);
                    return cs;
                },
                (CallableStatementCallback<List<Map<String, Object>>>) cs -> {
                    cs.execute();

                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs != null && rs.next()) {
                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("transaction_type", rs.getObject("transaction_type"));
                            resultMap.put("date", rs.getObject("Date"));
                            resultMap.put("financial_year", rs.getObject("financial_year"));
                            resultMap.put("material_code", rs.getObject("material_code"));
                            resultMap.put("material_name", rs.getObject("material_name"));
                            resultMap.put("opening_quantity", rs.getObject("opening_quantity"));
                            resultMap.put("purchase_quantity", rs.getObject("purchase_quantity"));
                            resultMap.put("issue_quantity", rs.getObject("issue_quantity"));
                            resultMap.put("stock_adjustment_type", rs.getObject("stock_adjustment_type"));
                            resultMap.put("stock_adjustment_quantity", rs.getObject("stock_adjustment_quantity"));
                            resultMap.put("transfer_quantity", rs.getObject("transfer_quantity"));
                            resultMap.put("return_quantity", rs.getObject("product_material_return_quantity"));
                            resultMap.put("godown_section_name", rs.getObject("godown_section_name"));
                            resultMap.put("closing_balance_quantity", rs.getObject("closing_balance_quantity"));
                            resultMap.put("supplier_return_quantity", rs.getObject("supplier_return_quantity"));
                            resultMap.put("godown_section_beans_name", rs.getObject("godown_section_beans_name"));
                            resultMap.put("created_on", rs.getObject("created_on"));
                            resultMap.put("transaction_id", rs.getObject("transaction_id"));
                            getData.add(resultMap);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("MaterialTransactionData: " + getData);
                    return getData; // Returning the entire list instead of a single map
                }
        );
    }


    private List<Map<String, Object>> calculateClosingBalTransaction(List<Map<String, Object>> materialTransactionByProcedure, String from_date) {
        List<Map<String, Object>> updatedData = new ArrayList<>();
        double previous_row_closing_quantity = 0;

        for (Map<String, Object> rowData : materialTransactionByProcedure) {
            java.sql.Date dateObj = (java.sql.Date) rowData.get("date");
            String date = dateObj != null ? dateObj.toString() : null;

            double supplierReturnQty = rowData.get("supplier_return_quantity") != null
                    ? ((Number) rowData.get("supplier_return_quantity")).doubleValue()
                    : 0.0;

            if (date.equals(from_date) && rowData.get("transaction_type").equals("Stock Details")) {
                previous_row_closing_quantity = rowData.get("closing_balance_quantity") != null ? ((Number) rowData.get("closing_balance_quantity")).doubleValue() : 0.0;
                System.out.println("PrevClos: " + previous_row_closing_quantity);
            }

            if (rowData.get("transaction_type").toString().contains("GR")) {
                // Update the closing balance quantity for GR
                double purchaseQuantity = rowData.get("purchase_quantity") != null ? ((Number) rowData.get("purchase_quantity")).doubleValue() : 0.0;
                double newClosingBalance = purchaseQuantity + previous_row_closing_quantity;
                rowData.put("opening_quantity", previous_row_closing_quantity);
                rowData.put("closing_balance_quantity", newClosingBalance);
                System.out.println("GR: " + newClosingBalance);
                previous_row_closing_quantity = newClosingBalance;  // Update for next iteration
            } else if (rowData.get("transaction_type").toString().contains("MI")) {
                // Update the closing balance quantity for MI
                double issueQuantity = rowData.get("issue_quantity") != null ? ((Number) rowData.get("issue_quantity")).doubleValue() : 0.0;
                double newClosingBalance = previous_row_closing_quantity - issueQuantity;
                rowData.put("opening_quantity", previous_row_closing_quantity);
                rowData.put("closing_balance_quantity", newClosingBalance);
                System.out.println("MI: " + newClosingBalance);
                previous_row_closing_quantity = newClosingBalance;  // Update for next iteration
            }else if (rowData.get("transaction_type").equals("Stock Adjustment")) {
                double closing_balance = rowData.get("closing_balance_quantity") != null ? ((Number) rowData.get("closing_balance_quantity")).doubleValue() : 0.0;;
                previous_row_closing_quantity = closing_balance;  // Update for next iteration
                System.out.println("StockAdjustment: " + previous_row_closing_quantity);
            }else if (rowData.get("transaction_type").toString().contains("MT") && rowData.get("stock_adjustment_type").equals("Outward")) {
                double closing_balance = rowData.get("closing_balance_quantity") != null ? ((Number) rowData.get("closing_balance_quantity")).doubleValue() : 0.0;;
                previous_row_closing_quantity =  closing_balance;
                System.out.println("MT Outward transferQuantity: " + closing_balance);
            }else if (rowData.get("transaction_type").toString().contains("MT") && rowData.get("stock_adjustment_type").equals("Inward")) {
                double transferQuantity = rowData.get("transfer_quantity") != null ? ((Number) rowData.get("transfer_quantity")).doubleValue() : 0.0;
                double newClosingBalance = transferQuantity + previous_row_closing_quantity;
                rowData.put("opening_quantity", previous_row_closing_quantity);
                rowData.put("closing_balance_quantity", newClosingBalance);
                System.out.println("GR: " + newClosingBalance);
                previous_row_closing_quantity = newClosingBalance;
            }else if (rowData.get("transaction_type").toString().contains("UU")) {
                double returnQuantity = rowData.get("return_quantity") != null ? ((Number) rowData.get("return_quantity")).doubleValue() : 0.0;
                double newClosingBalance = returnQuantity + previous_row_closing_quantity;
                rowData.put("opening_quantity", previous_row_closing_quantity);
                rowData.put("closing_balance_quantity", newClosingBalance);
                System.out.println("Return UU: " + newClosingBalance);
                previous_row_closing_quantity = newClosingBalance;
            }else if (rowData.get("transaction_type").toString().contains("RGP")) {
                double newClosingBalance = previous_row_closing_quantity - supplierReturnQty;
                rowData.put("opening_quantity", previous_row_closing_quantity);
                rowData.put("closing_balance_quantity", newClosingBalance);
                previous_row_closing_quantity = newClosingBalance;
                System.out.println("Supplier Return: " + newClosingBalance);
            }
            else if (rowData.get("transaction_type").equals("Stock Details")) {
                rowData.put("closing_balance_quantity", previous_row_closing_quantity);
                System.out.println("StockDetails: " + previous_row_closing_quantity);
            }










            updatedData.add(rowData);
        }
        return updatedData;
    }




}
