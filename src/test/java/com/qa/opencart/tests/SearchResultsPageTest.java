package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class SearchResultsPageTest extends BaseTest{

	@BeforeClass
	public void searchPageSetUp() {
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
		searchPage = accPage.doSearch("mac book");
	}
	
	@Test
	public void searchPageHeadertest() {
		searchPage = accPage.doSearch("mac book");
		String actHeader = searchPage.searchKeyHeader();
		String expHeader = "Search - mac book";
		Assert.assertEquals(actHeader, expHeader);
	}
	
	@Test
	public void searchResultsTest() {
		searchPage = accPage.doSearch("mac book");
		int actCount = searchPage.searchResults().size();
		Assert.assertEquals(actCount, 3);
	}
	
	@Test
	public void selectProductTest() {
		searchPage = accPage.doSearch("mac book");
		String expHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(expHeader, "MacBook Pro");
	}
	
}
