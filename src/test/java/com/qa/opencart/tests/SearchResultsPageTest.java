package com.qa.opencart.tests;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: OpenCart Search Results Page Design")
@Feature("Search Results Page Features")
@Story("US 101: Test Search Results")
public class SearchResultsPageTest extends BaseTest{

	@BeforeClass
	public void searchPageSetUp() {
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Search Results Page Header")
	@Test
	public void searchPageHeadertest() {
		searchPage = accPage.doSearch("mac book");
		String actHeader = searchPage.searchKeyHeader();
		String expHeader = "Search - mac book";
		Assert.assertEquals(actHeader, expHeader);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Search Results Page Search Function")
	@Test
	public void searchResultsTest() {
		searchPage = accPage.doSearch("mac book");
		int actCount = searchPage.searchResults().size();
		Assert.assertEquals(actCount, 3);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Search Results: Select Product Feature")
	@Test
	public void selectProductTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		String expHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(expHeader, "MacBook Pro");
	}
	
}
