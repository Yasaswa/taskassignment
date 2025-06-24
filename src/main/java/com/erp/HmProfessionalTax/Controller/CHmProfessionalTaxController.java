package com.erp.HmProfessionalTax.Controller;

import com.erp.HmProfessionalTax.Service.IHmProfessionalTaxService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/HmProfessionalTax")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmProfessionalTaxController {

	@Autowired
	IHmProfessionalTaxService iHmProfessionalTaxService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ProfessionalTaxData") JSONObject jsonObject) {
		Map<String, Object> responce = iHmProfessionalTaxService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{professional_tax_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int company_id, @PathVariable String deleted_by) {
		return iHmProfessionalTaxService.FnDeleteRecord(company_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{gender}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable String gender) {
		Map<String, Object> responce = iHmProfessionalTaxService.FnShowParticularRecordForUpdate(gender);
		return responce;
	}

}
