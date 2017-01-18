package Aufgabe2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

	private HashMap<V, HashMap<V, Double>> adjacencyList;

	public AdjacencyListUndirectedGraph() {
		adjacencyList = new HashMap<>();
	}

	@Override
	public boolean addVertex(V v) {
		if (!adjacencyList.containsKey(v)) {
			adjacencyList.put(v, new HashMap<V, Double>());
			return true;
		}		
		return false;
	}

	@Override
	public boolean addEdge(V v, V w) {
		if (!containsVertex(v) || !containsVertex(w) || v.equals(w)) {
			throw new IllegalArgumentException();
		}
		if (!containsEdge(v, w)) {
			adjacencyList.get(v).put(w, 1.0);
			adjacencyList.get(w).put(v, 1.0);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {
		if (!containsVertex(v) || !containsVertex(w) || v.equals(w)) {
			throw new IllegalArgumentException();
		}
		if (!containsEdge(v, w)) {

			adjacencyList.get(v).put(w, weight);
			adjacencyList.get(w).put(v, weight);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(V v) {		
		return adjacencyList.containsKey(v);
	}

	@Override
	public boolean containsEdge(V v, V w) {
		if (!containsVertex(v) || !containsVertex(w)) {
			throw new IllegalArgumentException();
		}
		return adjacencyList.get(v).containsKey(w);
	}

	@Override
	public double getWeight(V v, V w) {
		if (!containsVertex(v) || !containsVertex(w)) {
			throw new IllegalArgumentException();
		}
		if (containsEdge(v, w)) {
			return adjacencyList.get(v).get(w);
		}
		return 0;
	}

	@Override
	public int getNumberOfVertexes() {
		return adjacencyList.size();
	}

	@Override
	public int getNumberOfEdges() {
		return getEdgeList().size();
	}

	@Override
	public List<V> getVertexList() {
		List<V> l = new LinkedList<V>();
		for (V key : adjacencyList.keySet()) {
			l.add(key);
		}
		return l;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		List<Edge<V>> l = new LinkedList<Edge<V>>();
		for (V v : adjacencyList.keySet()) {
			for (V w : adjacencyList.get(v).keySet()) {
				Edge<V> e = new Edge<V>(v, w, adjacencyList.get(v).get(w));
				if (!l.contains(e)) {
					l.add(e);
				}
			}
		}
		return l;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		List<V> l = new LinkedList<V>();
		for (V key : adjacencyList.get(v).keySet()) {
			l.add(key);
		}
		return l;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		List<Edge<V>> l = new LinkedList<Edge<V>>();
		for (V w : adjacencyList.get(v).keySet()) {
			l.add(new Edge<V>(v, w, adjacencyList.get(v).get(w)));
		}
		return l;
	}

	@Override
	public int getDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		return adjacencyList.get(v).size();
	}
}