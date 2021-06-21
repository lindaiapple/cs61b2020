package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KDTreeTest {
	@Test
	public void putTest() {
		List<Point> points = new ArrayList<>();
		points.add(new Point(2,3));
		points.add(new Point(4, 2));
		points.add(new Point(4, 5));
		points.add(new Point(3, 3));
		points.add(new Point(1, 5));
		points.add(new Point(4, 4));
		//Point searchPoint = new Point(0, 7);
		NativePointSet set = new NativePointSet(points);
		Point best2 = set.nearest(0, 7);
		KDTree tree = new KDTree(points);
		
		Point best1 = tree.nearest(0, 7);
		assertEquals(best1, best2);
	}
	@Test
	public void randomizedTest() {
		Random random = new Random();
		List<Point> lists = new ArrayList<>();
		random.setSeed(2);
		for(int i = 0; i<100000; i++) {
			double rand1 = random.nextDouble();
			double rand2 = random.nextDouble();
			lists.add(new Point(rand1, rand2));
		}
		NativePointSet set = new NativePointSet(lists);
		KDTree tree = new KDTree(lists);
		for(int j = 0; j<10000;j++) {
			double testX = random.nextDouble();
			double testY = random.nextDouble();
			Point navieBestPoint = set.nearest(testX, testY);
			Point kDtreePoint = tree.nearest(testX, testY);
			assertEquals(navieBestPoint, kDtreePoint);
		}
	}
}
