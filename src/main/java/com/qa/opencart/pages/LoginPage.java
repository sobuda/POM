package com.qa.opencart.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import  static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eUtil;
	
	//1. Private and final By Locators
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private final By forgotPswLink = By.linkText("Forgotten Password");
	
	//2. Public  Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	
	//3. Public Page Methods/Actions
	
	public String getLoginPageTitle() {
		String pageTitle = eUtil.waitForTitle(DEFAULT_TIMEOUT, LOGIN_PAGE_TITLE);
		System.out.println("Page Title: "+pageTitle);
		return pageTitle;
	}
	
	public String getLoginPageURL() {
		String pageURL = eUtil.waitForURLContains(DEFAULT_TIMEOUT, LOGIN_PAGE_FRACT_URL);
		System.out.println("Page Title: "+pageURL);
		return pageURL;
	}
	
	public boolean isForgotPswLinkExist() {
		return eUtil.isElementDisplayed(forgotPswLink);
	}
	
	public AccountsPage doLogin(String username, String password) {
		System.out.println("User credentials:"+username+": "+password);
		eUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		eUtil.doSendKeys(this.password, password);
		eUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
}
