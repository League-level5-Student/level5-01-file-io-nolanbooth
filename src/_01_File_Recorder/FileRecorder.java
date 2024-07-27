package _01_File_Recorder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	public static void main(String[] args) {
	
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("thing.txt"));
			writer.write("noah");
			writer.write("\n");
			
			Scanner scan = new Scanner(System.in);
			
			String message = scan.nextLine();
			
			
			writer.write(message);
			
			writer.close();
		
		}catch(IOException e){
		e.printStackTrace();		
				
			}
		
		
	
	
	
	}
	
}
