 
DELIMITER $$

CREATE PROCEDURE `fabric_insert_update_stock`(
    IN p_p_goodsReceiptNo VARCHAR(50), 
    IN p_grn_date DATE, 
    IN p_p_material_id VARCHAR(30),
    IN p_batch_no VARCHAR(30),
    IN p_company_id INT
)
BEGIN
    DECLARE v_stockDate DATE;
    DECLARE v_currentDate DATE;
    DECLARE v_isNewBatch BOOLEAN;
    DECLARE v_issueDate DATE;

    -- Loop variables
    DECLARE v_openQty DECIMAL(10,2);
    DECLARE v_openWeight DECIMAL(10,2);
    DECLARE v_openBoxes INT;
    DECLARE v_reserveQty DECIMAL(10,2);
    DECLARE v_reserveWeight DECIMAL(10,2);
    DECLARE v_reserveBoxes INT;
    DECLARE v_closingQty DECIMAL(10,2);
    DECLARE v_closingWeight DECIMAL(10,2);
    DECLARE v_closingBoxes INT;
    DECLARE v_salesQTY DECIMAL(10,2);
    DECLARE v_salesWeight DECIMAL(10,2);
    DECLARE v_salesBox INT;
    DECLARE v_productionQTY DECIMAL(10,2);
    DECLARE v_productionWeight DECIMAL(10,2);
    DECLARE v_productionBox INT;

    SET v_stockDate = p_grn_date;
    SET v_currentDate = CURDATE();
    SET v_isNewBatch = FALSE;
    SET v_issueDate = p_grn_date;

    -- Check if record exists
    SELECT COUNT(*) > 0 INTO v_isNewBatch
    FROM sm_product_rm_stock_details
    WHERE stock_date = v_stockDate
      AND goods_receipt_no = p_p_goodsReceiptNo
      AND batch_no = p_batch_no
      AND company_id = p_company_id
      AND day_closed = 0
      AND product_rm_id = p_p_material_id;

    -- If new batch, insert missing records for next days
    IF v_isNewBatch THEN

        WHILE v_stockDate < v_currentDate DO
            SET v_stockDate = DATE_ADD(v_stockDate, INTERVAL 1 DAY);

            -- Insert the same row with the current stock_date
        INSERT INTO sm_product_rm_stock_details (
            company_id, company_branch_id, financial_year,stock_date, day_closed,
            product_type_group, product_type_id, product_rm_id, product_material_unit_id, 
            product_material_packing_id, batch_no, batch_expiry_date, supplier_id, goods_receipt_no,
            goods_receipt_version, customer_id, sales_order_no, sales_order_version, so_sr_no,
            customer_order_no, customer_goods_receipt_no, customer_goods_receipt_version, order_quantity,
            order_weight, batch_rate, pending_quantity, pending_weight, opening_quantity, opening_weight,opening_no_of_boxes,
            reserve_quantity, reserve_weight,reserve_no_of_boxes, excess_quantity, excess_weight, pree_closed_quantity,
            pree_closed_weight, purchase_quantity, purchase_weight,purchase_no_of_boxes, purchase_return_quantity,
            purchase_return_weight, purchase_rejection_quantity, purchase_rejection_weight, jobcard_quantity,
            jobcard_weight, production_issue_quantity, production_issue_weight,issue_no_of_boxes, production_issue_return_quantity,
            production_issue_return_weight, production_issue_rejection_quantity, production_issue_rejection_weight,
            production_quantity, production_weight, production_return_quantity, production_return_weight,
            production_rejection_quantity, production_rejection_weight, assembly_production_issue_quantity,
            assembly_production_issue_weight, sales_quantity, sales_weight, sales_return_quantity, sales_return_weight,
            sales_rejection_quantity, sales_rejection_weight, customer_receipt_quantity, customer_receipt_weight,
            customer_return_quantity, customer_return_weight, customer_rejection_quantity, customer_rejection_weight,
            transfer_issue_quantity, transfer_issue_weight, transfer_receipt_quantity, transfer_receipt_weight,
            outsources_out_quantity, outsources_out_weight, outsources_in_quantity, outsources_in_weight,
            outsources_rejection_quantity, outsources_rejection_weight, loan_receipt_quantity, loan_receipt_weight,
            loan_issue_quantity, loan_issue_weight, cancel_quantity, cancel_weight, difference_quantity, 
            difference_weight, closing_balance_quantity, closing_balance_weight,closing_no_of_boxes, godown_id, godown_section_id, 
            godown_section_beans_id, remark, is_active, is_delete, created_by, created_on, modified_by, modified_on,
            deleted_by, deleted_on, total_box_weight, total_quantity_in_box, weight_per_box_item
        )
        SELECT 
            company_id, company_branch_id, financial_year, v_stockDate as stock_date, day_closed,
            product_type_group, product_type_id, product_rm_id, product_material_unit_id, 
            product_material_packing_id, batch_no, batch_expiry_date, supplier_id, goods_receipt_no,
            goods_receipt_version, customer_id, sales_order_no, sales_order_version, so_sr_no,
            customer_order_no, customer_goods_receipt_no, customer_goods_receipt_version, order_quantity,
            order_weight, batch_rate, pending_quantity, pending_weight, 
            closing_balance_quantity AS opening_quantity, closing_balance_weight AS opening_weight, closing_no_of_boxes as opening_no_of_boxes,
            reserve_quantity, reserve_weight,reserve_no_of_boxes, excess_quantity, excess_weight, pree_closed_quantity, pree_closed_weight,
            0 AS purchase_quantity, 0 AS purchase_weight,  0 as purchase_no_of_boxes,
            purchase_return_quantity, purchase_return_weight, purchase_rejection_quantity, purchase_rejection_weight,
            jobcard_quantity, jobcard_weight, 0 as production_issue_quantity, 0 as production_issue_weight, 0 as issue_no_of_boxes,
            0 as production_issue_return_quantity, 0 as production_issue_return_weight, 0 as  production_issue_rejection_quantity, 
            0 as production_issue_rejection_weight, 0 as  production_quantity,0 as  production_weight,0 as  production_return_quantity, 
            0 as production_return_weight, 0 as production_rejection_quantity, 0 as production_rejection_weight, 
            0 as assembly_production_issue_quantity, 0 as assembly_production_issue_weight, 0 as sales_quantity, 0 as sales_weight, 
            0 as sales_return_quantity, 0 as sales_return_weight, 0 as sales_rejection_quantity, 0 as sales_rejection_weight, 
            0 as customer_receipt_quantity,0 as  customer_receipt_weight, 0 as customer_return_quantity,0 as  customer_return_weight, 
            0 as customer_rejection_quantity, 0 as customer_rejection_weight, 0 as transfer_issue_quantity, 0 as transfer_issue_weight, 
            0 as transfer_receipt_quantity, 0 as transfer_receipt_weight, 0 as outsources_out_quantity, 0 as outsources_out_weight, 
            0 as outsources_in_quantity,0 as  outsources_in_weight, 0 as outsources_rejection_quantity, 0 as outsources_rejection_weight, 
            0 as loan_receipt_quantity, 0 as loan_receipt_weight, 0 as loan_issue_quantity, 0 as loan_issue_weight, 0 as cancel_quantity, 
            0 as cancel_weight, 0 as difference_quantity, 0 as difference_weight, closing_balance_quantity, closing_balance_weight, closing_no_of_boxes,
            godown_id, godown_section_id, godown_section_beans_id, remark, is_active, is_delete, created_by, 
            created_on, modified_by, modified_on, deleted_by, deleted_on, total_box_weight, total_quantity_in_box, 
            weight_per_box_item
        FROM sm_product_rm_stock_details
        WHERE stock_date = DATE_SUB(v_stockDate, INTERVAL 1 DAY)
        AND goods_receipt_no = p_p_goodsReceiptNo
        AND product_rm_id = p_p_material_id
       AND batch_no = p_batch_no;
        END WHILE;

        UPDATE sm_product_rm_stock_details
        SET day_closed = 1
        WHERE stock_date != v_currentDate
          AND goods_receipt_no = p_p_goodsReceiptNo
          AND batch_no = p_batch_no
          AND product_rm_id = p_p_material_id;

    ELSE
        -- Handle update case
        WHILE v_issueDate < v_currentDate DO
            SET v_stockDate = DATE_ADD(v_issueDate, INTERVAL 1 DAY);

            -- 1st query: Get issue quantities
            select
             	production_quantity,
                production_weight,
                production_no_of_boxes,
                sales_quantity,
                sales_weight,
                sales_no_of_boxes
            into
            	v_productionQTY,
            	v_productionWeight,
            	v_productionBox,
                v_salesQTY,
                v_salesWeight,
                v_salesBox
            FROM sm_product_rm_stock_details
            WHERE stock_date = v_stockDate
              AND goods_receipt_no = p_p_goodsReceiptNo
              AND product_rm_id = p_p_material_id
              AND batch_no = p_batch_no
              AND company_id = p_company_id;

            -- 2nd query: Get closing and reserve values from previous day
            SELECT
                closing_balance_quantity,
                closing_balance_weight,
                closing_no_of_boxes,
                reserve_quantity,
                reserve_weight,
                reserve_no_of_boxes
            INTO
                v_openQty,
                v_openWeight,
                v_openBoxes,
                v_reserveQty,
                v_reserveWeight,
                v_reserveBoxes
            FROM sm_product_rm_stock_details
            WHERE stock_date = DATE_SUB(v_stockDate, INTERVAL 1 DAY)
              AND goods_receipt_no = p_p_goodsReceiptNo
              AND product_rm_id = p_p_material_id
              AND batch_no = p_batch_no
              AND company_id = p_company_id;

            -- Calculate closing values
            SET v_closingQty = (v_openQty + v_productionQTY) - v_salesQTY;
            SET v_closingWeight = (v_openWeight + v_productionWeight) - v_salesWeight;
            SET v_closingBoxes = (v_openBoxes  + v_productionBox)- v_salesBox;

            -- Update current day's row
            UPDATE sm_product_rm_stock_details
            SET
                opening_quantity = v_openQty,
                opening_weight = v_openWeight,
                opening_no_of_boxes = v_openBoxes,
                reserve_quantity = v_reserveQty,
                reserve_weight = v_reserveWeight,
                reserve_no_of_boxes = v_reserveBoxes,
                closing_balance_quantity = v_closingQty,
                closing_balance_weight = v_closingWeight,
                closing_no_of_boxes = v_closingBoxes
            WHERE stock_date = v_stockDate
              AND goods_receipt_no = p_p_goodsReceiptNo
              AND product_rm_id = p_p_material_id
              AND batch_no = p_batch_no
              AND company_id = p_company_id;

            SET v_issueDate = v_stockDate;
        END WHILE;
    END IF;

END$$

   DELIMITER ;
