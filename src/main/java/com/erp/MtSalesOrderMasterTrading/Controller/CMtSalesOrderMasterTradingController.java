package com.erp.MtSalesOrderMasterTrading.Controller;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingSummaryViewModel;
import com.erp.MtSalesOrderMasterTrading.Repository.*;
import com.erp.MtSalesOrderMasterTrading.Service.IMtSalesOrderMasterTradingService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.http.HttpEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;


@RestController
@RequestMapping("/api/MtSalesOrderMasterTrading")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtSalesOrderMasterTradingController {

    @Autowired
    IMtSalesOrderSchedulesTradingRepository iMtSalesOrderSchedulesTradingRepository;
    @Autowired
    IMtSalesOrderTaxSummaryRepository iMtSalesOrderTaxSummaryRepository;
    @Autowired
    IMtSalesOrderMasterTradingRepository iMtSalesOrderMasterTradingRepository;
    @Autowired
    IMtSalesOrderMasterTradingService iMtSalesOrderMasterTradingService;
    @Autowired
    IMtSalesOrderSchedulesTradingViewRepository iMtSalesOrderSchedulesTradingViewRepository;
    @Autowired
    IMtSalesOrderTaxSummaryViewRepository iMtSalesOrderTaxSummaryViewRepository;
    @Autowired
    IMtSalesOrderPaymentTermsTradingRepository iMtSalesOrderPaymentTermsTradingRepository;
    @Autowired
    IMtSalesOrderTermsTradingRepository iMtSalesOrderTermsTradingRepository;

    @Autowired
    IMtSalesOrderDetailsTradingRepository iMtSalesOrderDetailsTradingRepository;

    @Value("${printOuts.file.path}")
    private String printOutsDirectory;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @PostMapping("/FnAddUpdateRecord/{isApprove}")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtSalesOrderData") JSONObject jsonObject,
                                                 @PathVariable boolean isApprove) {
        Map<String, Object> response = iMtSalesOrderMasterTradingService.FnAddUpdateRecord(jsonObject, isApprove);
        return response;

    }

    @DeleteMapping("/FnDeleteRecord/{sales_order_version}/{company_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@RequestParam("sales_order_no") String sales_order_no,
                                              @PathVariable int sales_order_version, @PathVariable int company_id, @PathVariable String deleted_by) {
        return iMtSalesOrderMasterTradingService.FnDeleteRecord(sales_order_no, sales_order_version, company_id, deleted_by);

    }

    @GetMapping("/FnShowAllActiveRecords/{company_id}")
    public Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowAllActiveRecords(Pageable pageable,
                                                                                   @PathVariable int company_id) throws SQLException {
        Page<CMtSalesOrderMasterTradingSummaryViewModel> cMtSalesOrderMasterTradingSummaryViewModel = iMtSalesOrderMasterTradingService
                .FnShowAllActiveRecords(pageable, company_id);
        return cMtSalesOrderMasterTradingSummaryViewModel;
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{sales_order_master_transaction_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int sales_order_master_transaction_id,
                                                               @PathVariable int company_id) {
        return iMtSalesOrderMasterTradingService.FnShowParticularRecordForUpdate(sales_order_master_transaction_id,
                company_id);
    }


    @GetMapping("/FnShowParticularRecords/{sales_order_master_transaction_id}/{company_id}")
    public Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowParticularRecord(
            @PathVariable int sales_order_master_transaction_id, Pageable pageable, @PathVariable int company_id) {
        return iMtSalesOrderMasterTradingService.FnShowParticularRecord(sales_order_master_transaction_id, pageable,
                company_id);

    }

    @GetMapping("/FnShowAllDetailsAndMastermodelRecords/{sales_order_version}/{financial_year}/{company_id}")
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
            @RequestParam("sales_order_no") String sales_order_no, @PathVariable int sales_order_version,
            @PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
        return iMtSalesOrderMasterTradingService.FnShowAllDetailsandMastermodelRecords(sales_order_no,
                sales_order_version, financial_year, company_id);
    }

    @PostMapping("/FnShowSalesOrderDetailsTradingCustomerRecord/{company_id}")
    public Map<String, Object> FnShowSalesOrderDetailsTradingCustomerRecord(
            @RequestParam("customerOrderNos") JSONObject customerOrderNo, Pageable pageable,
            @PathVariable int company_id) {
        return iMtSalesOrderMasterTradingService.FnShowSalesOrderDetailsTradingCustomerRecord(customerOrderNo, pageable,
                company_id);
    }

    @GetMapping("/FnShowAllReportRecords")
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
            throws SQLException {
        return iMtSalesOrderMasterTradingService.FnShowAllReportRecords(pageable, reportType);

    }

    @GetMapping("/FnShowParticularRecords/{company_id}")
    public Page<CMtSalesOrderDetailsTradingViewModel> FnShowParticularRecord(
            @RequestParam("customer_order_no") String customer_order_no, Pageable pageable,
            @PathVariable int company_id) {
        return iMtSalesOrderMasterTradingService.FnShowParticularRecord(customer_order_no, pageable, company_id);

    }

    @PostMapping("/FnReadExcel")
    public HashMap<Object, Object> FnReadExcel(@RequestParam("file") MultipartFile reapExcelDataFile) {
        HashMap<Object, Object> responce = new HashMap<Object, Object>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
            List<String> columns = new ArrayList<String>();
            List<String> formFieldData = new ArrayList<String>();
            ArrayList<Object> data = new ArrayList<Object>();
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println("=> " + sheet.getSheetName());
            DataFormatter dataFormatter = new DataFormatter();
            System.out.println("Iterating over Rows and Columns using Iterator");
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                System.out.println("Row Number: " + row.getRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> getData = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (row.getRowNum() == 10) {
                        columns.add(cellValue);
                    } else if (row.getRowNum() > 10) {
                        getData.add(cellValue);

//						// Quantity data entry check
//						String decimalPattern = "([0-9]*)\\.([0-9]*)";
//						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
//						if (cell.getColumnIndex() == 4
//								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
//							responce.put("success", "0");
//							responce.put("error", "Please enter valid data in file!...");
//							return responce;
//						}
                    } else if (row.getRowNum() >= 5 && row.getRowNum() <= 8) {
                        formFieldData.add(cellValue);
                    }
                    System.out.print(cellValue + "\t");
                }
                if (getData.size() != 0) {
                    data.add(getData);
                }

                System.out.println();
            }

            String filename = reapExcelDataFile.getOriginalFilename().substring(0,
                    reapExcelDataFile.getOriginalFilename().lastIndexOf("."));

            String[] company_id = filename.split("@");
            String sales_order_no = company_id[0].replace("_", "/");

