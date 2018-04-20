package concurrentcache;

import java.util.*;
public class ConcurrentCache<K, V> implements Cache<K,V>{
	
	private int size;
	private ArrayList<Cache<K,V>> subcaches;

	public ConcurrentCache(int n, int subcapacity){

		this.size = 0;
		subcaches = new ArrayList<Cache<K,V>>(n);

		for(int i = 0; i<n; i++){
			subcaches.add(createSubCache(subcapacity));
		}

	}

	private int getIndex(K key){
		int hashModSize = key.hashCode() % subcaches.size();
		//second term ensures that output is a valid positive integer
		return hashModSize + (hashModSize < 0 ? subcaches.size() : 0 );
	}

	protected Cache<K,V> createSubCache(int subcapacity){
		return new EvictorCache<K,V>(subcapacity, createEvictor());
	}

	protected Evictor<K> createEvictor(){
		return new LRUEvictor<K>();
	}

	/*
	Returns the current number of key value pairs contained in the cache
	*/
	@Override
	public int size(){
		return size;
	}

	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/
	@Override
	public boolean containsKey(K key){
		return subcaches.get(getIndex(key)).containsKey(key);
	}

	/*
	Returns value associated with input key
	*/
	@Override
	public V get(K key){
		return subcaches.get(getIndex(key)).get(key);
	}

	/*
	Associates key value pair into cache
	*/
	@Override
	public void put(K key, V value){

		int oldSize = subcaches.get(getIndex(key)).size();

		subcaches.get(getIndex(key)).put(key, value);

		size += subcaches.get(getIndex(key)).size() - oldSize;
	}

	/*
	Removes key value pair associated with key
	*/
	@Override
	public void remove(K key){

		int oldSize = subcaches.get(getIndex(key)).size();

		subcaches.get(getIndex(key)).remove(key);
		
		size += subcaches.get(getIndex(key)).size() - oldSize;
	}

	@Override
	public void clear(){
		for(Cache<K,V> subcache : subcaches){
			subcache.clear();
		}
		size = 0;
	}
}