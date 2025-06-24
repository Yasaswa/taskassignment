package com.erp.StoreStockReport.Service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.json.JsonObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Service
public class CStoreStockReportImpl implements IStoreStockReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> GetStockReportByDateSummary(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage, Integer companyId) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append(" WITH stock_adjustments AS ( ");
        query.append("SELECT ");
        query.append("act2.product_category2_id, ");
        query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Add' THEN adj.stock_adjustment_quantity ELSE 0 END) AS total_add, ");
        query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Add' THEN adj.stock_adjustment_quantity * adj.material_rate ELSE 0 END) AS adjust_by_add_value, ");
        query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Reduce' THEN adj.stock_adjustment_quantity ELSE 0 END) AS total_reduce, ");
        query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Reduce' THEN adj.stock_adjustment_quantity * adj.material_rate ELSE 0 END) AS adjust_by_reduce_value ");
        query.append("FROM sm_product_stock_adjustment_details adj ");
        query.append("LEFT JOIN sm_product_rm rm ON rm.product_rm_id = adj.product_material_id AND rm.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category1 ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_rm_technical rt ON rt.product_rm_id = adj.product_material_id AND rt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category2 act2 ON act2.product_category2_id = rt.product_category2_id AND act2.is_delete = 0 ");
        query.append("WHERE adj.is_delete = 0 ");
        if (companyId != 0) {
            query.append("AND adj.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        query.append("AND adj.stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);

        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND ct1.product_category1_id = :product_category1_id ");
            params.addValue("product_category1_id", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND  act2.product_category2_id = :product_category2_id ");
            params.addValue("product_category2_id", category2);
        }
        query.append("GROUP BY act2.product_category2_id ");
        query.append(") ");

        query.append("SELECT COUNT(*) over() as total_count,");
        query.append(" ct1.product_category1_name as product_category1_name, ");
        query.append(" ct2.product_category2_name as product_category2_name, ");
        query.append("SUM(case when st.stock_date = :from_date then st.opening_quantity else 0 end) as opening_quantity, ");
        query.append("IFNULL(SUM(case when st.stock_date = :from_date then st.opening_quantity * st.batch_rate else 0 end) / ");
        query.append("nullif(SUM(case when st.stock_date = :from_date then st.opening_quantity else 0 end), 0), 0) ");
        query.append("as opening_average_batch_rate, ");
        query.append("SUM(case when st.stock_date = :from_date then st.opening_quantity * st.batch_rate else 0 end) as opening_total_material_value, ");

        query.append("SUM(st.purchase_quantity) as purchase_quantity, ");
        query.append("IFNULL(SUM(st.purchase_quantity * st.batch_rate) / SUM(st.purchase_quantity), 0) as purchase_average_batch_rate, ");
        query.append("IFNULL(SUM(st.purchase_quantity * st.batch_rate), 0) as purchase_total_material_value, ");

        query.append("COALESCE(sa.total_add, 0) AS adjust_by_add, ");
        query.append("COALESCE(sa.adjust_by_add_value, 0) AS adjust_by_add_value, ");
        query.append("COALESCE(sa.total_reduce, 0) AS adjust_by_reduce, ");
        query.append("COALESCE(sa.adjust_by_reduce_value, 0) AS adjust_by_reduce_value, ");

        query.append("SUM(st.transfer_issue_quantity) as transfer_issue_quantity, ");
        query.append("IFNULL(SUM(st.transfer_issue_quantity * st.batch_rate), 0) as transfer_issue_quantity_value, ");

        query.append("SUM(st.transfer_receipt_quantity) as transfer_receipt_quantity, ");
        query.append("IFNULL(SUM(st.transfer_receipt_quantity * st.batch_rate), 0) as transfer_receipt_quantity_value, ");

        query.append("SUM(st.production_issue_quantity) as issue_quantity, ");
        query.append("ROUND(IFNULL(SUM(st.production_issue_quantity * st.batch_rate) / NULLIF(SUM(st.production_issue_quantity), 0), 0), 2) AS issue_average_batch_rate, ");
        query.append("IFNULL(SUM(st.production_issue_quantity * st.batch_rate), 0) as issue_total_material_value, ");

        query.append(" SUM(st.production_issue_return_quantity) as return_quantity, ");
        query.append(" ROUND(IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate) / nullif(SUM(st.production_issue_return_quantity), 0), 0), 2) as return_average_batch_rate, ");
        query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate), 0) as return_total_material_value, ");

        query.append(" SUM(st.supplier_return_quantity) as supplier_return_quantity, ");
        query.append(" ROUND(IFNULL(SUM(st.supplier_return_quantity * st.batch_rate) / nullif(SUM(st.supplier_return_quantity), 0), 0), 2) as supplier_return_batch_rate, ");
        query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate), 0) as supplier_return_material_value, ");

        query.append("SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end) as closing_balance_quantity, ");
        query.append("ROUND(IFNULL(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end) / ");
        query.append("nullif(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end), 0), 0), 2) as closing_average_batch_rate, ");
        query.append("ROUND(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end), 2) as closing_total_material_value ");

        query.append("FROM sm_product_rm_stock_details AS st ");
        query.append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category1 as ct1 on ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_rm_technical as rt on rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category2 as ct2 on ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ");
        query.append("LEFT JOIN stock_adjustments sa ON sa.product_category2_id = ct2.product_category2_id ");

        query.append(" WHERE st.stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);
        query.append(" AND st.is_delete = 0 ");
        if (companyId != 0) {
            query.append("AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.equalsIgnoreCase("")) {
            query.append(" AND rm.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        query.append(" GROUP BY ct1.product_category1_name, ct2.product_category2_name ");
        query.append(" having ")
                .append(" opening_quantity > 0 ")
                .append(" or purchase_quantity > 0 ")
                .append(" or transfer_issue_quantity > 0 ")
                .append(" or transfer_receipt_quantity > 0 ")
                .append(" or issue_quantity > 0 ")
                .append("  or closing_balance_quantity  > 0 ");
     query.append(" ORDER BY rm.product_rm_code ");
        //        query.append(" ORDER BY ct1.product_category1_name, ct2.product_category2_name ");

        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }
        System.out.println("Summary Query: " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
//        System.out.println("Stock Summary Data : " + fetchData);
        return fetchData;

    }

    @Override
    public List<Map<String, Object>> GetStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage, Integer companyId) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String product_id = commonIdsObj.getString("product_id");
        System.out.println("  from_date : " + from_date + " to_date: " + to_date);
        System.out.println("  product_type_id : " + productTypeId + " product_id: " + product_id);
        System.out.println("  category1_id : " + category1 + " category2_id: " + category2);
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("WITH ");
        query.append("adjust_add AS ( ");
        query.append(" SELECT product_material_id, ");
        query.append(" SUM(CASE WHEN stock_adjustment_type = 'Add' THEN stock_adjustment_quantity ELSE 0 END) AS adjust_by_add, ");
        query.append(" SUM(CASE WHEN stock_adjustment_type = 'Add' THEN stock_adjustment_quantity * material_rate ELSE 0 END) AS adjust_by_add_value ");
        query.append(" FROM sm_product_stock_adjustment_details ");
        query.append(" WHERE is_delete = 0 ");
        if (companyId != 0) {
            query.append("AND company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        //query.append("AND company_id = :companyId ");
        //params.addValue("companyId", companyId);
        query.append(" AND stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);
        query.append(" GROUP BY product_material_id having adjust_by_add > 0 ");
        query.append("), ");
        query.append("adjust_reduce AS ( ");
        query.append(" SELECT product_material_id, ");
        query.append(" SUM(CASE WHEN stock_adjustment_type = 'Reduce' THEN stock_adjustment_quantity ELSE 0 END) AS adjust_by_reduce, ");
        query.append(" SUM(CASE WHEN stock_adjustment_type = 'Reduce' THEN stock_adjustment_quantity * material_rate ELSE 0 END) AS adjust_by_reduce_value ");
        query.append(" FROM sm_product_stock_adjustment_details ");
        query.append(" WHERE is_delete = 0 ");
        if (companyId != 0) {
            query.append("AND company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        query.append(" AND stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);

        query.append(" GROUP BY product_material_id having adjust_by_reduce > 0 ");
        query.append(") ");
        query.append("SELECT COUNT(*) OVER() AS total_count, ");
        query.append(" st.product_rm_id AS material_id, ");
        query.append(" pt.product_type_name AS product_type_name, ");
        query.append(" ct1.product_category1_name AS product_category1_name, ");
        query.append(" ct2.product_category2_name AS product_category2_name, ");
        query.append(" rm.product_rm_code AS material_code, ");
        query.append(" rm.product_rm_name AS material_name, ");
        query.append(" gdb.godown_section_beans_name AS godown_section_beans_name, ");
        query.append(" SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ");
        query.append(" IFNULL(SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity * st.batch_rate ELSE 0 END) / ");
        query.append("        NULLIF(SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END), 0), 0) AS opening_average_batch_rate, ");
        query.append("        SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity * st.batch_rate ELSE 0 END) AS opening_total_material_value, ");
        query.append(" SUM(st.purchase_quantity) AS purchase_quantity, ");
        query.append(" IFNULL(SUM(st.purchase_quantity * st.batch_rate) / NULLIF(SUM(st.purchase_quantity), 0), 0) AS purchase_average_batch_rate, ");
        query.append(" IFNULL(SUM(st.purchase_quantity * st.batch_rate), 0) AS purchase_total_material_value, ");
        query.append(" COALESCE(adjust_add.adjust_by_add, 0) AS adjust_by_add, ");
        query.append(" COALESCE(adjust_add.adjust_by_add_value, 0) AS adjust_by_add_value, ");
        query.append(" COALESCE(adjust_reduce.adjust_by_reduce, 0) AS adjust_by_reduce, ");
        query.append(" COALESCE(adjust_reduce.adjust_by_reduce_value, 0) AS adjust_by_reduce_value, ");

        query.append("SUM(st.transfer_issue_quantity) as transfer_issue_quantity, ");
//      query.append("COALESCE(SUM(st.transfer_issue_quantity * st.batch_rate) / SUM(st.transfer_issue_quantity), 0) as purchase_average_batch_rate, ");
        query.append("COALESCE(SUM(st.transfer_issue_quantity * st.batch_rate), 0) as transfer_issue_quantity_value, ");

        query.append("SUM(st.transfer_receipt_quantity) as transfer_receipt_quantity, ");
//      query.append("COALESCE(SUM(st.transfer_receipt_quantity * st.batch_rate) / SUM(st.transfer_receipt_quantity), 0) as purchase_average_batch_rate, ");
        query.append("COALESCE(SUM(st.transfer_receipt_quantity * st.batch_rate), 0) as transfer_receipt_quantity_value, ");

        query.append(" SUM(st.production_issue_quantity) AS issue_quantity, ");
        query.append(" IFNULL(SUM(st.production_issue_quantity * st.batch_rate) / NULLIF(SUM(st.production_issue_quantity), 0), 0) AS issue_average_batch_rate, ");
        query.append(" IFNULL(SUM(st.production_issue_quantity * st.batch_rate), 0) AS issue_total_material_value, ");

        query.append(" SUM(st.production_issue_return_quantity) as return_quantity, ");
        query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate) / nullif(SUM(st.production_issue_return_quantity), 0), 0) as return_average_batch_rate, ");
	    query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate), 0) as return_total_material_value, ");

        query.append(" SUM(st.supplier_return_quantity) as supplier_return_quantity, ");
        query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate) / nullif(SUM(st.supplier_return_quantity), 0), 0) as supplier_return_batch_rate, ");
        query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate), 0) as supplier_return_material_value, ");

        query.append(" SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END) AS closing_balance_quantity, ");
        query.append(" ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END) / ");
        query.append(" NULLIF(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END), 0), 0), 2) AS closing_average_batch_rate, ");
        query.append(" ROUND(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END), 2) AS closing_total_material_value ");
        query.append("FROM sm_product_rm_stock_details AS st ");
        query.append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0  ");
        query.append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ");
        query.append("LEFT JOIN adjust_add ON adjust_add.product_material_id = st.product_rm_id ");
        query.append("LEFT JOIN adjust_reduce ON adjust_reduce.product_material_id = st.product_rm_id ");
        query.append("LEFT JOIN cm_godown_section_beans as gdb ON gdb.godown_section_beans_id = st.godown_section_beans_id  AND gdb.is_delete = 0 ");

        query.append(" WHERE st.stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);
        query.append(" AND st.is_delete = 0 ");
        if (companyId != 0) {
            query.append("AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.equalsIgnoreCase("")) {
            query.append(" AND rm.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.equalsIgnoreCase("")) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        query.append(" GROUP BY rm.product_rm_id, rm.product_rm_code ");
        query.append(" having ")
                .append(" opening_quantity > 0 ")
                .append(" or purchase_quantity > 0 ")
                .append(" or transfer_issue_quantity > 0 ")
                .append(" or transfer_receipt_quantity > 0 ")
                .append(" or issue_quantity > 0 ")
                .append("  or closing_balance_quantity  > 0 ");
        query.append(" ORDER BY ct1.product_category1_name, ct2.product_category2_name ,rm.product_rm_id");
        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }

        System.out.println("Details Query: " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
//        System.out.println("Stock Details Data : " + fetchData);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> GetStockReportToExport(JSONObject commonIdsObj, String reportType) {
        List<Map<String, Object>> fetchData = new ArrayList<>();

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String product_id = commonIdsObj.getString("product_id");
        int companyId = commonIdsObj.getString("company_id").equalsIgnoreCase("All") ? 0 : Integer.parseInt(commonIdsObj.getString("company_id"));
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        if (reportType.equalsIgnoreCase("summary")) {

            query.append(" WITH stock_adjustments AS ( ");
            query.append("SELECT ");
            query.append("act2.product_category2_id, ");
            query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Add' THEN adj.stock_adjustment_quantity ELSE 0 END) AS total_add, ");
            query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Add' THEN adj.stock_adjustment_quantity * adj.material_rate ELSE 0 END) AS adjust_by_add_value, ");
            query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Reduce' THEN adj.stock_adjustment_quantity ELSE 0 END) AS total_reduce, ");
            query.append("SUM(CASE WHEN adj.stock_adjustment_type = 'Reduce' THEN adj.stock_adjustment_quantity * adj.material_rate ELSE 0 END) AS adjust_by_reduce_value ");
            query.append("FROM sm_product_stock_adjustment_details adj ");
            query.append("LEFT JOIN sm_product_rm rm ON rm.product_rm_id = adj.product_material_id AND rm.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_category1 ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_rm_technical rt ON rt.product_rm_id = adj.product_material_id AND rt.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_category2 act2 ON act2.product_category2_id = rt.product_category2_id AND act2.is_delete = 0 ");
            query.append("WHERE adj.is_delete = 0 ");
            query.append("AND adj.stock_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
            if (!category1.equalsIgnoreCase("All")) {
                query.append(" AND ct1.product_category1_id = :product_category1_id ");
                params.addValue("product_category1_id", category1);
            }
            if (!category2.equalsIgnoreCase("All")) {
                query.append(" AND  act2.product_category2_id = :product_category2_id ");
                params.addValue("product_category2_id", category2);
            }
            if (companyId != 0) {
                query.append(" AND adj.company_id = :companyId ");
                params.addValue("companyId", companyId);
            }
            query.append(" GROUP BY act2.product_category2_id having total_add > 0  OR total_reduce > 0");
            query.append(") ");

            query.append(" select ROW_NUMBER() OVER(ORDER BY ct1.product_category1_name, ct2.product_category2_name)  as serial_no, ");
            query.append(" ct1.product_category1_name as product_category1_name, ");
            query.append(" ct2.product_category2_name as product_category2_name, ");

            query.append("SUM(case when st.stock_date = :from_date then st.opening_quantity else 0 end) as opening_quantity, ");
            query.append("ROUND(IFNULL(SUM(case when st.stock_date = :from_date then st.opening_quantity * st.batch_rate else 0 end) / ");
            query.append("nullif(SUM(case when st.stock_date = :from_date then st.opening_quantity else 0 end), 0), 0), 2) ");
            query.append("as opening_average_batch_rate, ");
            query.append("ROUND(IFNULL(SUM(case when st.stock_date = :from_date then st.opening_quantity * st.batch_rate else 0 end), 0), 2) as opening_total_material_value, ");

            query.append(" SUM(st.purchase_quantity) as purchase_quantity, ");
            query.append("ROUND(IFNULL(SUM(st.purchase_quantity * st.batch_rate) / SUM(st.purchase_quantity), 0), 2) as purchase_average_batch_rate, ");
            query.append("ROUND(IFNULL(SUM(st.purchase_quantity * st.batch_rate), 0), 2) as purchase_total_material_value, ");

            query.append("COALESCE(sa.total_add, 0) AS adjust_by_add, ");
            query.append("COALESCE(sa.adjust_by_add_value, 0) AS adjust_by_add_value, ");
            query.append("COALESCE(sa.total_reduce, 0) AS adjust_by_reduce, ");
            query.append("COALESCE(sa.adjust_by_reduce_value, 0) AS adjust_by_reduce_value, ");

            query.append("SUM(st.transfer_issue_quantity) as transfer_issue_quantity, ");
            query.append("COALESCE(SUM(st.transfer_issue_quantity * st.batch_rate), 0) as transfer_issue_quantity_value, ");

            query.append("SUM(st.transfer_receipt_quantity) as transfer_receipt_quantity, ");
            query.append("COALESCE(SUM(st.transfer_receipt_quantity * st.batch_rate), 0) as transfer_receipt_quantity_value, ");

            query.append("SUM(st.production_issue_quantity) as issue_quantity, ");
            query.append("ROUND(IFNULL(SUM(st.production_issue_quantity * st.batch_rate) / SUM(st.production_issue_quantity), 0), 2) as issue_average_batch_rate, ");
            query.append("ROUND(IFNULL(SUM(st.production_issue_quantity * st.batch_rate), 0), 2) as issue_total_material_value, ");

            query.append(" SUM(st.production_issue_return_quantity) as return_quantity, ");
            query.append(" ROUND(IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate) / nullif(SUM(st.production_issue_return_quantity), 0), 0), 2) as return_average_batch_rate, ");
            query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate), 0) as return_total_material_value, ");

            query.append(" SUM(st.supplier_return_quantity) as supplier_return_quantity, ");
            query.append(" ROUND(IFNULL(SUM(st.supplier_return_quantity * st.batch_rate) / nullif(SUM(st.supplier_return_quantity), 0), 0), 2) as supplier_return_batch_rate, ");
            query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate), 0) as supplier_return_material_value, ");

            query.append("SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end) as closing_balance_quantity, ");
            query.append("ROUND(IFNULL(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end) / ");
            query.append("nullif(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end), 0), 0), 2) as closing_average_batch_rate, ");
            query.append("ROUND(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end), 2) as closing_total_material_value ");

            query.append("FROM sm_product_rm_stock_details AS st ");
            query.append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_category1 as ct1 on ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_rm_technical as rt on rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ");
            query.append("LEFT JOIN sm_product_category2 as ct2 on ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ");
            query.append("LEFT JOIN stock_adjustments sa ON sa.product_category2_id = ct2.product_category2_id ");
            //query.append("LEFT JOIN cm_godown_section_beans as gdb ON gdb.godown_section_beans_id = st.godown_section_beans_id ");

            query.append(" WHERE st.stock_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
            if (companyId != 0) {
                query.append("AND st.company_id = :companyId ");
                params.addValue("companyId", companyId);
            }
            query.append(" AND st.is_delete = 0 ");
            if (!category1.equalsIgnoreCase("All")) {
                query.append(" AND rm.product_category1_id = :category1 ");
                params.addValue("category1", category1);
            }
            if (!category2.equalsIgnoreCase("All")) {
                query.append(" AND rt.product_category2_id = :category2 ");
                params.addValue("category2", category2);
            }
            if (!productTypeId.equalsIgnoreCase("")) {
                query.append(" AND rm.product_type_id = :productTypeId ");
                params.addValue("productTypeId", productTypeId);
            }
            query.append(" GROUP BY ct2.product_category2_name ");
            query.append(" having ")
                    .append(" opening_quantity > 0 ")
                    .append(" or purchase_quantity > 0 ")
                    .append(" or transfer_issue_quantity > 0 ")
                    .append(" or transfer_receipt_quantity > 0 ")
                    .append(" or issue_quantity > 0 ")
                    .append("  or closing_balance_quantity  > 0 ");
            query.append(" ORDER BY ct1.product_category1_name, ct2.product_category2_name ");

        } else {
            query.append("WITH ");
            query.append("adjust_add AS ( ");
            query.append("    SELECT product_material_id, ");
            query.append("           SUM(CASE WHEN stock_adjustment_type = 'Add' THEN stock_adjustment_quantity ELSE 0 END) AS adjust_by_add, ");
            query.append("           SUM(CASE WHEN stock_adjustment_type = 'Add' THEN stock_adjustment_quantity * material_rate ELSE 0 END) AS adjust_by_add_value ");
            query.append("    FROM sm_product_stock_adjustment_details ");
            query.append("    WHERE is_delete = 0 AND stock_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
            if (companyId != 0) {
                query.append(" AND company_id = :companyId ");
                params.addValue("companyId", companyId);
            }
            query.append("    GROUP BY product_material_id having adjust_by_add > 0 ");
            query.append("), ");
            query.append("adjust_reduce AS ( ");
            query.append("    SELECT product_material_id, ");
            query.append("           SUM(CASE WHEN stock_adjustment_type = 'Reduce' THEN stock_adjustment_quantity ELSE 0 END) AS adjust_by_reduce, ");
            query.append("           SUM(CASE WHEN stock_adjustment_type = 'Reduce' THEN stock_adjustment_quantity * material_rate ELSE 0 END) AS adjust_by_reduce_value ");
            query.append("    FROM sm_product_stock_adjustment_details ");
            query.append("    WHERE is_delete = 0 AND stock_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
            if (companyId != 0) {
                query.append(" AND company_id = :companyId ");
                params.addValue("companyId", companyId);
            }
            query.append("    GROUP BY product_material_id having adjust_by_reduce > 0 ");
            query.append(") ");
            query.append(" select ROW_NUMBER() OVER(ORDER BY ct1.product_category1_name, ct2.product_category2_name)  as serial_no, ");
            query.append(" ct1.product_category1_name as product_category1_name, ");
            query.append(" ct2.product_category2_name as product_category2_name, ");
            query.append(" rm.product_rm_code as material_code, ");
            query.append(" rm.product_rm_name as material_name, ");
            query.append(" gdb.godown_section_beans_name AS godown_section_beans_name, ");
            query.append(" SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ");
            query.append(" ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity * st.batch_rate ELSE 0 END) /\n" +
                    "           NULLIF(SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END), 0), 0), 2) \n" +
                    "           AS opening_average_batch_rate, ");
            query.append(" ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity * st.batch_rate ELSE 0 END), 0), 2) AS opening_total_material_value,  ");
            query.append(" SUM(st.purchase_quantity) AS purchase_quantity, ");
            query.append(" ROUND(ifnull(SUM(st.purchase_quantity * st.batch_rate) / SUM(st.purchase_quantity),0),2) AS purchase_average_batch_rate, ");
            query.append(" ROUND(ifnull(SUM(st.purchase_quantity * st.batch_rate),0),2) AS purchase_total_material_value, ");

            query.append("       COALESCE(adjust_add.adjust_by_add, 0) AS adjust_by_add, ");
            query.append("       COALESCE(adjust_add.adjust_by_add_value, 0) AS adjust_by_add_value, ");
            query.append("       COALESCE(adjust_reduce.adjust_by_reduce, 0) AS adjust_by_reduce, ");
            query.append("       COALESCE(adjust_reduce.adjust_by_reduce_value, 0) AS adjust_by_reduce_value, ");

            query.append("SUM(st.transfer_issue_quantity) as transfer_issue_quantity, ");
            query.append("COALESCE(SUM(st.transfer_issue_quantity * st.batch_rate), 0) as transfer_issue_quantity_value, ");

            query.append("SUM(st.transfer_receipt_quantity) as transfer_receipt_quantity, ");
            query.append("COALESCE(SUM(st.transfer_receipt_quantity * st.batch_rate), 0) as transfer_receipt_quantity_value, ");

            query.append(" SUM(st.production_issue_quantity) AS issue_quantity, ");
            query.append(" ROUND(ifnull(SUM(st.production_issue_quantity * st.batch_rate) / SUM(st.production_issue_quantity),0),2) AS issue_average_batch_rate, ");
            query.append(" ROUND(ifnull(SUM(st.production_issue_quantity * st.batch_rate),0),2) AS issue_total_material_value, ");

            query.append(" SUM(st.production_issue_return_quantity) as return_quantity, ");
            query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate) / nullif(SUM(st.production_issue_return_quantity), 0), 0) as return_average_batch_rate, ");
            query.append(" IFNULL(SUM(st.production_issue_return_quantity * st.batch_rate), 0) as return_total_material_value, ");

            query.append(" SUM(st.supplier_return_quantity) as supplier_return_quantity, ");
            query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate) / nullif(SUM(st.supplier_return_quantity), 0), 0) as supplier_return_batch_rate, ");
            query.append(" IFNULL(SUM(st.supplier_return_quantity * st.batch_rate), 0) as supplier_return_material_value, ");

            query.append(" SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end) as closing_balance_quantity, ");
            query.append(" ROUND(IFNULL(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end) / " +
                    " nullif(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity else 0 end), 0), 0), 2) as closing_average_batch_rate, ");
            query.append(" ROUND(SUM(case when st.stock_date = :to_date then st.closing_balance_quantity * st.batch_rate else 0 end),2) as closing_total_material_value ");
            query.append(" FROM sm_product_rm_stock_details AS st ");
            query.append(" LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id  and rm.is_delete = 0 ");
            query.append(" LEFT JOIN sm_product_type as pt on pt.product_type_id = st.product_type_id and pt.is_delete = 0 ");
            query.append(" LEFT JOIN sm_product_category1 as ct1 ON ct1.product_category1_id = rm.product_category1_id and ct1.is_delete = 0 ");
            query.append(" LEFT JOIN sm_product_rm_technical as rt ON rt.product_rm_id = st.product_rm_id and rt.is_delete = 0 ");
            query.append(" LEFT JOIN sm_product_category2 as ct2 ON ct2.product_category2_id = rt.product_category2_id and ct2.is_delete = 0 ");
            query.append("LEFT JOIN adjust_add ON adjust_add.product_material_id = st.product_rm_id ");
            query.append("LEFT JOIN adjust_reduce ON adjust_reduce.product_material_id = st.product_rm_id ");
            query.append("LEFT JOIN cm_godown_section_beans as gdb ON gdb.godown_section_beans_id = st.godown_section_beans_id AND gdb.is_delete = 0 ");

            query.append(" WHERE st.stock_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
            query.append(" AND st.is_delete = 0 ");
            if (companyId != 0) {
                query.append("AND st.company_id = :companyId ");
                params.addValue("companyId", companyId);
            }
            if (!category1.equalsIgnoreCase("All")) {
                query.append(" AND rm.product_category1_id = :category1 ");
                params.addValue("category1", category1);
            }
            if (!category2.equalsIgnoreCase("All")) {
                query.append(" AND rt.product_category2_id = :category2 ");
                params.addValue("category2", category2);
            }
            if (!productTypeId.equalsIgnoreCase("")) {
                query.append(" AND rm.product_type_id = :productTypeId ");
                params.addValue("productTypeId", productTypeId);
            }
            if (!product_id.equalsIgnoreCase("")) {
                query.append(" AND st.product_rm_id = :product_id ");
                params.addValue("product_id", product_id);
            }
            query.append(" GROUP BY rm.product_rm_id, rm.product_rm_code ");
            query.append(" having ")
                    .append(" opening_quantity > 0 ")
                    .append(" or purchase_quantity > 0 ")
                    .append(" or transfer_issue_quantity > 0 ")
                    .append(" or transfer_receipt_quantity > 0 ")
                    .append(" or issue_quantity > 0 ")
                    .append("  or closing_balance_quantity  > 0 ");
            query.append(" ORDER BY ct1.product_category1_name, ct2.product_category2_name ");
        }
        System.out.println("Query: " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        System.out.println("Query update: " + query);
        fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fetchData;
    }


    @Override
    public List<Map<String, Object>> GetRawMaterialStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String product_id = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String godown_section_id = commonIdsObj.getString("godown_section_id");
        String godown_section_beans_id = commonIdsObj.getString("godown_section_beans_id");
        String batch_no = commonIdsObj.getString("batch_no");
        String supplier_id = commonIdsObj.getString("supplier_id");

//
//        System.out.println("product_id: " + product_id);
//        System.out.println("product_type_id: " + productTypeId);
//        System.out.println("from_date: " + from_date + "to_date: " + to_date + "category1 : " + category1 + " category2 : " + category2);

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("SELECT COUNT(*) OVER() AS total_count, ")
                .append("pt.product_type_name AS product_type_name, ")
                .append("ct1.product_category1_name AS product_category1_name, ")
                .append("ct2.product_category2_name AS product_category2_name, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("st.batch_no AS lot_no, ")
                .append("st.weight_per_box_item AS cone_per_wt, ")
                .append("gwn.godown_name AS godown_name, ")
                .append("sb.supp_branch_name AS supp_name, ")
                .append("stdt.sub_department_id AS sub_department_id, ")
                .append("stdt.department_id AS department_id, ")
                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_no_of_boxes ELSE 0 END) AS opening_no_of_boxes, ")
                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ")
                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_weight ELSE 0 END) AS opening_total_box_weight, ")
                .append("st.purchase_no_of_boxes AS purchase_no_of_boxes, ")
                .append("st.purchase_quantity AS purchase_quantity, ")
                .append("ROUND(IFNULL(st.purchase_weight * st.batch_rate / st.purchase_weight, 0), 2) AS purchase_average_batch_rate, ")
                .append("ROUND(IFNULL(st.purchase_weight, 0), 2) AS purchase_total_box_weight, ")
                .append("ROUND(IFNULL(st.purchase_weight * st.batch_rate, 0), 2) AS purchase_total_material_value, ")
                .append("IFNULL((stdt.product_material_issue_boxes_warping), 0) AS issue_no_of_boxes_warping, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_quantity_warping), 0), 2) AS issue_material_quantity_warping, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_weight_warping), 0), 2) AS issue_total_material_weight_warping, ")
                .append("IFNULL((stdt.product_material_issue_boxes_weaving), 0) AS issue_no_of_boxes_weaving, ")
                .append("IFNULL((stdt.product_material_issue_quantity_weaving), 0) AS issue_material_quantity_weaving, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_weight_weaving), 0), 2) AS issue_total_material_weight_weaving, ")
                .append("IFNULL((strt.product_material_return_boxes_warping), 0) AS product_material_return_boxes_warping, ")
                .append("ROUND(IFNULL((strt.product_material_return_quantity_warping), 0), 2) AS product_material_return_quantity_warping, ")
                .append("ROUND(IFNULL((strt.product_material_return_weight_warping), 0), 2) AS product_material_return_weight_warping, ")
                .append("IFNULL(SUM(strt.product_material_return_boxes_weaving), 0) AS product_material_return_boxes_weaving, ")
                .append("ROUND(IFNULL(SUM(strt.product_material_return_quantity_weaving), 0), 2) AS product_material_return_quantity_weaving, ")
                .append("ROUND(IFNULL(SUM(strt.product_material_return_weight_weaving), 0), 2) AS product_material_return_weight_weaving, ")
                .append("IFNULL(SUM(st.supplier_return_boxes), 0) AS supplier_return_boxes, ")
                .append("ROUND(IFNULL(SUM(st.supplier_return_quantity), 0), 2) AS supplier_return_quantity, ")
                .append("ROUND(IFNULL(SUM(st.supplier_return_weight), 0), 2) AS supplier_return_weight, ")
                .append("SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ")
                .append("ROUND(SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_balance_quantity ELSE 0 END), 2) AS closing_balance_quantity, ")
                .append("ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_balance_weight ELSE 0 END), 0), 2) AS closing_total_box_weight ")
                .append("FROM sm_product_rm_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ")
                .append("LEFT JOIN cm_supplier_branch AS sb ON sb.supp_branch_id = st.supplier_id AND sb.is_delete = 0 ")
                .append("LEFT JOIN cm_godown AS gwn ON gwn.godown_id = st.godown_id AND gwn.is_delete = 0 ")
                .append("LEFT JOIN (")
                .append("SELECT issued.sub_department_id, issued.department_id, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_quantity ELSE 0 END) AS product_material_issue_quantity_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_no_boxes ELSE 0 END) AS product_material_issue_boxes_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_weight ELSE 0 END) AS product_material_issue_weight_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_quantity ELSE 0 END) AS product_material_issue_quantity_warping, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_no_boxes ELSE 0 END) AS product_material_issue_boxes_warping, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_weight ELSE 0 END) AS product_material_issue_weight_warping, ")
                .append("issued.godown_id, issued.godown_section_id, issued.godown_section_beans_id, ")
                .append("issued.batch_no, issued.product_material_id, issued.cone_per_wt ")
                .append("FROM st_material_issue_batch_wise issued ")
                .append("WHERE issued.transaction_date BETWEEN :fromDate AND :toDate ")
                .append("AND issued.is_delete = 0 ")
                .append("AND issued.issue_status = 'MI' ")
                .append("AND issued.issue_return_status = 'P' ")
                .append("GROUP BY issued.product_material_id, issued.batch_no, issued.cone_per_wt ")
                .append(") AS stdt ON stdt.product_material_id = st.product_rm_id ")
                .append("AND st.weight_per_box_item = stdt.cone_per_wt ")
                .append("AND st.batch_no = stdt.batch_no ")
                .append("AND st.godown_id = stdt.godown_id ")
                .append("AND st.godown_section_id = stdt.godown_section_id ")
                .append("AND st.godown_section_beans_id = stdt.godown_section_beans_id ")
                .append("LEFT JOIN (")
                .append("SELECT ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_boxes ELSE 0 END) AS product_material_return_boxes_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_quantity ELSE 0 END) AS product_material_return_quantity_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_weight ELSE 0 END) AS product_material_return_weight_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_boxes ELSE 0 END) AS product_material_return_boxes_warping, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_quantity ELSE 0 END) AS product_material_return_quantity_warping, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_weight ELSE 0 END) AS product_material_return_weight_warping, ")
                .append("retnd.product_material_id, retnd.godown_id, retnd.godown_section_id, retnd.godown_section_beans_id, ")
                .append("retnd.batch_no, retnd.cone_per_wt ")
                .append("FROM st_material_issue_batch_wise retnd ")
                .append("WHERE retnd.issue_return_status = 'R' ")
                .append("AND retnd.is_delete = 0 ")
                .append("AND retnd.receipt_quantity > 0 ")
                .append("AND retnd.transaction_date BETWEEN :fromDate AND :toDate ")
                .append("GROUP BY retnd.product_material_id, retnd.cone_per_wt, retnd.batch_no ")
                .append(") AS strt ON strt.product_material_id = st.product_rm_id ")
                .append("AND st.weight_per_box_item = strt.cone_per_wt ")
                .append("AND st.batch_no = strt.batch_no ")
                .append("AND st.godown_id = strt.godown_id ")
                .append("AND st.godown_section_id = strt.godown_section_id ")
                .append("AND st.godown_section_beans_id = strt.godown_section_beans_id ")
                .append("WHERE st.stock_date BETWEEN :fromDate AND :toDate ")
                .append("AND st.is_delete = 0 ");

        // Add dynamic filters
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.equalsIgnoreCase("")) {
            query.append(" AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.equalsIgnoreCase("")) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        //filter for godown wise show data
        if (!godown_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!godown_section_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_section_id = :godown_section_id ");
            params.addValue("godown_section_id", godown_section_id);
        }
        if (!godown_section_beans_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_section_beans_id = :godown_section_beans_id ");
            params.addValue("godown_section_beans_id", godown_section_beans_id);
        }
        if (!batch_no.equalsIgnoreCase("All")) {
            query.append(" AND st.batch_no = :batch_no ");
            params.addValue("batch_no", batch_no);
        }
        if (!supplier_id.equalsIgnoreCase("All")) {
            query.append(" AND sb.supp_branch_name = :supplier_id ");
            params.addValue("supplier_id", supplier_id);
        }

        // Add static filter conditions
        query.append("AND (st.opening_no_of_boxes > 0 ")
                .append("OR st.opening_quantity > 0 ")
                .append("OR st.opening_weight > 0 ")
                .append("OR st.purchase_no_of_boxes > 0 ")
                .append("OR st.purchase_quantity > 0 ")
                .append("OR st.purchase_weight > 0 ")
                .append("OR stdt.product_material_issue_boxes_warping > 0 ")
                .append("OR stdt.product_material_issue_quantity_warping > 0 ")
                .append("OR stdt.product_material_issue_weight_warping > 0 ")
                .append("OR strt.product_material_return_boxes_warping > 0 ")
                .append("OR strt.product_material_return_quantity_warping > 0 ")
                .append("OR strt.product_material_return_weight_warping > 0 ")
                .append("OR stdt.product_material_issue_boxes_weaving > 0 ")
                .append("OR stdt.product_material_issue_quantity_weaving > 0 ")
                .append("OR stdt.product_material_issue_weight_weaving > 0 ")
                .append("OR strt.product_material_return_boxes_weaving > 0 ")
                .append("OR strt.product_material_return_quantity_weaving > 0 ")
                .append("OR strt.product_material_return_weight_weaving > 0 ")
                .append("OR st.supplier_return_boxes > 0 ")
                .append("OR st.supplier_return_quantity > 0 ")
                .append("OR st.supplier_return_weight > 0 ")
                .append("OR st.closing_no_of_boxes > 0 ")
                .append("OR st.closing_balance_quantity > 0 ")
                .append("OR st.closing_balance_weight > 0) ")
                .append("GROUP BY rm.product_rm_id, st.batch_no, st.weight_per_box_item, st.godown_id, st.godown_section_id, st.godown_section_beans_id ")
                .append("ORDER BY st.product_rm_id ");

        // Add pagination
        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }

        // Add common parameters
        params.addValue("fromDate", from_date)
                .addValue("toDate", to_date);
        System.out.println("Raw Stock Details query  : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> GetRawMaterialStockReportByDateDetailsToExport(JSONObject commonIdsObj) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String product_id = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String godown_section_id = commonIdsObj.getString("godown_section_id");
        String godown_section_beans_id = commonIdsObj.getString("godown_section_beans_id");
        String batch_no = commonIdsObj.getString("batch_no");
        String supplier_id = commonIdsObj.getString("supplier_id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ROW_NUMBER() OVER(ORDER BY st.product_rm_id)  as serial_no, ")
                .append("pt.product_type_name AS product_type_name, ")
                .append("ct1.product_category1_name AS product_category1_name, ")
                .append("ct2.product_category2_name AS product_category2_name, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("st.batch_no AS lot_no, ")
                .append("st.weight_per_box_item AS cone_per_wt, ")
                .append("gwn.godown_name AS godown_name, ")
                .append("sb.supp_branch_name AS supp_name, ")
                .append("stdt.sub_department_id AS sub_department_id, ")
                .append("stdt.department_id AS department_id, ")

                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_no_of_boxes ELSE 0 END) AS opening_no_of_boxes, ")
                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ")
                .append("SUM(CASE WHEN st.stock_date = :fromDate THEN st.opening_weight ELSE 0 END) AS opening_total_box_weight, ")

                .append("st.purchase_no_of_boxes AS purchase_no_of_boxes, ")
                .append("st.purchase_quantity AS purchase_quantity, ")
                .append("ROUND(IFNULL(st.purchase_weight * st.batch_rate / st.purchase_weight, 0), 2) AS purchase_average_batch_rate, ")
                .append("ROUND(IFNULL(st.purchase_weight, 0), 2) AS purchase_total_box_weight, ")
                .append("ROUND(IFNULL(st.purchase_weight * st.batch_rate, 0), 2) AS purchase_total_material_value, ")

                .append("IFNULL((stdt.product_material_issue_boxes_warping), 0) AS issue_no_of_boxes_warping, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_quantity_warping), 0), 2) AS issue_material_quantity_warping, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_weight_warping), 0), 2) AS issue_total_material_weight_warping, ")

                .append("IFNULL((stdt.product_material_issue_boxes_weaving), 0) AS issue_no_of_boxes_weaving, ")
                .append("IFNULL((stdt.product_material_issue_quantity_weaving), 0) AS issue_material_quantity_weaving, ")
                .append("ROUND(IFNULL((stdt.product_material_issue_weight_weaving), 0), 2) AS issue_total_material_weight_weaving, ")

                .append("IFNULL((strt.product_material_return_boxes_warping), 0) AS product_material_return_boxes_warping, ")
                .append("ROUND(IFNULL((strt.product_material_return_quantity_warping), 0), 2) AS product_material_return_quantity_warping, ")
                .append("ROUND(IFNULL((strt.product_material_return_weight_warping), 0), 2) AS product_material_return_weight_warping, ")

                .append("IFNULL(SUM(strt.product_material_return_boxes_weaving), 0) AS product_material_return_boxes_weaving, ")
                .append("ROUND(IFNULL(SUM(strt.product_material_return_quantity_weaving), 0), 2) AS product_material_return_quantity_weaving, ")
                .append("ROUND(IFNULL(SUM(strt.product_material_return_weight_weaving), 0), 2) AS product_material_return_weight_weaving, ")

                .append("IFNULL(SUM(st.supplier_return_boxes), 0) AS supplier_return_boxes, ")
                .append("ROUND(IFNULL(SUM(st.supplier_return_quantity), 0), 2) AS supplier_return_quantity, ")
                .append("ROUND(IFNULL(SUM(st.supplier_return_weight), 0), 2) AS supplier_return_weight, ")

                .append("SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ")
                .append("ROUND(SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_balance_quantity ELSE 0 END), 2) AS closing_balance_quantity, ")
                .append("ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :toDate THEN st.closing_balance_weight ELSE 0 END), 0), 2) AS closing_total_box_weight ")

                .append("FROM sm_product_rm_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ")
                .append("LEFT JOIN cm_supplier_branch AS sb ON sb.supp_branch_id = st.supplier_id AND sb.is_delete = 0 ")
                .append("LEFT JOIN cm_godown AS gwn ON gwn.godown_id = st.godown_id AND gwn.is_delete = 0 ")

                .append("LEFT JOIN (")
                .append("SELECT issued.sub_department_id, issued.department_id, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_quantity ELSE 0 END) AS product_material_issue_quantity_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_no_boxes ELSE 0 END) AS product_material_issue_boxes_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id IN (62, 95) THEN issued.issue_weight ELSE 0 END) AS product_material_issue_weight_weaving, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_quantity ELSE 0 END) AS product_material_issue_quantity_warping, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_no_boxes ELSE 0 END) AS product_material_issue_boxes_warping, ")
                .append("SUM(CASE WHEN issued.sub_department_id = 93 THEN issued.issue_weight ELSE 0 END) AS product_material_issue_weight_warping, ")
                .append("issued.godown_id, issued.godown_section_id, issued.godown_section_beans_id, ")
                .append("issued.batch_no, issued.product_material_id, issued.cone_per_wt ")
                .append("FROM st_material_issue_batch_wise issued ")
                .append("WHERE issued.transaction_date BETWEEN :fromDate AND :toDate ")
                .append("AND issued.is_delete = 0 ")
                .append("AND issued.issue_status = 'MI' ")
                .append("AND issued.issue_return_status = 'P' ")
                .append("GROUP BY issued.product_material_id, issued.batch_no, issued.cone_per_wt ")
                .append(") AS stdt ON stdt.product_material_id = st.product_rm_id ")
                .append("AND st.weight_per_box_item = stdt.cone_per_wt ")
                .append("AND st.batch_no = stdt.batch_no ")
                .append("AND st.godown_id = stdt.godown_id ")
                .append("AND st.godown_section_id = stdt.godown_section_id ")
                .append("AND st.godown_section_beans_id = stdt.godown_section_beans_id ")

                .append("LEFT JOIN (")
                .append("SELECT ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_boxes ELSE 0 END) AS product_material_return_boxes_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_quantity ELSE 0 END) AS product_material_return_quantity_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id IN (62, 95) THEN retnd.receipt_weight ELSE 0 END) AS product_material_return_weight_weaving, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_boxes ELSE 0 END) AS product_material_return_boxes_warping, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_quantity ELSE 0 END) AS product_material_return_quantity_warping, ")
                .append("SUM(CASE WHEN retnd.sub_department_id = 93 THEN retnd.receipt_weight ELSE 0 END) AS product_material_return_weight_warping, ")
                .append("retnd.product_material_id, retnd.godown_id, retnd.godown_section_id, retnd.godown_section_beans_id, ")
                .append("retnd.batch_no, retnd.cone_per_wt ")
                .append("FROM st_material_issue_batch_wise retnd ")
                .append("WHERE retnd.issue_return_status = 'R' ")
                .append("AND retnd.is_delete = 0 ")
                .append("AND retnd.receipt_quantity > 0 ")
                .append("AND retnd.transaction_date BETWEEN :fromDate AND :toDate ")
                .append("GROUP BY retnd.product_material_id, retnd.cone_per_wt, retnd.batch_no ")
                .append(") AS strt ON strt.product_material_id = st.product_rm_id ")
                .append("AND st.weight_per_box_item = strt.cone_per_wt ")
                .append("AND st.batch_no = strt.batch_no ")
                .append("AND st.godown_id = strt.godown_id ")
                .append("AND st.godown_section_id = strt.godown_section_id ")
                .append("AND st.godown_section_beans_id = strt.godown_section_beans_id ")

                .append("WHERE st.stock_date BETWEEN :fromDate AND :toDate ")
                .append("AND st.is_delete = 0 ");

        // Add dynamic filters
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.equalsIgnoreCase("")) {
            query.append(" AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.equalsIgnoreCase("")) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        //filter for godown wise show data
        if (!godown_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!godown_section_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_section_id = :godown_section_id ");
            params.addValue("godown_section_id", godown_section_id);
        }
        if (!godown_section_beans_id.equalsIgnoreCase("All")) {
            query.append(" AND st.godown_section_beans_id = :godown_section_beans_id ");
            params.addValue("godown_section_beans_id", godown_section_beans_id);
        }
        if (!batch_no.equalsIgnoreCase("All")) {
            query.append(" AND st.batch_no = :batch_no ");
            params.addValue("batch_no", batch_no);
        }
        if (!supplier_id.equalsIgnoreCase("All")) {
            query.append(" AND sb.supp_branch_name = :supplier_id ");
            params.addValue("supplier_id", supplier_id);
        }

        // Add static filter conditions
        query.append("AND (st.opening_no_of_boxes > 0 ")
                .append("OR st.opening_quantity > 0 ")
                .append("OR st.opening_weight > 0 ")
                .append("OR st.purchase_no_of_boxes > 0 ")
                .append("OR st.purchase_quantity > 0 ")
                .append("OR st.purchase_weight > 0 ")
                .append("OR stdt.product_material_issue_boxes_warping > 0 ")
                .append("OR stdt.product_material_issue_quantity_warping > 0 ")
                .append("OR stdt.product_material_issue_weight_warping > 0 ")
                .append("OR strt.product_material_return_boxes_warping > 0 ")
                .append("OR strt.product_material_return_quantity_warping > 0 ")
                .append("OR strt.product_material_return_weight_warping > 0 ")
                .append("OR stdt.product_material_issue_boxes_weaving > 0 ")
                .append("OR stdt.product_material_issue_quantity_weaving > 0 ")
                .append("OR stdt.product_material_issue_weight_weaving > 0 ")
                .append("OR strt.product_material_return_boxes_weaving > 0 ")
                .append("OR strt.product_material_return_quantity_weaving > 0 ")
                .append("OR strt.product_material_return_weight_weaving > 0 ")
                .append("OR st.supplier_return_boxes > 0 ")
                .append("OR st.supplier_return_quantity > 0 ")
                .append("OR st.supplier_return_weight > 0 ")
                .append("OR st.closing_no_of_boxes > 0 ")
                .append("OR st.closing_balance_quantity > 0 ")
                .append("OR st.closing_balance_weight > 0) ")
                .append("GROUP BY rm.product_rm_id, st.batch_no, st.weight_per_box_item, st.godown_id, st.godown_section_id, st.godown_section_beans_id ")
                .append("ORDER BY st.product_rm_id ");
        params.addValue("fromDate", from_date)
                .addValue("toDate", to_date);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println("Raw Stock Details query for ExcelExport : " + query);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> GetYarnStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {

        String to_date = commonIdsObj.optString("to_date", "");
        String productTypeId = commonIdsObj.optString("product_type_id", "");
        String category1 = commonIdsObj.optString("category1_id", "All");
        String category2 = commonIdsObj.optString("category2_id", "All");
        String product_id = commonIdsObj.optString("product_id", "");
        String godown_id = commonIdsObj.optString("godown_id", "All");
        String godown_section_id = commonIdsObj.optString("godown_section_id", "All");

        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        // Build the SELECT clause
        query.append("SELECT ")
                .append("COUNT(*) OVER() AS total_count, ")
                .append("ct1.product_category1_name AS product_category1_name, ")
                .append("ct2.product_category2_name AS product_category2_name, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("st.batch_no AS lot_no, ")
                .append("grd.remark AS grn_remark, ")
                .append("grd.godown_name AS godown_name, ")
                .append("sb.supp_branch_name AS supp_name, ")
                .append("0 AS each_box_quantity, ")
//                .append("CASE WHEN st.stock_date = :to_date THEN st.closing_no_of_boxes ELSE 0 END AS closing_no_of_boxes, ")
                .append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ")
                .append("ROUND(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END), 2) AS closing_balance_quantity, ")
                .append("ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_weight ELSE 0 END), 0), 2) AS closing_total_box_weight ")
                .append("FROM sm_product_rm_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ")
                .append("LEFT JOIN cm_supplier_branch AS sb ON sb.supp_branch_id = st.supplier_id AND sb.is_delete = 0 ")
                .append("LEFT JOIN ptv_goods_receipt_details AS grd ON grd.goods_receipt_no = st.goods_receipt_no AND grd.is_delete = 0 ");

        // Apply WHERE filters
        query.append("WHERE st.stock_date = :to_date ");
        params.addValue("to_date", to_date);
        query.append("AND st.is_delete = 0 ");
        query.append("AND st.day_closed = 0 ");
        query.append("AND (st.closing_no_of_boxes > 0 OR st.closing_balance_quantity > 0 OR st.closing_balance_weight > 0) ");

        if (!"All".equalsIgnoreCase(category1)) {
            query.append("AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!"All".equalsIgnoreCase(category2)) {
            query.append("AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.isEmpty()) {
            query.append("AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append("AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (!"All".equalsIgnoreCase(godown_id)) {
            query.append("AND st.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!"All".equalsIgnoreCase(godown_section_id)) {
            query.append("AND st.godown_section_id = :godown_section_id ");
            params.addValue("godown_section_id", godown_section_id);
        }

        // Grouping and ordering
        query.append("GROUP BY  st.product_rm_id, st.batch_no, st.weight_per_box_item ");
        query.append("ORDER BY st.product_rm_id ");

        // Handle pagination
        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }
        // Execute the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println("Yarn Stock Details query: " + query);
        return fetchData;
    }


    @Override


    public Map<String, Object> GetYarnStockTotalDetails(JSONObject commonIdsObj) {

        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String product_id = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String godown_section_id = commonIdsObj.getString("godown_section_id");

        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        // Query for total closing Boxes, Quantity, Weight
        query.append("SELECT ")
                .append("COUNT(*) OVER() AS total_count, ")
                .append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ")
                .append("ROUND(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END), 2) AS closing_balance_quantity, ")
                .append("ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_weight ELSE 0 END), 0), 2) AS closing_total_box_weight ")
                .append("FROM sm_product_rm_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ")
                .append("LEFT JOIN cm_supplier_branch AS sb ON sb.supp_branch_id = st.supplier_id AND sb.is_delete = 0 ")
                .append("LEFT JOIN ptv_goods_receipt_details AS grd ON grd.goods_receipt_no = st.goods_receipt_no AND grd.is_delete = 0 ");

        query.append("WHERE st.stock_date = :to_date ");
        params.addValue("to_date", to_date);
        query.append("AND st.is_delete = 0 ");
        query.append("AND st.day_closed = 0 ");
        query.append("AND (st.closing_no_of_boxes > 0 OR st.closing_balance_quantity > 0 OR st.closing_balance_weight > 0) ");

        if (!"All".equalsIgnoreCase(category1)) {
            query.append("AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!"All".equalsIgnoreCase(category2)) {
            query.append("AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.isEmpty()) {
            query.append("AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append("AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (!"All".equalsIgnoreCase(godown_id)) {
            query.append("AND st.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!"All".equalsIgnoreCase(godown_section_id)) {
            query.append("AND st.godown_section_id = :godown_section_id ");
            params.addValue("godown_section_id", godown_section_id);
        }
        // Grouping and ordering
        query.append("ORDER BY st.product_rm_id ");

        // Execute the query using NamedParameterJdbcTemplate
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);

        System.out.println("Yarn Stock TOTAL query: " + query);

        // Return the first row if available, or an empty map
        return fetchData.isEmpty() ? Collections.emptyMap() : fetchData.get(0);
    }

    @Override
    public List<Map<String, Object>> GetYarnStockReportByDateDetailsToExport(JSONObject commonIdsObj) {

        String to_date = commonIdsObj.optString("to_date", "");
        String productTypeId = commonIdsObj.optString("product_type_id", "");
        String category1 = commonIdsObj.optString("category1_id", "All");
        String category2 = commonIdsObj.optString("category2_id", "All");
        String product_id = commonIdsObj.optString("product_id", "");
        String godown_id = commonIdsObj.optString("godown_id", "All");
        String godown_section_id = commonIdsObj.optString("godown_section_id", "All");

        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        // Build the SELECT clause
        query.append("SELECT ")
                .append(" ROW_NUMBER() OVER(ORDER BY st.product_rm_id)  as serial_no, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("st.batch_no AS lot_no, ")
                .append("grd.godown_name AS godown_name, ")
                .append("sb.supp_branch_name AS supp_name, ")
                .append("SUM(case when st.stock_date = :to_date then st.closing_no_of_boxes else 0 end) as closing_no_of_boxes, ")
                .append("ROUND(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END), 2) AS closing_balance_quantity, ")
                .append("ROUND(IFNULL(SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_weight ELSE 0 END), 0), 2) AS closing_total_box_weight ")
                .append("FROM sm_product_rm_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm_technical AS rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0 ")
                .append("LEFT JOIN sm_product_category2 AS ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0 ")
                .append("LEFT JOIN cm_supplier_branch AS sb ON sb.supp_branch_id = st.supplier_id AND sb.is_delete = 0 ")
                .append("LEFT JOIN ptv_goods_receipt_details AS grd ON grd.goods_receipt_no = st.goods_receipt_no AND grd.is_delete = 0 ");

        // Apply WHERE filters
        query.append("WHERE st.stock_date = :to_date ");
        params.addValue("to_date", to_date);
        query.append("AND st.is_delete = 0 ");
        query.append("AND st.day_closed = 0 ");
        query.append("AND (st.closing_no_of_boxes > 0 OR st.closing_balance_quantity > 0 OR st.closing_balance_weight > 0) ");

        if (!"All".equalsIgnoreCase(category1)) {
            query.append("AND rm.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!"All".equalsIgnoreCase(category2)) {
            query.append("AND rt.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!productTypeId.isEmpty()) {
            query.append("AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append("AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (!"All".equalsIgnoreCase(godown_id)) {
            query.append("AND st.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!"All".equalsIgnoreCase(godown_section_id)) {
            query.append("AND st.godown_section_id = :godown_section_id ");
            params.addValue("godown_section_id", godown_section_id);
        }

        // Grouping and ordering
        query.append("GROUP BY  st.product_rm_id, st.batch_no, st.weight_per_box_item ");
        query.append("ORDER BY st.product_rm_id ");

        // Execute the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println(fetchData);
        System.out.println("Yarn Stock Export query: " + query);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> getAgingStockDetailsExportExcel(JSONObject commonIdsObj) {

        int companyId = commonIdsObj.getInt("company_id");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String product_id = commonIdsObj.getString("product_id");

        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("WITH dated_data AS ( ")
                .append("SELECT ")
                .append("COUNT(*) OVER() AS total_count, ")
                .append("st.goods_receipt_no, ")
                .append("st.product_rm_id, ")
                .append("rm.product_rm_code, ")
                .append("rm.product_rm_name, ");
        query.append("DATEDIFF(st.stock_date, COALESCE(DATE(rm.created_on), ")
                .append("(SELECT st1.stock_date ")
                .append("FROM sm_product_rm_stock_details st1 ")
                .append("WHERE st1.is_delete = 0 ")
                .append("AND st1.goods_receipt_no = 'Opening Balance' and st1.godown_id = 2 ");
        if (!productTypeId.isEmpty() && !"0".equals(productTypeId)) {
            query.append("AND st1.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append("AND st1.product_rm_id =  st.product_rm_id ");
            params.addValue("product_id", product_id);
        }
        if (companyId != 0) {
            query.append("AND st1.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        query.append("LIMIT 1))) AS dayclose_date_diff, ");
        query.append(" SUM(st.closing_balance_quantity) AS closing_balance_quantity, ")
                .append("SUM((CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 0 AND 30 THEN st.closing_balance_quantity ELSE 0 END) + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 0 AND 30 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) END ELSE 0 END) AS below_30_days, ");

        query.append("SUM(CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 0 AND 30 THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 0 AND 30 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate END ELSE 0 END) AS below_30_days_amount, ");

        query.append("SUM((CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 31 AND 60 THEN st.closing_balance_quantity ELSE 0 END) + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 31 AND 60 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) END ELSE 0 END) AS between_31_and_60_days, ");

        query.append("SUM(CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 31 AND 60 THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 31 AND 60 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate END ELSE 0 END) AS between_31_and_60_days_amount, ");

        query.append("SUM((CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 61 AND 180 THEN st.closing_balance_quantity ELSE 0 END) + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 61 AND 180 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) END ELSE 0 END) AS between_61_and_180_days, ");

        query.append("SUM(CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) BETWEEN 61 AND 180 THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) BETWEEN 61 AND 180 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate END ELSE 0 END) AS between_61_and_180_days_amount, ");

        query.append(" SUM((case when st.goods_receipt_no != 'Opening Balance' and DATEDIFF(CURDATE(), coalesce(pgrd.goods_receipt_date, DATE(rm.created_on))) between 181 and 365 then st.closing_balance_quantity else 0 end) + ")
                .append(" case when st.goods_receipt_no = 'Opening Balance' and DATEDIFF(CURDATE(), coalesce(spsad.stock_date, DATE(rm.created_on))) between 181 and 365 ")
                .append(" then case when spsad.stock_adjustment_type = 'add' then coalesce(spsad.stock_adjustment_quantity, st.closing_balance_quantity) ")
                .append(" else coalesce(spsad.stock_adjustment_quantity, st.closing_balance_quantity) end else 0 end) as between_181_and_365_days, ")

                .append(" SUM(case when st.goods_receipt_no != 'Opening Balance' and DATEDIFF(CURDATE(), coalesce(pgrd.goods_receipt_date, DATE(rm.created_on))) between 181 and 365 then st.closing_balance_quantity * st.batch_rate else 0 end + ")
                .append(" case when st.goods_receipt_no = 'Opening Balance' and DATEDIFF(CURDATE(), coalesce(spsad.stock_date, DATE(rm.created_on))) between 181 and 365 then ")
                .append(" case when spsad.stock_adjustment_type = 'add' then coalesce(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate ")
                .append(" else coalesce(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate end else 0 end) as between_181_and_365_days_amount, ");

        query.append(" SUM((CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) > 365 THEN st.closing_balance_quantity ELSE 0 END) + ")
                .append(" CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) > 365 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) END ELSE 0 END) AS above_one_year, ")

                .append("SUM(CASE WHEN st.goods_receipt_no != 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(pgrd.goods_receipt_date, DATE(rm.created_on))) > 365 THEN st.closing_balance_quantity * st.batch_rate ELSE 0 END + ")
                .append("CASE WHEN st.goods_receipt_no = 'Opening Balance' AND DATEDIFF(CURDATE(), COALESCE(spsad.stock_date, DATE(rm.created_on))) > 365 THEN ")
                .append("CASE WHEN spsad.stock_adjustment_type = 'add' THEN COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate ")
                .append("ELSE COALESCE(spsad.stock_adjustment_quantity, st.closing_balance_quantity) * st.batch_rate END ELSE 0 END) AS above_one_year_amount, ");

        query.append(" SUM( DISTINCT st.closing_balance_quantity) as total_closing_balance_quantity, ");
        query.append(" SUM( DISTINCT st.closing_balance_quantity * st.batch_rate ) as total_amount ");

        query.append(" FROM sm_product_rm_stock_details st ")
                .append("LEFT JOIN sm_product_stock_adjustment_details spsad ON " +
                        "st.product_rm_id = spsad.product_material_id " +
//                        "AND st.goods_receipt_no = 'Opening Balance' " +
                        "AND spsad.is_delete = 0 ")
                .append("LEFT JOIN pt_goods_receipt_details pgrd ON " +
                        " st.product_rm_id = pgrd.product_material_id " +
//                        " AND st.goods_receipt_no = pgrd.goods_receipt_no " +
                        "AND pgrd.is_delete = 0 ")
                .append("LEFT JOIN sm_product_rm rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0 ")
                .append("WHERE st.day_closed = 0 ")
                .append("AND st.is_delete = 0 ")
                .append("AND st.godown_id = 2 ")
                .append("AND st.closing_balance_quantity > 0 ")
                .append("AND st.product_type_id IN (2, 12) ");

        if (!productTypeId.isEmpty() && !"0".equals(productTypeId)) {
            query.append("AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append("AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (companyId != 0) {
            query.append("AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        query.append("GROUP BY st.product_rm_id, ")
                .append("rm.product_rm_code, ")
                .append("rm.product_rm_name ) ");

        query.append(" SELECT ")
                .append("dated_data.goods_receipt_no, ")
                .append("dated_data.product_rm_id, ")
                .append("dated_data.product_rm_code, ")
                .append("dated_data.product_rm_name, ");
        query.append(" (ABS(dated_data.below_30_days) + ")
                .append("    (case when dated_data.dayclose_date_diff between 0 and 30 ")
                .append("   then dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year) else 0 end)) as below_30_days, ")

                .append(" (ABS(dated_data.below_30_days_amount) + ")
                .append("       (case when dated_data.dayclose_date_diff between 0 and 30 ")
                .append("   then (dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) * st.batch_rate else 0 end)) as below_30_days_amount, ");


        query.append(" (ABS(dated_data.between_31_and_60_days) + ")
                .append("   (case when dated_data.dayclose_date_diff between 31 and 60 ")
                .append("   then dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year) else 0 end)) as between_31_and_60_days, ")

                .append(" (ABS(dated_data.between_31_and_60_days_amount) + ")
                .append("   (case when dated_data.dayclose_date_diff between 31 and 60 ")
                .append("   then (dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) * st.batch_rate else 0 end)) as between_31_and_60_days_amount, ");


        query.append(" (ABS(dated_data.between_61_and_180_days) + ")
                .append("   (case when dated_data.dayclose_date_diff between 61 and 180 ")
                .append("   then dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year) else 0 end)) as between_61_and_180_days, ")
                .append("   (ABS(dated_data.between_61_and_180_days_amount) + ")
                .append("   (case when dated_data.dayclose_date_diff between 61 and 180 ")
                .append("    then (dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) * st.batch_rate else 0 end)) as between_61_and_180_days_amount, ");


        query.append("   (ABS(dated_data.between_181_and_365_days) + ")
                .append("   (case when dated_data.dayclose_date_diff between 181 and 365 ")
                .append("  then dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year) else 0 end)) as between_181_and_365_days, ")

                .append("   (ABS(dated_data.between_181_and_365_days_amount) + ")
                .append("    (case when dated_data.dayclose_date_diff between 181 and 365 ")
                .append("  then (dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) * st.batch_rate else 0 end )) as between_181_and_365_days_amount, ");

        query.append("  (ABS(dated_data.above_one_year) +  ")
                .append("  (case when dated_data.dayclose_date_diff > 365  ")
                .append(" then (dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) else 0 end)) as above_one_year,  ")

                .append(" (ABS(dated_data.above_one_year_amount) +   ")
                .append("     (case when dated_data.dayclose_date_diff > 365  ")
                .append("  then ((dated_data.closing_balance_quantity - ABS(dated_data.below_30_days + dated_data.between_31_and_60_days + dated_data.between_61_and_180_days + dated_data.between_181_and_365_days + dated_data.above_one_year)) * st.batch_rate) else 0 end)) as above_one_year_amount,  ");

        query.append(" dated_data.total_closing_balance_quantity, ");
        query.append(" dated_data.total_amount ");
        query.append(" FROM sm_product_rm_stock_details st ")
                .append(" LEFT JOIN dated_data ON 1 = 1 ")
                .append(" WHERE st.day_closed = 0 ")
                .append("    AND st.is_delete = 0 ")
                .append("    AND st.godown_id = 2 ")
                .append("    AND st.closing_balance_quantity > 0 ")
                .append("    AND st.product_type_id IN (2, 12) ");

        // Add dynamic filters
        if (!productTypeId.isEmpty() && !"0".equals(productTypeId)) {
            query.append(" AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.isEmpty()) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (companyId != 0) {
            query.append(" AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        // Add group by clause
        query.append(" GROUP BY dated_data.product_rm_id, dated_data.product_rm_code, dated_data.product_rm_name ");

        System.out.println("aging Stock query: " + query);
        // Execute the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println(fetchData);
        return fetchData;


    }

    @Override
    public List<Map<String, Object>> GetFabricStockReport(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {

        String to_date = commonIdsObj.getString("to_date");
        String from_date = commonIdsObj.getString("from_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String product_id = commonIdsObj.getString("product_id");
        int companyId = commonIdsObj.getInt("company_id");
        int godown_id = commonIdsObj.getInt("godown_id");
        System.out.println("to_date: " + to_date);
        System.out.println("  product_type_id : " + productTypeId + " product_id: " + product_id);
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("COUNT(*) OVER() AS total_count, ");
        query.append("st.product_rm_id AS material_id, ");
        query.append("pt.product_type_name AS product_type_name, ");
        query.append("fg.product_fg_name AS material_name, ");
        query.append("fg.product_fg_code AS material_code, ");
        query.append("st.batch_no AS batch_no, ");
        query.append("ct1.product_category1_name AS product_category1_name, ");
        query.append("gd.godown_name AS godown_name, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_no_of_boxes ELSE 0 END) AS opening_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_weight ELSE 0 END) AS opening_weight, ");
        query.append("SUM(st.production_no_of_boxes) AS production_no_of_boxes, ");
        query.append("SUM(st.production_quantity) AS production_quantity, ");
        query.append("SUM(st.production_weight) AS production_weight, ");
        query.append("SUM(st.sales_no_of_boxes) AS sales_no_of_boxes, ");
        query.append("SUM(st.sales_quantity) AS sales_quantity, ");
        query.append("SUM(st.sales_weight) AS sales_weight, ");
        query.append("SUM(st.sales_rejection_quantity) AS sales_rejection_quantity, ");
        query.append("SUM(st.sales_rejection_weight) AS sales_rejection_weight, ");
        query.append("SUM(st.sales_rejection_no_of_boxes) AS sales_rejection_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END) AS closing_balance_quantity, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_weight ELSE 0 END) AS closing_balance_weight ");
        query.append("FROM sm_product_rm_stock_details AS st ");
        query.append("LEFT JOIN sm_product_fg AS fg ON fg.product_fg_id = st.product_rm_id AND fg.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = fg.product_category1_id AND ct1.is_delete = 0 ");
        query.append("LEFT JOIN cm_godown AS gd ON gd.godown_id = st.godown_id AND gd.is_delete = 0 ");
        query.append("WHERE st.stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);
        query.append("AND st.is_delete = 0 ");

        query.append(" AND st.godown_id = :godown_id ");
        params.addValue("godown_id", godown_id);

        if (companyId != 0) {
            query.append("AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        if (!productTypeId.isEmpty() && !"0".equals(productTypeId)) {
            query.append(" AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.equalsIgnoreCase("")) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        query.append("AND (");
        query.append("st.opening_no_of_boxes > 0 OR ");
        query.append("st.opening_quantity > 0 OR ");
        query.append("st.opening_weight > 0 OR ");
        query.append("st.production_no_of_boxes > 0 OR ");
        query.append("st.production_quantity > 0 OR ");
        query.append("st.production_weight > 0 OR ");
        query.append("st.sales_no_of_boxes > 0 OR ");
        query.append("st.sales_quantity > 0 OR ");
        query.append("st.sales_rejection_quantity > 0 OR ");
        query.append("st.sales_rejection_weight > 0 OR ");
        query.append("st.sales_rejection_no_of_boxes > 0 OR ");
        query.append("st.production_weight > 0 OR "); // Duplicate in original query
        query.append("st.production_weight > 0 OR "); // Duplicate in original query
        query.append("st.closing_no_of_boxes > 0 OR ");
        query.append("st.closing_balance_quantity > 0 OR ");
        query.append("st.closing_balance_weight > 0");
        query.append(") ");
        query.append("GROUP BY st.product_rm_id , st.batch_no ");
        query.append("HAVING closing_balance_quantity > 0 ");
        query.append("ORDER BY material_id ");
        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }
        System.out.println("Fabric Stock Query: " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fabricStockData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
//        System.out.println("Fabric Stock Data : " + fabricData);
        return fabricStockData;
    }

    @Override
    public List<Map<String, Object>> GetFabricStockReportExport(JSONObject commonIdsObj) {
        String to_date = commonIdsObj.getString("to_date");
        String from_date = commonIdsObj.getString("from_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String product_id = commonIdsObj.getString("product_id");
        int companyId = commonIdsObj.getInt("company_id");
        int godown_id = commonIdsObj.getInt("godown_id");

        System.out.println("to_date: " + to_date);
        System.out.println("  product_type_id : " + productTypeId + " product_id: " + product_id);
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("st.product_rm_id AS material_id, ");
        query.append("pt.product_type_name AS product_type_name, ");
        query.append("fg.product_fg_name AS material_name, ");
        query.append("fg.product_fg_code AS material_code, ");
        query.append("st.batch_no AS batch_no, ");
        query.append("ct1.product_category1_name AS product_category1_name, ");
        query.append("gd.godown_name AS godown_name, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_no_of_boxes ELSE 0 END) AS opening_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_quantity ELSE 0 END) AS opening_quantity, ");
        query.append("SUM(CASE WHEN st.stock_date = :from_date THEN st.opening_weight ELSE 0 END) AS opening_weight, ");
        query.append("SUM(st.production_no_of_boxes) AS production_no_of_boxes, ");
        query.append("SUM(st.production_quantity) AS production_quantity, ");
        query.append("SUM(st.production_weight) AS production_weight, ");
        query.append("SUM(st.sales_no_of_boxes) AS sales_no_of_boxes, ");
        query.append("SUM(st.sales_quantity) AS sales_quantity, ");
        query.append("SUM(st.sales_weight) AS sales_weight, ");
        query.append("SUM(st.sales_rejection_quantity) AS sales_rejection_quantity, ");
        query.append("SUM(st.sales_rejection_weight) AS sales_rejection_weight, ");
        query.append("SUM(st.sales_rejection_no_of_boxes) AS sales_rejection_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_no_of_boxes ELSE 0 END) AS closing_no_of_boxes, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_quantity ELSE 0 END) AS closing_balance_quantity, ");
        query.append("SUM(CASE WHEN st.stock_date = :to_date THEN st.closing_balance_weight ELSE 0 END) AS closing_balance_weight ");
        query.append("FROM sm_product_rm_stock_details AS st ");
        query.append("LEFT JOIN sm_product_fg AS fg ON fg.product_fg_id = st.product_rm_id AND fg.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_type AS pt ON pt.product_type_id = st.product_type_id AND pt.is_delete = 0 ");
        query.append("LEFT JOIN sm_product_category1 AS ct1 ON ct1.product_category1_id = fg.product_category1_id AND ct1.is_delete = 0 ");
        query.append("LEFT JOIN cm_godown AS gd ON gd.godown_id = st.godown_id AND gd.is_delete = 0 ");
        query.append("WHERE st.stock_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);
        query.append("AND st.is_delete = 0 ");

        query.append(" AND st.godown_id = :godown_id ");
        params.addValue("godown_id", godown_id);

        if (companyId != 0) {
            query.append("AND st.company_id = :companyId ");
            params.addValue("companyId", companyId);
        }
        if (!productTypeId.isEmpty() && !"0".equals(productTypeId)) {
            query.append(" AND st.product_type_id = :productTypeId ");
            params.addValue("productTypeId", productTypeId);
        }
        if (!product_id.equalsIgnoreCase("")) {
            query.append(" AND st.product_rm_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        query.append("AND (");
        query.append("st.opening_no_of_boxes > 0 OR ");
        query.append("st.opening_quantity > 0 OR ");
        query.append("st.opening_weight > 0 OR ");
        query.append("st.production_no_of_boxes > 0 OR ");
        query.append("st.production_quantity > 0 OR ");
        query.append("st.production_weight > 0 OR ");
        query.append("st.sales_no_of_boxes > 0 OR ");
        query.append("st.sales_quantity > 0 OR ");
        query.append("st.sales_rejection_quantity > 0 OR ");
        query.append("st.sales_rejection_no_of_boxes > 0 OR ");
        query.append("st.sales_rejection_weight > 0 OR ");
        query.append("st.production_weight > 0 OR "); // Duplicate in original query
        query.append("st.production_weight > 0 OR "); // Duplicate in original query
        query.append("st.closing_no_of_boxes > 0 OR ");
        query.append("st.closing_balance_quantity > 0 OR ");
        query.append("st.closing_balance_weight > 0");
        query.append(") ");
        query.append("GROUP BY st.product_rm_id, st.batch_no ");
        query.append("HAVING closing_balance_quantity > 0 ");
        query.append("ORDER BY material_id ");
        System.out.println("Fabric Excel Export Query : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fabricStockData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
//        System.out.println("Fabric Stock Data : " + fabricData);
        return fabricStockData;
    }

    @Override
    public List<Map<String, Object>> CallGenerateAgingProcedure(JSONObject commonIdsObj, Integer page, Integer pageSize) {
        List<Map<String, Object>> results = new ArrayList<>();
        Integer companyId = commonIdsObj.getInt("company_id");
        String materialId = commonIdsObj.getString("product_id");
        String productTypeId = commonIdsObj.getString("productTypeId");
        String category1_id = commonIdsObj.getString("category1_id");
        String category2_id = commonIdsObj.getString("category2_id");
        String actionType = commonIdsObj.getString("actionType");
        String to_date = commonIdsObj.getString("to_date");
//getData&paginated
        jdbcTemplate.execute((Connection conn) -> {
            CallableStatement callableStatement = null;
            Long totalRowCount = null;
            try {
                String query = "{CALL GetAgingOfMaterials(?,?,?,?,?,?,?,?,?)}";
                callableStatement = conn.prepareCall(query);

                callableStatement.setString(1, materialId);
                callableStatement.setInt(2, companyId);
                callableStatement.setInt(3, Integer.parseInt(productTypeId));
                callableStatement.setInt(4, Integer.parseInt(category1_id));
                callableStatement.setInt(5, Integer.parseInt(category2_id));
                callableStatement.setInt(6, page);
                callableStatement.setInt(7, pageSize);
                callableStatement.setString(8, to_date);
                if (!actionType.equalsIgnoreCase("")) {
                    callableStatement.setString(9, actionType);
                }

                boolean hasResults = callableStatement.execute();
                int resultSetIndex = 0; // Index to track result set position
                while (hasResults) {
                    ResultSet rs = callableStatement.getResultSet();
                    int serialNo = page + 1; // Initialize a counter for serial number
                    if (resultSetIndex == 0) {
                        // First result set: totalRowCount
                        if (rs.next()) {
                            totalRowCount = rs.getLong("totalRowCount");
                        }
                    } else if (resultSetIndex == 1) {
                        while (rs.next()) {

                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("totalRowCount", totalRowCount);
                            resultMap.put("serialNo", serialNo); // Add serial number
                            resultMap.put("product_material_id", rs.getString("product_rm_id"));
                            resultMap.put("product_material_code", rs.getString("product_rm_code"));
                            resultMap.put("product_material_name", rs.getString("product_rm_name"));
                            resultMap.put("product_category1_name", rs.getString("product_category1_name"));
                            resultMap.put("product_category2_name", rs.getString("product_category2_name"));
                            resultMap.put("godown_section_beans_name", rs.getString("godown_section_beans_name"));
                            resultMap.put("below_30_days", rs.getBigDecimal("total_1_30"));
                            resultMap.put("below_1_30_amount", rs.getBigDecimal("total_1_30_amount"));
                            resultMap.put("between_31_and_60_days", rs.getBigDecimal("total_31_60"));
                            resultMap.put("below_31_60_amount", rs.getBigDecimal("total_31_60_amount"));
                            resultMap.put("between_61_and_180_days", rs.getBigDecimal("total_61_180"));
                            resultMap.put("below_61_180_amount", rs.getBigDecimal("total_61_180_amount"));
                            resultMap.put("between_181_and_365_days", rs.getBigDecimal("total_181_365"));
                            resultMap.put("below_181_365_amount", rs.getBigDecimal("total_181_365_amount"));
                            resultMap.put("above_one_year", rs.getBigDecimal("total_above_365"));
                            resultMap.put("above_one_year_amount", rs.getBigDecimal("total_above_365_amount"));
                            resultMap.put("total_closing_quantity", rs.getBigDecimal("total_closing_quantity"));
                            resultMap.put("total_closing_amount", rs.getBigDecimal("total_closing_amount"));

                            results.add(resultMap); // Add the result to the list
                            serialNo++; // Increment the serial number for the next iteration
                        }
                    }
                    resultSetIndex++;
                    hasResults = callableStatement.getMoreResults();

                }
            } finally {
                if (callableStatement != null) {
                    callableStatement.close();
                }
            }
            return null;
        });

        return results;
    }

    @Override
    public List<Map<String, Object>> CallGenerateAgingProcedureSummary(JSONObject commonIdsObj, Integer page, Integer pageSize) {
        List<Map<String, Object>> results = new ArrayList<>();
        Integer companyId = commonIdsObj.getInt("company_id");
        String productTypeId = commonIdsObj.getString("productTypeId");
        String category1_id = commonIdsObj.getString("category1_id");
        String category2_id = commonIdsObj.getString("category2_id");
        String actionType = commonIdsObj.getString("actionType");
        String to_date = commonIdsObj.getString("to_date");
//getData&paginated
        jdbcTemplate.execute((Connection conn) -> {
            CallableStatement callableStatement = null;
            Long totalRowCount = null;
            try {
                String query = "{CALL GetAgingOfMaterialsSummary(?,?,?,?,?,?,?,?)}";
                callableStatement = conn.prepareCall(query);

                callableStatement.setInt(1, companyId);
                callableStatement.setInt(2, Integer.parseInt(productTypeId));
                callableStatement.setInt(3, Integer.parseInt(category1_id));
                callableStatement.setInt(4, Integer.parseInt(category2_id));
                callableStatement.setInt(5, page);
                callableStatement.setInt(6, pageSize);
                callableStatement.setString(7, to_date);
                if (!actionType.equalsIgnoreCase("")) {
                    callableStatement.setString(8, actionType);
                }

                boolean hasResults = callableStatement.execute();
                int resultSetIndex = 0; // Index to track result set position
                while (hasResults) {
                    ResultSet rs = callableStatement.getResultSet();
                    int serialNo = page + 1; // Initialize a counter for serial number
                    if (resultSetIndex == 0) {
                        // First result set: totalRowCount
                        if (rs.next()) {
                            totalRowCount = rs.getLong("totalRowCount");
                        }
                    } else if (resultSetIndex == 1) {
                        while (rs.next()) {

                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("totalRowCount", totalRowCount);
                            resultMap.put("serialNo", serialNo); // Add serial number
                            resultMap.put("product_category1_name", rs.getString("product_category1_name"));
                            resultMap.put("product_category2_name", rs.getString("product_category2_name"));
                            resultMap.put("below_30_days", rs.getBigDecimal("total_1_30"));
                            resultMap.put("below_1_30_amount", rs.getBigDecimal("total_1_30_amount"));
                            resultMap.put("between_31_and_60_days", rs.getBigDecimal("total_31_60"));
                            resultMap.put("below_31_60_amount", rs.getBigDecimal("total_31_60_amount"));
                            resultMap.put("between_61_and_180_days", rs.getBigDecimal("total_61_180"));
                            resultMap.put("below_61_180_amount", rs.getBigDecimal("total_61_180_amount"));
                            resultMap.put("between_181_and_365_days", rs.getBigDecimal("total_181_365"));
                            resultMap.put("below_181_365_amount", rs.getBigDecimal("total_181_365_amount"));
                            resultMap.put("above_one_year", rs.getBigDecimal("total_above_365"));
                            resultMap.put("above_one_year_amount", rs.getBigDecimal("total_above_365_amount"));
                            resultMap.put("total_closing_quantity", rs.getBigDecimal("total_closing_quantity"));
                            resultMap.put("total_closing_amount", rs.getBigDecimal("total_closing_amount"));

                            results.add(resultMap); // Add the result to the list
                            serialNo++; // Increment the serial number for the next iteration
                        }
                    }
                    resultSetIndex++;
                    hasResults = callableStatement.getMoreResults();

                }
            } finally {
                if (callableStatement != null) {
                    callableStatement.close();
                }
            }
            return null;
        });

        return results;
    }

    @Override
    public List<Map<String, Object>> GetSizedBeamStockReportExport(JSONObject commonIdsObj) {
        String to_date = commonIdsObj.getString("to_date");
        String from_date = commonIdsObj.getString("from_date");
        int companyId = commonIdsObj.getInt("company_id");
        String beamStatus = commonIdsObj.getString("beam_status");
        String jobType = commonIdsObj.getString("job_type");
        String setNo = commonIdsObj.getString("set_no");
        String beamNo = commonIdsObj.getString("beam_no");

        System.out.println("to_date: " + to_date);
        System.out.println("  Beam Status : " + beamStatus + "  Job Type :" + jobType);
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("product_material_name, ");
        query.append("sizing_production_code, ");
        query.append("total_ends, ");
        query.append("beam_inward_type, ");
        query.append("sizing_length, ");
        query.append("set_no, ");
        query.append("job_type, ");
        query.append("sized_beam_status_desc, ");
        query.append("customer_name, ");
        query.append("remaining_length, ");
        query.append("DATE_FORMAT(sizing_production_date, '%d-%m-%Y') AS sizing_production_date, ");
        query.append("DATE_FORMAT(cut_beam_date, '%d-%m-%Y') AS cut_beam_date ");
        query.append("FROM xtv_sizing_production_stock_details ");
        query.append("WHERE company_id = :companyId ");
        params.addValue("companyId", companyId);
        query.append("AND is_delete = 0 ");
        if (!Objects.equals(from_date, "") && !Objects.equals(to_date, "")) {
            query.append("AND sizing_production_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
        }
        if (!Objects.equals(beamStatus, "All")) {
            query.append("AND sized_beam_status_desc = :beam_status ");
            params.addValue("beam_status", beamStatus);
        }
        if (!Objects.equals(jobType, "")) {
            query.append("AND job_type = :job_type ");
            params.addValue("job_type", jobType);
        }
        if (!Objects.equals(setNo, "")) {
            query.append("AND set_no = :set_no ");
            params.addValue("set_no", setNo);
        }
        if (!Objects.equals(beamNo, "")) {
            query.append("AND beam_no = :beam_no ");
            params.addValue("beam_no", beamNo);
        }
        System.out.println("Sized Beam Stock Export Query : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fabricStockData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fabricStockData;
    }


    // Cotton Bales Stock Report

    @Override
    public List<Map<String, Object>> GetCottonBaleStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String materialId = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String quality_status = commonIdsObj.getString("quality_status");
        String mill_lot_no = commonIdsObj.getString("mill_lot_no");
        String supp_lot_no = commonIdsObj.getString("supp_lot_no");
        String closing_quantity_comparator = commonIdsObj.getString("closing_quantity_comparator");
        String closing_quantity = commonIdsObj.getString("closing_quantity");


        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("WITH opening_stock AS (")
                .append(" SELECT product_material_id, ")
                .append(" batch_no, ")
                .append(" COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN quantity WHEN transaction_type = 'Issue' THEN -quantity END END), 0) AS opening_quantity, ")
                .append(" ROUND(COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN weight WHEN transaction_type = 'Issue' THEN -weight END END), 0), 2) AS opening_weight ")
                .append(" FROM sm_material_stock_log ")
                .append(" WHERE is_delete = 0 ");
        params.addValue("fromDate", from_date);
        if (!materialId.isEmpty()) {
            query.append(" AND product_material_id = :materialId ");
            params.addValue("product_material_id", materialId);
        }
        query.append(" GROUP BY product_material_id, batch_no ")
                .append(") ")
                .append("SELECT COUNT(*) OVER() AS total_count, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("gwn.godown_name AS godown_name, ")
                .append("st.batch_no, ")
                .append("st.batch_rate, ")
                .append("grncb.supplier_batch_no as supplier_batch_no, ")
                .append("grncb.press_running_no_from as press_running_no_from, ")
                .append("grncb.press_running_no_to as press_running_no_to, ")
                .append("os.opening_quantity, ")
                .append("os.opening_weight, ")
                .append("Round(os.opening_weight * st.batch_rate, 2) as opening_amount, ")

                .append("SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS purchase_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS purchase_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS purchase_amount, ")

                .append("SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS issue_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS issue_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS issue_amount, ")

                // Changes for Cotton Bales Sales Return
                .append(" SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS return_quantity, ")
                .append(" SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END) AS return_weight, ")
//                .append(" ROUND(SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) * tr.batch_rate, 2) AS return_amount, ")
//                .append(" (CASE WHEN tr.transaction_type = 'Return' THEN tr.batch_rate ELSE 0 END) as return_batch_rate, ")

                .append(" (os.opening_quantity + SUM(case when tr.transaction_type = 'Purchase' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end) - SUM(case when tr.transaction_type = 'Issue' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end) - SUM(case when tr.transaction_type = 'Return' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end)) as closing_quantity, ")
                .append(" ROUND(os.opening_weight + SUM(case when tr.transaction_type = 'Purchase' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end) - SUM(case when tr.transaction_type = 'Issue' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end) - SUM(case when tr.transaction_type = 'Return' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end), 2) as closing_weight, ")
                .append("ROUND((os.opening_weight + SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END)  - SUM(CASE  WHEN tr.transaction_type = 'Issue'  AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) ) * st.batch_rate, 2) AS closing_amount ")

                .append("FROM sm_product_material_stock_new st ")
                .append("LEFT JOIN sm_material_stock_log tr ON st.product_material_id = tr.product_material_id AND st.batch_no = tr.batch_no ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_material_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN opening_stock os ON st.product_material_id = os.product_material_id AND st.batch_no = os.batch_no ")
                .append("LEFT JOIN cm_godown AS gwn ON gwn.godown_id = st.godown_id AND gwn.is_delete = 0 ")
                .append("LEFT JOIN pt_grn_cotton_bales_details AS grncb ON grncb.goods_receipt_no = st.goods_receipt_no AND grncb.batch_no = st.batch_no AND grncb.is_delete = 0 ");
        params.addValue("fromDate", from_date);
        params.addValue("toDate", to_date);
        query.append(" WHERE tr.is_delete = 0 ");
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!materialId.equalsIgnoreCase("")) {
            query.append(" AND tr.product_material_id = :materialId ");
            params.addValue("materialId", materialId);
        }
        if (!godown_id.equalsIgnoreCase("All")) {
            query.append(" AND tr.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!quality_status.equalsIgnoreCase("All")) {
            query.append(" AND st.quality_status = :quality_status");
            params.addValue("quality_status", quality_status);
        }
        if (!mill_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.batch_no = :mill_lot_no");
            params.addValue("mill_lot_no", mill_lot_no);
        }
        if (!supp_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.supplier_batch_no = :supp_lot_no");
            params.addValue("supp_lot_no", supp_lot_no);
        }
        query.append(" GROUP BY st.product_material_id, st.batch_no");
        query.append(" HAVING ");
        if (!(closing_quantity != null && !closing_quantity.isEmpty())) {
            query.append(" closing_quantity  > 0 or issue_quantity > 0 or purchase_quantity > 0 or  opening_quantity >0 ");
        } else {
            query.append(" closing_quantity ").append(closing_quantity_comparator).append(" ").append(closing_quantity);
        }
        query.append(" ORDER BY st.product_material_id");
        if (entriesPerPage != 0) {
            query.append(" LIMIT ")
                    .append(pageCurrent)
                    .append(", ")
                    .append(entriesPerPage);
        }
        System.out.println("Cotton Bale Stock query  : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> GetCottonBaleStockReportTotals(JSONObject commonIdsObj) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String materialId = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String quality_status = commonIdsObj.getString("quality_status");
        String mill_lot_no = commonIdsObj.getString("mill_lot_no");
        String supp_lot_no = commonIdsObj.getString("supp_lot_no");
        String closing_quantity_comparator = commonIdsObj.getString("closing_quantity_comparator");
        String closing_quantity = commonIdsObj.getString("closing_quantity");


        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("WITH opening_stock AS (")
                .append(" SELECT product_material_id, ")
                .append(" batch_no, ")
                .append(" COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN quantity WHEN transaction_type = 'Issue' THEN -quantity END END), 0) AS opening_quantity, ")
                .append(" ROUND(COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN weight WHEN transaction_type = 'Issue' THEN -weight END END), 0), 2) AS opening_weight ")
                .append(" FROM sm_material_stock_log ")
                .append(" WHERE is_delete = 0 ");
        params.addValue("fromDate", from_date);
        if (!materialId.isEmpty()) {
            query.append(" AND product_material_id = :materialId ");
            params.addValue("product_material_id", materialId);
        }
        query.append(" GROUP BY product_material_id, batch_no ")
                .append(") ")
                .append("SELECT COUNT(*) OVER() AS total_count, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("gwn.godown_name AS godown_name, ")
                .append("st.batch_no, ")
                .append("st.batch_rate, ")
                .append("grncb.supplier_batch_no as supplier_batch_no, ")
                .append("grncb.press_running_no_from as press_running_no_from, ")
                .append("grncb.press_running_no_to as press_running_no_to, ")
                .append("os.opening_quantity, ")
                .append("os.opening_weight, ")
                .append("Round(os.opening_weight * st.batch_rate, 2) as opening_amount, ")

                .append("SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS purchase_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS purchase_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS purchase_amount, ")

                .append("SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS issue_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS issue_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS issue_amount, ")

                // Changes for Cotton Bales Sales Return
                .append(" SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS return_quantity, ")
                .append(" SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END) AS return_weight, ")
//                .append(" ROUND(SUM(CASE WHEN tr.transaction_type = 'Return' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) * tr.batch_rate, 2) AS return_amount, ")
//                .append(" (CASE WHEN tr.transaction_type = 'Return' THEN tr.batch_rate ELSE 0 END) as return_batch_rate, ")

                .append(" (os.opening_quantity + SUM(case when tr.transaction_type = 'Purchase' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end) - SUM(case when tr.transaction_type = 'Issue' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end) - SUM(case when tr.transaction_type = 'Return' and tr.transaction_date between :fromDate and :toDate then tr.quantity else 0 end)) as closing_quantity, ")
                .append(" ROUND(os.opening_weight + SUM(case when tr.transaction_type = 'Purchase' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end) - SUM(case when tr.transaction_type = 'Issue' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end) - SUM(case when tr.transaction_type = 'Return' and tr.transaction_date between :fromDate and :toDate then tr.weight else 0 end), 2) as closing_weight, ")
                .append("ROUND((os.opening_weight + SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END)  - SUM(CASE  WHEN tr.transaction_type = 'Issue'  AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) ) * st.batch_rate, 2) AS closing_amount ")

                .append("FROM sm_product_material_stock_new st ")
                .append("LEFT JOIN sm_material_stock_log tr ON st.product_material_id = tr.product_material_id AND st.batch_no = tr.batch_no ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_material_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN opening_stock os ON st.product_material_id = os.product_material_id AND st.batch_no = os.batch_no ")
                .append("LEFT JOIN cm_godown AS gwn ON gwn.godown_id = st.godown_id AND gwn.is_delete = 0 ")
                .append("LEFT JOIN pt_grn_cotton_bales_details AS grncb ON grncb.goods_receipt_no = st.goods_receipt_no AND grncb.batch_no = st.batch_no AND grncb.is_delete = 0 ");
        params.addValue("fromDate", from_date);
        params.addValue("toDate", to_date);
        query.append(" WHERE tr.is_delete = 0 ");
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!materialId.equalsIgnoreCase("")) {
            query.append(" AND tr.product_material_id = :materialId ");
            params.addValue("materialId", materialId);
        }
        if (!godown_id.equalsIgnoreCase("All")) {
            query.append(" AND tr.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!quality_status.equalsIgnoreCase("All")) {
            query.append(" AND st.quality_status = :quality_status");
            params.addValue("quality_status", quality_status);
        }
        if (!mill_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.batch_no = :mill_lot_no");
            params.addValue("mill_lot_no", mill_lot_no);
        }
        if (!supp_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.supplier_batch_no = :supp_lot_no");
            params.addValue("supp_lot_no", supp_lot_no);
        }
        query.append(" GROUP BY st.product_material_id, st.batch_no");
        query.append(" HAVING ");
        if (!(closing_quantity != null && !closing_quantity.isEmpty())) {
            query.append(" closing_quantity  > 0 or issue_quantity > 0 or purchase_quantity > 0 or  opening_quantity >0 ");
        } else {
            query.append(" closing_quantity ").append(closing_quantity_comparator).append(" ").append(closing_quantity);
        }
        query.append(" ORDER BY st.product_material_id");

        System.out.println("Cotton Bale Stock Totals query  : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fetchData;
    }

    @Override
    public List<Map<String, Object>> GetCottonBaleStockReportByDateDetailsToExport(JSONObject commonIdsObj) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String productTypeId = commonIdsObj.getString("product_type_id");
        String category1 = commonIdsObj.getString("category1_id");
        String category2 = commonIdsObj.getString("category2_id");
        String materialId = commonIdsObj.getString("product_id");
        String godown_id = commonIdsObj.getString("godown_id");
        String godown_section_id = commonIdsObj.getString("godown_section_id");
        String godown_section_beans_id = commonIdsObj.getString("godown_section_beans_id");
        String quality_status = commonIdsObj.getString("quality_status");
        String mill_lot_no = commonIdsObj.getString("mill_lot_no");
        String supp_lot_no = commonIdsObj.getString("supp_lot_no");
        String closing_quantity_comparator = commonIdsObj.getString("closing_quantity_comparator");
        String closing_quantity = commonIdsObj.getString("closing_quantity");


        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("WITH opening_stock AS (")
                .append(" SELECT product_material_id, ")
                .append(" batch_no, ")
                .append(" COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN quantity WHEN transaction_type = 'Issue' THEN -quantity END END), 0) AS opening_quantity, ")
                .append(" ROUND(COALESCE(SUM(CASE WHEN transaction_date < :fromDate THEN CASE WHEN transaction_type = 'Purchase' THEN weight WHEN transaction_type = 'Issue' THEN -weight END END), 0), 2) AS opening_weight ")
                .append(" FROM sm_material_stock_log ")
                .append(" WHERE is_delete = 0 ");
        params.addValue("fromDate", from_date);
        if (!materialId.isEmpty()) {
            query.append(" AND product_material_id = :materialId ");
            params.addValue("product_material_id", materialId);
        }
        query.append(" GROUP BY product_material_id, batch_no ")
                .append(") ")
                .append("SELECT ")
                .append("COUNT(*) OVER() AS total_count, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name, ")
                .append("gwn.godown_name AS godown_name, ")
                .append("st.batch_no, ")
                .append("grncb.supplier_batch_no as supplier_batch_no, ")
                .append("st.batch_rate, ")
                .append("grncb.press_running_no_from as press_running_no_from, ")
                .append("grncb.press_running_no_to as press_running_no_to, ")
                .append("os.opening_quantity, ")
                .append("os.opening_weight, ")
                .append("Round(os.opening_weight * st.batch_rate, 2) as opening_amount, ")
                .append("SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS purchase_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS purchase_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS purchase_amount, ")
                .append("SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) AS issue_quantity, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS issue_weight, ")
                .append("ROUND(SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) * st.batch_rate, 2) AS issue_amount, ")
                .append("(os.opening_quantity + SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END) - SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.quantity ELSE 0 END)) AS closing_quantity, ")
                .append("ROUND(os.opening_weight + SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END) - SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :fromDate AND :toDate THEN tr.weight ELSE 0 END), 2) AS closing_weight, ")
                .append("ROUND((os.opening_weight + SUM(CASE WHEN tr.transaction_type = 'Purchase' AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END)  - SUM(CASE  WHEN tr.transaction_type = 'Issue'  AND tr.transaction_date BETWEEN :fromDate AND :toDate  THEN tr.weight ELSE 0  END) ) * st.batch_rate, 2) AS closing_amount ")

                .append("FROM sm_product_material_stock_new st ")
                .append("LEFT JOIN sm_material_stock_log tr ON st.product_material_id = tr.product_material_id AND st.batch_no = tr.batch_no ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_material_id AND rm.is_delete = 0 ")
                .append("LEFT JOIN opening_stock os ON st.product_material_id = os.product_material_id AND st.batch_no = os.batch_no ")
                .append("LEFT JOIN cm_godown AS gwn ON gwn.godown_id = st.godown_id AND gwn.is_delete = 0 ")
                .append("LEFT JOIN pt_grn_cotton_bales_details AS grncb ON grncb.goods_receipt_no = st.goods_receipt_no  AND grncb.batch_no = st.batch_no AND grncb.is_delete = 0 ");
        params.addValue("fromDate", from_date);
        params.addValue("toDate", to_date);
        query.append(" WHERE tr.is_delete = 0 ");
        if (!category1.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category1_id = :category1 ");
            params.addValue("category1", category1);
        }
        if (!category2.equalsIgnoreCase("All")) {
            query.append(" AND tr.product_category2_id = :category2 ");
            params.addValue("category2", category2);
        }
        if (!materialId.equalsIgnoreCase("")) {
            query.append(" AND tr.product_material_id = :materialId ");
            params.addValue("materialId", materialId);
        }
        if (!godown_id.equalsIgnoreCase("All")) {
            query.append(" AND tr.godown_id = :godown_id ");
            params.addValue("godown_id", godown_id);
        }
        if (!quality_status.equalsIgnoreCase("All")) {
            query.append(" AND st.quality_status = :quality_status");
            params.addValue("quality_status", quality_status);
        }
        if (!mill_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.batch_no = :mill_lot_no");
            params.addValue("mill_lot_no", mill_lot_no);
        }
        if (!supp_lot_no.equalsIgnoreCase("All")) {
            query.append(" AND grncb.supplier_batch_no = :supp_lot_no");
            params.addValue("supp_lot_no", supp_lot_no);
        }
        query.append(" GROUP BY st.product_material_id, st.batch_no");
        query.append(" HAVING ");
        if (!(closing_quantity != null && !closing_quantity.isEmpty())) {
            query.append(" closing_quantity  > 0 or issue_quantity > 0 or purchase_quantity > 0 or  opening_quantity >0 ");
        } else {
            query.append(" closing_quantity ").append(closing_quantity_comparator).append(" ").append(closing_quantity);
        }
        query.append(" ORDER BY st.product_material_id");

        System.out.println("Cotton Bales Stock Excel query : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return fetchData;
    }


    //Cotton Bales Mixing Chart's weightage Report
    public List<Map<String, Object>> FnGetBalesWeightageReport(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {
        List<Map<String, Object>> response = new ArrayList<>();

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        Integer plant_id = commonIdsObj.getInt("plant_id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("SELECT pmccb.mixing_chart_no, pmccb.mixing_chart_date, COUNT(*) OVER() AS total_count,");
        query.append("SUM(pmccb.issue_weight) as total_issued_weight,");
        query.append("SUM(pmccb.actual_issue_weight) as total_actual_issue_weight,");
        query.append("COALESCE((SUM(pmccb.uhml * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS uhml, ");
        query.append("COALESCE((SUM(pmccb.mi * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS mi, ");
        query.append("COALESCE((SUM(pmccb.ul * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS ul, ");
        query.append("COALESCE((SUM(pmccb.mic * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS mic, ");
        query.append("COALESCE((SUM(pmccb.str * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS str, ");
        query.append("COALESCE((SUM(pmccb.rd * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS rd, ");
        query.append("COALESCE((SUM(pmccb.b_plus * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS b_plus, ");
        query.append("COALESCE((SUM(pmccb.total_neps * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS total_neps, ");
        query.append("COALESCE((SUM(pmccb.sc_n * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS sc_n, ");
        query.append("COALESCE((SUM(pmccb.sfc_n * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS sfc_n, ");
        query.append("COALESCE((SUM(pmccb.trash * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS trash, ");
        query.append("COALESCE((SUM(pmccb.moisture * pmccb.issue_quantity)) / SUM(pmccb.issue_quantity), 0) AS moisture ");
        query.append("FROM pt_mixing_chart_cotton_bales pmccb where pmccb.is_delete = 0 ");
        if (!from_date.isEmpty() && !to_date.isEmpty()) {
            query.append(" AND pmccb.mixing_chart_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
        }
        if (!plant_id.equals(0)) {
            query.append("AND pmccb.plant_id = :plant_id ");
            params.addValue("plant_id", plant_id);
        }
        query.append(" GROUP BY pmccb.mixing_chart_no");
        query.append(" ORDER BY pmccb.mixing_chart_cotton_bales_transaction_id DESC ");

        if (entriesPerPage != 0) {
            query.append("LIMIT ").append(pageCurrent).append(", ").append(entriesPerPage);
        }

        System.out.println("Cotton Bales Weightage" + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);

//         response.put("CottonBalesWeightage", fetchData);
        return fetchData;
//        return fetchData;
    }

    // Bottom Details started.
    @Override
    public List<Map<String, Object>> BottomDetailsStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String materialId = commonIdsObj.getString("product_id");
        String set_no = commonIdsObj.getString("set_no");
        String stock_status = commonIdsObj.getString("stock_status");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("SELECT st.*,  COUNT(*) OVER() AS total_count, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name ")
                .append("FROM sm_warping_bottom_stock_details st ")
                .append("LEFT JOIN sm_product_rm rm ON rm.product_rm_id = st.product_material_id AND rm.is_delete = 0 ")
                .append("WHERE st.is_delete = 0 ");

        if (!materialId.equalsIgnoreCase("")) {
            query.append("AND st.product_material_id = :materialId ");
            params.addValue("materialId", materialId);
        }
        if (!set_no.equalsIgnoreCase("All")) {
            query.append("AND st.set_no = :set_no ");
            params.addValue("set_no", set_no);
        }
        if (!stock_status.equalsIgnoreCase("All")) {
            query.append("AND st.stock_status = :stock_status ");
            params.addValue("stock_status", stock_status);
        }
        query.append(" AND st.bottom_receipt_date BETWEEN :from_date AND :to_date ");
        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);

        // For pagination
        query.append("ORDER BY st.product_material_id ");
        if (entriesPerPage != 0) {
            query.append("LIMIT ").append(pageCurrent).append(", ").append(entriesPerPage);
        }

        System.out.println("Bottom details Stock query: " + query);

        NamedParameterJdbcTemplate namedParameterJdbcTemplates = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplates.queryForList(query.toString(), params);

        return fetchData;
    }


    @Override
    public List<Map<String, Object>> GetBottomDetailsStockReportByDateDetailsToExport(JSONObject commonIdsObj) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String product_id = commonIdsObj.getString("product_id");
        String set_no = commonIdsObj.getString("set_no");
        String stock_status = commonIdsObj.getString("stock_status");


        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        // Build the SELECT clause
        query.append("SELECT st.*, ")
                .append("ROW_NUMBER() OVER(ORDER BY st.product_material_id) AS serial_no, ")
                .append("rm.product_rm_code AS material_code, ")
                .append("rm.product_rm_name AS material_name ")
                .append("FROM sm_warping_bottom_stock_details AS st ")
                .append("LEFT JOIN sm_product_rm AS rm ON rm.product_rm_id = st.product_material_id AND rm.is_delete = 0 ")
                .append("WHERE st.is_delete = 0 "); // Add WHERE to start filtering

        if (!from_date.isEmpty() && !to_date.isEmpty()) {
            query.append(" AND st.bottom_receipt_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
        }

        if (!product_id.isEmpty()) {
            query.append(" AND st.product_material_id = :product_id ");
            params.addValue("product_id", product_id);
        }
        if (!set_no.equalsIgnoreCase("All")) {
            query.append(" AND st.set_no = :set_no ");
            params.addValue("set_no", set_no);
        }
        if (!stock_status.equalsIgnoreCase("All")) {
            query.append("AND st.stock_status = :stock_status ");
            params.addValue("stock_status", stock_status);
        }

        query.append(" ORDER BY st.product_material_id "); // Correct ORDER BY


        // Execute the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println(fetchData);
        System.out.println("Bottom Details Stock Export query: " + query);
        return fetchData;
    }


    @Override
    public List<Map<String, Object>> GetCottonBalesStockReportByDateDetailsToExport(JSONObject commonIdsObj) {

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String plant_id = String.valueOf(commonIdsObj.get("plant_id"));

        // Initialize parameter source and query string
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        // Build the SELECT clause
        query.append("SELECT st.*, ")
                .append("ROW_NUMBER() OVER(ORDER BY st.mixing_chart_cotton_bales_transaction_id) AS serial_no ")
                .append("FROM pt_mixing_chart_cotton_bales AS st ")
                .append("WHERE st.is_delete = 0 ");

        if (!from_date.isEmpty() && !to_date.isEmpty()) {
            query.append(" AND st.mixing_chart_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
        }

        if (!plant_id.equals("0")) {
            query.append(" AND st.plant_id = :plant_id ");
            params.addValue("plant_id", plant_id);
        }

        query.append(" ORDER BY st.mixing_chart_cotton_bales_transaction_id DESC");


        // Execute the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        System.out.println(fetchData);
        System.out.println("Cotton Bales Weightage Stock Export query: " + query);
        return fetchData;
    }


    @Override
    public List<Map<String, Object>> GetSizedBeamStockReportData(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {
        String to_date = commonIdsObj.getString("to_date");
        String from_date = commonIdsObj.getString("from_date");
        int companyId = commonIdsObj.getInt("company_id");
        String beamStatus = commonIdsObj.getString("beam_status");
        String jobType = commonIdsObj.getString("job_type");
        String setNo = commonIdsObj.getString("set_no");
        String beamNo = commonIdsObj.getString("beam_no");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) over() as total_count, ");
        query.append("product_material_name, ");
        query.append("sizing_production_code, ");
        query.append("total_ends, ");
        query.append("beam_inward_type, ");
        query.append("sizing_length, ");
        query.append("set_no, ");
        query.append("job_type, ");
        query.append("sized_beam_status_desc, ");
        query.append("customer_name, ");
        query.append("remaining_length, ");
        query.append("DATE_FORMAT(sizing_production_date, '%d-%m-%Y') AS sizing_production_date, ");
        query.append("DATE_FORMAT(cut_beam_date, '%d-%m-%Y') AS cut_beam_date ");
        query.append("FROM xtv_sizing_production_stock_details ");
        query.append("WHERE company_id = :companyId ");
        params.addValue("companyId", companyId);
        query.append("AND is_delete = 0 ");
        if (!Objects.equals(from_date, "") && !Objects.equals(to_date, "")) {
            query.append("AND sizing_production_date BETWEEN :from_date AND :to_date ");
            params.addValue("from_date", from_date);
            params.addValue("to_date", to_date);
        }
        if (!Objects.equals(beamStatus, "All")) {
            query.append("AND sized_beam_status_desc = :beam_status ");
            params.addValue("beam_status", beamStatus);
        }
        if (!Objects.equals(jobType, "")) {
            query.append("AND job_type = :job_type ");
            params.addValue("job_type", jobType);
        }
        if (!Objects.equals(setNo, "")) {
            query.append("AND set_no = :set_no ");
            params.addValue("set_no", setNo);
        }
        if (!Objects.equals(beamNo, "")) {
            query.append("AND beam_no = :beam_no ");
            params.addValue("beam_no", beamNo);
        }
        if (entriesPerPage != 0) {
            query.append("LIMIT ").append(pageCurrent).append(", ").append(entriesPerPage);
        }
        System.out.println("Sized Beam Stock Export Query : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> sizedStockData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return sizedStockData;
    }

    @Override
    public List<Map<String, Object>> GetLoomProductionWastageReportData(JSONObject commonIdsObj) {
        String to_date = commonIdsObj.getString("to_date");
        String from_date = commonIdsObj.getString("from_date");
        int companyId = commonIdsObj.getInt("company_id");
        String production_wastage_types_name = commonIdsObj.getString("production_wastage_types_name");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("xpwt.production_wastage_types_name, ");
        query.append("COALESCE(SUM(xwpww.wastage_quantity), 0) AS total_wastage ");
        query.append("FROM xmv_production_wastage_types xpwt ");
        query.append("LEFT JOIN xtv_weaving_production_warping_wastage xwpww ");
        query.append("ON xpwt.field_id = xwpww.production_wastage_types_id ");
        query.append("AND xwpww.is_delete = 0 ");
        query.append("AND xwpww.production_date BETWEEN :from_date AND :to_date ");
        query.append("WHERE xpwt.section_id = 18 ");
        query.append("AND xpwt.sub_section_id = 24 ");

        if (!Objects.equals(production_wastage_types_name, "All")) {
            query.append("AND xpwt.production_wastage_types_name = :production_wastage_types_name ");
            params.addValue("production_wastage_types_name", production_wastage_types_name);
        }

        query.append("GROUP BY xpwt.production_wastage_types_name ");

        params.addValue("from_date", from_date);
        params.addValue("to_date", to_date);

        System.out.println("Loom Production Wastage Report Query: " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        return namedParameterJdbcTemplate.queryForList(query.toString(), params);
    }


}

