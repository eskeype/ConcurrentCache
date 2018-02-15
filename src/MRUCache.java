import java.util.*;
public class MRUCache<K,V>{

	//interface will change so that keys can be removed by a user. Come on dude.
	
	private final int capacity;
	private HashMap<K,V> map;
	private K mostRecentlyUsed;

	public MRUCache(int capacity){

		this.capacity = capacity;
		map = new HashMap<>(capacity);
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

		mostRecentlyUsed = key;
		return map.get(key);
	}
	/*
	Returns value associated with input key
	*/

	public void put(K key, V value){

		map.put(key,value);

		if(map.size() > capacity){
			map.remove(mostRecentlyUsed);
		}

		mostRecentlyUsed = key;
	}
	/*
	Associates key value pair into cache
	*/
}