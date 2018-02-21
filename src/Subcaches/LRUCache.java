import java.util.*;
public class LRUCache<K,V> implements Cache<K,V>{

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

	/*
	Returns the current number of key value pairs contained in the cache
	*/
	@Override	
	public int size(){
		return map.size();
	}

	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/
	@Override
	public boolean containsKey(K key){
		return map.containsKey(key);
	}

	/*
	Returns value associated with input key
	*/
	@Override
	public V get(K key){
		return map.get(key);
	}

	/*
	Associates key value pair into cache
	*/
	@Override
	public void put(K key, V value){
		map.put(key,value);
	}

	/*
	Removes key value pair associated with key
	*/
	@Override
	public void remove(K key){
		map.remove(key);
	}

}
