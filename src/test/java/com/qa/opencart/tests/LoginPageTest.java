package com.qa.opencart.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import  static com.qa.opencart.constants.AppConstants.*;

@Epic("Epic 100: OpenCart WebPages Design")
@Feature("Login Page Features")
@Story("US 101: Test LoginPage with Valid Credentials")
public class LoginPageTest extends BaseTest {
	
	private static Logger log = LogManager.getLogger(LoginPageTest.class);
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.MINOR)
	@Description("Test LoginPage title")
	@Test(description="Login Page title Check")
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking LoginPage Title: "+actTitle);
		Assert.assertEquals(actTitle,  LOGIN_PAGE_TITLE);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test LoginPage URL")
	@Test(description="Login Page URL CHeck")
	public void loginPageURLTest() {
		String actURL= loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACT_URL));
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test LoginPage title")
	@Issue("BugId: 1024")
	@Test(description="Forgot Passwork Link Check")
	public void forgotPswLinkExist() {
		Assert.assertTrue(loginPage.isForgotPswLinkExist());
	}
	
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Check if user can login with valid credentials")
	@Test(priority = Short.MAX_VALUE,description="Login Check")
	public void dologinTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
		Assert.assertEquals(accPage.getAccountsPageTitle(), HOME_PAGE_TITLE);
		
	}
	
	@Test(enabled=false, description="Work In Progress WIP")
	public void forgotpsw() {
		log.warn("In Progress");
	}
}
