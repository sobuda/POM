package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.PRODUCT_PAGE_FRACT_URL;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

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
	
	@Test
	public void productMetaDataTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct();
		Map<String,String> actProdMeta = productInfoPage.getProductMapDetails();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actProdMeta.get("Brand"), "Apple");
		softAssert.assertEquals(actProdMeta.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdMeta.get("Reward Points"), "800");
		softAssert.assertEquals(actProdMeta.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProdMeta.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(actProdMeta.get("ExTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}

	
	
}
