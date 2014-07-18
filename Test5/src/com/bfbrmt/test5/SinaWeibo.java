package com.bfbrmt.test5;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.util.Log;

public class SinaWeibo extends Platform implements SinaWeiboAPI.APIRequestCallback {
	private String access_token;
	private String uid;
	
	public static final int UNKNOWN = 1; // no access token
	public static final int AUTHORIZED = 2; // get access token and user has logged in
	
	private static SinaWeibo instance = null;
	int state;
	private SinaWeiboAPI api;
	private PlatformCallback callback;
	
	private SinaWeibo() {
		api = SinaWeiboAPI.getInstance();
		api.setAPIRequestCallback(this);
		state = UNKNOWN;
	}
	
	public static void init(Context context) {
		SinaWeiboAPI.init(context);
	}

	public static SinaWeibo getInstance() {
		if (instance == null) {
			instance = new SinaWeibo();
		}
		return instance;
	}

	public void setCallback(PlatformCallback callback) {
		this.callback = callback;
	}
	
	public void login() throws StateSwitchExecption {
		if (state == UNKNOWN) {
			api.authorize(null);
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public void revokeAuthorize() throws StateSwitchExecption {
		if (state == AUTHORIZED) {
			api.revokeauthorize(null);
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public void logout() throws StateSwitchExecption {
		if (state == AUTHORIZED) {
			access_token = null;
			state = UNKNOWN;
			thirdPartyLogout();
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public class StateSwitchExecption extends Exception {
		private static final long serialVersionUID = 1L;
		public StateSwitchExecption() {
			super();
		}
		public StateSwitchExecption(String msg) {
			super(msg);
		}
	}

	@Override
	protected void thirdPartyLogin() {
		// TODO Auto-generated method stub
		String poststr = "access_token=" + access_token + "&platform=" + "sinaweibo";
		String resultstr = HttpVisiter.post(thirdPartyLogin, poststr);
		Map<String, String> result = Utils.String2Map(resultstr);
		Log.v("mark", "s1");
		callback.onThirdPartyLoginComplete(result);
		Log.v("mark", "s2");
	}

	@Override
	protected void thirdPartyLogout() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String poststr = "username=*" + "sinawebo" + uid;
				String resultstr = HttpVisiter.post(thirdPartyLogout, poststr);
				Map<String, String> result = Utils.String2Map(resultstr);
				callback.onThirdPartyLoginComplete(result);
			}
		}).start();
	}

	@Override
	protected void thirdPartyRemoveAccount() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String poststr = "access_token=" + access_token + "&platform=" + "sinaweibo";
				String resultstr = HttpVisiter.post(thirdPartyRemoveAccount, poststr);
				Map<String, String> result = Utils.String2Map(resultstr);
				callback.onThirdPartyLoginComplete(result);
				access_token = null;
				state = UNKNOWN;
			}
		}).start();
	}

	@Override
	public void onAuthorizeComplete(String code) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("code", code);
		api.access_token(params);
		state = AUTHORIZED;
	}

	@Override
	public void onAccessTokenComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		access_token = result.get("access_token");
		Log.v("access_token", access_token);
		uid = result.get("uid");
		thirdPartyLogin();
	}

	@Override
	public void onGetTokenInfoComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRevokeAuthorizeComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		thirdPartyRemoveAccount();
	}
}
