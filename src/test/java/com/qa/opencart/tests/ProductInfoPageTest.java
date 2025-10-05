package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.PRODUCT_PAGE_FRACT_URL;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 300: OpenCart Product Info Page Design")
@Feature("Product Info Page Features")
@Story("US 103:Test Product Info Page")
public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	@DataProvider
	public Object[][] productHeaderTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	@Owner("Sowmya")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Product Info Page: Product {1} Header")
	@Test(dataProvider ="productHeaderTestData")
	public void productHeaderTest(String searchKey,String productName) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@Owner("Sowmya")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Product Page URL")

	@Test
	public void productPageURLTest() {
		searchPage = accPage.doSearch("mac book");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		String actProductPageURL = productInfoPage.getProductPageURL();
		Assert.assertTrue(actProductPageURL.contains(PRODUCT_PAGE_FRACT_URL));

	}
	
	@DataProvider
	public Object[][] productImagesData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@DataProvider
	public Object[][] getProductDataExcel(){
		return ExcelUtil.getTestData(AppConstants.EXCEL_PRODUCT_SHEET);
	}

	@DataProvider
	public Object[][] getProductCSVData(){
		return CSVUtil.getCsvData(AppConstants.CSV_PRODUCT_SHEET);
	}
	@Test(dataProvider="getProductCSVData")
	public void productImageCountTest(String searchKey,String productName,String expImageCount) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getImageCount();
		Assert.assertEquals(String.valueOf(actImagesCount), expImageCount);
	}
	
	@DataProvider
	public Object[][] productDetailsData() {
		return new Object[][]  {
			{"macbook","MacBook Pro","Apple","Product 18","800","Out Of Stock","$2,000.00","$2,000.00"},
			{"macbook","MacBook Air","Apple","Product 17","700","Out Of Stock","$1,202.00","$1,000.00"},
			{"imac","iMac","Apple","Product 14",null,"Out Of Stock","$122.00","$100.00"},
			{"samsung","Samsung SyncMaster 941BW",null,"Product 6",null,"2-3 Days","$242.00","$200.00"},
			{"samsung","Samsung Galaxy Tab 10.1",null,"SAM1","1000","Pre-Order","$241.99","$199.99"}
		};
	}
	
	@Test(dataProvider="productDetailsData")
	public void productMetaDataTest(String searchKey, String productName, String brand,
			String productCode,String rewardPoints,String avalability,String productPrice,String exTaxPrice) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		Map<String,String> actProdMeta = productInfoPage.getProductMapDetails();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actProdMeta.get("Brand"), brand);
		softAssert.assertEquals(actProdMeta.get("Product Code"), productCode);
		softAssert.assertEquals(actProdMeta.get("Reward Points"), rewardPoints);
		softAssert.assertEquals(actProdMeta.get("Availability"), avalability);
		softAssert.assertEquals(actProdMeta.get("ProductPrice"), productPrice);
		softAssert.assertEquals(actProdMeta.get("ExTaxPrice"), exTaxPrice);
		
		softAssert.assertAll();
		
	}

	
	
}
