package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationPageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	@DataProvider
	public Object[][] userInfoData(){
		return new Object[][] {
			{"Vishal","Mehta","9988776655","Vishal@M123","yes"},
			{"Gayathri","Doddar","9988776655","Gayatri@D123","no"},
			{"Divya","GS","9988776655","Divya@M123","yes"}
		};
	}
	
	@Test(dataProvider = "userInfoData")
	public void userResgistrationTest(String firstName, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastname, telephone, password, subscribe));
		
	}
}
