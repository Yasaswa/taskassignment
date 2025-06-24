
create table `hm_attendance_status` (
  `company_id` varchar(100) not null default '1' COMMENT '* Default from Sesssion  ',
  `attendance_status_id` bigint(20) not null auto_increment COMMENT '* Auto Increamentd  ',
  `attendance_status_name` varchar(255) not null COMMENT '* Data Entry Text box with Validation alpha numeric,Max length No duplicate ',
  `attendance_status_short_name` varchar(50) default null COMMENT '* Data Entry Text box with Validation Captial  alpha  No duplicate  ',
 `is_active` bit(1) default b'1',
  `is_delete` bit(1) default b'0',
  `created_by` varchar(255) default '1',
  `created_on` datetime default null,
  `modified_by` varchar(255) default null,
  `modified_on` datetime default null,
  `deleted_by` varchar(255) default null,
  `deleted_on` datetime default null,
  primary key (`attendance_status_id`)
) engine = InnoDB auto_increment = 1 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;

INSERT INTO hm_attendance_status (
    company_id,
    attendance_status_id,
    attendance_status_name,
    attendance_status_short_name,
    is_active,
    is_delete,
    created_by,
    created_on,
    modified_by,
    modified_on,
    deleted_by,
    deleted_on
) VALUES
('1', 1, 'Present', 'PR', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-01-18 15:04:05', 'Dakshabhi Deepak S (Managing Director)', '2024-01-18 15:04:05', NULL, NULL),
('1', 2, 'Absent', 'AB', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-01-18 15:04:33', 'Dakshabhi Deepak S (Managing Director)', '2024-01-18 15:04:33', NULL, NULL),
('1', 3, 'Half Day', 'HF', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-02 16:59:39', 'Dakshabhi Deepak S (Managing Director)', '2024-02-02 16:59:39', NULL, NULL),
('1', 4, 'Out Door Duty', 'OD', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-02 17:11:51', 'Dakshabhi Deepak S (Managing Director)', '2024-02-02 17:11:51', NULL, NULL),
('1', 5, 'WeeklyOff', 'WO', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL),
('1', 6, 'Leaves', 'LV', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL),
('1', 7, 'Holiday', 'HD', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL),
('1', 8, 'C-OFF', 'CF', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL),
('1', 9, 'Holiday Present', 'HP', b'1', b'0', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL),
('1', 10, 'General', 'GN', b'1', b'1', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', 'Dakshabhi Deepak S (Managing Director)', '2024-02-16 12:08:42', NULL, NULL);
