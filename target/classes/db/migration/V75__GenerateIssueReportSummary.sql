DELIMITER //

CREATE  PROCEDURE `GenerateIssueReportSummary`(
    IN from_date DATE,
    IN to_date DATE,
    IN company_id INT
)
BEGIN
    -- Material Issue Details grouped by cost center name
    SELECT
        `pc`.profit_center_short_name AS profit_center_short_name,
        `pc`.profit_center_name AS profit_center_name,
        `cc`.cost_center_name AS cost_center_name,
        `ct`.`product_category1_name` AS `product_category1_name`,
        SUM(`mi`.`product_material_issue_quantity`) AS `total_issue_quantity`,
        SUM(`mi`.`product_material_issue_quantity` * `mi`.`material_batch_rate`) / SUM(`mi`.`product_material_issue_quantity`) AS `average_batch_rate`,
        SUM(`mi`.`product_material_issue_quantity` * `mi`.`material_batch_rate`) AS total_material_value,
        `mi`.`company_id` AS company_id
    FROM
        `st_indent_material_issue_details` AS `mi`
    LEFT JOIN
        `sm_product_rm` AS `rm`
        ON `rm`.`product_rm_id` = `mi`.`product_material_id`
    LEFT JOIN
        `sm_product_category1` AS `ct`
        ON `ct`.`product_category1_id` = `rm`.`product_category1_id`
    LEFT JOIN
        `sm_product_unit` AS `u`
        ON `u`.`product_unit_id` = `mi`.`product_material_unit_id`
    LEFT JOIN
        `fm_cost_center` AS `cc`
        ON `cc`.`cost_center_id` = `mi`.`cost_center_id`
    LEFT JOIN
        `fm_profit_center` AS `pc`
        ON `pc`.`profit_center_id` = `mi`.`profit_center_id`
    WHERE
        `mi`.`issue_date` BETWEEN from_date AND to_date
        AND `mi`.`issue_item_status` = 'MI'
        AND `mi`.`company_id` = company_id
    GROUP BY
        `cc`.`cost_center_name`, `ct`.`product_category1_name`
    ORDER BY
        `cc`.`cost_center_name` ASC, `ct`.`product_category1_name` ASC;
END //

DELIMITER ;