
DELIMITER //

CREATE PROCEDURE `reschedule_stock_issue`(
    IN goodsReceiptNo VARCHAR(50),
    IN issueDate DATE,
    IN material_id VARCHAR(30)
)
BEGIN
    DECLARE stockDate DATE;
    DECLARE currentDate DATE;
    DECLARE openQty DECIMAL(10,2);
    DECLARE openWeight DECIMAL(10,2);
    DECLARE openBoxes INT;
    DECLARE reserveQty DECIMAL(10,2);
    DECLARE reserveWeight DECIMAL(10,2);
    DECLARE reserveBoxes INT;
    DECLARE closingQty DECIMAL(10,2);
    DECLARE closingWeight DECIMAL(10,2);
    DECLARE closingBoxes INT;
    DECLARE issueQTY DECIMAL(10,2);
    DECLARE issueWeight DECIMAL(10,2);
    DECLARE issueBox INT;



    -- Loop through each day from the issueDate to the current date
    WHILE issueDate < currentDate DO
        -- Move to the next day
        SET stockDate = DATE_ADD(issueDate, INTERVAL 1 DAY);

       -- Get the previous day's data
        SELECT
             production_issue_quantity as issueQTY, production_issue_weight as issueWeight, issue_no_of_boxes as issueBox
        INTO issueQTY, issueWeight, issueBox
        FROM sm_product_rm_stock_details
        WHERE stock_date = stockDate
        AND goods_receipt_no = goodsReceiptNo
        AND product_rm_id = material_id;

        -- Get the previous day's data
        SELECT
             (closing_balance_quantity - issueQTY) as closingQty, (closing_balance_weight - issueWeight) as closingWeight
             , (closing_no_of_boxes - issueBox) as closingBoxes,
            reserve_quantity, reserve_weight, reserve_no_of_boxes,
            closing_balance_quantity AS openQty,
            closing_balance_weight AS openWeight,
            closing_no_of_boxes AS openBoxes
        INTO closingQty, closingWeight, closingBoxes, reserveQty, reserveWeight, reserveBoxes, openQty, openWeight, openBoxes
        FROM sm_product_rm_stock_details
        WHERE stock_date = DATE_SUB(stockDate, INTERVAL 1 DAY)
        AND goods_receipt_no = goodsReceiptNo
        AND product_rm_id = material_id;


        -- Update the stock details for the current stockDate
        UPDATE sm_product_rm_stock_details
        SET
            opening_quantity = openQty,
            opening_weight = openWeight,
            opening_no_of_boxes = openBoxes,
            reserve_quantity = reserveQty,
            reserve_weight = reserveWeight,
            reserve_no_of_boxes = reserveBoxes,
            closing_balance_quantity = closingQty,
            closing_balance_weight = closingWeight,
            closing_no_of_boxes = closingBoxes
        WHERE stock_date = stockDate
        AND goods_receipt_no = goodsReceiptNo
        AND product_rm_id = material_id;

        -- Move the issueDate to the next day for the loop
        SET issueDate = stockDate;
    END WHILE;

end //

DELIMITER ;