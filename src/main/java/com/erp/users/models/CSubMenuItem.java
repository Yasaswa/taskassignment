package com.erp.users.models;

// Define a class to represent submenu items with name and icon

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CSubMenuItem {
	private String name;
	private String iconClass;
}