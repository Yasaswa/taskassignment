

update cm_godown set godown_name = 'Weaving Loom Shed', godown_short_name = 'Loom Shed' where godown_name = 'Weaving Weaving Godown';
update cm_godown_section set godown_section_name = 'Loom Shed Section', godown_section_short_name = 'Loom Shed' where godown_section_name = 'Weaving Weaving Section';

update cm_godown set is_delete = 1  where  godown_name = 'Weaving Yarn Godown';
update cm_godown_section set is_delete = 1  where  godown_section_name = 'Weaving Yarn Section';
update cm_godown_section_beans set is_delete = 1  where  godown_id = 3 and godown_section_id = 3;

INSERT INTO cm_godown (company_id,company_branch_id,product_type_id,godown_type,godown_name,godown_short_name,godown_area,godown_section_count,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,18,'Both','Weaving Inspection Area','Inspection Area',1.0000,1,1,0,NULL,NULL,NULL,'2023-06-20 16:49:53',NULL,NULL);

INSERT INTO cm_godown_section (company_id,company_branch_id,godown_id,godown_section_name,godown_section_short_name,godown_section_area,section_beans_count,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,12,'Inspection Area Section','Inspection Area',1.0000,1,1,0,NULL,NULL,NULL,NULL,NULL,NULL);


INSERT INTO cm_godown_section_beans (company_id,company_branch_id,godown_id,godown_section_id,godown_section_beans_name,godown_section_beans_short_name,godown_section_beans_area,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,12,50,'NA','NA',1.0000,1,0,NULL,NULL,NULL,NULL,NULL,NULL);
