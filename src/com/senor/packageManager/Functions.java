package com.senor.packageManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//import jdk.nashorn.internal.parser.JSONParser;

/*Resources
   * https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
*/
public class Functions {

	public String getCurrentWorkingDirectory() {
		//Path currentRelativePath = Paths.get("");
		//return currentRelativePath.toAbsolutePath().toString(); ///home/user/workspace/PasswordManager
		
		return System.getProperty("user.dir"); ///home/user/workspace/PasswordManager
	}
	
	public String getSlash() { //Allows file writing to work cross platform by not having something like C:\/config.json or /home/senor/\config.json
		return System.getProperty("file.separator");
	}
	
	public String newLine() { //Gets the proper newline for editors that aren't Notepad++ or some other "smart" editor!
		return System.getProperty("line.separator");
	}
	
	public String userName() { //Gets the username of the person running the program! Great to help break formality, but will probably freak people out. It shows up as "user" on my computer as I run Qubes! I should implement a way to change the name if it is "wrong", such as in my case!
		return System.getProperty("user.name");
	}
	
	public String getSystemInfo() { //This gets System Info, can be useful for debugging!
		return System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version");
	}
	
	public String readConfigFile(String configFile) throws IOException {
		File file = new File(configFile);
		Scanner scan = new Scanner(file, "UTF-8");
		String info = "";
		while (scan.hasNext())
			info += scan.nextLine() + "\n";
		return info;
	}
	
	public void writeConfigFile(String configFile, String text) throws IOException {
		File file = new File(configFile);
		
		FileOutputStream fos = new FileOutputStream(file); //Opens File
		OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8"); //Opens Write
		
		if(text == null) //Checks to see if I was fed a null pointer! Should I allow it to error out?
			out.write(""); //Currently replaces null pointer with empty string!
		else
			out.write(text); //Writes Text to File
		out.flush(); //Saves File
		out.close(); //Close File
	}
	
	public boolean doesFileOrDirectoryExist(String file) {
		File f = new File(file);
		
		if(f.exists()) { 
		    return true;
		}
		
		return false;
	}
	
	public boolean isDirectory(String file) {
		File f = new File(file);
		
		if(f.isDirectory()) { 
		    return true;
		}
		
		return false;
	}
	
	public File readFileDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(this.getCurrentWorkingDirectory())); //Sets the current working directory as the starting directory!
		int status = chooser.showOpenDialog(null);

		if (status != JFileChooser.APPROVE_OPTION) //The okay or save button was not chosen!
			return null; //What do you want the output to be?
		else {
			return chooser.getSelectedFile();
		}
	}
	
	public File writeFileDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(this.getCurrentWorkingDirectory())); //Sets the current working directory as the starting directory!
		int status = chooser.showSaveDialog(null);

		if (status != JFileChooser.APPROVE_OPTION) //The okay or save button was not chosen!
			return null; //What do you want the output to be?
		else {
			return chooser.getSelectedFile();
		}
	}
}