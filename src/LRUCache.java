import java.util.*;
public class LRUCache<K,V>{

	private final int capacity;
	private LinkedHashMap<K,V> map;

	public LRUCache(int capacity){
		this.capacity = capacity;

		this.map = new LinkedHashMap<K, V>(capacity, 0.75f, true){
        	protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
            }
        };
	}
	
	public int size(){
		return map.size();
	}
	/*
	Returns the current number of key value pairs contained in the cache
	*/

	public boolean containsKey(K key){
		return map.containsKey(key);
	}
	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/


	public V get(K key){
		return map.get(key);
	}
	/*
	Returns value associated with input key
	*/

	public void put(K key, V value){
		map.put(key,value);
	}
	/*
	Associates key value pair into cache
	*/
}
