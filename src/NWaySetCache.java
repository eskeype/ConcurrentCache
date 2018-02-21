import java.util.*;
public class NWaySetCache<K, V> implements Cache<K,V>{
	
	private int size;
	private ArrayList<Cache<K,V>> subcaches;

	public NWaySetCache(int n, int subcapacity){

		this.size = 0;
		subcaches = new ArrayList<Cache<K,V>>(n);

		for(int i = 0; i<n; i++){
			subcaches.add(createSubCache(subcapacity));
		}

	}

	private int getIndex(K key){
		if (key.hashCode()<0)
			return (key.hashCode() % subcaches.size()) + subcaches.size();
		return key.hashCode() % subcaches.size();
	}

	protected Cache<K,V> createSubCache(int subcapacity){
		return new LRUCache<K,V>(subcapacity);
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
		if(!containsKey(key))
			size++;
		subcaches.get(getIndex(key)).put(key, value);
	}

	/*
	Removes key value pair associated with key
	*/
	@Override
	public void remove(K key){
		if(containsKey(key))
			size--;
		subcaches.get(getIndex(key)).remove(key);
	}
}