package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** @author StevenR
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort";
	}
		

	/**@author StevenR
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO 
		quickSortRec(0,points.length - 1 );
	}
	
	
	/**@author StevenR
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		
		if (first < last)
        {
		int x=partition(first,last);
		quickSortRec(first, x - 1);
		quickSortRec(x + 1, last);
        }
	}
	
	
	/**@author StevenR
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		int startplace=first-1;
		for(int x=first;x<last;x++) {
			if(points[x].compareTo(points[last])<=0) {
				startplace+=1;
				swap(x,startplace);
			}
		}
		startplace+=1;
		swap(startplace,last);
		return startplace; 
	}	
		


	
	// Other private methods if needed ...
}
