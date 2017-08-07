package org.crossbrowser.testng.playstore;
import java.io.IOException;

import org.testng.Reporter;

public class AppiumService {
	
	public void startServer(String port) {
		try{
			stopServer();
		}catch(Exception e){
			e.printStackTrace();
		}
		Runtime runtime = Runtime.getRuntime();
		try {
			Thread.sleep(200);
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p "+port+" --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			Reporter.log("Appium server start command is executing.");
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}