package com.bfbrmt.test5;

import java.util.Map;

import com.bfbrmt.test5.SinaWeibo.StateSwitchExecption;

public abstract class Platform {
	public static String thirdPartyLogin = "http://192.168.56.1:8888/api/thirdpartylogin";
	public static String thirdPartyLogout = "http://192.168.56.1:8888/api/thirdpartylogout";
	public static String thirdPartyRemoveAccount = "http://192.168.56.1:8888/api/thirdpartremoveaccount";
	
	public abstract void setCallback(PlatformCallback callback);
	
	public abstract void login() throws StateSwitchExecption;
	
	protected abstract void thirdPartyLogin();
	
	public abstract void logout() throws StateSwitchExecption;
	
	protected abstract void thirdPartyLogout();

	public abstract void revokeAuthorize() throws StateSwitchExecption;
	
	protected abstract void thirdPartyRemoveAccount();
	
	public interface PlatformCallback {
		public void onThirdPartyLoginComplete(Map<String, String> result);
		public void onThirdPartyLogoutComplete(Map<String, String> result);
		public void onThirdPartyRemoveAccountComplete(Map<String, String> result);
	}
}
