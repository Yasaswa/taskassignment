package com.erp.Common.Filter;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesModel;
import com.erp.Common.AmListingFiltersPreferances.Repository.IAmListingFiltersPreferancesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping(value = "/api/filter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilterController {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IAmListingFiltersPreferancesRepository iAmListingFiltersPreferancesRepository;
    @Autowired
    private JdbcTemplate executeQuery;

    @PostMapping("/FnShowFilterRecords")
    public Object FnShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery) {
        Map<String, Object> resp = new HashMap();
        try {
            boolean is_deleteStatus = false;
            String query = "Select ";
            String countCheck = "Select COUNT(*) ";
            JSONObject select = (JSONObject) jsonQuery.get("selectQuery");
            JSONObject where = (JSONObject) jsonQuery.get("whereQuery");
            JSONObject operator = (JSONObject) jsonQuery.get("operators");
            JSONObject groupBy = (JSONObject) jsonQuery.get("group_by");
            JSONObject additionalParam = (JSONObject) jsonQuery.get("additionalParam");
            JSONObject IfGrpByNotSelected = (JSONObject) jsonQuery.get("orderBy");
            JSONObject viewnameJson = (JSONObject) jsonQuery.get("viewname");
            String viewName = "";

            if (!viewnameJson.keySet().isEmpty()) {
                for (String currentKey : viewnameJson.keySet()) {
                    Object key = viewnameJson.get(currentKey);
                    String value = key.toString();
                    viewName = value;
                }

            }

            if (!select.keySet().isEmpty()) {
                for (String currentKey : select.keySet()) {
                    Object key = select.get(currentKey);
                    String value = key.toString();
                    query += value + ", ";
                }
                query = query.substring(0, query.length() - 2);
            } else {
                query += "*";
            }
            query += " FROM " + viewName + " ";
            countCheck += " FROM " + viewName + " ";

            query += " WHERE ";
            countCheck += " WHERE ";

            if (!where.keySet().isEmpty()) {
                for (String currentKey : where.keySet()) {
                    Object key = where.get(currentKey);
                    String value = key.toString();
                    String operatorValue = (String) operator.get(currentKey);
                    String val = String.format("'%s'", value);
                    query += currentKey + " " + operatorValue + " " + val + " AND ";
                }
                for (String currentKey : where.keySet()) {
                    if (!currentKey.equals("is_delete")) {
                        is_deleteStatus = true;
                    }
                }
                if (is_deleteStatus == true) {
                    for (String currentKey : additionalParam.keySet()) {
                        Object key = additionalParam.get(currentKey);
                        String value = key.toString();
                        if (!currentKey.equals("is_delete")) {
                            String val = String.format("'%s'", value);
                            query += currentKey + " " + " = " + val + " AND ";
                        }
                    }
                    query = query.substring(0, query.length() - 5);
                } else {
                    for (String currentKey : additionalParam.keySet()) {
                        Object key = additionalParam.get(currentKey);
                        String value = key.toString();
                        String val = String.format("'%s'", value);
                        query += currentKey + " " + " = " + val + " AND ";
                    }
                    query = query.substring(0, query.length() - 5);
                }
            } else {
                for (String currentKey : additionalParam.keySet()) {
                    Object key = additionalParam.get(currentKey);
                    String value = key.toString();
                    String val = String.format("'%s'", value);
                    query += currentKey + " " + " = " + val + " AND ";
                    countCheck += currentKey + " " + " = " + val + " AND ";
                }
                query = query.substring(0, query.length() - 5);
                countCheck = countCheck.substring(0, countCheck.length() - 5);
            }

//			if (!groupBy.get("GROUP BY").equals("")) {
//				String groupByVal = (String) groupBy.get("GROUP BY");
//				query += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal;
//			} else {
//				for (String currentKey : IfGrpByNotSelected.keySet()) {
//					Object key = IfGrpByNotSelected.get(currentKey);
//					String value = key.toString();
//					query += " " + currentKey + " " + value + " DESC";
//				}
//
//			}

            System.out.println("filter data export query: " + query);
            Set<String> Headerkeys = null;
            List<Map<String, Object>> exportData = executeQuery.queryForList(query);
            if (exportData.size() != 0) {
                Headerkeys = exportData.get(0).keySet();
            }

            resp.put("content", exportData);
            resp.put("Headerkeys", Headerkeys);
//			System.out.println("Filter Export Data: " + resp);
            return resp;

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("success", "0");
            resp.put("data", "");
            resp.put("error", e.getMessage());
            return resp;
        }

        return resp;

    }

