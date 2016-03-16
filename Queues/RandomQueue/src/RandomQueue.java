import java.util.HashSet;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {

	Item[] a = (Item[])new Object[1];//没有泛型数组
	int N;//length
	public void resize(int cap){
		Item[] temp = (Item[])new Object[cap];
		for(int i=0;i<N;i++){
			temp[i]=a[i];
		}
		a = temp;
	}
	public boolean isEmpty(){return N==0;}
	public void enqueue(Item item){
		if(a.length==N)resize(2*a.length);//double length
		a[N++] = item;
	}
	public Item dequeue(){
		int i = (int) (Math.random()*(N-1));
		Item t;
		t = a[N-1];
		a[N-1] = a[i];//swap a[i] and a[N-1]
		a[i] = t;
		Item item = a[N-1];
		a[N-1] = null;
		N--;
		if(N>0&&N==a.length/4)resize(a.length/2);
		return item;
	}
	public Item sample(){
		int i = (int) (Math.random()*(N-1));
		Item item = a[i];
		return item;
	}
	public String toString(){
		String s="";
		for(Item i:this){
			s+=""+i;
		}
		return s;
	}
	@Override
	public Iterator<Item> iterator() {
		
		return new RandomQueueIterator();
	}
	public class RandomQueueIterator implements Iterator<Item>{
		int i = N;
		HashSet rands = new HashSet();
		public boolean hasNext() {
			return i>0;
		}
		public Item next() {	
			System.out.println("N:  "+N);
			int k = (int) (Math.random()*N);//Math.random生成0-1(不生成1);
			while(!rands.add(k)){
				k = (int) (Math.random()*N);
				System.out.println(k);
				//break;
			}
			rands.add(k);
			i--;
			return a[k];
		}
		public void remove(){}

	}
	public static void main(String[] args){
		
		RandomQueue a = new RandomQueue();
		a.enqueue(5);
		a.enqueue(6);
		a.enqueue(1);
		a.enqueue(2);
		System.out.println(a);		
		
	}
	
}
