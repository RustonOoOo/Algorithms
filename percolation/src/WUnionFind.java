
public class WUnionFind implements UF{
	private int[] parent;
	private int[] size;
	
	public WUnionFind(int N){
		parent = new int[N];
		size = new int[N];
		for(int i=0;i<N;i++){
			this.parent[i] = i;
			this.size[i] = 1;
		}
	}
	@Override
	public boolean isConnected(int i, int j) {
		return find(i)==find(j);
	}

	@Override
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(size[rootP]<size[rootQ]){//&&rootP!=0){
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP]; 
		}
		else {
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		//System.out.println("union  "+p+"  "+q);
	}

	@Override
	public int find(int p) {
		while(p!=parent[p]){
			parent[p] = parent[parent[p]];//compression
			p = parent[p];
		}
		return p;
	}
	public static void main(String[] args){
		WUnionFind uf = new WUnionFind(10);
		uf.union(1, 2);
		uf.union(2, 3);
		uf.union(5, 4);
		uf.union(6, 4);
		uf.union(2, 4);
		System.out.println(uf.isConnected(1, 9));
		System.out.println(uf.isConnected(8, 4));
		System.out.println(uf.isConnected(8, 7));
	}
}
