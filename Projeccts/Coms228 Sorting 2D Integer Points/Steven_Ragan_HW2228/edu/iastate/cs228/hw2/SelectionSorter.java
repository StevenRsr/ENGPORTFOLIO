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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**@author StevenR
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm="selection sort";
		
	}	

	
	/** @author StevenR
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO
		int min=0;
		for(int i=0;i< points.length;i++){
			 min=i;
			for(int x=i+1; x<points.length;x++) {
				if(points[x].compareTo(points[min])<0){
					min=x;
				}
			}
			swap(min,i);
		}
	}	

}	
