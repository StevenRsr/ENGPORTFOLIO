package edu.iastate.cs228.hw2;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**@author StevenR
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4]; 
		int c=0,t=0;
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		Scanner sc=new Scanner(System.in);
		while(c!=3) {

			System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
			c=sc.nextInt();
			if(c==2||c==1) {
				t++;
				System.out.println("Trial "+t+": "+c);
			}
			if(c==2) {
				System.out.print("Points From a File\r\nFile Name: ");
				String file = sc.next();
				
				scanners[0] = new PointScanner(file, Algorithm.QuickSort);
				scanners[0].scan();
				
				scanners[1] = new PointScanner(file, Algorithm.MergeSort);
				scanners[1].scan();
				
				scanners[2] = new PointScanner(file, Algorithm.InsertionSort);
				scanners[2].scan();
				
				scanners[3] = new PointScanner(file, Algorithm.SelectionSort);
				scanners[3].scan();
				
				System.out.println("algorithm size  time (ns)");
				System.out.println("----------------------------------");
				System.out.println(scanners[3].stats());
				System.out.println(scanners[2].stats());
				System.out.println(scanners[1].stats());
				System.out.println(scanners[0].stats());
			}else if(c==1) {
				Random r = new Random();
				System.out.print("Enter number of random points: ");
				int numP= sc.nextInt();
				scanners[0] = new PointScanner(generateRandomPoints(numP,r), Algorithm.QuickSort);
				scanners[0].scan();
				
				scanners[1] = new PointScanner(generateRandomPoints(numP,r), Algorithm.MergeSort);
				scanners[1].scan();
				
				scanners[2] = new PointScanner(generateRandomPoints(numP,r), Algorithm.InsertionSort);
				scanners[2].scan();
				
				scanners[3] = new PointScanner(generateRandomPoints(numP,r), Algorithm.SelectionSort);
				scanners[3].scan();
				
				System.out.println("algorithm size  time (ns)");
				System.out.println("----------------------------------");
				System.out.println(scanners[3].stats());
				System.out.println(scanners[2].stats());
				System.out.println(scanners[1].stats());
				System.out.println(scanners[0].stats());
			
			}
			if(c!=3) {
			System.out.println("----------------------------------");
			System.out.println();
			}
			
		}
		sc.close();
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**@author StevenR
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts<1) {
			throw new IllegalArgumentException();
		}
		Point[] re=new Point[numPts];
		for(int x=0;x<numPts;x++) {
			re[x]=new Point(rand.nextInt(101)-50,rand.nextInt(101)-50);
		}
		return re; 
		// TODO 
	}
	
}
