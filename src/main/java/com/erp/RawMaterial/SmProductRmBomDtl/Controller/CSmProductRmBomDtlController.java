package com.erp.RawMaterial.SmProductRmBomDtl.Controller;

import com.erp.RawMaterial.SmProductRmBomDtl.Service.ISmProductRmBomDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/SmProductRmBomDtl")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductRmBomDtlController {

	@Autowired
	ISmProductRmBomDtlService iSmProductRmBomDtlService;


}
