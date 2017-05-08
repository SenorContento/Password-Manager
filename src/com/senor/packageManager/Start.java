package com.senor.packageManager;

import java.io.File;
import java.io.IOException;
import java.sql.Array;

public class Start {
	
	public String configFile = "config.json";
	public String config = null;
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		//GUI gui = new GUI();
		//gui.startGUI(args);
		
		Start me = new Start(); //Opens up the main class as an object!
		Functions fun = new Functions(); //Opens up the Functions class to perform all the "low level" operations of this application!
		JSON json = new JSON();
		
		me.setConfigFile(fun.getCurrentWorkingDirectory() + fun.getSlash() + "config.json"); //Sets default path for config file
		me.initializeProgram(fun, me.getConfigFile()); //Reads the config file and loads it into the program!
		
		me.runTests(fun, json);
	}
	
	public void initializeProgram(Functions fun, String config) { //Attempts to read the config file and load it into the program!
		if(fun.doesFileOrDirectoryExist(config) && !fun.isDirectory(config)) { //This not only checks to make sure the CWD exists, but is also a file!
			this.setCurrentConfig(this.readConfigFile(fun, this.getConfigFile()));
		} else if(fun.isDirectory(config)) { //This checks to make sure the config is a directory!
			//How do you want to handle the config being a directory?
			//Most likely use a file dialog on every startup unless command line arguments specify the config file!
			
			File newConfig = null; //This sets a canceled transaction (such as the user canceling the prompt)

			while(newConfig == null) { //This checks to make sure it runs until the user selects a file
				newConfig = fun.readFileDialog(); //This sets the file to see if it is null
			}
			
			this.setConfigFile(newConfig.toString()); //Once a file has been chosen, set this as the new config until the next time it is run!
			this.setCurrentConfig(this.readConfigFile(fun, this.getConfigFile()));
		} else {
			this.writeConfigFile(fun, config, this.defaultConfig());
			this.setCurrentConfig(this.defaultConfig());
		}
		
		this.parseConfig(this.getCurrentConfig());
	}
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	
	public String getConfigFile() {
		return this.configFile;
	}
	
	public String defaultConfig() { //This sets the default settings for the program if no config file is specified!
		String[] keys = {};
		Object[] values = {};
		
		JSON json = new JSON();
		return json.writeJSON(keys, values);
		
		//return "{}";
	}
	
	public void setCurrentConfig(String config) {
		this.config = config;
	}
	
	public String getCurrentConfig() {
		return this.config;
	}
	
	public String readConfigFile(Functions fun, String configFile) {
		try {
			return fun.readConfigFile(configFile);
		} catch (IOException e) {
			System.out.println("Cannot read config file: \"" + configFile + "\"");
			e.printStackTrace();
			
			return e.toString();
		}
	}
	
	public String writeConfigFile(Functions fun, String configFile, String config) {
		try {
			fun.writeConfigFile(configFile, config);
			
			return "Written Config File: \"" + configFile + "\"";
		} catch (IOException e) {
			System.out.println("Cannot write config file: \"" + configFile + "\"");
			e.printStackTrace();
			
			return e.toString();
		}
	}
	
	public void parseConfig(String config) {
		
	}
	
	public void runTests(Functions fun, JSON json) {
		System.out.println("Name: " + fun.userName());
		
		System.out.println("Config File: " + this.getConfigFile());
		System.out.println("Config: " + this.getCurrentConfig());
		System.out.println("Read JSON: " + json.readJSON(this.getCurrentConfig()));
		System.out.println("Read JSON Array: " + json.readJSONArray(json.readJSON(this.getCurrentConfig()).get("List2")));
		
		Object[] array1 = {9,false,"real"};
		
		String[] keys = {"hello", "cool", "three", "List3"};
		Object[] values = {"one", false, 3, json.writeJSONArray(array1)};
		String list3 = json.writeJSON(keys, values);
		
		System.out.println("Write JSON String: " + list3);
		System.out.println("Read List 3: " + json.readJSONArray(json.readJSON(list3).get("List3")));
		
		/*String[] keysTwo = {"hello", "cool", "three"};
		String[] valuesTwo = {"one", "false", "3"};
		System.out.println("Write JSON Array: " + json.writeJSON(keys, values));*/
	}
}
