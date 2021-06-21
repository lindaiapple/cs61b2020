package bearmaps.proj2c;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import bearmaps.ArrayHeapMinPQ;

import edu.princeton.cs.algs4.Stopwatch;



public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
	
	private HashMap<WeightedEdge<Vertex>, Vertex> edgeTo;
	private HashMap<Vertex, Double> distTo;
	//private HashSet<Vertex> visited;
	private ArrayHeapMinPQ<Vertex> pq;
	private SolverOutcome outcome;
	private ArrayList<Vertex> solution;
	private double solutionWeight ;
	private int numStatesExplored = 0;
	private double timeSpent;
	private Vertex startVertex;
	private Vertex endVertex;
    private final AStarGraph<Vertex> graph;
    
	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
		Stopwatch sw = new Stopwatch();
				graph = input;
				startVertex = start;
				endVertex = end;
				//visited = new HashSet<>();
				edgeTo = new HashMap<WeightedEdge<Vertex>, Vertex>();
				distTo = new HashMap<Vertex, Double>();
				pq = new ArrayHeapMinPQ<>();
				solution = new ArrayList<Vertex>();
				
				if(start.equals(end)) {
					solutionWeight = 0;
					solution.add(start);
					outcome = SolverOutcome.SOLVED;
					numStatesExplored = 0;
					timeSpent = 0;
					return;
				}
				//put source into priority queue;
				pq.add(start, input.estimatedDistanceToGoal(start, end));
				distTo.put(start, 0.0);
				Vertex current;
//				
				do {
					current = pq.removeSmallest();
					numStatesExplored++;
					solution.add(current);
					//visited.add(current);
					if(current.equals(end) && sw.elapsedTime() < timeout) {
						solutionWeight = distTo.get(current);
						outcome = SolverOutcome.SOLVED;
						break;
					}	
				
					
					for(WeightedEdge<Vertex> edge : input.neighbors(current)) {
						Vertex w = edge.to();
						if(!distTo.containsKey(w)) {
							distTo.put(w, distTo.get(current) + edge.weight());
							edgeTo.put(edge, edge.from());
							
							pq.add(w, distTo.get(w) + input.estimatedDistanceToGoal(w, end));
							
						}
						else {
							if(distTo.get(w) > distTo.get(current) + edge.weight()) {
								distTo.replace(w, distTo.get(current) + edge.weight()) ;
								edgeTo.put(edge, edge.from());
								if(pq.contains(w)) {
									pq.changePriority(w, distTo.get(w) + input.estimatedDistanceToGoal(w, end));
								}else {
									pq.add(w, distTo.get(w) + input.estimatedDistanceToGoal(w, end));
								}
						}
							
							
						}
						
					
					
					}
					if(sw.elapsedTime() > timeout) {
						outcome = SolverOutcome.TIMEOUT;
						break;
						}
					else if(pq.size() == 0) {
						outcome = SolverOutcome.UNSOLVABLE;
						break;
						}
				
				}while(!pq.isEmpty());
				
				timeSpent = sw.elapsedTime();
				
	}
	
//	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
//		Stopwatch sw = new Stopwatch();
//		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
//		distTo = new HashMap<>();
//		edgeTo = new HashMap<>();
//		solution = new ArrayList<>();
//		distTo.put(start, 0.0);
//		pq.add(new Tuple(start, input.estimatedDistanceToGoal(start, end)));
//		//search begins!
//		while(!pq.isEmpty()) {
//			//when search ends?
//			if(sw.elapsedTime()>timeout) {
//				outcome = SolverOutcome.TIMEOUT;
//				break;
//			}
//			if(pq.poll().v.equals(end)) {
//				outcome = SolverOutcome.SOLVED;
//				break;
//			}
//			Vertex v = pq.poll().v;
//			for(WeightedEdge<Vertex> edge : input.neighbors(v) ) {
//				Vertex w = edge.to();
//				if(!distTo.containsKey(w)) {
//					distTo.put(w, distTo.get(v)+edge.weight());
//					edgeTo.put(edge, edge.from());
//					pq.add(new Tuple(w,distTo.get(w)+input.estimatedDistanceToGoal(w, end)));
//				}else {
//					if(distTo.get(v)+edge.weight() < distTo.get(w)) {
//						distTo.replace(w, distTo.get(v)+edge.weight());
//						edgeTo.put(edge, edge.from());
//						if(pq.contains(w)) {
//							
//						}
//					}
//				}
//			}
//		}
//		
//		
//	}		
	@Override
    public SolverOutcome outcome() {
    	return outcome;
    }
	
    public List<Vertex> solution(){
    	return solution;
    	
    }
    public double solutionWeight() {
         return solutionWeight;
    }
    public int numStatesExplored() {
    	return numStatesExplored;
    }
    public double explorationTime() {
    	return timeSpent;
    }
    public static void main(String args) {
    	System.out.println("linda");
    }
    class Tuple implements Comparable<Tuple>{
		Vertex v;
		double distance;
		public Tuple(Vertex vertex, double dis) {
			v = vertex;
			distance = dis;
		}
		@Override
		public int compareTo(Tuple that) {
			// TODO Auto-generated method stub
			return Double.compare(this.distance, that.distance);
		}
    }
}

