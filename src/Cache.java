public interface Cache<K,V>{
	/*
	This interface specificies basic functionality of any cache data structure.
	It exists to allow for different caching strategies, like LRU, MRU, etc. to
	be used and built as per the user's preference.

	Aditionally, all classes implementing Cache should include a constructor with
	the following signature:

	public IMPLEMENTING_CLASS(int capacity)

	where capacity signifies the largest allowable number of pairs to be contained
	in the cache.
	*/

	public int size();
	/*
	Returns the current number of key value pairs contained in the cache
	*/

	public boolean containsKey(K key);
	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/


	public V get(K key);
	/*
	Returns value associated with input key
	*/

	public void put(K key, V value);
	/*
	Associates key value pair into cache
	*/

	//caches should be able to remove too. Come on dude.

}