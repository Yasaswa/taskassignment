package com.erp.Common.ExceptionHandling;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExceptionHandlingClass {

	private final CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	public ResponseEntity<Map<String, Object>> handleException(Exception e, int companyId, String message, HttpStatus status, String source_name) {
		e.printStackTrace();
		logError(e, companyId, source_name);
		Map<String, Object> errorResponse = createErrorResponse(message, e.getMessage());
		return ResponseEntity.status(status).body(errorResponse);
	}

	private void logError(Exception e, int companyId, String endpoint) {
		int errorCode = e instanceof SQLException ? ((SQLException) e.getCause()).getErrorCode() : 0;
		String errorMessage = e.getMessage();
		amApplicationErrorLogsServiceImpl.addErrorLog(companyId, "api", endpoint, errorCode, errorMessage);
	}

	public Map<String, Object> createSuccessResponse(String message) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", 1);
		response.put("message", message);
		return response;
	}

	private Map<String, Object> createErrorResponse(String message, String errorDetail) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", 0);
		response.put("message", message);
		response.put("error", errorDetail);
		return response;
	}
}
