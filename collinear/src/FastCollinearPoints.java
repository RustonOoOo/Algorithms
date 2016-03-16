import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;


/*
 * My code file input1000.txt time :27.02
 */


public class FastCollinearPoints {
	
	private ArrayList<LineSegment> segments = new ArrayList<>();
	
	public FastCollinearPoints(Point[] points)     {
		// finds all line segments containing 4 or more points\
		Stopwatch s = new Stopwatch();
		Arrays.sort(points);
		//System.out.println(Arrays.toString(points));
		for(int i=0;i<points.length - 1;i++){
			Arrays.sort(points);//put the smallest in the front**this is very important***
			
			Arrays.sort(points,i,points.length, points[i].slopeOrder());
			//new MergeSort().mergeSort(points, i, points.length - 1, points[i].slopeOrder());
			//System.out.println(points[i]);
			//System.out.println(Arrays.toString(points));
			int count = 0;
			
			Point start = points[i];
			Point end = null;
			/*
			 * find to points[i]'s equal slope points 
			 */
			for(int j = i+1;j<points.length - 1;j++){
			//System.out.println(j);
				if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[j+1])){
					count ++;
					//System.out.println(count);
					end = points[j+1];//update end point
				}
				else if(count!=2) {//if not equal to the front then recount
					count = 0;
				}
				if(count >= 2){//
					count = 0;
					segments.add(new LineSegment(start, end));					
					//System.out.println("dd");
				}
				
			}
		}
		System.out.println(s.elapsedTime());
	}
	private void addPointsTOSegs(Point[] points, int start, int end) {
		
		
	}
	public int numberOfSegments() {
		return segments.size();
	}
	public ArrayList<LineSegment> segments() {
		// the line segments
		return segments;
	}
	
	public static void main(String[] args) {
		
		Point[] points = null;
		 try {
			 points = BruteCollinearPoints.readFile("C:\\Users\\abc84\\Downloads\\collinear\\rs1423.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    // draw the points
	    StdDraw.show(0);
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    for (LineSegment segment : collinear.segments()) {
	    	//StdOut.println(segment);
	        segment.draw();
	    }

	}
}