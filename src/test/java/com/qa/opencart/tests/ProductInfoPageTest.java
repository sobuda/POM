package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void productHeaderTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct();
		String actProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}

	@Test
	public void productPageURLTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct();
		String actProductPageURL = productInfoPage.getProductPageURL();
		Assert.assertTrue(actProductPageURL.contains(PRODUCT_PAGE_FRACT_URL));

	}

	@Test
	public void productImageCountTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct();
		int actImagesCount = productInfoPage.getImageCount();
		Assert.assertEquals(actImagesCount, 4);
	}
}
