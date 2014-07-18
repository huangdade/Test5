package com.bfbrmt.test5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpVisiter {
	public static String get(String urlstr, String gets) {
		String result = "";
		String fullurlstr = urlstr;
		if (gets != null) {
			fullurlstr += "?" + gets;
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(fullurlstr).openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String inputLine = null;
			while ((inputLine = buffer.readLine()) != null) {
				result += inputLine + "\n";
			}
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String post(String urlstr, String posts) {
		String result = "";
		try {
			HttpURLConnection conn = (HttpURLConnection)(new URL(urlstr).openConnection());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestProperty("Content-Length", String.valueOf(posts.length()));
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(posts);
			out.flush();
			out.close();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String inputLine = null;
			while ((inputLine = buffer.readLine()) != null) {
				result += inputLine + "\n";
			}
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
