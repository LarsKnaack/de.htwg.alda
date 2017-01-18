package Aufgabe1;

import java.util.Map;

public class MapDictionary <K,V> implements Dictionary <K,V>{
	
	private Map <K,V> map;
	
	public MapDictionary (Map<K,V> m) {
		map = m;
	}

	@Override
	public V insert(K key, V value) {
		return map.put(key, value);
	}

	@Override
	public V search(K key) {
		return map.get(key);
	}

	@Override
	public V remove(K key) {
		return map.remove(key);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MapDictionary: \n");
		for (Map.Entry<K, V> e : map.entrySet()) {
			sb.append(e.getKey() + " " + e.getValue() + "\n");
		}
		return sb.toString();
	}
	

}
