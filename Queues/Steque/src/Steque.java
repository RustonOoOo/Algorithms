
public class Steque<Item> {
	public int N;
	public Node first;
	public Node last;
	public class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		if(isEmpty()) last = first;//connect last and first
		else first.next = oldfirst;		
		
		N++;
	}
	public Item pop(){

		Item item = first.item;
		first = first.next;
		N--;		
		if(isEmpty()){
			System.out.println("empty");
			last=null;
		}
		return item;
	}
	public void enqueue(Item item){
		Node oldlast = last;

		last = new Node();
		last.item = item;
		if(isEmpty())first = last;//
		
		else oldlast.next = last;
		N++;
		
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
		Steque a = new Steque();
		a.push(5);
		a.push(8);
		a.enqueue(4);
		a.push(3);
		//a.push(6);
		//a.pop();
		//a.pop();
		//a.pop();
		//a.pop();
		System.out.println(a);
	}
}
