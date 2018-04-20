package concurrentcache;

public interface Cache<K,V>{
	/*
	This interface specificies basic functionality of any cache data structure.
	It exists to allow for different caching strategies, like LRU, MRU, etc. to
	be used and built as per the user's preference. Additionally, the n-way set
	associative cache itself will implement this interface.

	*/

	/*
	Returns the current number of key value pairs contained in the cache
	*/
	public int size();

	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/
	public boolean containsKey(K key);

	/*
	Returns value associated with input key
	*/
	public V get(K key);

	/*
	Associates key value pair into cache
	*/
	public void put(K key, V value);

	/*
	Removes key value pair associated with key if key exists
	*/
	public void remove(K key);


	/*
	Clears contents of the cache
	*/
	public void clear();

}