package com.erp.SmMaterialStockManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.erp.SmMaterialStockManagement.Service.ISmMaterialStockManagementService;

@RestController
@RequestMapping("/api/SmMaterialStockManagement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmMaterialStockManagementController {

	@Autowired
	ISmMaterialStockManagementService iSmProductMaterialStockNewService;
}
