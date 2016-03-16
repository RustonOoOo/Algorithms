import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomQue1<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int size;
	private Random r = new Random();
	
	
	
	public RandomQue1() {
		items = (Item[])new Object[1];
		size = 0;
	}
	
	private void resize(int N) {
		Item[] temp = (Item[])new Object[N];
		for(int i=0;i<size;i++){
			temp[i] = items[i];
		}
		items = temp;
	}
	
	private void swap(Item[] a,int i, int j) {
		Item t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public boolean isEmpty(){
		// is the queue empty?
		return size == 0;
	}
	public int size() {
		// return the number of items on the queue
		return size;
	}
	public void enqueue(Item item) {
		// add the item
		if(size == items.length) resize(size*2);//extend arrayl
		items[size++] = item;
	}
	public Item dequeue() {
		// remove and return a random item
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int pos = r.nextInt(size);
		Item item = items[pos];
		swap(items, pos, size - 1);
		items[size - 1] = null; //±ÜÃâ¶ÔÏóÓÎÀë
		size --;
		return item;
	}
	public Item sample()  {
		// return (but do not remove) a random item
		int pos = r.nextInt(size);
		Item item = items[pos];
		return item;
	}
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RIterator();
		
	}
	private class RIterator implements Iterator<Item> {
		private Item[] copy ;
		private int N;
		
		public RIterator() {
			copy = (Item[])new Object[size];
			N = size;
			for(int i=0;i<size;i++){
				copy[i] = items[i];
			}
		}
		@Override
		public boolean hasNext() {
			return N != 0;
		}

		@Override
		public Item next() {
			int pos = r.nextInt(N);
			Item item = copy[pos];
			swap(copy, pos, --N );
			copy[N] = null;
			return item;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	public String toString() {
		String s = "";
		for(Item i : this){
			s += i.toString();
		}
		return s;
	}
	public static void main(String[] args)  {
		// unit testing
		RandomQue1<Integer> r = new RandomQue1<>();
		for(int i=0;i<10;i++){
			r.enqueue(i);
		}
		r.dequeue();
		r.dequeue();
		r.dequeue();
		r.dequeue();
		r.dequeue();
		r.dequeue();
		r.dequeue();
		r.dequeue();
		System.out.println(r);
	}
	
}
