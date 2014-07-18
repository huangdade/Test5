package com.bfbrmt.test5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;

public class SinaWeiboLoginActivity extends Activity {
	private WebView loginPage;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Bundle bundle = getIntent().getExtras();
		String gets = bundle.getString("gets");
		String url = bundle.getString("url");
		final String redirect_uri = bundle.getString("redirect_uri");
		loginPage = (WebView)findViewById(R.id.loginPage);
		loginPage.setWebViewClient(new WebViewClient() {
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				Log.v("mark", "receive ssl error");
				handler.proceed();
			}
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url != null) {
					Log.v("webview url", url);
					String regEx = "^(http://)?" + redirect_uri + "(/)?\\?code=(\\w+)";
					Log.v("reg", regEx);
					Pattern pat = Pattern.compile(regEx);
					Matcher mat = pat.matcher(url);
					if (mat.find()) {
						SinaWeiboLoginActivity.this.finish();
						Log.v("info", "Authorize succeed");
						String code = mat.group(3);
						Log.v("code", code);
						SinaWeiboAPI api = SinaWeiboAPI.getInstance();
						api.callback.onAuthorizeComplete(code);
						Log.v("mark", "2.1");
					} else {
						view.loadUrl(url);
					}
				}
				return true;
			}
		});
		loginPage.getSettings().setJavaScriptEnabled(true);
		loginPage.loadUrl(url + "?" + gets);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
