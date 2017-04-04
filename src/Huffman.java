package hw3;

import hw3.Entry;

public class Huffman<Key extends Comparable<Key>, Value> {
	private Entry[] a;
	private int N;
	
	public Huffman(Entry[] harray, int initialSize) {
		a = harray;
		N = initialSize;
	}
	
	public int size() { return N; }
	
	public void createHeap() {
		for (int i=N/2; i>0; i--) {
			downheap(i);
		}
	}
	
	private boolean greater(int i, int j) {
		return a[i].getKey() > a[j].getKey();
	}
	
	private void swap(int i, int j) {
		Entry temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public Entry deleteMin() {
		Entry min = a[1];
		swap(1, N--);
		a[N+1] = null;
		downheap(1);
		return min;
	}
	
	private void downheap(int i) {
		while (2*i <= N) {
			int k = 2*i;
			if (k < N && greater(k, k+1)) k++;
			if (!greater(i, k)) break;
			swap(i, k);
			i = k;
		}
	}
	
	public void insert(Entry temp) {
		a[++N] = temp;
		upheap(N);
	}
	
	public void upheap(int j) {
		while (j>1 && greater(j/2, j)) {
			swap(j/2, j);
			j = j/2;
		}
	}
	
	public Entry createTree() {
		while(size() > 1) {
			Entry e1 = deleteMin();
			Entry e2 = deleteMin();
			Entry temp = new Entry(e1.getKey()+e2.getKey(),
					e1.getValue()+e2.getValue(),
					e1, e2, "");
			insert(temp);
		}
		return deleteMin();
	}
}