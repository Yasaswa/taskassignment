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

public class company_stock_transfer {
    public static void main(String[] args) {
//        String excelFilePath = "C:/HP_ERP/CategoriesUpdate.xlsx"; // 1st Excel file path
        String excelFilePath = "C:/Users/dakshabhi/Downloads/CATEGERY_WISE.xlsx"; // 2nd Excel file path
        // staging connection
//        String jdbcURL = "jdbc:mysql://192.99.21.191:3306/pashupati_erp_staging";
//        String dbUser = "admin";
//        String dbPassword = "Not2BuseD@2024";

        // localhost connection
        String jdbcURL = "jdbc:mysql://localhost:3306/erp_new";
        String dbUser = "root";
        String dbPassword = "dakshabhi1234";

        // Production Connection
//        String jdbcURL = "jdbc:mysql://localhost:3308/ERP_PASHUPATI_PROD_1_0";
//        String dbUser = "pashupati";
//        String dbPassword = "pashupati";

//        before run file on tunnel session first
        Connection conn = null;
        // Read the Excel file and get data
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            Integer updatedRowsCount = 0;

            // Connect to the database
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            if (conn != null) {
                System.out.println("connection open");
                for (Row row : sheet) {
                    if (row.getRowNum() > 6) {
                        System.out.println("row data");
                        String oldCompany = row.getCell(7).getStringCellValue(); // Column 2
                        String newCompany = row.getCell(8).getStringCellValue(); // Column 4
                        String material_code = "";
                        if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                            // Retrieve and format the numeric value
                            material_code = String.format("%.0f", row.getCell(5).getNumericCellValue());
                        } else if (row.getCell(5).getCellType() == CellType.STRING) {
                            // Use the string value directly if it's already a string
                            material_code = row.getCell(5).getStringCellValue();
                        }

                        String material_name = row.getCell(6).getStringCellValue(); // Column 6
                        System.out.println("Row No : " + row.getRowNum());
                        System.out.println("value1: " + oldCompany);
                        System.out.println("value2: " + newCompany);
                        System.out.println("value3: " + material_code);
                        System.out.println("value4: " + material_name);

//                     Call the procedure for each row
                        if (!oldCompany.equalsIgnoreCase(newCompany)){
                            callProcedure(conn, oldCompany, newCompany, material_code);
                        }

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
    private static void callProcedure(Connection conn, String oldCompany, String newCompany, String materialCode) {
//        Connection conn = null;
        CallableStatement stmt = null;

        try {

            // Prepare the stored procedure call
            String procedureCall = "{call CreateOpeningBalanceStockEntries(?, ?,?,?)}";
            stmt = conn.prepareCall(procedureCall);

            // Set parameters for the procedure
            stmt.setString(1, "2024-12-02");// First parameter
            stmt.setString(2, oldCompany);   // Second parameter
            stmt.setString(3, newCompany);  // Second parameter
            stmt.setString(4, materialCode);  // Third parameter

            // Execute the procedure
            stmt.execute();

            System.out.println("Procedure executed for: " + oldCompany + ", " + newCompany + " , " + materialCode);

        } catch (SQLException e) {
            System.out.println("Error calling stored procedure: " + e.getMessage());
        }
    }
}
