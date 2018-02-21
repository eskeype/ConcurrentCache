import java.util.*;
public class MRUCache<K,V> implements Cache<K,V>{

	//interface will change so that keys can be removed by a user. Come on dude.
	
	private final int capacity;
	private LinkedHashMap<K,V> map;

	public MRUCache(int capacity){

		this.capacity = capacity;
		map = new LinkedHashMap<>(capacity, 0.75f, true);
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
			map.remove(map.keySet().iterator().next());
		}

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