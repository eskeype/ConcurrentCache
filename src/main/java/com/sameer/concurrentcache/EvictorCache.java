package concurrentcache;

import java.util.*;

public class EvictorCache<K,V> implements Cache<K,V>{


	/*
	This class implements Cache using a custom Evictor. Any time a change is made
	to the EvictorCache, the corresponding information is passed to the EvictorCache.

	When the EvictorCache needs to evict a key, the cache queries its Evictor's
	chooseEvict method to dermine which key to evict.
	*/

	private Evictor<K> evictor;
	private HashMap<K,V> map;
	private int capacity;

	public EvictorCache(int capacity, Evictor<K> evictor)
	{
		map = new HashMap<>();
		this.evictor = evictor;
		this.capacity = capacity;
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
	public synchronized V get(K key){
		evictor.keyAccessed(key);
		return map.get(key);
	}

	/*
	Associates key value pair into cache
	*/
	@Override
	public synchronized void put(K key, V value){
		if(!map.containsKey(key) && capacity == map.size()){
			map.remove(evictor.chooseEvict());
		}
		map.put(key,value);
		evictor.keyAdded(key);
	}

	/*
	Removes key value pair associated with key if key exists
	*/
	@Override
	public synchronized void remove(K key){
		map.remove(key);
		evictor.keyRemoved(key);
	}


	/*
	Clears contents of the cache
	*/
	@Override
	public synchronized void clear(){
		evictor.cacheCleared();
		map.clear();
	}
}