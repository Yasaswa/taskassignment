/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.controllers;

import com.erp.AmModulesForms.Repository.IAmModulesFormsViewRepository;
import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessViewRepository;
import com.erp.AmModulesSubMenu.Repository.IAmModulesSubMenuViewRepository;
import com.erp.Common.AmListingFiltersPreferances.Model.CAmListingFiltersPreferancesViewModel;
import com.erp.Common.AmListingFiltersPreferances.Repository.IAmListingFiltersPreferancesViewRepository;
import com.erp.Company.Companies.Model.CCompanyViewModel;
import com.erp.security.auth.JwtUtils;
import com.erp.security.auth.TokenRefreshException;
import com.erp.security.services.UserDetailsImpl;
import com.erp.users.models.CSubMenuItem;
import com.erp.users.models.RefreshToken;
import com.erp.users.payload.request.LoginRequest;
import com.erp.users.payload.request.SignupRequest;
import com.erp.users.payload.response.MessageResponse;
import com.erp.users.payload.response.UserInfoResponse;
import com.erp.users.repository.RoleRepository;
import com.erp.users.repository.UserRepository;
import com.erp.users.service.RefreshTokenService;
import com.erp.users.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	Userservice userservice;

	@Autowired
	IAmModulesFormsViewRepository iAmModulesFormsViewRepository;

	@Autowired
	IAmModulesFormsUserAccessViewRepository iAmModulesFormsUserAccessViewRepository;

	@Autowired
	IAmModulesSubMenuViewRepository iAmModulesSubMenuViewRepository;

	@Autowired
	IAmListingFiltersPreferancesViewRepository iAmListingFiltersPreferancesViewRepository;

	@Autowired
	RefreshTokenService refreshTokenService;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		//set authentications
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		if (Integer.parseInt(userDetails.getCompany_access()) != 0 && loginRequest.getCompany_id() != Integer.parseInt(userDetails.getCompany_access())) {
			System.out.println("Permission rejected for specific company");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied: Access is not granted for this company.");
		}

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUser_code());
			ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

			List<CAmModulesFormsUserAccessViewModel> userAccessDetails = new ArrayList<>();
			List<CAmListingFiltersPreferancesViewModel> listingFilterPreferenceDetails = new ArrayList<>();
			List<Map<String, Object>> pageRoutes = null;
			String company_access = "";

			if (userDetails != null) {
	//			Get forms whose has access to this user from amv_modules_forms_user_access
				userAccessDetails = iAmModulesFormsUserAccessViewRepository.getAllModuleForms(userDetails.getUser_code());
				listingFilterPreferenceDetails = iAmListingFiltersPreferancesViewRepository.getAllListingFilterPreferences(userDetails.getUser_id(), userDetails.getCompany_id());
				List<String> getAllMenues = userAccessDetails.stream().map(CAmModulesFormsUserAccessViewModel::getModules_menu_name).distinct().collect(Collectors.toList());
				if (userAccessDetails != null) {
	//			    Page routes file content
					pageRoutes = createPageRoutes(userAccessDetails, getAllMenues);
					company_access = userAccessDetails.get(0).getCompany_access();
				}
			}
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
					.body(
							new UserInfoResponse(userDetails.getUser_code(), userDetails.getUser_id(), userDetails.getCompany_id(), userDetails.getCompany_branch_id(), userDetails.getDepartment_id(), userDetails.getUsername(),
									userDetails.getUser(), userDetails.getUser_type(), userDetails.getCompany_name(), userDetails.getCompany_branch_name(),userDetails.getDepartment_name(),
									userDetails.getCompany_branch_address(), userDetails.getBranch_short_name(), pageRoutes, userAccessDetails, listingFilterPreferenceDetails, company_access
							));
	}


	private List<Map<String, Object>> createPageRoutes(List<CAmModulesFormsUserAccessViewModel> getAllModuleForms, List<String> getAllMenues) {
		List<Map<String, Object>> pageRoutes = new ArrayList<>();

		// Masters
		List<CSubMenuItem> getAllSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Masters"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		if (getAllSubMenu.size() != 0) {
			Map<String, Object> masters = new HashMap<>();
			masters.put("name", "Masters");

			List<Map<String, Object>> mastersCollapse = new ArrayList<>();

			getAllSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Masters"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				mastersCollapse.add(common);

			});
			masters.put("collapse", mastersCollapse);
			pageRoutes.add(masters);
		}
		// Purchase Transaction
		List<CSubMenuItem> getAllPurchaseSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Purchase"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());
		if (getAllPurchaseSubMenu.size() != 0) {
			Map<String, Object> purchase = new HashMap<>();

			purchase.put("name", "Purchase");
			List<Map<String, Object>> purchaseCollapse = new ArrayList<>();

			getAllPurchaseSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Purchase"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				purchaseCollapse.add(common);

			});
			purchase.put("collapse", purchaseCollapse);
			pageRoutes.add(purchase);
		}

//		Sales Transaction
		List<CSubMenuItem> getAllSalesSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Sales"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		Map<String, Object> sales = new HashMap<>();

		if (getAllSalesSubMenu.size() != 0) {
			sales.put("name", "Sales");
			List<Map<String, Object>> salesCollapse = new ArrayList<>();

			getAllSalesSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Sales"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				salesCollapse.add(common);

			});
			sales.put("collapse", salesCollapse);
			pageRoutes.add(sales);
		}

