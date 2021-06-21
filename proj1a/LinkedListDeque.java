import java.util.Iterator;

class LinkedListDeque<T> implements Iterable<T> {
	private int size;
	LinkedListNode sentinel;
	
	private class LinkedListNode<T>{
		public T data;
		public  LinkedListNode prev;
		public  LinkedListNode next;
		
		LinkedListNode(T t){
			this.data = t;
			prev = null;
			next = null;
		}
		LinkedListNode (T t, LinkedListNode p, LinkedListNode n){
			this.data = t;
			prev = p;
			next = n;
		}
		public boolean hasNext() {
			return this.next != null;
		}
		public T next() {
			return (T) next.data();
		}
		public T prev() {
			return (T) prev.data();
		}
		public T data() {
			return data;
		}
		public void remove() {
			if(this.next == null) return;
			this.prev.next = this.next;
			this.next.prev = this.prev;
			next = null;
			prev = null;
		}
		
	} 

	
	public  LinkedListDeque(){
		size = 0;
		sentinel = new LinkedListNode<Integer>(63, sentinel, sentinel);
	
	}
	public void addFirst(T item) {
		LinkedListNode node = new LinkedListNode (item);
		node.next = sentinel.next;
		sentinel.next = node;
		node.prev = sentinel;
		if (size == 0) sentinel.prev = node;
		size++;
	}
	public void addLast(T item) {
		LinkedListNode node = new LinkedListNode (item);
		node.prev = sentinel.prev;
		node.next = sentinel;
		sentinel.prev = node;
		if(size == 0) sentinel.next = node;
		size++;
		
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void printDeque() {
		String result = " [ ";
		if(size == 0) return;
		else{
			LinkedListNode node = sentinel.next;
			while(node.hasNext()) {
				System.out.println(node.data());
				node = node.next;
			}
		}
		
	}
	public T removeFirst() {
		if (isEmpty()) return null;
		else {
			LinkedListNode first = sentinel.next;
			T t = (T) first.data();
			first.remove();
			size--;
			
			return t;
		}
		
	}
	public T removeLast() {
		if(isEmpty()) return null;
		else {
			LinkedListNode last = sentinel.prev;
			last.remove();
			return (T) last.data;
		}
	}
	public LinkedListNode front() {
		if (isEmpty()) return null;
		return sentinel.next;
	}
	public T get(int index) {
		if(index < 0 || index > size || size == 0) return null;
		LinkedListNode front = this.front();
		for (int i = index; i > 0; i--) {
			front = front.next;
		}
		return (T) front.data;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new iteratorDList();
	}
	
	private class iteratorDList implements Iterator<T>{
		private LinkedListNode node = sentinel.next;
		
		public boolean hasNext() {
			return node.next != sentinel;
		}
		
		public T next() {
			T item = (T) node.data;
			node = node.next;
			return item;
			
		}
	}
}
