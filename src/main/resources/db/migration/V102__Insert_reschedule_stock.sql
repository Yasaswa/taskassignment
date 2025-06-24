DELIMITER //

CREATE  PROCEDURE `Insert_reschedule_stock`(
    IN goodsReceiptNo VARCHAR(50),
    IN grn_date DATE,
    IN material_id VARCHAR(30)
)
BEGIN
    DECLARE stockDate DATE;
    DECLARE currentDate DATE;

    -- Get the starting stock date and current date
    SET stockDate = grn_date;
    SET currentDate = CURDATE();

    -- Update the stock details for the initial GRN date
    UPDATE sm_product_rm_stock_details
    SET day_closed = 1
    WHERE stock_date = grn_date
    AND goods_receipt_no = goodsReceiptNo
    AND product_rm_id = material_id;

    -- Loop through each day from the stock_date to the current date
    WHILE stockDate < currentDate DO
        -- Move to the next day
        SET stockDate = DATE_ADD(stockDate, INTERVAL 1 DAY);

        -- Insert the same row with the current stock_date
        INSERT INTO sm_product_rm_stock_details (
            company_id, company_branch_id, financial_year,stock_date, day_closed,
            product_type_group, product_type_id, product_rm_id, product_material_unit_id,
            product_material_packing_id, batch_no, batch_expiry_date, supplier_id, goods_receipt_no,
            goods_receipt_version, customer_id, sales_order_no, sales_order_version, so_sr_no,
            customer_order_no, customer_goods_receipt_no, customer_goods_receipt_version, order_quantity,
            order_weight, batch_rate, pending_quantity, pending_weight, opening_quantity, opening_weight,
            reserve_quantity, reserve_weight, excess_quantity, excess_weight, pree_closed_quantity,
            pree_closed_weight, purchase_quantity, purchase_weight, purchase_return_quantity,
            purchase_return_weight, purchase_rejection_quantity, purchase_rejection_weight, jobcard_quantity,
            jobcard_weight, production_issue_quantity, production_issue_weight, production_issue_return_quantity,
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
            difference_weight, closing_balance_quantity, closing_balance_weight, godown_id, godown_section_id,
            godown_section_beans_id, remark, is_active, is_delete, created_by, created_on, modified_by, modified_on,
            deleted_by, deleted_on, total_box_weight, total_quantity_in_box, weight_per_box_item
        )
        SELECT
            company_id, company_branch_id, financial_year, stockDate as stock_date, day_closed,
            product_type_group, product_type_id, product_rm_id, product_material_unit_id,
            product_material_packing_id, batch_no, batch_expiry_date, supplier_id, goods_receipt_no,
            goods_receipt_version, customer_id, sales_order_no, sales_order_version, so_sr_no,
            customer_order_no, customer_goods_receipt_no, customer_goods_receipt_version, order_quantity,
            order_weight, batch_rate, pending_quantity, pending_weight,
            closing_balance_quantity AS opening_quantity, closing_balance_weight AS opening_weight,
            reserve_quantity, reserve_weight, excess_quantity, excess_weight, pree_closed_quantity, pree_closed_weight,
            0 AS purchase_quantity, 0 AS purchase_weight,
            purchase_return_quantity, purchase_return_weight, purchase_rejection_quantity, purchase_rejection_weight,
            jobcard_quantity, jobcard_weight, production_issue_quantity, production_issue_weight,
            production_issue_return_quantity, production_issue_return_weight, production_issue_rejection_quantity,
            production_issue_rejection_weight, production_quantity, production_weight, production_return_quantity,
            production_return_weight, production_rejection_quantity, production_rejection_weight,
            assembly_production_issue_quantity, assembly_production_issue_weight, sales_quantity, sales_weight,
            sales_return_quantity, sales_return_weight, sales_rejection_quantity, sales_rejection_weight,
            customer_receipt_quantity, customer_receipt_weight, customer_return_quantity, customer_return_weight,
            customer_rejection_quantity, customer_rejection_weight, transfer_issue_quantity, transfer_issue_weight,
            transfer_receipt_quantity, transfer_receipt_weight, outsources_out_quantity, outsources_out_weight,
            outsources_in_quantity, outsources_in_weight, outsources_rejection_quantity, outsources_rejection_weight,
            loan_receipt_quantity, loan_receipt_weight, loan_issue_quantity, loan_issue_weight, cancel_quantity,
            cancel_weight, difference_quantity, difference_weight, closing_balance_quantity, closing_balance_weight,
            godown_id, godown_section_id, godown_section_beans_id, remark, is_active, is_delete, created_by,
            created_on, modified_by, modified_on, deleted_by, deleted_on, total_box_weight, total_quantity_in_box,
            weight_per_box_item
        FROM sm_product_rm_stock_details
        WHERE stock_date = DATE_SUB(stockDate, INTERVAL 1 DAY)
        AND goods_receipt_no = goodsReceiptNo
        AND product_rm_id = material_id;
    END WHILE;
END //

DELIMITER ;