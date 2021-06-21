import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


class ArrayDeque<T> implements Iterable<T> {
	private int rear;
	private int front;
	private T[] array;
	private int size;
	
	public ArrayDeque(int length) {
		array = (T[])new Object[length];
		front = -1;
		rear = 0;
		size = 0;
	}
	
	public ArrayDeque() {
		array = (T[])new Object[1];
		front = -1;
		rear = 0;
		size = 0;
	}
	
	@Test
	public void test() {
		int[] a = {3, 2};
		int[] b = {4, 2};
		assertArrayEquals( a, b );
	}
	
	
	public int size() {
		return size;
		
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void insertFront(T item) {
		if(size == array.length) {
			resize(2 * size);
		}
		
			if(size == 0) {
				front = 0;
			}
			else {
				front = Math.floorMod((front -1),array.length);
			}
			
			System.out.println("length :" + array.length);
			System.out.println("front :" + front);
			
			array[front] = item;
			System.out.println("array[front]" + front + array[front]);
			
			size ++;
			System.out.println("size :" + size);
			
		
		
	}
	
	
	private void resize( int t) {
		T[] temp = (T[]) new Object[t];
		
		int index = 0;
		int i = front;
		while(index < size) {
			temp[index] = array[i];
			index++;
			i = Math.floorMod((i + 1),array.length);
		}
		
		
		array = temp;
		front = 0;
		rear = size -1;
	}
	
	public void insertBack(T item) {
		if(size == array.length) {
			resize(2 * size);
		}
		
			if(size == 0) {
				front = 0;
				rear = 0;
			}
			else {
				rear = Math.floorMod((rear +1),array.length);
			}
			
			System.out.println("length :" + array.length);
			
			
			array[rear] = item;
			System.out.println("array[front]" + front + " " + array[rear]);
			
			size ++;
			System.out.println("size :" + size);
			
		
		
	}
	
	public T removeFront() {
		if( size == 0) return null;
		T f = array[front];
		array[front] = null;
		size--;
		if(size == 0) {
			front = -1;
			rear = 0;
		}else {
			front = (front + 1) % array.length;
		}
		
		if (size == array.length / 4) {
            resize(array.length / 2);
        }

		return f;
	}
	
	public T removeBack() {
		if(size == 0) return null;
		T r = array[rear];
		array[rear] = null;
		size --;
		
		if(size == 0) {
			rear = 0;
			front = -1;
		}else {
			rear = (rear - 1) % array.length;
		}
		
		if (size == array.length / 4) {
            resize(array.length / 2);
        }
		
		return r;
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<T> {

        int index = front;
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size ;
        }

        @Override
        public T next() {
            T item = array[index];
            i++;
            index = Math.floorMod((index + 1),array.length);
            return item;
        }
    }
	
}