//	@PostMapping("/FnMShowFilterRecords/{page}/{size}")
//	public Object FnMShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery, @PathVariable int page,
//	                                   @PathVariable int size) {
//		Map<String, Object> resp = new HashMap();
//		int viewModel = 0;
//		try {
//			boolean is_deleteStatus = false;
//			String query = "Select ";
//			String countCheck = "Select COUNT(*) ";
//			JSONObject select = (JSONObject) jsonQuery.get("selectQuery");
//			JSONObject where = (JSONObject) jsonQuery.get("whereQuery");
//			JSONObject operator = (JSONObject) jsonQuery.get("operators");
//			JSONObject groupBy = (JSONObject) jsonQuery.get("group_by");
//			JSONObject additionalParam = (JSONObject) jsonQuery.get("additionalParam");
//			JSONObject IfGrpByNotSelected = (JSONObject) jsonQuery.get("orderBy");
//			JSONObject viewnameJson = (JSONObject) jsonQuery.get("viewname");
//			String viewName = "";
//
//			if (!viewnameJson.keySet().isEmpty()) {
//				for (String currentKey : viewnameJson.keySet()) {
//					Object key = viewnameJson.get(currentKey);
//					String value = key.toString();
//					viewName = value;
//				}
//
//			}
//
//			if (!select.keySet().isEmpty()) {
//				for (String currentKey : select.keySet()) {
//					Object key = select.get(currentKey);
//					String value = key.toString();
//					query += value + ", ";
//				}
//				query = query.substring(0, query.length() - 2);
//			} else {
//				query += "*";
//			}
//			query += " FROM " + viewName + " ";
//			countCheck += " FROM " + viewName + " ";
//
//			query += " WHERE ";
//			countCheck += " WHERE ";
//
//			if (!where.keySet().isEmpty()) {
//				for (String currentKey : where.keySet()) {
//					Object key = where.get(currentKey);
//					String value = key.toString();
//					String operatorValue = (String) operator.get(currentKey);
//					String val = String.format("'%s'", value);
//					query += currentKey + " " + operatorValue + " " + val + " AND ";
//					countCheck += currentKey + " " + operatorValue + " " + val + " AND ";
//				}
//				for (String currentKey : where.keySet()) {
//					if (!currentKey.equals("is_delete")) {
//						is_deleteStatus = true;
//					}
//				}
//				if (is_deleteStatus == true) {
//					for (String currentKey : additionalParam.keySet()) {
//						Object key = additionalParam.get(currentKey);
//						String value = key.toString();
//						String val = value;
//						if(!currentKey.equals("is_delete")) {
//							val = String.format("'%s'", value);
//						}
//						 if (value.contains(" AND ")) {
//					            String[] dates = value.split(" AND ");
//					            String startDate = dates[0];
//					            String endDate = dates[1];
//					            // Format the date values with single quotes
//					            String formattedStartDate = String.format("'%s'", startDate);
//					            String formattedEndDate = String.format("'%s'", endDate);
//					            // Construct the condition with the 'BETWEEN' operator and formatted dates
//					            query += currentKey + " BETWEEN " + formattedStartDate + " AND " + formattedEndDate + " AND ";
//					            countCheck += currentKey + " BETWEEN " + formattedStartDate + " AND " + formattedEndDate + " AND ";
//					     } else if (!currentKey.equals("is_delete")) {
//									query += currentKey + " " + " = " + val + " AND ";
//									countCheck += currentKey + " " + " = " + val + " AND ";
//						}
//
//
//					}
//					query = query.substring(0, query.length() - 5);
//					countCheck = countCheck.substring(0, countCheck.length() - 5);
//				} else {
//					for (String currentKey : additionalParam.keySet()) {
//						Object key = additionalParam.get(currentKey);
//						String value = key.toString();
//						String val = String.format("'%s'", value);
//						query += currentKey + " " + " = " + val + " AND ";
//						countCheck += currentKey + " " + " = " + val + " AND ";
//					}
//					query = query.substring(0, query.length() - 5);
//					countCheck = countCheck.substring(0, countCheck.length() - 5);
//				}
//			} else {
//
//				if (!additionalParam.keySet().isEmpty()) {
//				    for (String currentKey : additionalParam.keySet()) {
//				        Object key = additionalParam.get(currentKey);
//				        String value = key.toString();
//				        String val = value;
//						if(!currentKey.equals("is_delete")) {
//							val = String.format("'%s'", value);
//						}
//				        // Check if the value is a range (e.g., date range)
//				        if (value.contains(" AND ")) {
//				            String[] dates = value.split(" AND ");
//				            String startDate = dates[0];
//				            String endDate = dates[1];
//				            // Format the date values with single quotes
//				            String formattedStartDate = String.format("'%s'", startDate);
//				            String formattedEndDate = String.format("'%s'", endDate);
//				            // Construct the condition with the 'BETWEEN' operator and formatted dates
//				            query += currentKey + " BETWEEN " + formattedStartDate + " AND " + formattedEndDate + " AND ";
//				            countCheck += currentKey + " BETWEEN " + formattedStartDate + " AND " + formattedEndDate + " AND ";
//				        } else {
//				            // For other parameters, use the default '=' operator
//				            query += currentKey + " = " + val + " AND ";
//				            countCheck += currentKey + " = " + val + " AND ";
//				        }
//				    }
//				    // Remove the trailing 'AND' from the query
//				    query = query.substring(0, query.length() - 5);
//				    countCheck = countCheck.substring(0, countCheck.length() - 5);
//				}
//
//
////				for (String currentKey : additionalParam.keySet()) {
////					Object key = additionalParam.get(currentKey);
////					String value = key.toString();
////					String val = String.format("'%s'", value);
////					query += currentKey + " " + " = " + val + " AND ";
////					countCheck += currentKey + " " + " = " + val + " AND ";
////				}
////				query = query.substring(0, query.length() - 5);
////				countCheck = countCheck.substring(0, countCheck.length() - 5);
//			}
//
//			if (page != 0 && size != 0) {
//				page = size * page;
//			}
//
//			if (!groupBy.get("GROUP BY").equals("")) {
//				String groupByVal = (String) groupBy.get("GROUP BY");
//				query += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal;
//				countCheck += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal + " DESC";
//				if (size != 0) {
//					query += " LIMIT " + page + ", " + size;
//					countCheck += " LIMIT " + size + " OFFSET " + page;
//				}
//			} else {
//				for (String currentKey : IfGrpByNotSelected.keySet()) {
//					Object key = IfGrpByNotSelected.get(currentKey);
//					String value = key.toString();
//					query += " " + currentKey + " " + value + " DESC ";
//					countCheck += " " + currentKey + " " + value + " DESC ";
//					if (size != 0) {
//						query += "LIMIT " + page + ", " + size;
//						countCheck += "LIMIT " + size;
//					}
//				}
//			}
//
//
//
//			System.out.println("filter query: " + query);
//			System.out.println("filter data countCheck: " + countCheck);
//
//			viewModel = executeQuery.queryForObject(countCheck, Integer.class);
//
//			List<Map<String, Object>> cViewModels = executeQuery.queryForList(query);
//			Set<String> Headerkeys = null;
//			if (cViewModels.size() != 0) {
//				Headerkeys = cViewModels.get(0).keySet();
//			}
//
////			System.out.println("Filter Table Data: " + cViewModels.toString());
//			resp.put("content", cViewModels);
//			resp.put("totalElements", viewModel);
//			resp.put("Headerkeys", Headerkeys);
//
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//				return resp;
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//			return resp;
//		}
//
//		return resp;
//
//	}

    @PostMapping("/FnMShowFilterRecords/{page}/{size}")
    public Object FnMShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery, @PathVariable int page, @PathVariable int size) {
        Map<String, Object> resp = new HashMap<>();
        int viewModel = 0;
        try {
            boolean is_deleteStatus = false;
            StringBuilder query = new StringBuilder("Select ");
            StringBuilder countCheck = new StringBuilder("Select COUNT(*) ");
            JSONObject select = jsonQuery.getJSONObject("selectQuery");
            JSONObject where = jsonQuery.getJSONObject("whereQuery");
            JSONObject operator = jsonQuery.getJSONObject("operators");
            JSONObject groupBy = jsonQuery.getJSONObject("group_by");
            JSONObject additionalParam = jsonQuery.getJSONObject("additionalParam");
            JSONObject logicalOperator = jsonQuery.optJSONObject("logicalOperator");
            JSONObject IfGrpByNotSelected = jsonQuery.getJSONObject("orderBy");
            JSONObject viewnameJson = jsonQuery.getJSONObject("viewname");
            String viewName = "";

            if (!viewnameJson.keySet().isEmpty()) {
                for (String currentKey : viewnameJson.keySet()) {
                    Object key = viewnameJson.get(currentKey);
                    String value = key.toString();
                    viewName = value;
                }
            }

            if (!select.keySet().isEmpty()) {
                for (String currentKey : select.keySet()) {
                    Object key = select.get(currentKey);
                    String value = key.toString();
                    query.append(value).append(", ");
                }
                query.setLength(query.length() - 2);
            } else {
                query.append("*");
            }
            query.append(" FROM ").append(viewName).append(" ");
            countCheck.append(" FROM ").append(viewName).append(" ");

            query.append(" WHERE ");
            countCheck.append(" WHERE ");
            if(!additionalParam.isEmpty() && logicalOperator != null && !logicalOperator.isEmpty()) {
                additionalParam = sortJsonObject(additionalParam, logicalOperator);
            }
            if (!where.keySet().isEmpty()) {
                for (String currentKey : where.keySet()) {
                    Object key = where.get(currentKey);
                    String value = key.toString();
                    String operatorValue = operator.getString(currentKey);
                    String val = String.format("'%s'", value);
                    query.append(currentKey).append(" ").append(operatorValue).append(" ").append(val).append(" AND ");
                    countCheck.append(currentKey).append(" ").append(operatorValue).append(" ").append(val).append(" AND ");
                }
                for (String currentKey : where.keySet()) {
                    if (!currentKey.equals("is_delete")) {
                        is_deleteStatus = true;
                    }
                }
                if (is_deleteStatus) {
                    boolean insideOrGroup = false; // Track whether we are inside an OR group

                    for (String currentKey : additionalParam.keySet()) {
                        Object key = additionalParam.get(currentKey);
                        String value = key.toString();
                        String val = value;
                        if (!currentKey.equals("is_delete")) {
                            val = String.format("'%s'", value);
                        }
                        String operatorValue = " AND ";
                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
                            if (logicalOperator.has(currentKey)) {
                                Object operatorKey = logicalOperator.get(currentKey);
                                operatorValue = operatorKey.toString();
                            }
                        }

                        if ("OR".equals((operatorValue).trim())) {
                            if (!insideOrGroup) {
                                // Start a new group of OR conditions
                                query.append(" ( ");
                                countCheck.append(" ( ");
                                insideOrGroup = true;
                            }
                        } else {
                            if (insideOrGroup) {
                                query.setLength(query.length() - 5);
                                countCheck.setLength(countCheck.length() - 5);
                                // Close the current group of OR conditions if we encounter AND
                                query.append(" ) AND ");
                                countCheck.append(" ) AND ");
                                insideOrGroup = false;
                            }
                        }

                        // Append the current condition
                        query.append(currentKey).append(" = ").append(val).append(operatorValue);
                        countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
                    }
                    // Close any remaining open OR groups
                    if (insideOrGroup) {
                        query.append(" ) ");
                        countCheck.append(" ) ");
                    }
//                    for (String currentKey : additionalParam.keySet()) {
//                        Object key = additionalParam.get(currentKey);
//                        String value = key.toString();
//                        String val = value;
//                        String operatorValue = " AND ";
//                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
//                            if (logicalOperator.has(currentKey)) {
//                                Object operatorKey = logicalOperator.get(currentKey);
//                                operatorValue = operatorKey.toString();
//                            }
//                        }
//
//                        if (!currentKey.equals("is_delete")) {
//                            val = String.format("'%s'", value);
//                        }
//                        if (value.contains(" AND ")) {
//                            String[] dates = value.split(" AND ");
//                            String formattedStartDate = String.format("'%s'", dates[0]);
//                            String formattedEndDate = String.format("'%s'", dates[1]);
//                            query.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
//                            countCheck.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
//                        } else if (!currentKey.equals("is_delete")) {
//                            query.append(currentKey).append(" = ").append(val).append(operatorValue);
//                            countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
//                        }
//                    }
                    query.setLength(query.length() - 5);
                    countCheck.setLength(countCheck.length() - 5);
                } else {
//                    for (String currentKey : additionalParam.keySet()) {
//                        Object key = additionalParam.get(currentKey);
//                        String value = key.toString();
//                        String val = String.format("'%s'", value);
//                        String operatorValue = " AND ";
//                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
//                            if (logicalOperator.has(currentKey)) {
//                                Object operatorKey = logicalOperator.get(currentKey);
//                                operatorValue = operatorKey.toString();
//                            }
//                        }
//
//                        query.append(currentKey).append(" = ").append(val).append(operatorValue);
//                        countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
//                    }

                    boolean insideOrGroup = false; // Track whether we are inside an OR group

                    for (String currentKey : additionalParam.keySet()) {
                        Object key = additionalParam.get(currentKey);
                        String value = key.toString();
                        String val = value;
                        if (!currentKey.equals("is_delete")) {
                            val = String.format("'%s'", value);
                        }
                        String operatorValue = " AND ";
                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
                            if (logicalOperator.has(currentKey)) {
                                Object operatorKey = logicalOperator.get(currentKey);
                                operatorValue = operatorKey.toString();
                            }
                        }

                        if ("OR".equals((operatorValue).trim())) {
                            if (!insideOrGroup) {
                                // Start a new group of OR conditions
                                query.append(" ( ");
                                countCheck.append(" ( ");
                                insideOrGroup = true;
                            }
                        } else {
                            if (insideOrGroup) {
                                query.setLength(query.length() - 5);
                                countCheck.setLength(countCheck.length() - 5);
                                // Close the current group of OR conditions if we encounter AND
                                query.append(" ) AND ");
                                countCheck.append(" ) AND ");
                                insideOrGroup = false;
                            }
                        }

                        // Append the current condition
                        query.append(currentKey).append(" = ").append(val).append(operatorValue);
                        countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
                    }
                    // Close any remaining open OR groups
                    if (insideOrGroup) {
                        query.append(" ) ");
                        countCheck.append(" ) ");
                    }
                    query.setLength(query.length() - 5);
                    countCheck.setLength(countCheck.length() - 5);
                }
            } else {
                if (!additionalParam.keySet().isEmpty()) {
//                    for (String currentKey : additionalParam.keySet()) {
//                        Object key = additionalParam.get(currentKey);
//                        String value = key.toString();
//                        String val = value;
//                        String operatorValue = " AND ";
//                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
//                            if (logicalOperator.has(currentKey)) {
//                                Object operatorKey = logicalOperator.get(currentKey);
//                                operatorValue = operatorKey.toString();
//                            }
//                        }
//
//                        if (!currentKey.equals("is_delete")) {
//                            val = String.format("'%s'", value);
//                        }
//                        if (value.contains(" AND ")) {
//                            String[] dates = value.split(" AND ");
//                            String formattedStartDate = String.format("'%s'", dates[0]);
//                            String formattedEndDate = String.format("'%s'", dates[1]);
//                            query.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
//                            countCheck.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
//                        } else {
//                            query.append(currentKey).append(" = ").append(val).append(operatorValue);
//                            countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
//                        }
//                    }

                    boolean insideOrGroup = false; // Track whether we are inside an OR group

                    for (String currentKey : additionalParam.keySet()) {
                        Object key = additionalParam.get(currentKey);
                        String value = key.toString();
                        String val = value;
                        if (!currentKey.equals("is_delete")) {
                            val = String.format("'%s'", value);
                        }
                        String operatorValue = " AND ";
                        if (logicalOperator != null && !logicalOperator.isEmpty()) {
                            if (logicalOperator.has(currentKey)) {
                                Object operatorKey = logicalOperator.get(currentKey);
                                operatorValue = operatorKey.toString();
                            }
                        }

                        if ("OR".equals((operatorValue).trim())) {
                            if (!insideOrGroup) {
                                // Start a new group of OR conditions
                                query.append(" ( ");
                                countCheck.append(" ( ");
                                insideOrGroup = true;
                            }
                        } else {
                            if (insideOrGroup) {
                                query.setLength(query.length() - 5);
                                countCheck.setLength(countCheck.length() - 5);
                                // Close the current group of OR conditions if we encounter AND
                                query.append(" ) AND ");
                                countCheck.append(" ) AND ");
                                insideOrGroup = false;
                            }
                        }
                        if (value.contains(" AND ")) {
                            String[] dates = value.split(" AND ");
                            String formattedStartDate = String.format("'%s'", dates[0]);
                            String formattedEndDate = String.format("'%s'", dates[1]);
                            query.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
                            countCheck.append(currentKey).append(" BETWEEN ").append(formattedStartDate).append(" AND ").append(formattedEndDate).append(" AND ");
                        } else {
                            query.append(currentKey).append(" = ").append(val).append(operatorValue);
                            countCheck.append(currentKey).append(" = ").append(val).append(operatorValue);
                        }
                    }
                    // Close any remaining open OR groups
                    if (insideOrGroup) {
                        query.append(" ) ");
                        countCheck.append(" ) ");
                    }
                    query.setLength(query.length() - 5);
                    countCheck.setLength(countCheck.length() - 5);
                }
            }

            // Conditionally include LIKE part of the query
