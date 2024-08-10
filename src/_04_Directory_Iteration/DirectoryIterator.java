package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		/*
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
//		JFileChooser jfc = new JFileChooser();
//		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		int returnVal = jfc.showOpenDialog(null);
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			File directory = jfc.getSelectedFile();
//			File[] files = directory.listFiles();
//			if(files != null) {
//				for(File f : files) {
//				  System.out.println(f.getAbsolutePath());
//				  
//				  
//				  
//				  
//				}
//			}
//		}

		// no idea wtf is happening

		/*
		 * Your task is to write a program that iterates through the src folder of this
		 * current Java Project. For every .java file it finds, the program will add a
		 * (non-legally binding) copyright statement at the bottom. Be aware of possible
		 * directories inside of directories. (e.g //Copyright © 2019 FirstName
		 * LastName)
		 */
		JFileChooser fileChooser = new JFileChooser(); // create new fileChooser
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // set which files the chooser should go thru
		int returnValue = fileChooser.showOpenDialog(null); // if opens dialog, integer value different than if it
															// doesn't open
		// check for return value -> if it opens, check approve options for jfilechooser

		if (returnValue == JFileChooser.APPROVE_OPTION) { // approve option has same sorta value as fileChooser open
															// dialog
			File directory = fileChooser.getSelectedFile(); // setting equal to selected file
			addCopyrightToJavaFiles(directory); // method

		}

	}

	public static void addCopyrightToJavaFiles(File directory) {
		// all files and directories in the current directory
		File[] files = directory.listFiles(); // array of files equal to the directory's files
		if (files != null) { // if the files thing is not empty
			for (File f : files) { // for every file
				if (f.isDirectory()) { // if the file is actually a directory then just call the method again on the
										// file
					// recursive call method if directory????
					addCopyrightToJavaFiles(f);
				} else if (f.getName().endsWith(".java")) {
					addCopyright(f); // if it ends with a .java, then that means that it is an actual thing we want
										// to mess with, so
					// we can add the copy right to it.
				}
			}
		}

	}

	public static void addCopyright(File javaFile) {
		try (FileWriter fw = new FileWriter(javaFile, true)) { // kinda strange try with a condition

			fw.write("\n" + " //copyright noah 2024"); // write
			System.out.println("Hopefully add copyright to " + javaFile.getName()); // just in case
		} catch (IOException e) { // ja
			e.printStackTrace();
		}

	}

}
