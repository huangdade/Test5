package com.bfbrmt.test5;

import com.bfbrmt.test5.Platform.PlatformCallback;
import com.bfbrmt.test5.SinaWeibo.StateSwitchExecption;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

public class ThirdPartyPlatform {
	public static final int SINAWEIBO = 1;
	private static Context context;
	
	public static void init(Context _context) {
		context = _context;
		SinaWeibo.init(context);
	}
	
	public static Platform getPlatform(int PLATFORM) {
		Platform platform = null;
		switch (PLATFORM) {
			case SINAWEIBO:
				platform = SinaWeibo.getInstance();
				break;
			default:
				break;
		}
		return platform;
	}
	
	public static void displatPlatformList() {
		Dialog dlg = new Dialog(context);
		View dlgView = View.inflate(context, R.layout.platform_list, null);
		View sinaweibo = dlgView.findViewById(R.id.sinaweibo);
		sinaweibo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Platform weibo = ThirdPartyPlatform.getPlatform(ThirdPartyPlatform.SINAWEIBO);
				weibo.setCallback((PlatformCallback) context);
				try {
					weibo.login();
				} catch (StateSwitchExecption e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		View tecentqq = dlgView.findViewById(R.id.tecentqq);
		tecentqq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dlg.setContentView(dlgView);
		dlg.show();
	}
}
