package bearmaps;

import java.util.List;



public class NativePointSet implements PointSet {
	private List<Point> points;
	public NativePointSet(List<Point> points) {
		this.points = points;
	}
	
	@Override
	public Point nearest(double x, double y) {
		// TODO Auto-generated method stub
		Point point = new Point(x,y);
		Point bestPoint = points.get(0);
		for(Point p : points) {
			if(Point.distance(p, point) < Point.distance(bestPoint, point)) {
				bestPoint = p;
			}
		}
		return bestPoint;
	}
	
}
