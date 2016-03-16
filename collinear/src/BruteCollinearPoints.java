import java.awt.LinearGradientPaint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class BruteCollinearPoints {
	
	private int numberOfSegments = 0;
	private ArrayList<LineSegment> segments ;

	public BruteCollinearPoints(Point[] points){
		// finds all line segments containing 4 points
		Stopwatch s = new Stopwatch();
		segments = new ArrayList<>();
		if(points == null) throw new NullPointerException();
		for(int i=0;i<points.length;i++){
			if (points[i] == null) {
				throw  new NullPointerException();
			}
		}
		int N = points.length;
		int counter = 0;
		for(int i=0;i<N;i++) {//N^4
			for(int j=i+1;j<N;j++){
				for(int k=j+1;k<N;k++){
					for(int n=k+1;n<N;n++){
						//System.out.println("j"+j);
						//if(points[i].compareTo(points[j])==0||)
						double slope1 = points[i].slopeTo(points[j]);
						double slope2 = points[i].slopeTo(points[k]);
						double slope3 = points[i].slopeTo(points[n]);
						if(slope1 == slope2 && slope1 == slope3 ) addPointsToSegements(points, i, j, k, n);
					}
				}
			}
		}
		System.out.println(s.elapsedTime());
	}
	public int numberOfSegments() {
		// the number of line segments
		return segments.size();
	}
	private void  addPointsToSegements(Point[] points,int i,int j,int k,int n) {
		Point[] p = new Point[]{
				points[i],points[j],points[k],points[n]
		};
		Point min = p[0];
		Point max = p[0];
		for(int s=0;s<p.length;s++){
			if(p[s].compareTo(min)<0)min = p[s];
			else if(p[s].compareTo(max)>0) max = p[s];
		}
		LineSegment l = new LineSegment(min, max);
		segments.add(l);
	}
	public ArrayList<LineSegment> segments() {
		return segments;
	}
	
	public static Point[] readFile(String name) throws IOException {
		File file = new File(name);
		Point[] points = null;
		InputStream in;				
		if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader r = new BufferedReader(reader);
			String line = r.readLine();
			line = line.trim();
			int N = Integer.parseInt(line);
			points = new Point[N];
			int k = 0;
			while((line = r.readLine())!=null&&k<N){
				line = line.trim();
				int i = line.lastIndexOf(" ");
				int j = line.indexOf(" ");
				Point p = new Point(Integer.parseInt(line.substring(0,j)), Integer.parseInt(line.substring(i+1,line.length())));
				points[k++] = p;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return points;
	}

	public static void main(String[] args) {
		Point[] points = null;
		 try {
			 points = readFile("C:\\Users\\abc84\\Downloads\\collinear\\horizontal100.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
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
	    	StdOut.println(segment);
	        segment.draw();
	    }

	}

}
