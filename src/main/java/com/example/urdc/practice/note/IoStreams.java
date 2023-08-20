//package com.example.urdc.practice.note;
//
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.spring.webApp.models.FileUploader;
//@Service
//public class IoStreams {
//
//	public File upload(FileUploader fileUploader) throws Exception {
//		
//		File mfile = fileUploader.getFile();
//		if (mfile==null) {
//			System.out.println("Nagsai ");
//			throw new Exception("Please provide a file");
//		}
//	String directory=fileUploader.getTempDir();
//		
//		String extension = getFileExtension(originalName);
//		boolean isPdf = false;
//		isPdf = ".pdf".equalsIgnoreCase(extension) ? true : false;
//		if (!isPdf) {
//			throw new Exception("Please provide a pdf file");
//		}
//		byte[] data = null;
//		try {
//		data = mfile.getBytes();
//		}catch (IOException e) {
//			e.getMessage();
//		}
//		
//		InputStream fileStream = new ByteArrayInputStream(data);
//		File file = createFile(directory,originalName, data, false);
//		return file;
//	}
//
//	private File createFile(String fileName, String originalName, byte[] data, boolean b) throws IOException {
//		File file = new File(fileName,originalName);
//		BufferedOutputStream bout = null;
//		bout = new BufferedOutputStream(new FileOutputStream(file));
//		file.createNewFile();
//		file.getAbsoluteFile();
//		bout.write(data);
//		bout.flush();
//		bout.close();
//		return file;
//	}
//
//	
//	 private String getFileExtension(String name) { 
//		 String extension ="";
//	        if(name!=null) { 
//	        	extension=name.substring(name.lastIndexOf("."));
//	        	} 
//	        		return extension; 
//	 	}
//	 
//
//}
