

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDetailsStockAndUpdateStockSummary`()
begin

UPDATE
	sm_product_rm_stock_summary pss
JOIN (
	SELECT
		product_rm_id,
		company_id,
		-- Stock Date and Other Aggregations (Filtered by is_delete = 0 and stock_date range)
		SUM(purchase_no_of_boxes) as total_purchase_no_of_boxes,
		SUM(purchase_quantity) as total_purchase_quantity,
		SUM(purchase_weight) as total_purchase_weight,
		
		SUM(issue_no_of_boxes) as total_issue_no_of_boxes,
		SUM(production_issue_quantity) as total_production_issue_quantity,
		SUM(production_issue_weight) as total_production_issue_weight,
		
		-- Closing Balance Aggregations (Filtered by day_closed = 0 and is_delete = 0)
		SUM(case when day_closed = 0 and is_delete = 0 then closing_no_of_boxes else 0 end) as total_closing_no_of_boxes,
		SUM(case when day_closed = 0 and is_delete = 0 then closing_balance_quantity else 0 end) as total_closing_balance_quantity,
		SUM(case when day_closed = 0 and is_delete = 0 then closing_balance_weight else 0 end) as total_closing_balance_weight,
		
		SUM(case when day_closed = 0 and is_delete = 0 then opening_no_of_boxes else 0 end) as total_opening_no_of_boxes,
		SUM(case when day_closed = 0 and is_delete = 0 then opening_quantity else 0 end) as total_opening_quantity,
		SUM(case when day_closed = 0 and is_delete = 0 then opening_weight else 0 end) as total_opening_weight,
		
		SUM(case when day_closed = 0 and is_delete = 0 then reserve_no_of_boxes else 0 end) as total_reserve_no_of_boxes,
		SUM(case when day_closed = 0 and is_delete = 0 then reserve_quantity else 0 end) as total_reserve_quantity,
		SUM(case when day_closed = 0 and is_delete = 0 then reserve_weight else 0 end) as total_reserve_weight,
		
ROUND(
    IFNULL(
        SUM(
            CASE 
                WHEN day_closed = 0 AND is_delete = 0 THEN closing_balance_quantity * batch_rate
                ELSE 0 
            END
        ) / NULLIF(
            SUM(
                CASE 
                    WHEN day_closed = 0 AND is_delete = 0 THEN closing_balance_quantity
                    ELSE 0 
                END
            ), 
            0
        ), 
        0
    ), 
    2
) AS closing_average_batch_rate

	FROM sm_product_rm_stock_details
	WHERE is_delete = 0
	GROUP BY product_rm_id, company_id
) psd ON
	pss.product_rm_id = psd.product_rm_id
	AND pss.company_id = psd.company_id
set
	pss.purchase_quantity = psd.total_purchase_no_of_boxes,
	pss.purchase_quantity = psd.total_purchase_quantity,
	pss.purchase_weight = psd.total_purchase_weight,
	
	pss.issue_no_of_boxes = psd.total_issue_no_of_boxes,
	pss.production_issue_quantity = psd.total_production_issue_quantity,
	pss.production_issue_weight = psd.total_production_issue_weight,
	
	pss.closing_no_of_boxes = psd.total_closing_no_of_boxes,
	pss.closing_balance_quantity = psd.total_closing_balance_quantity,
	pss.closing_balance_weight = psd.total_closing_balance_weight,
	
	pss.opening_no_of_boxes = psd.total_opening_no_of_boxes,
	pss.opening_quantity = psd.total_opening_quantity,
	pss.opening_weight = psd.total_opening_weight,
	
	pss.reserve_no_of_boxes = psd.total_reserve_no_of_boxes,
	pss.reserve_quantity = psd.total_reserve_quantity,
	pss.reserve_weight = psd.total_reserve_weight,
	pss.material_rate = psd.closing_average_batch_rate
WHERE  pss.is_delete = 0
and pss.day_closed = 0;

end

//
DELIMITER ;












