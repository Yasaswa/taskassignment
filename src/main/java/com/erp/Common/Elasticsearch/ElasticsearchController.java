package com.erp.Common.Elasticsearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("api/elasticsearch")
public class ElasticsearchController {

	@Autowired
	ElasticsearchService elasticsearchService;

	private final RestTemplate restTemplate;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public ElasticsearchController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.elasticsearchRestTemplate = null;
	}

//
//	@GetMapping("/getrec")
//	public ResponseEntity<String> getAllRecords() {
//		String elasticsearchUrl = "http://15.235.12.19:9201/erpind/_search";
//
//		ResponseEntity<String> response = restTemplate.getForEntity(elasticsearchUrl, String.class);
//		// Parse the response JSON to extract the desired fields
//		String responseBody = response.getBody();
//		CSmvProductRmBomSummaryModel result = parseResponse(responseBody);
//		// Process and return the extracted data
//		return ResponseEntity.ok(result.getCompany_name());
//
//	}
//
//	private CSmvProductRmBomSummaryModel parseResponse(String responseBody) {
//		try {
//			JsonNode root = objectMapper.readTree(responseBody);
//			JsonNode hits = root.path("hits").path("hits").get(0).path("_source");
//			return objectMapper.treeToValue(hits, CSmvProductRmBomSummaryModel.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	@GetMapping("/search")
	public ResponseEntity<String> search() {
		String elasticsearchUrl = "http://15.235.12.19:9201/erpind/_search";
		String query = buildQuery("Nos", "1");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity<>(query, headers);

		ResponseEntity<String> response = restTemplate.exchange(elasticsearchUrl, HttpMethod.POST, requestEntity,
				String.class);

		return response;
	}

	private String buildQuery(String field1Value, String field2Value) {
//return "{\r\n"
//		+ "   \"query\":{\r\n"
//		+ "      \"match_all\":{}\r\n"
//		+ "   }\r\n"
//		+ "}";
//		return "{" +
//				"\"_source\": [\"product_parent_rm_name\", \"product_parent_rm_tech_spect\"]," +
//	            "\"query\": {" +
//	                "\"bool\": {" +
//	                    "\"must\": [" +
//	                        "{\"term\": {\"company_id\": \"" + field2Value + "\"}}" +
//	                    "]" +
//	                "}" +
//	            "}" +
//	        "}";

//		return"{" +
//			  "\"query\": {"+
//			  "\"bool\": {" +
//			      "\"must\": ["+
//			        "{\"term\": {\"company_id\": \"" + field2Value + "\" } },"+
//	//		        "{\"match\": {\"field2\": \"" + value2 + "\" } }"+
//			        "]"+
////			      "filter": [
////			        { "range": { "date_field": { "gte": "2023-01-01", "lte": "2023-12-31" } } },
////			        { "term": { "category": "electronics" } }
////			      "]"+
//			  "}" +
//	 "}" +
//"}";

//		return "{" +
//			    "\"query\": {" +
////			        "\"filtered\": {" +
////			            "\"query\": {" +
////			                "\"query_string\": {" +
////			                    "\"query\": \"*tom*\"," +
////			                    "\"default_operator\": \"OR\"," +
////			                    "\"fields\": [\"username\"]" +
////			                "}" +
////			            "}," +
////			            "\"filter\": {" +
////			                "\"bool\": {" +
//			                    "\"must\": [" +
//			                        "{\"term\": {\"company_id\": \""+ field2Value +"\"}}," +
////			                        "{\"term\": {\"isPrivate\": \"0\"}}," +
////			                        "{\"term\": {\"isOwner\": \"1\"}}" +
//			                    "]" +
////			                "}" +
////			            "}" +
////			        "}" +
//			    "}" +
//			"}";


		// ----------------  -----------------//

		return "{" +
				"\"_source\": [\"*\"]," +
				"\"query\": {" +
				"\"bool\": {" +
				"\"must\": [" +
				"{\"term\": {\"company_id\": \"" + field2Value + "\"}}," +
				"{\"term\": {\"product_parent_rm_id\": \"6\"}}," +
				"{\"match\": {\"product_child_rm_name\": \"RM2 Category1-A\"}}" +
				"]," +
				"\"must_not\": [" +
//                "{\"term\": {\"company_id\": \"" + field2Value + "\"}}," +
//                "{\"term\": {\"is_delete\": \"true\"}}," +
				"{\"match\": {\"product_child_rm_name\": \"RM2 Catgory1-A\"}}" +
				"]" +
				"}" +
				"}" +
				"}";
	}

}
