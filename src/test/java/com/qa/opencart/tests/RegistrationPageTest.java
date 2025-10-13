package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 400: OpenCart Registration Page Design")
@Feature("Registration Page Features")
@Story("US 206:Test Registration Page")
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
	
	@DataProvider
	public Object[][] getUserDataExcel(){
		return ExcelUtil.getTestData(AppConstants.EXCEL_REGISTER_SHEET);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Registration with Valid Inputs: FirstName: {0}, LastName: {1}, Tele: {2}, password: {3}, subscribe: {4}")
	@Test(dataProvider = "getUserDataExcel")
	public void userResgistrationTest(String firstName, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastname, telephone, password, subscribe));
		
	}
}
