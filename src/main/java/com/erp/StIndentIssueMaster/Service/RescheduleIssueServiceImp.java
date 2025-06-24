package com.erp.StIndentIssueMaster.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
public class RescheduleIssueServiceImp {

    @Autowired
    private JdbcTemplate executeQuery;

    // Method to handle the rescheduling with its own transaction
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rescheduleStockIssue(List<Map<String, Object>> issuedBatchList, int company_id, int godownId) {
        String storedProcedure = "{call reschedule_stock_issue(?,?,?,?,?)}";
        executeQuery.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall(storedProcedure)) {
                for (Map<String, Object> batch : issuedBatchList) {
                    cs.setString(1, batch.get("goods_receipt_no").toString());
                    cs.setString(2, batch.get("issue_date").toString());
                    cs.setString(3, batch.get("product_material_id").toString());
                    cs.setInt(4, company_id);
                    cs.setInt(5, godownId);
                    cs.addBatch();
                }
                cs.executeBatch();
            }
            return null;
        });
    }
}
