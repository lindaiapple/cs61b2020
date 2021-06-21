package bearmaps;

import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
	@Test
    public void addTest() {
		ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
		for(int i = 0; i < 100; i++) {
			a.add("hi"+i, i);
		}
		java.util.Set<String> set = a.indexMap.keySet();
		assertEquals(100, a.size());
		for(String s : set) {
			assertTrue(a.contains(s));
		}
		
	}
	@Test
	public void deleteMinTest() {
		ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
		heap.add("s", 3.3);
		heap.add("a", 1);
		heap.add("b", 4);
		heap.add("d", 0.5);
		heap.add("ss", 0.3);
		heap.removeSmallest();
		assertFalse(heap.contains("ss"));
		assertTrue(heap.contains("s"));
		assertTrue(heap.contains("a"));
		assertTrue(heap.contains("b"));
		assertTrue(heap.contains("d"));
		
	}
	@Test
	public void changePriorityTest() {
		ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
		heap.add("s", 3.3);
		heap.add("a", 1);
		heap.add("b", 4);
		heap.add("d", 0.5);
		heap.add("ss", 0.3);
		heap.changePriority("s", 100);
		double p = heap.list.get(heap.indexMap.get("s")).getPriority();
		assertTrue(100.0 == p);
		heap.changePriority("a", 10);
		p = heap.list.get(heap.indexMap.get("a")).getPriority();
		assertTrue(10 == p);
	}
	public static double runtimeTest() {
		
		ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < 1000; i++) {
			double random = StdRandom.uniform(0, 1000);
			a.add(i, random);
		}
        return sw.elapsedTime();
	}
	public void main(String args) {
		
		double a = ArrayHeapMinPQTest.runtimeTest();
		System.out.println(a);
	}
	
        
}
