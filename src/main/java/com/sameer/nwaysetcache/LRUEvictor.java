package nwaysetcache;

import java.util.*;
public class LRUEvictor<K> implements Evictor<K>{

	LinkedHashSet<K> set;

	public LRUEvictor(){
		set = new LinkedHashSet<>();
	}

	/*
	Will be called when key is added to cache. Should handle reassignments
	*/
	@Override
	public void keyAdded(K key){
		set.add(key);
	}

	/*
	Will be called when key has been accessed from the cache
	*/
	@Override
	public void keyAccessed(K key){
		if(!set.contains(key))
			return;

		set.remove(key);
		set.add(key);
	}

	/*
	Will be called when a key is removed from the cache
	*/
	@Override
	public void keyRemoved(K key){
		set.remove(key);
	}

	/*
	Will be called when cache is cleared
	*/
	@Override
	public void cacheCleared(){
		set.clear();
	}

	/*
	Will be called to request which key should be discarded
	Key should be discarded from this data structure as well
	*/
	@Override
	public K chooseEvict(){
		K evicted = set.iterator().next();
		set.remove(evicted);
		return evicted;
	}

}