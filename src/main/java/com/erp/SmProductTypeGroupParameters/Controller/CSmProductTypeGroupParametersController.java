//package com.erp.SmProductTypeGroupParameters.Controller;
//
//import java.sql.SQLException;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.erp.SmProductTypeGroupParameters.Service.ISmProductTypeGroupParametersService;
//
//
//
//@RestController
//@RequestMapping("/api/SmProductTypeGroupParameters")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class CSmProductTypeGroupParametersController {
//
//	@Autowired
//	ISmProductTypeGroupParametersService iSmProductTypeGroupParametersService;
//
//	@PostMapping("/FnAddUpdateRecord")
//	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ProductTypeParameterGroupData") JSONObject jsonObject) {
//		Map<String, Object> responce = iSmProductTypeGroupParametersService.FnAddUpdateRecord(jsonObject);
//		return responce;
//	}
//
//	
//	@DeleteMapping("/FnDeleteRecord/{product_type_group_parameters_id}/{deleted_by}")
//	public Map<String, Object> FnDeleteRecord(@PathVariable int product_type_group_parameters_id,@PathVariable String deleted_by) {
//		return iSmProductTypeGroupParametersService.FnDeleteRecord(product_type_group_parameters_id,deleted_by);
//
//	}
//	
//	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_group_parameters_id}/{company_id}")
//	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_type_group_parameters_id, @PathVariable int company_id)  {
//		return  iSmProductTypeGroupParametersService.FnShowParticularRecordForUpdate(product_type_group_parameters_id, company_id);
//	}
//
//
// 	
// 
//}
