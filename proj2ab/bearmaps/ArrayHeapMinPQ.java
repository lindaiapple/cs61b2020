package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;



public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	 ArrayList<PriorityNode> list;
	 HashMap<T, Integer> indexMap;
	
	public ArrayHeapMinPQ() {
		list = new ArrayList<PriorityNode>();
		indexMap = new HashMap<>();
		// TODO Auto-generated constructor stub
	}
	
	class PriorityNode implements Comparable<PriorityNode> {
       private T item;
       private double priority;

       PriorityNode(T e, double p) {
           item = e;
           priority = p;
       }

       public T getItem() {
           return item;
       }

       public double getPriority() {
           return priority;
       }

//       void setPriority(double priority) {
//           this.priority = priority;
//       }

       @Override
       public int compareTo(PriorityNode other) {
           if (other == null) {
               return -1;
           }
           return Double.compare(this.getPriority(), other.getPriority());
       }

       @Override
       @SuppressWarnings("unchecked")
       public boolean equals(Object o) {
           if (o == null || o.getClass() != this.getClass()) {
               return false;
           } else {
               return ((PriorityNode) o).getItem().equals(getItem());
           }
       }

       @Override
       public int hashCode() {
           return item.hashCode();
       }
       public String toString() {
       	String string = "[ ";
       	string += item + ":" + priority + " ],";
       	return string;
       }
   }

	@Override
	public void add(T item, double priority) {
		// TODO Auto-generated method stub
		if(indexMap.containsKey(item)) {
			throw new IllegalArgumentException("can not duplicates!");
		}
		PriorityNode node = new PriorityNode(item, priority);
		list.add(node);
		swim(list.size()-1);
		indexMap.put(item, list.size() - 1);
	}
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public boolean contains(T item) {
		if(item == null) {
			throw new IllegalArgumentException("the item imput is null");
		}
		// TODO Auto-generated method stub
		return indexMap.containsKey(item);
		
	}

	@Override
	public T getSmallest() {
		// TODO Auto-generated method stub
		return list.get(0).item;
	}

	@Override
	public T removeSmallest() {
		if(list.size() == 0) return null;
		// TODO Auto-generated method stub
		T item = list.get(0).getItem();
		Collections.swap(list, 0, list.size()-1);;
		list.remove(list.size()-1);
		sink(0,list.size());
		indexMap.remove(item);
		return item;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void changePriority(T item, double priority) {
		// TODO Auto-generated method stub
		if(!indexMap.containsKey(item)) {
			 throw new NoSuchElementException("Item doesn't exist");
		}
		
		//double oldPriority = indexMap.get(item).priority;
		PriorityNode newNode = new PriorityNode(item, priority);
		//indexMap.replace(item, newNode);
		//int index = list.indexOf(newNode);
		//list.set(index, newNode);
		int index = indexMap.get(item);
		double oldPriority = list.get(index).getPriority();
		list.set(index, newNode);
		if(oldPriority < priority) {
			sink(index, list.size());
		}else if(oldPriority > priority) {
			swim(index);
		}
		
	}
	
//	private int search(ArrayList<PriorityNode> l, double p) {
//		int lo = 0;
//		int hi = l.size()-1;
//		while(lo <= hi) {
//			int mid = lo + (hi-lo)/2;
//			if(l.get(mid).getPriority() < p) hi = mid-1;
//			else if(l.get(mid).getPriority() > p) lo = mid+1;
//			else return mid;
//		}
//		return -1;
//	}
//	private void sort(ArrayList<PriorityNode> list) {
//		int N = list.size();
//		while(N > 1) {
//			Collections.swap(list, 0, N-1);
//			N--;
//			sink(0,N);
//		}
//	}
	

	private void swap(int i, int j) {
		T item1 = list.get(i).getItem();
		T item2 = list.get(j).getItem();
		Collections.swap(list, i, j);
		indexMap.replace(item1, j);
		indexMap.replace(item2, i);
		
	}
	private boolean less(int a, int b) {
		return list.get(a).compareTo(list.get(b)) < 0;
	}
	private void swim(int k) {
		while(k > 0 && less(k, (k-1)/2 )) {
			swap( k, (k-1)/2);
			k = (k-1)/2;
		}
		
	}
	private void sink(int k, int size) {
		while(2*k + 1 < size) {
			int j = 2*k +1;
			if(j+1 < size && less(j+1, j)) j++;
			if(less(k, j)) break;
			swap( k, j);
			k = j;
		}
	}
	public String print() {
		String resultString = "[ ";
		for(PriorityNode node : list) {
			resultString += node.toString();
			resultString += " ";
		}
		resultString += " ]";
		return resultString;
	}

	public static void main (String args[]) {
		ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
		heap.add("s", 3.3);
		heap.add("a", 1);
		heap.add("b", 4);
		heap.add("d", 0.5);
		heap.add("ss", 1);
		//heap.removeSmallest();
		System.out.print(heap.contains("c"));
		System.out.print(heap.print());
		heap.changePriority("s", 10);
		heap.changePriority("a", 0.3);
		heap.changePriority("b", 1);
		System.out.print(heap.print());
		//heap.add("a", 1);
		
	}
	
}