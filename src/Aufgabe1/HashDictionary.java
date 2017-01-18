package Aufgabe1;

import java.util.LinkedList;

@SuppressWarnings({"hiding","unchecked", "rawtypes"})
public class HashDictionary<K,V> implements Dictionary<K,V> {
	
	
	private class Entry<K,V>{
		private K key;
		private V value;
		public Entry(K k, V v){
			key = k;
			value = v;
		}
	}
	
	private LinkedList<Entry>[] data;
	private int DEF_CAP = 13001;
	
	public HashDictionary() {
		data = new LinkedList[DEF_CAP];
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % DEF_CAP;
	}
	@Override
	public V insert(K key, V value) {
		int hashkey = hash(key);
		if(data[hashkey] == null){
			data[hashkey] = new LinkedList<Entry>();
		}
		data[hashkey].add(new Entry(key, value));
		return value;
	}

	@Override
	public V search(K key) {
		int hashkey = hash(key);
		for(Entry<K,V> e:data[hashkey]){
			if(e.key.equals(key)){
				return e.value;
			}
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int hashkey = hash(key);
		for(Entry<K,V> e : data[hashkey]){
			if(e.key.equals(key)) {
				V res = e.value;
				data[hashkey].remove(e);
				return res;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(LinkedList<Entry> l : data){
			if (l != null) {
				for(Entry<K,V> e : l) {
					if (e != null) { 
						sb.append(e.key).append(" ").append(e.value).append("\n");
					}
				}
			}
		}
		return sb.toString();
	}
	

}
