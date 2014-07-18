package com.bfbrmt.test5;

import java.util.Map;

import com.bfbrmt.test5.SinaWeibo.StateSwitchExecption;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Platform.PlatformCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ThirdPartyPlatform.init(this);
		TextView thirdpartyplatform = (TextView)findViewById(R.id.thirdpartyplatform);
		thirdpartyplatform.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ThirdPartyPlatform.displatPlatformList();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onThirdPartyLoginComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		switch (Integer.valueOf(result.get("state"))) {
			case 1:
				Log.v("result", "第三方登录失败");
				break;
			case 2:
				break;
			case 3:
				Log.v("result", "第三方登录成功");
				break;
			default:
		}
	}

	@Override
	public void onThirdPartyLogoutComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		switch (Integer.valueOf(result.get("state"))) {
			case 1:
				Log.v("result", "第三方已经退出登录");
				break;
			default:
		}
	}

	@Override
	public void onThirdPartyRemoveAccountComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		switch (Integer.valueOf(result.get("state"))) {
			case 1:
				Log.v("result", "第三方账号收回授权失败");
				break;
			case 2:
				break;
			case 3:
				Log.v("result", "第三方账号成功收回授权");
				break;
			default:
		}
	}

}