//		Finance Transactions
		List<CSubMenuItem> getAllFinanceSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Finance"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		Map<String, Object> finance = new HashMap<>();

		if (getAllFinanceSubMenu.size() != 0) {

			finance.put("name", "Finance");
			List<Map<String, Object>> financeCollapse = new ArrayList<>();

			getAllFinanceSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Finance"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				financeCollapse.add(common);

			});
			finance.put("collapse", financeCollapse);
			pageRoutes.add(finance);

		}

		// Production Transaction
		List<CSubMenuItem> getAllProductionSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Production"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		Map<String, Object> production = new HashMap<>();

		if (getAllProductionSubMenu.size() != 0) {
			production.put("name", "Production");
			List<Map<String, Object>> productionCollapse = new ArrayList<>();

			getAllProductionSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Production"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				productionCollapse.add(common);

			});
			production.put("collapse", productionCollapse);
			pageRoutes.add(production);
		}

		// HR Payroll Transaction
		List<CSubMenuItem> getAllHrPayrollSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("HR Payroll"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		Map<String, Object> hrPayroll = new HashMap<>();

		if (getAllHrPayrollSubMenu.size() != 0) {
			hrPayroll.put("name", "HR Payroll");
			List<Map<String, Object>> hrPayrollCollapse = new ArrayList<>();

			getAllHrPayrollSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("HR Payroll"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				hrPayrollCollapse.add(common);

			});
			hrPayroll.put("collapse", hrPayrollCollapse);
			pageRoutes.add(hrPayroll);
		}

//		Registers
		List<CSubMenuItem> getAllRegistersSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Registers"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		Map<String, Object> register = new HashMap<>();

		if (getAllRegistersSubMenu.size() != 0) {
			register.put("name", "Registers");
			List<Map<String, Object>> registerCollapse = new ArrayList<>();

			getAllRegistersSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Registers"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				registerCollapse.add(common);

			});
			register.put("collapse", registerCollapse);
			pageRoutes.add(register);
		}

		// Masters
		List<CSubMenuItem> getAllHelpSubMenu = getAllModuleForms.stream()
				.filter(item -> item.getMenu_type().equals("Help"))
				.sorted(Comparator.comparing(
						CAmModulesFormsUserAccessViewModel::getSub_menu_display_sequence,
						Comparator.nullsLast(Comparator.naturalOrder())))
				.map(item -> new CSubMenuItem(item.getModules_sub_menu_name(), item.getSub_menu_icon_class()))
				.distinct()
				.collect(Collectors.toList());

		if (getAllHelpSubMenu.size() != 0) {
			Map<String, Object> masters = new HashMap<>();
			masters.put("name", "Help");

			List<Map<String, Object>> helpsCollapse = new ArrayList<>();

			getAllHelpSubMenu.forEach(item -> {
				Map<String, Object> common = new HashMap<>();
				common.put("name", item.getName());
				common.put("dropdown", true);
				common.put("icon", item.getIconClass());

				List<Map<String, Object>> commonCollapse = new ArrayList<>();

				List<CAmModulesFormsUserAccessViewModel> getFilteredData = getAllModuleForms.stream()
						.filter(moduleItem -> moduleItem.getModules_sub_menu_name().equals(item.getName()) && moduleItem.getMenu_type().equals("Help"))
						.sorted(Comparator.comparing(
								CAmModulesFormsUserAccessViewModel::getModule_form_display_sequence,
								Comparator.nullsLast(Comparator.naturalOrder())))
						.collect(Collectors.toList());

				getFilteredData.forEach(filteredItem -> {
					Map<String, Object> subCollapse = new HashMap<>();
					subCollapse.put("name", filteredItem.getDisplay_name());
					subCollapse.put("route", filteredItem.getListing_navigation_url());
					subCollapse.put("url_parameter", filteredItem.getUrl_parameter());
					commonCollapse.add(subCollapse);
				});

				common.put("collapse", commonCollapse);
				helpsCollapse.add(common);

			});
			masters.put("collapse", helpsCollapse);
			pageRoutes.add(masters);
		}
		return pageRoutes;

	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
		String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);
		System.out.println("refresh token: - " + refreshToken);

		if ((refreshToken != null) && (refreshToken.length() > 0)) {
			return refreshTokenService.findByToken(refreshToken)
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUser)
					.map(user -> {
						ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);
						System.out.println("Token is refreshed successfully!");
						return ResponseEntity.ok()
								.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
								.body(new MessageResponse("Token is refreshed successfully!"));
					})
					.orElseThrow(() -> new TokenRefreshException(refreshToken,
							"Refresh token is not in database!"));
		}
		System.out.println("Refresh Token is empty!");
		return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser(HttpServletRequest request) {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String refresh_token = jwtUtils.getJwtRefreshFromCookies(request);
		System.out.println("Logout refresh token: - " + refresh_token);
//		String refresh_token = jwtUtils.getCookieValueByName(request, "ditsJwtRefresh");

		if (principle.toString() != "anonymousUser") {
			String userCode = ((UserDetailsImpl) principle).getUser_code();
			refreshTokenService.deleteByUserId(userCode, refresh_token);
		}

		ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
		ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//		}

		// Create new user's account
//		User user = new User(signUpRequest.getUsername()
////				, signUpRequest.getEmail(),
//				,encoder.encode(signUpRequest.getPassword()));

//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//					case "admin":
//						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(adminRole);
//
//						break;
//					case "mod":
//						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(modRole);
//
//						break;
//					default:
//						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(userRole);
//				}
//			});
//		}

//		user.setRoles(roles);
//		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@PostMapping("/companyoptions")
	public List<Map<String, String>> companyoptionsController(){
		return userRepository.getcompanydeatils();

	}
}
