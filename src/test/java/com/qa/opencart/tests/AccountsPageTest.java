package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACT_URL;
import static com.qa.opencart.constants.AppConstants.*;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle,  HOME_PAGE_TITLE);
	}
	
	
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		Assert.assertTrue(actURL.contains(HOME_PAGE_FRACT_URL));
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actHeaders = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeaders, ACC_PAGE_HEADERS_LIST);
	}
}
