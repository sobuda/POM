package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import  static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eUtil;
	
	//1. Private and final By Locators
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private final By forgotPswLink = By.linkText("Forgotten Password");
	private final By RegisterLink = By.linkText("Register");
	
	private static Logger log = LogManager.getLogger(LoginPage.class);
	
	//2. Public  Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	
	//3. Public Page Methods/Actions
	@Step("Step1: Get LoginPage Title")
	public String getLoginPageTitle() {
		String pageTitle = eUtil.waitForTitle(DEFAULT_TIMEOUT, LOGIN_PAGE_TITLE);
		log.info("Page Title: "+pageTitle);
		return pageTitle;
	}
	@Step("Step 2: Get LoginPage Url")
	public String getLoginPageURL() {
		String pageURL = eUtil.waitForURLContains(DEFAULT_TIMEOUT, LOGIN_PAGE_FRACT_URL);
		log.info("Page Title: "+pageURL);
		return pageURL;
	}
	@Step("Step 3: Check for Forgot Password Link")
	public boolean isForgotPswLinkExist() {
		return eUtil.isElementDisplayed(forgotPswLink);
	}
	
	@Step("Step 4: Check Login with Valid credentials username: {0} password: {1}")
	public AccountsPage doLogin(String username, String password) {
		log.info("User credentials:"+username+": "+password);
		eUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		eUtil.doSendKeys(this.password, password);
		eUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	@Step("Step 5: Navigate to Registration Page")
	public RegistrationPage navigateToRegisterPage() {
		eUtil.clickWhenReady(RegisterLink, DEFAULT_TIMEOUT);
		return new RegistrationPage(driver);
	}
	
	
	
	
	
	
	
	
	//delete from usertable where email like'%testAutomation%'
}
