package _02_File_Encrypt_Decrypt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information in such a way
	 * that only authorized parties can access it and those who are not authorized
	 * cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message down based
	 * on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a message and a key from the user. Use the key to
	 * shift each letter in the users input and save the final result to a file.
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number between 0 and 26");

		char[] alphabetArray = new char[26];

		int offset = scan.nextInt() % 26; // modulo should be less than 26 now, at most 25 or something

		// {26} will give char 26 in array

		for (int i = 0; i < alphabetArray.length; i++) {
			alphabetArray[i] = (char) (i + 97);

		}
		// System.out.println(alphabetArray.length);

//	for(char c : alphabetArray) {
//		System.out.println(c);
//		
//	}

		char[] scrambleArray = new char[26];

		for (int i = 0; i < alphabetArray.length; i++) {
			scrambleArray[i] = alphabetArray[(i + offset) % 26];

		}
		for (char c : scrambleArray) {
			System.out.println(c);
		}
		System.out.println("Enter a message that you want to be encrypted");

		Scanner noah = new Scanner(System.in);

		String message = noah.nextLine();

		StringBuilder bob = new StringBuilder(message);

		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) != ' ') {
				int dumbthing = Character.getNumericValue(message.charAt(i)) - 10;
				System.out.println(dumbthing);

				bob.insert(i, scrambleArray[dumbthing]);

			} else {

				bob.insert(i, ' ');
			}
		}
		bob.setLength(bob.length() / 2);

		String finalMessage = bob.toString();

		System.out.println(finalMessage);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("noah.txt"));
			
			writer.write(finalMessage);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
