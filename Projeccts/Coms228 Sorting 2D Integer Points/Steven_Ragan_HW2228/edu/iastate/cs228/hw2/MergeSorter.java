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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** @author StevenR
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm="mergesort";
	}


	/**@author StevenR
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**@author StevenR
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length>1) {
			//split the things 
			int conup=0;
			int mid=pts.length/2;
			Point[] ptsR = new Point[pts.length - mid];
			Point[] ptsL = new Point[mid];

			for(int x = 0; x < ptsL.length; x++)
			{
				ptsL[x] = pts[conup];
				conup+=1;
			}
			for(int x = 0; x < ptsR.length; x++)
			{
				ptsR[x] = pts[conup];
				conup+=1;
			}
			mergeSortRec(ptsR);
			mergeSortRec(ptsL);
			
			mergePoints(ptsL,ptsR,pts);
		}else return;
	}

	
	// Other private methods if needed ...
	/**@author StevenR
	 * 
	 * @param ptsL	left half of pts
	 * @param ptsR	right half of pts
	 * @param pts	point array 
	 */
	private void mergePoints( Point[] ptsL, Point[] ptsR, Point[] pts) {
		int rightP=0;
		int leftP=0;
		int fullP=0;
		// go aslong as we got stuff to add in both things so we can keep comparing 
		while(rightP<ptsR.length&&leftP<ptsL.length) {
			
			if(ptsL[leftP].compareTo(ptsR[rightP])==-1) {
				pts[fullP]=ptsL[leftP];
				fullP+=1;
				leftP+=1;
			}else  {
				pts[fullP]=ptsR[rightP];
				fullP+=1;
				rightP+=1;
			}
		}
		//add the rest 
		while(ptsL.length>leftP) {
			pts[fullP]=ptsL[leftP];
			fullP+=1;
			leftP+=1;
		}
		while(ptsR.length>rightP) {
			pts[fullP]=ptsR[rightP];
			fullP+=1;
			rightP+=1;
		}
	}
}
