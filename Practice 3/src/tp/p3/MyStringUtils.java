//Flavius Ciapsa y Marcos Herrero

package tp.p3;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyStringUtils {
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
		result += elmnt;
		}
		return result;
	}
	
	public static String centre(String text, int len){
		String out = String.format(" %"+len+"s %s %"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		return out.substring((int)start, (int)end);
	}
	
	public static boolean isValidFilename(String filename) {
		try {
		   Paths.get(filename);
		   return true;
		} 
		catch (InvalidPathException ipe) {
		   return false;
		}
	}
	
	public static boolean fileExists(String filename) {
		try {
		    Path path = Paths.get(filename);
		    return Files.exists (path) && Files.isRegularFile(path);
		}
		
		catch (InvalidPathException ipe) {
		    return false; 
		}
	}
	
	public static boolean isReadable(String filename) {
		try {
		    Path path = Paths.get(filename);
		    return Files.exists (path) && Files.isRegularFile(path) && Files.isReadable(path);
		}
		
		catch (InvalidPathException ipe) {
		    return false; 
		}
	}

}
