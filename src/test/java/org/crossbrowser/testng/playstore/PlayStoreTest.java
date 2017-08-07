package org.crossbrowser.testng.playstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**
 * Unit test for simple App.
 */
public class PlayStoreTest {
	private AppiumDriver<MobileElement> driver;
	private BasePage basePage;
	final static Logger logger = Logger.getLogger(PlayStoreTest.class);

	@AndroidFindBy(xpath = ".//android.widget.Button[@text='ACCEPT']")
	private MobileElement acceptButton;

//	@AfterSuite
	void bindUp() {
		logger.info("Closing the App and Appium driver.");
		AppiumService appiumService = new AppiumService();
		appiumService.stopServer();
		logger.info("Appium server stopped.");
		BasePage basePage = new BasePage(driver);
		basePage.closeDriver(driver);
	}

	@Test
	@Parameters({"deviceId", "port", "plateformV" })
	void playStoreAppLaunch(String deviceId, String port, String plateformV)
			throws InterruptedException, IOException {
System.out.println(port + " "+ plateformV + " " + deviceId );
		basePage = new BasePage(driver);
		driver = basePage.setup(deviceId, port, plateformV);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 3, TimeUnit.SECONDS), this);

		// wait until element presence

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(acceptButton));
		acceptButton.click();

	}

//	@DataProvider
//	public Object[][] getDevices() {
//		ArrayList<String> al;
//		String line = "";
//		int lines = 0;
//		Object[][] obj = null;
//		try {
//			al = AdbUitls.getConnetedDevices();
//			obj = new Object[al.size()][1];
//			int i = 0;
//			for (String str : al) {
//				obj[i][0] = str;
//				i++;
//			}
//
//		} catch (Exception e) {
//			System.err.println("Error");
//		}
//		return obj;
//	}
}
