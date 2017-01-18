package Aufgabe3.src;

import Aufgabe2.AdjacencyListUndirectedGraph;
import Aufgabe2.Graph;
import Aufgabe3.sim.SYSimulation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class DijkstraShortestPath<V> {

	private Graph<V> graph;
	private Map<V,V> path;
	private V start;
	private V goal;
	private Map<V, Double> distance;

	@SuppressWarnings("unchecked")
	public DijkstraShortestPath(Graph<V> g) {
		graph = g;
		path = new TreeMap<V, V>();
		distance = new TreeMap<V, Double>();
		start = (V) new Integer(43);
		goal = (V) new Integer(175);
	};

	public boolean searchShortestPath(V s, V g) {
		start = s;
		goal = g;
		List<V> l = new LinkedList<V>();
		for (V v : graph.getVertexList()) {
			distance.put(v, Double.POSITIVE_INFINITY);
			if (start.equals(v)) {
				distance.put(v, 0.0);
				l.add(v);
				//path.put(v, null);
			}
		}
		while(!l.isEmpty()) {
			V v = l.remove(getMin(l));
			if (v.equals(goal)) {
				return true;
			}
			for (V w : graph.getAdjacentVertexList(v)) {
				if (distance.get(w) == Double.POSITIVE_INFINITY) {
					l.add(w);
				}
				if (distance.get(v) + graph.getWeight(v, w) < distance.get(w)) {
					path.put(w, v);
					distance.put(w, distance.get(v) + graph.getWeight(v, w));
				}
			}
		}
		return false;
	}

	private int getMin(List<V> l) {
		Double min = Double.POSITIVE_INFINITY;
		Double dist = 0.0;
		V w = null;
		for (V v : l) {
			dist = distance.get(v);
			if (dist < min) {
				min = dist;
				w = v;
			}
		}
		return l.indexOf(w);
	}

	public List<V> getShortestPath() {
		if (searchShortestPath(start, goal)) {
			List<V> l = new LinkedList<V>();
			V station = goal;
			while (!station.equals(start)) {
				l.add(0, station);
				station = path.get(station);
			}
			l.add(0, start);
			return l;
		}
		return null;
	}

	public double getDistance() {
		if (searchShortestPath(start, goal)) {
			return distance.get(goal);
		}
		return Double.POSITIVE_INFINITY;
	}

	public static void main(final String[] args) throws FileNotFoundException {
		Graph<Integer> g = new AdjacencyListUndirectedGraph<Integer>();
		Scanner in = new Scanner(new File("Aufgabe3/src/ScotlandYard.txt"));
        while (in.hasNext()) {
        	int a = in.nextInt();
        	int b = in.nextInt();
        	if (in.hasNext("UBahn")) {
            	g.addVertex(a);
            	g.addVertex(b);
            	g.addEdge(a, b, 5);            	
            }
            if (in.hasNext("Taxi")) {
            	g.addVertex(a);
            	g.addVertex(b);
            	g.addEdge(a, b, 3);            	
            }
            if (in.hasNext("Bus")) {
            	g.addVertex(a);
            	g.addVertex(b);
            	g.addEdge(a, b, 2);            	
            }
            in.nextLine();
        }
        in.close();

        DijkstraShortestPath<Integer> dsp = new DijkstraShortestPath<Integer>(g);
        System.out.println(dsp.getShortestPath().toString());
        System.out.println(dsp.getDistance());

        SYSimulation sim;
		try {
			sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		int pre = 0;
		Color c = Color.RED;
		sim.startSequence("1 - 199");
		for (int station : dsp.getShortestPath()) {
			if(pre != 0) {
				if (g.getWeight(pre, station) == 2) {
					c = Color.GREEN;
				} else if (g.getWeight(pre, station) == 3) {
					c = Color.YELLOW;
				} else {
					c = Color.RED;
				}
				sim.drive(pre, station, c);
			}
			pre = station;
			sim.visitStation(station);
		}
		sim.stopSequence();
	}
}
