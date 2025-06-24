package com.erp.Common.EmailUtilities.PropertyReader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {

	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public static Properties loadProperties() throws IOException {
		Properties configuration = new Properties();
		InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties");
		configuration.load(inputStream);
		inputStream.close();
		return configuration;
	}

	public Connection createDatabaseConnection() throws IOException, ClassNotFoundException, SQLException {
		Connection connection = null;
		Properties conf = PropertiesReader.loadProperties();
		String databaseUrl = conf.getProperty("spring.datasource.url");
		String databaseUserName = conf.getProperty("spring.datasource.username");
		String databasePassword = conf.getProperty("spring.datasource.password");

		if (databaseUrl != "" && databaseUserName != "" && databasePassword != "") {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
			return connection;
		} else {
			System.out.println("Error in creating database connection due to uncompleted properties...!");
		}
		return connection;
	}

	public Map<String, Object> getEmailDetailsByCompanyId(int companyId)
			throws ClassNotFoundException, IOException, SQLException {
		PropertiesReader propertiesLoader = new PropertiesReader();
		Connection dbConnection = propertiesLoader.createDatabaseConnection();
		Map<String, Object> response = new HashMap<>();
		if (dbConnection != null) {
			String query = "select from_email_id,from_email_username, from_email_password, smtp_host_name, smtp_port from am_company_settings where email_service_status = TRUE and is_delete = FALSE And company_id = ?";
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setInt(1, companyId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				response.put("from_email_id", resultSet.getString("from_email_id"));
				response.put("from_email_username", resultSet.getString("from_email_username"));
				response.put("from_email_password", resultSet.getString("from_email_password"));
				response.put("smtp_host_name", resultSet.getString("smtp_host_name"));
				response.put("smtp_port", resultSet.getInt("smtp_port"));
			}
		} else {
			System.out.println("Database connection not created...! for method: " + this.getClass());
		}
		return response;
	}

	public Map<String, Object> getEmailTemplateByTemplateName(
//			int companyId,
			String templateName)
			throws ClassNotFoundException, IOException, SQLException {
		PropertiesReader propertiesLoader = new PropertiesReader();
		Connection dbConnection = propertiesLoader.createDatabaseConnection();
		Map<String, Object> response = new HashMap<>();
		if (dbConnection != null) {
//			String query = "select emails_templates_description from am_templates_emails where is_delete = 0 AND company_id = ? AND emails_templates_type = ? ";
			String getEmailTemplateQuery = "select communications_templates_description from amv_templates_communications where is_delete = 0 and communications_operation= 'Email' and communications_templates_type = ? ";
			preparedStatement = dbConnection.prepareStatement(getEmailTemplateQuery);
//			preparedStatement.setInt(1, companyId);
			preparedStatement.setString(1, templateName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				response.put("communications_templates_description", resultSet.getString("communications_templates_description"));
			}
		} else {
			System.out.println("Database connection not created...! for method: " + this.getClass());
		}
		return response;
	}

}
