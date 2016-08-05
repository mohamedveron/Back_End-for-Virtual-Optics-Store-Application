package Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtilities {
	public static void saveFile(String path, InputStream reader) throws Exception{
		System.out.println(path);
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		file.createNewFile();
		OutputStream writer = new FileOutputStream(file);
		byte[] buffer = new byte[4096];
		int count = -1;
		while((count = reader.read(buffer)) != -1){
			writer.write(buffer, 0, count);
		}
		writer.close();
		reader.close();
	}
}
