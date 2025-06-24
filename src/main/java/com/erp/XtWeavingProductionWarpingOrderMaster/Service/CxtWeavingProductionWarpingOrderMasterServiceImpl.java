package com.erp.XtWeavingProductionWarpingOrderMaster.Service;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderStyleDetailsModel;
import com.erp.XtWeavingProductionOrderMaster.Model.CXtvWeavingProductionOrderStyleDetailsModel;
import com.erp.XtWeavingProductionOrderMaster.Repository.IXtvWeavingProductionOrderStyleDetailsRepository;
import com.erp.XtWeavingProductionWarpingOrderMaster.Repository.IxtWeavingProductionWarpingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class CxtWeavingProductionWarpingOrderMasterServiceImpl implements IxtWeavingProductionWarpingOrderMasterService{


    @Autowired
    private IXtvWeavingProductionOrderStyleDetailsRepository iXtvWeavingProductionOrderStyleDetailsRepository;

    // Removed the circular dependency
    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(String set_no, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            List<CXtvWeavingProductionOrderStyleDetailsModel> inputList =
                    iXtvWeavingProductionOrderStyleDetailsRepository.getSalesOrderStyleDetails(set_no, company_id);

            Map<String, Map<String, Object>> resultMap = new HashMap<>();

            for (CXtvWeavingProductionOrderStyleDetailsModel detail : inputList) {
                String key = detail.getSales_order_no();
                Map<String, Object> output = resultMap.getOrDefault(key, new HashMap<>());

                output.put("company_branch_id", String.valueOf(detail.getCompany_branch_id()));
                output.put("sort_no", String.valueOf(detail.getSort_no()));
                output.put("customer_name", detail.getCustomer_name());
                output.put("company_id", String.valueOf(detail.getCompany_id()));
                output.put("set_no", detail.getSet_no());
                output.put("schedule_date", detail.getSchedule_date());
                output.put("sales_order_no", detail.getSales_order_no());

                // Directly add quantities to the output map
                if (detail.getStyle_variant1() != null) {
                    output.put(detail.getStyle_variant1(), detail.getStyle_variant1_qty());
                }
                if (detail.getStyle_variant1_qty() != null) {
                    double qty = (double) output.getOrDefault("total", 0.0); // Get the current total quantity
                    qty += detail.getStyle_variant1_qty(); // Add the current quantity
                    output.put("total", qty); // Update the total quantity in the output
                }
                resultMap.put(key, output);
            }
            List<Map<String, Object>> WeavingProductionSalesOrderDetailsForWarping = new ArrayList<>(resultMap.values());
            // Print or return the resultList as required
            WeavingProductionSalesOrderDetailsForWarping.forEach(System.out::println);

            responce.put("WeavingProductionSalesOrderDetailsForWarping", WeavingProductionSalesOrderDetailsForWarping);
            responce.put("success", 1);

        } catch (DataAccessException e) {
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