//			CMtSalesOrderMasterTradingModel cmtSalesOrderMasterTradingModel = iMtSalesOrderMasterTradingRepository
//					.getLastSalesOrderVersion(sales_order_no, company_id[1]);
//
//			List<CMtSalesOrderSchedulesTradingViewModel> cMtSalesOrderSchedulesTradingViewModels = iMtSalesOrderSchedulesTradingViewRepository
//					.FnShowSalesOrderSchedules(cmtSalesOrderMasterTradingModel.getSales_order_no(),
//							cmtSalesOrderMasterTradingModel.getSales_order_version(), company_id[1]);
//
//			List<CMtSalesOrderPaymentTermsTradingModel> cMtSalesOrderPaymentTermsTradingModels = iMtSalesOrderPaymentTermsTradingRepository
//					.FnShowSalesOrderPaymentTerms(cmtSalesOrderMasterTradingModel.getSales_order_no(),
//							cmtSalesOrderMasterTradingModel.getSales_order_version(), company_id[1]);
//
//			List<CMtSalesOrderTermsTradingModel> cMtSalesOrderTermsTradingModels = iMtSalesOrderTermsTradingRepository
//					.FnShowSalesOrdertermsTrading(cmtSalesOrderMasterTradingModel.getSales_order_no(),
//							cmtSalesOrderMasterTradingModel.getSales_order_version(), company_id[1]);
//
//			List<CMtSalesOrderMasterTaxSummaryViewModel> cMtSalesOrderMasterTaxSummaryModels = iMtSalesOrderTaxSummaryViewRepository
//					.FnShowSalesOrderTaxSummaryView(cmtSalesOrderMasterTradingModel.getSales_order_no(),
//							cmtSalesOrderMasterTradingModel.getSales_order_version(), company_id[1]);

            responce.put("success", "1");
            responce.put("data", data);
            responce.put("columns", columns);
            responce.put("formFieldData", formFieldData);
