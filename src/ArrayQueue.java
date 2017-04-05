package hw3;

import hw3.EmptyQueueException;

public class ArrayQueue<E> { // Queue class for BFS
	protected E [] Q;
	protected int size = 0;
	protected int front = 0;
	protected int rear = 0;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		Q = (E[]) new Object[1];
	}

	public int size() { return size; }
	public boolean isEmpty() { return (size == 0); }

	public void resize(int capacity) {
		@SuppressWarnings("unchecked")
		E[] temp = (E[])new Object[capacity];
		for (int i=0; i<size; i++)
			temp[i] = Q[(front+(i+1))%Q.length];
		Q = temp;
		front = Q.length - 1;
		rear = size -1;
	} 

	public void enqueue(E newItem) {
		if ((rear+1)%Q.length == front)
			resize(2*Q.length);
		rear = (rear+1) % Q.length;
		Q[rear] = newItem;
		size++;
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) throw new EmptyQueueException("Queue is empty.");
		front = (front+1) % Q.length;
		E item = Q[front];
		Q[front] = null;
		size--;
		if (size > 0 && size == Q.length/4)
			resize(Q.length/2);
		return item;
	}
}