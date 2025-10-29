package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private static Logger log = LogManager.getLogger(RegistrationPage.class);


	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		
		eUtil = new ElementUtil(driver);
	}

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscrYes = By.xpath("(//label[@class='radio-inline']/input)[1]");
	private By subscrNo = By.xpath("(//label[@class='radio-inline']/input)[2]");
	private By agree = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit']");

	// Your Account Has Been Created!
	private By successMsg = By.cssSelector("#content h1");
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public boolean userRegistration(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		log.info("Registration info: ",firstName, lastName, telephone, password, subscribe);
		eUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
		eUtil.doSendKeys(this.lastName, lastName);
		eUtil.doSendKeys(this.email, StringUtils.getRandomEmail());
		eUtil.doSendKeys(this.telephone, telephone);
		eUtil.doSendKeys(this.password, password);
		eUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eUtil.doClick(subscrYes);
		}
		else eUtil.doClick(subscrNo);
			
		eUtil.doClick(agree);
		eUtil.doClick(continueBtn);
		
		if(eUtil.waitForURLContains(10, REGISTRATION_SUCCESS_FRACT_URL).contains(REGISTRATION_SUCCESS_FRACT_URL)) {
			
		
		//eUtil.waitForElementsVisible(successMsg, MEDIUM_DEFAULT_TIMEOUT);
		
		if(eUtil.doElementGetText(successMsg).contains(REGISTER_SUCCESS_MESSAGE)) {
			
			eUtil.clickWhenReady(logout, MEDIUM_DEFAULT_TIMEOUT);
			eUtil.clickWhenReady(registerLink, MEDIUM_DEFAULT_TIMEOUT);
			return true;
		}
		else return false;
		
	}
		else return false;
	
	}

}
