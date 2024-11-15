package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main method
 * Asks for a files name, reads said file prints codes for said file and then decodes said file.
 * 
 * 
 * @throws FileNotFoundException if file is not found
 * @author StevenRagan
 *
 */
public class MainClass {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter filename to decode:");
		String fileName = scn.next();  
		File fin = new File(fileName);
		Scanner scFile = new Scanner(fin);
		String enMsg = "";
		Scanner scEn = new Scanner(fin);
		while (scEn.nextLine().contains("^")) {			
			enMsg =enMsg+scFile.nextLine()+ "\n";
		}
		MsgTree tree = new MsgTree(enMsg);
		System.out.println("character  code\n-------------------------");
		MsgTree.printCodes(tree, "");
		System.out.println("\nMESSAGE:");
		String aMsg = scFile.nextLine();
		tree.decode(tree, aMsg);
		scn.close();
		scEn.close();
		scFile.close();
	}
}
	

	