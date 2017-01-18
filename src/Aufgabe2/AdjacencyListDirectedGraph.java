package Aufgabe2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {

	private HashMap<V, HashMap<V, Double>> preList;
	private HashMap<V, HashMap<V, Double>> sucList;

	public AdjacencyListDirectedGraph() {
		preList = new HashMap<>();
		sucList = new HashMap<>();
	}

	@Override
	public boolean addVertex(V v) {
		if (!preList.containsKey(v)) {
			preList.put(v, new HashMap<>());
			sucList.put(v, new HashMap<>());
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
			Edge<V> k = new Edge<>(v, w);
			sucList.get(v).put(w, k.weight);
			preList.get(w).put(v, k.weight);
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
			Edge<V> k = new Edge<>(v, w, weight);
			sucList.get(v).put(w, k.weight);
			preList.get(w).put(v, k.weight);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(V v) {
		return sucList.containsKey(v);
	}

	@Override
	public boolean containsEdge(V v, V w) {
		if (!containsVertex(v) || !containsVertex(w)) {
			throw new IllegalArgumentException();
		}
		return sucList.get(v).containsKey(w);
	}

	@Override
	public double getWeight(V v, V w) {
		if (!containsVertex(v) || !containsVertex(w)) {
			throw new IllegalArgumentException();
		}
		if (containsEdge(v, w)) {
			return sucList.get(v).get(w);
		}
		return 0;
	}

	@Override
	public int getNumberOfVertexes() {
		return sucList.size();
	}

	@Override
	public int getNumberOfEdges() {
		return getEdgeList().size();
	}

	@Override
	public List<V> getVertexList() {
		List<V> l = new LinkedList<V>();
		for (V key : sucList.keySet()) {
			l.add(key);
		}
		return l;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		List<Edge<V>> l = new LinkedList<Edge<V>>();
		for (V v : sucList.keySet()) {
			for (V w : sucList.get(v).keySet()) {
				l.add(new Edge<>(v, w, sucList.get(v).get(w)));
			}
		}
		return l;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		List<V> l = new LinkedList<>();
		for (V key : sucList.get(v).keySet()) {
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
		for (V w : sucList.get(v).keySet()) {
			l.add(new Edge<V>(v, w, sucList.get(v).get(w)));
		}
		return l;
	}

	@Override
	public int getInDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		return preList.get(v).size();
	}

	@Override
	public int getOutDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		return sucList.get(v).size();
	}

	@Override
	public List<V> getPredecessorVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		List<V> l = new LinkedList<V>();
		for (V key : preList.get(v).keySet()) {
			l.add(key);
		}
		return l;
	}

	@Override
	public List<V> getSuccessorVertexList(V v) {
		return getAdjacentVertexList(v);
	}

	@Override
	public List<Edge<V>> getOutgoingEdgeList(V v) {
		return getIncidentEdgeList(v);
	}

	@Override
	public List<Edge<V>> getIncomingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		List<Edge<V>> l = new LinkedList<Edge<V>>();
		for (V w : preList.get(v).keySet()) {
			l.add(new Edge<>(v, w, preList.get(v).get(w)));
		}
		return l;
	}
}