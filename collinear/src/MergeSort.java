import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.Exchanger;

import edu.princeton.cs.algs4.LongestCommonSubstring;

public class MergeSort {
	int count = 0;
	
	private final static int CUTOFF = 7;
	public void mergeSort(Object[] objects,int lo, int hi, Comparator c) {
		Object[] temp = new Object[objects.length];
		//System.out.println(objects.length);
		mergeSort(objects, temp, lo, hi, c);
	}
	private void mergeSort(Object[] objects,Object[] temp, int lo, int hi, Comparator c) {
		
		//if(lo + CUTOFF>=hi) {
		//	insertionSort(objects,lo,hi,c);
		//	return;
		//}
		if (lo >= hi) {
			return;
		}
		int mid = lo + (hi - lo)/2;
		mergeSort(objects, temp, lo, mid , c);
		mergeSort(objects, temp, mid + 1, hi, c);
		merge(objects, temp, lo, mid, hi, c);
		
	}
	private void insertionSort(Object[] objects, int lo, int hi, Comparator c) {
		for(int i=lo+1;i<=hi;i++){
			for(int j=i;j>lo&&less(objects[j], objects[j-1], c);j--){//j ¼ÇµÃ+1
				exch(objects,j,j-1); 
			}
		}
	}
	private void exch(Object[] objects, int j, int i) {
		Object temp = objects[j];
		objects[j] = objects[i];
		objects[i] = temp;
	}
	private void merge(Object[] objects,Object[] temp,int lo, int mid, int hi, Comparator c ){
		int i = lo;
		int j = mid + 1;
		int k = lo;
		while(i<=mid&&j<=hi){
			if(less(objects[j],objects[i],c)){
				temp[k++] = objects[j];
				j++;
			}
			else{
				temp[k++] = objects[i];
				i++;
			}
		}
		while(i<=mid){
			temp[k++] = objects[i++];
		}
		while(j<=hi){
			temp[k++] = objects[j++];
		}
		//System.out.println(Arrays.toString(temp));
		for(int m=lo;m<k;m++){
			objects[m] = temp[m];
		}
		count ++;
		if(count == 7)System.out.println(Arrays.toString(objects));
	}
	public boolean isSorted(Object[] objects,Comparator c) {
		for(int i=1;i<objects.length;i++){
			if(less(objects[i],objects[i-1], c))return false;
		}
		return true;
	}
	private static Comparator<Integer> comparator() {
		
		return new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
			//	System.out.println("o1:  "+o1+"o2:  "+o2);
				return o1 - o2;
			}
		};
	}
	private boolean less(Object object1, Object object2,Comparator c) {
		return c.compare(object1, object2)<0;
	}
	public static void main(String[] args) {
		Integer[] a = new Integer[20];
		Random r = new Random();
		for(int i=0;i<20;i++){
			a[i] = r.nextInt(30);
		}
		//System.out.println(Arrays.toString(a));
		MergeSort mergeSort = new MergeSort();
		//mergeSort.mergeSort(a,0,a.length-1,comparator());
		//mergeSort.insertionSort(a, 0, a.length, comparator());
		//System.out.println(Arrays.toString(a) + mergeSort.isSorted(a, comparator()));
		Integer[] b = new Integer[]{
				38, 77, 57, 94, 78, 41, 60, 91, 74, 72, 21, 73 
		};
		mergeSort.mergeSort(b, 0, b.length - 1, comparator());
	}
}
