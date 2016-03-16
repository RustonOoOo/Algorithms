import java.util.Arrays;

public class UnionFind implements UF{
	private int size;
	private int[] parent;//={0,0,0,0,0,0,0,0,0,0};
	public UnionFind(int N){
		parent = new int[N];
		this.size = N;
		for(int i=0;i<N;i++){
			this.parent[i] = i;
		}
	}
	@Override
	public boolean isConnected(int i,int j) {
		return find(i)==find(j);
	}

	@Override
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		for(int m=0;m<this.size;m++){
			if(parent[m]==i)parent[m]=j;
		}
	}

	@Override
	public int find(int p) {
		return parent[p];
	}
	public static void main(String[] args){
		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(2, 3);
		uf.union(3, 4);
		uf.union(9, 2);
		uf.union(8, 1);
		System.out.println(uf.isConnected(1, 9));
		System.out.println(uf.isConnected(8, 4));
		System.out.println(uf.isConnected(8, 7));
	}

}
