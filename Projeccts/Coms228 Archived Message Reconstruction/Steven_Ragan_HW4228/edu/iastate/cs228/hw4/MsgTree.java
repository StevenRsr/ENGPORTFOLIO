package edu.iastate.cs228.hw4;

/**
 * Public class that makes a tree in order to decode the message found within the final
 * 
 * @author StevenRagan
 */
public class MsgTree {
	
	/**
	 * Public Node character.
	 */
	public char payloadChar;
	
	/**
	 * Public left side of tree.
	 */
	public MsgTree left;
	
	/**
	 * Public right side of tree.
	 */
	public MsgTree right;
	
	/**
	 * Private int of the index within the tree string. 
	 */
	private static int staticCharIdx = 0;
	
	/**
	 * Private int of the number of char.
	 */
	private static int numChar;
	
	/**@author StevenRagan
	 * Constructor, building the tree from a string.
	 * 
	 * @param encodingString
	 */
	public MsgTree(String encodingString) {
		if (encodingString.charAt(staticCharIdx)=='^') {
			staticCharIdx++;
			left = new MsgTree(encodingString);
			right = new MsgTree(encodingString);
			
		} else {
			payloadChar = encodingString.charAt(staticCharIdx);
			staticCharIdx++;
		}
		numChar = 0;
	}
	
	/**@author StevenRagan
	 * Constructor for a single node with null children.
	 * 
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar) {
		this.left = null;
		this.right = null;
		this.payloadChar = payloadChar;
		numChar = 0;
	}
	
	/**@author StevenRagan
	 * Method to print the characters and their binary codes.
	 * 
	 * @param root
	 * @param code
	 */
	public static void printCodes(MsgTree root, String code) {
		if (root.payloadChar!='^'&&root.payloadChar!='\0'&&root.payloadChar=='\n') {
				System.out.println("   \\n      "+code);
		}else if(root.payloadChar!='^'&&root.payloadChar!='\0') {
				System.out.println("   "+root.payloadChar+"       "+code);
		}
		
		if (root.left != null) {
			printCodes(root.left,code+"0");
		}
		if (root.right != null) {
			printCodes(root.right,code+"1");
		}
	}
	/**@author StevenRagan
	 * Decodes message and prints after the printCodes method.
	 * @param codes
	 * @param msg
	 */
	public void decode(MsgTree codes, String msg) {
		MsgTree retree = codes;
		int i = 0;
		while (i < msg.length()) {
			while(retree.left!=null&&retree.right!=null) {
				 if (msg.charAt(i)=='1') {
					retree = retree.right;
				}
				if (msg.charAt(i)=='0') {
					retree = retree.left;
				}
				i++;
			}
			System.out.print(retree.payloadChar);
			retree=codes;
			numChar++;
		}
	}
	

	
}