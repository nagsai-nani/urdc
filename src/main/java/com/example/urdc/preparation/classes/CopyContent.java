package com.example.urdc.preparation.classes;


	import java.io.IOException;
	import java.io.RandomAccessFile;

	public class CopyContent {
	    public static void mains() {
	        String sourceFilePath = "C:\\Users\\harry\\Desktop\\hashwin.class";
	        String destinationFilePath = "C:\\Users\\harry\\Desktop\\ezyZip\\com\\weguard\\enterprise\\controllers";

	        long startPosition = 100; // Starting position of the content to copy (in bytes)
	        long length = 200; // Length of the content to copy (in bytes)

	        try (RandomAccessFile sourceFile = new RandomAccessFile(sourceFilePath, "r");
	             RandomAccessFile destinationFile = new RandomAccessFile(destinationFilePath, "rw")) {

	            // Set the file pointer to the desired start position in the source file
	            sourceFile.seek(startPosition);

	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            long bytesRemaining = length;

	            while (bytesRemaining > 0 && (bytesRead = sourceFile.read(buffer, 0, (int) Math.min(buffer.length, bytesRemaining))) != -1) {
	                destinationFile.write(buffer, 0, bytesRead);
	                bytesRemaining -= bytesRead;
	            }

	            System.out.println("Content copied successfully from the middle of the file.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while copying the content from the middle of the file.");
	            e.printStackTrace();
	        }
	    }
	    
	    public static void main(String[] args) {
			CopyContent.mains();	
		}
	}