//            if (jsonQuery.has("LIKE")) {
//                JSONArray likeQueryArray = jsonQuery.getJSONArray("LIKE");
//                if (likeQueryArray.length() > 0) {
//                    if (where.length() > 0 || additionalParam.length() > 0) {
//                        query.append(" AND ");
//                        countCheck.append(" AND ");
//                    }
//                    for (int i = 0; i < likeQueryArray.length(); i++) {
//                        JSONObject likeCondition = likeQueryArray.getJSONObject(i);
//                        String column = likeCondition.getString("field");
//                        String value = likeCondition.getString("value");
//                        if (i == 0) query.append("(");
//                        query.append(column).append(" LIKE '%").append(value).append("%'");
//                        countCheck.append(column).append(" LIKE '%").append(value).append("%'");
//                        if (i < likeQueryArray.length() - 1) {
//                            query.append(" OR ");
//                            countCheck.append(" OR ");
//                        }
//
//                        if (likeQueryArray.length() - 1 == i) {
//                            query.append(")");
//                        }
//                    }
//                }
//            }

            if (jsonQuery.has("CONDITIONS")) {
                JSONArray conditionsArray = jsonQuery.getJSONArray("CONDITIONS");
                if (conditionsArray.length() > 0) {
                    if (where.length() > 0 || additionalParam.length() > 0) {
                        query.append(" AND ");
                        countCheck.append(" AND ");
                    }

                    for (int i = 0; i < conditionsArray.length(); i++) {
                        JSONObject condition = conditionsArray.getJSONObject(i);
                        String column = condition.getString("field");

                        // Fetch operator1 for the current condition (LIKE, =, >, <, IN, etc.)
                        String operator1 = condition.has("operator") ? condition.getString("operator") : "LIKE";

                        // Fetch the logical operator1 from each condition (AND, OR)
                        String logicalOperator1 = condition.has("logicalOperator") ? condition.getString("logicalOperator") : "AND";

                        if (i == 0) {
                            query.append("(");
                            countCheck.append("(");
                        }

                        if ("IN".equals(operator1)) {
                            // If operator1 is IN, fetch the array of values
                            JSONArray valuesArray = condition.getJSONArray("value");
                            StringBuilder inValues = new StringBuilder();

                            // Construct the IN clause with comma-separated values
                            for (int j = 0; j < valuesArray.length(); j++) {
                                inValues.append("'").append(valuesArray.getString(j)).append("'");
                                if (j < valuesArray.length() - 1) {
                                    inValues.append(", ");
                                }
                            }

                            query.append(column).append(" IN (").append(inValues).append(")");
                            countCheck.append(column).append(" IN (").append(inValues).append(")");

                        } else if ("LIKE".equals(operator1)) {
                            // Handle LIKE operator1
                            String value = condition.getString("value");
                            query.append(column).append(" LIKE '%").append(value).append("%'");
                            countCheck.append(column).append(" LIKE '%").append(value).append("%'");

                        } else {
                            // Handle other operators (e.g., =, >, <)
                            String value = condition.getString("value");
                            query.append(column).append(" ").append(operator1).append(" '").append(value).append("'");
                            countCheck.append(column).append(" ").append(operator1).append(" '").append(value).append("'");
                        }

                        // Append logical operator1 (AND/OR) between conditions
                        if (i < conditionsArray.length() - 1) {
                            query.append(" ").append(logicalOperator1).append(" ");
                            countCheck.append(" ").append(logicalOperator1).append(" ");
                        }

                        if (conditionsArray.length() - 1 == i) {
                            query.append(")");
                            countCheck.append(")");
                        }
                    }
                }
            }

            if (page != 0 && size != 0) {
                page = size * page;
            }

            if (!groupBy.get("GROUP BY").equals("")) {
                String groupByVal = groupBy.getString("GROUP BY");
                query.append(" GROUP BY ").append(groupByVal).append(" ORDER BY ").append(groupByVal);
                countCheck.append(" GROUP BY ").append(groupByVal).append(" ORDER BY ").append(groupByVal).append(" DESC");
                if (size != 0) {
                    query.append(" LIMIT ").append(page).append(", ").append(size);
                    countCheck.append(" LIMIT ").append(size).append(" OFFSET ").append(page);
                }
            } else {
                for (String currentKey : IfGrpByNotSelected.keySet()) {
                    Object key = IfGrpByNotSelected.get(currentKey);
                    String value = key.toString();
                    query.append(" ").append(currentKey).append(" ").append(value).append(" DESC ");
                    countCheck.append(" ").append(currentKey).append(" ").append(value).append(" DESC ");
                    if (size != 0) {
                        query.append("LIMIT ").append(page).append(", ").append(size);
                        countCheck.append("LIMIT ").append(size);
                    }
                }
            }

            System.out.println("filter query: " + query);
            System.out.println("filter data countCheck: " + countCheck);

            viewModel = executeQuery.queryForObject(countCheck.toString(), Integer.class);

            List<Map<String, Object>> cViewModels = executeQuery.queryForList(query.toString());
            Set<String> Headerkeys = null;
            if (cViewModels.size() != 0) {
                Headerkeys = cViewModels.get(0).keySet();
            }

