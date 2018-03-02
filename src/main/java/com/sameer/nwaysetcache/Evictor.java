package nwaysetcache;

public interface Evictor<K>{

	/*
	This interface was designed to specify the replacement policy for a 
	cache given information regarding the cache's usage.

	An instance of this interface is to be used with the EvictorCache class.
	When an operation (e.g. access, key addition, key removal, etc) is made
	to the EvictorCache, a cooresponding function call is made by the cache's
	Evictor, to pass the cache's usage information to the Evictor. When the
	EvictorCache reaches its capacity, it queries the Evictor's chooseEvict
	method to decide which key to remove before associating a new key value 
	pair.
	*/

	/*
	Will be called when key is added to cache. Should handle reassignments
	*/
	public void keyAdded(K key);

	/*
	Will be called when key has been accessed from the cache
	*/
	public void keyAccessed(K key);

	/*
	Will be called when a key is removed from the cache. Should do nothing if key is not in cache
	*/
	public void keyRemoved(K key);

	/*
	Will be called when cache is cleared
	*/
	public void cacheCleared();

	/*
	Will be called to request which key should be discarded
	Key should be discarded from this data structure as well
	*/
	public K chooseEvict();

}