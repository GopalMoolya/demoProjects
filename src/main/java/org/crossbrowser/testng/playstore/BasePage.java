package org.crossbrowser.testng.playstore;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.crossbrowser.testng.playstore.AppiumService;

public class BasePage {
	public AppiumDriver<MobileElement> driver;
	final static Logger logger = Logger.getLogger(BasePage.class);
	// public void startAppiumServer(String server, String port) throws
	// IOException {
	//
	// // prop = loadPropertyFile();
	//
	// CommandLine command = new CommandLine("D:\\Program
	// File\\nodejs\\node.exe");
	// command.addArgument("C:/Users/Mahadev/AppData/Roaming/npm/node_modules/appium/build/lib/appium.js",
	// false);
	//// String appiumServer = "0.0.0.0";
	// command.addArgument("--address", true);
	// command.addArgument(server);
	// command.addArgument("--port", true);
	// command.addArgument(port);
	// command.addArgument("--session-override", true);
	// command.addArgument("--no-reset");
	// command.addArgument("--log-level", false);
	// command.addArgument("error");
	// DefaultExecuteResultHandler resultHandler = new
	// DefaultExecuteResultHandler();
	// ExecuteWatchdog watchdog = new
	// ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
	// DefaultExecutor executor = new DefaultExecutor();
	// executor.setExitValue(1);
	// executor.setWatchdog(watchdog);
	// String url = null;
	// try {
	// System.out.println("Command to be executed : " + command.toString());
	// executor.execute(command, resultHandler);
	// AppiumServiceBuilder builder = new AppiumServiceBuilder();
	// builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
	// url = server + ":" + port;
	// Thread.sleep(5000);
	// System.out.println("Appium Server Started on URL : " + url);
	// } catch (IOException e) {
	// System.out.println("Unable to start appium on " + url);
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// System.out.println("Unable to start appium on " + url);
	// e.printStackTrace();
	// }
	// }

	public AppiumDriver<MobileElement> setup(String deviceUUID, String port, String plateformV) throws InterruptedException, ExecuteException, IOException {
		AppiumService appiumServer = new AppiumService();
		appiumServer.startServer(port);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("udid", deviceUUID);// 4cce901d
		// capabilities.setCapability("udid", "32048b1852e8a165");//Samsung Note
		// 3
		// capabilities.setCapability("udid","ZX1B34CH5K");
		logger.info("Setting capabilities....");
		capabilities.setCapability("deviceName", "ham");
		capabilities.setCapability("platformVersion", plateformV);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.vending");
		capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
		for (int i = 0; i < 10; i++) {
			try {
				driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+ port+"/wd/hub"), capabilities);
				i = 10;
				Thread.sleep(2000);
				// time to launch app
				return driver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void closeDriver(AppiumDriver<MobileElement> driver) {
		driver.close();
	}

	public BasePage(AppiumDriver<MobileElement> driver) {

		this.driver = driver;
	}

	public void scrollToLeft() throws InterruptedException {
		Dimension size;

		size = driver.manage().window().getSize();
		System.out.println(size);
		int starty = (int) (size.height / 2);
		int startx = (int) (size.width * 0.20);
		int endx = (int) (size.width * 0.80);
		Thread.sleep(4000);
		driver.swipe(startx, starty, endx, starty, 1000);
		Thread.sleep(1000);
	}

	public void scrollToRight() throws InterruptedException {
		Dimension size;

		size = driver.manage().window().getSize();
		System.out.println(size);
		int starty = (int) (size.height / 2);
		int endx = (int) (size.width * 0.20);
		int startx = (int) (size.width * 0.80);
		Thread.sleep(4000);
		driver.swipe(startx, starty, endx, starty, 1000);
		Thread.sleep(1000);
	}

	public void scrollToDown() throws InterruptedException {
		Dimension size;

		size = driver.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.height / 2);
		int endy = (int) (size.width * 0.20);
		int starty = (int) (size.width * 0.80);
		Thread.sleep(4000);
		driver.swipe(startx, starty, startx, endy, 1000);
		Thread.sleep(1000);
	}

	public void scrollToUp() throws InterruptedException {
		Dimension size;

		size = driver.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.height / 2);
		int endy = (int) (size.width * 0.80);
		int starty = (int) (size.width * 0.20);
		Thread.sleep(4000);
		driver.swipe(startx, starty, startx, endy, 1000);
		Thread.sleep(1000);
	}

	public void takeScreenShot(AppiumDriver<MobileElement> driver) {
		// Set folder name to store screenshots.
		
		String destDir = "screenshots";
		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to
		// destDir.
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void waitUntilVisibilityOf(AppiumDriver<MobileElement> driver, int time, WebElement s){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(s));
	}
	
	public void waitUntilVisibilityOf(AppiumDriver<MobileElement> driver, int time, MobileElement s){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(s));
	}
}