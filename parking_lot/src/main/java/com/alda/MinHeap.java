package com.alda;

public class MinHeap {
	
	private int[] Heap;
	private int size;
	private int maxsize;
	
	private static final int FRONT = 1;
	
	public MinHeap(int maxsize) {
		this.size = 0;
		this.maxsize = maxsize;
		this.Heap = new int[this.maxsize + 1];
		Heap[0] = Integer.MIN_VALUE;
	}
	
	private int parent (int pos) {
		return pos/2;
	}
	
	private int leftChild (int pos) {
		return (2 * pos);
	}
	
	private int rightChild (int pos) {
		return (2 * pos) + 1;
	}
	
	private boolean isLeaf (int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		
		return false;
	}
	
	private void swap(int fpos, int spos) {
		int tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}
	
	private void minHeapify(int pos) {
		
		if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)]
                || Heap[pos] > Heap[rightChild(pos)]) {
 
                // Swap with the left child and heapify
                // the left child
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
 
                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
		
	}
	
	public int getSize() {
		return size;
	}
	
	public void insert (int element) {
		if (size >= maxsize) {
			return;
		}
		
		Heap[++size] = element;
		int current = size;
		
		while (Heap[current] < Heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}
	
	// Function to print the contents of the heap
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(" PARENT : " + Heap[i]
                             + " LEFT CHILD : " + Heap[2 * i]
                             + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }
    
	public int remove() {
		int popped = Heap[FRONT];
		Heap[FRONT] = Heap[size];
		minHeapify(FRONT);
		size--;
		return popped;
	}
	
}
