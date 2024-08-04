package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
public static void main(String[] args) {
	int offset = 0;
	String message = "";
	
	try {
	BufferedReader reader = new BufferedReader(new FileReader("noah.txt"));
	 message = reader.readLine();
	
	 offset = Integer.parseInt(reader.readLine().trim());
	

	
	System.out.println(message + "  Key: " + offset);
	
	reader.close();
	
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	
	char[] scrambleArray = new char[26];
	
	char[] alphabetArray = new char[26];
	
	for (int i = 0; i < alphabetArray.length; i++) {
		alphabetArray[i] = (char) (i + 97);

	}
	for (int i = 0; i < alphabetArray.length; i++) {
		scrambleArray[i] = alphabetArray[(i + offset) % 26];

	}
	StringBuilder bob = new StringBuilder(message);

	for (int i = 0; i < message.length(); i++) {
		if (message.charAt(i) != ' ') {
			int dumbthing = Character.getNumericValue(message.charAt(i))-10;
			System.out.println(dumbthing);

			bob.insert(i, alphabetArray[(Math.abs(dumbthing-offset))%26]);

		} else {

			bob.insert(i, ' ');
		}
	}
	bob.setLength(bob.length() / 2);

	String finalMessage = bob.toString();
	
	System.out.println(finalMessage);


	}
}
