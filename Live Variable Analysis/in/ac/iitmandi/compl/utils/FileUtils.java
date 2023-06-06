package in.ac.iitmandi.compl.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author arjun
 *
 */
public class FileUtils {
	
	public static boolean isDebugMode = false;
	
	public static void createDotFile(String name) {
		if(CommonUtils.isNotNull(name)) {
			File fileObj = new File(name+".dot");
			try {
				fileObj.createNewFile();
			} catch (IOException e) {
				if(isDebugMode) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void createJavaFile(String name) {
		if(CommonUtils.isNotNull(name)) {
			File fileObj = new File(name+".java");
			try {
				fileObj.createNewFile();
			} catch (IOException e) {
				if(isDebugMode) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void writeToBuffer(StringBuffer bufferObj, String text) {
		if(null!=bufferObj) {
			bufferObj.append(text);
		}
	}
	
	public static void writeDotFile(String fileName, String text) {
		try (FileWriter fileWriter = new FileWriter(fileName+".dot")){
			fileWriter.write(text);
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public static void writeJavaFile(String fileName, String text) {
		try (FileWriter fileWriter = new FileWriter(fileName+".java")){
			fileWriter.write(text);
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
}