package com.bfbrmt.test5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	public static Map<String, String> String2Map(String jsonstr) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(jsonstr);
			@SuppressWarnings("unchecked")
			Iterator<String> iter = json.keys();
			String key;
			while (iter.hasNext()) {
				key = iter.next();
				result.put(key, json.getString(key));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
