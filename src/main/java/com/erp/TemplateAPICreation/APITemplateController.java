package com.erp.TemplateAPICreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/apiCreation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class APITemplateController {

	@Autowired
	private JdbcTemplate executeQuery;


	@PostMapping("/FnCreateAPI/{tableName}")
	public Object createAPI(@PathVariable String tableName) {
		try {
			String pKeyName = "";
			String viewName = "";
			String rptName = "";
			String[] tbNameSplit = tableName.split("_");
			String databaseName = "ERP_PASHUPATI_DEV_1_0";

			for (int splitIndex = 0; splitIndex < tbNameSplit.length; splitIndex++) {
				if (splitIndex == 0) {
					viewName += tbNameSplit[0] + "v_";
					rptName += tbNameSplit[0] + "v_";
				} else {
					viewName += tbNameSplit[splitIndex] + "_";
					rptName += tbNameSplit[splitIndex] + "_";
				}
			}
			viewName = viewName.substring(0, viewName.length() - 1);
			rptName += "rpt";
			String formHeading = convertString(tableName.replaceAll("_", " "));

			int tableExist = executeQuery.queryForObject(
					"SELECT count(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = '" + databaseName + "' and TABLE_NAME ='" + tableName + "'",
					Integer.class);
			int viewExist = executeQuery.queryForObject(
					"SELECT count(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = '" + databaseName + "' and TABLE_NAME ='" + viewName + "'",
					Integer.class);
			int rptExist = executeQuery.queryForObject(
					"SELECT count(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = '" + databaseName + "'and  TABLE_NAME ='" + rptName + "'",
					Integer.class);

			File resourcesDirectory = new File("src/main/java/com/erp/");
			System.out.println(resourcesDirectory.getAbsolutePath());

// ################Package Creation Starts#################################################//			
			if (tableExist != 0) {
				String realPath = resourcesDirectory.getAbsolutePath();
				String createPackage = realPath + "/" + formHeading + "/";
				if (!new File(createPackage).exists()) {
					new File(createPackage).mkdir();

// ################Package Creation Ends#################################################//				

// ################Table Model Creation Starts############################################//
					File apiCreationTemplatePathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/ModelTemplate.txt");
					File apiCreationViewTemplatePathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/ViewModelTemplate.txt");
					File apiCreationRptTemplatePathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/RptModelTemplate.txt");
					String apicreationTemplatePath = apiCreationTemplatePathDirectory.getAbsolutePath();
					String apicreationViewTemplatePath = apiCreationViewTemplatePathDirectory.getAbsolutePath();
					String apicreationRptTemplatePath = apiCreationRptTemplatePathDirectory.getAbsolutePath();

					String line;
					String crModel = createPackage + "/" + "Model";
					new File(crModel).mkdir();

					File crModelFile = new File(crModel + "/C" + formHeading + "Model.java");
					crModelFile.createNewFile();

					File fromFile = new File(apicreationTemplatePath); // creates a new
					FileReader fr = new FileReader(fromFile); // reads the file
					BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream

					String lineConstructor = "public C" + formHeading + "Model ( ";
					FileWriter myWriter = new FileWriter(crModel + "/C" + formHeading + "Model.java");

//				Getting List of columns of table			
					List<Map<String, Object>> listModelProperties = executeQuery.queryForList(
							"SELECT COLUMN_NAME, (case  when DATA_TYPE = 'bigint' then 'Integer'  when DATA_TYPE = 'text' then 'String'  when DATA_TYPE = 'varchar' then 'String'  when DATA_TYPE = 'bit' then 'boolean' when DATA_TYPE = 'datetime' then 'Date' when DATA_TYPE = 'date' then 'Date' when DATA_TYPE = 'decimal' then 'double' else DATA_TYPE end ) As DATA_TYPE FROM  INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + databaseName + "' and  TABLE_NAME = '"
									+ tableName + "'");

					while ((line = br.readLine()) != null) {

						line = line.replaceAll("#TableName#", tableName);
						line = line.replaceAll("#FormHeading#", formHeading);

//					Creating field names In Model
						if (line.contains("#CreateAPIProperties#")) {
							line = "";
							for (int listIndex = 0; listIndex < listModelProperties.size(); listIndex++) {
								String fieldName = (String) listModelProperties.get(listIndex).get("COLUMN_NAME");
								String fieldType = (String) listModelProperties.get(listIndex).get("DATA_TYPE");
								if (listIndex == 0) {
									pKeyName = fieldName;
									line += "@Id	\n@GeneratedValue(strategy = GenerationType.IDENTITY)\n@Column(name = \""
											+ fieldName + "\")\n";
									line += "private  " + " int " + fieldName + ";\n";
									lineConstructor += " int  " + fieldName + " , ";
								} else if (fieldName.equals("created_on")) {
									line += "@CreationTimestamp\n@Column(name = \"created_on\", updatable = false)\n";
									line += "private  " + fieldType + "  " + fieldName + ";\n";
									lineConstructor += fieldType + " " + fieldName + " , ";
								} else {
									line += "private  " + fieldType + "  " + fieldName + ";\n";
									lineConstructor += fieldType + " " + fieldName + " , ";
								}

							}
							lineConstructor = lineConstructor.substring(0, lineConstructor.length() - 3);
						}

//					Creating Getter Setters In Model
						if (line.contains("#CreateAPIPropertiesGetSet#")) {
							line = "";
							for (int listIndex = 0; listIndex < listModelProperties.size(); listIndex++) {
								String fieldName = (String) listModelProperties.get(listIndex).get("COLUMN_NAME");
								String fieldType = (String) listModelProperties.get(listIndex).get("DATA_TYPE");
								if (listIndex == 0) {
									line += "\n public  " + "int" + "  get" + fieldName + "() {\n";
									line += "return  " + fieldName + ";\n }\n";
									line += "public void  set" + fieldName + "(  int  " + fieldName + ") {\n";
									line += "this." + fieldName + " = " + fieldName + ";\n }";
								} else {
									line += "\n public  " + fieldType + "  get" + fieldName + "() {\n";
									line += "return  " + fieldName + ";\n }\n";
									line += "public void  set" + fieldName + "( " + fieldType + "  " + fieldName
											+ ") {\n";
									line += "this." + fieldName + " = " + fieldName + ";\n }";
								}

							}
						}

//					Creating Constructor In Model
						if (line.contains("#CreateAPIConstructor#")) {
							lineConstructor += ")  { \n 	super();\n";
							line = "";
							for (int listIndex = 0; listIndex < listModelProperties.size(); listIndex++) {
								String fieldName = (String) listModelProperties.get(listIndex).get("COLUMN_NAME");
								String fieldType = (String) listModelProperties.get(listIndex).get("DATA_TYPE");
								// line += "private "+ fieldType + " "+ fieldName + ";\n";
								lineConstructor += "this. " + fieldName + "  =  " + fieldName + "; \n";

							}
							lineConstructor += "  } \n";
							lineConstructor += "public C" + formHeading + "Model() {\n super(); \n} \n \n } \n";

							line = lineConstructor;
						}

						line = line + "\n";
						myWriter.write(line);

					}
					myWriter.close();
					fr.close(); // closes the stream and release the resources

					System.out.println("Model is completed!.....");

//################Table Model Creation Ends############################################//

// ################View Model Creation Starts############################################//
					if (viewExist != 0) {
						String vline;

						File crVModelFile = new File(crModel + "/C" + formHeading + "ViewModel.java");
						crVModelFile.createNewFile();

						File vfromFile = new File(apicreationViewTemplatePath); // creates a new
						FileReader vfr = new FileReader(vfromFile); // reads the file
						BufferedReader vbr = new BufferedReader(vfr); // creates a buffering character input stream

						String vlineConstructor = "public C" + formHeading + "ViewModel ( ";
						FileWriter vMyWriter = new FileWriter(crModel + "/C" + formHeading + "ViewModel.java");

//				Getting List of columns of table			
						List<Map<String, Object>> vlistModelProperties = executeQuery.queryForList(
								"SELECT COLUMN_NAME, (case  when DATA_TYPE = 'bigint' then 'Integer' when DATA_TYPE = 'text' then 'String' when DATA_TYPE = 'varchar' then 'String'  when DATA_TYPE = 'bit' then 'boolean' when DATA_TYPE = 'datetime' then 'Date' when DATA_TYPE = 'date' then 'Date' when DATA_TYPE = 'decimal' then 'double' else DATA_TYPE end ) As DATA_TYPE FROM  INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + databaseName + "' and  TABLE_NAME = '"
										+ viewName + "'");

						while ((vline = vbr.readLine()) != null) {

							vline = vline.replaceAll("#TableName#", viewName);
							vline = vline.replaceAll("#FormHeading#", formHeading);

//					Creating field names In Model
							if (vline.contains("#CreateAPIProperties#")) {
								vline = "";
								for (int vlistIndex = 0; vlistIndex < vlistModelProperties.size(); vlistIndex++) {
									String fieldName = (String) vlistModelProperties.get(vlistIndex).get("COLUMN_NAME");
									String fieldType = (String) vlistModelProperties.get(vlistIndex).get("DATA_TYPE");
									if (vlistIndex == 0) {
										vline += "@Id	\n\n";
										vline += "private  " + " int " + fieldName + ";\n";
										vlineConstructor += " int  " + fieldName + " , ";
									} else {
										vline += "private  " + fieldType + "  " + fieldName + ";\n";
										vlineConstructor += fieldType + " " + fieldName + " , ";
									}

								}
								vlineConstructor = vlineConstructor.substring(0, vlineConstructor.length() - 3);
							}

//					Creating Getter Setters In View Model
							if (vline.contains("#CreateAPIPropertiesGetSet#")) {
								vline = "";
								for (int vlistIndex = 0; vlistIndex < vlistModelProperties.size(); vlistIndex++) {
									String fieldName = (String) vlistModelProperties.get(vlistIndex).get("COLUMN_NAME");
									String fieldType = (String) vlistModelProperties.get(vlistIndex).get("DATA_TYPE");
									if (vlistIndex == 0) {
										vline += "\n public  " + "int" + "  get" + fieldName + "() {\n";
										vline += "return  " + fieldName + ";\n }\n";
										vline += "public void  set" + fieldName + "(  int  " + fieldName + ") {\n";
										vline += "this." + fieldName + " = " + fieldName + ";\n }";
									} else {
										vline += "\n public  " + fieldType + "  get" + fieldName + "() {\n";
										vline += "return  " + fieldName + ";\n }\n";
										vline += "public void  set" + fieldName + "( " + fieldType + "  " + fieldName
												+ ") {\n";
										vline += "this." + fieldName + " = " + fieldName + ";\n }";
									}

								}
							}

//					Creating Constructor In Model
							if (vline.contains("#CreateAPIConstructor#")) {
								vlineConstructor += ")  { \n 	super();\n";
								vline = "";
								for (int vlistIndex = 0; vlistIndex < vlistModelProperties.size(); vlistIndex++) {
									String fieldName = (String) vlistModelProperties.get(vlistIndex).get("COLUMN_NAME");
									String fieldType = (String) vlistModelProperties.get(vlistIndex).get("DATA_TYPE");
									// line += "private "+ fieldType + " "+ fieldName + ";\n";
									vlineConstructor += "this. " + fieldName + "  =  " + fieldName + "; \n";

								}
								vlineConstructor += "  } \n";
								vlineConstructor += "public C" + formHeading
										+ "ViewModel() {\n super(); \n} \n \n } \n";

								vline = vlineConstructor;
							}

							vline = vline + "\n";
							vMyWriter.write(vline);

						}
						vMyWriter.close();
						vfr.close(); // closes the stream and release the resources

						System.out.println("View Model is completed!.....");
					}
//################View Model Creation Ends############################################//

// ################RPT Model Creation Starts############################################//
					if (rptExist != 0) {
						String rline;

						File crRModelFile = new File(crModel + "/C" + formHeading + "RptModel.java");
						crRModelFile.createNewFile();

						File rfromFile = new File(apicreationRptTemplatePath); // creates a new
						FileReader rfr = new FileReader(rfromFile); // reads the file
						BufferedReader rbr = new BufferedReader(rfr); // creates a buffering character input stream

						String rlineConstructor = "public C" + formHeading + "RptModel ( ";
						FileWriter rMyWriter = new FileWriter(crModel + "/C" + formHeading + "RptModel.java");

//				Getting List of columns of table			
						List<Map<String, Object>> rlistModelProperties = executeQuery.queryForList(
								"SELECT COLUMN_NAME, (case  when DATA_TYPE = 'bigint' then 'Integer' when DATA_TYPE = 'text' then 'String' when DATA_TYPE = 'varchar' then 'String'  when DATA_TYPE = 'bit' then 'boolean' when DATA_TYPE = 'datetime' then 'Date' when DATA_TYPE = 'date' then 'Date' when DATA_TYPE = 'decimal' then 'double' else DATA_TYPE end ) As DATA_TYPE FROM  INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + databaseName + "' and  TABLE_NAME = '"
										+ rptName + "'");

						while ((rline = rbr.readLine()) != null) {
							rline = rline.replaceAll("#TableName#", rptName);
							rline = rline.replaceAll("#FormHeading#", formHeading);

//					Creating field names In Model
							if (rline.contains("#CreateAPIProperties#")) {
								rline = "";
								for (int rlistIndex = 0; rlistIndex < rlistModelProperties.size(); rlistIndex++) {
									String fieldName = (String) rlistModelProperties.get(rlistIndex).get("COLUMN_NAME");
									String fieldType = (String) rlistModelProperties.get(rlistIndex).get("DATA_TYPE");
									if (rlistIndex == 0) {
										rline += "@Id	\n";
										rline += "private  " + " String " + fieldName + ";\n";
										rlineConstructor += " String  " + fieldName + " , ";
									} else {
										rline += "private  String  " + fieldName + ";\n";
										rlineConstructor += " String  " + fieldName + " , ";
									}

								}
								rlineConstructor = rlineConstructor.substring(0, rlineConstructor.length() - 3);
							}

//					Creating Getter Setters In View Model
							if (rline.contains("#CreateAPIPropertiesGetSet#")) {
								rline = "";
								for (int rlistIndex = 0; rlistIndex < rlistModelProperties.size(); rlistIndex++) {
									String fieldName = (String) rlistModelProperties.get(rlistIndex).get("COLUMN_NAME");
									String fieldType = (String) rlistModelProperties.get(rlistIndex).get("DATA_TYPE");
									if (rlistIndex == 0) {
										rline += "\n public  " + "String" + "  get" + fieldName + "() {\n";
										rline += "return  " + fieldName + ";\n }\n";
										rline += "public void  set" + fieldName + "(  String  " + fieldName + ") {\n";
										rline += "this." + fieldName + " = " + fieldName + ";\n }";
									} else {
										rline += "\n public  String " + "  get" + fieldName + "() {\n";
										rline += "return  " + fieldName + ";\n }\n";
										rline += "public void  set" + fieldName + "( String  " + fieldName + ") {\n";
										rline += "this." + fieldName + " = " + fieldName + ";\n }";
									}

								}
							}

//					Creating Constructor In Model
							if (rline.contains("#CreateAPIConstructor#")) {
								rlineConstructor += ")  { \n 	super();\n";
								rline = "";
								for (int rlistIndex = 0; rlistIndex < rlistModelProperties.size(); rlistIndex++) {
									String fieldName = (String) rlistModelProperties.get(rlistIndex).get("COLUMN_NAME");
									String fieldType = (String) rlistModelProperties.get(rlistIndex).get("DATA_TYPE");
									rlineConstructor += "this. " + fieldName + "  =  " + fieldName + "; \n";

								}
								rlineConstructor += "  } \n";
								rlineConstructor += "public C" + formHeading + "RptModel() {\n super(); \n} \n \n } \n";

								rline = rlineConstructor;
							}

							rline = rline + "\n";
							rMyWriter.write(rline);

						}
						rMyWriter.close();
						rfr.close(); // closes the stream and release the resources

						System.out.println("Rpt Model is completed!.....");
					}
//################RPT Model Creation Ends############################################//

//###################Controller Creation Starts############################################//			

					File apiCreationControllerPathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/ControllerTemplate.txt");
					String apicreationControllerTemplatePath = apiCreationControllerPathDirectory.getAbsolutePath();

					String crController = createPackage + "/" + "Controller";
					new File(crController).mkdir();

					File crControllerFile = new File(crController + "/C" + formHeading + "Controller.java");
					crControllerFile.createNewFile();

					FileWriter controllerMyWriter = new FileWriter(
							crController + "/C" + formHeading + "Controller.java");

					File controllerfromFile = new File(apicreationControllerTemplatePath); // creates a new
					FileReader controllerfr = new FileReader(controllerfromFile); // reads the file
					BufferedReader controllerbr = new BufferedReader(controllerfr); // creates a buffering character
					// input
					// stream
					String controllerLine = "";
					while ((controllerLine = controllerbr.readLine()) != null) {
						controllerLine = controllerLine.replaceAll("#FormHeading#", formHeading);
						controllerLine = controllerLine.replaceAll("Trans_id", pKeyName);
						controllerLine = controllerLine + "\n";
						controllerMyWriter.write(controllerLine);
					}

					controllerMyWriter.close();
					controllerfr.close(); // closes the stream and release the resources

//###################Controller Creation Ends############################################//				

//###################Service Creation Starts############################################//	
					File apiCreationServicePathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/ServiceTemplate.txt");
					String apicreationServiceTemplatePath = apiCreationServicePathDirectory.getAbsolutePath();

					String crService = createPackage + "/" + "Service";
					new File(crService).mkdir();

					File crServiceFile = new File(crService + "/I" + formHeading + "Service.java");
					crServiceFile.createNewFile();

					FileWriter serviceMyWriter = new FileWriter(crService + "/I" + formHeading + "Service.java");

					File servicefromFile = new File(apicreationServiceTemplatePath); // creates a new
					FileReader servicefr = new FileReader(servicefromFile); // reads the file
					BufferedReader servicebr = new BufferedReader(servicefr); // creates a buffering character input
					// stream
					String serviceLine = "";
					while ((serviceLine = servicebr.readLine()) != null) {
						serviceLine = serviceLine.replaceAll("#FormHeading#", formHeading);
						serviceLine = serviceLine.replaceAll("Trans_id", pKeyName);
						serviceLine = serviceLine + "\n";
						serviceMyWriter.write(serviceLine);
					}
					serviceMyWriter.close();
					servicefr.close(); // closes the stream and release the resources

//###################Service Creation Ends############################################//	

//###################Service Implementation Creation  Starts############################################//	
					File apiCreationServiceImplPathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/ServiceImplTemplate.txt");
					String apicreationServiceImplTemplatePath = apiCreationServiceImplPathDirectory.getAbsolutePath();

					String crServiceImpl = createPackage + "/" + "Service";
					new File(crServiceImpl).mkdir();

					File crServiceImplFile = new File(crService + "/C" + formHeading + "ServiceImpl.java");
					crServiceImplFile.createNewFile();

					FileWriter serviceImplMyWriter = new FileWriter(
							crServiceImpl + "/C" + formHeading + "ServiceImpl.java");

					File serviceImplfromFile = new File(apicreationServiceImplTemplatePath); // creates a new
					FileReader serviceImplfr = new FileReader(serviceImplfromFile); // reads the file
					BufferedReader serviceImplbr = new BufferedReader(serviceImplfr); // creates a buffering character
					// input
					// stream
					String serviceImplLine = "";
					while ((serviceImplLine = serviceImplbr.readLine()) != null) {
						serviceImplLine = serviceImplLine.replaceAll("#FormHeading#", formHeading);
						serviceImplLine = serviceImplLine.replaceAll("Trans_id", pKeyName);
						serviceImplLine = serviceImplLine + "\n";
						serviceImplMyWriter.write(serviceImplLine);
					}
					serviceImplMyWriter.close();
					serviceImplfr.close(); // closes the stream and release the resources

//###################Service Implementation Creation  Ends############################################//	

//###################Table Model Repository Creation  Starts############################################//		
					File apiCreationRepositoryPathDirectory = new File(
							resourcesDirectory + "/TemplateAPICreation/RepositoryTemplate.txt");
					String apicreationRepositoryTemplatePath = apiCreationRepositoryPathDirectory.getAbsolutePath();

					String crRepository = createPackage + "/" + "Repository";
					new File(crRepository).mkdir();

					File crRepositoryFile = new File(crRepository + "/I" + formHeading + "Repository.java");
					crRepositoryFile.createNewFile();

					FileWriter RepositoryMyWriter = new FileWriter(crRepositoryFile);

					File RepositoryfromFile = new File(apicreationRepositoryTemplatePath); // creates a new
					FileReader Repositoryfr = new FileReader(RepositoryfromFile); // reads the file
					BufferedReader Repositorybr = new BufferedReader(Repositoryfr); // creates a buffering character
					// input
					// stream
					String RepositoryLine = "";
					while ((RepositoryLine = Repositorybr.readLine()) != null) {
						RepositoryLine = RepositoryLine.replaceAll("#FormHeading#", formHeading);
						RepositoryLine = RepositoryLine.replaceAll("Trans_id", pKeyName);
						RepositoryLine = RepositoryLine + "\n";
						RepositoryMyWriter.write(RepositoryLine);
					}
					RepositoryMyWriter.close();
					Repositoryfr.close(); // closes the stream and release the resources

// ###################Table Model Repository Creation Ends############################################//

//###################View  Model Repository Creation  Starts############################################//	
					if (viewExist != 0) {

						File apiCreationViewRepositoryPathDirectory = new File(
								resourcesDirectory + "/TemplateAPICreation/ViewRepositoryTemplate.txt");
						String apicreationViewRepositoryTemplatePath = apiCreationViewRepositoryPathDirectory
								.getAbsolutePath();

						File crViewRepositoryFile = new File(crRepository + "/I" + formHeading + "ViewRepository.java");
						crViewRepositoryFile.createNewFile();

						FileWriter viewRepositoryMyWriter = new FileWriter(crViewRepositoryFile);

						File viewRepositoryfromFile = new File(apicreationViewRepositoryTemplatePath); // creates a new
						FileReader viewRepositoryfr = new FileReader(viewRepositoryfromFile); // reads the file
						BufferedReader viewRepositorybr = new BufferedReader(viewRepositoryfr); // creates a buffering
						// character
						// input
						// stream
						String viewRepositoryLine = "";
						while ((viewRepositoryLine = viewRepositorybr.readLine()) != null) {
							viewRepositoryLine = viewRepositoryLine.replaceAll("#FormHeading#", formHeading);
							viewRepositoryLine = viewRepositoryLine.replaceAll("Trans_id", pKeyName);
							viewRepositoryLine = viewRepositoryLine + "\n";
							viewRepositoryMyWriter.write(viewRepositoryLine);
						}
						viewRepositoryMyWriter.close();
						viewRepositoryfr.close(); // closes the stream and release the resources

					}
// ###################View Model Repository Creation Ends############################################//

// ###################Rpt Model Repository Creation Starts############################################//
					if (rptExist != 0) {
						File apiCreationRptRepositoryPathDirectory = new File(
								resourcesDirectory + "/TemplateAPICreation/RptRepositoryTemplate.txt");
						String apicreationRptRepositoryTemplatePath = apiCreationRptRepositoryPathDirectory
								.getAbsolutePath();

						File crRptRepositoryFile = new File(crRepository + "/I" + formHeading + "RptRepository.java");
						crRptRepositoryFile.createNewFile();

						FileWriter rptRepositoryMyWriter = new FileWriter(crRptRepositoryFile);

						File rptRepositoryfromFile = new File(apicreationRptRepositoryTemplatePath); // creates a new
						FileReader rptRepositoryfr = new FileReader(rptRepositoryfromFile); // reads the file
						BufferedReader rptRepositorybr = new BufferedReader(rptRepositoryfr); // creates a buffering
						// character
						// input stream
						String rptRepositoryLine = "";
						while ((rptRepositoryLine = rptRepositorybr.readLine()) != null) {
							rptRepositoryLine = rptRepositoryLine.replaceAll("#FormHeading#", formHeading);
							rptRepositoryLine = rptRepositoryLine.replaceAll("Trans_id", pKeyName);
							rptRepositoryLine = rptRepositoryLine + "\n";
							rptRepositoryMyWriter.write(rptRepositoryLine);
						}
						rptRepositoryMyWriter.close();
						rptRepositoryfr.close(); // closes the stream and release the resources

					}
// ###################Rpt Model Repository Creation Ends############################################//					

				}
			} else {
				System.out.println("Table not Exist!.....");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	static String convertString(String s) {
		int ctr = 0;
		int n = s.length();
		char ch[] = s.toCharArray();
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (i == 0)
				ch[i] = Character.toUpperCase(ch[i]);
			if (ch[i] == ' ') {
				ctr++;
				ch[i + 1] = Character.toUpperCase(ch[i + 1]);
				continue;
			} else
				ch[c++] = ch[i];
		}
		return String.valueOf(ch, 0, n - ctr);
	}
}