//        System.out.println("Filter Table Data: " + cViewModels.toString());
            resp.put("content", cViewModels);
            resp.put("totalElements", viewModel);
            resp.put("Headerkeys", Headerkeys);

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());
                return resp;

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("success", "0");
            resp.put("data", "");
            resp.put("error", e.getMessage());
            return resp;
        }

        return resp;
    }


    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddListingFilterPreferenceData(@RequestBody CAmListingFiltersPreferancesModel amListingFiltersPreferancesModel) {
        Map<String, Object> responce = new HashMap<>();

        int company_id = amListingFiltersPreferancesModel.getCompany_id();
        try {

            //update Listing Filters Preferences
            iAmListingFiltersPreferancesRepository.updateListingFilterPreferenceRecord(amListingFiltersPreferancesModel.getUser_id(), amListingFiltersPreferancesModel.getModules_forms_id(), amListingFiltersPreferancesModel.getReport_type(), company_id);

            // Save amListingFiltersPreferancesModel
            CAmListingFiltersPreferancesModel respListingFiltersPreferancesModel = iAmListingFiltersPreferancesRepository.save(amListingFiltersPreferancesModel);


            responce.put("success", 1);
            responce.put("data", respListingFiltersPreferancesModel);
            responce.put("error", "");
            responce.put("message", "Record added successfully!...");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/FnShowFilterRecords/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", sqlEx.getMessage());
                return responce;

            }
        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;

    }

    public static JSONObject sortJsonObject(JSONObject additionalParam, JSONObject logicalOperator) throws JSONException {
        // Create a LinkedHashMap to maintain the order of keys
        Map<String, Object> orderedMap = new LinkedHashMap<>();

        // First add all keys not in logicalOperator
        for (String key : additionalParam.keySet()) {
            if (!logicalOperator.has(key)) {
                orderedMap.put(key, additionalParam.get(key));
            }
        }

        // Then add all keys that are in logicalOperator
        for (String key : logicalOperator.keySet()) {
            if (additionalParam.has(key)) {
                orderedMap.put(key, additionalParam.get(key));
            }
        }

        // Convert LinkedHashMap to JSONObject
        return new JSONObject(orderedMap);
    }


}



