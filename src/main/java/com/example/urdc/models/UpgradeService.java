//package com.example.urdc.models;
//
///**
//*  __        __    ____                     _
//*  \ \      / /__ / ___|_   _  __ _ _ __ __| |
//*   \ \ /\ / / _ \ |  _| | | |/ _` | '__/ _` |
//*    \ V  V /  __/ |_| | |_| | (_| | | | (_| |
//*     \_/\_/ \___|\____|\__,_|\__,_|_|  \__,_|
//*
//*   =================================================================================
//*   This file is a part of "WeGuard", Copyright (C) 2022, Wenable Inc. - All Rights Reserved.
//*   Wenable Inc. licenses this file to you under the WEGUARD ("WeGuard") LICENSE, Version 0.7 (the "License")
//*   =================================================================================
//*   You may not use this file except in compliance with the "License".
//*   A copy of the "License" (license.txt) is shipped along with this "WeGuard" source code bundle.
//*   In case you are unable to locate the "License",  it is crucial that you obtain a copy by contacting Wenable Inc.
//*
//*   CR/PR				Reason								Responsible		URL
//* ---------------------------------------------------------------------------------------------------------------------
//*	WGSUPPRTL-369		Added License Info					Subhayan		https://wenable.atlassian.net/browse/WGSUPPRTL-369
//* ---------------------------------------------------------------------------------------------------------------------
//*/
//
//
//package com.weguard.support.server.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.weguard.enterprise.models.AppInfoModel;
//import com.weguard.enterprise.models.CustomerLicenseModel;
//import com.weguard.enterprise.models.DefaultConfigModel;
//import com.weguard.enterprise.models.License;
//import com.weguard.enterprise.models.User;
//import com.weguard.support.server.dao.PolicyDao;
//import com.weguard.support.server.dao.UserDao;
//import com.weguard.support.server.exceptions.GenericException;
//import com.weguard.support.server.models.SupportNotes;
//import com.weguard.support.server.request.dtos.AppUpgradeDto;
//import com.weguard.support.server.request.dtos.BannerNotificationDto;
//import com.weguard.support.server.request.dtos.CustomerBulkLicenseRequest;
//import com.weguard.support.server.request.dtos.LicenceUpgradeDto;
//import com.weguard.support.server.response.dtos.UpgradeResponse;
//import com.weguard.support.server.utils.AppConstants;
//import com.weguard.support.server.utils.AppProperties;
//import com.weguard.support.server.utils.AppUtils;
//import com.weguard.support.server.utils.TokenUtils;
//
//@Service
//public class UpgradeService {
//
//	@Autowired
//	TokenUtils utils;
//
//	@Autowired
//	AppUtils appUtils;
//
//	@Autowired
//	RestTemplate rest;
//
//	@Autowired
//	AppProperties props;
//
//	@Autowired
//	SupportNotesService notesService;
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	PolicyDao policyDao;
//
//	@Autowired
//	PolicyService policyService;
//
//	@Autowired
//	UserDao userDao;
//
//	private final ObjectMapper objectMapper = new ObjectMapper();
//	private static Logger logger = LoggerFactory.getLogger(UpgradeService.class);
//
//	/**
//	 * 
//	 * This method is used to update the android apps_webox_wesupport_wetalk_weguard
//	 * for selected userIds
//	 */
//	public void upgradeLicense(CustomerLicenseModel model) {
//		logger.info("Updating started for the usersIds:" + model.getUserIds());
//		if (model == null || model.getUserIds().isEmpty()) {
//			throw new GenericException("Required fields are missing");
//		}
//		List<String> userIds = model.getUserIds();
//		SupportNotes notes = null;
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(model.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		try {
//			ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.UPDATE_PRO_APPS_PATH,
//					HttpMethod.POST, request, String.class);
//			if (respEnt.getBody() != null) {
//				if (respEnt.getStatusCode().is2xxSuccessful()) {
//					for (String userId : userIds) {
//						User user = userDao.getByUsername(userId);
//						notes = new SupportNotes(userId, AppConstants.PROAPPS_UPGRADE_MSG, null, null,
//								user.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//					}
//				}
//				logger.info("Done with updating proapps for the usersIds:" + model.getUserIds());
//			}
//		} catch (Exception e) {
//			for (String userId : userIds) {
//				User user = userDao.getByUsername(userId);
//				notes = new SupportNotes(userId, AppConstants.PROAPPS_UPGRADE_FAILED, null, null, user.getAccountId());
//				notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//			}
//			logger.info("Updating android proapps failed:" + e);
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 * this method is used to generate the support authorization token
//	 */
//	public HttpHeaders getHttpHeaders() {
//		// Generating JWT token which will access to enterprise server
//		String jwtToken = utils.generateToken(utils.getUserName(), utils.getRole(), appUtils.getRemoteIp());
//		// setting headers authorization,contenttype
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(AppConstants.SUPPORT_SADMIN_AUTHORIZATION, "Bearer " + jwtToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		return headers;
//	}
//
//	/**
//	 * 
//	 * This method is used to save the banner notification/apply banner notification
//	 * for selected userIds
//	 */
//	public JsonNode saveBannerNotification(BannerNotificationDto bean) {
//		logger.info("Updating Bannernotification for the admins:" + bean.getAdmins());
//		if (bean == null || bean.getAdmins().isEmpty()) {
//			throw new GenericException("Required fields are missing");
//		}
//		List<String> admins = new ArrayList<String>();
//		if (bean.getAdmins().contains("All")) {
//			List<User> user = userDao.getAllUsers();
//			for (User users : user) {
//				admins.add(users.getUserName());
//			}
//			bean.setAdmins(admins);
//		}
//		List<String> userIds = bean.getAdmins();
//		SupportNotes notes = null;
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(bean.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.BANNER_NOTIFICATION_PATH,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//				if (respEnt.getStatusCode().is2xxSuccessful()) {
//					for (String userId : userIds) {
//						User user = userDao.getByUsername(userId);
//						notes = new SupportNotes(userId, AppConstants.BANNER_NOTIFICATION_MSG, null, null,
//								user.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.BANNER_NOTIFICATION);
//					}
//				}
//				logger.info("Done with updating bannernotifications for the admins:" + bean.getAdmins());
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//			for (String userId : userIds) {
//				User user = userDao.getByUsername(userId);
//				notes = new SupportNotes(userId, AppConstants.BANNER_NOTIFICATION_FAILED, null, null,
//						user.getAccountId());
//				notesService.saveUpgradeNotes(notes, AppConstants.BANNER_NOTIFICATION);
//			}
//			logger.info("Updating bannernotification  failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * This method is used to bulk license update for selected accountIds and
//	 * selected policyTypes
//	 */
//	public void bulkUpdate(CustomerBulkLicenseRequest dto) {
//		logger.info("Started bulk update for the accountIds:" + dto.getAccountIdList());
//		if (dto == null || dto.getAccountIdList().isEmpty()) {
//			throw new GenericException("Required fields are missing");
//		}
//		List<User> users = null;
//		SupportNotes notes = null;
//		users = userService.getUserIds(dto.getAccountIdList());
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(dto.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		try {
//			ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.BULK_UPDATE_PATH,
//					HttpMethod.POST, request, String.class);
//			if (respEnt.getBody() != null) {
//				if (respEnt.getStatusCode().is2xxSuccessful()) {
//					for (User user : users) {
//						notes = new SupportNotes(user.getUserName(), AppConstants.POLICY_LEVEL_UPDATE, null, null,
//								user.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//					}
//				}
//				logger.info("Done with bulk update for the  accountsIds:" + dto.getAccountIdList());
//			}
//		} catch (Exception e) {
//			for (User user : users) {
//				notes = new SupportNotes(user.getUserName(), AppConstants.POLICY_LEVEL_UPDATE_FAILED, null, null,
//						user.getAccountId());
//				notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//			}
//			logger.info("Bulk update  failed:" + e);
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 * This method is used to save company config for particular company
//	 */
//	public JsonNode saveConfig(String requestString) {
//		logger.info("started saving company config" + requestString);
//		if (requestString == null) {
//			throw new GenericException("Required fields are missing");
//		}
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(requestString.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.COMPANY_CONFIG,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//				logger.info("Done with saving comapany config");
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//			logger.info("Saving company  config failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public JsonNode saveEmmServiceAccount(String requestString) {
//		logger.info("Saving EMM serviceAccount :" + requestString);
//		if (requestString == null) {
//			throw new GenericException("Required fields are missing");
//		}
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(requestString.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.EMM_SERVICE_PATH,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//				logger.info("EMM serviceAccount saved successfully:" + root);
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//			logger.info("EMM serviceAccount saving failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * This method is used to save the default config
//	 */
//	public JsonNode saveDefaultConfig(DefaultConfigModel bean) {
//		logger.info("Saving default config" + bean);
//		if (bean == null || bean.getResellerIdList().isEmpty()) {
//			throw new GenericException("Required fields are missing");
//		}
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(bean.toString(), headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.ADD_DEFAULT_CONFIG_PATH,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//
//				logger.info("Done with adding default configuration for the ids:" + bean.getResellerIdList());
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//
//			logger.info("Adding default configurations failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//
//	/**
//	 * 
//	 * This method is used to update the windows msi apps weguard_wesupport for
//	 * selected accounts or all accounts
//	 */
//	public JsonNode upgradeWindowsProApps(AppUpgradeDto dto) {
//		logger.info("Started updating windows pro app:" + dto.getAppName() + "for the accounts"
//				+ dto.getUpgradeAccountIdList());
//		List<User> users = null;
//		SupportNotes notes = null;
//		if (dto.getIgnoreAccountIdList() == null && dto.getUpgradeAccountIdList() == null) {
//			users = userService.getUserIds(null);
//		} else if (dto.getUpgradeAccountIdList() != null) {
//			users = userService.getUserIds(dto.getUpgradeAccountIdList());
//		}
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(dto.toString(), headers);
//		// request sending for the windows server
//		logger.info("Making an api call to windows server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getWindowsUrl() + AppConstants.WINDOWS_PRO_APP_PATH,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//				if (respEnt.getStatusCode().is2xxSuccessful()) {
//					for (User user : users) {
//						notes = new SupportNotes(user.getUserName(), AppConstants.WINDOWS_PRO_APP_MSG, null, null,
//								user.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//					}
//				}
//				logger.info("Done with updating pro app:" + dto.getAppName() + "accountIds:"
//						+ dto.getUpgradeAccountIdList());
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//			for (User user : users) {
//				notes = new SupportNotes(user.getUserName(), AppConstants.WINDOWS_PRO_APP_MSG_FAILED, null, null,
//						user.getAccountId());
//				notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//			}
//			logger.info("Windows pro app update failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * This method is used reload the apis
//	 */
//	public JsonNode initRestApis() {
//		logger.info("Starting Reload apis");
//		HttpHeaders headers = getHttpHeaders();
//		// setting headers and request body to httpentity
//		HttpEntity<String> request = new HttpEntity<String>(headers);
//		// request sending for the enterprise server
//		logger.info("Making an api call to enterprise server");
//		ResponseEntity<String> respEnt = rest.exchange(props.getEnterpriseUrl() + AppConstants.RELOAD_APIS_PATH,
//				HttpMethod.POST, request, String.class);
//		try {
//			JsonNode root = null;
//			if (respEnt.getBody() != null) {
//				root = objectMapper.readTree(respEnt.getBody());
//				logger.info("Done with reload apis");
//				return root;
//			}
//		} catch (JsonProcessingException e) {
//
//			logger.info("Reload apis failed:" + e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public List<UpgradeResponse> updateLicense(LicenceUpgradeDto dto) {
//		logger.info(" Updating licenses :{} ", dto);
//		if (dto == null || dto.getPolicyIds() == null || dto.getPolicyIds().isEmpty()) {
//			throw new GenericException("Required fields are missing");
//		}
//		List<UpgradeResponse> res = new ArrayList<UpgradeResponse>();
//		for (String policyId : dto.getPolicyIds()) {
//			SupportNotes notes = null;
//			UpgradeResponse response = null;
//			String appName = null;
//			License license = null;
//			// Getting the license from the db and updating only the persona app list in
//			// the
//			// license without affecting the other fields in the db by using update set
//			// method
//			license = policyDao.getByIdAndDeleted(policyId);
//			List<AppInfoModel> apps = license.getProductInfo().getPersonaAppList();
//			List<AppInfoModel> resp = new ArrayList<AppInfoModel>();
//			AppInfoModel app = null;
//			for (AppInfoModel appInfoModel : apps) {
//				if (dto.getWeBox() != null && appInfoModel.getAppID() != null
//						&& appInfoModel.getAppID().equalsIgnoreCase(dto.getWeBox().getAppID())) {
//					appName = "webox";
//					if (appInfoModel.getVersionCode() < dto.getWeBox().getVersionCode()) {
//						logger.info(" Updating the webox :{} ", dto.getWeBox());
//						app = new AppInfoModel();
//						app = setAppDetails(dto.getWeBox());
//						resp.add(app);
//						response = getResponse(policyId, false, license.getName());
//						res.add(response);
//						sendLogs(appName, dto.getUserName(), license.getName(), license.getAccountId());
//					} else {
//						notes = new SupportNotes(dto.getUserName(),
//								appName + AppConstants.LICENSE_UPGRADE_FAILED + license.getName(), null, null,
//								license.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//						response = getResponse(policyId, true, license.getName());
//						resp.add(appInfoModel);
//						res.add(response);
//					}
//				} else if (dto.getWeSupport() != null && appInfoModel.getAppID() != null
//						&& appInfoModel.getAppID().equalsIgnoreCase(dto.getWeSupport().getAppID())) {
//					appName = "wesupport";
//					if (appInfoModel.getVersionCode() < dto.getWeSupport().getVersionCode()) {
//						logger.info(" Updating the wesupport :{} ", dto.getWeSupport());
//						app = new AppInfoModel();
//						app = setAppDetails(dto.getWeSupport());
//						resp.add(app);
//						response = getResponse(policyId, false, license.getName());
//						sendLogs(appName, dto.getUserName(), license.getName(), license.getAccountId());
//						res.add(response);
//					} else {
//						notes = new SupportNotes(dto.getUserName(),
//								appName + AppConstants.LICENSE_UPGRADE_FAILED + license.getName(), null, null,
//								license.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//						response = getResponse(policyId, true, license.getName());
//						resp.add(appInfoModel);
//						res.add(response);
//
//					}
//				} else if (dto.getWeTalk() != null && appInfoModel.getAppID() != null
//						&& appInfoModel.getAppID().equalsIgnoreCase(dto.getWeTalk().getAppID())) {
//					appName = "wetalk";
//					if (appInfoModel.getVersionCode() < dto.getWeTalk().getVersionCode()) {
//						logger.info(" Updating the wetalk :{} ", dto.getWeTalk());
//						app = new AppInfoModel();
//						app = setAppDetails(dto.getWeTalk());
//						resp.add(app);
//						response = getResponse(policyId, false, license.getName());
//						res.add(response);
//						sendLogs(appName, dto.getUserName(), license.getName(), license.getAccountId());
//					} else {
//						notes = new SupportNotes(dto.getUserName(),
//								appName + AppConstants.LICENSE_UPGRADE_FAILED + license.getName(), null, null,
//								license.getAccountId());
//						notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//						response = getResponse(policyId, true, license.getName());
//						resp.add(appInfoModel);
//						res.add(response);
//
//					}
//				} else {
//					resp.add(appInfoModel);
//				}
//			}
//			if (dto.getWeGuard() != null) {
//				appName = "weguard";
//				if (license.getProductInfo().wegVersionCode < dto.getWeGuard().getVersionCode()) {
//					// updating
//					logger.info(" Updating the weguard :{} ", dto.getWeGuard());
//					policyService.updateAppInfoModel(policyId, resp, dto.getWeGuard());
//					sendLogs(appName, dto.getUserName(), license.getName(), license.getAccountId());
//					response = getResponse(policyId, false, license.getName());
//					res.add(response);
//				} else {
//					notes = new SupportNotes(dto.getUserName(),
//							appName + AppConstants.LICENSE_UPGRADE_FAILED + license.getName(), null, null,
//							license.getAccountId());
//					notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//					response = getResponse(policyId, true, license.getName());
//					res.add(response);
//				}
//			}
//			logger.info(" Done with updating licenses :{} ", dto);
//		}
//		// updating
//					policyService.updateAppInfoModel(policyId, resp, null);
//		return res;
//		/**
//		 * If the apps are not present then the apps are added
//		 */
//		boolean weSupport = true;
//		boolean weTalk = true;
//		boolean weBox = true;
//
//		for (int i = 0; i < resp.size(); i++) {
//			if (resp.get(i).getAppID().equalsIgnoreCase(AppConstants.WESUPPORT)) {
//				weSupport = false;
//			} else if (resp.get(i).getAppID().equalsIgnoreCase(AppConstants.WEBOX)) {
//				weBox = false;
//			} else if (resp.get(i).getAppID().equalsIgnoreCase(AppConstants.WETALK)) {
//				weTalk = false;
//			}
//		}
//		if (weBox && dto.getWeBox() != null) {
//			logger.info(" WeBox app is added for the policy weBox :{}", dto.getWeBox());
//			app = new AppInfoModel();
//			app = setAppDetails(dto.getWeBox());
//			response = getResponse(policyId, false, license.getName());
//			res.add(response);
//			resp.add(app);
//		}
//		if (weSupport && dto.getWeSupport() != null) {
//			logger.info(" WeSupport app is added for the policy weSupport:{}", dto.getWeSupport());
//			app = new AppInfoModel();
//			app = setAppDetails(dto.getWeSupport());
//			response = getResponse(policyId, false, license.getName());
//			res.add(response);
//			resp.add(app);
//		}
//		if (weTalk && dto.getWeTalk() != null) {
//			logger.info(" WeTalk app is added for the policy weTalk:{}", dto.getWeTalk());
//			app = new AppInfoModel();
//			app = setAppDetails(dto.getWeTalk());
//			response = getResponse(policyId, false, license.getName());
//			res.add(response);
//			resp.add(app);
//		}
//
//	}
//
//	// success logs
//	private void sendLogs(String appName, String username, String policyName, String accountId) {
//		logger.info(" Sending the logs by appName:{}, username:{}", appName, username);
//		SupportNotes notes = null;
//		notes = new SupportNotes(username, appName + AppConstants.LICENSE_UPGRADE_MSG + policyName, null, null,
//				accountId);
//		logger.info(" Successfully updated license");
//		notesService.saveUpgradeNotes(notes, AppConstants.UPGRADE);
//		logger.info(" Done with saving the upgrade apps notes:{} ", notes);
//	}
//
//	private AppInfoModel setAppDetails(AppInfoModel model) {
//		logger.info(" Setting the app details :{}", model);
//		AppInfoModel app = new AppInfoModel();
//		app.setApkSize(model.getApkSize());
//		app.setAppID(model.getAppID());
//		app.setAppName(model.getAppName());
//		app.setAppVersion(model.getAppVersion());
//		app.setType(model.getType());
//		app.setPlaystoreApp(model.isPlaystoreApp());
//		app.setChecksum(model.getChecksum());
//		app.setHash(model.getHash());
//		app.setLauncherIntentPkg(model.getLauncherIntentPkg());
//		app.setDisable(model.isDisable());
//		app.setLauncherIntentActivity(model.getLauncherIntentActivity());
//		app.setShowonPortal(model.isShowonPortal());
//		app.setShowonDevice(model.isShowonDevice());
//		app.setUrlToDownload(model.getUrlToDownload());
//		app.setVersionCode(model.getVersionCode());
//		return app;
//
//	}
//
//	public UpgradeResponse getResponse(String policyId, boolean error, String policyName) {
//		UpgradeResponse resp = new UpgradeResponse(policyId, error, policyName);
//		return resp;
//	}
//}
