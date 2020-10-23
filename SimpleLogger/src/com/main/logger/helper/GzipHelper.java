package com.main.logger.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipHelper {

	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private GZIPOutputStream gzipOutputStream;
	public
	GzipHelper(File file) {
		
		this.file  = file;
		
	}
	public
	synchronized void makeZip() {
		try {
			
			fis = new FileInputStream(file);
			fos = new FileOutputStream(new File(file.getAbsolutePath()+".gzip"));
			//Encrypt the file
			gzipOutputStream = new GZIPOutputStream(fos);
			
			int i = 0;
			
				while((i=fis.read())!=-1)
					gzipOutputStream.write(i);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					file.delete();
					fis.close();
					gzipOutputStream.close();
					fos.close();
				} catch (IOException e) {
					//2nd try must be printed
					e.printStackTrace();
				}
			}
	}
}
