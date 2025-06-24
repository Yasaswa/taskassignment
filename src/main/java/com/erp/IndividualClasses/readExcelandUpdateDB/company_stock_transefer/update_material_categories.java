package com.erp.IndividualClasses.readExcelandUpdateDB.company_stock_transefer;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class update_material_categories {

        public static void main(String[] args) {
            String excelFilePath = "C:/Users/yogip/Downloads/REPLECMENT CAT.xlsx"; //  Excel file path
            // staging connection
//        String jdbcURL = "jdbc:mysql://192.99.21.191:3306/pashupati_erp_qa";
//        String dbUser = "admin";
//        String dbPassword = "Not2BuseD@2024";

            // localhost connection
//            String jdbcURL = "jdbc:mysql://localhost:3306/erp-development";
//            String dbUser = "root";
//            String dbPassword = "root";

            // Production Connection
        String jdbcURL = "jdbc:mysql://192.168.50.97:3306/ERP_PASHUPATI_PROD_1_0";
        String dbUser = "pashupati"; //for 191 server
        String dbPassword = "pashupati1234";
//        String dbUser = "root"; //for 97 server

//        before run file on tunnel session first
            Connection conn = null;
            // Read the Excel file and get data
            try (FileInputStream fis = new FileInputStream(excelFilePath);
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(8); // Read the first sheet
                Integer updatedRowsCount = 0;

                // Connect to the database
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

                if(conn != null) {
                    System.out.println("connection open");
                    for (Row row : sheet) {
                        if (row.getRowNum() > 1) {
                            String category1 = row.getCell(1).getStringCellValue(); // Column 2
                            String category2 = row.getCell(3).getStringCellValue(); // Column 4
                            String material_code = "";
                            if (row.getCell(4).getCellType() == CellType.STRING) {
                                material_code = row.getCell(4).getStringCellValue(); // Column 5
                            } else if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                                material_code = String.valueOf(row.getCell(4).getNumericCellValue()); // Column 5
                            }

                            String material_name = row.getCell(5).getStringCellValue(); // Column 6
                            System.out.println("Row No : " + row.getRowNum());
                            System.out.println("value1: " + category1);
                            System.out.println("value2: " + category2);
                            System.out.println("value3: " + material_code);
                            System.out.println("value4: " + material_name);

//                     Call the procedure for each row
                            callProcedure(conn, category1, category2, material_code);
                            updatedRowsCount += 1;
                        }
                    }
                    System.out.println("updated Rows: " + updatedRowsCount);
                }

            } catch (IOException e) {
                System.out.println("Error reading Excel file: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error calling stored procedure: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) conn.close();
                    System.out.println("Connection Closed : ");
                } catch (SQLException e) {
                    System.out.println("Error closing resources: " + e.getMessage());
                }
            }
        }

        // Method to call a stored procedure in the database
        private static void callProcedure(Connection conn, String category1, String category2, String materialCode) {
//        Connection conn = null;
            CallableStatement stmt = null;

            try {
//            // Connect to the database
//            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

                // Prepare the stored procedure call
                String procedureCall = "{call update_product_categories(?, ?,?)}";
                stmt = conn.prepareCall(procedureCall);

                // Set parameters for the procedure
                stmt.setString(1, category1);  // First parameter
                stmt.setString(2, category2);  // Second parameter
                stmt.setString(3, materialCode);  // Second parameter

                // Execute the procedure
                stmt.execute();

                System.out.println("Procedure executed for: " + category1 + ", " + category2 + " , " + materialCode);

            } catch (SQLException e) {
                System.out.println("Error calling stored procedure: " + e.getMessage());
            }
        }
    }