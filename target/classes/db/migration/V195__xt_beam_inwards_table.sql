INSERT INTO xt_beam_inwards_table (customer_id, beam_inward_type, company_id, company_branch_id, beam_status, financial_year, beam_inwards_date, beam_type, is_delete)
SELECT 0, property_name, 2, 3, 'E', 2024, '2024-11-29', property_id, is_delete
FROM am_properties
WHERE property_master_id = 176 AND properties_master_name = 'SizedBeams';


update am_properties set property_group ='PT' where property_master_id = 176 AND properties_master_name = 'SizedBeams';

update am_properties set property_group ='BI' where property_id IN (1027,1028,1029,1030) AND properties_master_name = 'SizedBeams';