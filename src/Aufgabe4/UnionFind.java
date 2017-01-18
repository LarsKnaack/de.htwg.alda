package Aufgabe4;

import java.util.HashMap;
import java.util.Map;


public class UnionFind {
	
	UnionFind() {
		index = 0;
		map = new HashMap<>();
	}

	Map<Integer, Integer> map;
	private int index;
	
	/*
	 * Adds a new element to this union find structure 
	 * and returns its index
	*/
	public int addElement(){
		index++;
		map.put(index, 0);
		return index;
	}
	
	/*
	 * Finds and returns the representative for the given element
	 */
	public int find(int element){
		if(map.get(element) <= 0){
			return element;
		}
		return find(map.get(element));
	}
	
	public int size(){
		return map.size();
	}
	
	/*
	 * Merges the classes in which element1 and element2 are, 
	 * by giving them the same representative
	 */
	public void union(int element1, int element2){
		element1 = find(element1);
		element2 = find(element2);

		if(element1 == element2){
			return;
		}
		
		unionByHeight(element1, element2);	
	}

	private void unionByHeight(int element1, int element2) {
		if (map.get(element1) < map.get(element2)) {
			map.put(element2, element1);
		} else {
			if (map.get(element1) == map.get(element2)) {
				map.put(element2, element1);
				map.put(element1, map.get(element1) - 1);
			} else {
				map.put(element1, element2);
			}
		}
	}
	
	public String toString(int element){
		StringBuilder sb = new StringBuilder();
		while(map.get(element) >= 0) {
		sb.append(element).append("-----");
		element = map.get(element);
		}
		return sb.append(element).toString();
	}
}
