
public class Percolation {
	
	private int N;
	private UF uf;
	private boolean[][] blocks;//grids the value represents if openned


	public Percolation(int N) {
		if(N<=0)System.out.println("N should>0");//throw new IndexOutOfBoundsException();
		uf = new WUnionFind(N*N+2); //use WeightedUnionFind
		
		for(int i=1;i<=N;i++){//connect the upper wall to 0 to simplify the model
			uf.union(i, 0);
		}
		for(int i=N*N;i>N*(N-1);i--){
			uf.union(i, N*N+1);
		}
		this.N = N;
		blocks = new boolean[N+1][N+1];//N*N blocks but 0 row and 0 colum is not used
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				this.blocks[i][j] = false;
			}
		}
		this.uf = uf;	
	}

	public boolean open(int i,int j){
		if(isOpen(i,j))return false;
		if(i<=0||j<=0)throw new IndexOutOfBoundsException();
		blocks[i][j] = true;
		int id = (i-1)*N + j; //map 2D to 1D
		//System.out.println("id"+id);
		if(j>1&&blocks[i][j-1])uf.union(id, id-1);//check left
		if(j<N&&blocks[i][j+1])uf.union(id, id+1);//check right
		if(i>1&&blocks[i-1][j])uf.union(id, id - N);//check up
		if(i<N&&blocks[i+1][j])uf.union(id, id + N);//check down
		return true;
	}
	
	public boolean isOpen(int i,int j){
		return blocks[i][j];
	}

	public boolean percolates() {
		if(uf.isConnected(N*N+1,0))//N*N+1 is the virtual down wall and 0 is the virtual upper wall 
				return true;
		return false;
	}

	public boolean isFull(int row, int col) {//if the grid is connected to the virtual upper wall
		int id = (row - 1) * N + col;
		if(uf.isConnected(id, 0)){
			return true;
		}
		return false;
	}
	
}
