import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;
/*
 * data analysis
 */
public class PercolationStats {
	
	private double[] data;
	private Percolation p;
	private Random r = new Random();
	private int T;
	public PercolationStats(int N, int T){  
		// perform T independent experiments on an N-by-N grid
		this.T = T;
		data = new double[T];
		for(int i=0;i<T;i++){
			p = new Percolation(N);
			int count = 0;
			while(true){
				int m = r.nextInt(N)+1 > N ? N : r.nextInt(N)+1;
				int n = r.nextInt(N)+1 > N ? N : r.nextInt(N)+1;
				if(p.open(m, n)) count++;
				if(p.percolates()) break;
			}
			data[i] = (double)count / (N*N);
		}
		
	}   
	public double mean(){  
		// sample mean of percolation threshold
		double total = 0;
		for(int i=0;i<T;i++){
			total += data[i];
		}
		return total/T;
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		double u = mean();
		double oSquare = 0;
		for(int i=0;i<T;i++){
			oSquare += (data[i]-u)*(data[i]-u)/(T-1);
		}
		return Math.sqrt(oSquare);
		
	}
  
	public static void main(String[] args){
		Stopwatch sw = new Stopwatch();
		PercolationStats ps = new PercolationStats(300,100);
		/*
		 * if not use path compression the performance is much lower (try to comment the path compression)
		 * ex:
		 * if commented:		time£º3.323
							0.5933672222222222
			    			0.00740732531520167
			    			
			else : 				time£º0.827
							0.5925326666666669
							0.0076565089624747
				
		 *  
		 */
		double time = sw.elapsedTime();
		System.out.println("time£º" + time);
		System.out.println(ps.mean());
		System.out.println(ps.stddev());
   }
}