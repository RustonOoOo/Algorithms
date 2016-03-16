import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private int N;//length
	private Node first;
	private Node last;
	class Node{
		Item item;
		Node previous;
		Node next;
		Node(Item item){
			this.item = item;
		}
		public String toString(){
			return ""+item;
		}
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void pushLeft(Item item){
		Node oldfirst = first;
		first = new Node(item);
		if(isEmpty())last = first;
		
		else{
			first.next = oldfirst;
			oldfirst.previous = first;
		}				
		N++;
	}
	public void pushRight(Item item){
		Node oldlast = last;
		last = new Node(item);
		if(isEmpty())first = last;
		else {
			oldlast.next = last;
			last.previous = oldlast;
		}
		N++;		
	}
	public Item popLeft(){
		Item item = first.item;
		first = first.next;
		//first.previous = null ;
		N--;
		if(isEmpty()){
			System.out.println("Empty");
			last = null;
		}
		return item;
	}
	public Item popRight(){
		Item item = last.item;
		last = last.previous;//此时last还未进入垃圾回收
		last.next = null;//避免对象游离
		N--;
		if(isEmpty()){
			System.out.println("Empty");
			first = null;
		}
		return item;
	}
	public String toString(){
		String s="";
		Node current=first;
		while(current!=null){
			s+=current.item + " ";
			current = current.next;
		}
		return s;
	}

	public static void main(String[] args){
		Deque a = new Deque();
		a.pushLeft("00");
		a.pushLeft("22");
		a.pushRight(3);
		a.pushRight(4);
		a.popRight();
		a.popLeft();
		//a.popRight();
		System.out.println(a);
	}
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		
		return new DequeIterator();
	}
	public class DequeIterator implements Iterator<Item>{
		
		Node current  = first;
		public boolean hasNext(){return current!=null;}
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove() {}
		
	}
		
	
}
