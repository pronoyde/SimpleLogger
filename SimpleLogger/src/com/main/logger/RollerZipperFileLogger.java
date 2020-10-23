package com.main.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.main.logger.helper.BaseWriter;
import com.main.logger.helper.GzipHelper;

public class RollerZipperFileLogger extends RollerFileLogger {

	public RollerZipperFileLogger(Class clazz, File file,  FileWriter out, BaseWriter baseWriter, int maxSize) {
		super(clazz, file, out, baseWriter, maxSize);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected
	void renameFile() {
		
		 try {
			 
			 String oldFile=file.getAbsolutePath();
			 //renamedFile= new File(file.getAbsolutePath()+"-"+date.toString().replace(' ', '-'));
			 
			 Path source = Paths.get(oldFile);

			 try{

				//workaround to keep fileName unique
				 Date date=new Date();
			   // rename a file in the same directory use nio classes instead of legacy renameTo()
				   String destFileName=file.getAbsolutePath()+"-"+date.toString().replace(' ', '-')+Math.random();
				   Files.move(source, source.resolveSibling(destFileName));
				   new GzipHelper(new File(destFileName)).makeZip();
				   //file.delete();

			 } catch (IOException e) {
			   e.printStackTrace();
			 }
			 baseWriter.close();
			 out.close();
			 file=new File(oldFile);
			 out = new FileWriter(file,true);
			 baseWriter= new BaseWriter(out);
			 
			 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
