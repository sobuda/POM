package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co= new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
			System.out.println("==========Runing in HeadlessMode========");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
			System.out.println("==========Runing in inCognitoMode========");

		}
		
		return co;
	}
	
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo= new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless");
			System.out.println("==========Runing in HeadlessMode========");

		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--inprivate");
			System.out.println("==========Runing in inCognitoMode========");

		}
		
		return eo;
	}
	
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo= new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
			System.out.println("==========Runing in HeadlessMode========");

		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
			System.out.println("==========Runing in inCognitoMode========");

		}
		
		return fo;
	}
}
