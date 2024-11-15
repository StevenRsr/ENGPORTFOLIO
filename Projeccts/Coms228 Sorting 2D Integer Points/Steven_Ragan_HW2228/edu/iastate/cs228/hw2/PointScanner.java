package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author 
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	

	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**@author StevenR
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts==null||pts.length==0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			points =pts;
			sortingAlgorithm =algo;
		}
	}

	
	/**@author StevenR
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		// TODO
		try {
			File in =new File(inputFileName);
			Scanner c=new Scanner(in);
			int amoutx=0;
			while(c.hasNextInt()) {
				c.nextInt();
				amoutx++;
			}
			c.close();
			c=new Scanner(in);
			if(amoutx%2!=0) {
				
				throw new InputMismatchException();
			}
			points= new Point[amoutx/2];
			int amoutxx=0;
			while(c.hasNextInt()) {
				points[amoutxx]=new Point(c.nextInt(),c.nextInt());
				amoutxx++;
			}
			c.close();
			sortingAlgorithm =algo;
		}catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}

	
	/**@author StevenR
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter; 
		aSorter=null;
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		if(sortingAlgorithm==Algorithm.QuickSort)
		{
			aSorter =new QuickSorter(points);
		}else if(sortingAlgorithm==Algorithm.InsertionSort)
		{
			aSorter =new InsertionSorter(points);
		}else if(sortingAlgorithm==Algorithm.SelectionSort)
		{
			aSorter =new SelectionSorter(points);
		}else if(sortingAlgorithm==Algorithm.MergeSort)
		{
			aSorter =new MergeSorter(points);
		}
		aSorter.setComparator(1);
		long sT = System.nanoTime(); 
		aSorter.sort();
		long eT = System.nanoTime();
		scanTime = eT - sT;
		int Ymid = aSorter.getMedian().getY();
		aSorter.setComparator(0);
		 sT = System.nanoTime(); 
		aSorter.sort();
		 eT = System.nanoTime();
		scanTime=scanTime+eT-sT;
		medianCoordinatePoint = new Point(aSorter.getMedian().getY(),Ymid);
		
	
		
	}
	
	
	/**@author StevenR
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String Re= null;
		if(sortingAlgorithm==Algorithm.QuickSort)
		{
			Re="QuickSort         "+points.length+"  "+scanTime;
		}else if(sortingAlgorithm==Algorithm.InsertionSort)
		{
			Re="InsertionSort     "+points.length+"  "+scanTime;
		}else if(sortingAlgorithm==Algorithm.SelectionSort)
		{
			Re="SelectionSort     "+points.length+"  "+scanTime;
		}else if(sortingAlgorithm==Algorithm.MergeSort)
		{
			Re="MergeSort         "+points.length+"  "+scanTime;
		}
		return Re; 
	
	}
	
	
	/**@author StevenR
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: "+medianCoordinatePoint.toString(); 

}

	
	/**@author StevenR
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		try {
			FileWriter out= new FileWriter("outPut.txt");
			out.write(toString());
			out.close();
		}catch (Exception e) {
			throw new FileNotFoundException();
		
		}	
	}
}
