import concurrentcache.*;
import java.util.*;

public class LeastCache<K,V> implements Cache<K,V>{
	/*
	An example cache that evicts the least key according to natural order when
	the cache exceeds capacity
	*/

	private int capacity;
	private TreeMap<K,V> map;

	public LeastCache(int capacity){
		this.capacity = capacity;
		map = new TreeMap<K,V>();
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
		if(map.size() == capacity){
			map.pollFirstEntry();
		}
		map.put(key,value);
	}

	/*
	Removes key value pair associated with key if key exists
	*/
	@Override
	public void remove(K key){
		map.remove(key);
	}


	/*
	Clears contents of the cache
	*/
	@Override
	public void clear(){
		map.clear();
	}

}