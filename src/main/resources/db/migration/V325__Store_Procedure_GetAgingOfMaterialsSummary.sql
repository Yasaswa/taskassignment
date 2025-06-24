DROP PROCEDURE IF EXISTS GetAgingOfMaterialsSummary;

CREATE  PROCEDURE `GetAgingOfMaterialsSummary`(
    IN v_company_id INT,
    IN v_product_type_id INT,
    IN v_productCategory1Id INT,
    IN v_productCategory2Id INT,
    IN page INT,
    IN pageSize INT,
    IN actionType VARCHAR(20)
)
BEGIN
    -- Drop temporary table if it exists
    DROP TEMPORARY TABLE IF EXISTS temp_aging_results;

    -- Create temporary table
    CREATE TEMPORARY TABLE temp_aging_results (
        product_category1_id INT,
        product_category2_id INT,
        product_category1_name VARCHAR(255),
        product_category2_name VARCHAR(255),
        total_1_30 DECIMAL(10,2) DEFAULT 0,
        total_1_30_amount DECIMAL(10,2) DEFAULT 0,
        total_31_60 DECIMAL(10,2) DEFAULT 0,
        total_31_60_amount DECIMAL(10,2) DEFAULT 0,
        total_61_180 DECIMAL(10,2) DEFAULT 0,
        total_61_180_amount DECIMAL(10,2) DEFAULT 0,
        total_181_365 DECIMAL(10,2) DEFAULT 0,
        total_181_365_amount DECIMAL(10,2) DEFAULT 0,
        total_above_365 DECIMAL(10,2) DEFAULT 0,
        total_above_365_amount DECIMAL(10,2) DEFAULT 0,
        total_closing_quantity DECIMAL(10,2) DEFAULT 0,
        total_closing_amount DECIMAL(10,2) DEFAULT 0
    );

    -- Insert summarized data into the temp table
    INSERT INTO temp_aging_results (
        product_category1_id, product_category2_id, product_category1_name, product_category2_name,
        total_1_30, total_1_30_amount, total_31_60, total_31_60_amount,
        total_61_180, total_61_180_amount, total_181_365, total_181_365_amount,
        total_above_365, total_above_365_amount, total_closing_quantity, total_closing_amount
    )
    SELECT
        ct1.product_category1_id,
        ct2.product_category2_id,
        ct1.product_category1_name,
        ct2.product_category2_name,

        -- Sum quantities for each aging bucket
        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 1 AND 30
                THEN st.closing_balance_quantity ELSE 0
            END
        ) AS total_1_30,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 1 AND 30
                THEN st.closing_balance_quantity * st.batch_rate ELSE 0
            END
        ) AS total_1_30_amount,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 31 AND 60
                THEN st.closing_balance_quantity ELSE 0
            END
        ) AS total_31_60,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 31 AND 60
                THEN st.closing_balance_quantity * st.batch_rate ELSE 0
            END
        ) AS total_31_60_amount,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 61 AND 180
                THEN st.closing_balance_quantity ELSE 0
            END
        ) AS total_61_180,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 61 AND 180
                THEN st.closing_balance_quantity * st.batch_rate ELSE 0
            END
        ) AS total_61_180_amount,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 181 AND 365
                THEN st.closing_balance_quantity ELSE 0
            END
        ) AS total_181_365,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) BETWEEN 181 AND 365
                THEN st.closing_balance_quantity * st.batch_rate ELSE 0
            END
        ) AS total_181_365_amount,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) > 365
                THEN st.closing_balance_quantity ELSE 0
            END
        ) AS total_above_365,

        SUM(
            CASE
                WHEN DATEDIFF(CURDATE(), COALESCE(grd.goods_receipt_date, rm.created_on, STR_TO_DATE('2024-12-01', '%Y-%m-%d'))) > 365
                THEN st.closing_balance_quantity * st.batch_rate ELSE 0
            END
        ) AS total_above_365_amount,

        SUM(st.closing_balance_quantity) AS total_closing_quantity,
        SUM(st.closing_balance_quantity * st.batch_rate) AS total_closing_amount
    FROM sm_product_rm_stock_details st
    LEFT JOIN sm_product_rm rm ON rm.product_rm_id = st.product_rm_id AND rm.is_delete = 0
    LEFT JOIN sm_product_rm_technical rt ON rt.product_rm_id = st.product_rm_id AND rt.is_delete = 0
    LEFT JOIN sm_product_category1 ct1 ON ct1.product_category1_id = rm.product_category1_id AND ct1.is_delete = 0
    LEFT JOIN sm_product_category2 ct2 ON ct2.product_category2_id = rt.product_category2_id AND ct2.is_delete = 0
    LEFT JOIN pt_goods_receipt_details grd ON grd.goods_receipt_no = st.goods_receipt_no
        AND grd.product_material_id = st.product_rm_id
        AND grd.company_id = v_company_id
        AND grd.is_delete = 0
    WHERE st.company_id = v_company_id
      AND st.product_type_id = v_product_type_id
      AND st.is_delete = 0
      AND st.day_closed = 0
      AND (v_productCategory1Id = 0 OR v_productCategory1Id IS NULL OR ct1.product_category1_id = v_productCategory1Id)
      AND (v_productCategory2Id = 0 OR v_productCategory2Id IS NULL OR ct2.product_category2_id = v_productCategory2Id)
    GROUP BY ct1.product_category1_id, ct2.product_category2_id;

    -- Return results
--     IF actionType = "getData&paginated" THEN
--         SELECT COUNT(*) AS totalRowCount FROM temp_aging_results;
--         SELECT * FROM temp_aging_results LIMIT page, pageSize;
--     ELSE
--         SELECT * FROM temp_aging_results;
--     END IF;
   IF actionType = "getData&paginated" OR actionType = "" THEN
    -- Ensure `page` and `pageSize` have valid values
    IF page IS NULL OR page = '' THEN
        SET page = 0;
    END IF;

    IF pageSize IS NULL OR pageSize = '' OR pageSize = 0 THEN
        SET pageSize = 50;
    END IF;

    SELECT COUNT(*) AS totalRowCount FROM temp_aging_results;
    SELECT * FROM temp_aging_results LIMIT page, pageSize;
else
    SELECT COUNT(*) AS totalRowCount FROM temp_aging_results;
    SELECT * FROM temp_aging_results;
END IF;

END