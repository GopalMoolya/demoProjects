package org.crossbrowser.testng.playstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AdbUitls {

	public static ArrayList<String> getConnetedDevices() throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		int lines = 0;
		ProcessBuilder proc = new ProcessBuilder("adb", "devices");
		Process p = proc.start();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = br2.readLine()) != null) {
			if (lines > 0 && line.contains("device")) {
				al.add(line.substring(0, line.indexOf("\t")));
				System.out.println();
			}
			lines++;
		}
		return al;
	}

	public static String getDeviceName(String udid) throws IOException {
		ProcessBuilder proc = new ProcessBuilder("adb", "-s", udid, "shell", "getprop", "ro.product.model");
		Process p = proc.start();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = br2.readLine();
		return line;
	}

}