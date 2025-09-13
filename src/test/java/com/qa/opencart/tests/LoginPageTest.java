package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import  static com.qa.opencart.constants.AppConstants.*;

public class LoginPageTest extends BaseTest {
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,  LOGIN_PAGE_TITLE);
	}
	@Test
	public void loginPageURLTest() {
		String actURL= loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACT_URL));
	}
	
	@Test
	public void forgotPswLinkExist() {
		Assert.assertTrue(loginPage.isForgotPswLinkExist());
	}
	
	@Test(priority = Short.MAX_VALUE)
	public void dologinTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
		Assert.assertEquals(accPage.getAccountsPageTitle(), HOME_PAGE_TITLE);
		
	}
}
