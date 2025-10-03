package com.qa.opencart.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class AppConstants {
	
	public static final int DEFAULT_TIMEOUT = 5;
	
	public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
	
	public static final int LONG_DEFAULT_TIMEOUT = 15;

	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	
	public static final String LOGIN_PAGE_FRACT_URL = "account/login";

	public static final String HOME_PAGE_FRACT_URL = "account/account";

	public static final String HOME_PAGE_TITLE = "My Account";
	
	public static final String PRODUCT_PAGE_FRACT_URL = "route=product/product&product_id";
	
	
	
	public static final String[] list = {"My Account","My Orders","My Affiliate Account","Newsletter"};
	
	public static final ArrayList<String> ACC_PAGE_HEADERS_LIST = new ArrayList<String>(Arrays.asList(list));
			//List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	/*=================Sheet Name============================*/
	
	public static final String EXCEL_REGISTER_SHEET = "Register";
	public static final String EXCEL_PRODUCT_SHEET = "ProductDetails";
	
	
	public static final String CSV_PRODUCT_SHEET = "TestData";


}