//			responce.put("salesOrderVersion", cmtSalesOrderMasterTradingModel.getSales_order_version());
//			responce.put("salesOrderSchedules", cMtSalesOrderSchedulesTradingViewModels);
//			responce.put("salesOrderPaymentTerms", cMtSalesOrderPaymentTermsTradingModels);
//			responce.put("salesOrderTermsTrading", cMtSalesOrderTermsTradingModels);
//			responce.put("salesOrderTaxSummaryView", cMtSalesOrderMasterTaxSummaryModels);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return responce;
    }

    @PostMapping("/FnAcceptCustomerOrder/{company_id}")
    public Map<String, Object> FnAcceptCustomerOrder(
            @RequestParam("customerSOAcceptanceJson") JSONObject customerSOAcceptanceJson,
            @PathVariable int company_id) {
        Map<String, Object> response = iMtSalesOrderMasterTradingService.FnAcceptCustomerOrder(customerSOAcceptanceJson,
                company_id);
        return response;
    }

    @PostMapping("/FnSendEmail/{sales_order_master_transaction_id}/{company_id}")
    public Map<String, Object> FnSendEmail(@PathVariable int sales_order_master_transaction_id,
                                           @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
                                           @RequestParam("salesOrderPDF") MultipartFile salesOrderPDF) {

        // Ensure the upload directory exists; create it if not
        File uploadDir = new File(printOutsDirectory.toString());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save the file to the specified directory
        String fileName = salesOrderPDF.getOriginalFilename();
        Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

        try {
            salesOrderPDF.transferTo(filePath.toFile());

            // Update the mail-attachments means add the newly created file-path.
            JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
            mailAttachments.put(filePath.toString());
            emailData.put("mailAttachmentFilePaths", mailAttachments);
            System.out.println("Sales Order PDF File Saved: " + filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now send for email sending.
        Map<String, Object> emailSendingResponse = iMtSalesOrderMasterTradingService.FnSendEmail(company_id,
                sales_order_master_transaction_id, emailData);
        return emailSendingResponse;
    }


    @PostMapping("/FnCancelSalesOrder/{sales_order_version}/{company_id}/{modified_by}")
    public Map<String, Object> FnCancelSalesOrder(@RequestParam("sales_order_no") String sales_order_no,
                                                  @PathVariable int sales_order_version, @PathVariable int company_id, @PathVariable String modified_by) {
        return iMtSalesOrderMasterTradingService.FnCancelSalesOrder(sales_order_no, sales_order_version, company_id, modified_by);

    }


    @GetMapping("/FnGetOutStandingSOData/{company_id}/{from_date}/{to_date}/{product_type}/{sales_order_item_status}/{customer_id}")
    public Map<String, Object> FnGetOutStandingSOData(@PathVariable Integer company_id, @PathVariable String from_date, @PathVariable String to_date, @PathVariable String product_type, @PathVariable String sales_order_item_status, @PathVariable Integer customer_id,
                                                      @RequestParam String customer_order_no, @RequestParam String product_material_id, @RequestParam String sales_order_no) {

        Map<String, Object> response = new HashMap<>();
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            StringBuilder salesOrderQuery = new StringBuilder("select msodt.sales_order_no, msodt.sales_order_date, msodt.sales_order_item_status , cc.customer_name, msodt.customer_order_no, msodt.customer_order_Date, msodt.product_type, msodt.product_material_id, msodt.material_rate, msodt.material_quantity, msodt.material_weight, msodt.pending_quantity, msodt.pending_weight, msodt.pree_closed_quantity, msodt.pree_closed_weight, ");
            salesOrderQuery.append(" coalesce((select sum(mtdispatch.actual_dispatch_quantity) from mt_dispatch_schedule_details_trading mtdispatch where mtdispatch.customer_order_no = msodt.customer_order_no and mtdispatch.product_material_id = msodt.product_material_id and mtdispatch.sales_order_no = msodt.sales_order_no and mtdispatch.is_delete = 0), 0) as dispatched_quantity, ");
            salesOrderQuery.append(" coalesce((select sum(mtdispatch.actual_dispatch_weight) from mt_dispatch_schedule_details_trading mtdispatch where mtdispatch.customer_order_no = msodt.customer_order_no and mtdispatch.product_material_id = msodt.product_material_id and mtdispatch.sales_order_no = msodt.sales_order_no and mtdispatch.is_delete = 0), 0) as dispatched_weight ");
            salesOrderQuery.append(" from mt_sales_order_details_trading msodt ");
            salesOrderQuery.append(" left join mt_sales_order_master_trading msomt on msomt.sales_order_no = msodt.sales_order_no and msomt.is_delete = 0 and msomt.company_id = msodt.company_id and msomt.customer_order_no = msodt.customer_order_no ");
            salesOrderQuery.append(" left join cm_customer cc on cc.customer_id = msomt.customer_id and cc.is_delete = 0 where msodt.is_delete = 0 ");
            salesOrderQuery.append(" and msodt.company_id = :company_id ");
            params.addValue("company_id", company_id);

            if (!sales_order_no.equals("sales_order_no")) {
                salesOrderQuery.append(" and msodt.sales_order_no = :sales_order_no ");
                params.addValue("sales_order_no", sales_order_no);
            }

            if (!product_type.equals("product_type")) {
                salesOrderQuery.append(" and msodt.product_type = :product_type ");
                params.addValue("product_type", product_type);
            }

            if (from_date.equals("from_date")) {
                salesOrderQuery.append(" and msodt.sales_order_item_status in ('A', 'I') ");
            } else if (sales_order_item_status.equals("All")) {
                salesOrderQuery.append(" and msodt.sales_order_item_status in ('A', 'I', 'P') ");
            } else {
                salesOrderQuery.append(" and msodt.sales_order_item_status = :sales_order_item_status ");
                params.addValue("sales_order_item_status", sales_order_item_status);
            }

            if (!customer_order_no.equals("customer_order_no")) {
                salesOrderQuery.append(" and msodt.customer_order_no = :customer_order_no ");
                params.addValue("customer_order_no", customer_order_no);
            }

            if (!product_material_id.equals("product_material_id")) {
                salesOrderQuery.append(" and msodt.product_material_id = :product_material_id ");
                params.addValue("product_material_id", product_material_id);
            }

            if (!from_date.equals("from_date")) {
                salesOrderQuery.append(" and msodt.sales_order_date between :from_date and :to_date ");
                params.addValue("from_date", from_date);
                params.addValue("to_date", to_date);
            }

            if (customer_id != 0) {
                salesOrderQuery.append(" and msomt.customer_id = :customer_id ");
                params.addValue("customer_id", customer_id);
            }

            System.out.println("Sales Order Query: " + salesOrderQuery);
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
            List<Map<String, Object>> salesOrderData = namedParameterJdbcTemplate.queryForList(salesOrderQuery.toString(), params);

            //
            List<String> productMaterialIds = salesOrderData.stream().map(data -> String.valueOf(data.get("product_material_id"))).distinct().collect(Collectors.toList());

            String table_name = "Greige  Fabric".equals(product_type) ? "sm_product_fg" : "sm_product_rm";
            String product_name_column = "Greige  Fabric".equals(product_type) ? "product_fg_name" : "product_rm_name";
            String product_code_column = "Greige  Fabric".equals(product_type) ? "product_fg_code" : "product_rm_code";
            String product_id_column = "Greige  Fabric".equals(product_type) ? "product_fg_id" : "product_rm_id";

            List<Map<String, Object>> productMaterialData = getProductMaterialData(productMaterialIds, table_name, product_name_column, product_code_column, product_id_column);

            response.put("SalesOrderData", salesOrderData);
            response.put("ProductMaterialData", productMaterialData);
        } catch (Exception e) {
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    public List<Map<String, Object>> getProductMaterialData(
            List<String> materialIds,
            String tableName,
            String productNameColumn,
            String productCodeColumn,
            String productIdColumn
    ) {
        // Build SQL string dynamically
        String sql = String.format(
                "SELECT %s AS product_material_name, %s AS product_material_code , %s AS product_material_id  FROM %s WHERE is_delete = 0 AND %s IN :materialIds",
                productNameColumn, productCodeColumn, productIdColumn, tableName, productIdColumn
        );

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("materialIds", materialIds);

        // Transform result to List<Map<String, Object>>
        query.unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(org.hibernate.transform.AliasToEntityMapResultTransformer.INSTANCE);

        return query.getResultList();
    }
//     @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendOverdueSalesOrderReport() throws IOException {
        LocalDate threeMonthsAgosalesOrders = LocalDate.now().minusMonths(3);
        String overdueSODate = threeMonthsAgosalesOrders.toString();

        List<CMtSalesOrderDetailsTradingViewModel> overdueSalesOrders =
                iMtSalesOrderMasterTradingRepository.GetAllOverDueSalesOrders(overdueSODate);

        if (overdueSalesOrders == null || overdueSalesOrders.isEmpty()) return;

        // Split by product_type_short_name
        List<CMtSalesOrderDetailsTradingViewModel> sbOrders = overdueSalesOrders.stream()
                .filter(o -> "SB".equalsIgnoreCase(o.getProduct_type_short_name()))
                .collect(Collectors.toList());

        List<CMtSalesOrderDetailsTradingViewModel> gfOrders = overdueSalesOrders.stream()
                .filter(o -> "GF".equalsIgnoreCase(o.getProduct_type_short_name()))
                .collect(Collectors.toList());

        List<String> emailList = iMtSalesOrderMasterTradingRepository.findActiveDepartmentHeadEmails();
        JSONArray toMailData = new JSONArray(emailList);
        JSONArray attachments = new JSONArray();

        if (!sbOrders.isEmpty()) {
            File sbFile = generateSalesOrderPDF(sbOrders, "Sized_Yarn_Orders.pdf", "Sized Yarn");
            attachments.put(sbFile.getAbsolutePath());
        }

        if (!gfOrders.isEmpty()) {
            File gfFile = generateSalesOrderPDF(gfOrders, "Grey_Fabric_Orders.pdf", "Greige Fabric");
            attachments.put(gfFile.getAbsolutePath());
        }

        if (attachments.isEmpty()) return; // Just in case

        JSONObject emailData = new JSONObject();
        emailData.put("EmailType", "Sales Order Overdue");
        emailData.put("alias", "Sales Department");
        emailData.put("toMailData", toMailData);
        emailData.put("bccData", new JSONArray());
        emailData.put("ccData", new JSONArray());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        JSONArray params = new JSONArray();
        params.put("Overdue Sales Orders Report");
        params.put(LocalDate.now().format(formatter));
        params.put("Total Overdue Orders: " + overdueSalesOrders.size());
        emailData.put("parameters", params);
        emailData.put("subject", "Overdue Sales Orders Summary - " + LocalDate.now().format(formatter));
        emailData.put("mailAttachmentFilePaths", attachments);

        iMtSalesOrderMasterTradingService.FnSendEmail(2, 0, emailData);
    }

    private File generateSalesOrderPDF(List<CMtSalesOrderDetailsTradingViewModel> overDueOrders, String fileName, String productType) throws IOException, DocumentException {
        File file = new File("C:/opt/" + fileName);

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        if (!overDueOrders.isEmpty()) {
            CMtSalesOrderDetailsTradingViewModel firstOrder = overDueOrders.get(0);
            // Company Name & Address from model
            Font companyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            Paragraph companyName = new Paragraph(firstOrder.getCompany_name(), companyFont);
            companyName.setAlignment(Element.ALIGN_CENTER);

            Paragraph companyAddress = new Paragraph(firstOrder.getCompany_address1(), addressFont);
            companyAddress.setAlignment(Element.ALIGN_CENTER);
            companyAddress.setSpacingAfter(15);

            document.add(companyName);
            document.add(companyAddress);
        }

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Overdue Sales Orders (" + productType + ")", titleFont);

        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Create a table with 13 columns
        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{3, 3, 3, 3, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f});

        // Add table headers
        Stream.of(
                "Cust. Order No", "Cust. Name(Bill To)", "Order Date", "Product Code",
                "Product Qty.", "Product Wt.", "Disp. Qty.", "Disp. Wt.", "Pending. Qty.", "Pending. Wt.",
                "Rate", "Order Status"
        ).forEach(col -> {
            PdfPCell cell = new PdfPCell(new Phrase(col));
            cell.setPadding(5);
            table.addCell(cell);
        });
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Add rows
        for (CMtSalesOrderDetailsTradingViewModel order : overDueOrders) {
            table.addCell(order.getCustomer_order_no());
            table.addCell(order.getCustomer_name());
            LocalDate date = LocalDate.parse(order.getSales_order_date(), inputFormatter);
            String formattedDate = date.format(outputFormatter);
            table.addCell(formattedDate);
            table.addCell(order.getProduct_material_code());
            table.addCell(createRightAlignedCell(order.getMaterial_quantity()));
            table.addCell(createRightAlignedCell(order.getMaterial_weight()));
            table.addCell(createRightAlignedCell(order.getPrevious_dispatch_quantity()));
            table.addCell(createRightAlignedCell(order.getPrevious_dispatch_weight()));
            table.addCell(createRightAlignedCell(order.getPending_quantity()));
            table.addCell(createRightAlignedCell(order.getPending_weight()));
            table.addCell(createRightAlignedCell(order.getMaterial_rate()));
            table.addCell(order.getSales_order_status_desc());
        }
        document.add(table);
        document.close();
        return file;
    }

    private PdfPCell createRightAlignedCell(Object value) {
        PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(value)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5);
        return cell;
    }

}
