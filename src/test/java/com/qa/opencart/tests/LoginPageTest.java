package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import  static com.qa.opencart.constants.AppConstants.*;

public class LoginPageTest extends BaseTest {
	@Test(description="Login Page title Check")
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,  LOGIN_PAGE_TITLE);
	}
	@Test(description="Login Page URL CHeck")
	public void loginPageURLTest() {
		String actURL= loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACT_URL));
	}
	
	@Test(description="Forgot Passwork Link Check")
	public void forgotPswLinkExist() {
		Assert.assertTrue(loginPage.isForgotPswLinkExist());
	}
	
	@Test(priority = Short.MAX_VALUE,description="Login Check")
	public void dologinTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
		Assert.assertEquals(accPage.getAccountsPageTitle(), HOME_PAGE_TITLE);
		
	}
	
	@Test(enabled=true, description="Work In Progress WIP")
	public void forgotpsw() {
		System.out.println();
	}
}
