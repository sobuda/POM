package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACT_URL;
import static com.qa.opencart.constants.AppConstants.*;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: OpenCart AccountPage Design")
@Feature("Account Page Features")
@Story("US 101: Test AccountPage")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
	}
	
	@BeforeMethod
	public void beforeMethod() {
		  //log
		  ChainTestListener.log("Properties: "+prop);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.MINOR)
	@Description("Account Page title")
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle,  HOME_PAGE_TITLE);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Account Page URL")
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		Assert.assertTrue(actURL.contains(HOME_PAGE_FRACT_URL));
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Account Page Headers")
	@Test
	public void accPageHeadersTest() {
		List<String> actHeaders = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeaders, ACC_PAGE_HEADERS_LIST);
	}
	
	
	@AfterMethod
	public void logAfterInitBrowser() {
		ChainTestListener.log("Initialising driver...."+" Accounts Page");
	}
}
