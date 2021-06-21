package bearmaps;

import java.util.List;

import edu.princeton.cs.algs4.StdDraw;



class KDTree implements PointSet{
	private Node root;
	private int size;
	
	class Node{
		Point point;
		Node leftNode;
		Node rightNode;
		
		Node(Point p){
			point = p;
			leftNode = null;
			rightNode = null;
		}
	}
	
	KDTree(){
		root = null;
		size = 0;
	}
	KDTree(List<Point> l){
		for(Point point : l) {
			put(point);
		}
	}
	
	public int size() {
		return size;
	}
	
	
	public void put(Point p) {
		if(p == null) {
			throw new IllegalArgumentException();
		}
		root = put(p,root,true);
	}
	private Node put(Point p, Node node, boolean flag) {
		if(node == null) {
			size++;
			return new Node(p);
		}
		
		int cmp = compareIndex(p, flag, node);
			if(cmp <= 0) {
				node.leftNode = put(p, node.leftNode, !flag);
			}else {
				node.rightNode = put(p, node.rightNode, !flag);
			}
		
		return node;
		
	}
	private int compareIndex(Point p,boolean flag, Node node) {
		int cmp;
		if(flag == true) {
			cmp = Double.compare(p.getX(), node.point.getX());
		}else {
			cmp = Double.compare(p.getY(), node.point.getY());
		}
		return cmp;
	}

	@Override
	public Point nearest(double x, double y) {
		
		Point point = new Point(x, y);
		return nearest(point, root,  root.point,  true);
	}
	
	private Point nearest(Point p, Node d, Point nearestPoint, boolean flag) {
		
		if(d == null) return nearestPoint;
		
		if(d.point.equals(p)) return p;
		
		if(Point.distance(p, nearestPoint) > Point.distance(p, d.point)) {
			
			nearestPoint = d.point;
			
		}
		double PartitionDistance = comparePoints(d, p, flag);
		
		if(PartitionDistance * PartitionDistance <= Point.distance(p, nearestPoint)) {
			if(flag) {
				nearestPoint = nearest(p, d.leftNode, nearestPoint, !flag);
				if(PartitionDistance * PartitionDistance <= Point.distance(p, nearestPoint)) {
					nearestPoint = nearest(p, d.rightNode, nearestPoint, !flag);
				}
				
			}else {
				nearestPoint = nearest(p, d.rightNode, nearestPoint, !flag);
				if(PartitionDistance * PartitionDistance < Point.distance(p, nearestPoint)) {
					nearestPoint = nearest(p, d.leftNode, nearestPoint, !flag);
				}
			}
		}else {
				if(flag) {
					nearestPoint = nearest(p, d.leftNode, nearestPoint, !flag);
				}else {
					nearestPoint = nearest(p, d.rightNode, nearestPoint, !flag);
				}
		}
		return nearestPoint;
	}
	
	
	private double comparePoints(Node node, Point p, boolean flag) {
		// TODO Auto-generated method stub
		if(flag) {
			return Math.abs(p.getX() - node.point.getX());
		}else {
			return Math.abs(p.getY() - node.point.getY());
		}
	}
	public void print() {
		print(root);
	}
	private void print(Node d) {
		if(d.leftNode != null) {
			print(d.leftNode);
		}
		System.out.print(d.point);
		if(d.rightNode != null) {
			print(d.rightNode);
		}
	}
	public void draw() {
		drawPoint(root, true);
	}
	
	
	private void drawPoint(Node node, boolean flag) {
		if(node == null) return;
		StdDraw.setPenRadius();

		if(flag) {
			StdDraw.setPenColor(StdDraw.RED);
			//StdDraw.line(x0, y0, x1, y1);
		}else {
			StdDraw.setPenColor(StdDraw.BLUE);
		}
		StdDraw.setPenRadius(0.01);
		StdDraw.point(node.point.getX(), node.point.getY());
		
		drawPoint(node.leftNode, !flag);
		drawPoint(node.rightNode, !flag);
	}
	
	
	public static void main(String args[]) {
		KDTree tree = new KDTree();
		tree.put(new Point(2.0,3.0));
		tree.put(new Point(1, 5));
		tree.put(new Point(4.0,2.0));
		tree.put(new Point(4.0,5.0));
		tree.put(new Point(3.0,3.0));
		tree.put(new Point(4.0,19.0));
		//tree.print();
		Point aPoint = tree.nearest(7, 7);
		System.out.print(aPoint);
		tree.draw();
	}

}
