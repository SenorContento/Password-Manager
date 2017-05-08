/*
 * TODO: 
 * Please move this class to the Utility Library and Fix it to work on all types, not just String and String[]
 * 
 */

package com.senor.packageManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {
	public JSONObject readJSON(String json) {
		JSONParser parse = new JSONParser();
		Object obj = null;
		try {
			obj = parse.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("You Cannot Have Variable JSON equal null!!! Check the function calling Functions.readJSON(String json);");
			e.printStackTrace();
		}
		
		JSONObject jsonObj = (JSONObject) obj;
		
		return jsonObj;
		//return jsonObj.toJSONString();
	}
	
	//@SuppressWarnings("unchecked")
	public String writeJSON(String[] keys, Object[] values) { //I am sure there is a better way to do this! Especially since I want it to work regardless of the type inside the array!
		//JSONObject jsonObj = new JSONObject();
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < values.length; i++) {
			map.put(keys[i], values[i]);
		}
		  
		//jsonObj.putAll(map);
		
		return JSONObject.toJSONString(map);
	}
	
	public JSONArray readJSONArray(Object object) {
		JSONArray jsonArray = (JSONArray) object;
		
        /*@SuppressWarnings("unchecked")
		Iterator<String> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
        
		return jsonArray;
	}
	
	public JSONArray writeJSONArray(Object[] array) {
		JSONArray list = new JSONArray();//new LinkedList();
		
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		
		/*list.add("foo"); 
		list.add(new Integer(100)); 
		list.add(new Double(1000.21)); 
		list.add(new Boolean(true)); 
		list.add(null);*/
		
		return list;//JSONValue.toJSONString(list);
	}
}