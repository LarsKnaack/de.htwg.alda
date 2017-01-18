package Aufgabe2;
//tiefensuche mit keller, breitensuche mit schlange
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class GraphTraversion {

	public <V> List<V> depthFirstSearch(Graph<V> g, V s) {
		List<V> visited = new LinkedList<V>();
		visitD(s, g, visited);
		return visited;
	}

	public <V> List<V> breadthFirstSearch(Graph<V> g, V s) {
		List<V> visited = new LinkedList<V>();
		visitB(s, g, visited);
		return visited;
	}

	<V> List<V> topologicalSort(DirectedGraph<V> g) {
		List<V> ts = new LinkedList<V>();
		Map<V, Integer> inDegree = new TreeMap<V, Integer>();
		Queue<V> q = new LinkedList<V>();
		int i = 1;
		for (V v : g.getVertexList()) {
			inDegree.put(v, g.getPredecessorVertexList(v).size());
			if (inDegree.get(v) == 0) {
				q.add(v);
			}
		}
		while (!q.isEmpty()) {
			V v = q.remove();
			ts.add(v);
		    for (V w : g.getSuccessorVertexList(v)) {
		    	inDegree.put(w, inDegree.get(w) - 1);
			    if (inDegree.get(w) == 0) {
				    q.add(w);
				    inDegree.put(w, inDegree.get(w) - i);
				}
			}
		}
		if (ts.size() != g.getNumberOfVertexes()) {
			return null;
		}
		return ts;
	}

	<V> void visitD(V v, Graph<V> g, List<V> visited) {
		Deque<V> stk = new LinkedList<V>();
		stk.push(v);
		while(!stk.isEmpty()) {
			v = stk.pop();
			if (visited.contains(v)) {
				continue;
			}
			visited.add(v);
			for (V w : g.getAdjacentVertexList(v)) {
				if(!visited.contains(w)) {
					stk.push(w);
				}
			}
		}
	}

	<V> void visitB(V v, Graph<V> g, List<V> visited) {
		Queue<V> q = new LinkedList<V>();
		q.add(v);
		while(!q.isEmpty()) {
			v = q.remove();
			if (visited.contains(v)) {
				continue;
			}
			visited.add(v);
			for (V w : g.getAdjacentVertexList(v)) {
				if(!visited.contains(w)) {
					q.add(w);
				}
			}
		}
	}

	public static void main(final String[] args) {
		GraphTraversion gt = new GraphTraversion();
		Graph<Integer> g = new AdjacencyListUndirectedGraph<Integer>();
		for (int i = 0; i < 7; ++i) {
			g.addVertex(i);
		}
		for (int i = 0; i < 5; ++i) {
			g.addEdge(i, 6);
		}
		List<Integer> l1 = gt.depthFirstSearch(g, new Integer(6));
		List<Integer> l2 = gt.breadthFirstSearch(g, new Integer(6));
		System.out.println(l1.toString());
		System.out.println(l2.toString());
		
		Graph<Integer> gu = new AdjacencyListDirectedGraph<Integer>();
		for (int i = 0; i < 7; ++i) {
			gu.addVertex(i);
		}
		for (int i = 0; i < 4; ++i) {
			gu.addEdge(6, i);
		}
		gu.addEdge(4, 6);
		gu.addEdge(4, 3);
		gu.addEdge(4, 5);
		gu.addEdge(1, 2);
		gu.addEdge(0, 5);
		gu.addEdge(3, 2);
		gu.addEdge(2, 5);
		List<Integer> l3 = gt.depthFirstSearch(gu, new Integer(4));
		List<Integer> l4 = gt.breadthFirstSearch(gu, new Integer(4));
		List<Integer> l5 = gt.topologicalSort((DirectedGraph<Integer>) gu);
		System.out.println(l3.toString());
		System.out.println(l4.toString());
		System.out.println(l5.toString());
		
//Aufgabe5:
		Graph<String> gs = new AdjacencyListDirectedGraph<String>();
		gs.addVertex("Unterhose");
		gs.addVertex("Hemd");
		gs.addVertex("Hose");
		gs.addVertex("G�rtel");
		gs.addVertex("Unterhemd");
		gs.addVertex("Mantel");
		gs.addVertex("Str�mpfe");
		gs.addVertex("Schal");
		gs.addVertex("M�tze");
		gs.addVertex("Schuhe");
		gs.addVertex("Pullover");
		gs.addVertex("Handschuhe");
		
		gs.addEdge("Unterhose", "Hose");
		gs.addEdge("Hose", "G�rtel");
		gs.addEdge("Unterhemd", "Hemd");
		gs.addEdge("Hemd", "Pullover");
		gs.addEdge("Pullover", "Mantel");
		gs.addEdge("Str�mpfe", "Schuhe");
		gs.addEdge("Mantel", "Handschuhe");
		gs.addEdge("Mantel", "M�tze");
		gs.addEdge("Mantel", "Schal");
		gs.addEdge("Hose", "Schuhe");
		
		List<String> l6 = gt.topologicalSort((DirectedGraph<String>) gs);
		System.out.println(l6.toString());
	}
}
